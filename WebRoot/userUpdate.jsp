<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<script type="text/javascript">
function checkit()
{
	var a= document.form1.password.value;
	var b= document.form1.password1.value;
	if(a!=b)
		{
		document.getElementById("ps").innerHTML="<font color=red>两次输入的密码不匹配</font>";
		/* document.form1.password.value=null;
		document.form1.password1.value=null; */
		return false;
		}
	return true;
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


</script>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function (){
	
	   $("#sel1").change(function(){
			 var b =$("#sel1 option:selected").val();
			
		   //alert(111);
			 $.ajax({
			 type:"get",
			 url:"${pageContext.request.contextPath }/user?action=address_cit",
			 data:"p_name="+b, 
			 dataType:"text",
			 success: function(data){
			 var city = data.split(",");
			 alert(city);
			 var aa;
			 $("#sel2 option:gt(0)").remove();
			 for (var i = 0; i < city.length; i++) {
						aa +="<option>" + city[i] + "</option>";
					}
			 $("#sel2").append(aa);
				}
			})
		});
		  $("#sel2").change(function (){
			  var c=$("#sel2 option:selected").val();
				  $.ajax
		 ({
			
			 type:"get",
			 url:"${pageContext.request.contextPath }/user?action=address_cou",
			 data:"cname="+c, 
			 dataType:"text",
			 success: function(data){
			   var town = data.split(",");
			  var aa;
			  $("#sel3 option:gt(0)").remove();
			   for(var i=0;i<town.length;i++)
				   {
				   aa+=("<option value="+town[i]+">"+town[i]+"</option>");
				   
				   }
			   $("#sel3").append(aa);
			}})}); 

	});

</script>
<body>
<div class="main">
	<div class="optitle clearfix">
		<div class="title">用户信息修改&gt;&gt;</div>

	</div>
	<form id="form1" name="form1" method="post"    action="/SuperMarket/user?action=update" >
<input type="hidden" name="flag" value="doAdd">
		<div class="content">
			<table class="box"><font color="red"></font>
			
			<tr>
					<td class="field">用户名称：</td>
					<td><input type="text" name="username" value="${user.name }"  class="text" id="textfield2" onblur="checkName()" /> <font color="red">*</font><span id="msg"></span></td>
				</tr>
				<tr>
					<td class="field">用户密码：</td>

					<td><input type="password" name="password" value="${user.password }" class="text" id="textfield2" /> <font color="red">*</font></td>
				</tr>
			<tr>
					<td class="field">确认密码：</td>
					<td><input type="password" name="password1" class="text" onblur="checkit()"/> <font color="red">*</font><span id = "ps" ></span></td>
				</tr>

				<tr>
					<td class="field">用户性别：</td>
					<td><select name="sex" id="select">
    <option value="0">女</option>
    <option value="1">男</option>
  </select></td>
				</tr>

				<tr>
					<td class="field">用户年龄：</td>
					<td><input type="text" name="age" value="${user.age }" class="text" id="textfield2"/> <font color="red">*</font></td>
				</tr>
				<tr>
					<td class="field">用户电话：</td>
					<td><input type="text" name="mobile" value="${user.phone }" class="text" id="textfield2"/> <font color="red">*</font></td>

				</tr>
				<tr>
					<td class="field">用户地址：</td>
					<td>
					<select id="sel1" >
                    <option value="0">选择省</option>
                    <option>直辖市</option>
                    <option>安徽省</option>
                    <option>江西省</option>
                    <option>浙江省</option>
                    <option>湖南省</option>
                    <option>广东省</option>
                    <option>湖北省</option>
                    <option>江苏省</option>
                    <option>福建省</option>
                    </select>
                    <select id="sel2">
                    <option>选择市</option>
                    </select>
                    <select id="sel3" name="sel3">
                    <option>选择县</option>
                    </select>
					</td>
				</tr>
				<tr>
					<td class="field">用户权限：</td>

					<td><input type="radio" name="auth" id="auth" value="0" checked="checked"/>普通用户
					<input type="radio" name="auth" id="auth" value="1" />经理</td>
				</tr>
			</table>
		</div>
		<div class="buttons">
			<input type="submit" name="button" id="button" value="数据提交" class="input-button"/>
			  <input type="button" name="button" id="button" onclick="window.location='user.jsp';" value="返回" class="input-button"/> 
		</div>

	</form>
</div>


</body>
</html>