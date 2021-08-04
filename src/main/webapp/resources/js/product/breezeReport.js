/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){
	if($("#s_adm_class").val() == 3 || $("#s_adm_class").val() == 4){
		$(".tabmenu li").eq(0).hide();
		$(".tabmenu li").eq(3).hide();
	}

	$('#start_dt').val($.datepicker.formatDate('yy-mm-dd', new Date()));
	$('#end_dt').val($.datepicker.formatDate('yy-mm-dd', new Date()));

	$("#start_dt").datepicker({
		 changeMonth: true,
		onSelect: function(dateText , inst){
			var dateArr = dateText.split("/");
			year = dateArr[2];
			month = dateArr[0];
			day = dateArr[1];
		}
	});

	$("#end_dt").datepicker({
		 changeMonth: true,
		onSelect: function(dateText , inst){
			var dateArr = dateText.split("/");
			year = dateArr[2];
			month = dateArr[0];
			day = dateArr[1];
		}
	});


	var val = $(':radio[name="gubun"]:checked').val();
	if(val=="01"){ // 전체
		$("#placeTr").hide();
		$("#ageTr").hide();
		$("#memTr").hide();
		$("#btn_sch").show();
	}else if(val=="02"){  // 지역별
		$("#placeTr").show();
		$("#ageTr").hide();
		$("#memTr").hide();
		$("#btn_sch").hide();
	}else if(val=="03"){  // 연령별
		$("#ageTr").show();
		$("#placeTr").hide();
		$("#memTr").hide();
		$("#btn_sch").hide();
	}else if(val=="04"){  // 회원별
		$("#memTr").show();
		$("#placeTr").hide();
		$("#ageTr").hide();
		$("#btn_sch").hide();
	} 
	
	var bSq = $('#btbs_sq_s').val();
	if(bSq != 1){
		$("select[name='btbs_sq']").val(bSq).attr("selected", "selected");
		$('#btbs_sq').attr("disabled", true);
	}


	goChart();

    $("#btn_sch").click(function(){
    	goChart();
    });
    $("#btn_sch2").click(function(){
    	goChart();
    });
    $("#btn_sch3").click(function(){
    	goChart();
    });
    $("#btn_sch4").click(function(){
    	goChart();
    });



});

$("input[name='gubun']:radio").change(function () {
	var type = this.value;
                                         
	if(type=="01"){ // 전체
		$("#placeTr").hide();
		$("#ageTr").hide();
		$("#memTr").hide();
		$("#btn_sch").show();
	}else if(type=="02"){  // 지역별
		$("#placeTr").show();
		$("#ageTr").hide();
		$("#memTr").hide();
		$("#btn_sch").hide();
	}else if(type=="03"){  // 연령별
		$("#ageTr").show();
		$("#placeTr").hide();
		$("#memTr").hide();
		$("#btn_sch").hide();
	}else if(type=="04"){  // 회원별
		$("#memTr").show();
		$("#placeTr").hide();
		$("#ageTr").hide();
		$("#btn_sch").hide();
	}                               
});


/********************************************************************
Name   : dateDiff
Desc   : 날짜 차이 계산
Param  :
********************************************************************/
function dateDiff(_date1, _date2) {
    var diffDate_1 = _date1 instanceof Date ? _date1 : new Date(_date1);
    var diffDate_2 = _date2 instanceof Date ? _date2 : new Date(_date2);

    var diff = Math.abs(diffDate_2.getTime() - diffDate_1.getTime());
    diff = Math.ceil(diff / (1000 * 3600 * 24));

    return diff;
}
/********************************************************************
Name   : dateDiff2
Desc   : 월 차이 계산
Param  :
********************************************************************/
function dateDiff2(_date1, _date2) {
    var diffDate_1 = _date1 instanceof Date ? _date1 : new Date(_date1);
    var diffDate_2 = _date2 instanceof Date ? _date2 : new Date(_date2);

    var diff = diffDate_2.getMonth() - diffDate_1.getMonth();

    return diff;
}

