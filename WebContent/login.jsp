<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户登陆</title>
		<link href="css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	<% 
	Cookie[] cookies = request.getCookies();
	String name = ""; 
	if(cookies != null){
		for(Cookie ck : cookies){
			if(ck.getName().equals("name")){
				name = ck.getValue();
				break;
			}
		}
	}
	%>
		<form name="form1" method="post" action="" id="form1" onsubmit="return checkForm()">
			<table width="300" border="0" align="center" cellpadding="4"
				cellspacing="1" bgcolor="#FF9900">
				<tr>
					<td height="24" colspan="2" align="center" bgcolor="#FFCC66">
						管理员登陆${requestScope.sname }
					</td>
				</tr>
				<tr>
					<td width="77" height="24" align="center" bgcolor="#FFFFFF">
						管理账号
					</td>
					<td width="204" height="24" bgcolor="#FFFFFF">
						<input name="adminname" type="text" id="adminname" class="input1" value="<%=name %>">
					</td>
				</tr>
				<tr>
					<td height="24" align="center" bgcolor="#FFFFFF">
						管理密码
					</td>
					<td height="24" bgcolor="#FFFFFF">
						<input name="adminpwd" type="password" id="adminpwd"
							class="input1">
					</td>
				</tr>
				<tr>
					<td height="24" align="center" bgcolor="#FFFFFF">
						管理级别：
					</td>
					<td height="24" bgcolor="#FFFFFF">
						<input name="adminlevel" type="radio" value="1" checked>
						管理员
						<input name="adminlevel" type="radio" value="2">
						操作员
					</td>
				</tr>
				<tr>
					<td height="24" align="center" bgcolor="#FFFFFF">
						验证码：
					</td>
					<td height="24" bgcolor="#FFFFFF">
						<input id="admincode" name="admincode" type="text" size="8">
						<img id="img" src="ImgCodeMakerServlet" onclick="changeImg()">		
					</td>
				</tr>
				
				<tr>
					<td height="24" colspan="2" align="center" bgcolor="#FFFFFF">
						<input type="submit" name="Submit" value="进入系统" onClick="">
						&nbsp;&nbsp;
						<input type="hidden" name="targetURL" value="">
					</td>
				</tr>
			</table>
		</form>
		
		<script type="text/javascript">
		    function checkForm(){
		    	if(!checkName()){
		    		return false;
		    	}else if(!checkPswd()){
		    		return false;
		    	}else if(!checkCode()){
		    		return false;
		    	}else{
		    		var formId = document.getElementById("form1");
		    		formId.action = "<%=path %>/LoginServlet";
		    		return true;
		    	}
		    }
		    
		    function checkName(){
		    	var name = document.getElementById("adminname");
		    	if(name == null || name == ""){
		    		return false;
		    	}else{
		    		return true;
		    	}
		    }
		    function checkPswd(){
		    	var pwd = document.getElementById("adminpwd");
		    	if(pwd == null || pwd == ""){
		    		return false;
		    	}else{
		    		return true;
		    	}
		    }
		    function checkCode(){
		    	var code = document.getElementById("admincode");
		    	if(code == null || code == ""){
		    		return false;
		    	}else{
		    		return true;
		    	}
		    }
		    
		    function changeImg(){
		    	var img = document.getElementById("img");
		    	img.src= "<%=path %>/ImgCodeMakerServlet";
		    }
		</script>
	</body>
</html>