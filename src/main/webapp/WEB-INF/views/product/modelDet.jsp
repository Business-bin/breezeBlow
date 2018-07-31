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
			<form action="/product/insertModel" method="post" id="insertModelForm" enctype="multipart/form-data">
				<input type="hidden" id="gubun" value="${gubun}" />
				<input type="hidden" id="stat" value="${modelDet.STAT}" />
				<input type="hidden" id="mdSq" name="mdSq" value="${modelDet.MD_SQ}" />
				<input type="hidden" id="delImageNm" name="delImageNm" value="${modelDet.CP_IMG_NM}" />
				<div class="tbar">
					<c:if test="${gubun eq 'det'}">
						<div class="w100 fl"><i class="axi axi-play-arrow"></i>모델 상세 정보</div>
					</c:if>
					<c:if test="${gubun eq 'new'}">
						<div class="w100 fl"><i class="axi axi-play-arrow"></i>모델 신규 등록</div>
					</c:if>
				</div>
				<div class="w100" style="height: 400px;">
					<table class="basictables mgt4 w100">
						<colgroup>
							<col width="14%" />
							<col width="13%" />
							<col width="30%" />
							<col width="13%" />
							<col width="15%" />
							<col width="15%" />
						</colgroup>
						<tbody>
							<tr>
								<td id="rs" rowspan="7" class="ac">
									<img src="${modelDet.CP_IMG_NM}" id="modelImg" />
									<label for="modelFile" id="fileBox" class="mgt10">사진업로드</label>
									<input type="file" id="modelFile" class="init" name="modelFile" />
								</td>
								<th>제품명</th>
								<td><input type="text" id="cpNm" class="w100" name="cpNm" value="${modelDet.CP_NM}" /></td>
								<th>모델명</th>
								<td colspan="2"><input type="text" id="mdNm" class="w100" name="mdNm" value="${modelDet.MD_NM}" /></td>
							</tr>
							<tr>
								<th>제품분류</th>
								<td>
									<div class="checks small">
										<c:choose>
											<c:when test="${modelDet.MINI_YN eq 'N'}">
												<input type="radio" id="mini1" name="miniYn" value="N" checked="checked"/><label for="mini1">마스터</label>
												<input type="radio" id="mini2" name="miniYn" value="Y"/><label for="mini2">미니</label>
											</c:when>
											<c:when test="${modelDet.MINI_YN eq 'Y'}">
												<input type="radio" id="mini1" name="miniYn" value="N"/><label for="mini1">마스터</label>
												<input type="radio" id="mini2" name="miniYn" value="Y" checked="checked"/><label for="mini2">미니</label>
											</c:when>
											<c:otherwise>
												<input type="radio" id="mini1" name="miniYn" value="N" checked="checked"/><label for="mini1">마스터</label>
												<input type="radio" id="mini2" name="miniYn" value="Y"/><label for="mini2">미니</label>
											</c:otherwise>
										</c:choose>
									</div>
								</td>
								<th>상세규격</th>
								<td colspan="2"><input type="text" id="detStd" class="w100" name="detStd" value="${modelDet.DET_STD}" /></td>
							</tr>
							<tr>
								<th>기기설명</th>
								<td colspan="4"><textarea rows="3" cols="20" id="des" class="" name="des">${modelDet.DES}</textarea></td>
							</tr>
							<tr>
								<th class="mw70">주요사용처</th>
								<td colspan="4"><textarea rows="3" cols="20" id="usg" class="" name="usg">${modelDet.USG}</textarea></td>
							</tr>
							<tr>
								<th>상세스펙</th>
								<td colspan="4"><textarea rows="3" cols="20" id="detSpec" class="" name="detSpec">${modelDet.DET_SPEC}</textarea></td>
							</tr>
							<tr>
								<th>판매상태</th>
								<td colspan="4" class="mw400 pdt12" >
									<div class="checks small">
										<input type="radio" id="stat1" class="" name="stat" value="01" />
										<label for="stat1" class="mgl10">판매중</label>
										<input type="radio" id="stat2" class="" name="stat" value="02" />
										<label for="stat2" class="mgl10">판매중지</label>
										<input type="radio" id="stat3" class="" name="stat" value="03" />
										<label for="stat3" class="mgl10">재고부족</label>
										<input type="radio" id="stat4" class="" name="stat" value="04" />
										<label for="stat4" class="mgl10">판매대기</label>
									</div>
								</td>
							</tr>
							<tr id="drsn">
								<th>삭제사유</th>
								<td colspan="4"><input type="text" id="delRsn" class="" name="" value="${modelDet.DEL_RSN}" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<form action="/product/modelList" method="post" id="backForm">
				<input type="hidden" name="startDate" value="${dMap.startDate}"/>
				<input type="hidden" name="endDate" value="${dMap.endDate}"/>
				<input type="hidden" name="keyword" value="${dMap.keyword}"/>
			</form>
			<div>
				<c:if test="${gubun eq 'new'}">
					<button class="button" onclick="fileupload('insertModelForm','/product/insertModel', '등록되었습니다.')">등록</button>
				</c:if>
				<c:if test="${gubun eq 'det'}">
					<button class="button" onclick="fileupload('insertModelForm','/product/updateModel', '수정되었습니다.')">수정완료</button>
					<button class="button" id="delModel">삭제</button>
				</c:if>
				<button class="button" id="backModelList">목록으로</button>
			</div>
		</div>
	</div>

	<div id="contents">
		  <div id="layerPopup">
		  	<table class="basictables">
					<tbody>
						<tr>
							<th>관리자ID</th>
							<td><input type="text" id="adminId" class="w100" name="" value="${adminId}"/></td>
						</tr>
						<tr>
							<th>제품명</th>
							<td><input type="text" id="" class="w100" name="" value="${modelDet.CP_NM}"/></td>
						</tr>
						<tr>
							<th>삭제사유</th>
							<td><textarea rows="5" cols="20" id="delRsn2" name="delRsn"></textarea></td>
						</tr>
						<tr>
							<td class="ac" colspan="2">
								<button class="button" id="del" type="button">삭제하기</button>
		  		  				<button class="button" id="close" type="button">닫기</button>
		  		  			</td>
						</tr>
					</tbody>
				</table>

		  </div>
		  </div> -->
	</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/product/modelDet.js"></script>