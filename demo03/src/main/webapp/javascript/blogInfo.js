//表单提交
singlePageForCommit = {
	FORM_ID:"commentForm",	
	
	init:function(){
		singlePageForCommit.form= $("#"+this.FORM_ID);
        this.form.submit(this.handleSubmit);  
	},
	
	handleSubmit: function (event) {
		 var datas = {};  
		 singlePageForCommit.form.find("input").each(function (i){  
            if (!($(this).attr("type") == "button" || $(this).attr("type") == "submit" || $(this).attr("type") == "reset" )){  
                 datas[$(this).attr("name")] = $(this).val();  
             }  
          });
		 datas.content=$("#content").val();
		 datas.blogId=blogId;
		 if(singlePageForCommit.checkDatas(datas)){
			 singlePageForCommit.ajaxSubmit(datas);  
		 }
		 event.preventDefault(); 
	},
	
	checkDatas : function(datas){
		if(isBlank(datas.content)){
			alert("请输入评论内容！");
			return false;
		}
		if(isBlank(datas.captchaCode)){
			alert("请输入验证码！");
			return false;
		}
		return true
	},
	
	ajaxSubmit:function(datas){
		var url=singlePageForCommit.form.attr("action");  
		$.post(url,datas,function(data){
			if(data.code==-1){
				alert(data.msg);
			}else{
				window.location.reload();
			}
		});
	},
	
		
}

//刷新验证码
function reloadCaptcha(){
	$.post("loadCaptcha",function(data){
		$("#randImage").attr("src","loadCaptcha");
	});
}
