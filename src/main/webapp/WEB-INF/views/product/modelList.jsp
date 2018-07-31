<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- css 변환 -->
<link rel="stylesheet" type="text/css" href="/resources/css/product/product.css" />
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->

<section>
	<div class="container">
		<div class="container_body">
			<ul class="tabmenu web">
				<li><a href="/product/modelList">모델 관리</a></li>
				<li><a href="/product/productList">제품 관리</a></li>
				<li><a href="/product/breezeReport">Breeze 통계</a></li>
				<li><a href="/product/fwrList">펌웨어 관리</a></li>
			</ul>
			<form action="/product/modelDet" method="post" id="detForm">
				<input type="hidden" value="" id="gubun" name="gubun" />
				<input type="hidden" value="" id="mdSq" name="mdSq" />
			<table class="basictables mgt14">
				<colgroup>
					<col width="12%"/>
					<col width="30%"/>
					<col width="12%"/>
					<col width="17%"/>
				</colgroup>
				<tbody>
					<tr>
						<th class="mw50">등록일</th>
						<td class="mw420"><input type="text" id="startDate" class="" name="startDate" value="${pMap.startDate}" readonly="readonly"/> -
						<input type="text" id="endDate" class="" name="endDate" value="${pMap.endDate}" readonly="readonly"/>
						날짜제외 <input type="checkbox" id="nCal" /></td>
						<th class="mw50">제품/모델명</th>
						<td class="ac mw300">
							<!-- <select id="search" name="search">
								<option value="">전체</option>
								<option value="cp">제품명</option>
								<option value="md">모델명</option>
							</select> -->
							<input type="text" id="keyword" class="" name="keyword" value="${pMap.keyword}"/>
							<div class="sbtn" id="modelSearch"><i class="axi axi-search3"></i></div>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			<div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>모델 목록</div>
				<div class="w50 ar fl" id="tcnt">총 0개</div>
			</div>
			<table id="model_list"></table>
			<div id="pager"></div>
			<div class="button_area tright">
				<a href="#" class="button" id="addModel">신규추가</a>
			</div>
		</div>
	</div>
</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/product/modelList.js"></script>