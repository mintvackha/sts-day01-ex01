<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>���� �Խ��� ���</title>
</head>
<body>

<h1>�Խ� �� ���</h1>
<table width="600" border="1">
	<tr>
		<th>��ȣ</th>
		<th>����</th>
		<th>�۾���</th>
		<th>��¥</th>
		<th>��ȸ��</th>
	</tr>
	<c:forEach var="row" items="${boardList }">
		<tr>
			<td>${row.seq }</td>
			<td><a href="detail.do?seq=${row.seq }">${row.title }</a></td>
			<td>${row.writer }</td>
			<td>${row.regdate }</td>
			<td>${row.cnt }</td>
		</tr>
	</c:forEach>
</table>
<a href="input.do">�۾���</a>

</body>
</html>