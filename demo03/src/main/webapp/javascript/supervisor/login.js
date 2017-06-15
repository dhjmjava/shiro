adminLogin={
   LOGIN_FORM:"loginForm",
   init : function(){
	   adminLogin.form = $("#" + this.LOGIN_FORM);  
       this.form.submit(this.handleSubmit);  
   },
   handleSubmit:function(){
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
       var url = "/supervisor/dologin";  
       $.post(url,datas,function(data){
    	   adminLogin.showResult(data);  
       });  
   },  
   showResult: function (data){  
	   if(data!=""&&data!=null){
			$("#pwd_id").html(data);
		}else{
			window.location.href="/supervisor/admin.html";
		}
   }  
}
$(function(){
	adminLogin.init();
})
