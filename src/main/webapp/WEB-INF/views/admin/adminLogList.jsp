<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/admin/admin.css">
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->

<section>
<form name="modifyForm" id="modifyForm" method="post" action="">
<input type="hidden" name="user_name" id="user_name"/>
</form>
		<div class="container">
			<div class="container_body">
				<ul class="tabmenu web">
					<li><a href="/admin/adminList">관리자 목록</a></li>
					<li class="active"><a href="/admin/adminLogList">관리자 활동로그</a></li>
				</ul>
                <form name="selectedShForm" id="selectedShForm" method="post" action="/admin/adminLogListProc">
                <input type="hidden" name="sch_flag" id="sch_flag" value="${dMap.sch_flag}"/>
                <input type="hidden" name="adm_sq" id="adm_sq" value=""/>
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
								<input type="text" name="start_dt" id="start_dt" value="" readonly="readonly"> ~
								<input type="text" name="end_dt" id="end_dt" value="" readonly="readonly">
								날짜제외 <input type="checkbox" id="nCal" />
							</td>
							<th><span class="tcenter">검색종류</span></th>
							<td>
								<select name="category" id="category" style="width:100px">
									<option value="email">아이디</option>
									<option value="user_name">관리자명</option>
								</select>
								<input type="text" name="keyword" id="keyword" value="" style="width:240px"/>
								<div class="sbtn" id="btn_sch"><i class="axi axi-search3"></i></div>
							</td>
						</tr>
					</tbody>
				</table>
			    </form>
			<div class="tbar">
				<div class="w50 fl"><i class="axi axi-play-arrow"></i>S관리자 활동로그 </div>
				<div class="w50 ar fl">총  <span id="totalCnt">0</span>개</div>
			</div>
			<table id="admin_log_list"></table>
			<div id="pager"></div>
			</div>
		</div>
	</section>


<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/admin/adminLogList.js"></script>
