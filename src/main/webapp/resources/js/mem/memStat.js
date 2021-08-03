var sDate;
var eDate;
$(function(){
	if($("#s_adm_class").val() == 3 || $("#s_adm_class").val() == 4){
		$(".tabmenu li").eq(2).hide();
		$(".tabmenu li").eq(3).hide();
	}
	$("#layerPopup2").draggable();
	$(".sel1").hide();
	$(".sel2").hide();
	/* 검색 시작일 */
	$("#startDate").datepicker({
		changeMonth : true,
		onSelect : function(dateText, inst) {
			var dateArr = dateText.split("/");
			year = dateArr[2];
			month = dateArr[0];
			day = dateArr[1];
		}
	});

	/* 검색 끝일 */
	$("#endDate").datepicker({
		changeMonth : true,
		onSelect : function(dateText, inst) {
			var dateArr = dateText.split("/");
			year = dateArr[2];
			month = dateArr[0];
			day = dateArr[1];
		}
	});

	/* 당월 1일부터 당일 계산 */
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	if (month < 10) {
		month = "0" + month.toString();
	}
	if (day < 10) {
		day = "0" + day.toString();
	}
	sDate = getStartDate(365);
	eDate = year + "-" + month + "-" + day;
	$("#startDate").val(sDate);
	$("#endDate").val(eDate);

	/* 통계전체 */
	$("#cls1").click(function(){
		$(".sel2").hide();
		$(".sel1").hide();
		$(".stat1").show();
		$(".stat2").show();
		$("#statSearch1").show();
		$("#container1").attr("style", "width: 600px; height: 350px; margin: 0 auto; float:left; margin-left:80px; z-index:-1;");
		$("#container4").attr("style","width: 600px; height: 350px; margin: 0 auto; float:left; margin-left:30px; margin-top:50px;");
		$(".init").val("");
		$("#startDate").val(sDate);
		$("#endDate").val(eDate);
		ch1();
		ch4();
	});

	/* 연령별통계 */
	$("#cls2").click(function(){
		$(".sel2").hide();
		$(".sel1").show();
		$(".stat1").hide();
		$("#statSearch1").hide();
		$("#container1").attr("style","height:700px; width:1200px; margin: 0 auto; float:left; margin-left:80px; z-index:-1;");
		$(".init").val("");
		$("#startDate").val(sDate);
		$("#endDate").val(eDate);
		ch1();
		$("#container1").show();
	});

	/* 지역별통계 */
	$("#cls3").click(function(){
		$(".sel1").hide();
		$(".sel2").show();
		$(".stat2").hide();
		$("#statSearch1").hide();
		$("#container4").attr("style","height:700px; width:1200px; margin: 0 auto; float:left; margin-left:80px; z-index:-1;");
		$(".init").val("");
		$("#startDate").val(sDate);
		$("#endDate").val(eDate);
		ch4();
		$("#container4").show();
	});

	/* btb사이트 검색 */
	$("#btbSearch").click(function() {
		$("#dim-layer").show();
		$("#layerPopup2").show();
		$("#layerPopup2 a").focus();
		$("#btbsNm").val($("#siteNm").val());
		//grid2.fn_SearchGrid2();
	});

	/* 레이어팝업 닫기 */
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup2").hide();
	});

	//grid2.fn_initLoadGrid2();


	/*	조건별 그래프 출력 */
	$("#statSearch1").click(function(){
		
		if(!dayDiffCheck($('#startDate').val(),$('#endDate').val(),'-')){
			return false ;
		};
		if(!dateDiffDay($('#startDate').val(),$('#endDate').val(),'-',365)){
			return false ;
		};
		ch1();
		ch2();
		ch3();
		ch4();
	});

	$("#statSearch2").click(function(){
		
		if(!dayDiffCheck($('#startDate').val(),$('#endDate').val(),'-')){
			return false ;
		};
		if(!dateDiffDay($('#startDate').val(),$('#endDate').val(),'-',365)){
			return false ;
		};
		ch1();
	});

	$("#statSearch3").click(function(){
		alert("333");
		if(!dayDiffCheck($('#startDate').val(),$('#endDate').val(),'-')){
			return false ;
		};
		if(!dateDiffDay($('#startDate').val(),$('#endDate').val(),'-',365)){
			return false ;
		};
		ch4();
	});
	/*	조건별 그래프 출력 */

	ch1();
	ch2();
	ch3();
	ch4();

	/* 탭이동 */
	$(".tabmenu>li").eq(1).addClass("active");
});

