<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" type="text/css" href="/resources/css/pop/pop.css" />
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>

<!-- 소스 작성 start-->
	<section>
		<div class="container">
		
			<div class="container_body">
			<ul class="tabmenu web">
				<li><a href="/etc/popList">팝업목록</a></li>
				<li class="active"><a href="/etc/etcInit">환경설정</a></li>
			</ul>
			<div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>메일발송 관리</div>
			</div>
			
			<table class="basictables mgt14">
				<colgroup>
					<col width="10%"/>
					<col width="40%"/>
					<col width="10%"/>
					<col width="40%"/>
				</colgroup> 
				<tr>
					<th>SMTP HOST</th>
					<td>
						<input type="text"  value="${ETC.HOST}" id="mailHost" name="mailHost" style="width:100%;">
					</td>
					<th>SMTP PORT</th>
					<td>
						<input type="text" value="${ETC.PORT}" id="mailPort" name="mailPort" style="width:100%;">
					</td>
				</tr>
				<tr>
					<th>계정 ID</th>
					<td>
						<input type="text" value="${ETC.ID}" id="mailId" name="mailId" style="width:100%;">
					</td>
					<th>계정 비밀번호</th>
					<td>
						<input type="password" value="${ETC.PWD}" id="mailPwd" name="mailPwd" style="width:100%;">
					</td>
				</tr>
				<tr>
					<th>SSL 사용유무</th>
					<td>
						<div class="checks small">
						<input type="radio" value="Y" id="sslYn1" name="sslYn" <c:if test="${ETC.SSL_YN eq 'Y'}"> checked="checked" </c:if>> 
						<label for="sslYn1">예  </label> 
						<input type="radio" value="N" id="sslYn2" name="sslYn" <c:if test="${ETC.SSL_YN eq 'N'}"> checked="checked" </c:if>> 
							<label for="sslYn2">아니요  </label> 
						</div>
					</td>
					<th>SSL PORT</th>
					<td>
						<input type="text" value="${ETC.SSL_PORT}" id="sslPort" name="sslPort" style="width:100%;">
					</td>
				</tr>
				
			</table>
			<div class="button_area tcenter">
				<button class="button" id="mailSetBtn">설정</button>
			</div>
			<br>
			<div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>Event Alarm 설정</div>
			</div>
			<table class="basictables mgt14">
				<colgroup>
					<col width="10%"/>
					<col width="80%"/>
				</colgroup> 
				<tr>
					<td style="background:#757a88;color:#FFF;">
						아토피 지수  
					</td>
					<td style="background:#757a88;color:#FFF;">
						  최소길이 <input type="number"  value="${ETC.ATO_MIN}" id="atoMin" name="atoMin" style="width:100px;"> 이하 ,
						최대값이  <input type="number"  value="${ETC.ATO_MAX}" id="atoMax" name="atoMax" style="width:100px;"> 경우, 
						Alarm전송
					</td>
				</tr>
				
			</table>
			<div class="button_area tcenter">
				<button class="button" id="alarmSetBtn">설정</button>
			</div>
			
		</div>
	</div>
	
	
</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/etc/etcInit.js"></script>