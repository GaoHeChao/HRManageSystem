<%@ page import="java.beans.beancontext.BeanContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<%@ page import="com.icss.bean.DepartmentsBean" %>
<html>
	<head>
		<title>更新部门数据</title>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
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

					<form name="form1" method="post" action="" id="form1" onsubmit="checkForm()">
						<table width="500" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#CCCCCC">
							<%
							DepartmentsBean bean = (DepartmentsBean) request.getAttribute("idBean");
							
							%>
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFCC00">
									修改部门信息
								</td>
							</tr>
							<tr>
								<td width="120" height="24" bgcolor="#FFFFFF">
									部门编号
								</td>
								<td width="357" height="24" bgcolor="#FFFFFF">
									<input name="department_id" type="text" id="department_id"
									readonly="readonly" value="<%=bean.getDepartment_id() %>">
									*
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									部门名称
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="department_name" type="text" id="department_name" onblur="return checkDepName()"
									value="<%=bean.getDepartment_name() %>">
									*
									<span id="depts"></span>
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									部门地址
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="location_name" type="text" id="location_name" onblur="return checkLoc()"
									value="<%= bean.getLocation_name() %>">
									*
									<span id="locts"></span>
								</td>
							</tr>
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFFFFF">
									<input type="submit" name="Submit" value="更新" >
									<input type="button" name="Submit2" value="取消" onclick="history.back();">
								</td>
							</tr>
						</table>
					</form>

				</td>
			</tr>
		</table>

		<!-- 页面底部 -->
		<!-- 验证修改后的表单 -->
<script type="text/javascript">
    function checkForm(){
    	if(!checkDepName()){
    		return false;
    	}else if(!checkLoc()){
    		return false;
    	}else{
	   		var formId = document.getElementById("form1");
	   		formId.action = "<%=request.getContextPath()%>/UpdateDeptServlet";
	   		return true;
    	}
    }
    function checkDepName(){
    	var dep_name = document.getElementById("department_name").value;
    	var depts = document.getElementById("depts");
    	if(dep_name == null || dep_name == ""){
    		depts.innerHTML = "部门名称不能为空！";
    		depts.style.color = "red";
    		return false;
    	}else{
    		depts.innerHTML = "部门名称输入正确！";
    		depts.style.color = "green";
    		return true;
    	}
    }
    function checkLoc(){
    	var loc_name = document.getElementById("location_name").value;
    	var locts = document.getElementById("locts");
    	if(loc_name == null || loc_name == ""){
    		locts.innerHTML = "部门位置不能为空！";
    		locts.style.color = "red";
    		return false;
    	}else{
    		locts.innerHTML = "输入正确！";
    		locts.style.color = "green";
    		return true;
    	}
    }
</script>
		
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