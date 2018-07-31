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
	<form action="/mem/memDet" method="post" id="detForm">
		<input type="hidden" id="gubun" name="gubun" value="" />
		<input type="hidden" id="memSq" name="memSq" value="" />
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
					<col width="10%"/>
					<col width="40%"/>
					<col width="8%"/>
					<col width="22%"/>
					<col width="8%"/>
					<col width="12%"/>
				</colgroup>
				<tbody>
					<tr>
						<th class="mw80">B2B사이트명</th>
						<td>
							<input type="text" id="siteNm" class="" name="siteNm" value="${pMap.siteNm}"/>
							<input type="hidden" id="btbsSq" class="" name="btbsSq" value="${pMap.btbsSq}"/>
							<div class="sbtn" id="btbSearch"><i class="axi axi-search3"></i></div>
						</td>
						<th class="mw60">거주지역</th>
						<td>
							<select id="addr_1" name="addr_1">
								<option value="">전체</option>
								<c:forEach items="${sdList}" var="sd">
									<c:if test="${pMap.addr_1 eq sd.CODE}">
										<option value="${sd.CODE}" selected="selected">${sd.CODENAME}</option>
									</c:if>
									<c:if test="${pMap.addr_1 ne sd.CODE}">
										<option value="${sd.CODE}">${sd.CODENAME}</option>
									</c:if>
								</c:forEach>
							</select>
							<input type="hidden" id="add2" value="${pMap.addr_2}"/>
							<select id="addr_2" name="addr_2">
								<option value="">전체</option>
							</select>
							<input type="hidden" id="add3" value="${pMap.addr_3}"/>
							<select id="addr_3" name="addr_3">
								<option value="">전체</option>
							</select>
						</td>
						<th class="mw60">성별</th>
						<td>
							<select id="memGen" name="memGen">
								<c:choose>
									<c:when test="${pMap.memGen eq 'M'}">
										<option value="">전체</option>
										<option value="M" selected="selected">남</option>
										<option value="F">여</option>
									</c:when>
									<c:when test="${pMap.memGen eq 'F'}">
										<option value="">전체</option>
										<option value="M">남</option>
										<option value="F" selected="selected">여</option>
									</c:when>
									<c:otherwise>
										<option value="">전체</option>
										<option value="M">남</option>
										<option value="F">여</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
					</tr>
					<tr>
						<th class="mw60">등록일</th>
						<td class="mw420"><input type="text" id="startDate" class="" name="startDate" value="${pMap.startDate}" readonly="readonly"/> -
						<input type="text" id="endDate" class="" name="endDate" value="${pMap.endDate}" readonly="readonly"/>
						날짜제외 <input type="checkbox" id="nCal" /></td>
						<th class="mw60">검색종류</th>
						<td colspan="3">
							<select id="search" name="search">
								<option value="id">아이디</option>
								<option value="nm">이름</option>
							</select>
							<input type="text" id="keyWord" class="" name="keyWord" value="${pMap.keyWord}"/>
							<div class="sbtn" id="memSearch"><i class="axi axi-search3"></i></div>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>회원 목록</div>
				<div class="w50 ar fl" id="tcnt">총 0명</div>
			</div>
			<table id="mem_list"></table>
			<div id="pager"></div>
		</div>
	</div>
	</form>
	<div id="dim-layer"></div>
	<div id="layerPopup2">
		<table class="basictables tcenter">
			<tbody>
				<tr>
					<th>B2B사이트명 검색</th>
				</tr>
				<tr>
					<td><input type="text" id="btbsNm" name="btbsNm" value="" />
						<button class="button" id="btbSearch2" type="button">검색</button>
						<button class="button" id="close" type="button">닫기</button></td>
				</tr>
				</tbody>
		</table>
		<table>
			<tbody>
				<tr>
					<td>
						<table id="btb_list"></table>
						<div id="pager2"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/mem/memList.js"></script>