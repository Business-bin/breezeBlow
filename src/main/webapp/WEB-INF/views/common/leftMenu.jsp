<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="mobile">
	<div class="header_area">
		<button id="c-button--slide-left" class="c-button mmenu"><i class="axi axi-menu"></i></button>
		<div class="title"><a href="#">MANAGER</a></div>
		<ul class="loginout">
			<li><a href="#"><i class="axi axi-bmg-customer-segment-1"></i><br/>Modify</a></li>
			<li><a href="#"><i class="axi axi-ion-log-out"></i><br/>Logout</a></li>
		</ul>
	</div>
</header>
<%String s_adm_class = (String)request.getSession().getAttribute("ADM_CLASS");%>
<input type="hidden" name="s_adm_class" id="s_adm_class" value="<%=s_adm_class%>"/>
<%String btbsSq = (String)request.getSession().getAttribute("BTBS_SQ");%>
<input type="hidden" name="btbs_sq_s" id="btbs_sq_s" value="<%=btbsSq%>"/>
<%String btbsNm = (String)request.getSession().getAttribute("BTBS_NM");%>
<input type="hidden" name="btbs_nm_s" id="btbs_nm_s" value="<%=btbsNm%>"/>
<nav class="web">
	<div class="nav_area">
		<ul class="nav">
			<li><a href="#" id="btnMain"><i class="axi axi-home"></i><br/>HOME</a></li>
			<li id="btnAdmin1"><a href="#" id="btnAdmin"><i class="axi axi-person"></i><br/>관리자정보</a></li>
			<li id="btnBtb1"><a href="#" id="btnBtb"><i class="axi axi-sitemap"></i><br/>B2B관리</a></li>
			<li><a href="#" id="btnMemList"><i class="axi axi-group"></i><br/>회원관리</a></li>
			<li><a href="#" id="btnModelList"><i class="axi axi-bmg-checklist"></i><br/>제품관리</a></li>
			<li><a href="#" id="btnAppList"><i class="axi axi-settings-cell"></i><br/>APP관리</a></li>
			<li><a href="#" id="btnBoard"><i class="axi axi-happytalk-counselor"></i><br/>고객센터</a></li>
			<li><a href="#" id="btnEtc"><i class="axi axi-desktop-mac"></i><br/>기타관리</a></li>
			<li><a href="#" id="btnStat"><i class="axi axi-ion-map"></i><br/>사용현황표</a></li>
		</ul>
	</div>
</nav>
<script src="/resources/js/common/leftmenu.js"></script>