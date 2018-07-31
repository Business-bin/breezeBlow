<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- css 변환 -->
<link rel="stylesheet" type="text/css" href="/resources/css/mem/mem.css" />
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start -->
<section>
	<div class="container">
		<div class="container_body">
			<ul class="tabmenu web">
				<li><a href="/mem/memList">회원 목록</a></li>
				<li><a href="/mem/memStat">회원 통계</a></li>
				<li><a href="/push/pushList">Push발송</a></li>
				<li><a href="/sms/smsList">SMS 발송</a></li>
			</ul>
			<form id="statForm" action="" method="post"></form>
			<table class="basictables mgt14">
				<colgroup>
					<col width="35%"/>
					<col width="20%"/>
					<col width="45%"/>
				</colgroup>
				<tbody>
					<tr>
						<td class="pdl20 mw350" colspan="2">
						<div class="checks small">
							<input type="radio" id="cls1" name="cls" checked="checked"/>
							<label for="cls1" class="mgl10">전체 </label>
							<input type="radio" id="cls2" name="cls"/>
							<label for="cls2" class="mgl50">연령별 </label>
							<input type="radio" id="cls3" name="cls"/>
							<label for="cls3" class="mgl50">지역별</label>
						</div></td>
						<th class="mw45">검색일</th>
						<td class="mw400 ac" colspan="3"><input type="text" id="startDate" class="" name="startDate" readonly="readonly"/> -
						<input type="text" id="endDate" class="" name="endDate" readonly="readonly"/><div class="sbtn mgl10" id="statSearch1"><i class="axi axi-search3"></i></div></td>
					</tr>
					<tr class="sel1 sel2">
						<th>성별</th>
						<td>
							<select id="memGen" class="init" name="memGen">
								<option value="">전체</option>
								<option value="M">남</option>
								<option value="F">여</option>
							</select>
						</td>
						<th class="mw45">회원별</th>
						<td>
							<select id="memKind" class="init" name="memKind">
								<option value="">전체</option>
								<option value="senko">SENKO회원</option>
								<option value="btb">B2B회원</option>
							</select>
						</td>
						<th class="sel1">제품정보</th>
						<td class="sel1 mw200"><input type="text" id="prodNm" class="init" name="prodNm" /><div class="sbtn mgl10" id="statSearch2"><i class="axi axi-search3"></i></div></td>
						<th class="sel2">거주지역</th>
						<td class="sel2">
							<select id="addr1" class="init" name="addr1">
								<option value="">전체</option>
								<c:forEach items="${sdList}" var="sd">
									<option value="${sd.CODE}">${sd.CODENAME}</option>
								</c:forEach>
							</select>
							<div class="sbtn mgl20" id="statSearch3"><i class="axi axi-search3"></i></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="container1" class="stat2" style="width: 600px; height: 350px; margin: 0 auto; float:left; margin-left:80px; z-index:-1;"></div>
	<div id="container2" class="stat1 stat2" style="width: 600px; height: 350px; margin: 0 auto; float:left; margin-left:30px; z-index:-1;"></div>
	<div id="container3" class="stat1 stat2" style="width: 600px; height: 350px; margin: 0 auto; float:left; margin-left:80px; margin-top:50px;"></div>
	<div id="container4" class="stat1" style="width: 600px; height: 350px; margin: 0 auto; float:left; margin-left:30px; margin-top:50px;"></div>
</section>
<!--  -->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<script src="/resources/js/mem/memStat.js"></script>