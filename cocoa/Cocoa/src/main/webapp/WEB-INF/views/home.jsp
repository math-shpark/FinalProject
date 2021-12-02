<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
<link href="resources/css/styles.css" rel="stylesheet" />
<script type="text/javascript" src="resources/js/jquery-3.6.0.min.js"></script>
<title>CoCoa</title>
<script>
	$(document).ready(function() {

		// 코칭 카테고리 클릭시 [코칭 글 작성] 표시
		$('#coaching').click(function() {
			$('#cwrite_btn').removeAttr('hidden');
			$('#pwrite_btn').attr('hidden', '');
		});

		// 프로젝트 카테고리 클릭시 [프로젝트 글 작성] 표시
		$('#project').click(function() {
			$('#cwrite_btn').attr('hidden', '');
			$('#pwrite_btn').removeAttr('hidden');
		});
	});
</script>
</head>
<body style="background-color: #FFEBCD">

	<!-- 상단바 -->
	<nav class="navbar navbar-expand-lg" style="background-color: #663333;">

		<div class="container px-4 px-lg-5">

			<!-- 로고 -->
			<a class="navbar-brand" href="/cocoa/" style="color: black;"><b>CoCoa</b></a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">

				<!-- 상단 가운데공간 지우면 안됌 -->
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
				</ul>

				<!-- 우측 상단 변경 -->
				<c:choose>
					<c:when test="${isLogOn == true && member != null}">
						<form action="/cocoa/logout" method="get" class="d-flex">
							<input name="My Page" class="btn btn-outline-dark" type="button"
								value="My Page" onClick="location.href='/cocoa/myPage'" /> <input
								name="logout" class="btn btn-outline-dark" type="submit"
								value="logout" />
						</form>
					</c:when>
					<c:otherwise>
						<form action="/cocoa/view_login" method="get" class="d-flex">
							<input name="login" class="btn btn-outline-dark" type="submit"
								value="log in" /> <input name="join"
								class="btn btn-outline-dark" type="button" value="Sign in"
								onClick="location.href='/cocoa/view_join'" />
						</form>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</nav>

	<!-- 카테고리바 -->
	<header class="py-5" style="background-color: #663333">
		<div class="container px-4 px0lg05 my-5">

			<!-- 코칭 / 프로젝트 -->
			<div class="d-grid gap-3 d-sm-flex justify-content-sm-center">
				<a id="coaching" class="btn btn-primary btn-lg px-4 me-sm-3">Coaching</a>
				<a id="project" class="btn btn-primary btn-lg px-4 me-sm-3">Project</a>
			</div>
			<br>

			<!-- 코칭 세부 -->
			<div class="d-grid gap-3 d-sm-flex justify-content-sm-center">
				<a class="btn btn-primary btn-lg px-4 me-sm-3" href="">C/C++</a> <a
					class="btn btn-primary btn-lg px-4 me-sm-3" href="">Java</a> <a
					class="btn btn-primary btn-lg px-4 me-sm-3" href="">Python</a> <a
					class="btn btn-primary btn-lg px-4 me-sm-3" href="">PHP</a> <a
					class="btn btn-primary btn-lg px-4 me-sm-3" href="">Swift</a>
			</div>

			<!-- 프로젝트 세부(미완) -->
		</div>
	</header>

	<!-- 카탈로그바 -->
	<section class="py-5">
		<div class="container px-4 px-lg-5 mt-5">

			<!-- 글 주제별 이동 -->
			<!-- 로그인 여부에따라 숨김 / 표시 -->
			<c:choose>
				<c:when test="${isLogOn == true && member != null}">
					<a id="pwrite_btn" class="btn btn-primary px-4 me-sm-3"
						href="/cocoa/view_projectWrite" style="float: right" hidden="">p글작성</a>
					<a id="cwrite_btn" class="btn btn-primary px-4 me-sm-3"
						href="/cocoa/view_coachWrite" style="float: right">c글작성</a>
				</c:when>
			</c:choose>
			<br>
			<br>
			<br>


			<!-- 생성된 카탈로그 표시 (select된 개수만큼 반복) + 페이징 필요 -->
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="coach" items="${coachesList }">

					<!-- 카탈로그 틀 -->
					<div class="col mb-5">
						<div class="card h-100">

							<!-- 언어 표시 -->
							<div class="badge bg-dark text-white position-absolute"
								style="top: 0.5rem; right: 0.5rem">${coach.lang}</div>

							<!-- 간판 이미지 (src=경로) -->
							<img class="card-img-top"
								src="${contextPath}/coachImgDownload?coach=${coach.coach }&coachNO=${coach.coachNO}&cImg=${coach.cImg}"
								height="200px" alt="..." />

							<!-- 간판이미지 아래 정보 -->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- 제목 -->
									<h5 class="fw-bolder">${coach.cTitle }</h5>
									<!-- 코치 -->
									${coach.coach}<br>
									<!-- 기본요금 -->
									${coach.basicPrice} 원
								</div>
							</div>

							<!-- coachInfo / projectInfo 이동 -->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="${contextPath}/viewCoach?coachNO=${coach.coachNO}">살펴보기</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<!-- 위의 카탈로그 틀이 반복 생성 및 표시 -->
			</div>
		</div>
	</section>

	<!-- 하단바 (마지막에 추가) -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; CoCoa 2021</p>
		</div>
	</footer>
</body>
</html>