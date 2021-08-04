<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="/resources/css/product/product.css">	
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!--  -->
<section>
	<div class="container">
		<div class="container_body">
			<ul class="tabmenu web">
				<li><a href="/product/modelList">모델 관리</a></li>
				<li><a href="/product/productList">전체 제품 관리</a></li>
				<li class="active"><a href="/product/breezeReport">Breeze 통계</a></li>
				<li><a href="/product/fwrList">펌웨어 관리</a></li>
			</ul>		
			<table class="basictables mgt14">
				<colgroup>
					<col width="10%"/>
					<col width="26%"/>
					<col width="10%"/>
					<col width="22%"/>
					<col width="10%"/>
					<col width="22%"/>
				</colgroup>
				<tr>
					<td colspan="6">
					<div class="checks small">
						<input type="radio" id="gubun1" name="gubun" value="01" checked="checked"/><label for="gubun1" class="mw100">전체 </label>
						<input type="radio" id="gubun2" name="gubun" value="02" /><label for="gubun2" class="mw100">지역별 </label> 
						<input type="radio" id="gubun3" name="gubun" value="03" /><label for="gubun3" class="mw100">연령별 </label> 
						<input type="radio" id="gubun4" name="gubun" value="04" /><label for="gubun4" class="mw100">회원별 </label> 
					</div>
					</td>
				</tr>
				<tr>
					<th><span class="tcenter">검색일</span></th>
					<td>
						<input type="text" name="start_dt" id="start_dt" value="" readonly="readonly"> ~
						<input type="text" name="end_dt" id="end_dt" value="" readonly="readonly">
						날짜제외 <input type="checkbox" id="nCal" />
					</td>			
					<th>검색단위</th>
					<td>
						<select name="category" id="category" style="width:200px">
							<option value="hour">시간</option>
							<option value="day">일</option>
							<option value="month">월</option>
						</select>
					</td>
					<th>검색수치</th>
					<td>
						<select name="category2" id="category2" style="width:200px">
							<option value="average">평균</option>
							<option value="min">최소</option>
							<option value="max">최대</option>
						</select>
						<div class="sbtn" id="btn_sch" ><i class="axi axi-search3"></i></div>
					</td>		
				</tr>
				<tr id="placeTr">
					<th>지역별</th>
					<td>
						<select name="addr_si" id="addr_si" style="width:200px">
						<c:forEach items="${siList}" var="si">
							<option value="${si.CODE}">${si.CODENAME}</option>
						</c:forEach>
						</select>
					</td>
					<th>센서종류</th>
					<td colspan="3">
						<select name="sensor_type" id="sensor_type" style="width:200px">
						<c:forEach items="${sensorTypeList}" var="sensorType">
							<option value="${sensorType.CODE}">${sensorType.CODENAME}</option>
						</c:forEach>
						</select>
						<div class="sbtn" id="btn_sch2"><i class="axi axi-search3"></i></div>
					</td>		
				</tr>	
				<tr id="ageTr">
					<th>연령대</th>
					<td>
						<select name="age_type" id="age_type" style="width:200px">
						<c:forEach items="${ageList}" var="age">
							<option value="${age.CODE}">${age.CODENAME}</option>
						</c:forEach>
						</select>
					</td>
					<th>센서종류</th>
					<td colspan="3">
						<select name="sensor_type2" id="sensor_type2" style="width:200px">
						<c:forEach items="${sensorTypeList}" var="sensorType">
							<option value="${sensorType.CODE}">${sensorType.CODENAME}</option>
						</c:forEach>
						</select>
						<div class="sbtn" id="btn_sch3"><i class="axi axi-search3"></i></div>
					</td>		
				</tr>				
				<tr id="memTr">
					<th>B2B명</th>
					<td colspan="5">
						<select name="btbs_sq" id="btbs_sq" style="width:200px">
						<c:forEach items="${btbList}" var="btb">
							<option value="${btb.BTBS_SQ}">${btb.BTBS_SITE_NM}</option>
						</c:forEach>
						</select>
						<div class="sbtn" id="btn_sch4"><i class="axi axi-search3"></i></div>
					</td>		
				</tr>		
			</table>
		</div>
		<div class="container">	 
			<div class="container_body">
				<div id="containers" style="min-width: 310px; height: 400px; margin: 50 auto"></div>
			</div>
		</div>
	</div>
</section>
	 
	 
<!--  -->	 
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<script src="/resources/js/product/breezeReport.js"></script>