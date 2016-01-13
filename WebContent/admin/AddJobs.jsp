<%@page import="com.icss.servlet.AddJobServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<html>
	<head>
		<title>增加新职务</title>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>

	<body>
		<!-- 页面头部 -->
		<jsp:include page="/admin/header.jsp"></jsp:include>
		
		<!-- 页面内容 -->
		<table border="0" width="950" height="350" bgcolor="#ffffff"
			align="center">
			<tr>
				<td align="center" valign="top">
					<form method="post" action="" id="form1" name="form1" onsubmit="add()">
						<table width="500" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#CCCCCC">
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFCC00">
									请填写新职务类型信息
								</td>
							</tr>
							<tr>
								<td width="130" height="24" bgcolor="#FFFFFF">
									职务编号
								</td>
								<td width="347" height="24" bgcolor="#FFFFFF">
									<input name="job_id" type="text" id="job_id" onblur="return checkId()">
									*
									<span id="idts"></span>
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									职务名称
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="job_title" type="text" id="job_title" onblur="return checkTitle()">
									*
									<span id="titlets"></span>
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									最低工资
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="min_salary" type="text" id="min_salary" onblur="return checkMin()">
									*
									<span id="mints"></span>
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									最高工资
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="max_salary" type="text" id="max_salary" onblur="return checkMax()">
									*
									<span id="maxts"></span>
								</td>
							</tr>
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFFFFF">
									<input type="submit" name="Submit" value="提交" onClick="">
									<input type="button" name="Submit2" value="取消"
										onclick="history.back()">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>

<script type="text/javascript">
    function add(){
    	if(!checkId()){
    		return false;
    	}else if(!checkTitle()){
    		return false;
    	}else if(!checkMin()){
    		return false;
    	}else if(!checkMax()){
    		return false;
    	}else{
    		var formId = document.getElementById("form1");
    		formId.action = "<%=path%>/AddJobServlet";
    		return true;
    	}
    }
    function checkId(){
    	var id = document.getElementById("job_id").value;
    	var ts = document.getElementById("idts");
    	if(id == null || id == ""){
    		ts.innerHTML = "输入错误！";
    		ts.style.color = "red";
    		return false;
    	}else{
    		ts.innerHTML = "输入正确！";
    		ts.style.color = "green";
    		return true;
    	}
    }
    function checkTitle(){
    	var title = document.getElementById("job_title").value;
    	var titts = document.getElementById("titlets");
    	if(title == null || title == ""){
    		titts.innerHTML = "输入错误！";
    		titts.style.color = "red";
    		return false;
    	}else{
    		titts.innerHTML = "输入正确！";
    		titts.style.color = "green";
    		return true;
    	}
    }
    function checkMin(){
    	var min = document.getElementById("min_salary").value;
    	var ts = document.getElementById("mints");
    	if(min == null || min == ""){
    		ts.innerHTML = "输入错误！";
    		ts.style.color = "red";
    		return false;
    	}else{
    		ts.innerHTML = "输入正确！";
    		ts.style.color = "green";
    		return true;
    	}
    }
    function checkMax(){
    	var max = document.getElementById("max_salary").value;
    	var maxts = document.getElementById("maxts");
    	if(max == null || max == ""){
    		maxts.innerHTML = "输入错误！";
    		maxts.style.color = "red";
    		return false;
    	}else{
    		maxts.innerHTML = "输入正确！";
    		maxts.style.color = "green";
    		return true;
    	}
    }
</script>
		<!-- 页面底部 -->
		
<table width="950" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td><hr></td>
  </tr>
  <tr>
    <td align="center">©版权所有</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
	</body>
</html>