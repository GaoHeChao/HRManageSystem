<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.sql.*" %>
<% String path = request.getContextPath();%>

<html>
	<head>
		<title>员工图片</title>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<script type="text/javascript">
		    function addphoto(){
		    	location.href = "admin/AddPhoto.jsp?emp_id="+<%=request.getParameter("emp_id")%>;
		    }
		    
		    function delphoto(photo_id){
		    	var flag = window.confirm("你确定要删除第"+ photo_id +"张照片吗？");
		    	if(flag){
		    		location.href = "DelPhotoServlet?photo_id=" + photo_id;
		    	}
		    }
		</script>
	</head>

	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center">
					<a href="javascript:addphoto()">添加<%=request.getParameter("emp_id") %>号员工照片</a>
					<a href="javascript:window.close()">关闭窗口</a>				
			    </td>
			</tr>
		</table>    
		<table>
		    <tr>
		        <th width="10%">员工编号</th>
		        <th width="40%">员工照片</th>
		        <th width="20%">操作</th>
		    </tr>
		    
    		<%
		    ResultSet rs = (ResultSet)request.getAttribute("resultset");
		    while(rs.next()){
		    	
		    %>
		    <tr>
	            <td align="center"><%=rs.getInt(3) %></td>
				<!-- <img src="<%=rs.getString(2) %>/769_JSP内置对象.jpg" /> --> <br />
				<td></td>
				<td>
					<a href="javascript:delphoto(<%=rs.getInt(1) %>)">删除图片</a>	
					<a href="DownPhotoServlet?=<%=rs.getInt(1) %>">下载图片</a>		
				</td>
			</tr>
		    <% } %>
		    
		    <c:forEach items="${fileMap }" var="photos">
		    <tr>
		       <c:url value="DownPhotoServlet" var="downurl">
			           <c:param name="photoName" value="${photos.key }"></c:param>
			   </c:url>
		       <td></td>
		       <td align="center">
			       ${photos.value }
		       </td>
		       <td>
		                   <a href="${downurl }">下载图片</a>
		       </td>
		    </tr>
		    </c:forEach>
		    
		</table>


	</body>
</html>