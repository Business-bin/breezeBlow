<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/btb/btb.css">
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->
<section>
	<div class="container">
		<div class="container_body">
			<ul class="tabmenu web">
				<li class="active"><a href="/btb/btbList">B2B 사이트 관리</a></li>
				<li><a href="/btb/btbAdminList">B2B 관리자 관리</a></li>
			</ul>
            <form name="selectedShForm" id="selectedShForm" method="post" action="/btb/btbListProc">
            <input type="hidden" name="btbs_sq" id="btbs_sq" value=""/>
			<table class="basictables mgt14">
				<colgroup>
					<col width="15%" />
					<col width="35%" />
					<col width="15%" />
					<col width="35%" />
				</colgroup>
				<tbody>
					<tr>
						<th><span class="tcenter">등록일</span></th>
						<td class="mw420">
							<input type="text" name="start_dt" id="start_dt" value="${pMap.start_dt}" readonly="readonly"> ~
							<input type="text" name="end_dt" id="end_dt" value="${pMap.end_dt}" readonly="readonly">
							날짜제외 <input type="checkbox" id="nCal" />
						</td>
						<th><span class="tcenter">검색종류</span></th>
						<td>
							<select name="category" id="category" style="width:100px">
								<c:choose>
									<c:when test="${pMap.category eq 'comp_nm'}">
										<option value="comp_nm" selected="selected">회사명</option>
										<option value="btbs_nm">사이트명</option>
									</c:when>
									<c:when test="${pMap.category eq 'btbs_nm'}">
										<option value="comp_nm">회사명</option>
										<option value="btbs_nm" selected="selected">사이트명</option>
									</c:when>
									<c:otherwise>
										<option value="comp_nm">회사명</option>
										<option value="btbs_nm">사이트명</option>
									</c:otherwise>
								</c:choose>
							</select>
							<input type="text" name="keyword" id="keyword" value="${pMap.keyword}" style="width:240px"/>
							<div class="sbtn" id="btn_sch"><i class="axi axi-search3"></i></div>
						</td>
					</tr>
				</tbody>
			</table>
		    </form>
			<div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>B2B사이트 목록</div>
				<div class="w50 ar fl">총  <span id="totalCnt">0</span>개</div>
			</div>
			<table id="btb_list"></table>
			<div id="pager"></div>
			<div class="button_area tright">
				<a href="/btb/btbAdd" class="button" style="" >등록</a>
			</div>
		</div>
	</div>
</section>


<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/btb/btbList.js"></script>
<!-- jQuery 기본 js파일  -->
<!-- <script src="/resources/js/jquery-1.9.1.js"></script>    -->
<!-- jQuery UI 라이브러리 js파일  -->
<!-- <script src="/resources/js/jquery-ui.js"></script>   -->