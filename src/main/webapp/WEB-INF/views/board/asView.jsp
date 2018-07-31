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
				<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>AS 상세정보</div>
				</div>
				<form action="" id="frm" method="post" >
				<input type="hidden" id="stat" name="stat" value="${rMap.STAT}">
				<input type="hidden" id="agr_sms_yn" name="agr_sms_yn" value="${rMap.AGR_SMS_YN}">
				<input type="hidden" id="as_req_mem_sq" name="as_req_mem_sq" value="${rMap.AS_REQ_MEM_SQ}">
				<input type="hidden" id="as_req_sq" name="as_req_sq" value="${rMap.AS_REQ_SQ}">
				<input type="hidden" id="mem_email" name="mem_email" value="${rMap.MEM_EMAIL}">
				<div class="w100">
				<table class="basictables">
					<colgroup>
						<col width="15%" />
						<col width="15%" />
						<col width="30%" />
						<col width="15%" />
						<col width="25%" />
					</colgroup>
					<tbody>
					<tr>
						<td rowspan="3">
							<img id="prod_img" name="prod_img" src="${rMap.CP_IMG_NM}" style="width:200px;height:200px;"/>
						</td>
						<th class="mw110">접수번호</th>
						<td>
							<input type="text" name="req_sq" id="req_sq" value="${rMap.AS_REQ_SQ}" style="width:200px" maxlength="200" disabled/>
						</td>
						<th class="mw110">AS 등록일시</th>
						<td>
							<input type="text" name="reg_dttm" id="reg_dttm" value="${rMap.REG_DTTM1}" style="width:200px" maxlength="200" disabled/>
						</td>
					</tr>
					<tr>
						<th class="mw110">제품명</th>
						<td>
							<input type="text" name="as_req_cp_curd" id="as_req_cp_curd" value="${rMap.AS_REP_CP_NM}" style="width:200px" maxlength="200" disabled/>
						</td>
						<th class="mw110">제품 S/N</th>
						<td>
							<input type="text" name="as_req_cp_sn" id="as_req_cp_sn" value="${rMap.AS_REQ_CP_SN}" style="width:200px" maxlength="200" disabled/>
						</td>
					</tr>
					<tr>
						<th class="mw110">출고일시</th>
						<td>
							<input type="text" name="reg_dttm" id="reg_dttm" value="${rMap.REG_DTTM2}" style="width:200px" maxlength="200" disabled/>
						</td>
						<th class="mw110">AS 상태</th>
						<td>
							<input type="text" name="as_stat" id="as_stat" value="${rMap.STAT_NM}" style="width:200px" maxlength="200" disabled/>
						</td>
					</tr>
					<tr>
						<td colspan="5">
							<textarea rows="10" name="as_req_cont" id="as_req_cont"  style="width:100%" maxlength="5000" disabled>${rMap.AS_REQ_CONT}</textarea>
						</td>
					</tr>
					</tbody>
				</table>
				</div>
				<br>
				<table class="basictables">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>
					<tr>
						<th class="mw110">유저명</th>
						<td>
							<input type="text" name="as_req_nm" id="as_req_nm" value="${rMap.MEM_NM}" style="width:100%" maxlength="200" disabled/>
						</td>
						<th class="mw110">연락처</th>
						<td>
							<input type="text" name="user_hp" id="user_hp" value="${rMap.USER_HP}" style="width:200px" maxlength="200" disabled/>
						</td>
					</tr>
					<tr>
						<th class="mw110">성별</th>
						<td>
							<input type="text" name="mem_gen" id="mem_gen" value="${rMap.MEM_GEN}" style="width:200px" maxlength="200" disabled/>
						</td>
						<th class="mw110">주소</th>
						<td>
							<input type="text" name="mem_addr" id="mem_addr" value="${rMap.MEM_ADDR}" style="width:100%" maxlength="200" disabled/>
						</td>
					</tr>
				</table>
				<br>
				<table class="basictables">
					<tr id="stat_01">
						<td>
							<div class="asView">AS 접수 내용</div><div id="stat_01_date" class="asView1">접수일시: ${rMap.REP_CPL_DTTM1}</div>
							<textarea rows="5" name="rep_cpl_comment" id="rep_cpl_comment"  style="width:100%" maxlength="5000" >${rMap.REP_CPL_COMMENT}</textarea>
						</td>
					</tr>
					<tr id="stat_02">
						<td>
							<div class="asView">AS 처리 내용</div><div id="stat_02_date"class="asView1">처리일시: ${rMap.CPL_DTTM1}</div>
							<textarea rows="5" name="cpl_comment" id="cpl_comment"  style="width:100%" maxlength="5000" >${rMap.CPL_COMMENT}</textarea>
						</td>
					</tr>
					<tr id="stat_03">
						<td>
							<div class="asView">AS 배송 정보</div><div id="stat_03_date" class="asView1">배송일시: ${rMap.SHIP_DTTM1}</div>
							<textarea rows="5" name="ship_comment" id="ship_comment"  style="width:100%" maxlength="5000" >${rMap.SHIP_COMMENT}</textarea>
						</td>
					</tr>
					<tr id="stat_04">
						<td>
							<div class="asView">AS 배송 완료 정보</div><div id="stat_04_date" class="asView1">배송완료일시: ${rMap.SHIP_CPL_DTTM1}</div>
							<textarea rows="5" name="ship_clp_comment" id="ship_clp_comment"  style="width:100%" maxlength="5000" >${rMap.SHIP_CLP_COMMENT}</textarea>
						</td>
					</tr>
				</table>
				</form>
				<form action="/board/asList" method="post" id="backForm">
					<input type="hidden" name="start_dt" value="${dMap.start_dt}"/>
					<input type="hidden" name="end_dt" value="${dMap.end_dt}"/>
					<input type="hidden" name="stat" value="${dMap.stat}"/>
					<input type="hidden" name="category" value="${dMap.category}"/>
					<input type="hidden" name="keyword" value="${dMap.keyword}"/>
				</form>
				<div class="button_area tcenter">
					<button type="button" class="button" id="btn_mod1">AS 접수하시겠습니까?</button>
					<button type="button" class="button" id="btn_mod2">제품수리를 완료하였습니까?</button>
					<button type="button" class="button" id="btn_mod3">배송처리 하시겠습니까?</button>
					<button type="button" class="button" id="btn_mod4">AS 처리 완료하시겠습니까?</button>
					<button type="button" class="button" id="goList" >목록</button>
				</div>
			</div>
		</div>
	</section>

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/board/asView.js"></script>
