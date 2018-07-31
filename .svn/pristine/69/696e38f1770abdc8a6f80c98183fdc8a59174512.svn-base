<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/sms/sms.css">
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->
	<section>
		<div class="container">
			<div class="container_body">
				
			<div class="pagetitle"><i class="axi axi-assignment-ind"></i> SMS </div>
			<div class="navi">
				<ul>
					<li>sms관리</li>
					<li>sms전송</li>
				</ul>
			</div>

			<table class="basictables mgt14">
				<colgroup>
					<col width="6%" />
					<col width="10%" />
					<col width="6%" />
					<col width="10%" />
					<col width="6%" />
					<col width="10%" />
					<col width="6%" />
					<col width="12%" />
					<col width="6%" />
					<col width="11%" />
					<col width="6%" />
					<col width="11%" />
				</colgroup>
				<tbody>
				<tr>
					<th class="mw70">지역선택</th>
					<td>
						<select class="w100 mw80"  name="sido" id= "sido" onchange="addrList('gugun',this.value)">
							<option value='000' selected="selected">전체</option>
						</select>
					</td>
					<th class="mw70">구군 선택</th>
					<td>
						<select class="w100 mw80"  name="gugun" id= "gugun" onchange="addrList('dong',this.value)">
							<option value='000' selected="selected">전체</option>
						</select>
					</td>
					<th style="width:150px" >읍면동 선택</th>
					<td>
						<select class="w100 mw80"  name="dong" id= "dong" onchange="getSendCnt()">
							<option value='000' selected="selected">전체</option>
						</select>
					</td>
					<th style="width:150px" >BTB 선택</th>
					<td>
						<input type="text" id="siteNm" class="" name="siteNm" value=""/>
						<input type="hidden" id="siteSq" class="" name="siteSq" value=""/>
						<div class="sbtn" id="btbSearch"><i class="axi axi-search3"></i></div>
						<!-- <select class="w100 mw80"  name="btbsnm" id= "btbsnm" onchange="getSendCnt()">
							<option value='000' selected="selected">전체</option>
						</select> -->
					</td>
				</tr>
				<tr>
					<th class="mw70" >기기 선택</th>
					<td>
						<select class="w100 mw80"  name="mtype" id= "mtype" onchange="getSendCnt()">
							<option value='000' >전체</option>
		                    <option value='IOS'>IOS</option>
		                    <option value='Android'>ANDROID</option>
						</select>
					</td>
					<th class="mw70" >상품 선택</th>
					<td>
					
						<input type="text" id="mdNm" class="" name="mdNm" value=""/>
						<input type="hidden" id="mdSq" class="" name="mdSq" value=""/>
						<div class="sbtn" id="mdSearch"><i class="axi axi-search3"></i></div>
						<!-- <select style="width:100%" name="prod" id= "prod">
							<option value='000' selected="selected">전체</option>
		                    <option value='001'>1상품</option>
		                    <option value='002'>2상품</option>
						</select> -->
					</td>
					<th class="mw70" >발송 타입 선택</th>
					<td>
						<div class="checks small">
							<input type="radio" value="0" id="smstype1" name="smstype" checked="checked">
							<label for="smstype1">실시간</label> 
							<input type="radio" value="1" id="smstype2" name="smstype" > 
							<label for="smstype2">예약발송</label> 
						</div>
					</td>
					<th class="mw70" >예약시간</th>
					<td>
						<input type="text" id="reservedate" readonly="readonly">
						<input type="text" id="reservetime" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th class="mw70" >sms type 선택</th>
					<td>
						<div class="checks small">
						<input type="radio" value="01" id="sendtype1" name="sendtype" checked="checked"> 
						<label for="sendtype1">단문  </label> 
						<input type="radio" value="02" id="sendtype2" name="sendtype" > 
							<label for="sendtype2">장문  </label> 
						</div>
					</td>
					<th style="width:150px" >송신 대상 건수</th>
					<td colspan="5">
						<input style="width:100%; font-weight: bold; color:red;" type="text" value="" id="sendCnt" name="sendCnt" readonly="readonly" >
					</td>
				</tr>
				<tr id="smsSubject">
					<th style="width:150px" >제목</th>
					<td colspan="7">
						<input style="width:100%" type="text" value="" id="subject" name="subject" placeholder="제목을 입력하세요">
					</td>
				</tr>
				<tr>
					<th style="width:150px" >내용</th>
					<td colspan="7">
						<textarea rows="20" cols="3" name="msg" id="msg" placeholder="내용을 입력하세요"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<span style="float: right;text-align: center;">
						<p class="limit">
							<span style="color: red"><span id="smsLength" ></span>자까지 입력가능합니다.</span>
							(<span id="textByte" >0</span> / <span id="smsLength2" ></span> byte)
						</p>
					</span>
					</td>
				</tr>
				</tbody>
			</table>
			<div class="button_area tcenter">
				<button class="button" id="smsListBtn"><i class="axi axi-person-add"></i>목록으로</button>
				<button class="button" id="smsSendBtn"><i class="axi axi-person-add"></i>전송</button>
			</div>
		</div>
	</div>
	<div id="dim-layer"></div>
	<div id="layerPopup2">
		<table class="basictables tcenter">
			<tbody>
				<tr>
					<th>B2B사이트명 검색</th>
				</tr>
				<tr>
					<td><input type="text" id="btbsNm" name="btbsNm" value="" />
						<button class="button" id="btbSearch2" type="button">검색</button>
						<button class="button" id="close" type="button">닫기</button></td>
				</tr>
				</tbody>
		</table>
		<table>
			<tbody>
				<tr>
					<td>
						<table id="btb_list"></table>
						<div id="pager2"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="layerPopup">
		<table class="basictables tcenter">
			<tbody>
				<tr>
					<th>삼품명 검색</th>
				</tr>
				<tr>
					<td><input type="text" id="smdNm" name="smdNm" value="" />
						<button class="button" id="mdSearch2" type="button">검색</button>
						<button class="button" id="close2" type="button">닫기</button></td>
				</tr>
				</tbody>
		</table>
		<table>
			<tbody>
				<tr>
					<td>
						<table id="md_list"></table>
						<div id="pager"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id="layerPopup3">
		<br>
		<span style="float: left;text-align: center; font-weight:600;">입력내용 확인</span>
		<br>
		<table class="basictables tcenter">
			<colgroup>
					<col width="20%" />
					<col width="80%" />
				</colgroup>
			<tbody>
				<tr>
					<th>발송지역</th>
					<td><input style="width:100%;" type="text" id="view1" value="" readonly="readonly">   </td>
				</tr>
				<tr>
					<th>B2B사이트</th>
					<td><input style="width:100%;" type="text" id="view2" value="" readonly="readonly">   </td>
				</tr>
				<tr>
					<th>기기</th>
					<td><input style="width:100%;" type="text" id="view3" value="" readonly="readonly">   </td>
				</tr>
				<tr>
					<th>제품명</th>
					<td><input style="width:100%;" type="text" id="view4" value="" readonly="readonly">   </td>
				</tr>
				<tr>
					<th>발송타입</th>
					<td><input style="width:100%;" type="text" id="view5" value="" readonly="readonly">   </td>
				</tr>
				<tr id="view6show">
					<th>예약시간</th>
					<td><input style="width:100%;" type="text" id="view6" value="" readonly="readonly">   </td>
				</tr>
				<tr>
					<th>sms 타입</th>
					<td><input style="width:100%;" type="text" id="view7" value="" readonly="readonly">   </td>
				</tr>
				<tr>
					<th>대상건수</th>
					<td><input style="width:100%;" type="text" id="view8" value="" readonly="readonly">   </td>
				</tr>
				<tr id="view9show">
					<th>제목</th>
					<td><input style="width:100%;" type="text" id="view9" value="" readonly="readonly">   </td>
				</tr>
				<tr id="view10show">
					<th>내용</th>
					<td><textarea style="resize: none;" rows="5" cols="4" id="view10" disabled="disabled"></textarea></td>
				</tr>
				</tbody>
		</table>
		<br>
		
		<table style="float: center;text-align: center;">
			<tbody>
				<tr>
					<td><span style="float: center;text-align: center; font-weight:600;">입력하신 내용으로 발송 하시겠습니까?</span></td>
				</tr>
				<tr>
					<td>
						<button class="button" id="sendSms" type="button">발송</button>
						<button class="button" id="close3" type="button">취소</button>
					</td>
				</tr>
				</tbody>
		</table>
		
		
	</div>
</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/sms/sms.js"></script>