<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 현재 페이지를 단독 실행하지 않습니다. 서블릿 요청을 하면 JSP 페이지가 같이 실행된다. --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
$(document).ready(function() {
	
	<%-- EL를 이용한 동적 데이터 출력 --%>
	$("#skey").find("option[value='${skey}']").attr("selected", "seleted");
	
	<%-- EL를 이용한 동적 데이터 출력 --%>
	$("#svalue").val("${svalue}");
	
	$("#skey").on("change",function(){
		$("#svalue").val("");		
	});
	
});
</script>

</head>
<body>
	<div class="container">

		<h1>
			회원관리 <small>v5.0</small>
		</h1>

		<div class="panel panel-default">
			<div class="panel-heading">회원 정보 입력</div>
			<div class="panel-body">

				<!-- action="확장자 방식의 서블릿요청주소" -->
				<form action="memberinsert.it" method="post">

					<div class="form-group">
						<label for="name">이름:</label> <input type="text"
							class="form-control" id="name" name="name" required maxlength="20"  placeholder="max 20">
					</div>
					<div class="form-group">
						<label for="phone">전화번호:</label> <input type="text"
							class="form-control" id="phone" name="phone" required maxlength="20"  placeholder="max 20">
					</div>
					<div class="form-group">
						<label for="email">이메일:</label> <input type="text"
							class="form-control" id="email" name="email" required maxlength="50"  placeholder="max 50">
					</div>

					<button type="submit" class="btn btn-default">Submit</button>

				</form>

			</div>
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">회원 명단 출력</div>
			<div class="panel-body">

				<table class="table table-striped">
					<thead>
						<tr>
							<th>번호</th>
							<th>이름</th>
							<th>전화번호</th>
							<th>이메일</th>
						</tr>
					</thead>
					<tbody>

						<!-- 
						<tr>
							<td>1</td>
							<td>John</td>
							<td>Doe</td>
							<td>john@example.com</td>
						</tr>
						<tr>
							<td>2</td>
							<td>Mary</td>
							<td>Moe</td>
							<td>mary@example.com</td>
						</tr>
						 -->
						<%-- JSTL, EL를 이용한 동적 데이터 출력 --%>
						<c:forEach var="m" items="${list}">
						<tr>
							<td>${m.mid}</td>
							<td>${m.name}</td>
							<td>${m.phone}</td>
							<td>${m.email}</td>
						</tr>
						</c:forEach>

					</tbody>
				</table>

				<form method="post" class="form-inline">

					<div class="form-group">
						<button type="button" class="btn btn-default">
							<%-- EL를 이용한 동적 데이터 출력 --%>
							Count <span class="badge">${count}</span>
						</button>
					</div>

					<div class="form-group">
						<select class="form-control" name="skey" id="skey">
							<option value="all">전체</option>
							<option value="name">이름</option>
							<option value="phone">전화번호</option>
							<option value="email">이메일</option>
						</select>
					</div>

					<div class="form-group">
						<input type="text" class="form-control" id="svalue" name="svalue">
					</div>

					<button type="submit" class="btn btn-default">Search</button>

				</form>

			</div>
		</div>

	</div>
</body>
</html>