<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/board/board.css">
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->
<form name="frm" id="frm" method="post">
<input type="hidden" name="bd_tp_1" id="bd_tp_1" value="Q"/>
<input type="hidden" name="bd_sq" id="bd_sq" value="${rMap.BD_SQ}"/>
<input type="hidden" name="bd_ans_yn" id="bd_ans_yn" value="${rMap.BD_ANS_YN2}"/>
</form>
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
				<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>Q&A 상세 내용</div>
				</div>
				<table class="basictables">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>
					<tr>
						<th class="mw110">문의자</th>
						<td>
							<input type="text" name="bd_user_nm" id="bd_user_nm" value="${rMap.BD_USER_NM}" style="width:300px;" disabled/>
						</td>
						<th class="mw110">문의자 ID</th>
						<td>
							<input type="text" name="bd_user_email" id="bd_user_email" value="${rMap.BD_USER_EMAIL}" style="width:300px;" disabled/>
						</td>
					</tr>
					<tr>
						<th class="mw110">카테고리</th>
						<td>
							<input type="text" name="s_category" id="s_category" value="${rMap.CATEGORY}" style="width:300px;" disabled/>
						</td>
						<th class="mw110">작성환경</th>
						<td>
							<input type="text" name="bd_os" id="bd_os" value="${rMap.BD_OS}" style="width:300px;" disabled/>
						</td>
					</tr>
					<tr>
						<th class="mw110">문의일시</th>
						<td>
							<input type="text" name="reg_dttm" id="reg_dttm" value="${rMap.REG_DTTM1}" style="width:300px;" disabled/>
						</td>
						<th class="mw110">답변일시</th>
						<td>
							<input type="text" name="reg_dttm" id="reg_dttm" value="${rMap.BD_ANS_REG_DTTM1}" style="width:300px;" disabled/>
						</td>
					</tr>
					<tr>
						<th class="mw110"><span class="tcenter">답변상태</span></th>
						<td colspan="3">
							<select name="reply_yn" id="reply_yn" style="width:100px" disabled>
								<option value="N">답변대기</option>
								<option value="Y">답변완료</option>
							</select>
						</td>
					</tr>
					<tr>
						<th class="mw110">제목</th>
						<td colspan="3">
							<input type="text" name="bd_nm" id="bd_nm" value="${rMap.BD_NM}" style="width:100%;" maxlength="200" disabled/>
						</td>
					</tr>
					<tr>
						<th class="mw110">내용</th>
						<td colspan="3">
							<textarea rows="10" cols="30" name="bd_cont" id="bd_cont"  style="width:100%;" disabled>${rMap.BD_CONT}</textarea>
						</td>
					</tr>
					<%--
					<c:forEach items="${dList}" var="qna">
					<tr>
						<th>댓글</th>
						<td>
							<textarea rows="6" name="mod_comment" id="mod_comment${qna.BD_DET_SQ}"  style="width:100%" maxlength="5000">${qna.BD_DET_CONT}</textarea>
						<!--
						    <a href="javascript:modComment('${qna.BD_DET_SQ}');" >&nbsp;&nbsp;수정</a>
							<a href="javascript:delComment('${qna.BD_DET_SQ}');" >&nbsp;&nbsp;삭제</a><br>
						-->
							<button type="button" class="button" id="" onclick="javascript:modComment('${qna.BD_DET_SQ}');"><i class="axi axi-person-add"></i>수정</button>
							<button type="button" class="button" id="" onclick="javascript:delComment('${qna.BD_DET_SQ}');"><i class="axi axi-delete2"></i>삭제</button>
						</td>
					</tr>
					</c:forEach>
					 --%>
					<tr>
						<th class="mw110">답변</th>
						<td colspan="3">
							<textarea rows="25" cols="30" name="bd_reply" id="bd_reply" style="width:100%" >${rMap.BD_ANS}</textarea>
						</td>
					</tr>
				</table>
				<form action="/board/qnaList" method="post" id="backForm">
					<input type="hidden" name="start_dt" value="${dMap.start_dt}"/>
					<input type="hidden" name="end_dt" value="${dMap.end_dt}"/>
					<input type="hidden" name="reply_yn" value="${dMap.reply_yn}"/>
					<input type="hidden" name="bd_tp_2" value="${dMap.bd_tp_2}"/>
					<input type="hidden" name="category" value="${dMap.category}"/>
					<input type="hidden" name="keyword" value="${dMap.keyword}"/>
				</form>
				<div class="button_area tcenter">
					<button type="button" class="button" id="btn_mod">답변등록</button>
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
							<td><input type="text" id="admEmail" name="admEmail" value="<%=adminMail %>" /></td>
						</tr>
						<tr>
							<th>삭제 사유</th>
							<td><textarea rows="5" cols="20" id="comment" name="comment" placeholder="삭제 사유를 입력하세요" maxlength="100"></textarea></td>
						</tr>
					</tbody>
				</table>
				<div class="button_area tcenter" id="buttonDiv">
					<button class="button" id="delQna" type="button">삭제</button>
					<button class="button" id="close" type="button">취소</button>
				</div>
			</div>
		</div>
	</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/board/qnaView.js"></script>
