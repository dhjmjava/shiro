/**  
 * Project Name:blog  
 * File Name:SystemController.java  
 * Package Name:com.blog.controller.supervisor  
 * Date:2016年11月16日下午9:35:04  
 * Copyright (c) 2016, jingmendh@163.com All Rights Reserved.  
 *  
*/  
  
package com.shiro.dh.controller.supervisor;  

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.dh.controller.BaseController;
import com.shiro.dh.entity.Blogger;
import com.shiro.dh.util.ErrorInfo;

/**  
 * ClassName:SystemController <br/>  
 * Function: 博主信息设置. <br/>  
 * Date:     2016年11月16日 下午9:35:04 <br/>  
 * @author   Administrator  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Controller
@RequestMapping("/supervisor")
public class SystemController extends BaseController{
	
	@RequestMapping("updateBloggerInfo")
	@RequiresPermissions("system:update")
	@ResponseBody
	public ErrorInfo updateBloggerInfo(Blogger blogger){
		ErrorInfo error = new ErrorInfo();
		try {
			
			bloggerServiceImpl.updateBloggerInfo(blogger);
			
		} catch (Exception e) {
			error.code=-1;
			error.msg="保存失败！";
			e.printStackTrace();
		}
		
		return error;
	}

}
  
