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
	<div id="dim-layer"></div>
	<div class="container">
		<div class="container_body">
			<ul class="tabmenu web">
					<li><a href="/product/modelList">모델 관리</a></li>
					<li><a href="/product/productList">제품 관리</a></li>
					<li><a href="/product/breezeReport">Breeze 통계</a></li>
					<li><a href="/product/fwrList">펌웨어 관리</a></li>
				</ul>
			<form action="/product/insertFwr" method="post" id="insertFwrForm">
				<input type="hidden" id="gubun" value="${gubun}" />
				<input type="hidden" id="fwrMdSq" name="fwrMdSq" value="${fwrDet.FWR_MD_SQ}" />
				<input type="hidden" id="delFwrFile" name="delFwrFile" value="${fwrDet.FWR_FILE_NM}" />
				<div class="tbar">
					<c:if test="${gubun eq 'new'}">
						<div class="w100 fl"><i class="axi axi-play-arrow"></i>펌웨어 등록</div>
					</c:if>
					<c:if test="${gubun eq 'det'}">
						<div class="w100 fl"><i class="axi axi-play-arrow"></i>상세 정보</div>
					</c:if>
				</div>
				<div style="width: 100%; height: 320px;">
					<table class="basictables">
						<colgroup>
							<col width="15%" />
							<col width="35%" />
							<col width="15%" />
							<col width="35%" />
						</colgroup>
						<tbody>
							<tr id="det">
								<th>고유번호</th>
								<td><input type="text" id="fwrSq" class="w100" name="fwrSq" value="${fwrDet.FWR_SQ}" readonly="readonly"/></td>
								<th>등록일시</th>
								<td><input type="text" id="regDttm" class="w100" name="regDttm" value="${fwrDet.REG_DTTM}" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>버전명</th>
								<td><input type="text" id="fwrVrsNm" class="w100" name="fwrVrsNm" value="${fwrDet.FWR_VRS_NM}" /></td>
								<th>적용모델</th>
								<td>
									<input type="text" id="fwrMd" class="" name="fwrMd" value="${fwrDet.FWR_MD}" disabled="disabled"/>
									<button type="button" class="button" id="modSearch">모델 검색</button>
								</td>
							</tr>
							<tr>
								<th>적용 일시</th>
								<td>
									<input type="text" id="startDate" class="" name="startDate" value="${fwrDet.APL_DTTM1}" readonly="readonly"/>
									<select id="hour" name="hour">
										<c:forEach begin="01" end="24" step="1" var="h">
											<c:if test="${fwrDet.APL_DTTM2 eq h}">
												<option value="${h}" selected="selected">${h}</option>
											</c:if>
											<c:if test="${fwrDet.APL_DTTM2 ne h}">
												<option value="${h}">${h}</option>
											</c:if>
										</c:forEach>
									</select> 시
									<select id="min" name="min">
										<c:forEach begin="01" end="60" step="1" var="m">
											<c:if test="${fwrDet.REG_DTTM3 eq m}">
												<option value="${m}" selected="selected">${m}</option>
											</c:if>
											<c:if test="${fwrDet.REG_DTTM3 ne m}">
												<option value="${m}">${m}</option>
											</c:if>
										</c:forEach>
									</select> 분
								</td>
								<th>사용 여부</th>
								<td>
									<select id="stat" class="w100" name="stat">
										<c:choose>
											<c:when test="${fwrDet.STAT eq '01'}">
												<option value="01" selected="selected">사용</option>
												<option value="02">미사용</option>
												<option value="03">삭제</option>
											</c:when>
											<c:when test="${fwrDet.STAT eq '02'}">
												<option value="01">사용</option>
												<option value="02" selected="selected">미사용</option>
												<option value="03">삭제</option>
											</c:when>
											<c:when test="${fwrDet.STAT eq '03'}">
												<option value="01">사용</option>
												<option value="02">미사용</option>
												<option value="03" selected="selected">삭제</option>
											</c:when>
											<c:otherwise>
												<option value="">선택</option>
												<option value="01">사용</option>
												<option value="02">미사용</option>
												<option value="03">삭제</option>
											</c:otherwise>
										</c:choose>
									</select>
								</td>
							</tr>
							<tr>
								<th>버전 제목</th>
								<td colspan="3"><input type="text" id="fwrVrsNm1" class="w100" name="fwrVrsNm1" value="${fwrDet.FWR_VRS_NM_1}" /></td>
							</tr>
							<tr>
								<th>펌웨어 설명</th>
								<td colspan="3">
									<textarea id="fwrUptDes" name="fwrUptDes" rows="3" cols="100%">${fwrDet.FWR_UPT_DES}</textarea>
								</td>
							</tr>
							<tr>
								<th>펌웨어 파일</th>
								<td colspan="3">
									<input type="text" id="fwrFileNm" class="w50" name="fwrFileNm" value=""/>
									<input type="file" id="fwrFile" class="" name="fwrFile"  />
									<label for="fwrFile" id="fileBox" class="mgt10">찾아보기</label>
									<span class="mgl10">현재 파일 : <a href="${fwrDet.FWR_FILE_NM}">${fwrDet.FWR_FILE_NM_1}</a></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<form action="/product/fwrList" method="post" id="backForm">
				<input type="hidden" name="startDate" value="${dMap.startDate}"/>
				<input type="hidden" name="endDate" value="${dMap.endDate}"/>
				<input type="hidden" name="search" value="${dMap.search}"/>
				<input type="hidden" name="keyword" value="${dMap.keyword}"/>
			</form>
			<div>
				<c:if test="${gubun eq 'new'}">
					<button class="button" id="newFwr">등록</button>
				</c:if>
				<c:if test="${gubun eq 'det'}">
					<button class="button" id="modFwr">수정</button>
					<button class="button" id="delFwr">삭제</button>
				</c:if>
				<button class="button" id="backFwrList">목록</button>
			</div>
		</div>
	</div>
	<div id="layerPopup5">
		<table class="basictables tcenter">
		<tbody>
			<tr>
				<th>제품 모델 검색</th>
			</tr>
			<tr>
				<td>
					<input type="text" id="prodNm" value="" />
					<button class="button" id="modelSearch" type="button">검색</button>
					<button class="button" id="close" type="button">닫기</button>
				</td>
			</tr>
			</tbody>
		</table>
		<table>
			<tbody>
				<tr>
					<td>
						<table id="model_list"></table>
						<div id="pager"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="layerPopup6">
		<table class="basictables">
			<tbody>
				<tr>
					<th>관리자 ID</th><td><input type="text" id="" class="w100" name=""  value="${adminId}" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>버전명</th><td><input type="text" id="vnm" class="w100" name="" value="${fwrDet.FWR_VRS_NM}" readonly="readonly"/></td>
				</tr>
				<tr>
					<th colspan="2">삭제 사유</th>
				</tr>
				<tr>
					<td colspan="2">
						<textarea rows="2" cols="100%" id="delRsn" name="delRsn"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="ac">
						<button class="button" id="del" type="button">삭제</button>
						<button class="button" id="close2" type="button">취소</button>
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
<script src="/resources/js/product/fwrDet.js"></script>