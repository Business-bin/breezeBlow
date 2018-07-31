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
			<form action="/product/productDet" method="post" id="detForm">
				<input type="hidden" value="" id="gubun" name="gubun" />
				<input type="hidden" value="" id="pprtMac" name="pprtMac" />
			<table class="basictables mgt14">
				<colgroup>
					<col width="10%"/>
					<col width="40%"/>
					<col width="10%"/>
					<col width="40%"/>
				</colgroup>
				<tbody>
					<tr>
						<th>등록일</th>
						<td class="mw420"><input type="text" id="startDate" class="" name="startDate" value="${pMap.startDate}" readonly="readonly"/> -
						<input type="text" id="endDate" class="" name="endDate" value="${pMap.endDate}" readonly="readonly"/>
						날짜제외 <input type="checkbox" id="nCal" /></td>
						<th>검색종류</th>
						<td>
							<select id="search" name="search">
								<c:forEach items="${sList}" var="s">
									<c:if test="${s.CODE eq pMap.search}">
										<option value="${s.CODE}" selected="selected">${s.CODENAME}</option>
									</c:if>
									<c:if test="${s.CODE ne pMap.search}">
										<option value="${s.CODE}">${s.CODENAME}</option>
									</c:if>
								</c:forEach>
								</select>
								<input type="text" id="keyWord" class="" name="keyWord" value="${pMap.keyWord}"/>
								<div class="sbtn" id="productSearch"><i class="axi axi-search3"></i></div>
							</td>
						</tr>
					</tbody>
				</table>
				</form>
				<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>제품 목록</div>
					<div id="tcnt" class="w50 ar fl">총 0개</div>
				</div>
				<table id="product_list"></table>
				<div id="pager"></div>
				<div class="button_area tright">
					<a href="#" class="button" id="addProd">제품 등록</a>
				</div>
			</div>
		</div>
	</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/product/productList.js"></script>