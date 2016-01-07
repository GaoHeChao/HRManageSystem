<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="../css/style.css">
	</head>

	<body>
		<form action="AddPhotoServlet?employee_id=100" method="post" enctype="multipart/form-data"
			name="form1">
			<table width="500" border="0" align="center" cellpadding="5"
				cellspacing="1" bgcolor="#CCCCCC">
				<tr>
					<td align="center" bgcolor="#FF9900">
						
					</td>
				</tr>
				<tr>
					<td align="center" bgcolor="#FFFFFF">
						<input type="file" name="photo_path" size="40">
						<input type="button" name="Submit" value="娣诲姞鍛樺伐鍥剧墖" onClick="location.href='PhotoViewServlet.html'">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>