/**  
 * Project Name:blog  
 * File Name:Page.java  
 * Package Name:com.blog.util  
 * Date:2016年11月2日下午10:00:17  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.util;  

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shiro.dh.constants.Constants;

/**  
 * ClassName:Page <br/>  
 * Function: 分页. <br/>  
 * Date:     2016年11月2日 下午10:00:17 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class Page<T> implements Serializable {
	
	/**  
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).  
	 * @since JDK 1.7  
	 */
	 
	private static final long serialVersionUID = 3793874900720582320L;

	private static Logger logger = LoggerFactory.getLogger(Page.class);
	
	
	/**
	 * 当前页
	 */
	public int currPage = 1;
	/**
	 * 总记录数
	 */
	public int totalCount;
	/**
	 * 总页数
	 */
	public int totalPageCount;
	/**
	 * 每页的记录条数
	 */
	public int pageSize;
	/**
	 * 显示的统计标题
	 */
	public String pageTitle = "";
	/**
	 * 当前页的记录
	 */
	public List<T> page;
	/**
	 * 搜索条件
	 */
	public Map<String,Object> conditions = new HashMap<String,Object>();
	
	/**
	 * 页码显示
	 */
	public List<Integer> pageNumber;
	
	public void setPageNumber(int totalCount){
		this.totalCount = totalCount;

		if (this.totalCount == 0) {
			this.totalPageCount = 0;
			
		} else {
			this.totalPageCount = (this.totalCount -1) / this.pageSize + 1;
			this.pageNumber = new ArrayList<Integer>(); 
			for(int i=1;i<=this.totalPageCount;i++){
				this.pageNumber.add(i);
			}
		}
	}
	
	public Page(int pageSize) {
		this.currPage = 1;
		this.pageSize = pageSize;
	}

	public Page() {
		this(Constants.TEN);
	}
	
	public void setCurrPage(int currPage) {
		this.currPage = currPage<= 0 ? Constants.ONE : currPage;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize <= 0 ? Constants.TEN : pageSize;
	}
	
	public void setPageNum(Object pageNum) {
		
		if(pageNum instanceof String[]){
			String[] pageStr = (String[]) pageNum;
			
			try {
				this.currPage = Integer.parseInt(pageStr[0]);
				this.currPage = this.currPage <= 0 ? 1 : this.currPage;
			} catch (Exception e) {
				logger.error(e.getMessage());
				this.currPage = 1;
			}
		}
		
		if (pageNum instanceof String) {
			String pageStr = (String) pageNum;
			
			try {
				this.currPage = Integer.parseInt(pageStr);
				this.currPage= this.currPage <=0 ? 1 : this.currPage;
			} catch (Exception e) {
				logger.error(e.getMessage());
				this.currPage = 1;
			}
		}

		if (pageNum instanceof Integer) {
			
			try {
				this.currPage = (Integer) pageNum;
				this.currPage = this.currPage <=0 ? 1 : this.currPage;
			} catch (Exception e) {
				logger.error(e.getMessage());
				this.currPage = 1;
			}
		}
	}
	
	public void setPageNum(int pageNum) {
		
		this.currPage = this.currPage <=0 ? 1 : this.currPage;
	}

	/**
	 * 得到总记录条数
	 * @param totalNum
	 * @return 如果不存在记录，返回false
	 */
	public boolean setTotalNum(int totalCount) {
		this.totalCount = totalCount;

		if (this.totalCount == 0) {
			this.totalPageCount = 0;
			
			return false;
		} else {
			this.totalPageCount = (this.totalCount -1) / this.pageSize + 1;
		}

		this.currPage = this.currPage > this.totalPageCount ? this.totalPageCount: this.currPage;
		
		return true;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

}
  
