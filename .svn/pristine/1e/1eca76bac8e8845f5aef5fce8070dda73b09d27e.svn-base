<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/sample/sample.css">	
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->
<%
HttpSession session1 = request.getSession(false);
String adm_nm = session1.getAttribute("ADM_EMAIL").toString();
%>
<section>
		<div class="container">
			<div class="container_body">
				<ul class="tabmenu web">
					<%if(s_adm_class.equals("1") || s_adm_class.equals("2")){ %>
					<li><a href="/board/asList">AS 관리</a></li>
					<%} %>
					<li class="active"><a href="/board/noticeList">공지사항 관리</a></li>
					<li><a href="/board/faqList">FAQ 관리</a></li>
					<li><a href="/board/qnaList">Q&A 관리</a></li>
				</ul>
				<div class="tbar">
					<div class="w50 fl"><i class="axi axi-play-arrow"></i>공지사항 등록</div>
				</div>
				<!--  <form action="/board/noticeAddProc" id="frm" method="post" enctype="multipart/form-data">  -->
				<form action="/board/noticeAddProc" id="frm" method="post" >
				<input type="hidden" name="bd_tp_1" id="bd_tp_1" value="N"/>	
				<table class="basictables">
					<colgroup>
						<col width="15%" />
						<col width="85%" />
					</colgroup>	
					<tr>
						<th class="mw110">작성자 ID</th>
						<td>
							<input type="text" name="adminMail" id="adminMail" value="<%=adminMail %>" style="width:300px;" disabled/>
						</td>
					</tr>
					<tr>
						<th class="mw110">노출여부</th>
						<td>
							<select name="bd_stat" id="bd_stat" style="width:100px">
							<c:forEach items="${statList}" var="stat">
								<option value="${stat.CODE}">${stat.CODENAME}</option>
							</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th class="mw110">제목</th>
						<td>
							<input type="text" name="bd_nm" id="bd_nm" value="" style="width:100%" maxlength="200"/>
						</td>
					</tr>		
					<tr>
						<th class="mw110">내용</th>
						<td>
							<textarea rows="25" cols="30" name="bd_cont" id="bd_cont"  style="width:100%" ></textarea>
						</td>
					</tr>
					<!-- 
					<tr>
						<th>첨부파일</th>
						<td>
							<input type="file" name="imageFile" id="imageFile1" style="width:450px; display:inline;" /><button type="button" class="button" id="" onclick="delFile(1);"><i class="axi axi-delete2"></i>삭제</button><br>
							<input type="file" name="imageFile" id="imageFile2" style="width:450px; display:inline;" /><button type="button" class="button" id="" onclick="delFile(2);"><i class="axi axi-delete2"></i>삭제</button><br>
							<input type="file" name="imageFile" id="imageFile3" style="width:450px; display:inline;" /><button type="button" class="button" id="" onclick="delFile(3);"><i class="axi axi-delete2"></i>삭제</button><br>
							<input type="file" name="imageFile" id="imageFile4" style="width:450px; display:inline;" /><button type="button" class="button" id="" onclick="delFile(4);"><i class="axi axi-delete2"></i>삭제</button><br>
							<input type="file" name="imageFile" id="imageFile5" style="width:450px; display:inline;" /><button type="button" class="button" id="" onclick="delFile(5);"><i class="axi axi-delete2"></i>삭제</button><br>
						</td>
					</tr>	
				 	-->				
				</table>
				</form>
				<div class="button_area tcenter">
					<button type="button" class="button" id="btn_add">등록</button>
					<button type="button" class="button" id="goList" >취소</button>
				</div>
			</div>
		</div>
	</section>	 

<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/board/noticeAdd.js"></script>
