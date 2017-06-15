/*公共的js文件*/

//ajax默认全局设置
$.ajaxSetup({
  cache: false,
  async: false,
  error: function (data) {
  	dealAjaxError(data);
  }
});

//ie8 下支持 indexOf
if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (elt /*, from*/) {
        var len = this.length >>> 0;
        var from = Number(arguments[1]) || 0;
        from = (from < 0)
             ? Math.ceil(from)
             : Math.floor(from);
        if (from < 0)
            from += len;
        for (; from < len; from++) {
            if (from in this &&
                this[from] === elt)
                return from;
        }
        return -1;
    };
}

/**
 * 获取当前采购项目名称
 * @returns
 */
function getRootUrl()
{
	if(typeof(rootUrl)=="undefined"){
		return parent.rootUrl;
	}
	return rootUrl;
}

/**
 * 对象转成请求参数字符串
 * @param obj
 * @returns {String}
 */
function objToParams(obj){
	 var formResult="1=1";
	 for(var key in obj){
		 formResult+="&"+key+"="+encodeURIComponent(obj[key]);
	 }
	 return formResult;
}

/**
 * 扩展方法 获取url参数的值 没有的话返回null
 * @param $ 参数名称
 */
(function($) {
	$.getUrlParam = function(name) {

		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");

		var r = window.location.search.substr(1).match(reg);

		if (r != null)
			return decodeURIComponent(r[2]);
		return null;

	}
})(jQuery);

/**扩展方法 序列化所有
 */
$.fn.serializeAll = function () {
    var temp = $(this).serialize();
    $(this).find("span[name]").each(function () {//支持span
        temp += "&" + $(this).attr("name") + "=" + encodeURIComponent($(this).text());
    });
    $(this).find("input[name]:disabled").each(function () {//支持disabled input
        temp += "&" + $(this).attr("name") + "=" + encodeURIComponent($(this).val());
    });
    $(this).find("textarea[name]:disabled").each(function () {//支持disabled textarea
        temp += "&" + $(this).attr("name") + "=" + encodeURIComponent($(this).val());
    });
    return temp;
};

/**
 * 将字符串转化为JSON对象 
 */
$.fn.serializeObject = function() {	
	var o = {};  
	var a = this.serializeArray();  
	var sObj=$(this);
	$(this).find("span[name]").each(function () {//支持span
		var temp={};
		temp.name=$(this).attr("name");
		temp.value=$(this).text();
		
		//防止重复添加
		if(sObj.find("input[name='"+temp.name+"']").length==0){
			a.push(temp);
		}
    });
	
	
	$.each(a, function() {  
	  if (o[this.name]) {  
	    if (!o[this.name].push) {  
	      o[this.name] = [ o[this.name] ];  
	    }  
	    o[this.name].push(this.value || '');  
	  } else {  
	    o[this.name] = this.value || '';  
	  }  
	});  
	
	return o;  
};  

