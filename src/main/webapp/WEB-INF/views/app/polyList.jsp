<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- css 변환 -->
<link rel="stylesheet" type="text/css" href="/resources/css/app/app.css" />
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->
<section>
	<div class="container">
		<div class="container_body">
			<ul class="tabmenu web">
				<li><a href="/app/appVrsList">버전 관리</a></li>
				<li><a href="/app/polyList">개인정보동의 관리</a></li>
				<li><a href="/app/tutorialList">튜토리얼 관리</a>
				<li><a href="/app/appUserStatusList">사용통계</a></li>
			</ul>
			<form action="/app/polyDet" method="post" id="detForm">
				<input type="hidden" value="" id="gubun" name="gubun" />
				<input type="hidden" value="" id="polySq" name="polySq" />
			<table class="basictables mgt14">
				<colgroup>
					<col width="15%"/>
					<col width="35%"/>
					<col width="15%"/>
					<col width="35%"/>
				</colgroup>
				<tbody>
					<tr>
						<th>등록일</th>
						<td class="mw420"><input type="text" id="startDate" class="" name="startDate" value="${pMap.startDate}" readonly="readonly"/> -
						<input type="text" id="endDate" class="" name="endDate" value="${pMap.endDate}" readonly="readonly"/>
						날짜제외 <input type="checkbox" id="nCal" /></td>
						<th>검색종류</th>
						<td colspan="3">
							<select id="search" name="search">
								<option value="">선택</option>
								<c:forEach items="${poly}" var="p">
									<c:if test="${pMap.search eq p.CODENAME}">
										<option value="${p.CODENAME}" selected="selected">${p.CODENAME}</option>
									</c:if>
									<c:if test="${pMap.search ne p.CODENAME}">
										<option value="${p.CODENAME}">${p.CODENAME}</option>
									</c:if>
								</c:forEach>
							</select>
							<div class="sbtn" id="polySearch"><i class="axi axi-search3"></i></div>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>약관 목록</div>
				<div class="w50 ar fl" id="tcnt">총 0개</div>
			</div>
			<table id="poly_list"></table>
			<div id="pager"></div>
			<div class="button_area tright">
				<a href="#" class="button" id="addPoly">신규생성</a>
			</div>
		</div>
	</div>
</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/app/polyList.js"></script>