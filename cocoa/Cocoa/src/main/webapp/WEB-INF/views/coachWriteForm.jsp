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
<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj) {
		obj.action = "${contextPath}/cocoa";
		obj.submit();
	}

	var cnt = 1;
	function fn_addFile() {
		$("#d_file")
				.append("<br>" + "<input type='file' name='file"+cnt+"' />");
		cnt++;
	}
</script>
<title>CoCoa</title>
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

	<!-- 코칭 글 작성바 -->
	<form name="coachWriteForm" method="post"
		action="${contextPath}/coachWrite" enctype="multipart/form-data">
		<section class="py-5">
			<div class="container main-secction">
				<div class="row">
				
					<!-- 좌측 프로필 : coach~pImg / coach~name / 등급이미지 -->
					<div class="col-md-3 col-sm-3 col-xs-12 user-profil-part pull-left">
						<div class="row">
							<div class="col-md-12 col-md-12-sm-12 col-xs-12 user-image text-center">
							
								<!-- 프로필 사진 클릭시 조회 가능 -->
								<a href="/cocoa/view_profileInfo">
									<img name="pImg" src="resources/image/kakao.png" width="120px" height="120px"><br><br>
								</a>
	
								<!-- coach -->
								<input type="text" name="coach" value="coach" readonly
									style="text-align: center; border: 0; background-color:#FFEBCD;"><br> <br>
	
								<!-- 등급이미지 : 좋아요수에 따른 변경 로직 필요 -->
								<img name="tier" src="resources/image/tier.jpg" width="50px" height="50px"><br><br>
							</div>
						</div>
					</div>
	
					<!-- 우측 내용 : cImg / cTitle / basicPrice / cContents -->
					<div class="card" style="width: 50rem;">
						
						<!-- cImg -->
						<!-- 파일업로드 기능 필요 -->
						<div align="center">
							<img id="preview" src="resources/image/open-book.png" width=200
								height=200 /><br> <label class="btn btn-outline-dark"
								for="cImg"> 대표 이미지 변경 </label><input type="file" id="cImg"
								name="cImg" onchange="readURL(this);" style="display: none;" />
						</div>
						
						<!-- cTitle / basicPrice / cContents 입력 -->
						<div class="coach">
						
							<!-- cTitle 입력 -->
							<hr><input name="cTitle" type="text" placeholder="제목을 입력하세요."
								style="border: 1; text-align: center; width: 100%;"><hr>
							
							<!-- basicPrice 입력 -->
							요금 : <input name="basicPrice" type="number" placeholder="요금을 입력하세요."
									style="border: 1; width:30%;">&nbsp;<b>원</b><hr>
	
							<!-- lang 선택 -->
							언어 : <select style="text-align:center; width:30%;" name="lang">
									<option id="empty">-- 선택 --</option>
									<option id="lang1">lang1</option>
									<option id="lang2">lang2</option>
									<option id="lang3">lang3</option>
									<option id="lang4">lang4</option>
									<option id="lang5">lang5</option>
								</select><hr>
							
							<!-- cContents 입력 -->
							<!-- textarea 닫아주는거 붙여써야함 -->
							세부 내용 : <br>
								<textarea name="cContents" rows="10" cols="20" 
								placeholder="요금 측정 기준 및 본인 PR을 해주세요."
								style="border: 1;width: 100%;"></textarea><hr>
						</div>
	
						<!-- 작성(submit) + 취소(버튼) -->
						<div class="card-body" style="text-align: center">
							<input type="submit" class="btn btn-outline-dark" value="등록" />
							&nbsp; <input type="button" class="btn btn-outline-dark"
								value="취소" onClick="backToList(this.form)" />
						</div>
					</div>
					
				</div>
			</div>
		</section>
	</form>

	<!-- 하단바 (마지막에 추가) -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; CoCoa 2021</p>
		</div>
</footer>
</body>
</html>