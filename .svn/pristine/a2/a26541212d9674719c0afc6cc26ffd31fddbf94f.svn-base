<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="customTag" uri="/WEB-INF/customTag.tld" %>
<!-- css 변환 -->
<link rel="stylesheet" type="text/css" href="/resources/css/app/app.css" />
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->

<section>
		<form action="/app/insertAppVrs" method="post" id="insertAppForm">
		<input type="hidden" id="gubun" name="gubun" value="${gubun}" />
		<input type="hidden" id="appSq" name="appSq" value="${appDet.APP_SQ}" />
		<div class="container">
			<div class="container_body">
				<ul class="tabmenu web">
					<li><a href="/app/appVrsList">버전 관리</a></li>
					<li><a href="/app/polyList">개인정보동의 관리</a></li>
					<li><a href="/app/tutorialList">튜토리얼 관리</a>
					<li><a href="/app/appUserStatusList">사용통계</a></li>
				</ul>
				<div class="tbar">
					<c:if test="${gubun eq 'new'}">
						<div class="w100 fl"><i class="axi axi-play-arrow"></i>버전 신규등록</div>
					</c:if>
					<c:if test="${gubun eq 'det'}">
						<div class="w100 fl"><i class="axi axi-play-arrow"></i>버전 상세</div>
					</c:if>
				</div>
				<div style="width:100%;height:300px;">
				<table class="basictables">
					<colgroup>
						<col width="20%"/>
						<col/>
						<col width="20%"/>
						<col/>
					</colgroup>
					<tbody>
						<tr>
							<th>관리자 ID</th>
							<td><input type="text" id="" name="" value="test@test.com1" disabled="disabled"/></td>
							<th>현재상태</th>
							<td>
								<select id="stat" name="stat">
									<c:forEach items="${appStat}" var="st">
										<c:if test="${st.CODE eq appDet.STAT}">
											<option value="${st.CODE}" selected="selected">${st.CODENAME}</option>
										</c:if>
										<c:if test="${st.CODE ne appDet.STAT}">
											<option value="${st.CODE}">${st.CODENAME}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>버전</th>
							<td><input type="text" id="appVrs" name="appVrs" value="${appDet.APP_VRS}"/></td>
							<th>등록 OS</th>
							<td>
								<select id="appOs" name="appOs">
									<c:if test="${appDet.APP_OS eq 'Android'}">
										<option value="Android" selected="selected">Android</option>
										<option value="IOS">IOS</option>
									</c:if>
									<c:if test="${appDet.APP_OS eq 'IOS'}">
										<option value="Android">Android</option>
										<option value="IOS" selected="selected">IOS</option>
									</c:if>
									<c:if test="${appDet.APP_OS eq null}">
										<option value="">선택</option>
										<option value="Android">Android</option>
										<option value="IOS">IOS</option>
									</c:if>
								</select>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td colspan="3"><textarea rows="10" cols="50" id="appVnm" name="appVnm">${appDet.APP_VNM}</textarea></td>
						</tr>
					</tbody>
				</table>
				</div>
			<button class="button" id="modApp" type="button">수정완료</button>
			<button class="button" id="addApp" type="button">신규추가</button>
			<button class="button" id="backAppList" type="button">목록으로</button>
			</div>
		</div>
		</form>
		<form action="/app/appVrsList" method="post" id="backForm">
			<input type="hidden" name="startDate" value="${dMap.startDate}"/>
			<input type="hidden" name="endDate" value="${dMap.endDate}"/>
			<input type="hidden" name="appOs" value="${dMap.appOs}"/>
			<input type="hidden" name="search" value="${dMap.search}"/>
			<input type="hidden" name="stat" value="${dMap.stat}"/>
			<input type="hidden" name="keyword" value="${dMap.keyword}"/>
		</form>
	</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/app/appVrsDet.js"></script>