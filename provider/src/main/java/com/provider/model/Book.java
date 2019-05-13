package com.provider.model;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable{
	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = 5928112476725781828L;
	private String id;
	private String name;
	private Date publishDate;
	
	public Book(String id, String name, Date publishDate) {
		super();
		this.id = id;
		this.name = name;
		this.publishDate = publishDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
}
