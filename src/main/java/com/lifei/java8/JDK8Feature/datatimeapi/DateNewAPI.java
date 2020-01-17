package com.lifei.java8.JDK8Feature.datatimeapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * JDK1.8 新的日期时间api
 * @author Administrator
 *
 */
public class DateNewAPI {

	public static void main(String[] args) {
		//当前日期
		LocalDate date = LocalDate.now();
		//转字符串
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String  dateStr = date.format(formatter);
		System.out.println(dateStr);
		//修改时间,使用plusXXX()方法
		date = date.plusDays(-1);
		System.out.println(date.toString());
		
		//时间函数 其他使用方法同LocalDate
		LocalDateTime dt = LocalDateTime.now();
		
	}
}
