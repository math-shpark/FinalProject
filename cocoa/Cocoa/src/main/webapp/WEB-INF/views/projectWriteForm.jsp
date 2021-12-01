<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
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
				<form action="/cocoa/logout" method="get" class="d-flex">
					<input name="My Page" class="btn btn-outline-dark" type="button"
						value="My Page" onClick="location.href='/cocoa/myPage'" /> <input
						name="logout" class="btn btn-outline-dark" type="submit"
						value="logout" />
				</form>

			</div>
		</div>
	</nav>

	<!-- 프로젝트 글 작성바 -->
	<form action="${contextPath}/projectWrite" method="post" enctype="multipart/form-data">
		<section class="py-5">
			<div class="container main-secction">
				<div class="row">
				
					<!-- 좌측 프로필 : leader~pImg / leader~name / 등급이미지 -->
					<div class="col-md-3 col-sm-3 col-xs-12 user-profil-part pull-left">
						<div class="row">
							<div class="col-md-12 col-md-12-sm-12 col-xs-12 user-image text-center">
							
								<!-- 프로필 사진 클릭시 조회 가능 -->
								<a href="/cocoa/view_profileInfo">
									<img name="pImg" src="resources/image/kakao.png" width="120px" height="120px"><br><br>
								</a>
	
								<!-- leader -->
								<input type="text" name="leader" value="coach" readonly
									style="text-align: center; border: 0; background-color:#FFEBCD;"><br> <br>
	
								<!-- 등급이미지 : 좋아요수에 따른 변경 로직 필요 -->
								<img name="tier" src="resources/image/tier.jpg" width="50px" height="50px"><br><br>
								
								<!-- kakao -->
								카카오톡 오픈채팅 링크 :<br><br>
								<input type="text" name="kakao" value="" placeholder="오픈채팅 링크 입력"
									style="text-align: center; border: 1; background-color:#FFEBCD;"><br> <br>
							</div>
						</div>
					</div>
	
					<!-- 우측 내용 : pImg / pTitle / memberCount / pContents -->
					<div class="card" style="width: 50rem;">
						
						<!-- pImg -->
						<div align="center">
							<br><img id="preview" src="..." width=90%
								height=300 /><br><br> <label class="btn btn-outline-dark"
								for="pImg"> 대표 이미지 변경 </label><input type="file" id="pImg"
								name="pImg" onchange="readURL(this);" style="display: none;" />
						</div>
						
						<!-- pTitle / memberCount / pContents 입력 -->
						<div class="project">
						
							<!-- pTitle 입력 -->
							<hr><input name="pTitle" type="text" placeholder="프로젝트명을 입력하세요."
								style="border: 1; text-align: center; width: 100%;"><hr>
							
							<!-- memberCount 입력 -->
							인원 : <input name="memberCount" type="number" placeholder="인원수를 입력하세요."
									style="border: 1; width:30%;">&nbsp;<b>명</b><hr>
	
							<!-- level 선택 -->
							난이도 : <select style="text-align:center; width:30%;" name="level">
									<option id="empty">-- 선택 --</option>
									<option id="level1">하수</option>
									<option id="level2">중수</option>
									<option id="level3">고수</option>
								</select><hr>
							
							<!-- pContents 입력 -->
							<!-- textarea 닫아주는거 붙여써야함 -->
							세부 내용 : <br>
								<textarea name="pContents" rows="10" cols="20" 
								placeholder="프로젝트 개요 및 포지션 별 자격요건을 써주세요."
								style="border: 1;width: 100%;"></textarea><hr>
								
							<!-- map (일단비워둠) -->
							<div><center>이곳은 맵 공간입니다.</center></div><hr>
						</div>
	
						<!-- 작성(submit) + 취소(버튼) -->
						<div class="card-body" style="text-align: center">
							<button type="submit" class="btn btn-outline-dark">작성</button>
							&nbsp;
							<a href="/cocoa/" class="btn btn-outline-dark">취소</a>
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