(function($) {
  $.fn.serializeJson = function(prefix) {
      var result = {};
      var n = prefix ? (prefix + '.') : "";
      var arrayTypes = this["arrayTypes"];
      var array = this.serializeArray();
      var datetimeTypes = this["datetimeTypes"] || [];
      for (var i = 0; i < array.length; i++) {
          if ($.inArray(array[i].name, datetimeTypes) >= 0) {
              if (array[i].value) {
                  array[i].value = new Date(array[i].value.replace(/-/g, "/")).getTime();
              } else {
                  delete array[i].name;
              }
          }
      }
      $(array).each(function() {
          if (!this.name || this.name.indexOf(n) != 0) return;
          var name = this.name.substring(n.length);
          var idot = name.indexOf('.');
          if (idot > 0) {
              var n1 = name.substring(0, idot);
              var n2 = name.substring(idot + 1);
              if (!result[n1]) result[n1] = {};
              result[n1][n2] = this.value;
          } else if (result[name]) {
              if ($.isArray(result[name])) {
                  result[name].push(this.value);
              } else {
                  result[name] = [result[name], this.value];
              }
          } else {
              result[name] = this.value;
          }
      });
      if (!arrayTypes) return result;
      for (var i = 0; i < arrayTypes.length; i++) {
          var n = arrayTypes[i];
          if (!result[n]) {
              delete result[n];
          } else if (!$.isArray(result[n])) {
              result[n] = [result[n]];
          }
      }
      return result;
  };

  var privateJsonString = function(object) {
      var type = typeof object;
      if ('object' == type && Array == object.constructor) {
          type = 'array';
      }
      switch (type) {
          case 'number':
              return object ? ("" + object) : "0";
          case 'string':
              return '"' + object.replace(/(\\|\")/g, "\\$1") + '"';
          case 'object':
              var results = [];
              for (var property in object) {
                  results.push(property + ':' + privateJsonString(object[property]));
              }
              return '{' + results.join(',') + '}';
          case 'array':
              var results = [];
              for (var i = 0; i < object.length; i++) {
                  results.push(object[i] ? privateJsonString(object[i]) : "null");
              }
              return '[' + results.join(',') + ']';
          default :
              return (object === undefined) ? ("" + object) : "null";
      }
  };
  $.objectToString = privateJsonString;
  $.fn.serializeJsonString = function(prefix) {
      return privateJsonString(this.serializeJson(prefix));
  };

})(jQuery);



/**
 * obj 数组转换成url参数形式
 *  @param obj 要转换的对象或数组
 *  @param key 形成的参数数组的前缀名字
 *  @param startIndex 为数组开始的序号
 */
function parseParam(obj, key, startIndex) {
  var paramStr = "";
  if (obj instanceof String || obj instanceof Number || obj instanceof Boolean || obj === "") {
      if (obj === "") {
          paramStr += "";
      }
      else {
          paramStr += "&" + key + "=" + encodeURIComponent(obj);
      }
  } else {
      $.each(obj, function (i) {
          if (startIndex == undefined) {
              startIndex = 0;
          }
          var k = key == null ? i : key + (obj instanceof Array ? "[" + (i + startIndex) + "]" : "." + i);
          if (obj[i] == undefined) {
              paramStr += '&' + parseParam("", k);
          }
          else {
              paramStr += '&' + parseParam(this, k);
          }
      });
  }
  return paramStr.substr(1);
}


/*******************************************easy ui 相关******************************************/
/**
 * 清除datagrid无数据的情况
 */
function clearNoData(dg) {
    var body = $("#" + dg).data().datagrid.dc.body2;
    if (body.parents(".datagrid-wrap.panel-body").find("div.noData" + nodataclass).length > 0) {
        body.parents(".datagrid-wrap.panel-body").find("div.noData" + nodataclass).remove();
    }
}

/**
 * 公共的处理datagrid的方法 样式处理、统一分页、无数据显示
 * dg datagrid 对象 data datagrid的数据,如果无data参数，表示是无数据状态
 */
function handleDatagrid(dg, data,pagesize) {//公共的处理datagrid的方法
	var body = dg.data().datagrid.dc.body2;
	
	if (typeof (data) == "undefined") {
	    if (body.parents(".datagrid-wrap.panel-body").find("div.noData").length == 0) {
	        body.parents(".datagrid-wrap.panel-body").append("<div class='noData'>查无数据!</div>");
	    }
	    body.parents(".datagrid-wrap.panel-body").children(".datagrid-pager.pagination").hide();
	}
	else {
	    if (data.total == 0) {//无数据显示
	        if (body.parents(".datagrid-wrap.panel-body").find("div.noData").length == 0) {
	            body.parents(".datagrid-wrap.panel-body").append("<div class='noData'>查无数据!</div>");
	        }
	    }
	    else {
	        if (body.parents(".datagrid-wrap.panel-body").find("div.noData").length > 0) {
	            body.parents(".datagrid-wrap.panel-body").find("div.noData").remove();
	        }
	    }
	    
	    if (data.total <= pagesize) {
	        body.parents(".datagrid-wrap.panel-body").children(".datagrid-pager.pagination").hide();
	    } else {
	        body.parents(".datagrid-wrap.panel-body").children(".datagrid-pager.pagination").show();
	    }
	}
}

/** 
* treegrid级联选择父节点 
* @param {Object} target 
* @param {Object} id 节点ID 
* @param {Object} status 节点状态，true:勾选，false:未勾选，status2:status2：勾选级联，取消勾选不级联
* @return {TypeName}  
*/
function selectParent(target, id, idField, status,status2) {
	var status2=true;
  var parent = target.treegrid('getParent', id);
  if (parent) {
      var parentId = parent[idField];
      if (status){
      	target.treegrid('select',id);
          target.treegrid('select', parentId);
          selectParent(target, parentId, idField, status);
      }
      else{
      	target.treegrid('unselect',id);
      }
      
  }
}

/**
 * treegrid 级联勾中父节点
 * @param target
 * @param id
 * @param idField
 * @param status
 */
function checkParent(target, id, idField, status) {
  var parent = target.treegrid('getParent', id);
  if (parent) {
      var parentId = parent[idField];
      if (true){
      	 check(parentId,2);
          checkParent(target, parentId, idField, status);
      }
  }
}

/** 
* treegrid级联选择子节点 
* @param {Object} target 
* @param {Object} id 节点ID 
* @param {Object} deepCascade 是否深度级联 
* @param {Object} status 节点状态，true:勾选，false:未勾选，status2：勾选不级联，取消勾选级联
* @return {TypeName}  
*/
function selectChildren(target, id, idField, deepCascade, status,status2) {
  //深度级联时先展开节点  
  if (status && deepCascade)
      target.treegrid('expand', id);
  //根据ID获取下层孩子节点  
  var children = target.treegrid('getChildren', id);
  for (var i = 0; i < children.length; i++) {
      var childId = children[i][idField];
      if (status==true&&status2==true)
          target.treegrid('select', childId);
      else
          target.treegrid('unselect', childId);
      selectChildren(target, childId, idField, deepCascade, status);//递归选择子节点  
  }
}

/**
 * 删除datagrid行
 * @param datagridId datagrid id
 * @param deleteControlObj 删除控件自身
 */
function deleteDatagridRow(datagridId,deleteControlObj){
	var trs=$(deleteControlObj).closest("table").children("tbody").children("tr");
	theIndex=trs.index($(deleteControlObj).closest("tr"));
	$("#"+datagridId).datagrid('deleteRow',theIndex);
}
/*****************************************************/



/********************************字符串和日期处理相关***********************************************/

/**
 * 字符串是否以***结尾
 * @param s
 * @returns {Boolean}
 */
String.prototype.endWith = function (s) {
    if (s == null || s == "" || this.length == 0 || s.length > this.length)
        return false;
    if (this.substring(this.length - s.length) == s)
        return true;
    else
        return false;
    return true;
}

/**
 * 字符串是否以***开头
 * @param s
 * @returns {Boolean}
 */
String.prototype.startWith = function (s) {
    if (s == null || s == "" || this.length == 0 || s.length > this.length)
        return false;
    if (this.substr(0, s.length) == s)
        return true;
    else
        return false;
    return true;
}

/**
* 除掉两端空格
*/
String.prototype.trim = function () { return this.replace(/(^\s*)|(\s*$)/g, ""); }

/**
 * 去除字符串头部部指定字符
 */
String.prototype.trimStart = function(trimStr){
    if(!trimStr){return this;}
    var temp = this;
    while(true){
        if(temp.substr(0,trimStr.length)!=trimStr){
            break;
        }
        temp = temp.substr(trimStr.length);
    }
    return temp;
};

/**
 * 去除字符串尾部指定字符
 */
String.prototype.trimEnd = function(trimStr){
    if(!trimStr){return this;}
    var temp = this;
    while(true){
        if(temp.substr(temp.length-trimStr.length,trimStr.length)!=trimStr){
            break;
        }
        temp = temp.substr(0,temp.length-trimStr.length);
    }
    return temp;
};

/**
 * 去除字符串头部或尾部指定字符
 */
/*String.prototype.trim = function(trimStr){ 和 angularjs冲突，所以注释掉 zhengkai
	    var temp = trimStr;
	    if(!trimStr){temp=" ";}
	    return this.trimStart(temp).trimEnd(temp);
	};*/
	
/**
 * 类似 String.format()方法 例如 {0} 作为占位符  
 * @returns
 */
String.format = function () {
    if (arguments.length == 0)
        return null;
    var str = arguments[0];
    for (var i = 1; i < arguments.length; i++) {
        var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
};

/**
 * js增加replaceAll方法
 */
String.prototype.replaceAll  = function(s1,s2){    
    return this.replace(new RegExp(s1,"gm"),s2);    
}



/**
 * 字符串左边填充0
 */
function padLeft(str, length) {
    str = '' + str;
    if (str.length >= length)
        return str;
    else
        return padLeft("0" + str, length);
}

/**
 * 数字转中文
 * @param dValue
 * @returns
 */
function numToChinese(dValue) {
    var maxDec = 2;
    // 验证输入金额数值或数值字符串：
    dValue = dValue.toString().replace(/,/g, "");
    dValue = dValue.replace(/^0+/, ""); // 金额数值转字符、移除逗号、移除前导零
    if (dValue == "") {
        return "零元整";
    } // （错误：金额为空！）
    else if (isNaN(dValue)) {
        return "错误：金额不是合法的数值！";
    }
    var minus = ""; // 负数的符号“-”的大写：“负”字。可自定义字符，如“（负）”。
    var CN_SYMBOL = ""; // 币种名称（如“人民币”，默认空）
    if (dValue.length > 1) {
        if (dValue.indexOf('-') == 0) {
            dValue = dValue.replace("-", "");
            minus = "负";
        } // 处理负数符号“-”
        if (dValue.indexOf('+') == 0) {
            dValue = dValue.replace("+", "");
        } // 处理前导正数符号“+”（无实际意义）
    }
    // 变量定义：
    var vInt = "";
    var vDec = ""; // 字符串：金额的整数部分、小数部分
    var resAIW; // 字符串：要输出的结果
    var parts; // 数组（整数部分.小数部分），length=1时则仅为整数。
    var digits, radices, bigRadices, decimals; // 数组：数字（0~9——零~玖）；基（十进制记数系统中每个数字位的基是10——拾,佰,仟）；大基（万,亿,兆,京,垓,杼,穰,沟,涧,正）；辅币（元以下，角/分/厘/毫/丝）。
    var zeroCount; // 零计数
    var i, p, d; // 循环因子；前一位数字；当前位数字。
    var quotient, modulus; // 整数部分计算用：商数、模数。
    // 金额数值转换为字符，分割整数部分和小数部分：整数、小数分开来搞（小数部分有可能四舍五入后对整数部分有进位）。
    var NoneDecLen = (typeof (maxDec) == "undefined" || maxDec == null || Number(maxDec) < 0 || Number(maxDec) > 5); // 是否未指定有效小数位（true/false）
    parts = dValue.split('.'); // 数组赋值：（整数部分.小数部分），Array的length=1则仅为整数。
    if (parts.length > 1) {
        vInt = parts[0];
        vDec = parts[1]; // 变量赋值：金额的整数部分、小数部分
        if (NoneDecLen) {
            maxDec = vDec.length > 5 ? 5 : vDec.length;
        } // 未指定有效小数位参数值时，自动取实际小数位长但不超5。
        var rDec = Number("0." + vDec);
        rDec *= Math.pow(10, maxDec);
        rDec = Math.round(Math.abs(rDec));
        rDec /= Math.pow(10, maxDec); // 小数四舍五入
        var aIntDec = rDec.toString().split('.');
        if (Number(aIntDec[0]) == 1) {
            vInt = (Number(vInt) + 1).toString();
        } // 小数部分四舍五入后有可能向整数部分的个位进位（值1）
        if (aIntDec.length > 1) {
            vDec = aIntDec[1];
        } else {
            vDec = "";
        }
    } else {
        vInt = dValue;
        vDec = "";
        if (NoneDecLen) {
            maxDec = 0;
        }
    }
    if (vInt.length > 44) {
        return "错误：金额值太大了！整数位长【" + vInt.length.toString() + "】超过了上限——44位/千正/10^43（注：1正=1万涧=1亿亿亿亿亿，10^40）！";
    }
    // 准备各字符数组 Prepare the characters corresponding to the digits:
    digits = new Array("零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"); // 零~玖
    radices = new Array("", "拾", "佰", "仟"); // 拾,佰,仟
    bigRadices = new Array("", "万", "亿", "兆", "京", "垓", "杼", "穰", "沟", "涧", "正"); // 万,亿,兆,京,垓,杼,穰,沟,涧,正
    decimals = new Array("角", "分", "厘", "毫", "丝"); // 角/分/厘/毫/丝
    resAIW = ""; // 开始处理
    // 处理整数部分（如果有）
    if (Number(vInt) > 0) {
        zeroCount = 0;
        for (i = 0; i < vInt.length; i++) {
            p = vInt.length - i - 1;
            d = vInt.substr(i, 1);
            quotient = p / 4;
            modulus = p % 4;
            if (d == "0") {
                zeroCount++;
            } else {
                if (zeroCount > 0) {
                    resAIW += digits[0];
                }
                zeroCount = 0;
                resAIW += digits[Number(d)] + radices[modulus];
            }
            if (modulus == 0 && zeroCount < 4) {
                resAIW += bigRadices[quotient];
            }
        }
        resAIW += "元";
    }
    // 处理小数部分（如果有）
    for (i = 0; i < vDec.length; i++) {
        d = vDec.substr(i, 1);
        if (d != "0") {
            resAIW += digits[Number(d)] + decimals[i];
        }
    }
    // 处理结果
    if (resAIW == "") {
        resAIW = "零" + "元";
    } // 零元
    if (vDec == "") {
        resAIW += "整";
    } // ...元整
    resAIW = CN_SYMBOL + minus + resAIW; // 人民币/负......元角分/整
    return resAIW;
}

/**
 * 中文转数字
 * @param num
 * @returns
 */
function getNumToUpper(num) {
    var numArray = new Array();
    var unit = "亿万元$";
    for (var i = 0; i < unit.length; i++) {
        var re = eval("/" + (numArray[i - 1] ? unit.charAt(i - 1) : "") + "(.*)" + unit.charAt(i) + "/");
        if (num.match(re)) {
            numArray[i] = num.match(re)[1].replace(/^拾/, "壹拾");
            numArray[i] = numArray[i].replace(/[零壹贰叁肆伍陆柒捌玖]/g, function ($1) {
                return "零壹贰叁肆伍陆柒捌玖".indexOf($1);
            });
            numArray[i] = numArray[i].replace(/[分角拾佰仟]/g, function ($1) {
                return "*" + Math.pow(10, "分角 拾佰仟 ".indexOf($1) - 2) + "+"
            }).replace(/^\*|\+$/g, "").replace(/整/, "0");
            numArray[i] = "(" + numArray[i] + ")*" + Math.ceil(Math.pow(10, (2 - i) * 4));
        } else
            numArray[i] = 0;
    }
    return eval(numArray.join("+"));
}



/* 
* 截取字符串（中文长度按2计算）
* 如果给定的字符串大于指定长度，截取指定长度返回，否者返回源字符串。
* @param str：需要截取的字符串 
* @param len: 需要截取的长度 
* @param isNoTitle: 是否去掉悬浮提示
*/
function cutString(str, len, isRemoveSuspendedTips) {
    if (str == null) {
        return "";
    }

    var regChinese = /[^\x00-\xff]/g;
    if (str.replace(regChinese, "**").length <= len) {
        return str;
    }

    len = len - 3;
    var startIndex = Math.floor(len / 2);
    for (var idx = startIndex; idx < str.length; idx++) {
        if (str.substring(0, idx).replace(regChinese, "**").length == len) {
            if (isRemoveSuspendedTips) {
                return str.substring(0, idx) + "...";
            }
            else {
                return "<span title=\"" + str + "\">" + str.substring(0, idx) + "...</span>";
            }
        }

        if (str.substring(0, idx).replace(regChinese, "**").length > len) {
            if (isRemoveSuspendedTips) {
                return str.substring(0, idx - 1) + "...";
            }
            else {
                return "<span title=\"" + str + "\">" + str.substring(0, idx - 1) + "...</span>";
            }
        }
    }

    return str;
}


/**
 * 格式化日期 
 * 调用方式： var str = new Date().Format("yyyy-MM-dd HH:mm:ss");
 */
Date.prototype.Format = function (fmt) {
 var o = {
     "M+": this.getMonth() + 1,                 //月份 
     "d+": this.getDate(),
     "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时 //日 
     "H+": this.getHours(),                   //小时 
     "m+": this.getMinutes(),                 //分 
     "s+": this.getSeconds(),                 //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds()             //毫秒 
 };
 if (/(y+)/.test(fmt))
     fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
     if (new RegExp("(" + k + ")").test(fmt))
         fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}


/**
 * 出现类型未定义请使用下面方法
 *  调用方式：
 */
function dateFormat(date,fmt){
	var o = { 
	        "M+": date.getMonth() + 1, //月份  
	        "d+": date.getDate(), //日  
	        "H+": date.getHours(), //小时  
	        "m+": date.getMinutes(), //分  
	        "s+": date.getSeconds(), //秒  
	        "q+": Math.floor((date.getMonth() + 3) / 3), //季度  
	        "S": date.getMilliseconds() //毫秒  
	    }; 
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length)); 
	    for (var k in o) 
	        if (new RegExp("(" + k + ")").test(fmt)) 
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length))); 
	    return fmt; 	
}

