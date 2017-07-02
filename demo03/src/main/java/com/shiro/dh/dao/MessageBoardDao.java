/**  
 * Project Name:demo03  
 * File Name:MessageBoardDao.java  
 * Package Name:com.shiro.dh.dao  
 * Date:2017年6月14日上午6:49:44  
 * Copyright (c) 2017, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shiro.dh.entity.MessageBoard;

/**  
 * ClassName:MessageBoardDao <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Date:     2017年6月14日 上午6:49:44 <br/>  
 * @author   daihui  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Repository
public interface MessageBoardDao extends JpaRepository<MessageBoard, Long>{
	
	//nativeQuery=true，标示此方法为原生查询
	@Query(value="select * from t_messageBoard where status=1 and is_use=1 order by publish_time desc",nativeQuery=true)
	List<MessageBoard> queryMessage();
	
	//更新方法需要使用@Modifying注解
	@Modifying
	@Query(value="update MessageBoard set status=?1,isUse=?2 where messageId=?3")
	MessageBoard updateMessage(boolean status,boolean isUse,long messageId);

}
  
