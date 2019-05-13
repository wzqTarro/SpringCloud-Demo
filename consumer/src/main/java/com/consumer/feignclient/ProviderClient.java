package com.consumer.feignclient;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.consumer.model.Book;

import feign.Headers;
import feign.Logger;
import feign.RequestLine;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.spring.SpringFormEncoder;

@FeignClient(name = "provider-service")
public interface ProviderClient {
	@RequestMapping(value = "/provider/getProvider", method = RequestMethod.GET)
	String getProvider(@RequestParam("name")String name);
	
	@RequestMapping(value = "/provider/getProviderList", method = RequestMethod.GET)
	List<Book> getProviderList(@RequestParam("name")String name);
	
	@RequestMapping(value = "/provider/getProviderStr", method = RequestMethod.POST)
	String getProviderStr(@RequestParam("name")String name);
	
	@RequestMapping(value = "/provider/getProviderPost", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	List<Book> getProviderPost(Book book);
	
	@Headers("Content-Type: application/x-www-form-urlencoded")
	@RequestMapping(value = "/provider/getProviderPostForm", method = RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	List<Book> getProviderPostForm(Map<String, Object> book);
	
	@RequestMapping(value = "/provider/getProviderPostMulMap", method = RequestMethod.POST, consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	List<Book> getProviderPostMulMap(MultiValueMap<String, Object> param);
}
