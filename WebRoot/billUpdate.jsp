<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

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
		<em></em>
		<div class="title">信息修改&gt;&gt;</div>
	</div>
	<form method="post" action="/SuperMarket/bill?action=update">
		<div class="content">
			<table class="box">
				<tr>
					<td class="field">商品名称：</td>
					<td><input type="text" name="name"  value=${bill.name} class="text" /></td>
				</tr>
				<tr>
				<td class="field">商品数量：</td>
					<td><input type="text" name="num" value="${bill.num }" class="text" /></td>
					</tr>
					<tr>
					<td class="field">交易金额：</td>
					<td><input type="text" name="money"  value="${bill.price }" class="text" /></td>
					</tr>
					<tr>
					<td class="field">供应商名称：</td>
					<td><input type="text" name="pname" value="${bill.pname }" class="text" /></td>
				</tr>
				<tr>
					<td class="field">商品描述：</td>
					<td><textarea name="discription" >${bill.detail }</textarea></td>
				</tr>
				<tr>
					<td class="field">是否付款：</td>
					<td><select name="isPay" value="${bill.flag }" >
						<option value="1">是</option>
						<option value="0">否</option>
					</select></td>
				</tr>
			</table>
		</div>
		<div class="buttons">
			<input type="submit" name="submit" value="确认" class="input-button" />
			<input type="button" name="button" value="返回" class="input-button" onclick="history.back();" />
		</div>
	</form>
</div>

</body>
</html>