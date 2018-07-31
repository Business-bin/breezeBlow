<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>

<html>
<head>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/resources/js/brb/brb.js"></script>
<link rel="stylesheet" href="/resources/css/login/login.css">

<title>login</title>
</head>
<%
            //request 한글 인코딩 처리
            request.setCharacterEncoding("UTF-8");
            //name 정보 가져오기
            Enumeration<String> e = request.getParameterNames();
            while (e.hasMoreElements()) {//Enumeration 안에 data가 있다면 ture 없으면 false
                String name = e.nextElement();//정보를 가져와라(각각의 요소들)
                String value = request.getParameter(name);//name에 해당하는

            }

        %>

<body>
	<div class="logind">
		<header>Breeze Blow Admin Login</header>
		<label>로그인 <span>*</span></label>
		<input type="hidden" id="ip" name="ip" value="<%=request.getRemoteAddr()%>"/>
		<input type="text" id="loginId" name="loginId" value=""/>

		<div class="help">메일주소</div>
		<label>비밀번호 <span>*</span></label>
		<input type="password" id="loginPw" name="loginPw" value=""/>

		<div class="help">영어, 숫자 특수문자를 조합한 8자리 이상</div>
		<button id="loginBtn">로그인</button>
	    <a id="passFind">비밀번호찾기</a>
	</div>
</body>
<script src="/resources/js/login/login.js"></script>
</html>



