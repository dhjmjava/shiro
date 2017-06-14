package com.shiro.dh.entity;import java.util.Date;import java.util.List;import javax.persistence.Transient;import org.springframework.format.annotation.DateTimeFormat;public class Blog implements java.io.Serializable {		private static final long serialVersionUID = 1135042213682650700L;		private long id;	private String title;	private String content;	private String author;	private String keyword;	private Boolean isUse;/** 是否显示 0-不显示 1-显示 **/	private int blogType;	private int readCount;//阅读量	@DateTimeFormat( pattern = "yyyy-MM-dd" )	private Date publishTime;	//blog下的评论	private List<BlogComments> comments;		//每月博客数	@Transient	private int blogCount;	//月份	@Transient	private String months;		//各类型总数	@Transient	private int typeCount;		//类型名称	@Transient	private String name;		public Blog(){}	public long getId() {		return id;	}	public void setId(long id) {		this.id = id;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public String getAuthor() {		return author;	}	public void setAuthor(String author) {		this.author = author;	}	public String getKeyword() {		return keyword;	}	public void setKeyword(String keyword) {		this.keyword = keyword;	}	public Date getPublishTime() {		return publishTime;	}	public void setPublishTime(Date publishTime) {		this.publishTime = publishTime;	}		public Boolean getIsUse() {		return isUse;	}	public void setIsUse(Boolean isUse) {		this.isUse = isUse;	}	public int getBlogType() {		return blogType;	}	public void setBlogType(int blogType) {		this.blogType = blogType;	}	public int getReadCount() {		return readCount;	}	public void setReadCount(int readCount) {		this.readCount = readCount;	}	public List<BlogComments> getComments() {		return comments;	}	public void setComments(List<BlogComments> comments) {		this.comments = comments;	}	public int getBlogCount() {		return blogCount;	}	public void setBlogCount(int blogCount) {		this.blogCount = blogCount;	}	public String getMonths() {		return months;	}	public void setMonths(String months) {		this.months = months;	}	public int getTypeCount() {		return typeCount;	}	public void setTypeCount(int typeCount) {		this.typeCount = typeCount;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	};	}