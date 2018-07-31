<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/sample/sample2.css">
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->
<section>
	<div class="container">
		<div class="container_body">
			<ul class="tabmenu web">
				<li class="active"><a href="/etc/popList">팝업목록</a></li>
				<li><a href="/etc/etcInit">환경설정</a></li>
			</ul>
			<form id="poupform" name="poupform" method="post" action="/pop/getPop" >
  			<input type="hidden" id="R_POP_SQ" name="R_POP_SQ" value="" />
			<table class="basictables mgt14">
		<!-- 	<colgroup>
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
			</colgroup> -->
			<tbody>
			<tr>
				<th>등록일</th>
				<td class="mw420">
					<input type="text" id="monthfrom" name="monthfrom" value="${pMap.monthfrom}" readonly="readonly"> ~ <input type="text" id="monthto" name="monthto" value="${pMap.monthto}" readonly="readonly">
					날짜제외 <input type="checkbox" id="nCal" />
				</td>
				<th>검색종류</th>
				<td >
					<select name="searchType" id= "searchType">
	                    <option value='001' <c:if test="${pMap.searchType eq '001'}">selected="selected"</c:if>>제목</option>
	                    <option value='002' <c:if test="${pMap.searchType eq '002'}">selected="selected"</c:if>>상태</option>
					</select>
					<input type="text" id="searStr" name="searStr" value="${pMap.searStr}" required="required">
					<div class="sbtn" id="sarchBtn"><i class="axi axi-search3"></i></div>
				</td>
			</tr>
			</tbody>
		</table>
		</form>
		<div class="tbar">
			<div class="w50 fl"><i class="axi axi-play-arrow"></i>팝업 목록</div>
			<div class="w50 ar fl" id="tcnt">총 0개</div>
		</div>
		<table id="pop_list"></table>
		<div id="pager"></div>
		<div class="button_area tright">
			<button class="button" id="newBtn"><i class="axi axi-person-add"></i>팝업등록</button>
		</div>
		</div>
	</div>
</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/etc/popList.js"></script>