/**
 * 字符串转日期
 * 格式：yyyy-MM-dd HH:mm:ss
 *     yyyy-MM-dd
 * @param strDate
 * @returns {Date}
 */
function strToDate(strDate){
	if(!strDate) return;
	var o = new Date(strDate.replace(/-/g,"/"));
	return o;
}

/**
 * 修改baseObj对象中name字段的日期时间格式值为long型
 */
function chgDateToLong(baseObj,name){
	if(baseObj[name] && baseObj[name]!="")
		baseObj[name] = strToDate(baseObj[name]).getTime();
	else
		baseObj[name] = "";
}

/**
 * 修改bean对象中name字段的long时间格式值为format格式的日期
 */
function chgLongToDateStr(bean, name, format){
	if(bean[name] && bean[name]!="")
		bean[name] = new Date(bean[name]).Format(format);
	else
		bean[name] = "";
}

/**
 * 获取某年某月的最后一天
 * @param year
 * @param month
 * @returns
 */
function getLastDay(year,month)   
{   
 var new_year = year;  //取当前的年份   
 var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）   
 if(month>12)      //如果当前大于12月，则年份转到下一年   
 {   
 new_month -=12;    //月份减   
 new_year++;      //年份增   
 }   
 var newnew_date = new Date(new_year,new_month,1);        //取当年当月中的第一天   
 return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期   
}  

