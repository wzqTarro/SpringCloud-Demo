package com.consumer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.consumer.feignclient.ProviderClient;
import com.consumer.model.Book;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProviderClient providerClient;
	
	@RequestMapping(value = "/getProvider", method = RequestMethod.GET)
	public String getProvider() {		
		Map<String, Object> param = new HashMap<>();
		param.put("name", "中文");
		String result = restTemplate.getForObject("http://provider-service/provider/getProvider?name={name}", String.class, param);
		return result;
	}
	
	@RequestMapping(value = "/getProviderList", method = RequestMethod.GET)
	public Object getProviderList() {
		Map<String, Object> param = new HashMap<>();
		param.put("name", "123");
		List<Map<String, Object>> result = restTemplate.getForObject("http://provider-service/provider/getProviderList?name={name}", List.class, param);
		return result;
	}
	
	@RequestMapping(value = "/getProviderStr", method = RequestMethod.POST)
	public String getProviderStr() {
		// 请求头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		// 请求参数
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("name", "中文");
		
		// 请求体
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
		
		String result = restTemplate.postForObject("http://provider-service/provider/getProviderStr", httpEntity, String.class);
		return result;
	}
	
	@RequestMapping(value = "/getProviderPost", method = RequestMethod.POST)
	public List<?> getProviderPost() {
		// 请求头
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		// 请求参数
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("id", "1");
		param.add("name", "2");
		param.add("publishDate", new Date());
		
		// 请求体
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, headers);
		
		List<Map<String, Object>> result = restTemplate.postForObject("http://provider-service/provider/getProviderPost", httpEntity, List.class);
		return result;
	}
	
	@RequestMapping(value = "/getProviderFeign", method = RequestMethod.GET)
	public String getProviderFeign() {		
		String result = providerClient.getProvider("中文");
		return result;
	}
	
	@RequestMapping(value = "/getProviderListFeign", method = RequestMethod.GET)
	public Object getProviderListFeign() {
		List<Book> result = providerClient.getProviderList("123");
		return result;
	}
	
	@RequestMapping(value = "/getProviderStrFeign", method = RequestMethod.POST)
	public String getProviderStrFeign() {
		String result = providerClient.getProviderStr("中文");
		return result;
	}
	
	@RequestMapping(value = "/getProviderPostFeign", method = RequestMethod.POST)
	public List<?> getProviderPostFeign() {
		Book book = new Book("1", "2", new Date());
		List<Book> result = providerClient.getProviderPost(book);
		return result;
	}
	
	@RequestMapping(value = "/getProviderPostMulMapFeign", method = RequestMethod.POST)
	public List<?> getProviderPostMulMapFeign() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		System.err.println(request.getSession().getServletContext().getRealPath("/img"));
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("id", "1");
		param.add("name", "2");
		param.add("publishDate", new Date());
		List<Book> result = providerClient.getProviderPostMulMap(param);
		return result;
	}
	
	@RequestMapping(value = "/getProviderPostFormFeign", method = RequestMethod.POST)
	public List<?> getProviderPostFormFeign() {
		Book book = new Book("1", "2", null);
		Map<String, Object> param = new HashMap<>();
		param.put("id", "1");
		param.put("name", "书名");
		param.put("publishDate", new Date());
		List<Book> result = providerClient.getProviderPostForm(param);
		return result;
	}
}