/********************************************************************
Name   : goChart
Desc   : 차트 조회
Param  :
********************************************************************/
function goChart(){

	//라디오버튼 구분
	var gubun = $(':radio[name="gubun"]:checked').val();
	//검색단위(시간,일,월)
	var searchType = $("#category option:selected").val();

	var xAxisText = "";
	if(searchType =="hour"){
		xAxisText = "시간";
	}else if(searchType =="day"){
		xAxisText = "날짜";
	}else if(searchType =="month"){
		xAxisText = "월";
	}

	//지역명
	var addr_si = $("#addr_si option:selected").text();
	// addr_si = addr_si.substring(0,2);
	//센서종류

	var sensorType = "";
	var sensorNm = "";
	if(gubun == "02"){
		sensorType = $("#sensor_type option:selected").val();
		sensorNm = $("#sensor_type option:selected").text();
	}else if(gubun == "03"){
		sensorType = $("#sensor_type2 option:selected").val();
		sensorNm = $("#sensor_type2 option:selected").text();
	}

	if(!dayDiffCheck($('#start_dt').val(),$('#end_dt').val(),'-')){
		return false ;
	};

	// 날짜제외 체크여부
	var dateChk = $("#nCal").prop("checked")
	if(searchType == "day"){
		if(dateDiff($('#start_dt').val(), $('#end_dt').val()) < 2 && dateChk != true){
			alert("검색단위가 일 일경우 시작일과 종료일이 최소 2일 이상 차이가 나야 합니다.");
			return;
		}

    	if(!dateDiffDay($('#start_dt').val(),$('#end_dt').val(),'-',30)){
    		return false ;
    	};

	}else if(searchType == "month"){
		if(dateDiff2($('#start_dt').val(), $('#end_dt').val()) < 2 && dateChk != true){
			alert("검색단위가 월 일경우 시작일과 종료일이 최소 2개월 이상 차이가 나야 합니다.");
			return;
		}
	}
	var sDate = $('#start_dt').val().replace(/-/g,'');
	var eDate = $('#end_dt').val().replace(/-/g,'');
	if($("#nCal").prop("checked") == true){
		sDate = "";
		eDate = "";
	}
	$.ajax({
		url : '/product/chartList',
		method : 'POST',
		dataType: 'JSON',
		data : {
			gubun          : gubun,
			start_dt       : sDate,
			end_dt         : eDate,
			category       : $('#category').val(),
			category2      : $('#category2').val(),
			addr_si        : addr_si,
			age_type       : $('#age_type').val(),
			btbs_sq        : $('#btbs_sq').val(),
			btbs_sq_s      : $('#btbs_sq_s').val()

		},
		success : function(jsonData) {

		   var list = jsonData.list;
		   console.log(list);

		   if(gubun == "01" || gubun == "04"){ //전체, 회원별
			   var temp =[];
			   var humi =[];
			   var hcho =[];
			   var co2 =[];
			   var co =[];
			   var pm25 =[];
			   var atopy =[];
			   var time =[];

			   if(list.length > 0){
			       for(var i=0; i<list.length; i++){

			    	   temp[i] = parseFloat(list[i].TEMP);
			    	   humi[i] = parseFloat(list[i].HUMI);
			    	   hcho[i] = parseFloat(list[i].HCHO);
			    	   co2[i] = parseFloat(list[i].CO2);
				       co[i] = parseFloat(list[i].CO);
				       pm25[i] = parseFloat(list[i].PM25);
				       atopy[i] = parseFloat(list[i].ATOPY);
				       time[i] = list[i].SDATE;
			       }
			   }

			   var seriesArray = [];
			   var seriesObject = {};

			   // 온도 세팅
			   seriesObject = {};
			   seriesObject.data = temp;
			   seriesObject.name = '온도';
			   seriesArray.push(seriesObject);

			   // 습도 세팅
			   seriesObject = {};
			   seriesObject.data = humi;
			   seriesObject.name = '습도';
			   seriesArray.push(seriesObject);

			   // HCHO 세팅
			   seriesObject = {};
			   seriesObject.data = hcho;
			   seriesObject.name = 'HCHO';
			   seriesArray.push(seriesObject);

			   // CO2 세팅
			   seriesObject = {};
			   seriesObject.data = co2;
			   seriesObject.name = 'CO2';
			   seriesArray.push(seriesObject);

			   // CO 세팅
			   seriesObject = {};
			   seriesObject.data = co;
			   seriesObject.name = 'CO';
			   seriesArray.push(seriesObject);

			   // PM2.5 세팅
			   seriesObject = {};
			   seriesObject.data = pm25;
			   seriesObject.name = 'PM2.5';
			   seriesArray.push(seriesObject);

			   // V 세팅
			   seriesObject = {};
			   seriesObject.data = atopy;
			   seriesObject.name = 'V';
			   seriesArray.push(seriesObject);

			   setHiChart(seriesArray, time, xAxisText);

		   }else if(gubun == "02"){//지역별

			   var addrData =[];
			   var time =[];
			   var time1 =[];
			   var seriesArray = [];
			   var seriesObject = {};
			   var addrNm ="";

			   if(list.length > 0){
			       for(var i=0; i<list.length; i++){
		    		   if(list[i].SDATE != null){
		    			   addrData.push(parseFloat(list[i][sensorType]));
		    			   time.push(list[i].SDATE);
		    			   addrNm = list[i].ADDR_2;
		    		   }else if(list[i].SDATE == null && list[i].ADDR_2 !=null){
		    			   seriesObject = {};
		    			   seriesObject.data = addrData;
		    			   seriesObject.name = addrNm; //구,군명
		    			   seriesArray.push(seriesObject);

		    			   time1 = time;
		    			   addrData =[];
		    			   time =[];
		    		   }
			       }
			   }

			   setHiChart(seriesArray, time1, xAxisText);

		   }else if(gubun == "03"){//연령별
			   var all =[];
			   var male =[];
			   var female =[];
			   var time =[];

			   if(list.length > 0){
			       for(var i=0; i<list.length; i++){
		    		   if(list[i].MEM_GEN == null && list[i].SDATE != null){
		    			   all.push(parseFloat(list[i][sensorType]));
		    			   time.push(list[i].SDATE);
		    		   }
			       }
			   }
			   
			   if(time.length >0){
				   for(var k=0; k<time.length; k++){
					   for(var j=0; j<list.length; j++){
						   if(time[k] == list[j].SDATE ){
							   if(list[j].MEM_GEN =="M"){
								   male[k] = parseFloat(list[j][sensorType]);
							   }else if(list[j].MEM_GEN =="F"){
								   female[k] = parseFloat(list[j][sensorType]);
							   }
						   }
					   }
					   
					   if(male[k]==null){
						   male[k] = '';
					   }
					   
					   if(female[k]==null){
						   female[k] = '';
					   }
				   }
			   }
			   
			   var seriesArray = [];
			   var seriesObject = {};

			   //전체 세팅
			   seriesObject = {};
			   seriesObject.data = all;
			   seriesObject.name = "전체";
			   seriesArray.push(seriesObject);

			   //남 세팅
			   seriesObject = {};
			   seriesObject.data = male;
			   seriesObject.name = "남";
			   seriesArray.push(seriesObject);

			   //여 세팅
			   seriesObject = {};
			   seriesObject.data = female;
			   seriesObject.name = "여";
			   seriesArray.push(seriesObject);

			   setHiChart(seriesArray, time, xAxisText);

		   }

		},
		error : function(e) {
			console.error('ajax 에러: ' + e.status);
		}
	});

}


function setHiChart(chartData, xAxisTime, xAxisText) {

    Highcharts.chart('containers', {
	    chart: {
	        type: 'spline'
	    },
	    title: {
	        text: 'Breeze 통계'
	    },

	    subtitle: {
	        text: ''
	    },
        xAxis: {
            categories: xAxisTime
 	       , title: {
	            text: xAxisText
	        }
        },
	    yAxis: {
	        title: {
	            text: 'Number of Sensor'
	        },
	     //   min: 0,
        	labels: {
	            formatter: function () {
	                return this.value + '';
	            }
        	}
	    },
//	    tooltip: {
//	        headerFormat: '<b>{point.x}</b><br>',
//	        pointFormat:  '{point.y}'+''
//	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'middle'
	    },

	    plotOptions: {
	        series: {
	            label: {
	                connectorAllowed: false
	            }
	        }
	    },

	    series: chartData,
	    responsive: {
	        rules: [{
	            condition: {
	                maxWidth: 500
	            },
	            chartOptions: {
	                legend: {
	                    layout: 'vertical',
	                    align: 'center',
	                    verticalAlign: 'middle'
	                }
	            }
	        }]
	    }

	});
}


