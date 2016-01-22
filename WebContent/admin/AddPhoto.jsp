<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String path = request.getContextPath();%>
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/style.css">
		
		<script type="text/javascript">
		   function uploadPhoto(){
			   document.forms[0].action="<%=path%>/UploadPhotoServlet?emp_id="+<%=request.getParameter("emp_id")%>;
		   }
		</script>
	</head>
    
	<body>
		<form action="" method="post" enctype="multipart/form-data" name="form1" onsubmit="uploadPhoto()">
			<table width="500" border="0" align="center" cellpadding="5"
				cellspacing="1" bgcolor="#CCCCCC">
				<tr>
					<td align="center" bgcolor="#FF9900">
						
					</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFFFFF">
						<input type="file" name="photo_path" size="40" >
						<input type="submit" name="Submit" value="开始上传" >
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>