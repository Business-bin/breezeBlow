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
			<form action="/product/updateRegMem" method="post" id="updateRegForm">
				<input type="hidden" id="pprtSq" name="pprtSq" value="${pprtSq}" />
				<div class="tbar">
					<div class="w100 fl"><i class="axi axi-play-arrow"></i>등록 정보</div>
				</div>
				<div class="w100" style="height: 400px;">
					<table class="basictables mgt4 w100">
						<colgroup>
							<col width="14%" />
							<col width="13%" />
							<col width="30%" />
							<col width="13%" />
							<col width="30%" />
						</colgroup>
						<tbody>
							<tr>
								<th>우편번호</th>
								<td><input type="text" id="" class="w100" name="zip"/></td>
								<th>주소1</th>
								<td><input type="text" id="" class="w100" name="addr1"/></td>
							</tr>
							<tr>
								<th>주소2</th>
								<td><input type="text" id="miniYn" class="w100" name="addr2"/></td>
								<th>주소3</th>
								<td><input type="text" id="" class="w100" name="addr3"/></td>
							</tr>
							<tr>
								<th>주소4</th>
								<td><input type="text" id="" class="w100" name="addr4"/></td>
								<th>동경</th>
								<td><input type="text" id="" class="w100" name="pprtLocX"/></td>
							</tr>
							<tr>
								<th>북위</th>
								<td><input type="text" id="" class="w100" name="pprtLocY"/></td>
							</tr>
							<tr>
								<th>회원</th>
								<td>
									<select name="memSq">
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
				<button type="button" class="button" id="regMem">등록</button>
			</div>
		</div>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<script src="/resources/js/product/test.js"></script>