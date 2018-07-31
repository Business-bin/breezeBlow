<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" type="text/css" href="/resources/css/status/status.css" />
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>

<!-- 소스 작성 start-->
	<section>
		<div class="container">

			<div class="container_body">
			<ul class="tabmenu web">
				<li class="active"><a href="/status/status">사용현황표</a></li>
			</ul>
			<!-- <div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>사용현황표</div>
			</div> -->
			<div id="map" class="mgr30 mgl30">
				<img src="/resources/images/koreaMap.png" usemap="#Map1"/>
				<map name="Map1">
				     <area shape="rect" coords="130,131,178,155" alt="경기도" href="#" id="m1"/>
				     <area shape="rect" coords="113,188,145,208" alt="서울" href="#" id="m2"/>
				     <area shape="rect" coords="62,202,94,221" alt="인천" href="#" id="m3"/>
				     <area shape="rect" coords="292,151,339,175" alt="강원도" href="#" id="m4"/>
				     <area shape="rect" coords="77,362,136,384" alt="충청남도" href="#" id="m5"/>
				     <area shape="rect" coords="163,398,197,417" alt="대전" href="#" id="m6"/>
				     <area shape="rect" coords="189,326,250,348" alt="충청북도" href="#" id="m7"/>
				     <area shape="rect" coords="338,395,395,415" alt="경상북도" href="#" id="m8"/>
				     <area shape="rect" coords="346,483,379,502" alt="대구" href="#" id="m9"/>
				     <area shape="rect" coords="125,516,183,538" alt="전라북도" href="#" id="m10"/>
				     <area shape="rect" coords="278,576,341,600" alt="경상남도" href="#" id="m11"/>
				     <area shape="rect" coords="426,547,464,570" alt="울산" href="#" id="m12"/>
				     <area shape="rect" coords="414,610,445,628" alt="부산" href="#" id="m13"/>
				     <area shape="rect" coords="99,603,133,625" alt="광주" href="#" id="m14"/>
				     <area shape="rect" coords="92,650,152,673" alt="전라남도" href="#" id="m15"/>
				     <area shape="rect" coords="38,844,86,867" alt="제주도" href="#" id="m16"/>
				</map>
			</div>
			<div id="status">
				<div id="con1" class="mgt30">
					<h1>현재지역</h1>
					<h3 class="mgt4 mgb4" id="curLoc"></h3>
				</div>
				<div id="con2" class="mgt10">
					<h1>이용현황</h1>
					<table class="basictables mgt14">
						<colgroup>
							<col width="50%"/>
							<col width="25%"/>
						</colgroup>
						<tr>
							<th>디바이스수</th><td id="dCount"></td>
						</tr>
					</table>
				</div>
				<div id="con3" class="mgt10">
					<h1>대기현황</h1>
					<p class="ar" id="curTime"></p>
					<table class="basictables mgt14">
						<colgroup>
							<col width="25%"/>
							<col width="25%"/>
							<col width="25%"/>
							<col width="25%"/>
						</colgroup>
						<tr>
							<th></th><th>최소</th><th>평균</th><th>최대</th>
						</tr>
						<tr id="temp">
						</tr>
						<tr id="humi">
						</tr>
						<tr id="hcho">
						</tr>
						<tr id="co2">
						</tr>
						<tr id="co">
						</tr>
						<tr id="pm25">
						</tr>
						<tr id="atopy">
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/status/status.js"></script>