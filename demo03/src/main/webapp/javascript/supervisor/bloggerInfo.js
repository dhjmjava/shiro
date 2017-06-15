"use strict"
function updateBloggerInfo(){
	var data=$("#updateBloggerForm").serializeObject();
	debugger
	console.log(data);
	$.post("/supervisor/updateBloggerInfo",data,function(data){
		if(data != null){
			if(data.code==-1){
				alert(data.msg);
			}else{
				alert("信息修改成功！");
				setTimeout("window.location.reload()",1500);
			}
		}
	});
	
}