/**
 * 校验变量值是否为null或空字符（undefined、null、"" 返回false,其他返回true）
 */
function isNotBlank(value){
	if(value == undefined){
		return false;
	}
	if(value == "undefined"){
		return false;
	}
	if(value == null){
		return false;
	}
	if(value == "null"){
		return false;
	}
	if(value === ""){
		return false;
	}
	return true;
}

/**
 * 校验变量值是否为null或空字符（undefined、null、"" 返回true,其他返回false）
 */
function isBlank(value){
	if(value == undefined){
		return true;
	}
	if(value == "undefined"){
		return true;
	}
	if(value == null){
		return true;
	}
	if(value == "null"){
		return true;
	}
	if(value === ""){
		return true;
	}
	return false;
}

/**
 * 处理null及undefined
 */
function dealNull(value){
	if(value == undefined || value == null){
		return "";
	}
	return value;
}
/*****************************************************/


/***********************公共的ajax相关*******************************/
/**
 * 公共的判断ajax请求是否成功的方法
 * @param data
 * @returns {Boolean}
 */
function isAjaxSuccess(data){
	//data不存在返回失败
	if(data == undefined) return false;
	//data存在且code不存在则认为成功，否则失败
	if(data.code == undefined) return true;
	
	return false;
}


/**
 * 公共的处理ajax错误信息的方法
 * @param data
 * @returns {Boolean}
 */
