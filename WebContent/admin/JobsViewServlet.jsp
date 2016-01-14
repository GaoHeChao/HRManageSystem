<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath();%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List" %>
<%@ page import="com.icss.bean.JobsBean" %>
<html>
	<head>
		<title>职务数据</title>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<script type="text/javascript">
			function del(job_id) {
				//弹出确认框
				var flag = window.confirm("你确定要删除" + job_id + "号职务吗?");
								
				//如果单击确定，则执行DelJobsServlet，并且传入职务编号
				if (flag) {
					location.href = "DelJobsServlet?job_id="+job_id;
				}
			}
			
			function upd(job_id){
				location.href = "QueryJobsByIdServlet?job_id=" + job_id;
			}
			
			function delByBatch(){
				var flag = window.confirm("你确定要删除吗？");
				if(flag){
					document.forms[0].action="VolumeDelJobServlet";
				}
			}
		</script>
	</head>

	<body>
		<!-- 页面头部 -->
		<jsp:include page="/admin/header.jsp"></jsp:include>
		
		<!-- 页面内容 -->
    <form method="post" name="form1" onsubmit="delByBatch()">
		<table border="0" width="950" height="350" bgcolor="#ffffff"
			align="center">
			<tr>
				<td align="center" valign="top">
					<table width="90%" border="0" cellpadding="5" cellspacing="1"
						bgcolor="#CCCCCC">
						<tr>
						   <th width="7%" height="24" align="center" bgcolor="#FFCC00">
								职务编号
							</th>
							<th width="16%" height="24" align="center" bgcolor="#FFCC00">
								职务编号
							</th>
							<th width="21%" height="24" align="center" bgcolor="#FFCC00">
								职务名称
							</th>
							<th width="20%" height="24" align="center" bgcolor="#FFCC00">
								最低工资
							</th>
							<th width="20%" height="24" align="center" bgcolor="#FFCC00">
								最高工资
							</th>
							<c:if test="${sessionScope.slevel == 1 }">
							<th width="16%" height="24" align="center" bgcolor="#FFCC00">
								操作
							</th>
							</c:if>
						</tr>

						<!-- 显示职务数据 -->
						<%--
						List<JobsBean> allJobs = (List<JobsBean>) request.getAttribute("allJobs");
						Iterator<JobsBean> it = allJobs.iterator();
						while(it.hasNext()){
                             JobsBean bean = it.next();
						--%>
						<c:forEach items="${requestScope.allJobs }" var="oneJob" varStatus="vs">
							<tr>
							    <td height="24" bgcolor="#FFFFFF">
									<input type="checkbox" name="delJobs" value="${oneJob.job_id }">
								</td>
								<td height="24" bgcolor="#FFFFFF">
									<%--=bean.getJob_id() --%>
									${oneJob.job_id }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneJob.job_title }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneJob.min_salary }
								</td>
								<td height="24" bgcolor="#FFFFFF">
									${oneJob.max_salary }
								</td>
							<c:if test="${sessionScope.slevel == 1 }">
								<td bgcolor="#FFFFFF">
									<a href="javascript:upd('${oneJob.job_id }')">修改</a>
									<a href="javascript:del('${oneJob.job_id }')">删除</a>	
								</td>
							</c:if>
							</tr>
						</c:forEach>
						<%-- } --%>
					</table>
					<c:if test="${sessionScope.slevel == 1 }">
					<p>
						<a href="admin/AddJobs.jsp">增加新职务种类</a>	
						<input type="submit" value="删除选中职务">				
				    </p>
				    </c:if>
				</td>
			</tr>
		</table>
</form>
		<!-- 页面底部 -->
        <jsp:include page="foot.jsp"></jsp:include>
	</body>
</html>