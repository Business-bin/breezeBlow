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
			<form action="/product/insertProduct" method="post" id="insertProdForm" enctype="multipart/form-data">
				<input type="hidden" id="gubun" value="${gubun}" />
				<input type="hidden" id="pprtSq" value="${prodDet.PPRT_SQ}" />
				<div class="tbar">
					<div class="w100 fl"><i class="axi axi-play-arrow"></i>제품 상세 정보</div>
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
								<td rowspan="8" class="ac mw170">
									<img src="${prodDet.CP_IMG_NM}" id="prodImg" />
								</td>
								<th>제품명</th>
								<td><input type="text" id="" class="w100" name="cpNm" value="${prodDet.CP_NM}" disabled="disabled"/></td>
								<th>모델명</th>
								<td><input type="text" id="" class="w100" name="mdNm" value="${prodDet.MD_NM}" disabled="disabled"/></td>
							</tr>
							<tr>
								<th>제품분류</th>
								<td><input type="text" id="miniYn" class="w100" name="" value="${prodDet.MORM}" disabled="disabled"/></td>
								<th class="mw90">Serial Number</th>
								<td><input type="text" id="" class="w100" name="pprtMac1" id="pprtMac1" value="${prodDet.PPRT_MAC}" disabled="disabled"/></td>
							</tr>
							<tr>
								<th>등록회원 ID</th>
								<td colspan="3">
									<input type="text" id="memId" class="w50" name="memId" value="${prodDet.MEMID}" disabled="disabled"/>
									<input type="hidden" id="memSq" name="memSq" value="${prodDet.MEM_SQ}" />
									<button type="button" class="button" id="memDet">회원정보 상세보기</button>
								</td>
							</tr>
							<tr>
								<th>설정이름</th>
								<td><input type="text" id="" class="w100" name="pprtAli" value="${prodDet.PPRT_LOC}" disabled="disabled"/></td>
								<th>제품상태</th>
								<td><input type="text" id="" class="w100" name="" value="${prodDet.PRODSTAT}" disabled="disabled"/></td>
							</tr>
							<tr>
								<th>설치장소</th>
								<td><input type="text" id="" class="w100 mw400" name="pprtLoc" value="${prodDet.PPRT_ALI}" disabled="disabled"/></td>
								<th>출고 일시</th>
								<td><input type="text" id="" class="w100" name="retDttm" value="${prodDet.REL_DTTM}" disabled="disabled"/></td>
							</tr>
							<tr>
								<th class="mw90">고객 등록 일시</th>
								<td><input type="text" id="" class="w100" name="regDttm" value="${prodDet.USER_CP_REG_DTTM}" disabled="disabled"/></td>
								<th>사용중지 일시</th>
								<td><input type="text" id="" class="w100" name="stpDttm" value="${prodDet.PPRT_STOP_DTTM}" disabled="disabled"/></td>
							</tr>
							<tr>
								<th>기기등록<br>초기화사유</th>
								<td colspan="3">
									<textarea rows="2" cols="100%" name="initRsn" disabled="disabled">${prodDet.PPRT_INIT_RSN}</textarea>
								</td>
							</tr>
							<tr>
								<th>사용중지사유</th>
								<td colspan="3">
									<textarea rows="2" cols="100%" name="stpRsn" disabled="disabled">${prodDet.PPRT_STOP_RSN}</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<form action="/product/productList" method="post" id="backForm">
				<input type="hidden" name="startDate" value="${dMap.startDate}"/>
				<input type="hidden" name="endDate" value="${dMap.endDate}"/>
				<input type="hidden" name="search" value="${dMap.search}"/>
				<input type="hidden" name="keyWord" value="${dMap.keyWord}"/>
			</form>
			<div>
				<button type="button" class="button" id="initProduct">기기등록 초기화</button>
				<button type="button" class="button" id="stopProduct">기기사용 중지</button>
				<button type="button" class="button" id="regMac">매칭기기 등록</button>
				<button type="button" class="button" id="backProdList">목록</button>
				<button type="button" class="button" id="regMem" onclick="goRegMem()">사용자 기기 등록</button>
			</div>
		</div>
	</div>
	<div id="dim-layer"></div>
	<div id="layerPopup2">
		<table class="basictables tcenter">
			<tbody>
				<tr>
					<th>시리얼번호 검색</th>
				</tr>
				<tr>
					<td><input type="text" id="pprtMac" name="pprtMac" value="" />
						<button class="button" id="macSearch" type="button">검색</button>
						<button class="button" id="close" type="button">닫기</button></td>
				</tr>
			</tbody>
		</table>
		<table>
			<tbody>
				<tr>
					<td>
						<table id="mac_list"></table>
						<div id="pager"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="layerPopup3">
		<table class="basictables tcenter">
			<tbody>
				<tr>
					<th>관리자ID</th><td><input type="text" id="adminId" name="adminId" value="${adminId}" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>제품명</th><td><input type="text" id="" name="" value="${prodDet.CP_NM}" readonly="readonly"/></td>
				</tr>
				<tr>
					<th colspan="2">기기등록 초기화 사유</th>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="4" cols="100%" id="pprtInitRsn" name="pprtInitRsn"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="button" id="pprtInit" type="button">초기화</button>
						<button class="button" id="close2" type="button">닫기</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="layerPopup7">
		<table class="basictables tcenter">
			<tbody>
				<tr>
					<th>관리자ID</th><td><input type="text" id="adminId" name="adminId" value="${adminId}" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>제품명</th><td><input type="text" id="" name="" value="${prodDet.CP_NM}" readonly="readonly"/></td>
				</tr>
				<tr>
					<th colspan="2">기기사용 중지 사유</th>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="4" cols="100%" id="pprtStopRsn" name="pprtStopRsn"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="button" id="pprtStop" type="button">사용중지</button>
						<button class="button" id="close3" type="button">닫기</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<script src="/resources/js/product/productDet.js"></script>