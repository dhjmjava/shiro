//公共的js

/**
 * 验证值是否为空
 * @param value
 * @returns {Boolean}
 */
function isBlank(value){
	if(value == undefined){return true;}
	if(value == "undefined"){return true;}
	if(value == null){return true;}
	if(value == "null"){return true;}
	if(value === ""){return true;}
	return false;
};

function commonSubmit(url,data){
	$.post(url,data,function(result){
		dealAjaxError(result);
	})
}

function dealAjaxError(data){
	if($.messager==undefined){
		alert(data.message);
		return;
	}
	if(isNotBlank(data) && isNotBlank(data.message)){
		if(data.message=="系统错误"){
			$.messager.alert("错误",data.message,'error');
		}
		else{
			$.messager.alert("提示",data.message,'info');
		}
		return ;
	}
	if(isNotBlank(data)&&isNotBlank(data.status)&&data.status==403){
		$.messager.alert("错误","无权限",'error');
		return;
	}
	if(isNotBlank(data)&&isNotBlank(data.status)&&data.status==401){//表示超时
		$.messager.alert("提示","登录已超时，请重新登录",'info',function(){
			this.location.href=this.location.href;
		});
		return;
	}
	
	$.messager.alert("错误","请求失败",'error');
}

/**
 * 将字符串转化为JSON对象 
 */
$.fn.serializeObject = function() {	
	var o = {};  
	var a = this.serializeArray();  
	var sObj=$(this);
	$(this).find("span[name]").each(function () {//支持span
		var temp={};
		temp.name=$(this).attr("name");
		temp.value=$(this).text();
		
		//防止重复添加
		if(sObj.find("input[name='"+temp.name+"']").length==0){
			a.push(temp);
		}
    });
	
	
	$.each(a, function() {  
	  if (o[this.name]) {  
	    if (!o[this.name].push) {  
	      o[this.name] = [ o[this.name] ];  
	    }  
	    o[this.name].push(this.value || '');  
	  } else {  
	    o[this.name] = this.value || '';  
	  }  
	});  
	
	return o;  
};  

