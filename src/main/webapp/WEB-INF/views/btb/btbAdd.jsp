<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/btb/btb.css">	
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->

<section>
		<div class="container">
			<div class="container_body">
				<ul class="tabmenu web">
					<li class="active"><a href="/btb/btbList">B2B 사이트 관리</a></li>
					<li><a href="/btb/btbAdminList">B2B 관리자 관리</a></li>
				</ul>	
				<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>B2B사이트 신규등록 </div>
				</div>		
				<table class="basictables">
				<input type="hidden" name="siNm" id="siNm"/>
				<input type="hidden" name="sggNm" id="sggNm"/>
				<input type="hidden" name="emdNm" id="emdNm"/>
				<input type="hidden" name="check_yn" id="check_yn"/>
				<input type="hidden" name="check_id" id="check_id"/>
				<input type="hidden" name="gubun" id="gubun"/>
				    <colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>
					<tr>
						<th class="mw110">* 사이트명</th>
						<td>
							<input type="text" name="btbs_nm" id="btbs_nm" value="" style="width:300px" maxlength="20" placeholder="한글/영문/숫자 20자 이내"/>
							<button class="button" id="btn_check">중복확인</button>
						</td>					
						<th class="mw110">* 회사명</th>
						<td>
							<input type="text" name="comp_nm" id="comp_nm" value="" style="width:300px" maxlength="20" placeholder="한글/영문/숫자 20자 이내"/>
						</td>
					</tr>					
					<tr>
						<th class="mw110">* 사이트URL</th>
						<td colspan="3">
							<input type="text" name="btbs_domain" id="btbs_domain" value="" style="width:300px" maxlength="100" placeholder="http://를 포함하여 입력"/>
						</td>
					</tr>
					<tr>
						<th class="mw110">* 회사주소</th>
						<td colspan="3">
							<input type="text"  style="width:100px;" id="zipNo"  name="zipNo" value="${rMap.btb.ZIP}" disabled/>
							<button class="button" id="addrSearchBtn">주소 검색</button><br>
							<input type="text"  style="width:500px" id="jibunAddr"  name="jibunAddr" value="${rMap.btb.ADDR_4}" readonly/>
							<input type="text"  style="width:500px" id="addrDetail"  name="addrDetail" value="${rMap.btb.ADDR_5}"/>
						</td>
					</tr>				
					<tr>
						<th class="mw110">* 템플릿 정보</th>
						<!--  <td colspan="3"><input type="text" name="btbs_cur_templ" id="btbs_cur_templ" value="" style="width:300px" maxlength="20"/></td> -->
						<td colspan="3">
							<select name="btbs_cur_templ" id="btbs_cur_templ" style="width:150px">
							<c:forEach items="${templList}" var="templ">
								<option value="${templ.CODE}">${templ.CODENAME}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>담당자 정보 </div>
				</div>	
				<table class="basictables">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>
					<tr>
						<th class="mw110">* 담당자명</th>
						<td>
							<input type="text" name="btbm_nm" id="btbm_nm" value="" style="width:300px" maxlength="20" placeholder="한글/영문/숫자 20자 이내"/>
						</td>
						<th class="mw110">* 직급</th>
						<td>
							<input type="text" name="btbm_rank" id="btbm_rank" value="" style="width:300px" maxlength="20" placeholder="한글/영문/숫자 20자 이내"/>
						</td>						
					</tr>				
					<tr>
						<th class="mw110">* 이메일</th>
						<td>
							<input type="text" name="btbm_email" id="btbm_email" value="" style="width:300px" maxlength="100" placeholder="이메일 주소"/>
						</td>
						<th class="mw110">* 연락처</th>
						<td>
							<select name="hp_1" id="hp_1" style="width:50px">
							<c:forEach items="${hpList}" var="hp">
								<option value="${hp.CODE}">${hp.CODENAME}</option>
							</c:forEach>
							</select>
							<input type="text" name="hp_2" id="hp_2" value="" style="width:50px"  maxlength="4"/>
							<input type="text" name="hp_3" id="hp_3" value="" style="width:50px"  maxlength="4"/>
						</td>						
					</tr>
				</table>				
				<div class="button_area tcenter">
					<button type="button" class="button" id="btn_add">등록</button>
					<button type="button" class="button" id="goList" >취소</button>
				</div>
			</div>
			<div id="dim-layer"></div>
			<div id="layerPopup">
				<table class="basictables">
					<tbody>
						<tr>
							<th>사이트명 중복확인</th>
						</tr>
						<tr>
							<td align="center" height="215px;">
								<div style="height:110px;">
									<input type="text" id="btbsNm" name="btbsNm" value="" style="width: 300px;" maxlength="20" placeholder="사이트명 입력"/><br><br>
									<button class="button" id="checkBtbNm" type="button">중복확인</button>
									<button class="button" id="close" type="button">닫기</button><br><br>
								</div>
								<div style="height:60px;">
									<a href="#" id="selBtbsNm" ><span id="msg" style="font-size:small;"></span></a>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>	 

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/btb/btbAdd.js"></script>
