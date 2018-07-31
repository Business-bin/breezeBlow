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
	<form action="/app/insertTutorial" method="post" id="insertTutorialForm">
	<input type="hidden" id="gubun" name="gubun" value="${gubun}" />
	<input type="hidden" id="tutSq" name="tutSq" value="${tutorialDet.TUT_SQ}" />
	<div class="container">
		<div class="container_body">
			<ul class="tabmenu web">
				<li><a href="/app/appVrsList">버전 관리</a></li>
				<li><a href="/app/polyList">개인정보동의 관리</a></li>
				<li><a href="/app/tutorialList">튜토리얼 관리</a>
				<li><a href="/app/appUserStatusList">사용통계</a></li>
			</ul>
			<div style="width:100%;height:400px;">
			<div class="tbar">
				<c:if test="${gubun eq 'det'}">
					<div class="w100 fl"><i class="axi axi-play-arrow"></i>튜토리얼 상세</div>
				</c:if>
				<c:if test="${gubun eq 'new'}">
					<div class="w100 fl"><i class="axi axi-play-arrow"></i>튜토리얼 신규</div>
				</c:if>
			</div>
			<table class="basictables mgb4">
				<colgroup>
					<col width="20%"/>
					<col/>
				</colgroup>
				<tbody>
					<tr>
						<th>튜토리얼이름</th>
						<td>
							<input type="text" id="tutNm" name="tutNm" value="${tutorialDet.TUT_NM}"/>
						</td>
					</tr>
					<tr>
						<th>현재상태</th>
						<td>
							<c:if test="${tutorialDet.STAT eq '01'}">
								<input type="radio" id="stat1" name="stat" value="01" checked="checked"/> 노출
								<input type="radio" id="stat2" name="stat" value="02"/> 비노출
							</c:if>
							<c:if test="${tutorialDet.STAT eq '02'}">
								<input type="radio" id="stat1" name="stat" value="01"/> 노출
								<input type="radio" id="stat2" name="stat" value="02" checked="checked"/> 비노출
							</c:if>
							<c:if test="${gubun eq 'new'}">
								<input type="radio" id="stat1" name="stat" value="01" checked="checked"/> 노출
								<input type="radio" id="stat2" name="stat" value="02"/> 비노출
							</c:if>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea rows="10" cols="50" id="tutCont" name="tutCont">${tutorialDet.TUT_CONT}</textarea></td>
					</tr>
				</tbody>
			</table>
			<button class="button" id="modTut" type="button">수정완료</button>
			<button class="button" id="addTut" type="button">신규추가</button>
			<button class="button" id="delTut" type="button">삭제</button>
			<button class="button" id="backTutList" type="button">목록으로</button>
			</div>
		</div>
	</div>
	<div id="dim-layer"></div>
	<div id="layerPopup" class="pdt20">
		<table>
			<tbody>
				<tr>
					<td class="tcenter">신규 생성하시겠습니까?</td>
				</tr>
				<tr>
					<td class="tcenter pdt16">
						<button class="button" id="add" class="mgr10" type="button">생성하기</button>
						<button class="button" id="close" type="button">닫기</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="layerPopup2" class="pdt20">
		<table>
			<tbody>
				<tr>
					<td class="tcenter">삭제하시겠습니까?</td>
				</tr>
				<tr>
					<td class="tcenter pdt16">
						<button class="button" id="del" class="mgr10" type="button">삭제하기</button>
						<button class="button" id="close2" type="button">닫기</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</form>
	<form action="/app/tutorialList" method="post" id="backForm">
		<input type="hidden" name="startDate" value="${dMap.startDate}"/>
		<input type="hidden" name="endDate" value="${dMap.endDate}"/>
		<input type="hidden" name="search" value="${dMap.search}"/>
	</form>
</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/app/tutorialDet.js"></script>