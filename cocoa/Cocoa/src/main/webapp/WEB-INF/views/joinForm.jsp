<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8'>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<link href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' rel='stylesheet'>
<link href='https://use.fontawesome.com/releases/v5.7.2/css/all.css' rel='stylesheet'>
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<script type='text/javascript' src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<link href="resources/css/join-styles.css" rel="stylesheet" />
<script type="text/javascript" src="resources/js/ajax.js"></script>
<title>Sign In</title>
</head>
<body>
	<div class="container">

		<div class="card rcol">
			<h2 class="heading mt-3 mb-4" align="center">
				<a href="/cocoa">CoCoa</a>
			</h2>
			<h3 class="mb-2">회원가입</h3>
			<form action="" method="post">
				<table width=460px>
					<tr>
						<td><div class="fone">
								<i class="fas fa-id-card"></i> <input type="text" name="id"
									id="id" class="form-control" placeholder="아이디(ID)">
							</div></td>
						<td align="center"><input type="button" class="btn btn-check" id="idCheck"
							value="ID 확인"></td>
					</tr>
					<tr>
						<td colspan="2"><div class="fone mt-2">
								<i class="fas fa-lock"></i> <input type="password" name="pwd"
									class="form-control" id="pwd1" placeholder="비밀번호(Password)"></td>
					</tr>
					<tr>
						<td colspan="2"><div class="fone mt-2">
								<input type="password" class="form-control" id="pwd2"
									placeholder="비밀번호 확인(Password Check)"></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="ml-3">
								<span id="alert-success" style="display: none;">비밀번호가일치합니다.</span> 
								<span id="alert-danger"
									style="display: none; color: #d92742; font-weight: bold;">비밀번호가 일치하지 않습니다.</span>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2"><div class="fone mt-2">
								<i class="fas fa-user"></i> <input type="text" name="name"
									id="name" class="form-control"
									placeholder="이름(Name) 또는 별명(Nickname)">
							</div></td>
					</tr>
					<tr>
						<td colspan="2"><div class="fone mt-2">
								<i class="fas fa-phone"></i> <input type="text" name="phone"
									id="phone" class="form-control"
									placeholder="전화번호(Phone Number)">
							</div></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><button type="submit"
								id="validate" class="btn btn-success mt-5">회원가입(Sign
								Up)</button></td>
					</tr>
				</table>
			</form>
			<p class="exist mt-2">
				계정이 이미 있으신가요? <a class="exist" href="view_login">로그인하기</a>
			</p>
		</div>
	</div>
</body>
</html>