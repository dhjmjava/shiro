$(function(){
	//加载条件搜索数据
	 $.ajax({
	      url:"/common" ,
	      dataType: "JSON",
	      type: "GET",
	      cache: false,
	      success: function (data) {
	    	  var typeList = data.typeList;
	    	  var dateList = data.dateList;
	    	  var links = data.links;
	    	  var blogger = data.blogger;
	    	  var msgs=data.msgs;
	    	  var commLink ='';
	    	  var commDate='';
	    	  var commtype='';
	    	  var msgStr="";
	    	  $.each(links,function(n,value){
	    		  commLink=commLink+'<li><span><a href='+value.url+' target="_blank">'+value.name+'</a></span></li>'
	    	  })
	    	  $.each(dateList,function(n,value){
	    		  commDate=commDate+'<li><span><a href="javascript:;" onclick="query(false,false,\''+value[1]+'\',null)" month="'+value[1]+'">'+value[1]+'</a>&nbsp;(<span>'+value[0]+'</span>)</span></li>';
	    	  })
	    	  $.each(typeList,function(n,value){
	    		  commtype=commtype+'<li><span><a href="javascript:;" onclick="query(false,false,null,'+value[2]+')">'+value[0]+'</a>&nbsp; (<span>'+value[1]+'</span>)</span></li>';
	    	  })
	    	   $.each(msgs,function(n,value){
	    		   msgStr=msgStr+'<li><span>'+(n+1)+'、'+value.msgContent+'</span></li>';
	    	  })
	    	  
	    	  $("#links").html(commLink);
	    	  $("#dates").html(commDate);
	    	  $("#types").html(commtype);
	    	  $("#msgs").html(msgStr);
	    	  $("#nickName").text(blogger.userName);
	    	  $("#userSign").text(blogger.motto);
	      }
	  });
}) 

function query(pageUp,pageDown,time,blogType){
	debugger
	if(pageUp){
		if(currPage == 1 || currPage==null){
			$("#pageUp").attr("class","disabled");
			return ;
		}
		$("#currPage").val(parseInt(currPage)-1);
	}
	if(pageDown){
		if(currPage==null||currPage == pageNumber){
			$("#pageDown").attr("class","disabled");
			return ;
		}
		$("#currPage").val( parseInt(currPage)+1);
	}
	if(blogType!=null&&blogType!=""){
		$("#typeId").val(blogType);
	}
	if(time!=null&&time!=""){
		$("#searchDate").val(time);
	}
	$("#homeForm").submit();
}

$(".jump").each(function(){
	var j = $(this);
	if(parseInt(j.text()) == currPage){
		j.parent().attr("class","active");
	}
})

//尾页
function pageEnd(){
	window.location.href="/?currPage="+pageNumber;
}

//跳页
$(".jump").click(function(){
	var num = $(this).text();
	$("#currPage").val(num);
	$("#homeForm").submit();
})
	
var i=0;
var j=0
function typeSearch(fn){
	i++;
	if(i==1){
		$("#currPage").val(1);
	}
	var tid = $(fn).attr("tid");
	$("#typeId").val(tid);
	$("#searchDate").val('');
	$("#homeForm").submit();
}
	
function dateSearch(fn){
	j++;
	if(j==1){
		$("#currPage").val(1);
	}
	var month = $(fn).attr("month");
	$("#searchDate").val(month);
	$("#typeId").val(-1);
	$("#homeForm").submit();
}
	
//首页博客字数限制
 $(function(){
	 $(".summary").each(function(){
			var maxwidth=700;
		    if($(this).text().length>maxwidth){
				$(this).html($(this).html().substring(0,maxwidth));
	         }
       });
 })
	
//搜索检查	
function check(){
  var q = $("#q").val().trim();
  if(null == q || '' == q){
		alert("请输入关键词！");
		return false;
	 }
  $("#searchForm").submit();
}
