<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="customTag" uri="/WEB-INF/customTag.tld" %>
<!-- css 변환 -->
<link rel="stylesheet" type="text/css" href="/resources/css/mem/mem.css" />
<div style="height:1050px;">
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->
<section>
	<div class="container">
		<div class="container_body">
		<form  id="updateMemForm" action="/mem/updateMem" method="post">
			<input type="hidden" id="addr1" name="addr1" value=""/>
			<input type="hidden" id="addr2" name="addr2" value="" />
			<input type="hidden" id="addr3" name="addr3" value="" />
			<input type="hidden" id="addr4" name="addr4" value="" />
			<ul class="tabmenu web">
				<li><a href="/mem/memList">회원 목록</a></li>
				<li><a href="/mem/memStat">회원 통계</a></li>
				<li><a href="/push/pushList">Push발송</a></li>
				<li><a href="/sms/smsList">SMS 발송</a></li>
			</ul>
			<input type="hidden" id="stat" name="stat" value="${mem.STAT}" />
			<div class="tbar">
				<div class="w100 fl"><i class="axi axi-play-arrow"></i>상세 정보</div>
			</div>
			<div class="w100">
				<table class="basictables">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="35%" />
					</colgroup>
					<tbody>
						<tr>
							<th class="mw110">일련번호</th>
							<td><input type="text" id="memSq" class="ac w100" name="memSq" value="${mem.MEM_SQ}" readonly="readonly"/></td>
							<th class="mw110">사이트명</th>
							<td><input type="text" id="btbsNm" class="ac w100" name="btbsNm" value="${mem.BTBS_NM}" readonly="readonly" /></td>
						</tr>
						<tr>
							<th class="mw110">회원ID</th>
							<td><input type="text" id="memEmail" class="ac w100" name="memEmail" value="${mem.MEM_EMAIL}" /></td>
							<th class="mw110">이름</th>
							<td><input type="text" id="memNm" class="ac w100" name="memNm" value="${mem.MEM_NM}" /></td>
						</tr>
						<tr>
							<th class="mw110">성별</th>
							<td>
								<c:if test="${mem.MEM_GEN eq 'M'}">
									<div class="checks small mgt6">
										<input type="radio" id="memGen1"  name="memGen" value="M" checked="checked" />
										<label for="memGen1">남 </label>
										<input type="radio" id="memGen2" name="memGen" value="F" />
									  	<label for="memGen2" class="mgl10">여 </label>
								  	</div>
								 </c:if>
								 <c:if test="${mem.MEM_GEN eq 'F'}">
									<div class="checks small mgt6">
										<input type="radio" id="memGen1" name="memGen" value="M"/>
										<label for="memGen1">남 </label>
										<input type="radio" id="memGen2" name="memGen" value="F" checked="checked" />
									  	<label for="memGen2" class="mgl10">여 </label>
								  	</div>
								  </c:if>
							</td>
							<th class="mw110">현재상태</th>
							<td><input type="text" id="statCode" class="ac w100" name="statCode" value="${mem.STATCODE}" readonly="readonly" /></td>
						</tr>
						<tr id="drsn">
							<th class="mw110">삭제사유</th>
							<td colspan="3" class="pdr8"><input type="text" id="delRsn" class="w100" name="delRsn" value="${mem.DEL_RSN}" readonly="readonly" /></td>
						</tr>
						<tr>
							<th class="mw110">거주지역</th>
							<td colspan="3"><input type="text" id="addr" class="w50 mw350" value="${mem.ADDR}" /><button type="button" id="addrBtn" class="button mgl10">주소 검색</button></td>
						</tr>
						<tr>
							<th class="mw110">생년월일</th>
							<td><input type="text" id="memBir" class="ac w100" name="memBir" value="${mem.MEM_BIR}" maxlength="10"/></td>
							<th class="mw110">핸드폰</th>
							<td><select id="hp1" name="hp1" class="ac">
									<c:forEach items="${hpList}" var="hp">
										<c:if test="${hp.CODE eq mem.hp1}">
											<option value="${hp.CODE}" selected="selected">${hp.CODENAME}</option>
										</c:if>
										<c:if test="${hp.CODE ne mem.hp1}">
											<option value="${hp.CODE}">${hp.CODENAME}</option>
										</c:if>
									</c:forEach>
								   </select>
									- <input type="text" id="hp2" class="ac" name="hp2" value="${mem.hp2}" size="4" maxlength="4"/>
									- <input type="text" id="hp3" class="ac" name="hp3" value="${mem.hp3}" size="4" maxlength="4"/>
							</td>
						</tr>
						<tr>
							<th class="mw110">위치정보 동의</th>
							<td>
								<c:if test="${mem.AGR_LI_YN eq 'Y'}">
									<div class="checks small mgt6">
										<input type="radio" id="agrLiYn1" name="agrLiYn" value="Y" checked="checked" />
										<label for="agrLiYn1">동의함</label>
										<input type="radio" id="agrLiYn2" name="agrLiYn" value="N" />
										<label for="agrLiYn2" class="mgl10">동의하지 않음</label>
									</div>
								</c:if>
								<c:if test="${mem.AGR_LI_YN eq 'N'}">
									<div class="checks small mgt6">
										<input type="radio" id="agrLiYn1" name="agrLiYn" value="Y" />
										<label for="agrLiYn1">동의함</label>
										<input type="radio" id="agrLiYn2" name="agrLiYn" value="N" checked="checked" />
										<label for="agrLiYn2" class="mgl10">동의하지 않음</label>
									</div>
								</c:if>
							</td>
							<th class="mw110">마케팅 수신 동의</th>
							<td>
								<c:if test="${mem.AGR_RM_YN eq 'Y'}">
									<div class="checks small mgt6">
										<input type="radio" id="agrRmYn1" name="agrRmYn" value="Y" checked="checked" />
										<label for="agrRmYn1">동의함</label>
										<input type="radio" id="agrRmYn2" name="agrRmYn" value="N" />
										<label for="agrRmYn2" class="mgl10">동의하지 않음</label>
									</div>
								</c:if>
								<c:if test="${mem.AGR_RM_YN eq 'N'}">
									<div class="checks small mgt6">
										<input type="radio" id="agrRmYn1" name="agrRmYn" value="Y" />
										<label for="agrRmYn1">동의함</label>
										<input type="radio" id="agrRmYn2" name="agrRmYn" value="N" checked="checked" />
										<label for="agrRmYn2" class="mgl10">동의하지 않음</label>
									</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<th class="mw110">Push 수신 동의</th>
							<td>
								<c:if test="${mem.AGR_PUSH_YN eq 'Y'}">
									<div class="checks small mgt6">
										<input type="radio" id="agrPushYn1" name="agrPushYn" value="Y" checked="checked" />
										<label for="agrPushYn1">동의함</label>
										<input type="radio" id="agrPushYn2" name="agrPushYn" value="N" />
										<label for="agrPushYn2" class="mgl10">동의하지 않음</label>
									</div>
								</c:if>
								<c:if test="${mem.AGR_PUSH_YN eq 'N'}">
									<div class="checks small mgt6">
										<input type="radio" id="agrPushYn1" name="agrPushYn" value="Y" />
										<label for="agrPushYn1">동의함</label>
										<input type="radio" id="agrPushYn2" name="agrPushYn" value="N" checked="checked" />
										<label for="agrPushYn2" class="mgl10">동의하지 않음</label>
									</div>
								</c:if>
							</td>
							<th class="mw110">SMS 수신 동의</th>
							<td>
								<c:if test="${mem.AGR_SMS_YN eq 'Y'}">
									<div class="checks small mgt6">
										<input type="radio" id="agrSmsYn1" name="agrSmsYn" value="Y" checked="checked" />
										<label for="agrSmsYn1">동의함</label>
										<input type="radio" id="agrSmsYn2" name="agrSmsYn" value="N" />
										<label for="agrSmsYn2" class="mgl10">동의하지 않음</label>
									</div>
								</c:if>
								<c:if test="${mem.AGR_SMS_YN eq 'N'}">
									<div class="checks small mgt6">
										<input type="radio" id="agrSmsYn1" name="agrSmsYn" value="Y" />
										<label for="agrSmsYn1">동의함</label>
										<input type="radio" id="agrSmsYn2" name="agrSmsYn" value="N" checked="checked" />
										<label for="agrSmsYn2" class="mgl10">동의하지 않음</label>
									</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<th class="mw110">가입 일시</th>
							<td><input type="text" id="regDttm" class="w100" name="regDttm" size="30" value="${fn:substring(mem.REG_DTTM,0,19)}" disabled="disabled" /></td>
							<th class="mw110">최종 로그인 일시</th>
							<td><input type="text" id="lastLoginDttm" class="w100" name="lastLoginDttm" size="30" value="${fn:substring(mem.RCNT_CON_DTTM,0,19)}" disabled="disabled" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="mgt10">
				<button class="button" id="logMem" type="button">활동기록</button>
				<button class="button" id="modMem" type="button">수정완료</button>
				<button class="button" id="chg" type="button">비밀번호변경</button>
				<button class="button" id="del" type="button">삭제</button>
				<button class="button" id="backMemList" type="button">목록으로</button>
			</div>
		</form>
		<form action="/mem/memList" method="post" id="backForm">
			<input type="hidden" name="siteNm" value="${dMap.siteNm}"/>
			<input type="hidden" name="btbsSq" value="${dMap.btbsSq}"/>
			<input type="hidden" name="addr_1" value="${dMap.addr_1}"/>
			<input type="hidden" name="addr_2" value="${dMap.addr_2}"/>
			<input type="hidden" name="addr_3" value="${dMap.addr_3}"/>
			<input type="hidden" name="memGen" value="${dMap.memGen}"/>
			<input type="hidden" name="startDate" value="${dMap.startDate}"/>
			<input type="hidden" name="endDate" value="${dMap.endDate}"/>
			<input type="hidden" name="search" value="${dMap.search}"/>
			<input type="hidden" name="keyWord" value="${dMap.keyWord}"/>
		</form>
		<form id="deleteMemForm" action="/mem/deleteMem" method="post">
			<input type="hidden" id="memSq2" name="memSq" value="${mem.MEM_SQ}" />
			<div id="dim-layer"></div>
			<div id="layerPopup">
				<table class="basictables">
					<tbody>
						<tr>
							<th>관리자ID</th>
							<td><input type="text" id="admEmail" name="admEmail"
								value="${admEmail}" disabled="disabled" /></td>
						</tr>
						<tr>
							<th>삭제사유</th>
							<td><textarea rows="5" cols="20" id="delRsn2" name="delRsn"></textarea></td>
						</tr>
						<tr>
							<td colspan="2" class="tright">
								<button class="button" id="delMem" type="button">삭제하기</button>
								<button class="button" id="close" type="button">닫기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<form id="updatePwdForm" action="/mem/updatePwd" method="post">
			<input type="hidden" id="memSq3" name="memSq" value="${mem.MEM_SQ}" />
			<div id="layerPopup3">
				<table class="basictables">
					<tbody>
						<tr>
							<th>변경할 비밀번호 입력</th>
						</tr>
						<tr>
							<td>
								<input type="text" id="pwd" name="memPwd" class="w100" placeholder="영문/숫자/특수문자 2가지 이상 조합, 8자 이상"/><br>
								<input type="text" id="pwd2" name="memPwd2" class="w100" placeholder="비밀번호를 다시 한번 입력하세요."/>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="ac">
								<button class="button" id="chgPwd" type="button">비밀번호 변경</button>
								<button class="button" id="close2" type="button">닫기</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
		<div id="layerPopup4">
			<table class="basictables">
				<colgroup>
					<col width="35%" />
					<col width="20%" />
					<col width="45%" />
				</colgroup>
				<tbody>
					<tr>
						<th colspan="3">제품 상세 정보</th>
					</tr>
					<tr>
						<td rowspan="7" class="mw100"><img src="" id="prodImg"/></td>
						<th>제품명</th><th id="cpNm"></th>
					</tr>
					<tr>
						<th>모델명</th><td id="mdNm"></td>
					</tr>
					<tr>
						<th>상세규격</th><td id="detStd"></td>
					</tr>
					<tr>
						<th>기기설명</th><td><textarea rows="3" cols="10" id="des"></textarea></td>
					</tr>
					<tr>
						<th class="mw120">주요사용처</th><td><textarea rows="3" cols="10" id="usg"></textarea></td>
					</tr>
					<tr>
						<th>S/N</th><td id="pprtMac"></td>
					</tr>
					<tr>
						<th>등록일시</th><td id="regDttm2"></td>
					</tr>
					<tr>
						<td colspan="3" class="ac">
							<button class="button" id="close3" type="button">닫기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="layerPopup5">
			<table class="basictables">
				<colgroup>
					<col width="35%" />
					<col width="20%" />
					<col width="45%" />
				</colgroup>
				<tbody>
					<tr>
						<th colspan="3">보유제품 수정</th>
					</tr>
					<tr>
						<td rowspan="7" class="mw100"><img src="" id="prodImg2"/></td>
						<th>제품명</th><th id="cpNm2"></th>
					</tr>
					<tr>
						<th>모델명</th><td id="mdNm2"></td>
					</tr>
					<tr>
						<th>상세규격</th><td id="detStd2"></td>
					</tr>
					<tr>
						<th>기기설명</th><td><textarea rows="3" cols="10" id="des2" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<th class="mw120">주요사용처</th><td><textarea rows="3" cols="10" id="usg2" readonly="readonly"></textarea></td>
					</tr>
					<tr>
						<th>S/N</th><td id="pprtMac2"></td>
					</tr>
					<tr>
						<th>등록일시</th><td id="regDttm3"></td>
					</tr>
					<tr>
						<th colspan="3">설치지역</th>
					</tr>
					<tr>
						<td colspan="3" class="ac">
						<input type="text" id="modAddr1" name="modAddr1" size="10" readonly="readonly"/>
						<input type="text" id="modAddr2" name="modAddr2" size="10" readonly="readonly"/>
						<input type="text" id="modAddr3" name="modAddr3" size="10" readonly="readonly"/>
						<input type="text" id="modAddr4" name="modAddr4" size="10" readonly="readonly"/>
						<input type="text" id="modAddr5" name="modAddr5" size="10" readonly="readonly"/>
						<button type="button" id="addrBtn2" class="button mgl10">주소 검색</button></td>
					</tr>
					<tr>
						<th colspan="3">설치장소</th>
					</tr>
					<tr>
						<td colspan="3" class="ac">
							<select id="pprtAli" name="pprtAli">
								<option value="거실">거실</option>
								<option value="침실">침실</option>
								<option value="주방">주방</option>
								<option value="서재">서재</option>
								<option value="아이방">아이방</option>
								<option value="">기타 (작접입력)</option>
							</select>
							<input type="text" id="aliText" name="aliText" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="ac"><button class="button mgr10" type="button" onclick="modProd()">수정</button><button class="button" id="close4" type="button">닫기</button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<input type="hidden" id="pprtMac3"/>
		<div id="layerPopup6">
		<input type="hidden" id="delSq" />
			<table class="basictables">
				<tbody>
					<tr>
						<th>관리자ID</th>
						<td><input type="text" id="admEmail" name="admEmail"
							value="${admEmail}" disabled="disabled" /></td>
					</tr>
					<tr>
						<th>삭제사유</th>
						<td><textarea rows="5" cols="20" id="initRsn" name="initRsn"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" class="tright">
							<button class="button" type="button" onclick="delProd()">삭제하기</button>
							<button class="button" id="close5" type="button">닫기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="layerPopup7">
			<table class="basictables">
				<tbody>
					<tr>
						<th>보유제품 등록</th>
					</tr>
					<tr>
						<td>
							<b>시리얼 번호 검색</b><br><br>
							<input type="text" id="regMac" name="regMac" placeholder="입력 예)aa:bb:cc:dd:ee"/>
							<button class="button mgr10" onclick="searchProd()" type="button">검색</button>
							선택 : <input type="text" class="mgl10" id="regMac2" name="regMac2" />
						</td>
					</tr>
					<tr>
						<td>
							<b>검색결과</b><br>
							<table id="mac_list" class="mgt4"></table>
							<div id="pager2"></div>
						</td>
					</tr>
					<tr>
						<td>
							<b>설치지역</b><br><br>
							<input type="text" id="regAddr1" name="regAddr1" size="10" readonly="readonly"/>
							<input type="text" id="regAddr2" name="regAddr2" size="10" readonly="readonly"/>
							<input type="text" id="regAddr3" name="regAddr3" size="10" readonly="readonly"/>
							<input type="text" id="regAddr4" name="regAddr4" size="10" readonly="readonly"/>
							<input type="text" id="regAddr5" name="regAddr5" size="10" readonly="readonly"/>
							<button type="button" id="addrBtn3" class="button mgl10">주소 검색</button>
						</td>
					</tr>
					<tr>
						<td>
							<b>설치장소</b><br><br>
							<select id="pprtAli2" name="pprtAli2">
								<option value="거실">거실</option>
								<option value="침실">침실</option>
								<option value="주방">주방</option>
								<option value="서재">서재</option>
								<option value="아이방">아이방</option>
								<option value="">기타 (작접입력)</option>
							</select>
							<input type="text" id="aliText2" name="aliText2" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="ac">
							<button class="button" type="button" onclick="regProd()">등록하기</button>
							<button class="button" id="close6" type="button">닫기</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tbar">
			<div class="w50 fl"><i class="axi axi-play-arrow"></i>보유 제품</div>
			<div class="w50 ar fl" id="tcnt">총 0개</div>
		</div>
		<table id="prod_list"></table>
		<div id="pager"></div>
		<div class="ar mgt10"><button class="button" onclick="rpop()" type="button">보유제품등록</button></div>
		</div>
	</div>
</section>
</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
<script src="/resources/js/mem/memDet.js"></script>