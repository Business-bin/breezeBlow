/********************************************************************
Name   : ready                                                 
Desc   : 
Param  :            
********************************************************************/
$(document).ready(function(){

	//setHiChart();
	console.log("sample11");
	//alert("sample");
	showPaging(1,5);
	
	
	$('#btn_sch').click(function(){
		console.log("btn_sch");
		getList();
	});
	
	$('#btn_sch2').click(function(){
		console.log("btn_sch2");
		getList2();
	});
	
	$('#btn_sch3').click(function(){
		console.log("btn_sch3");
		getList3();
	});
	
	$('#addBtn').click(function(){
		console.log("addBtn");
		addManager();
	});
	
    $("#changepw").change(function(){
        if($("#changepw").is(":checked")){
        	$("#changepw").val('Y');
        }else{
        	$("#changepw").val('N');
        }
    });
    
	
});


function setHiChart(){
	Highcharts.chart('container', {

	    title: {
	        text: '테스트, 2010-2016'
	    },

	    subtitle: {
	        text: 'Source: 테스트'
	    },

	    yAxis: {
	        title: {
	            text: 'Number of Employees'
	        }
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'middle'
	    },

	    plotOptions: {
	        series: {
	            label: {
	                connectorAllowed: false
	            },
	            pointStart: 2010
	        }
	    },

	    series: [{
	        name: 'Installation',
	        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
	    }, {
	        name: 'Manufacturing',
	        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
	    }, {
	        name: 'Sales & Distribution',
	        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
	    }, {
	        name: 'Project Development',
	        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
	    }, {
	        name: 'Other',
	        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
	    }],

	    responsive: {
	        rules: [{
	            condition: {
	                maxWidth: 500
	            },
	            chartOptions: {
	                legend: {
	                    layout: 'horizontal',
	                    align: 'center',
	                    verticalAlign: 'bottom'
	                }
	            }
	        }]
	    }

	});
}

