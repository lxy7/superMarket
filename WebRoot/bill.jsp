<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<script type="text/javascript">
function go()
{
	var a = document.getElementById("page").value;
	window.location.href="/SuperMarket/bill?action=bill&currentpage="+a; 
}
</script>
<body>
<div class="menu">
	<form method="post" action="/SuperMarket/bill?action=select">
		商品名称：<input type="text" name="productName" class="input-text" />&nbsp;&nbsp;&nbsp;&nbsp;
		是否付款：<select name="payStatus">
			<option value="3">请选择</option>
			<option value="1">已付款</option>
			<option value="0">未付款</option>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" name="submit" value="组合查询" class="button" />
	</form>
</div>
<div class="main">
	<div class="optitle clearfix">
		<em><input type="button" name="button" value="添加数据" class="input-button" onclick="location.href='modify.jsp'" /></em>
		<div class="title">账单管理&gt;&gt;</div>
	</div>
	<div class="content">
		<table class="list">
			<tr>
				<td>账单编号</td>
				<td>商品名称</td>
				<td>商品数量</td>
				<td>交易金额</td>
				<td>是否付款</td>
				<td>供应商名称</td>
				<td>商品描述</td>
				<td>账单时间</td>
				<td>操作</td>
				
			</tr>
			 <c:forEach var="li" items="${bilist }">
  <tr>
    <td height="23"><span class="STYLE1">${li.id}</span></td>
    <td><span class="STYLE1"><a href="#" onclick="doit('mod',1)">${li.name}</a></span></td>

    <td><span class="STYLE1">
    	${li.num }
    </span></td>
    <td><span class="STYLE1">${li.price }</span></td>
    <td><span class="STYLE1">${li.flag }</span></td>
    <td><span class="STYLE1">${li.pname }</span></td>
     <td><span class="STYLE1">${li.detail }</span></td>
      <td><span class="STYLE1">${li.date }</span></td>
      <td><a href="/SuperMarket/bill?id=${li.id }&action=delete">删除</a> &nbsp 
      <a href="/SuperMarket/bill?id=${li.id }&action=data">修改</a></td>
   

  </tr>
  </c:forEach> 
			
		</table>
		<br> 
		<center>
		 共${ num }条记录 当前第${ currentpage } 页
	 <a href="/SuperMarket/bill?action=bill&currentpage=1">首页</a>

		<c:if test="${currentpage>1 }">
		<a href="/SuperMarket/bill?action=bill&currentpage=${currentpage-1 }">上一页</a>
		</c:if>
		
		<c:if test="${currentpage le pagecount  }">
		
		<a href="/SuperMarket/bill?action=bill&currentpage=${currentpage+1 }">下一页</a>
		
		</c:if>

		<c:if test="${currentpage <= pagecount  }">
		
		<a href="/SuperMarket/bill?action=bill&currentpage=1"></a>
		
		</c:if>
		<a href="/SuperMarket/bill?action=bill&currentpage=${pagecount }">尾页</a> 
		<input type="text" name="currentPage" id="page" size="1" maxlength="3"
			value="${currentpage}"> 
		<input type="button" value="GO"onclick="go()"> 
 </center>
	</div>
</div>


</body>
</html>