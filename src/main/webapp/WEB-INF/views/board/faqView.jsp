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
					<li class="active"><a href="/board/faqList">FAQ 관리</a></li>
					<li><a href="/board/qnaList">Q&A 관리</a></li>
				</ul>
				<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>FAQ 상세 내용</div>
				</div>
				<form action="" id="frm" method="post">
				<input type="hidden" id="fileName" name="fileName">
				<input type="hidden" id="fileNewName" name="fileNewName">
				<input type="hidden" name="bd_tp_1" id="bd_tp_1" value="F"/>
				<input type="hidden" name="bd_sq" id="bd_sq" value="${rMap.faq.BD_SQ}"/>
				<input type="hidden" name="stat" id="stat" value="${rMap.faq.STAT}"/>
				<input type="hidden" name="bd_tp2" id="bd_tp2" value="${rMap.faq.BD_TP_2}"/>
				<table class="basictables">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>
					<tr>
						<th class="mw110">작성자 ID</th>
						<td>
							<input type="text" name="adminMail" id="adminMail" value="${rMap.faq.BD_USER_EMAIL}" style="width:300px;" disabled/>
						</td>
						<th class="mw110">등록일</th>
						<td>
							<input type="text" name="reg_dttm" id="reg_dttm" value="${rMap.faq.REG_DTTM1}" style="width:300px;" disabled/>
						</td>
					</tr>
					<tr>
						<th class="mw110">현재상태</th>
						<td colspan="3">
							<select name="bd_stat" id="bd_stat" style="width:100px">
							<c:forEach items="${statList}" var="stat">
								<option value="${stat.CODE}">${stat.CODENAME}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th class="mw110">카테고리</th>
						<td colspan="3">
							<select name="bd_tp_2" id="bd_tp_2" style="width:150px">
							<c:forEach items="${cateList}" var="cate">
								<option value="${cate.CODE}">${cate.CODENAME}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th class="mw110">제목</th>
						<td colspan="3">
							<input type="text" name="bd_nm" id="bd_nm" value="${rMap.faq.BD_NM}" style="width:100%" maxlength="200"/>
						</td>
					</tr>
					<tr>
						<th class="mw110">질문</th>
						<td colspan="3">
							<textarea rows="10" cols="30" name="bd_cont" id="bd_cont" style="width:100%" >${rMap.faq.BD_CONT}</textarea>
						</td>
					</tr>
					<tr>
						<th class="mw110">답변</th>
						<td colspan="3">
							<textarea rows="25" cols="30" name="bd_reply" id="bd_reply" style="width:100%" >${rMap.faq.BD_ANS}</textarea>
						</td>
					</tr>
				</table>
				</form>
				<form action="/board/faqList" method="post" id="backForm">
					<input type="hidden" name="start_dt" value="${dMap.start_dt}"/>
					<input type="hidden" name="end_dt" value="${dMap.end_dt}"/>
					<%-- <input type="hidden" name="bd_tp_2" value="${dMap.bd_tp_2}"/> --%>
					<input type="hidden" name="bd_tp_2" value=""/>
					<input type="hidden" name="category" value="${dMap.category}"/>
					<input type="hidden" name="keyword" value="${dMap.keyword}"/>
				</form>
				<div class="button_area tcenter">
					<button type="button" class="button" id="btn_mod">수정</button>
					<button type="button" class="button" id="btn_del">삭제</button>
					<button type="button" class="button" id="goList" >목록</button>
				</div>
			</div>
			<div id="dim-layer"></div>
			<div id="layerPopup">
				<table class="basictables">
					<tbody>
						<tr>
							<th>관리자ID</th>
							<td><input type="text" id="admEmail" name="admEmail" value="<%=adminMail %>" style="width:300px;" disabled/></td>
						</tr>
						<tr>
							<th>삭제 사유</th>
							<td><textarea rows="5" cols="20" id="comment" name="comment" placeholder="삭제 사유를 입력하세요" maxlength="100"></textarea></td>
						</tr>
					</tbody>
				</table>
				<div class="button_area tcenter" id="buttonDiv">
					<button class="button" id="delFaq" type="button">삭제</button>
					<button class="button" id="close" type="button">취소</button>
				</div>
			</div>
		</div>
	</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/board/faqView.js"></script>