/********************************************************************
Name   :      getList                                            
Desc   : 
Param  :            
********************************************************************/
function getList(){
	console.log("testList11");
	$.ajax(
			{async : true
			,type : "POST"
			,url : "/sample/testList"
			,dataType : "json"
			,data: {ID : '1'}
			,success : function(jsonData) {
				console.log("success" + jsonData.name);
				console.log(jsonData);
				$('#test').val(jsonData.name);
				alert("test");
			}
			,error:	function(request,status,error){
				console.log("error");
				console.log(request);
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
	});
}

/********************************************************************
Name   :      addManager                                            
Desc   : 
Param  :            
********************************************************************/
function addManager(){
	
	
	var statusVal = $("input[type=radio][name=data_status]:checked").val();
	var groupVal = $("input[type=radio][name=usergroup]:checked").val();
	var levelVal = $("input[type=radio][name=userlevel]:checked").val();
	
	alert("statusVal=="+statusVal);
	alert("groupVal=="+groupVal);
	alert("levelVal=="+levelVal);
	alert("체크박스=="+$('#changepw').val());
	
	if($('#userid').val()==''){
		alert('E-mail을 입력하세요');
		$('#userid').focus();
	}else if ($('#username_eng').val() == '') {
		alert('영문명을 입력하세요');
		$('#username_eng').focus();
	}else if ($('#username_kr').val() == '') {
		alert('한글명을 입력하세요');
		$('#username_kr').focus();
	}else if ($('#mobile').val() == '') {
		alert('전화번호를 입력하세요');
		$('#mobile').focus();
	} else if(confirm("저장 하시겠습니까?") == true){
		$.ajax({
			url : '/sample/addManager',
			method : 'POST',
			dataType: 'JSON',
			data : {
				data_status      : statusVal,
				userid	         : $('#userid').val(),	        //E-mail
				changepw         : $('#changepw').val(),	    //Password
				username_eng 	 : $('#username_eng').val(),	//영문명
				username_kr		 : $('#username_kr').val(),		//한글명
				mobile	         : $('#mobile').val(),	        //전화번호
				groupVal		 : groupVal,		            //User Group
				levelVal         : levelVal,                    //User Level
			},
			success : function(jsonData) {
				
			//	alert("??");
				console.log("success>>" + jsonData.user_name);
				console.log("name>>" + jsonData.list.name);
				console.log(jsonData);
			    alert("저장 되었습니다.");
			//	window.location.replace("/sample/sample");
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});
		
	}else{
		return;
	}
}

//텍스트 푸시 전송 버튼
$('#textPushBtn').click(function(e) {
	if($('#pushCampTitle').val()==''){
		alert('관리용 제목을 입력하세요');
		$('#pushCampTitle').focus();
	}else if ($('#pushPopupTitle').val() == '') {
		alert('상태창 제목을 입력하세요');
		$('#pushPopupTitle').focus();
	}else if ($('#pushPopupContent').val() == '') {
		alert('팝업 내용을 입력하세요');
		$('#pushPopupContent').focus();
	}else if ($('#innerContent').val() == '') {
		alert('인앱 메시지 내용을 입력하세요');
		$('#innerContent').focus();
	} else if ( ($('#pushPopupContent').val().search("<script") != -1) 
				|| ($('#innerContent').val().search("<script") != -1)
				|| ($('#pushCampTitle').val().search("<script") != -1)
				|| ($('#pushPopupTitle').val().search("<script") != -1)
				|| ($('#smsContent').val().search("<script") != -1)) {
		alert("<script> 태그는 사용하실 수 없습니다.");
		return;
	} else if(confirm("푸시를 발송하시겠습니까?") == true){
		$.ajax({
			url : './push.do',
			method : 'POST',
			data : {
				pushCampTitle	 : $('#pushCampTitle').val(),	//푸시 캠페인 이름
				pushPopupTitle 	 : $('#pushPopupTitle').val(),	//푸시  팝업 제목
				targetType		 : $('#targetType').val(),		//푸시 대상자
				checkReTarget	 : $('#checkReTarget').val(),	//SMS 리타켓 여부
				smsContent		 : $('#smsContent').val(),		//SMS 내용
				pushPopupContent : $('#pushPopupContent').val(),//푸시 팝업 내용
				innerContent 	 : $('#innerContent').val(),	//푸시 인앱 내용
				pushType		 : 'text'
			},
			success : function(result) {
				console.log(result);
				window.location.replace("pushList.do");
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});
		
	}else{
		return;
	}
});



/********************************************************************
Name   :      getList2                                            
Desc   : 
Param  :            
********************************************************************/
function getList2(){
	console.log("testListAll");
	$.ajax({async : false
		, url: '/sample/testListAll'
		, dataType: 'JSON'
		, type: 'POST'
		, data: { 
			IDNO1 		: 'test1',
			IDNO2 		: 'test1',
			meberName 	: 'test1'
		}
		, success: function(data) {
			console.log("success");
			console.log(data);
		}
		,complete : function(data) {
			console.log("com");
		}
		, error: function(xhr, ajaxOptions, thrownError) {
			console.log(xhr);
			console.log(ajaxOptions);
			console.log(thrownError);
//			alert('', '', '오류가 발생했습니다.. [' + xhr.status + ']', true);
		}
	});
	
}



/********************************************************************
Name   :      getList3                                            
Desc   : 
Param  :            
********************************************************************/
function getList3(){
	console.log("testListAll");
	$.ajax({
		 url: '/sample/testList2'
		, dataType: 'JSON'
		, type: 'POST'
		, data: { 
			IDNO1 		: 'test1',
			IDNO2 		: 'test1',
			meberName 	: 'test1'
		}
		, success: function(data) {
			console.log("success");
			console.log(data);
			alert(data);
		}
		,complete : function(data) {
			console.log("com");
		}
		, error: function(xhr, ajaxOptions, thrownError) {
			console.log(xhr);
			console.log(ajaxOptions);
			console.log(thrownError);
//			alert('', '', '오류가 발생했습니다.. [' + xhr.status + ']', true);
		}
	});
	
}




function showPaging(page,totalPage){
	page = parseInt(page);
	totalPage = parseInt(totalPage);
	var paging = $('#paging');
	paging.html("");
	var defSpan = $(document.createElement('span'));
	var limit = 10;
	var st = Math.floor((page-1)/limit)*(limit)+1;
	var ed = Math.min(st+limit-1,totalPage);
	var spans = [];
	if(totalPage<=0){
		paging.html("NoPage");
		return;
	}


	var span = defSpan.clone();
	span.prop('page',1);
	span.text('처음');
	spans.push(span);

	var span = defSpan.clone();
	span.prop('page',(st-1)<=0?-1:(st-1));
	span.text('이전');
	spans.push(span);

	for(var i=st,m=ed;i<=m;i++){
		var span = defSpan.clone();
		span.prop('page',i);
		if(i == page){
			span.addClass('current');
		}
		span.text(i);
		spans.push(span);
	}

	var span = defSpan.clone();
	span.prop('page',(ed+1)>=totalPage?-1:(ed+1));
	span.text('다음');
	spans.push(span);

	var span = defSpan.clone();
	span.prop('page',totalPage);
	span.text('마지막');
	spans.push(span);
	
	for(var i=0,m=spans.length;i<m;i++){
		paging.append(spans[i]);
	}

}
function submitPaging(n){
	alert(n);
	return false;
	if(!selectedShForm){return false;}
	selectedShForm.page.value = n;
	if(selectedShForm.onsubmit()){
		selectedShForm.submit();
	}
}
function clickPaging(event,paging){
	alert(paging);
	event = $(event)[0];
	if(event.target && event.target.tagName =='SPAN' && event.target.page && event.target.page>0){
		submitPaging(event.target.page)

	}
	return false;
}