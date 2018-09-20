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
	window.location = "provider.do?id="+id+"&flag="+flag;
}function go()
{
	var a = document.getElementById("page").value;
	window.location.href="/${pageContext.request.contextPath}/provider?action=provider&currentpage="+a; 
}

</script>
</head>
<body>
<div class="menu">

<table>
<tbody><tr><td><form method="post" action="/SuperMarket/provider?action=select">
<input name="flag" value="search" type="hidden">
供应商名称：<input name="providerName" class="input-text" type="text"> &nbsp;&nbsp;&nbsp;&nbsp;供应商描述：<input name="providerDesc" class="input-text" type="text">&nbsp;&nbsp;&nbsp;&nbsp;<input value="组 合 查 询" type="submit">
</form></td></tr>
</tbody></table>
</div>
<div class="main">
<div class="optitle clearfix">
<em><input value="添加数据" class="input-button" onclick="window.location='providerAdd.jsp'" type="button"></em>
		<div class="title">供应商管理&gt;&gt;</div>
	</div>

	<div class="content">
<table class="list">
  <tbody><tr>
    <td width="70" height="29"><div class="STYLE1" align="center">编号</div></td>
    <td width="80"><div class="STYLE1" align="center">供应商名称</div></td>
    <td width="100"><div class="STYLE1" align="center">供应商描述</div></td>
    <td width="100"><div class="STYLE1" align="center">联系人</div></td>

    <td width="100"><div class="STYLE1" align="center">电话</div></td>
    <td width="100"><div class="STYLE1" align="center">地址</div></td>
  <td>操作</td>
  </tr>
   <c:forEach var="li" items="${prolist }">
  <tr>
    <td height="23"><span class="STYLE1">${li.id}</span></td>
    <td><span class="STYLE1"><a href="#" onclick="doit('mod',1)">${li.name}</a></span></td>

    <td><span class="STYLE1">
    	${li.detail }
    </span></td>
    <td><span class="STYLE1">${li.peoson }</span></td>
    <td><span class="STYLE1">${li.phone }</span></td>
    <td><span class="STYLE1">${li.address }</span></td>
   <td><a href="/SuperMarket/provider?id=${li.id }&action=delete">删除</a> &nbsp 
      <a href="/SuperMarket/provider?id=${li.id }&action=data">修改</a></td>

  </tr>
  </c:forEach> 
</tbody></table>
<center>
  共${ num }条记录 当前第${ currentpage } 页
	 <a href="/SuperMarket/provider?action=provider&currentpage=1">首页</a>

		<c:if test="${currentpage>1 }">
		<a href="/SuperMarket/provider?action=provider&currentpage=${currentpage-1 }">上一页</a>
		</c:if>
		
		<c:if test="${currentpage le pagecount  }">
		
		<a href="/SuperMarket/provider?action=provider&currentpage=${currentpage+1 }">下一页</a>
		
		</c:if>

		<c:if test="${currentpage <= pagecount  }">
		
		<a href="/SuperMarket/provider?action=provider&currentpage=1"></a>
		
		</c:if>
		<a href="/SuperMarket/provider?action=provider&currentpage=${pagecount }">尾页</a> 
		<input type="text" name="currentPage" id="page" size="1" maxlength="3"
			value="${currentpage}"> 
		<input type="button" value="GO"onclick="go()"> 
 </center>
 
	</div>

</body>
</html>