<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- css 변환 -->
<link rel="stylesheet" type="text/css" href="/resources/css/mem/mem.css" />
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
				<li><a href="/push/pushList">Push발송</a></li>
				<li><a href="/sms/smsList">SMS 발송</a></li>
			</ul>
			<table class="basictables mgt14">
				<colgroup>
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
				</colgroup>
				<tbody>
					<tr>
						<th>회원명</th>
						<td>
							<input type="hidden" id="memSq" class="" name="memSq" value="${memData.MEM_SQ}" />
							<input type="hidden" id="memEmail" class="" name="memEmail" value="${memData.MEM_EMAIL}" />
							<input type="text" id="memData" name="memData" value="${memData.MEM_NM}(${memData.MEM_EMAIL})" size="30" disabled="disabled"/>
						</td>
						<th>검색일</th>
						<td class="mw350"><input type="text" id="startDate" class="" name="startDate" readonly="readonly"/>-
						<input type="text" id="endDate" class="" name="endDate" readonly="readonly"/>
						날짜제외 <input type="checkbox" id="nCal" />
						<div class="sbtn mgl10" id="logSearch"><i class="axi axi-search3"></i></div>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>활동 로그</div>
				<div class="w50 ar fl" id="tcnt">총 0개</div>
			</div>
			<table id="log_list"></table>
			<div id="pager"></div>
			<div class="button_area tright">
				<a href="#" class="button" id="detMem">회원상세정보</a>
			</div>
		</div>
	</div>
</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/mem/memLog.js"></script>