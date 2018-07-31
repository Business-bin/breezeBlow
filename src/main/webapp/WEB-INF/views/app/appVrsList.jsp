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
			<form action="/app/appVrsDet" method="post" id="detForm">
				<input type="hidden" value="" id="gubun" name="gubun" />
				<input type="hidden" value="" id="appSq" name="appSq" />
			<table class="basictables mgt14">
				<colgroup>
					<col width="8%"/>
					<col width="38%"/>
					<col width="8%"/>
					<col width="8%"/>
					<col width="8%"/>
					<col width="30%"/>
				</colgroup>
				<tbody>
					<tr>
						<th class="mw60">등록일</th>
						<td class="mw420"><input type="text" id="startDate" class="" name="startDate" value="${pMap.startDate}"readonly="readonly"/> -
						<input type="text" id="endDate" class="" name="endDate" value="${pMap.endDate}"readonly="readonly"/>
						날짜제외 <input type="checkbox" id="nCal" /></td>
						<th class="mw60">OS</th>
						<td>
							<select id="appOs" name="appOs">
								<option value="">전체</option>
								<option value="Android" <c:if test="${pMap.appOs eq 'Android'}">selected="selected"</c:if>>Android</option>
								<option value="IOS" <c:if test="${pMap.appOs eq 'IOS'}">selected="selected"</c:if>>IOS</option>
							</select>
						</td>
						<th class="mw60">검색종류</th>
						<td colspan="3" class="mw250">
							<select id="search" name="search">
								<option value="vrsNm"<c:if test="${pMap.search eq 'vrsNm'}">selected="selected"</c:if>>버전명</option>
								<option value="stat" <c:if test="${pMap.search eq 'stat'}">selected="selected"</c:if>>현재상태</option>
							</select>
							<select id="stat" name="stat" disabled="disabled">
								<option value="">선택</option>
								<option value="01"<c:if test="${pMap.stat eq '01'}">selected="selected"</c:if>>등록중</option>
								<option value="02"<c:if test="${pMap.stat eq '02'}">selected="selected"</c:if>>등록대기중</option>
								<option value="03"<c:if test="${pMap.stat eq '03'}">selected="selected"</c:if>>삭제</option>
							</select>
							<input type="text" id="keyword" name="keyword" value="${pMap.keyword}" placeholder="검색어를 입력하세요"/>
							<div class="sbtn" id="appVrsSearch"><i class="axi axi-search3"></i></div>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>버전 목록</div>
				<div class="w50 ar fl" id="tcnt">총 0개</div>
			</div>
			<table id="appVrs_list"></table>
			<div id="pager"></div>
			<div class="button_area tright">
				<a href="#" class="button" id="addAppVrs">등록</a>
			</div>
		</div>
	</div>
</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/app/appVrsList.js"></script>