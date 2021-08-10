<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="customTag" uri="/WEB-INF/customTag.tld" %>
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
				<input type="hidden" id="pprtSq" name="pprtSq" value="${pprtSq}" />
				<input type="hidden" id="pprtMac" name="pprtMac" />
				<div class="tbar">
					<div class="w100 fl"><i class="axi axi-play-arrow"></i>등록 정보</div>
				</div>
				<div class="divw80" style="height: 400px;">
					<table class="basictables mgt4 tableW700">
					<!-- <table class="basictables"> -->
						<%-- <colgroup>
							<col width="14%" />
							<col width="13%" />
							<col width="30%" />
							<col width="13%" />
							<col width="30%" />
						</colgroup> --%>
						<tbody>
							<tr>
								<td colspan="4" align="right"><button type="button" id="addrBtn" class="button mgl10">주소 검색</button></td>
							</tr>
							<tr>
								<th>시/도</th>
								<td><input type="text" id="regAddr1" name="regAddr1" class="w100" readonly="readonly"/></td>
								<th>시/군/구</th>
								<td><input type="text" id="regAddr2" name="regAddr2" class="w100" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>동</th>
								<td><input type="text" id="regAddr3" name="regAddr3" class="w100" readonly="readonly"/></td>
								<th>지번</th>
								<td><input type="text" id="regAddr4" name="regAddr4" class="w100" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>상세주소</th>
								<td colspan="3"><input type="text" id="regAddr5" name="regAddr5" class="w100" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>설치장소</th>
								<td colspan="3">
									<select id="pprtAli" name="pprtAli">
										<option value="거실">거실</option>
										<option value="침실">침실</option>
										<option value="주방">주방</option>
										<option value="서재">서재</option>
										<option value="아이방">아이방</option>
										<option value="">기타 (작접입력)</option>
									</select>
									<input type="text" id="aliText" name="aliText" readonly="readonly"/>
								</td>
							</tr>
							<tr>
								<th>회원</th>
								<td colspan="3">
									<select name="memSq" id="memSq">
										<option value="">-선택-</option>
										<c:forEach items="${memList}" var="m">
											<option value="${m.MEM_SQ}">${m.MEM_EMAIL}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div>
				<button type="button" class="button" id="regMem" onclick="regProd()">등록</button>
				<button type="button" class="button" id="backProdList" onclick="goList()">목록</button>
			</div>
		</div>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<script src="/resources/js/product/regMem.js"></script>