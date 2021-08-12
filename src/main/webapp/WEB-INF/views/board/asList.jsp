<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/board/board.css">
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
                <form name="frm" id="frm" method="post" action="">
                <input type="hidden" name="as_req_sq" id="as_req_sq" value="${pMap.AS_REQ_SQ}"/>
                <input type="hidden" name="stat" id="stat" value="${pMap.stat}"/>
				<table class="basictables mgt14">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>
					<tbody>
					    <tr>
					    	<th><span class="tcenter">AS 상태</span></th>
					    	<td colspan="3">
								<button type="button" class="button btn" name="btn_sch1" id="btn_sch1" >전체</button>&nbsp;
								<button type="button" class="button btn" name="btn_sch2" id="btn_sch2" >AS 접수대기중</button>&nbsp;
								<button type="button" class="button btn" name="btn_sch3" id="btn_sch3" >AS 처리중</button>&nbsp;
								<button type="button" class="button btn" name="btn_sch4" id="btn_sch4" >AS 처리완료</button>&nbsp;
								<button type="button" class="button btn" name="btn_sch5" id="btn_sch5" >AS 배송중</button>&nbsp;
								<button type="button" class="button btn" name="btn_sch6" id="btn_sch6" >AS 배송완료</button>
							</td>
					    </tr>
						<tr>
							<th><span class="tcenter">등록일</span></th>
							<td class="mw420">
								<input type="text" name="start_dt" id="start_dt" value="${pMap.start_dt}" readonly="readonly"> ~
								<input type="text" name="end_dt" id="end_dt" value="${pMap.end_dt}" readonly="readonly">
								날짜제외 <input type="checkbox" id="nCal" />
							</td>
							<th><span class="tcenter">검색종류</span></th>
							<td class="mw350">
								<select name="category" id="category" style="width:100px">
								<c:choose>
									<c:when test="${pMap.category eq 'as_req_email'}">
										<option value="as_req_nm">이름</option>
										<option value="as_req_email" selected="selected">아이디</option>
										<option value="as_req_cp_nm">AS제품</option>
									</c:when>
									<c:when test="${pMap.category eq 'as_req_cp_nm'}">
										<option value="as_req_nm">이름</option>
										<option value="as_req_email">아이디</option>
										<option value="as_req_cp_nm" selected="selected">AS제품</option>
									</c:when>
									<c:otherwise>
										<option value="as_req_nm">이름</option>
										<option value="as_req_email">아이디</option>
										<option value="as_req_cp_nm">AS제품</option>
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
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>AS 목록 </div>
					<div class="w50 ar fl">총  <span id="totalCnt">0</span>개</div>
				</div>
 				<table id="as_list"></table>
				<div id="pager"></div>
				<div class="button_area tright">
					<a href="#" class="button" id="addAs">AS 등록</a>
				</div>
				<div class="button_area tright">
				<!-- 	<a href="/board/noticeAdd" class="button" style="" >신규생성</a>   -->
				</div>
			</div>
		</div>
	</section>


<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/board/asList.js"></script>
