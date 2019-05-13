package com.provider.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.provider.model.Book;

@RestController
@RequestMapping("/provider")
public class ProviderController {
	
	@Value("${server.port}")
	private String port;
	
	@RequestMapping(value = "/getProvider", method = RequestMethod.GET)
	public String getProvider(@RequestParam("name")String name) {
		return port;
	}
	
	@RequestMapping(value = "/getProviderList", method = RequestMethod.GET)
	public List<Book> getProviderList(@RequestParam("name")String name) {
		List<Book> list = new ArrayList<>();
		list.add(new Book("书名", name, new Date()));
		list.add(new Book("2", name, new Date()));
		list.add(new Book("3", name, new Date()));
		return list;
	}
	
	@RequestMapping(value = "/getProviderStr", method = RequestMethod.POST)
	public String getProviderStr(@RequestParam("name")String name) {
		return "provider"+name;
	}
	
	@RequestMapping(value = "/getProviderPost", method = RequestMethod.POST)
	public List<Book> getProviderPost(@RequestBody Book book) {
		List<Book> list = new ArrayList<>();
		list.add(book);
		return list;
	}
	
	@RequestMapping(value = "/getProviderPostMulMap", method = RequestMethod.POST)
	public List<Book> getProviderPostMulMap(Book book) {
		List<Book> list = new ArrayList<>();
		list.add(book);
		return list;
	}
	
	@RequestMapping(value = "/getProviderPostForm", method = RequestMethod.POST)
	public List<Book> getProviderPostForm(Book book) {
		List<Book> list = new ArrayList<>();
		list.add(book);
		return list;
	}

}