function getStartDate(days) {
	var time = (1000 * 3600 * 24) * days;
	var endDate = new Date();
	var diff = Math.abs(endDate.getTime() - time);
	var date = diff instanceof Date ? diff : new Date(diff);

	var year = date.getFullYear();
	var month = (1 + date.getMonth());
	month = month >= 10 ? month : '0' + month;
	var day = date.getDate();
	day = day >= 10 ? day : '0' + day;
	return  year + '-' + month + '-' + day;

}

/*	연령별 회원통계 그래프  */
function ch1(){
	var sDate = $('#startDate').val();
	var eDate = $('#endDate').val();
	if($("#nCal").prop("checked") == true){
		sDate = "";
		eDate = "";
	}
	$.ajax({
		url : "/mem/getAgeStat",
		type : "POST",
		data : {
			startDate : sDate,
			endDate : eDate,
			memGen : $("#memGen").val(),
			memKind : $("#memKind").val(),
			prodNm : $("#prodNm").val()
		},
		success : function(result){
			var r = result.model.cat;
			var r2 = result.model.data;
			chart1(r, r2);
		},
		error : function(e){
			alert("erro : r"+e);
		}
	});
}

function chart1(r, r2) {
	var chart = {
		type : 'column'
	};
	var title = {
		text : '연령별 회원통계'
	};
	var subtitle = {
		text : ''
	};
	var xAxis = {
		categories : r,
		crosshair : true
	};
	var yAxis = {
		min : 0,
		title : {
			text : '회원수'
		}
	};
	var tooltip = {
		headerFormat : '<span style = "font-size:12px">{point.key}</span><table>',
		pointFormat : '<td style = "padding:0"><b>{point.y}명</b></td></tr>',
		footerFormat : '</table>',
		shared : true,
		useHTML : true
	};
	var plotOptions = {
		column : {
			pointPadding : 0.2,
			borderWidth : 0
		}
	};
	var credits = {
		enabled : false
	};
	var series = [ {
		name : '연령별',
		data : r2
	} ];

	var json = {};
	json.chart = chart;
	json.title = title;
	json.subtitle = subtitle;
	json.tooltip = tooltip;
	json.xAxis = xAxis;
	json.yAxis = yAxis;
	json.series = series;
	json.plotOptions = plotOptions;
	json.credits = credits;
	$('#container1').highcharts(json);
}
/*	연령별 회원통계 그래프  */

/*	성별 회원통계 그래프  */
function ch2(){
	var sDate = $('#startDate').val();
	var eDate = $('#endDate').val();
	if($("#nCal").prop("checked") == true){
		sDate = "";
		eDate = "";
	}
	$.ajax({
		url : "/mem/getGenStat",
		type : "POST",
		data : {
			startDate : sDate,
			endDate : eDate
		},
		success : function(result){
			var r = result.model.data;
			var c1 = parseInt(r.MALE);
			var c2 = parseInt(r.FEMALE);
			var c3 = parseInt(c1+c2);
			var r1 = parseInt((c1/c3*100).toFixed(1));
			var r2 = parseInt((c2/c3*100).toFixed(1));
			if(r1+r2 != 100){
				var temp = parseInt((100-(r1+r2))/2);
				r1 += temp;
				r2 += temp;
			}
			chart2(r1, r2, c1, c2);
		},
		error : function(e){
			alert("erro : r"+e);
		}
	});
}

function chart2(r1, r2, c1, c2) {
	var chart = {
		plotBackgroundColor : null,
		plotBorderWidth : null,
		plotShadow : false
	};
	var title = {
		text : '성별 회원통계'
	};
	var tooltip = {
		pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
	};
	var plotOptions = {
		pie : {
			allowPointSelect : true,
			cursor : 'pointer',

			dataLabels : {
				enabled : true,
				format : '<b>{point.name}</b>: {point.percentage:.1f}%',
				style : {
					color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
							|| 'black'
				}
			}
		}
	};
	var series = [ {
		type : 'pie',
		name : '성별',
		data : [{
			name: '남'+c1+"명",
			y: r1
		},{
			name: '여'+c2+"명",
			y: r2
		}]
	} ];
	var json = {};
	json.chart = chart;
	json.title = title;
	json.tooltip = tooltip;
	json.series = series;
	json.plotOptions = plotOptions;
	$('#container2').highcharts(json);
}
/*	성별 회원통계 그래프  */

