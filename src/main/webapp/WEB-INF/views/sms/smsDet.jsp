<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/sample/sample2.css">
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->
	<section>
		<div class="container">
			<div class="container_body">
				<ul class="tabmenu web">
				<li><a href="/mem/memList">회원 목록</a></li>
				<li><a href="/mem/memStat">회원 통계</a></li>
				<li><a href="#">Push발송</a></li>
				<li><a href="/sms/smsList">SMS 발송</a></li>
			</ul>
			<div class="pagetitle"><i class="axi axi-assignment-ind"></i> SMS 발송 상세</div>
			<div class="navi">
				<ul>
					<li>회원관리</li>
					<li>SMS발송</li>
					<li>SMS발송상세</li>
				</ul>
			</div>

			<table class="basictables">
					<tr>
						<th style="width:150px" >발송지역</th>
						<td>
							 <input style="width:100%;" type="text" value="${DET.AREA}" readonly="readonly">
						</td>
			<!-- 		</tr>
					<tr> -->
						<th style="width:150px" >회원 </th>
						<td>
							 <input style="width:100%;" type="text" value="${DET.BTBS_NM}" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th style="width:150px" >기기 </th>
						<td>
							<input style="width:100%;" type="text" value="${DET.MOBILE_TYPE}" readonly="readonly">
						</td>
						<th style="width:150px" >발송 타입</th>
						<td>
							<input style="width:100%;" type="text" value="${DET.SMS_TP}" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th style="width:150px" >예약시간</th>
						<td>
							<input type="text" style="width:100%;" id="reservedate" value="${DET.SMS_RES_DTTM}" readonly="readonly">

						</td>
						<th style="width:150px" >sms type 선택</th>
						<td>
							<input style="width:100%;" type="text" value="${DET.RESERVE_YN == 'Y' ? '예약' : '실시간'}" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th style="width:150px" >송신 대상 건수</th>
						<td >
							<input style="width:100%;" type="text" value="${DET.SMS_CNT}" readonly="readonly">
						</td>
						<th style="width:150px" >제목</th>
						<td colspan="7">
							<input style="width:100%;" type="text" value="${DET.SMS_SUBJECT}" readonly="readonly">
						</td>
					</tr>
					<tr>
						<th style="width:150px" >내용</th>
						<td colspan="7">
							<textarea rows="20" cols="4" id="msg" disabled="disabled">${DET.SMS_CONT}</textarea>
						</td>
					</tr>
					<tr>
						<th style="width:150px" >발송 일시</th>
						<td colspan="7">
							<input style="width:100%;" type="text" value="${DET.REG_DTTM}" readonly="readonly">
						</td>
					</tr>
				</table>
			<form action="/sms/smsList" method="post" id="backForm">
				<input type="hidden" name="sido" value="${dMap.sido}"/>
				<input type="hidden" name="gugun" value="${dMap.gugun}"/>
				<input type="hidden" name="dong" value="${dMap.dong}"/>
				<input type="hidden" name="siteNm" value="${dMap.siteNm}"/>
				<input type="hidden" name="siteSq" value="${dMap.siteSq}"/>
				<input type="hidden" name="mtype" value="${dMap.mtype}"/>
				<input type="hidden" name="mdNm" value="${dMap.mdNm}"/>
				<input type="hidden" name="mdSq" value="${dMap.mdSq}"/>
				<input type="hidden" name="monthto" value="${dMap.monthto}"/>
				<input type="hidden" name="monthfrom" value="${dMap.monthfrom}"/>
				<input type="hidden" name="searchType" value="${dMap.searchType}"/>
				<input type="hidden" name="searStr" value="${dMap.searStr}"/>
			</form>
			<div class="button_area tcenter">
				<button class="button" id="smsListBtn"><i class="axi axi-person-add"></i>목록으로</button>
				<!-- <button class="button" id="smsSendBtn"><i class="axi axi-person-add"></i>전송</button> -->
			</div>
		</div>
	</div>
</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/sms/smsDet.js"></script>