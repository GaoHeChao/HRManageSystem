<%@page import="com.icss.bean.JobsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<html>
	<head>
		<title>修改职务</title>
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
					<form method="post" action="" name="form1" id="form1" onsubmit="return checkForm()">
					<%
					JobsBean bean =(JobsBean) request.getAttribute("jbean");
					%>
						<table width="500" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#CCCCCC">
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFCC00">
									请修改职务类型信息
								</td>
							</tr>
							<tr>
								<td width="130" height="24" bgcolor="#FFFFFF">
									职务编号
								</td>
								<td width="347" height="24" bgcolor="#FFFFFF">
									<input name="job_id" type="text" id="job_id"
										readonly="readonly" value="<%=bean.getJob_id()%>">
									*
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									职务名称
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="job_title" type="text" id="job_title"
										value="<%=bean.getJob_title()%>">
									*
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									最低工资
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="min_salary" type="text" id="min_salary"
										value="<%=bean.getMin_salary() %>">
									*
								</td>
							</tr>
							<tr>
								<td height="24" bgcolor="#FFFFFF">
									最高工资
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<input name="max_salary" type="text" id="max_salary"
										value="<%=bean.getMax_salary() %>">
									*
								</td>
							</tr>
							<tr>
								<td height="24" colspan="2" align="center" bgcolor="#FFFFFF">
									<input type="submit" name="Submit" value="更新" onClick="'">
									<input type="button" name="Submit2" value="取消"
										onclick="history.back()">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		
		<!-- 验证修改表单 -->
		<script type="text/javascript">
		
		</script>

		<!-- 页面底部 -->
		<jsp:include page="/admin/foot.jsp"></jsp:include>
	</body>
</html>