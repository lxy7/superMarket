<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<script type="text/javascript">
function go() {
	alert('退出登录！');
	window.location.href="login.jsp";
	cookie.setMaxAge(0);
	alert(0);
	cookie.setPath("/");
	alert(1);
	response.addCookie(cookie);
	alert(2);
	
}
</script>
<body class="frame-bd">
<ul class="left-menu">
	<li><a href="/SuperMarket/bill?action=bill&currentpage=1" target="mainFrame"><img src="images/btn_bill.gif" /></a></li>
	<li><a href="/SuperMarket/provider?action=provider&currentpage=1" target="mainFrame"><img src="images/btn_suppliers.gif" /></a></li>
	<li><a href="/SuperMarket/user?action=user&currentpage=1" target="mainFrame"><img src="images/btn_users.gif" /></a></li>
	<li><a href="/SuperMarket/user?action=go" target="_top"><img src="images/btn_exit.gif" /></a></li>
</ul>
</body>

</html>