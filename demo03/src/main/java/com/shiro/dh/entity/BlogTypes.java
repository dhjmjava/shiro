package com.shiro.dh.entity;import javax.persistence.Column;import javax.persistence.Entity;import javax.persistence.GeneratedValue;import javax.persistence.Id;import javax.persistence.Table;@Entity@Table(name="t_blog_types")public class BlogTypes implements java.io.Serializable {	private static final long serialVersionUID = -8436758490590285623L;		private long id;	private String name;	private Boolean isUse;	private String describe;	private int order;		public BlogTypes(){}		@Id	@GeneratedValue 	public long getId() {		return id;	}	public void setId(long id) {		this.id = id;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	@Column(name="is_use")	public Boolean getIsUse() {		return isUse;	}	public void setIsUse(Boolean isUse) {		this.isUse = isUse;	}	public String getDescribe() {		return describe;	}	public void setDescribe(String describe) {		this.describe = describe;	}	public int getOrder() {		return order;	}	public void setOrder(int order) {		this.order = order;	}	}