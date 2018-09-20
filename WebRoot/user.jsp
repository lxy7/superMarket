<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<script type="text/javascript">
function doit(flag,id)
{
	if(flag=="del")
	{
		if(confirm("确认删除吗？")!=true)
			return;
	}
}
function go()
{
	var a = document.getElementById("page").value;
	window.location.href="/SuperMarket/user?action=user&currentpage="+a; 
}

function createXHR(){
	if(window.ActiveXObject){
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
		xhr = new XMLHttpRequest();
	}else{
		alert("can't create xhr object!");
	}
}

 window.onload=function(){
	var userName = document.search_mf.userName;
	//获取div区域
	var ctx_div=document.getElementById("context_div");
	//初始化隐藏div区域
	ctx_div.style.display="none";
	createXHR();
	userName.onkeyup=function(){
		var userNameVal=this.value;
		if(userNameVal==''){
			ctx_div.style.display="none";
			return;
		}
		xhr.open("get","${pageContext.request.contextPath}/user?action=select_input&userName="+userName.value);
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				//debugger;
				var data = xhr.responseText;
				
				console.log(data);
				var newdata=data.split(",");
				var child_div="";
				for(var i=0;i<newdata.length;i++){ 
					child_div +="<div onclick='change_div_ctx(this);' onmousemove='change_div_bgcolor_move(this);' onmouseout='change_div_bgcolor_out(this);'>"+newdata[i]+"</div>";
				}
				ctx_div.innerHTML=child_div;
				ctx_div.style.display="block";
			}
		};
		xhr.send(null);
	}
};
function change_div_bgcolor_move(div){
	div.style.backgroundColor="yellow";
}
function change_div_bgcolor_out(div){
	div.style.backgroundColor="";
}
function change_div_ctx(div){
	var ctx = div.innerHTML;
	document.search_mf.userName.value=ctx;
	div.parentNode.style.display="none";
} 
</script>
</head><body>

<div class="menu">

<table>
<tbody><tr><td><form method="post" action="/SuperMarket/user?action=select" name="search_mf">
<input name="flag" value="search" class="input-text" type="hidden">
用户名称：<input name="userName"   onmouseout="" class="input-text" type="text" value=${selectname}>&nbsp;&nbsp;&nbsp;&nbsp; <input value="查 询" type="submit">
</form></td></tr>
</tbody></table>
</div>
<div class="main">

<div class="optitle clearfix">
<em><input value="添加数据" class="input-button" onclick="window.location='userAdd.jsp'" type="button"></em>
		<div class="title">用户管理&gt;&gt;</div>
	</div>
	<div class="content">
<table class="list">
  <tbody><tr>
    <td width="70" height="29"><div class="STYLE1" align="center">编号</div></td>
    <td width="80"><div class="STYLE1" align="center">用户名称</div></td>
    <td width="100"><div class="STYLE1" align="center">性别</div></td>
    <td width="100"><div class="STYLE1" align="center">年龄</div></td>

    <td width="150"><div class="STYLE1" align="center">电话 </div></td>
    <td width="150"><div class="STYLE1" align="center">地址 </div></td>
    <td width="150"><div class="STYLE1" align="center">操作 </div></td>
  </tr>
  <c:forEach var="li" items="${userlist }">
  <tr>
    <td height="23"><span class="STYLE1">${li.id}</span></td>
    <td><span class="STYLE1"><a href="#" onclick="doit('mod',1)">${li.name}</a></span></td>

    <td><span class="STYLE1">
    	${li.sex }
    </span></td>
    <td><span class="STYLE1">${li.age }</span></td>
    <td><span class="STYLE1">${li.phone }</span></td>
    <td><span class="STYLE1">${li.address }</span></td>
    
    
<td><a href="/SuperMarket/user?id=${li.id }&action=delete">删除</a> &nbsp 
      <a href="/SuperMarket/user?id=${li.id }&action=data">修改</a></td>
  </tr>
  </c:forEach> 
  
  
  
 </tbody></table>
 <br>
 <center>
  共${ num }条记录 当前第${ currentpage } 页
	 <a href="/SuperMarket/user?action=user&currentpage=1">首页</a>

		<c:if test="${currentpage>1 }">
		<a href="/SuperMarket/user?action=user&currentpage=${currentpage-1 }">上一页</a>
		</c:if>
		
		<c:if test="${currentpage le pagecount  }">
		
		<a href="/SuperMarket/user?action=user&currentpage=${currentpage+1 }">下一页</a>
		
		</c:if>

		<c:if test="${currentpage <= pagecount  }">
		
		<a href="/SuperMarket/user?action=user&currentpage=1"></a>
		
		</c:if>
		<a href="/SuperMarket/user?action=user&currentpage=${pagecount }">尾页</a> 
		<input type="text" name="currentPage" id="page" size="1" maxlength="3"
			value="${currentpage}"> 
		<input type="button" value="GO"onclick="go()"> 
 </center>
 
</div>
</div>
<div id="context_div"    style="display:block; width:17%;background-color:red;position:absolute;left:89px;top:35px;">
</div>
</body>
</html>