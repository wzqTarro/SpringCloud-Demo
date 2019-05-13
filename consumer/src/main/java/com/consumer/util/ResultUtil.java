package com.consumer.util;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

public class ResultUtil {
	private static String code;
	private String msg;
	
	static {
		code = "123";
	}
	
	@PostConstruct
	private static void setCode() {
		code = "456";
	}
	
	public static void getcode() {
		System.err.println(code);
	} 
	public void getMsg() {
		System.err.println("getMsg");
	}
	

}
