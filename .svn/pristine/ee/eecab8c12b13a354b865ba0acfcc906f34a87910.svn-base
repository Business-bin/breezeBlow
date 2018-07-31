<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/resources/css/main/main.css">
<div id="o-wrapper">
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
	<!--  -->
	<section>
		<div class="container">
			<div class="container_body">
				<ul class="mainDashBoard">
					<li><div id="containers" style="width:100%; height: 300px; float:left" ></div></li>
					<li><div id="containers2" style="width:100%; height: 300px; float:right"></div></li>
					<li>
						<form action="/mem/memDet" method="post" id="detForm">
							<input type="hidden" id="gubun" name="gubun" value="" />
							<input type="hidden" id="memSq" name="memSq" value="" />
						</form>
						<div class="tbar">
							<div class="w50 fl"><i class="axi axi-play-arrow"></i>최근 가입 회원 목록</div>
							<div class="w50 ar fl"><a href="/mem/memList">더보기 ..</a></div>
						</div>
						<table id="mem_list"></table>
					</li>
					<li>
						<form name="frm" id="frm" method="post" action="">
							<input type="hidden" name="as_req_sq" id="as_req_sq" value=""/>
						</form>

						<div class="tbar">
							<div class="w50 fl"><i class="axi axi-play-arrow"></i>AS접수 목록</div>
							<div class="w50 ar fl"><a href="/board/asList">더보기 ..</a></div>
						</div>
						<table id="as_list"></table>
					</li>
					<li>
						<form name="bdForm" id="bdForm" method="post" action="">
							<input type="hidden" name="bd_sq" id="bd_sq" value=""/>
						</form>
						<div class="tbar">
							<div class="w50 fl"><i class="axi axi-play-arrow"></i>문의 접수  목록</div>
							<div class="w50 ar fl"><a href="/board/qnaList">더보기 ..</a></div>
						</div>
						<table id="qna_list"></table>
					</li>
				</ul>
			</div>
		</div>
	</section>
	<!--  -->
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<script src="/resources/js/main/main.js"></script>