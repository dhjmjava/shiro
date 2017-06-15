adminLogin={
   LOGIN_FORM:"loginForm",
   init : function(){
	   adminLogin.form = $("#" + this.LOGIN_FORM);  
       this.form.submit(this.handleSubmit);  
   },
   handleSubmit:function(){
	   debugger;
	   var datas={};
	   adminLogin.form.find("input").each(function (i){  
          if (!($(this).attr("type") == "button" || $(this).attr("type") == "submit" || $(this).attr("type") == "reset" ))  
               {  
                   datas[$(this).attr("name")] = $(this).val().trim();  
               }  
           }); 
	   if(!adminLogin.checkDatas(datas)){
		   return ;
	   }
	   adminLogin.ajaxSubmit(datas);  
       event.preventDefault();
   },
   checkDatas:function(datas){
	   if(isBlank(datas.username)||isBlank(datas.password)){
		   $.messager.alert('error',"请输入用户名或密码！");
		   return false;
	   }
	   return true;
   },
   ajaxSubmit: function (datas){  
	   console.log(datas);
       var url = "/supervisor/checkLogin";  
       $.post(url,datas,function(data){
    	   adminLogin.showResult(data);  
       });  
   },  
   showResult: function (data){  
	   if(data.code==-1){
			$("#pwd_id").html(data.msg);
			$.messager.alert('error',data);
		}else{
			window.location.href="/supervisor/admin.html";
		}
   }  
}
$(function(){
	adminLogin.init();
})
