
var currPage=null;
var totalCount=null;
$(function(){
	//加载条件搜索数据
	 $.ajax({
	      url:"/common" ,
	      dataType: "JSON",
	      type: "GET",
	      cache: false,
	      success: function (data) {
	    	  var typeBlogs = data.typeBlogs;
	    	  var blogDate = data.blogDate;
	    	  var links = data.links;
	    	  var blogger = data.blogger;
	    	  var searchDate = data.searchDate;
	    	  var typeId = data.typeId;
	    	  var msgs=data.msgs;
	    	  var commLink ='';
	    	  var commDate='';
	    	  var commtype='';
	    	  var msgStr="";
	    	  $.each(links,function(n,value){
	    		  commLink=commLink+'<li><span><a href='+value.url+' target="_blank">'+value.name+'</a></span></li>'
	    	  })
	    	  $.each(blogDate,function(n,value){
	    		  commDate=commDate+'<li><span><a href="javascript:;" onclick="dateSearch(this)" month="'+value.months+'">'+value.months+'</a>&nbsp;(<span>'+value.blogCount+'</span>)</span></li>';
	    	  })
	    	  $.each(typeBlogs,function(n,value){
	    		  commtype=commtype+'<li><span><a href="javascript:;" onclick="typeSearch(this)" tid="'+value.blogType+'">'+value.name+'</a>&nbsp; (<span>'+value.typeCount+'</span>)</span></li>';
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
	    	  $("#typeId").val(typeId);
	    	  $("#searchDate").val(searchDate);
	    	  $("#currPage").val(currPage);
	      }
	  });
}) 

$(".jump").each(function(){
	var j = $(this);
	if(parseInt(j.text()) == currPage){
		j.parent().attr("class","active");
	}
})

//上一页
function pageUp(){
	if(currPage == 1 || currPage==null){
		$("#pageUp").attr("class","disabled");
		return false;
	}
	$("#currPage").val(parseInt($("#currPage").val())-1);
	$("#homeForm").submit();
}

//下一页
function pageDown(){
	if(currPage==null||currPage == pageNumber){
		$("#pageDown").attr("class","disabled");
		return false;
	}
	$("#currPage").val( parseInt($("#currPage").val())+1);
	$("#homeForm").submit();
}

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
	debugger
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
