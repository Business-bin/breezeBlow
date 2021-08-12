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
				<li class="active"><a href="/board/asList">AS 관리</a></li>
				<li><a href="/board/noticeList">공지사항 관리</a></li>
				<li><a href="/board/faqList">FAQ 관리</a></li>
				<li><a href="/board/qnaList">Q&A 관리</a></li>
			</ul>
			<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>AS 등록</div>
			</div>
			<form action="/board/addAs" method="post" id="insertAsForm" >
				<input type="hidden" id="duCheck" value="N"/>
				<div id="one" class="w100" style="height: 210px;">
					<table class="basictables mgt14 w100">
						<%-- <colgroup>
							<col width="30%" />
							<col width="70%" />
						</colgroup> --%>
						<tbody>
							<tr>
								<th>접수 신청 고객</th>
								<td>
									<select name="memSq" id="memSq"  width="150px" onchange="getModel()">
										<option value="">-선택-</option>
										<c:forEach items="${memList}" var="m">
											<option value="${m.MEM_SQ}">${m.MEM_EMAIL}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th>접수 제품</th>
								<td>
									<select name="mdSq" id="mdSq" >
										<option value="">-선택-</option>
										<%-- <c:forEach items="${modelList}" var="m">
											<option value="${m.PPRT_MAC}">${m.CP_NM}</option>
										</c:forEach> --%>
									</select>
								</td>
							</tr>
							<tr>
								<th>접수 AS 내용</th>
								<td>
									<textarea rows="3" cols="20" id="content" class="" name="content"></textarea>
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</form>
			<div id="layerPopup">
				<table class="basictables">
					<tbody>
						<tr>
							<th>보유제품 등록</th>
						</tr>
						<tr>
							<td>
								<b>시리얼 번호 검색</b><br><br>
								<input type="text" id="regMac" name="regMac" placeholder="입력 예)aa:bb:cc:dd:ee"/>
								<button class="button mgr10" onclick="searchProd()" type="button">검색</button>
								선택 : <input type="text" class="mgl10" id="regMac2" name="regMac2" />
							</td>
						</tr>
						<tr>
							<td>
								<b>검색결과</b><br>
								<table id="mac_list" class="mgt4"></table>
								<div id="pager2"></div>
							</td>
						</tr>
						
						<tr>
							<td class="ac">
								<button class="button" type="button" onclick="regProd()">등록하기</button>
								<button class="button" id="closePop" type="button" onclick="closePop()">닫기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div>
				<button class="button" type="button" id="newProd" onclick="addAsNew()">AS 등록</button>
				<button class="button" id="asList" type="button" onclick="goAsList()">목록</button>
			</div>
		</div>
	</div>
	
</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/board/addAsNew.js"></script>