function dealAjaxError(data){
	if($.messager==undefined){
		alert(data.message);
		return;
	}
	if(isNotBlank(data) && isNotBlank(data.message)){
		if(data.message=="系统错误"){
			$.messager.alert("错误",data.message,'error');
		}
		else{
			$.messager.alert("提示",data.message,'info');
		}
		return ;
	}
	if(isNotBlank(data)&&isNotBlank(data.status)&&data.status==403){
		$.messager.alert("错误","无权限",'error');
		return;
	}
	if(isNotBlank(data)&&isNotBlank(data.status)&&data.status==401){//表示超时
		$.messager.alert("提示","登录已超时，请重新登录",'info',function(){
			this.location.href=this.location.href;
		});
		return;
	}
	
	$.messager.alert("错误","请求失败",'error');
}

/**
 * 获取服务器系统时间，毫秒数
 */
function getServerLongTime(){
	var longTime = 0;
	try{
		$.ajax({
			async: false,
            cache: false,
			type : 'POST',  
			contentType : 'application/json', 
			dataType : 'json', 
			url:getRootUrl()+'/common/getSystemTime.do',
			success : function(data) {  
				longTime = data;
		    },  
		   	error : function(data) {  
		   		longTime = 0;
		   	}  
		});
	}catch(e){
		longTime = 0;
	}
	return longTime;
}

