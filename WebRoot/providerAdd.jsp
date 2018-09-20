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
			
			 var aa;
			 $("#sel2 option:gt(0)").remove();
			 for (var i = 0; i < city.length; i++) {
						aa +="<option value="+city[i]+">" + city[i] + "</option>";
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
		<div class="title">供应商管理&gt;&gt;</div>
	</div>
	<form id="form1" name="form1" method="post" action="/SuperMarket/provider?action=add" onsubmit="return checkit();">
		<div class="content">
		<font color="red"></font>
<input name="flag" value="doAdd" type="hidden">
			<table class="box">

			<tbody>
				<tr>
					<td class="field">供应商名称：</td>
					<td><input name="proName" id="textfield2" value="" class="text" type="text"> <font color="red">*</font></td>

				</tr>
			<tr>
					<td class="field">供应商描述：</td>
					<td><textarea name="proDesc" id="textarea" cols="45" rows="5"></textarea></td>
				</tr>
				<tr>
					<td class="field">供应商联系：</td>

					<td><input name="contact" id="textfield2" value="" class="text" type="text"></td>
				</tr>
				<tr>
					<td class="field">供应商电话：</td>
					<td><input name="phone" id="textfield2" value="" class="text" type="text"></td>
				</tr>
				
				<tr>
					<td class="field">供应商地址：</td>
					<td>
					<select id="sel1" name = "sel1">
                    <option value="0">选择省</option>
                    <option>直辖市</option>
                    <option value="安徽省">安徽省</option>
                    <option value="江西省">江西省</option>
                    <option value="浙江省">浙江省</option>
                    <option value="湖南省">湖南省</option>
                    <option value="广东省">广东省</option>
                    <option value="湖北省">湖北省</option>
                    <option value="江苏省">江苏省</option>
                    <option value="福建省">福建省</option>
                    </select>
                    <select id="sel2" name = "sel2">
                    <option value="">选择市</option>
                    </select>
                    <select id="sel3" name="sel3">
                    <option value="">选择县</option>
                    </select>
					</td>
				</tr>
			</tbody></table>
		</div>

		<div class="buttons">
			<input name="button" id="button" value="提交" class="input-button" type="submit"> 
			<input name="button" id="button" onclick="window.location='provider.do';" value="返回" class="input-button" type="button"> 
		</div>
	</form>
</div>

</body>
</html>