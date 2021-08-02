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
					<%if(s_adm_class.equals("1") || s_adm_class.equals("2")){ %>
					<li><a href="/board/asList">AS 관리</a></li>
					<%} %>
					<li><a href="/board/noticeList">공지사항 관리</a></li>
					<li><a href="/board/faqList">FAQ 관리</a></li>
					<li class="active"><a href="/board/qnaList">Q&A 관리</a></li>
				</ul>
                <form name="selectedShForm" id="selectedShForm" method="post" action="">
                <%-- <input type="hidden" name="sch_flag" id="sch_flag" value="${dMap.sch_flag}"/>
                <input type="hidden" name="page_cnt" id="page_cnt" value="10"/>
                <input type="hidden" name="page" id="page" value="${dMap.page}"/>
                <input type="hidden" name="total_page" id="total_page" value="${dMap.total_page}"/>
                <input type="hidden" name="category1" id="category1" value="${dMap.category}"/> --%>
                <input type="hidden" name="bd_sq" id="bd_sq" value=""/>
				<table class="basictables mgt14">
					<colgroup>
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
						<col width="15%" />
						<col width="" />
						<col width="" />
					</colgroup>
					<tbody>
						<tr>
							<th><span class="tcenter">등록일</span></th>
							<td colspan="6">
								<input type="text" name="start_dt" id="start_dt" value="${pMap.start_dt}" readonly="readonly"> ~
								<input type="text" name="end_dt" id="end_dt" value="${pMap.end_dt}" readonly="readonly">
								날짜제외 <input type="checkbox" id="nCal" />
							</td>
						</tr>
						<tr>
							<th><span class="tcenter">카테고리</span></th>
							<td>
								<select name="bd_tp_2" id="bd_tp_2" style="width:100px">
									<option value="">전체</option>
									<c:forEach items="${cateList}" var="cate">
										<option value="${cate.CODE}" <c:if test="${pMap.bd_tp_2 eq cate.CODE}">selected="selected"</c:if>>${cate.CODENAME}</option>
									</c:forEach>
								</select>
							</td>
							<th><span class="tcenter">답변상태</span></th>
							<td>
								<select name="reply_yn" id="reply_yn" style="width:100px">
									<c:choose>
										<c:when test="${pMap.reply_yn eq 'N'}">
											<option value="">전체</option>
											<option value="N" selected="selected">답변대기</option>
											<option value="Y">답변완료</option>
										</c:when>
										<c:when test="${pMap.reply_yn eq 'Y'}">
											<option value="">전체</option>
											<option value="N">답변대기</option>
											<option value="Y" selected="selected">답변완료</option>
										</c:when>
										<c:otherwise>
											<option value="">전체</option>
											<option value="N">답변대기</option>
											<option value="Y">답변완료</option>
										</c:otherwise>
									</c:choose>

								</select>
							</td>
							<th><span class="tcenter">검색종류</span></th>
							<td class="mw350">
								<select name="category" id="category" style="width:100px">
									<c:choose>
										<c:when test="${pMap.category eq 'bd_user_nm'}">
											<option value="bd_nm">제목</option>
											<option value="bd_user_nm" selected="selected">문의자</option>
										</c:when>
										<c:otherwise>
											<option value="bd_nm">제목</option>
											<option value="bd_user_nm">문의자</option>
										</c:otherwise>
									</c:choose>

								</select>
								<input type="text" name="keyword" id="keyword" value="${pMap.keyword}" style="width:180px"/>
								<div class="sbtn" id="btn_sch"><i class="axi axi-search3"></i></div>
							</td>
						</tr>
					</tbody>
				</table>
			    </form>
				<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>Q&A 목록 </div>
					<div class="w50 ar fl">총  <span id="totalCnt">0</span>개</div>
				</div>
 				<table id="qna_list"></table>
				<div id="pager"></div>
				<div class="button_area tright">
				</div>
			</div>
		</div>
	</section>


<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/board/qnaList.js"></script>
