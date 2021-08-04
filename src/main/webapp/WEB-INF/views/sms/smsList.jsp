<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/sms/sms.css">
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->

<form id="smsform" name="smsform" method="post" action="/sms/smsDet">
  <input type="hidden" id= "R_SMS_SQ" name="R_SMS_SQ" value="" />
</form>
<section>
	<div class="container">
		<div class="container_body">
			<ul class="tabmenu web">
			<li><a href="/mem/memList">회원 목록</a></li>
			<li><a href="/mem/memStat">회원 통계</a></li>
			<li><a href="/push/pushList">Push발송</a></li>
			<li class="active"><a href="/sms/smsList">SMS 발송</a></li>
		</ul>
		<table class="basictables mgt14">
			<colgroup>
				<col width="6%" />
				<col width="10%" />
				<col width="6%" />
				<col width="10%" />
				<col width="6%" />
				<col width="10%" />
				<col width="6%" />
				<col width="12%" />
				<col width="6%" />
				<col width="11%" />
				<col width="6%" />
				<col width="11%" />
			</colgroup>
			<tbody>
			<tr>
				<th class="mw70">지역선택</th>
				<td>
					<input type="hidden" id="sd" value="${pMap.sido}"/>
					<select name="sido" id= "sido" class="w100 mw80" onchange="addrList('gugun',this.value)">
						<option value='000' selected="selected">전체</option>
					</select>
				</td>
				<th class="mw70">구군 선택</th>
				<td>
					<input type="hidden" id="gg" value="${pMap.gugun}"/>
					<select name="gugun" id= "gugun" class="w100 mw80" onchange="addrList('dong',this.value)">
						<option value='000' selected="selected">전체</option>
					</select>
				</td>
				<th class="mw70">동선택</th>
				<td>
					<input type="hidden" id="dg" value="${pMap.dong}"/>
					<select name="dong" id="dong" class="w100 mw80">
						<option value='000' selected="selected">전체</option>
					</select>
				</td>
				<th class="mw70">BTB 선택</th>
				<td class="mw190">
					<!-- <select name="btbsnm" id= "btbsnm" class="w100 mw80">
						<option value='000' selected="selected">전체</option>
					</select> -->
					<input type="text" id="siteNm" class="" name="siteNm" value="${pMap.siteNm}"/>
					<input type="hidden" id="siteSq" class="" name="siteSq" value="${pMap.siteSq}"/>
					<div class="sbtn" id="btbSearch"><i class="axi axi-search3"></i></div>
				</td>
				<th class="mw70">기기 선택</th>
				<td>
					<select name="mtype" id= "mtype" class="w100 mw80">
						<c:choose>
							<c:when test="${pMap.mtype eq 'IOS'}">
								<option value='000' >전체</option>
			                    <option value='IOS' selected="selected">IOS</option>
			                    <option value='ANDROID'>ANDROID</option>
							</c:when>
							<c:when test="${pMap.mtype eq 'ANDROID'}">
								<option value='000' >전체</option>
			                    <option value='IOS'>IOS</option>
			                    <option value='ANDROID' selected="selected">ANDROID</option>
							</c:when>
							<c:otherwise>
								<option value='000' >전체</option>
			                    <option value='IOS'>IOS</option>
			                    <option value='ANDROID'>ANDROID</option>
							</c:otherwise>
						</c:choose>
					</select>
				</td>
			</tr>
			<tr>
					<th class="mw70" >상품 선택</th>
					<td class="mw190">
						<input type="text" id="mdNm" class="" name="mdNm" value="${pMap.mdNm}"/>
						<input type="hidden" id="mdSq" class="" name="mdSq" value="${pMap.mdSq}"/>
						<div class="sbtn" id="mdSearch"><i class="axi axi-search3"></i></div>
						<!-- <select style="width:100%" name="prod" id= "prod">
							<option value='000' selected="selected">전체</option>
		                    <option value='001'>1상품</option>
		                    <option value='002'>2상품</option>
						</select> -->
					</td>

				<th>등록일</th>
				<td colspan="3" class="mw350">
					<input type="text" id="monthfrom"  value="${monthfrom}" readonly="readonly"> ~ 
					<input type="text" id="monthto" value="${monthto}" readonly="readonly">
					날짜제외 <input type="checkbox" id="nCal" />
				</td>
				<th>검색종류</th>
				<td colspan="7">
					<select name="searchType" id= "searchType">
	                    <c:choose>
		                	<c:when test="${pMap.searchType eq '001'}">
		                   		<option value='001' selected="selected">제목</option>
		                    	<option value='002'>내용</option>
							</c:when>
							<c:when test="${pMap.searchType eq '002'}">
		                   		<option value='001'>제목</option>
		                    	<option value='002' selected="selected">내용</option>
							</c:when>
							<c:otherwise>
								<option value='001'>제목</option>
		                    	<option value='002'>내용</option>
							</c:otherwise>
						</c:choose>
					</select>
					<input type="text" id="searStr" name="searStr" value="${pMap.searStr}">
					<div class="sbtn" id="sarchBtn"><i class="axi axi-search3"></i></div>
				</td>
			</tr>
			</tbody>
		</table>
		<div class="tbar">
			<div class="w50 fl"><i class="axi axi-play-arrow"></i>발송 목록</div>
			<div class="w50 ar fl" id="tcnt">총 0개</div>
		</div>
		<table id="sms_list"></table>
		<div id="pager"></div>
		<div class="button_area tright">
			<button class="button" id="newBtn"><i class="axi axi-person-add"></i>신규발송</button>
		</div>
		</div>
	</div>
	<div id="dim-layer"></div>
	<div id="layerPopup2">
		<table class="basictables tcenter">
			<tbody>
				<tr>
					<th>B2B사이트명 검색</th>
				</tr>
				<tr>
					<td><input type="text" id="btbsNm" name="btbsNm" value="" />
						<button class="button" id="btbSearch2" type="button">검색</button>
						<button class="button" id="close" type="button">닫기</button></td>
				</tr>
				</tbody>
		</table>
		<table>
			<tbody>
				<tr>
					<td>
						<table id="btb_list"></table>
						<div id="pager2"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="layerPopup">
		<table class="basictables tcenter">
			<tbody>
				<tr>
					<th>삼품명 검색</th>
				</tr>
				<tr>
					<td><input type="text" id="smdNm" name="smdNm" value="" />
						<button class="button" id="mdSearch2" type="button">검색</button>
						<button class="button" id="close2" type="button">닫기</button></td>
				</tr>
				</tbody>
		</table>
		<table>
			<tbody>
				<tr>
					<td>
						<table id="md_list"></table>
						<div id="pager"></div>
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
<script src="/resources/js/sms/smsList.js"></script>