/**
 * 从服务器获取新的guid
 */
function getNewGuid(){
	var guid = '';
	try{
		$.ajax({
			async: false,
			cache: false,
			type : 'POST',  
			contentType : 'application/json', 
			dataType : 'json', 
			url:getRootUrl()+'/common/getNewGUID.do',
			success : function(data) {  
				guid = data;
		    },  
		   	error : function(data) {  
		   		guid = '';
		   	}  
		});
	}catch(e){
		guid = '';
	}
	return guid;
}

/**
 * 获取枚举的数据
 * @enumName 枚举名称
 */
function getEnum(enumName){
	var jsonObj={};
	$.ajax({
		async: false,
		type : 'POST',
		url : getRootUrl()+'/common/getEnum.do?name='+enumName,
		dataType : 'json',
		success : function(data) {
			if (isAjaxSuccess(data)) {
				jsonObj=data;
			} else {
				dealAjaxError(data);
			}
		}
	});
	return jsonObj;
}
/**********************************************************/



/***********************其他*******************************/

/**
 * 字符串CA签名 附原文的数据签名
**dateTime:2008/8/15 19:00
**param: strData 字符数据
**return: 字符串
**description:对字符数据进行签名，若成功，返回签名后密文字符串，若失败，返回空(""), 返回 false 取消签名。
*/
function szcaSignStr(str){
	//???? 确认是否需要增加这一步，目前不加这个操作签名不成功，加这操作后可能选择有可能不是登录用户的CA（机器上有多个证书时）
	try{
	var str = SZCAOcx.AxSetKeyStore();
	if(!str){  //取消签名
		return false;
	}
	var result = AttachSign(str);
	}catch(e){
		$.messager.alert("错误","签名验证失败，请检查是否CA登录",'error');
		return;
	}
	return result;
}


/**
 * 网页内不重新打开浏览器 ，显示pdf
 */
function showPdfIfream(fileGuid,fileType){
	$("#pdf_div").text("");		
	var pdfUrl;
	if(fileType == "zgsc") {
		pdfUrl = pdfPath+'?fileGuid='+fileGuid +'&fileType=' + fileType;
	} else {
		pdfUrl = pdfPath+'?fileGuid='+fileGuid +'&fileType=0';
	}
	var upladoer = "<iframe id='mainFrame' src='"+pdfUrl+"' width='99%' height='99%' frameborder:'0' scrolling:'no' ></iframe>";
	$("#pdf_div").append(upladoer)
}


/**
 * 重新打开一个浏览器窗口，预览pdf
 * @param fileGuid
 */
function viewPDF(fileGuid){
	var pdfUrl;
	if(fileType == "zgsc") {
		pdfUrl = pdfPath+'?fileGuid='+fileGuid +'&fileType=' + fileType;
	} else {
		pdfUrl = pdfPath+'?fileGuid='+fileGuid +'&fileType=0';
	}
	window.open(pdfUrl,'','height=screen.height, width=screen.width,top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no')
}

/**
 * 阿拉伯数字转中文 
 * var k = new change("00102040"); 使用方法
 */
