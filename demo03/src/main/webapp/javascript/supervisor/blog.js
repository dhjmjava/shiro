"use strict";

//提交博客	
	function submitForm(){
			$.post("/blog/supervisor/submit-blog",$("#submitBlogForm").serialize(),function(data){
				if(data != null){
					if(data.code==-1){
						alert(data.msg);
					}else{
						alert("发布成功！");
						window.location.reload();
					}
				}
			});
		};
		//修改博客	
		function updateBlog(){
			$.post("/blog/supervisor/submitupdate",$("#updateBlogForm").serialize(),function(data){
				if(data != null){
					if(data.code==-1){
						alert(data.msg);
					}else{
						alert("修改成功！");
						window.location.reload();
					}
				}
			});
		};
	
//删除博客
	function delBlog(fn){
		var a=$(fn);
		var bid=a.attr("id");
		if(confirm("博客删除后博客回复，确认执行此操作？")){
			$.ajax({
				url : "/blog/supervisor/delete-blog",
				type : "POST",
				data : {
					"bid" : bid,
				},
				success : function(data) {
					if(data.code==-1){
						alert(data.msg);
					}else{
						a.parents("tr").remove();
					}
				},
				error : function(data) {
					alert("you are holder a problem！");
				}
			});
		}
	};

	
//时间显示
	$(function(){
		setInterval(updateTime, 1000);
		updateTime();
	});	
	function updateTime() {
		var time = new Date();
		var year = time.getFullYear();
		var month = time.getMonth()+1;
		var date = time.getDate();
		var hour = time.getHours();
		var minute =time.getMinutes();
		var second = time.getSeconds();
		$("#date").html(year + "年" + month + "月" + date + "日 "  + hour + ":" + minute + ":" + second);
	};
	//修改类别
	function modifyType(){
		 var id=$("#types").val();
		 var newType=$("#newType").val();
		 $.ajax({
				url : "/blog/supervisor/updateType",
				type : "POST",
				data : {
					"id" : id,
					"newType":newType
				},
				success : function(data) {
					if(data.code==-1){
						alert(data.msg);
					}else{
						alert("修改成功!");
						window.location.reload();
					}
				},
				error : function(data) {
					alert("you are holder a problem！");
				}
			});
		 
	}
	//新增类别
	function addType(){
		 var newType=$("#newType").val();
		 $.ajax({
				url : "/blog/supervisor/addType",
				type : "POST",
				data : {
					"newType":newType
				},
				success : function(data) {
					if(data.code==-1){
						alert(data.msg);
					}else{
						alert("新增成功！");
						window.location.reload();
					}
				},
				error : function(data) {
					alert("you are holder a problem！");
				}
			});
	}
	
	  "use strict"
	   //上一页
		function pageUp(){
			if(currPage == 1){
				$("#pageUp").attr("class","disabled");
				return false;
			}
			$("#currPage").val(parseInt($("#currPage").val())-1);
			$("#homeForm").submit();
		}

		//下一页
		function pageDown(){
			debugger;
			if(currPage == pageNumber){
				$("#pageDown").attr("class","disabled");
				return false;
			}
			var curr= parseInt($("#currPage").val());
			$("#currPage").val(curr+1);
			$("#homeForm").submit();
		}
		
		//跳页
		$(".jump").click(function(){
			var num = $(this).text();
			$("#currPage").val(num);
			$("#homeForm").submit();
		})
	