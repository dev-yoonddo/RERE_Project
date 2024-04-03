<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome ReRe</title>
</head>
<body>
	<c:set var="userID" value="${userID}"/>
	<div id="header" class="de-active">
		<nav class="navbar">
			<nav class="navbar_left">
				<div class="navbar_logo">
					<a href="/" id="mainlogo">ReRe</a>
				</div>
				<ul class="navbar_menu" style="float: left;">
					<li><a href="/community" class="menu">GO</a></li>
					<c:if test="${not empty user}">
                    <li><a>${userID}님 로그인</a></li>
                    </c:if>
				</ul>
			</nav>
			<ul class="navbar_login">
			    <c:choose>
                    <c:when test="${user == null}">
                        <li>
                        <div id="google-btn" style="width: 50px;">
                            <a href="/oauth2/authorization/google">
                            <img src="resources/image/web_neutral_rd_na@3x.png" style="width: 100%;"/>
                            </a>
                        </div>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/user/update"><i class="fa-solid fa-gear"
                                id="updateicon"></i></a></li>
                        <li><a href="/logout">LOGOUT</a></li>
                    </c:otherwise>
				</c:choose>
			</ul>
			<a class="navbar_toggleBtn" id="toggleicon"> <i
				class="fa-solid fa-bars"></i>
			</a> <input type="checkbox" id="toggleDeActive" hidden="hidden">
		</nav>
	</div>
<script>
function check(){
    location.href='/check-login';
}
//let msg = "${msg}";
//if (msg != "") {
//    console.log(msg);
//   let ok = confirm(msg);
//   if(ok == true){
//    location.href='login';
//    }else{
//    history.back();
//    }
    //window.location.replace("login");
//}
</script>
</body>
</html>