var _change = {
        ary0:["零", "一", "二", "三", "四", "五", "六", "七", "八", "九"],
        ary1:["", "十", "百", "千"],
        ary2:["", "万", "亿", "兆"],
        init:function (name) {
            this.name = name;
        },
        strrev:function () {
            var ary = []
            for (var i = this.name.length; i >= 0; i--) {
                ary.push(this.name[i])
            }
            return ary.join("");
        }, //倒转字符串。
        pri_ary:function () {
            var $this = this
            var ary = this.strrev();
            var zero = ""
            var newary = ""
            var i4 = -1
            for (var i = 0; i < ary.length; i++) {
                if (i % 4 == 0) { //首先判断万级单位，每隔四个字符就让万级单位数组索引号递增
                    i4++;
                    newary = this.ary2[i4] + newary; //将万级单位存入该字符的读法中去，它肯定是放在当前字符读法的末尾，所以首先将它叠加入$r中，
                    zero = ""; //在万级单位位置的“0”肯定是不用的读的，所以设置零的读法为空

                }
                //关于0的处理与判断。
                if (ary[i] == '0') { //如果读出的字符是“0”，执行如下判断这个“0”是否读作“零”
                    switch (i % 4) {
                        case 0:
                            break;
                        //如果位置索引能被4整除，表示它所处位置是万级单位位置，这个位置的0的读法在前面就已经设置好了，所以这里直接跳过
                        case 1:
                        case 2:
                        case 3:
                            if (ary[i - 1] != '0') {
                                zero = "零"
                            }
                            ; //如果不被4整除，那么都执行这段判断代码：如果它的下一位数字（针对当前字符串来说是上一个字符，因为之前执行了反转）也是0，那么跳过，否则读作“零”
                            break;
                    }
                    newary = zero + newary;
                    zero = '';
                }
                else { //如果不是“0”
                    newary = this.ary0[parseInt(ary[i])] + this.ary1[i % 4] + newary; //就将该当字符转换成数值型,并作为数组ary0的索引号,以得到与之对应的中文读法，其后再跟上它的的一级单位（空、十、百还是千）最后再加上前面已存入的读法内容。
                }

            }
            if (newary.indexOf("零") == 0) {
                newary = newary.substr(1)
            }//处理前面的0
            return newary;
        }
    }
//创建class类
function change() {
    this.init.apply(this, arguments);
}
change.prototype = _change
////////////////////////////////////


//cookie functions http://www.quirksmode.org/js/cookies.html
function createCookie(name, value, days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		var expires = "; expires=" + date.toGMTString();
	} else
		var expires = "";
	document.cookie = name + "=" + value + expires + "; path=/";
}
function readCookie(name) {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(nameEQ) == 0)
			return c.substring(nameEQ.length, c.length);
	}
	return null;
}
function eraseCookie(name) {
	createCookie(name, "", -1);
}


//除法函数，用来得到精确的除法结果
//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
//调用：accDiv(arg1,arg2)
//返回值：arg1除以arg2的精确结果
function accDiv(arg1,arg2){
	var t1=0,t2=0,r1,r2;
	try{t1=arg1.toString().split(".")[1].length}catch(e){}
	try{t2=arg2.toString().split(".")[1].length}catch(e){}
	with(Math){
		r1=Number(arg1.toString().replace(".",""))
		r2=Number(arg2.toString().replace(".",""))
		return (r1/r2)*pow(10,t2-t1);
	}
}

//给Number类型增加一个div方法，调用起来更加方便。
Number.prototype.div = function (arg){
	return accDiv(this, arg);
}

//乘法函数，用来得到精确的乘法结果
//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
//调用：accMul(arg1,arg2)
//返回值：arg1乘以arg2的精确结果
function accMul(arg1,arg2)
{
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length}catch(e){}
	try{m+=s2.split(".")[1].length}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}

//给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function (arg){
	return accMul(arg, this);
}

//加法函数，用来得到精确的加法结果
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
//调用：accAdd(arg1,arg2)
//返回值：arg1加上arg2的精确结果
function accAdd(arg1,arg2){
	var r1,r2,m;
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	m=Math.pow(10,Math.max(r1,r2))
	return (arg1*m+arg2*m)/m
}
//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg){
	return accAdd(arg,this);
}

//减法函数，用来得到精确的减法结果
//说明：javascript的减法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的减法结果。
//调用：accSubtr(arg1,arg2)
//返回值：arg1减去arg2的精确结果
function accSubtr(arg1,arg2){
	var r1,r2,m,n;
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	m=Math.pow(10,Math.max(r1,r2));
	//动态控制精度长度
	n=(r1>=r2)?r1:r2;
	return ((arg1*m-arg2*m)/m).toFixed(n);
}

//给Number类型增加一个subtr 方法，调用起来更加方便。
Number.prototype.subtr = function (arg){
	return accSubtr(arg,this);
}

//给input 添加tip属性 默认提示
$(function(){
	  setTimeout("addTips()", 300);
	  //addTips();
});

