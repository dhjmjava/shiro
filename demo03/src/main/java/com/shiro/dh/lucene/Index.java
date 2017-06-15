/**  
 * Project Name:blog  
 * File Name:Index.java  
 * Package Name:com.blog.lucene  
 * Date:2016年12月27日上午12:24:30  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.lucene;  

import java.io.File;
import java.util.List;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.shiro.dh.constants.Constants;
import com.shiro.dh.entity.Blog;
/**  
 * ClassName:Index <br/>  
 * Function: 全文检索. <br/>  
 * Date:     2016年12月27日 上午12:24:30 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class Index {
	private Directory directory; 
	
	/**
	 * 初始化已有数据
	 */
	public void initIndex(List<Blog> blogs)throws Exception{
		IndexWriter writer = getWriter();
		for(Blog blog:blogs){
			writer.addDocument(addDataToIndex(blog));
			writer.commit();
		}
		writer.close();
	}
	
	/**
	 * getWriter:(获取Indexwriter对象). <br/>   
	 * @author Administrator  
	 * @return
	 * @throws Exception  
	 * @since JDK 1.6
	 */
	public IndexWriter getWriter() throws Exception{
		directory = FSDirectory.open(new File(Constants.INDEXDIR));
		//Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);//创建标准分词器
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46);//中文分词器
		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_46, analyzer);
		IndexWriter writer  = new IndexWriter(directory, config);
		return writer;
	}
	
	/**
	 * 
	 * index:(索引文件). <br/>   
	 *  
	 * @author Administrator  
	 * @param indexDir
	 * @param blog
	 * @throws Exception  
	 * @since JDK 1.6
	 */
	public void index(Blog blog)throws Exception{
		IndexWriter writer = getWriter();
		writer.addDocument(addDataToIndex(blog));
		writer.commit();
		writer.close();
	}
	
	//添加数据到doc
	private Document addDataToIndex(Blog blog) throws Exception{
		Document document = new Document();
		document.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		document.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		document.add(new TextField("content",blog.getContent(),Field.Store.YES));
	    return document;
	}
	
	//更新索引
    public void updateIndex(Blog blog)throws Exception{
    	IndexWriter writer=getWriter();
		Document doc=new Document();
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new TextField("content",blog.getContent(),Field.Store.YES));
		writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);
		writer.close();
    }
    
    //删除索引
    public void deleteIndex(long blogId)throws Exception{
    	IndexWriter writer=getWriter();
		writer.deleteDocuments(new Term("id",String.valueOf(blogId)));
		writer.forceMergeDeletes(); // 强制删除
		writer.commit();
		writer.close();
    }
    
}
  
