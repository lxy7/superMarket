<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录 - 超市账单管理系统</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<script type="text/javascript">
function check()
{
	var name = document.mf.name.value;
	var password = document.mf.password.value;
	if(name=="")
		{
		alert("用户名不能为空！")
		return false;
		}
	if(password=="")
		{
		alert("密码不能为空！")
		return false;
		}
	return true;
	}
</script>
<body class="blue-style">
<div id="login">
	<div class="icon"></div>
	<div class="login-box">
		<form method="post" name = "mf" action="/SuperMarket/user?action=login" onsubmit="return check()">
			<dl>
				<dt>用户名：</dt>
				<dd><input type="text" name="name" class="input-text" /></dd>
				<dt>密　码：</dt>
				<dd><input type="password" name="password" class="input-text" /></dd>
			</dl>
			<div><p><font color="red">${newFlag}</font></p></div>
			<div class="buttons">
				<input type="submit" name="submit" value="登录系统" class="input-button" />
				<input type="reset" name="reset" value="重　　填" class="input-button" />
			</div>
			
		</form>
	</div>
</div>

</body>
</html>