<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<% String path = request.getContextPath(); %>
<html>
	<head>
		<title>增加员工</title>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=path %>/inc/Calendar1.inc"></script>

		<script type="text/javascript">
			//显示对应职务的最低和最高工资
			function showSal() {
				//获得职务表单元素对象
				var job_id = document.form1.job_id;
				//显示最低工资和最高工资
				if (job_id.value != "") {
					document.form1.min_salary.value = eval("document.form1.min"+job_id.value+".value");
					document.form1.max_salary.value = eval("document.form1.max"+job_id.value+".value");
				} else {
					document.form1.min_salary.value = "";
					document.form1.max_salary.value = "";
				}
			}
			
		</script>
	</head>

	<body>
		<!-- 页面头部 -->
		<jsp:include page="/admin/header.jsp"></jsp:include>
		
		<!-- 页面内容 -->
		<table border="0" width="950" height="350" bgcolor="#ffffff"
			align="center">
			<tr>
				<td align="center" valign="top">
					<form name="form1" id="form1" method="post" onsubmit="return checkForm()">
						<table width="500" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#CCCCCC">
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFCC00">
									请填写以下员工信息
								</td>
							</tr>
							<tr>
								<td width="120" height="24" bgcolor="#FFFFFF">
									员工姓名
								</td>
								<td width="357" height="24" bgcolor="#FFFFFF">
									<input name="empname" type="text" id="empname" onblur="return checkName()">
									*
									<span id="nts"></span>
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									邮箱地址
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="email" type="text" id="email">
									*
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									电话号码
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="phone_number" type="text" id="phone_number">
									例如123.456.789
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									入职日期
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="hire_date" type="text" id="hire_date"  onfocus="calendar()">
									yyyy-mm-dd
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									职务
								</td>
								
								<td height="24" bgcolor="#FFFFFF">
								
									<select name="job_id" id="job_id" onchange="showSal()">
										<option value="">
											请选择职务
										</option>
										<!-- 循环输出所有的职务option -->
										<c:forEach items="${requestScope.jobbean }" var="onejob">
											<option value="${onejob.job_id }">${onejob.job_title }</option>
										</c:forEach>
									</select>

									<!-- 隐藏表单元素：存储每种职务的最低和最高工资 -->
										<input type="hidden" name="minFWY" value="800"/>
										<input type="hidden" name="maxFWY" value="2000"/>
										<input type="hidden" name="minMS" value="3000"/>
										<input type="hidden" name="maxMS" value="10000"/>
																							
									工资范围
									<input name="min_salary" type="text" id="min_salary" size="10"
										readonly>
									~
									<input name="max_salary" type="text" id="max_salary" size="10"
										readonly>
									
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									工资
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="salary" type="text" id="salary">
									*
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									部门
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<select name="department_id" id="department_id">
										<option value="">
											请选择部门
										</option>
										<!-- 循环输出所有的部门的option -->
										<c:forEach items="${requestScope.depbean }" var="onedep">
											<option value="${onedep.department_id }">${onedep.department_name }</option>																			
										</c:forEach>
									</select>
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
		    function checkForm(){
		    	if(!checkName()){
		    		return false;
		    	}else{
		    		var formId = document.getElementById("form1");
			    	formId.action = "AddEmpServlet";
			    	return true;
		    	}
		    }
		    function checkName(){
		    	var name = document.getElementById("empname").value;
		    	var namets = document.getElementById("nts");
		    	if(name == null || name == ""){
		    		namets.innerHTML = "不能为空！";
		    		namets.style.color = "red";
		    		return false;
		    	}else{
		    		namets.innerHTML = "输入正确！";
		    		namets.style.color = "green";
		    		return true;
		    	}
		    }
		</script>

		<!-- 页面底部 -->
		<jsp:include page="foot.jsp"></jsp:include>
	</body>
</html>