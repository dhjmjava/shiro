"use strict";
//删除评论
	function delComment(fn){
		var a=$(fn)
		var cid=a.attr("cid");
		if(confirm("确认执行此操作？")){
			$.ajax({
				url : "/blog/supervisor/deleteComm",
				type : "POST",
				data : {
					"id" : cid,
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
