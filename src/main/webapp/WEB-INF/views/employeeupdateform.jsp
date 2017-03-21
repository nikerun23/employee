<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>직원 관리</title>

<!-- jqueryUI 환경 설정 추가 -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- jqueryUI 환경 설정 추가 -->
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script>
	$(document).ready(function() {
		
		//jqueryUI datepicker 호출 코드
		//HTML5의 달력 기능은 구형 브라우저에서 지원 안됨.
		$("#birthday").datepicker({
			changeMonth : true, //월 변경 가능
			changeYear : true, //년도 변경 가능
			dateFormat : "yy-mm-dd" //날짜 서식 지정
		});
		
		//ready 이벤트
		//Ajax 요청
		ajax($("#positionId").val());

		//SELECT 태그에 대한 change 이벤트
		$("#positionId").on("change", function() {
			//Ajax 요청
			ajax($(this).val());
		});
		
	});
	
	
	function ajax(positionId) {
		//Ajax 요청
		$.post("ajaxminbasicpay.it", {positionId:positionId}, function(data) {
			//메시지 수정
			$("#basicPay").attr("placeholder", "기본급 (최소 "+data+"원 이상)");
			//최소값 지정
			$("#basicPay").attr("min", data);
		});
	}
	
</script>

</head>
<body>
	<div class="container">
		<div style="margin-bottom: 1%;">
			<div>
				<h1 style="font-size: x-large;">
					<img src="${pageContext.request.contextPath}/resources/img/sist_logo.png" alt="logo"
					style="vertical-align: bottom;"> 직원관리<small>v2.0</small>
				</h1>
			</div>
			<div>
				<ul class="nav nav-pills nav-justified ">
					<li class="active"><a href="employeelist.it">직원관리</a></li>
					<li><a href="regionlist.it">지역관리</a></li>
					<li><a href="departmentlist.it">부서관리</a></li>
					<li><a href="positionlist.it">직위관리</a></li>
					
					<%-- 세션 정보를 EL 표현으로 출력 --%>
					<li><a href="logout.it" style="color: red">${sessionScope.adminloginkey} 로그아웃</a></li>

				</ul>
			</div>

		</div>
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">직원 수정</div>
				<div class="panel-body">
					<form role="form" action="employeeupdate.it" method="post">
					
						<%-- 수정 액션을 위한 직원번호 폼 추가 --%>
						<%-- 
						value="${emp.employeeId}" 속성을 지정하면
						서블릿에서 전달받은 Employee 객체의 employeeId 속성의 값을 
						value="" 속성의 값으로 지정하게 된다.
						--%>
						<div class="form-group">
							<label for="employeeId">직원번호:</label> <input type="text"  value="${emp.employeeId}"
								class="form-control" id="employeeId" name="employeeId"
								placeholder="직원번호 (수정시 사용)" required="required" readonly="readonly">
						</div>
						
						
						<div class="form-group">
							<label for="name">이름:</label> <input type="text" value="${emp.name}"
								class="form-control" id="name" name="name"
								placeholder="이름 (30자 이내)" maxlength="30" required="required">
						</div>
						
						<%-- 수정시 사용하지 않는 항목 --%>
						<%-- 
						<div class="form-group">
							<label for="ssn">주민번호(뒷자리):</label>
							<input type="number"
								class="form-control " id="ssn" name="ssn"
								placeholder="주민번호 뒷자리 (NNNNNNN)"  min="1000000"  max="9999999"
								required="required">
						</div>
						 --%>
						 
						<div class="form-group">
							<label for="birthday">생년월일:</label> <input type="text"  value="${emp.birthday}"
								class="form-control" id="birthday" name="birthday"
								placeholder="생년월일 (YYYY-MM-DD)" required="required">
						</div>

						<div class="form-group">
							<label for="lunar">양력음력:</label>
							<div class="radio">
								<%-- 
								${(emp.lunar==0)?"checked=\"checked\"":""} 지정하면
								서블릿에서 전달받은 Employee 객체의 lunar 속성의 값과
								양력, 음력 값을 비교해서 값이 일치하는 경우 
								checked="checked" 속성 지정하게 된다.
								--%>
								<label class="radio-inline"> <input type="radio"
									name="lunar" value="0" ${(emp.lunar==0)?"checked=\"checked\"":""}>양력
								</label> <label class="radio-inline"> <input type="radio"
									name="lunar" value="1" ${(emp.lunar==0)?"":"checked=\"checked\""}>음력
								</label>
							</div>
						</div>


						<div class="form-group">
							<label for="telephone">전화번호:</label> <input type="text"  value="${emp.telephone}"
								class="form-control" id="telephone" name="telephone"
								placeholder="전화번호 (30자 이내)" maxlength="30" required="required">
						</div>
						<div class="form-group">
							<label for="regionId">지역:</label> <select class="form-control"
								id="regionId" name="regionId">
								<%-- 데이터베이스에서 읽어온 지역정보를 동적 출력. JSTL, EL 사용 --%>
								<!-- <option value="1">서울</option> -->
								
								<%-- 
								${(emp.regionId==r.regionId)?"selected=\"selected\"":""} 지정하면
								서블릿에서 전달받은 Employee 객체의 regionId 속성의 값과
								r.regionId 값을 비교해서 값이 일치하는 경우 
								selected="selected" 속성 지정하게 된다.
								--%>
								<c:forEach var="r" items="${regionList}">
								<option value="${r.regionId}"  ${(emp.regionId==r.regionId)?"selected=\"selected\"":""} >${r.regionName}</option>
								</c:forEach>
								
							</select>
						</div>
						<div class="form-group">
							<label for="departmentId">부서:</label> <select
								class="form-control" id="departmentId" name="departmentId">
								<%-- 데이터베이스에서 읽어온 부서정보를 동적 출력. JSTL, EL 사용 --%>
								<!-- <option value="1">개발부</option> -->
								
								<c:forEach var="d" items="${departmentList}">
								<option value="${d.departmentId}" ${(emp.departmentId==d.departmentId)?"selected=\"selected\"":""}>${d.departmentName}</option>
								</c:forEach>
								
							</select>
						</div>
						<div class="form-group">
							<label for="positionId">직위:</label> 
							
							<%-- 직원 선택 이벤트(change)에 대해서 Ajax 액션 처리  --%>
							<select class="form-control"
								id="positionId" name="positionId">
								<%-- 데이터베이스에서 읽어온 직위정보를 동적 출력. JSTL, EL 사용 --%>
								<!-- <option value="1">사원</option> -->
								
								<c:forEach var="p" items="${positionList}">
								<option value="${p.positionId}" ${(emp.positionId==p.positionId)?"selected=\"selected\"":""}>${p.positionName}</option>
								</c:forEach>
								
							</select>
							
						</div>
						<div class="form-group">
							<label for="basicPay">기본급:</label>
							
							<%-- 기본급 (최소 XXX원 이상) 메시지는 Ajax 요청 및 응답 처리 --%>
							<input type="number"  value="${emp.basicPay}"
								class="form-control" id="basicPay" name="basicPay"
								placeholder="기본급 (최소 0원 이상)" required="required">
								
						</div>
						<div class="form-group">
							<label for="extraPay">수당:</label> <input type="number"  value="${emp.extraPay}"
								class="form-control" id="extraPay" name="extraPay"
								placeholder="수당" required="required">
						</div>

						<button type="submit" class="btn btn-default">Submit</button>
					</form>

				</div>
			</div>
		</div>
	</div>

</body>
</html>