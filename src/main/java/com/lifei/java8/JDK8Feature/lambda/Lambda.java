package com.lifei.java8.JDK8Feature.lambda;

import java.util.concurrent.Callable;

/**
 * JDK1.8 LambdaAPI
 * 相关知识点:
 * <br>1.@FunctionalInterface函数式接口,除去Object类的方法,只有一个抽象方法的接口(简单的区别方式,可以被@FunctionalInterface注解的类)
 * <br>2.常用的函数式接口,
 * java.lang.Runnable,
 * java.util.concurrent.Callable<V>,
 * java.util.Comparator<T>等等
 * <br>3,另外在包java.util.function下,几乎所有的接口都是函数式接口,
 * <br>主要频繁使用的是(泛型)
 * supplier 代表一个输出
 * consumer 代表一个输入
 * BiConsumer 代表两个输入
 * Function 代表一个输入一个输出
 * BiFunction 代表两个输入一个输出
 * UnaryOperator 相同类型的一个输入一个输出
 * BinaryOperator 相同类型的两个输入一个输出
 * 其他类基本是具体类型的上述方法
 * @author lifei
 *
 */
public class Lambda {

	public static void main(String[] args) {
		test_runnable();
		test_callable();
		test_myFI();
	}
	
	
	public static void test_runnable() {
		//Lambda表达式
		Runnable r1 = () -> {System.out.println("Lambda表达式 run");};
		//普通方式
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("普通方式 run");
			}
		};
		
		r1.run();
		r2.run();
	}
	public static void test_callable() {
		//Lambda表达式
		//因为Callable  call() throws Exception 所以需要在使用c1的时候处理异常,而在赋值时不需要
		Callable<String> c1 = () -> {return "Lambda表达式 call";};
		//普通方式
		Callable<String> c2 = new Callable<String>() {
			@Override
			public String call() {
				return "普通方式 call";
			}
		};
		
		try {
			System.out.println(c1.call());
			System.out.println(c2.call());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void test_myFI() {
		
		MyFI myfi = () -> {System.out.println("无参数无返回值的自定义函数式接口");};
		MyPFI mypfi = (int x,int y) -> {System.out.println("有参数无返回值的自定义函数式接口 :入参:"+x+" "+y);};		
		MyRFI myrfi = () -> {
			System.out.println("无参数有返回值的自定义函数式接口 :return 0");
			return 0;
		};
		MyPRFI myprfi = (int x,int y) -> {
			int r = x*y+x+y;
			System.out.println("有参数有返回值的自定义函数式接口 :return "+r);
			return r;
		};
		
		myfi.add();
		mypfi.add(10,10);
		myrfi.add();
		myprfi.add(20,4);
	}
}



/**
lambda表达式示例:
() -> {}                          // 无参，无返回值
() -> { System.out.println(1); }  // 无参，无返回值
() -> System.out.println(1)       // 无参，无返回值（上面的简写）
() -> { return 100; }             // 无参，有返回值
() -> 100                         // 无参，有返回值（上面的简写）
() -> null                        // 无参，有返回值（返回null）
(int x) -> { return x+1; }        // 单个参数，有返回值
(int x) -> x+1                    // 单个参数，有返回值（上面的简写）
(x) -> x+1                        // 单个参数，有返回值（不指定参数类型，多个参数必须用括号）
 x -> x+1                          // 单个参数，有返回值（不指定参数类型）

lambda表达式注意事项:
1.参数列表中,参数类型要么都省略,要么都不省略,不能部分省略
	错误示例:(x,int y) -> x+y
2.参数列表中,参数类型不能用final修饰
	错误示例:(int x,final int y) -> x+y
3.不能把lambda表达式赋给非函数式接口,如果非要赋值,需要进行强制转换
	错误示例:Object obj = ()->"hello"
	正确示例:Object obj = (Supplier<?>)()->"hello"
4.lambda不需要也不允许使用throws

lambda表达式官方文档地址:
https://docs.oracle.com/javase/specs/jls/se8/html/jls-15.html#jls-15.27

**/