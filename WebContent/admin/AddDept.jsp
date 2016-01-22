<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath(); %>
<html>
	<head>
		<title>增加新部门</title>
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

					<form name="form1" method="post" action="" id="form1" onsubmit="add()">
						<table width="500" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#CCCCCC">
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFCC00">
									请填写新部门信息
								</td>
							</tr>
							<tr>
								<td width="120" height="24" bgcolor="#FFFFFF">
									部门编号
								</td>
								<td width="357" height="24" bgcolor="#FFFFFF">
									<input name="department_id" type="text" id="department_id" onblur="return checkId();">
									*
									<span id="idts"></span>
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									部门名称
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="department_name" type="text" id="department_name" >
									*
									<span id="namets"></span>
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									部门地址
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="location_name" type="text" id="location_name" onblur="return checkLoc();">
									*
									<span id="locts"></span>
								</td>
							</tr>
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFFFFF">
									<input type="submit" name="Submit" value="提交" onClick="">
									<input type="button" name="Submit2" value="取消" onclick="history.back();">
								</td>
							</tr>
						</table>
					</form>

				</td>
			</tr>
	</table>
	<!-- 验证表单 -->
	<script type="text/javascript">
	    function add(){
	    	if(!checkId()){
	    		return false;
	    	}else if(!checkLoc()){
	    		return false;
	    	}else{
	    		var form1 = document.getElementById("form1");
			    //form1.action = "/HRManageSystem/AddDeptServlet";
			    form1.action = "<%=request.getContextPath()%>/AddDeptServlet";
			    return true;
	    	}	
	    }
	    
	    function checkId(){
	    	var dnum = document.getElementById("department_id").value;
	    	var idts = document.getElementById("idts");
	    	if(dnum>9 && dnum<100){
	    		idts.innerHTML = "输入正确！";
	    		idts.style.color = "green";
	    		return true;
	    	}else{
	    		idts.innerHTML = "输入值必须是两位数！";
	    		idts.style.color = "red";
	    		return false;
	    	}
	    }
	    
	    function checkLoc(){
	    	var dloc = document.getElementById("location_name").value;
	    	var locts = document.getElementById("locts");
	    	if(dloc == null || dloc == ""){
	    		locts.innerHTML = "地址不能为空！";
	    		locts.style.color = "red";
	    		return false;
	    	}else{
	    		locts.innerHTML = "输入正确！";
	    		locts.style.color = "green";
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
