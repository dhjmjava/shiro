/**  
 * Project Name:blog  
 * File Name:Searcher.java  
 * Package Name:com.blog.lucene  
 * Date:2016年12月27日上午12:39:37  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.lucene;  

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.shiro.dh.entity.Blog;
import com.shiro.dh.util.Convert;
import com.shiro.dh.util.Page;
/**  
 * ClassName:Searcher <br/>  
 * Function: 搜索接口. <br/>  
 * Date:     2016年12月27日 上午12:39:37 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class Searcher {
	
	/**
	 * 
	 * search:(根据关键词获取搜索结果). <br/>   
	 *  
	 * @author Administrator  
	 * @param indexDir
	 * @param q
	 * @throws Exception  
	 * @since JDK 1.6
	 */
	public static Page<Blog> search(String indexDir,String q)throws Exception{
		
		Directory dir = FSDirectory.open(new File(indexDir));
		IndexReader ir = DirectoryReader.open(dir); 
		IndexSearcher is = new IndexSearcher(ir);
		//Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_46);//创建中文分词器
		QueryParser parser = new QueryParser(Version.LUCENE_46,"content",analyzer);
		Query query = parser.parse(q);
		TopDocs docs = is.search(query,10);

		//将搜索结果高亮显示
		QueryScorer scorer = new QueryScorer(query);
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","<b/><font/>");
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
		highlighter.setTextFragmenter(fragmenter);
		ArrayList<Blog> blogs = new ArrayList<Blog>(); 
		for (ScoreDoc doc:docs.scoreDocs) {
			Document document = is.doc(doc.doc);
			String id = document.get("id");
			String title = document.get("title");
		    String content = document.get("content");
			if(content != null){
		    	TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
		    	//System.out.println(highlighter.getBestFragment(tokenStream, content));
		    	String result =  highlighter.getBestFragment(tokenStream, content);
		    	Blog blog = new Blog();
		    	blog.setId(Convert.strToLong(id, -1));
		    	blog.setTitle(title);
		    	blog.setContent(result.length()<200?result:result.substring(0, 200));
		    	blogs.add(blog);
		    	}
		}
		Page<Blog> page = new Page<Blog>();
		page.page=blogs;
		return page;
	}

}
  
