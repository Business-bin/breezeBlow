<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" type="text/css" href="/resources/css/admin/admin.css" />
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->

<section>
		<div class="container">
		<form name="admViewForm" id="admViewForm" method="post" action="">
		<input type="hidden" name="gender" id="gender" value="${rMap.admin.ADM_GEN}"/> 
		<input type="hidden" name="adm_sq" id="adm_sq" value="${rMap.admin.ADM_SQ}"/> 
		<input type="hidden" name="adm_hp1" id="adm_hp1" value="${rMap.admin.HP_1}"/> 
		<input type="hidden" name="adm_tel1" id="adm_tel1" value="${rMap.admin.TEL_1}"/> 
		<input type="hidden" name="stat" id="stat" value="${rMap.admin.STAT}"/> 
			<div class="container_body">
				
				<div>상세정보 </div>
				<table class="basictables" id="tbody">
					<tr>
						<th>아이디</th>
						<td>
							<input type="text" name="adm_email" id="adm_email" value="${rMap.admin.ADM_EMAIL}" style="width:300px;background-color: #e2e2e2;" disabled/>
						</td>					
						<th>관리자명</th>
						<td>
							<input type="text" name="adm_nm" id="adm_nm" value="${rMap.admin.ADM_NM}" style="width:150px" maxlength="20"/>
						</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>
							<select name="adm_gen" id="adm_gen" style="width:50px">
								<option value="M">남</option>
								<option value="F">여</option>
							</select>
						</td>
						<th>사원번호</th>
						<td><input type="text" name="adm_empn" id="adm_empn" value="${rMap.admin.ADM_EMPN}" class="numberonly" style="width:150px" maxlength="20"/></td>						
					</tr>
					<tr>
						<th>소속</th>
						<td><input type="text" name="adm_part" id="adm_part" value="${rMap.admin.ADM_PART}" style="width:150px" maxlength="20"/></td>
						<th>직급</th>
						<td><input type="text" name="adm_rank" id="adm_rank" value="${rMap.admin.ADM_RANK}" style="width:150px" maxlength="20"/></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<select name="tel_1" id="tel_1" style="width:50px">
								<option value="">선택</option>
							<c:forEach items="${telList}" var="tel">
								<option value="${tel.CODE}">${tel.CODENAME}</option>
							</c:forEach>
							</select>
							<input type="text" name="tel_2" id="tel_2" value="${rMap.admin.TEL_2}" style="width:50px" maxlength="4"/>
							<input type="text" name="tel_3" id="tel_3" value="${rMap.admin.TEL_3}" style="width:50px" maxlength="4"/>
						</td>					
						<th>휴대전화번호</th>
						<td>
							<select name="hp_1" id="hp_1" style="width:50px">
							<c:forEach items="${hpList}" var="hp">
								<option value="${hp.CODE}">${hp.CODENAME}</option>
							</c:forEach>
							</select>
							<input type="text" name="hp_2" id="hp_2" value="${rMap.admin.HP_2}" style="width:50px" maxlength="4"/>
							<input type="text" name="hp_3" id="hp_3" value="${rMap.admin.HP_3}" style="width:50px" maxlength="4"/>
						</td>
					</tr>
					<tr>
						<th>현재상태</th>
						<c:if test="${rMap.admin.STAT eq '01'}">
						<td colspan="3">
							<input type="text" name="adm_stat" id="adm_stat" value="${rMap.admin.CODENAME}" style="width:150px;background-color: #e2e2e2;" disabled/>
						</td>
						</c:if>
						<c:if test="${rMap.admin.STAT eq '02'}">
						<td>
							<input type="text" name="adm_stat" id="adm_stat" value="${rMap.admin.CODENAME}" style="width:150px;background-color: #e2e2e2;" disabled/>
						</td>
						</c:if>
						<c:if test="${rMap.admin.STAT eq '02'}">
						<th>사유</th>
						<td>
							<input type="text" name="del_rsn" id="del_rsn" value="${rMap.admin.DEL_RSN}" style="width:200px;background-color: #e2e2e2;" disabled/>
						</td>	
						</c:if>
					</tr>
				</table>
				
				<div class="button_area tcenter" id="buttonDiv">
					<button type="button" class="button" id="btn_pwd"><i class="axi axi-person-add"></i>비밀번호 변경</button>
					<button type="button" class="button" id="btn_mod"><i class="axi axi-person-add"></i>수정완료</button>
					<a href="/main" class="button" ><i class="axi axi-cancel"></i>메인</a>
				</div>
			</div>
			<div id="dim-layer1"></div>
			<div id="layerPopup1">
					<table class="basictables">
					<div><b>변경할 비밀번호 입력</b></div>
					<tr>
						<td>
							<input type="password" name="adm_pwd" id="adm_pwd" value="" style="width:300px"  placeholder="영어/숫자/특문 2가지 조합, 8자 이상"/>
						</td>
					</tr>				
					<tr>
						<td>
							<input type="password" name="adm_pwd1" id="adm_pwd1" value="" style="width:300px"  placeholder="비밀번호를 다시 한번 입력하세요"/>
						</td>
					</tr>				
				</table>
				<div class="button_area tcenter" id="buttonDiv">
					<button class="button" id="changePwd" type="button">비밀번호 변경</button>
					<button class="button" id="close1" type="button">취소</button>
				</div>
			</div>			
			</form>
		</div>
	</section>	 

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/common/adminView.js"></script>