/*	제품별 회원통계 그래프  */
function ch3(){
	var sDate = $('#startDate').val();
	var eDate = $('#endDate').val();
	if($("#nCal").prop("checked") == true){
		sDate = "";
		eDate = "";
	}
	$.ajax({
		url : "/mem/getProdStat",
		type : "POST",
		data : {
			startDate : sDate,
			endDate : eDate
		},
		success : function(result){
			var r = result.model.cat;
			var r2 = result.model.data;
			chart3(r, r2);
		},
		error : function(e){
			alert("erro : r"+e);
		}
	});
}

function chart3(r, r2) {
	var chart = {
		type : 'bar'
	};
	var title = {
		text : '제품별 회원통계'
	};
	var subtitle = {};
	var xAxis = {
		categories : r,
		title : {
			text : null
		}
	};
	var yAxis = {
		min : 0,
		labels : {
			overflow : 'justify'
		},
		title : {
			text : "회원수(명)"
		}
	};
	var tooltip = {
		valueSuffix : '명'
	};
	var plotOptions = {
		bar : {
			dataLabels : {
				enabled : true
			}
		}
	};
	var legend = {
		layout : 'vertical',
		align : 'right',
		verticalAlign : 'top',
		x : -40,
		y : 100,
		floating : true,
		borderWidth : 1,

		backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
		shadow : true
	};
	var credits = {
		enabled : false
	};
	var series = [ {
		name : "제품별",
		data : r2
	} ]

	var json = {};
	json.chart = chart;
	json.title = title;
	json.subtitle = subtitle;
	json.tooltip = tooltip;
	json.xAxis = xAxis;
	json.yAxis = yAxis;
	json.series = series;
	json.plotOptions = plotOptions;
	json.legend = legend;
	json.credits = credits;
	$('#container3').highcharts(json);
}
/*	제품별 회원통계 그래프  */

/*	지역별 회원통계 그래프  */
function ch4(){
	var sDate = $('#startDate').val();
	var eDate = $('#endDate').val();
	if($("#nCal").prop("checked") == true){
		sDate = "";
		eDate = "";
	}
	$.ajax({
		url : "/mem/getLocStat",
		type : "POST",
		data : {
			startDate : sDate,
			endDate : eDate,
			memGen : $("#memGen").val(),
			memKind : $("#memKind").val(),
			addr1 : $("#addr1").val()
		},
		success : function(result){
			var r = result.model.cat;
			var r2 = result.model.data;
			chart4(r, r2);
		},
		error : function(e){
			alert("erro : r"+e);
		}
	});
}

function chart4(r, r2){
	var chart = {
			type : 'bar'
		};
		var title = {
			text : '지역별 회원통계'
		};
		var subtitle = {};
		var xAxis = {
			categories : r,
			title : {
				text : null
			}
		};
		var yAxis = {
			min : 0,
			labels : {
				overflow : 'justify'
			},
			title : {
				text : "회원수(명)"
			}
		};
		var tooltip = {
			valueSuffix : '명'
		};
		var plotOptions = {
			bar : {
				dataLabels : {
					enabled : true
				}
			}
		};
		var legend = {
			layout : 'vertical',
			align : 'right',
			verticalAlign : 'top',
			x : -40,
			y : 100,
			floating : true,
			borderWidth : 1,

			backgroundColor : ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
			shadow : true
		};
		var credits = {
			enabled : false
		};
		var series = [ {
			name : "지역별",
			data : r2
		} ]

		var json = {};
		json.chart = chart;
		json.title = title;
		json.subtitle = subtitle;
		json.tooltip = tooltip;
		json.xAxis = xAxis;
		json.yAxis = yAxis;
		json.series = series;
		json.plotOptions = plotOptions;
		json.legend = legend;
		json.credits = credits;
		$('#container4').highcharts(json);
}
/*	지역별 회원통계 그래프  */