"use strict";

//删除link
function delLink(fn){
	var link=$(fn);
	var lid=link.attr("id");
	if(confirm("链接删除后博客回复，确认执行此操作？")){
		$.ajax({
			url : "/blog/supervisor/delete-link",
			type : "POST",
			data : {
				"lid" :lid,
			},
			success : function(data) {
				if(data.code==-1){
					alert(data.msg);
				}else{
					link.parents("tr").remove();
				}
			},
			error : function(data) {
				alert("删除链接失败！");
			}
		});
	}
};

function submitLink(){
	$.post("/blog/supervisor/submit-link",$("#submitLinkForm").serialize(),function(data){
		if(data != null){
			if(data.code==-1){
				alert(data.msg);
			}else{
				alert("添加成功！");
				window.location.reload();
			}
		}
	});
};

function updateLink(){
	$.post("/blog/supervisor/saveUpdate",$("#updateLinkForm").serialize(),function(data){
		if(data != null){
			if(data.code==-1){
				alert(data.msg);
			}else{
				alert("修改成功！");
				setTimeout("window.location.reload()",1500);
			}
		}
	});
	
}