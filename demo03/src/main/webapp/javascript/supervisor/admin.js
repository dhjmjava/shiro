var submitForm={
	 ID_FROM: "registerForm",  
	 ID_RESULT_CONTAINER: "registerResult",
	 init:function(){
		 submitForm.form=$("#"+this.ID_FROM);
		 submitForm.result=$("#"+this.ID_RESULT_CONTAINER);
		 this.form.submit(this.handleSubmit);
	 },
	 
	 handleSubmit: function(event){
		 var datas = {};  
		 submitForm.form.find("input").each(function (i){  
             if (!($(this).attr("type") == "button" || $(this).attr("type") == "submit" || $(this).attr("type") == "reset" )){  
                 datas[$(this).attr("name")] = $(this).val();  
             }  
         });  
		 submitForm.ajaxSubmit(datas);  
         event.preventDefault();  
	 },
	 
	 ajaxSubmit:function(datas){
		var url = submitForm.form.attr("action"); 
		$.post(url,datas,function(data){
			submitForm.showResult(data);
		});
	 },
	 
	 showResult:function(data){
		 alert(data);
	 }
}

//修改右边iframe的url
function changePanel(val){
	$("#right").attr("src",val); 
}