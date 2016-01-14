<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath();%>

<html>
	<head>
		<title>员工数据</title>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<script type="text/javascript">
			//****************-删除确认***************
			function del(employee_id) {
				//弹出确认框
				var flag = window.confirm("你确定要删除" + employee_id + "号员工吗?");
				//如果单击确定，则执行DelEmpServlet，并且传入员工编号
				if (flag) {
					alert("删除成功");
					location.href = "EmpViewServlet.html";
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
					<table width="98%" border="0" cellpadding="5" cellspacing="1"
						bgcolor="#CCCCCC">
						<tr>
							<td width="7%" height="24" bgcolor="#FFCC00">
								员工编号
							</td>
							<td width="11%" height="24" bgcolor="#FFCC00">
								员工姓名
							</td>
							<td width="16%" height="24" bgcolor="#FFCC00">
								邮箱
							</td>
							<td width="16%" height="24" bgcolor="#FFCC00">
								电话
							</td>
							<td width="12%" height="24" bgcolor="#FFCC00">
								入职时间
							</td>
							<td width="9%" height="24" bgcolor="#FFCC00">
								职务
							</td>
							<td width="9%" height="24" bgcolor="#FFCC00">
								工资
							</td>
							<td width="7%" height="24" bgcolor="#FFCC00">
								部门
							</td>
							<td width="13%" height="24" bgcolor="#FFCC00">
								操作
							</td>
						</tr>
						<c:forEach items="${requestScope.allEmp }" var="oneEmp" varStatus="vs">
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									${oneEmp.employee_id }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneEmp.emp_name }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneEmp.email }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneEmp.phone_number }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneEmp.hire_date }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneEmp.job_id }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneEmp.salary }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneEmp.department_id }
								</td>
								<c:if test="${sessionScope.slevel == 1 }">
								<td height="24" bgcolor="#FFFFFF">
									<a href="UpdateEmp.jsp">修改</a>
									<a href="javascript:del(102)">删除</a>
									<a href="PhotoViewServlet.jsp" target="_blank">照片</a>								
								</td>
								</c:if>
							</tr>
							</c:forEach>				
					</table>

					<!-- 分页显示用界面 -->
					<table width="95%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="24" align="center">
								共4条数据，每页显示9条，
								共1页，当前是第1页
							</td>
						</tr>
						<tr>
							<td height="24" align="center">
								<a href="?pagenum=1">首页</a>
								<a href="?pagenum=0">上页</a>
								<a href="?pagenum=2">下页</a>
								<a href="?pagenum=1">末页</a>
							</td>
						</tr>
						<tr>
							<td height="24" align="center">	
								<font color="red"><b>1</b>
								</font>
							</td>
						</tr>
					</table>
					<c:if test="${sessionScope.slevel == 1 }">
						<p>
							<a href="<%=path %>/QueryEmpDataServlet">增加新员工</a>					
					    </p>
				    </c:if>
				</td>
			</tr>
		</table>

		<!-- 页面底部 -->
		<jsp:include page="foot.jsp"></jsp:include>
	</body>
</html>