//添加提示效果
function addTips(){
	$(':input[tip]').each(function(index, element) {
        var self = $(this), txt = self.attr('tip');
        if(self.next("[name='span_tip']").length>0){
        	return true;
        }
        self.wrap($('<div></div>').css({position:'relative', zoom:'1', border:'none', background:'none', padding:'none', margin:'none'}));
        var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
        var holder = $('<span name=\'span_tip\' style="font-size:12px"></span>').text(txt).css({position:'absolute', left:pos.left, top:0, height:h, lienHeight:h, paddingLeft:'10px', color:'#aaa', "line-height":h+"px",display:'none'}).appendTo(self.parent());
        self.keydown(function(e) {
        	if(event.which!=8&&event.which!=36){//删除
        		 holder.hide();
        	}        	
        }).focusout(function(e) {
            if(!self.val()){
                holder.show();
            }
        }).focusin(function(e) {
        	if(!self.val()){
                holder.show();
            }else{
            	 holder.hide();
            }
        })
        .change(function(){
        	 if(!self.val()){
                 holder.show();
             }
        }).click(function(){
        	if(!self.val()){
                holder.show();
            }else{
            	 holder.hide();
            }
        });
        holder.click(function(e) {;
            self.focus();
        });
        
        if(!self.val()){
            holder.show();
        }
    });
}

//返回
function goBack(){
	history.go(-1);
}

/**
 * 格式化大文本换行的情况 datagrid
 */
function formatBigText(val) {
    if (val==null||val=="") {
        return "";
    }
    return val.replace(/\r\n/g, '<br/>').replace(/[\r\n]/g, '<br/>').replace(/[\s]/g, "&nbsp;");
}

/**
 * 去掉HTML标签
 * @param str
 * @param obj
 */
function delHtmlTag(str){  
	 var title = str.replace(/<[^>]+>/g,"");//去掉所有的html标记
	 return title;
} 



/**
 * 获取附件组隐藏文本域html集合
 * @param element 对应附件的元素对象
 * @author zhengkai
 */
function getFuJianZuHiddenInputs(element){
	var strHtml="";
	strHtml+='<input type="hidden" name="otherInfo" value="'+element.otherInfo+'">';
	strHtml+='<input type="hidden" name="fileGuid" value="'+element.fileGuid+'">';
	var fileName=element.fileName;
	if(typeof(element.fileName)=="undefined"){
		fileName=element.fileTitle;
	}
	strHtml+='<input type="hidden" name="fileName" value="'+fileName+'">';
	var uploadTime=element.uploadTime;
	if(typeof(element.uploadTime)=="undefined"){
		uploadTime=element.createTime;
	}
	strHtml+='<input type="hidden" name="uploadTime" value="'+uploadTime+'">';
	var fileGroupGuid=element.fileGroupGuid;
	if(typeof(element.fileGroupGuid)=="undefined"){
		fileGroupGuid="";
	}
	strHtml+='<input type="hidden" name="fileGroupGuid" value="'+fileGroupGuid+'">';
	
	var filePath=element.filePath;
	if(typeof(element.filePath)=="undefined"){
		filePath="";
	}
	strHtml+='<input type="hidden" name="filePath" value="'+filePath+'">';
	return strHtml;
}



/**
 * 获取上传的附件对象集合
 * @param containerId 所在容器的guid或者datagrid的id
 * @param isEasyUIDataGrid 是否easyui表格
 * @author zhengkai
 */
function getUploadFiles(containerId,isEasyUIDataGrid){
	if(typeof(containerId)=="undefined"){
		containerId="dgFuJian";
	}
	var objs=[];
	
	if(typeof(isEasyUIDataGrid)=="undefined"||isEasyUIDataGrid==false){
		$("#"+containerId+" tbody").children("tr").each(function(index,element){
			if($(element).find("[name='fileGuid']").length==0){
				return true;
			}
			if($(element).attr("display")=="none"){
				return true;
			}
			var obj={};
			obj.otherInfo=$(element).find("[name='otherInfo']").val();
			obj.uploadTime=$(element).find("[name='uploadTime']").val();
			obj.fileGuid=$(element).find("[name='fileGuid']").val();
			obj.fileName=$(element).find("[name='fileName']").val();
			obj.fileGroupGuid=$(element).find("[name='fileGroupGuid']").val();
			obj.filePath=$(element).find("[name='filePath']").val();
			objs.push(obj);
		});
	}
	else{
		var rows = $("#"+containerId).datagrid("getRows");
		for(var i=0;i<rows.length;i++){
			var obj={};
			obj.otherInfo=rows[i].otherInfo;
			obj.uploadTime=rows[i].uploadTime;
			obj.fileGuid=rows[i].fileGuid;
			obj.fileName=rows[i].fileName;
			obj.fileGroupGuid=rows[i].fileGroupGuid;
			if(isBlank(obj.fileGroupGuid)){
				obj.fileGroupGuid="";
			}
			obj.filePath=rows[i].filePath;
			if(isBlank(obj.filePath)){
				obj.filePath="";
			}
			objs.push(obj);
		}
	}

	return objs;
}