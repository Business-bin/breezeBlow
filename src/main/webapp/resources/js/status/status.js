
/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){
	// 최초 서울 데이터로 출력
	getData("서울");

	// 지역 클릭 이벤트
	$("area").click(function(){
    	var loc = $(this).attr("alt");
    	getData(loc);
    });

	// 10분마다 현재 지역 데이터 리프레시
	var interval = setInterval(function() {
		getData($("#curLoc").text());
	}, 600000);
});

function getData(loc){
	$.ajax({
		url : "/status/locstatus",
		type : "POST",
		data : {
			location : loc
		},
		success : function(result){
			var r = result.model.pMap;
			if(r.DCOUNT>0){
    			$("#dCount").text(r.DCOUNT);
    			$("#curTime").text(r.CURTIMES);
    			$("#curLoc").text(loc);
    			var t = "";
    			t = "<td>온도</td>";
    			t += "<td>"+r.TEMP_MIN+"</td>";
    			t += "<td>"+r.TEMP_AVG+"</td>";
    			t += "<td>"+r.TEMP_MAX+"</td>";
    			$("#temp").html(t);
    			t = "<td>습도</td>";
    			t += "<td>"+r.HUMI_MIN+"</td>";
    			t += "<td>"+r.HUMI_AVG+"</td>";
    			t += "<td>"+r.HUMI_MAX+"</td>";
    			$("#humi").html(t);
    			t = "<td>HCHO</td>";
    			t += "<td>"+r.HCHO_MIN+"</td>";
    			t += "<td>"+r.HCHO_AVG+"</td>";
    			t += "<td>"+r.HCHO_MAX+"</td>";
    			$("#hcho").html(t);
    			t = "<td>CO2</td>";
    			t += "<td>"+r.CO2_MIN+"</td>";
    			t += "<td>"+r.CO2_AVG+"</td>";
    			t += "<td>"+r.CO2_MAX+"</td>";
    			$("#co2").html(t);
    			t = "<td>CO</td>";
    			t += "<td>"+r.CO_MIN+"</td>";
    			t += "<td>"+r.CO_AVG+"</td>";
    			t += "<td>"+r.CO_MAX+"</td>";
    			$("#co").html(t);
    			t = "<td>PM2.5</td>";
    			t += "<td>"+r.PM25_MIN+"</td>";
    			t += "<td>"+r.PM25_AVG+"</td>";
    			t += "<td>"+r.PM25_MAX+"</td>";
    			$("#pm25").html(t);
    			t = "<td>ATOPY</td>";
    			t += "<td>"+r.ATOPY_MIN+"</td>";
    			t += "<td>"+r.ATOPY_AVG+"</td>";
    			t += "<td>"+r.ATOPY_MAX+"</td>";
    			$("#atopy").html(t);
			}else{
				alert("데이터 없음");
			}
		},
		error : function(){

		}
	});
}

