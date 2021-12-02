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
	
	var cnt = 1;
	function fn_addFile() {
		$("#d_file")
				.append("<br>" + "<input type='file' name='file"+cnt+"' />");
		cnt++;
	}
	
	$(document).ready(function() {
	      $('#c_cImgMod').hide(); //페이지를 로드할 때 숨길 요소
	      $('#c_modBtn').hide();
	      $('#c_mod').show();
	      $('#c_rmv').show();
	      
	      $('#c_mod').click(function(){
	      $('#c_mod').hide(); //클릭 시 첫 번째 요소 숨김
	      $('#c_rmv').hide(); //클릭 시 첫 번째 요소 숨김
	      $('#c_cImgMod').show(); //클릭 시 두 번째 요소 표시
	      $('#c_modBtn').show();
	      $('#c_cTitle').prop('disabled', false);
	      $('#c_basicPrice').prop('disabled', false);
	      $('#c_lang').prop('disabled', false);
	      $('#c_cContents').prop('disabled', false);
	      return false;
	      });
	      
	      });
	
	
	
	function fn_modify_coach(obj){
		obj.action="${contextPath}/modCoach";
		obj.submit();
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

	<!-- 프로젝트 글 [조회, 수정, 삭제] 바 -->
	<form method="post" enctype="multipart/form-data" name="frmCoach" action="${contextPath}">
		<section class="py-5">
			<div class="container main-secction">
				<div class="row">

					<!-- 좌측 프로필 : leader~proImg / leader~name / 등급이미지 -->
					<div class="col-md-3 col-sm-3 col-xs-12 user-profil-part pull-left">
						<div class="row">
							<div
								class="col-md-12 col-md-12-sm-12 col-xs-12 user-image text-center"
								style="width: 80%; height: 100%; border: 2px solid;">

								<!-- 후기 조회 이동 -->
								<br> <span style="float: right;"> <a
									href="/cocoa/view_reviewInfo"> <input type="button"
										name="view_reviewInfo" value="후기" style="font-size: 10px;">
								</a>
								</span>

								<!-- 프로필 조회 이동 -->
								<br> <br> <a href="/cocoa/view_profileInfo"> <img
									name="proImg" src="resources/image/kakao.png"
									style="border: 1px solid;" width="120px" height="120px"><br>
									<br>
								</a>

								<!-- coach -->
								<input type="text" name="coach" value="${coach.coach }" readonly
									style="text-align: center; border: 0; background-color: #FFEBCD;">
									<input type="hidden" name="coachNO" value="${coach.coachNO }"/><br>
								<br>


								<!-- 요청서 작성 -->
								<a href="/cocoa"> <input type="button" name="requestForm"
									value="요청서 작성" style="text-align: center; border: 1;">
								</a><br> <br>

								<!-- 본인이면 수정(submit) / 삭제(버튼) 표시 -->
								<!-- submit이 2개라서 formaction 사용 (post 방식) -->
								
									<c:if test="${member.id == coach.coach }">
										<input type="button" class="btn btn-outline-dark" value="수정"
											onClick="fn_enable(frmCoach)" id="c_mod"> &nbsp;
								<input type="button" class="btn btn-outline-dark" value="삭제"
											onClick="fn_remove_coach('${contextPath}/removeCoach', ${coach.coachNO })" id="c_rmv">
									</c:if>
								
								<br> <br>
							</div>
						</div>
					</div>

					<!-- 우측 내용 : cImg / cTitle / basicPrice / cContents -->
					<div class="card"
						style="width: 50rem; border: 1px solid; background-color: #FFCC99">
						
						

						<!-- cImg -->
						<div align="center">
						<input  type= "hidden"   name="originalFileName" value="${coach.cImg }" />
							<br> <img id="preview"
								src="${contextPath}/coachImgDownload?coach=${coach.coach }&coachNO=${coach.coachNO}&cImg=${coach.cImg}"
								width=100% height=300 style="border: 1px solid;" /> <br> <label
								class="btn btn-outline-dark" for="c_cImg" id="c_cImgMod">
								대표 이미지 변경 </label><input type="file" id="c_cImg" name="cImg"
								onchange="readURL(this);" style="display: none;"/><br> <br>
						</div>

						<!-- cTitle / basicPrice / cContents 조회 -->
						<div class="project">

							<!-- cTitle 표시 -->
							<hr>
							<input name="cTitle" type="text" value="${coach.cTitle }"
								id="c_cTitle" disabled
								style="border: 0; text-align: center; width: 100%; background-color: #FFCC99;">
							<hr>

							<!-- basicPrice 표시 -->
							기본요금 : <input name="basicPrice" id="c_basicPrice" type="text"
								value="${coach.basicPrice }" disabled 
								style="border: 0; width: 10%; text-align: center; background-color: #FFCC99;">
							<b>원</b>
							<hr>

							<!-- 언어 표시 -->
							언어 : <select
								style="text-align: center; width: 30%; background-color: #FFCC99;"
								name="lang" disabled id="c_lang">
								<option id="empty">${coach.lang }</option>
								<option id="lang1">lang1</option>
								<option id="lang2">lang2</option>
								<option id="lang3">lang3</option>
								<option id="lang4">lang4</option>
								<option id="lang5">lang5</option>
							</select>
							<hr>

							<!-- cContents 표시 -->
							<!-- textarea 닫아주는거 붙여써야함 -->
							세부 내용 : <br> <br>
							<textarea name="cContents" rows="10" cols="20"
								 disabled id="c_cContents"
								style="border: 1; width: 100%; background-color: #FFCC99;">${coach.cContents }</textarea>
							<hr>

							<!-- map (일단비워둠) -->
							<div style="text-align: center;">이곳은 맵 공간입니다.</div>
							<hr>
							<div align="center">
							<input type=button value="수정반영하기"   onClick="fn_modify_coach(frmCoach)" id="c_modBtn">
							</div>
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