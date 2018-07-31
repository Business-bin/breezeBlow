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
					<li><a href="/btb/btbList">B2B 사이트 관리</a></li>
					<li class="active"><a href="/btb/btbAdminList">B2B 관리자 관리</a></li>
				</ul>
				<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>B2B 관리자 등록</div>
				</div>	
				<table class="basictables">
				<input type="hidden" name="check_yn" id="check_yn"/>
				<input type="hidden" name="check_id" id="check_id"/>
				<input type="hidden" name="btbs_sq" id="btbs_sq"/>
				<input type="hidden" name="gubun" id="gubun"/>
					<colgroup>
						<col width="15%" />
						<col width="85%" />
					</colgroup>
					<tr>
						<th class="mw110">* 사이트명</th>
						<td>
							<input type="text" name="btb_site_nm" id="btb_site_nm" value="" style="width:300px" placeholder="사이트명 검색" disabled="disabled"/>
							<button class="button" id="btn_site_nm">사이트명 검색</button>
						</td>
					</tr>				
					<tr>
						<th class="mw110">* 아이디</th>
						<td>
							<input type="text" name="adm_email" id="adm_email" value="" style="width:300px" placeholder="이메일 주소"/>
							<button class="button" id="btn_check">중복확인</button>
						</td>
					</tr>
					<tr>
						<th class="mw110">* 비밀번호</th>
						<td>
							<input type="password" name="adm_pwd" id="adm_pwd" value="" style="width:300px" placeholder="영문/숫자/특수문자 2가지 이상 조합, 8자 이상"/>
						</td>
						
					</tr>		
					<tr>
						<th class="mw110">* 비밀번호 확인</th>
						<td>
							<input type="password" name="adm_pwd1" id="adm_pwd1" value="" style="width:300px" maxlength="50" placeholder="비밀번호 재입력"/>
						</td>
					</tr>
					<tr>
						<th class="mw110">* 관리자명</th>
						<td>
							<input type="text" name="adm_nm" id="adm_nm" value="" style="width:300px" maxlength="20" placeholder="한글/영문 20자 이내"/>
						</td>
					</tr>
					<!-- 
					<tr>
						<th class="mw110">* 성별</th>
						<td>
							<select name="adm_gen" id="adm_gen" style="width:50px">
								<option value="">선택</option>
								<option value="M">남</option>
								<option value="F">여</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th>사원번호</th>
						<td><input type="text" name="adm_empn" id="adm_empn" value="" class="numberonly" style="width:300px" maxlength="20"/></td>
					</tr>
					 -->
					<tr>
						<th class="mw110">소속</th>
						<td><input type="text" name="adm_part" id="adm_part" value="" style="width:300px" maxlength="20" placeholder="한글/영문 20자 이내"/></td>
					</tr>
					<tr>
						<th class="mw110">직급</th>
						<td><input type="text" name="adm_rank" id="adm_rank" value="" style="width:300px" maxlength="20" placeholder="한글/영문 20자 이내"/></td>
					</tr>
					<tr>
						<th class="mw110">전화번호</th>
						<td>
							<select name="tel_1" id="tel_1" style="width:50px">
								<option value="">선택</option>
							<c:forEach items="${telList}" var="tel">
								<option value="${tel.CODE}">${tel.CODENAME}</option>
							</c:forEach>
							</select>
							<input type="text" name="tel_2" id="tel_2" value="" style="width:50px" maxlength="4" />
							<input type="text" name="tel_3" id="tel_3" value="" style="width:50px" maxlength="4" />
						</td>
					</tr>					
					<tr>
						<th class="mw110">* 휴대전화번호</th>
						<td>
							<select name="hp_1" id="hp_1" style="width:50px">
							<c:forEach items="${hpList}" var="hp">
								<option value="${hp.CODE}">${hp.CODENAME}</option>
							</c:forEach>
							</select>
							<input type="text" name="hp_2" id="hp_2" value="" style="width:50px" maxlength="4" />
							<input type="text" name="hp_3" id="hp_3" value="" style="width:50px" maxlength="4" />
						</td>
					</tr>

				</table>
				<div class="button_area tcenter">
					<button type="button" class="button" id="btn_add">등록</button>
					<button type="button" class="button" id="goList" >취소</button>
				</div>
			</div>
		</div>
		<div id="dim-layer"></div>
		<div id="layerPopup3">
			<table class="basictables tcenter">
				<tbody>
					<tr>
						<th>B2B사이트명 검색</th>
					</tr>
					<tr>
						<td><input type="text" id="btbsNm" name="btbsNm" value="" />
							<button class="button" id="btbSearch" type="button">검색</button>
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
		<div id="dim-layer2"></div>
		<div id="layerPopup2">
			<table class="basictables">
				<tbody>
					<tr>
						<th>아이디 중복확인</th>
					</tr>
					<tr>
						<td align="center" height="215px;">
							<div style="height:110px;">
								<input type="text" id="admEmail" name="admEmail" value="" style="width: 300px;" placeholder="이메일 주소 입력"/><br><br>
								<button class="button" id="checkId" type="button">중복확인</button>
								<button class="button" id="close2" type="button">닫기</button><br><br>
							</div>
							<div style="width:400px;word-break:normal;word-wrap:break-word;">
								<a href="#" id="selAdmEmail" ><span id="msg" style="font-size:small;"></span></a>
							</div>
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
<script src="/resources/js/btb/btbAdminAdd.js"></script>
