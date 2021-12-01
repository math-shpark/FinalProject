<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<link href="resources/css/login-styles.css" rel="stylesheet" />
<title>Sign In</title>
<c:choose>
	<c:when test="${result=='loginFailed'}">
		<script>
			window.onload=function(){
				alert("아이디나 비밀번호가 틀립니다. 다시 로그인하세요!");
			}
		</script>
	</c:when>
</c:choose>
</head>
<body>
 <form action="/cocoa/login" method="post">
<div class="container">
    
        <div class="card rcol py">
            	<h2 class="heading mt-5 mb-4" align="center"><a href="/cocoa">CoCoa</a></h2>
                <h3 class="mb-2">로그인</h3>
               
                <div class="fone mt-3"> <i class="fas fa-id-card"></i> <input type="text" name="id" class="form-control ml-3" placeholder="ID"></div>
                <div class="fone mt-5"> <i class="fas fa-lock"></i> <input type="password" name="pwd" class="form-control ml-3" placeholder="Password"></div>
            <button type="submit" class="btn btn-success mt-5">Log In</button>
           
            <p class="exist mt-2">Don't have an account? <a class="warning" href="view_join">Register</a></p>
        </div>
    </div>
 </form>
</body>
</html>