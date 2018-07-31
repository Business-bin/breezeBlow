<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 			uri="http://java.sun.com/jsp/jstl/core"				%>
<%@ taglib prefix="fmt" 		uri="http://java.sun.com/jsp/jstl/fmt"				%>
<%@ taglib prefix="fn" 			uri="http://java.sun.com/jsp/jstl/functions" 		%>
<%@ taglib prefix="customTag" 	uri="/WEB-INF/customTag.tld" 						%>
<!doctype html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=1.0" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="" />
	<meta name="email" content="" />
	<meta name="url" content="" />

	<link rel="stylesheet" type="text/css" href="/resources/css/axicon.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/ax5/ax5grid.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/ax5/ax5calendar.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/ax5/ax5picker.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/ax5/ax5mask.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/ax5/ax5modal.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/ax5/ax5dialog.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/ax5/ax5select.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/style.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/brb/brb.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/jquery.timepicker.min.css" />

	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript" src="/resources/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script type="text/javascript" src="/resources/Highcharts/code/highcharts.js"></script>
	<script type="text/javascript" src="/resources/Highcharts/code/modules/series-label.js"></script>
	<script type="text/javascript" src="/resources/Highcharts/code/modules/exporting.js"></script>
	<script type="text/javascript" src="/resources/js/brb/brb.js"></script>
	<script type="text/javascript" src="/resources/js/jquery/ui/i18n/jquery.ui.datepicker-ko.js"></script>
	<script type="text/javascript" src="/resources/js/i18n/grid.locale-kr.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.timepicker.min.js"></script>
	<style>
		#hedermenu{
			text-align: center;
			width: 100%;
			height: 50px;
			background-color: #e4e4e4;
			position: fixed;
			top: 0px;
			left: 80px;
			border: 2;
			z-index:100;
		}
		#test1 {
			float: left;
			height: 45px;
			font-size: 15px;
			font-weight: 600;
			color: #000;
			vertical-align: middle;
			margin: 12 10 0 10;
		}

		#test2 {
			float: right;
			height: 45px;
			font-size: 15px;
			font-weight: 600;
			color: #000;
			vertical-align: middle;
			margin: 10 100 0 0;
		}
	</style>
</head>
<%

String adminMail = (String)request.getSession().getAttribute("ADM_EMAIL");
String adminSq = (String)request.getSession().getAttribute("ADM_SQ");
String b2bnm = (String)request.getSession().getAttribute("BTBS_NM");
String adminNm = (String)request.getSession().getAttribute("ADM_NM");
%>
<form name="myinfoForm" id="myinfoForm" method="post" action="/login/adminView" >
	<input type="hidden" name="admSq" id="admSq" value="<%=adminSq %>"/>
	<input type="hidden" name="viewid" id="viewid" value="myinfo"/>
</form>
<div id="hedermenu" class="mw650">
	<p id="test1"><%=b2bnm%> ADMINISTRATOR</p>
	<div id="test2">
		<b style="color:#ff8040;"><%=adminMail%></b>
		<button class="button" id="myInfo"><i class="axi axi-person-add"></i>내정보</button>
		<button class="button" id="logout"><i class="axi axi-ion-log-out"></i>로그아웃</button>
	</div>
</div>
<script src="/resources/js/common/header.js"></script>
