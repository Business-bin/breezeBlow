/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){
	console.log("main");

	getGrapData();
	grid.fn_MemGrid();
	grid.fn_AsGrid();
	grid.fn_QnaGrid();
	if($('#s_adm_class').val() == 3 || $('#s_adm_class').val() == 4){
		$('#asDiv').hide();
	}
});

/********************************************************************
Name   : 그래프 데이터 조회
Desc   :
Param  :
********************************************************************/
function getGrapData(){

	$.ajax(
		{async : true
			, url: '/main/getMainGrape'
			, dataType: 'JSON'
			, type: 'POST'
			, data: {}
			, success: function(data) {

				setHiChart(data);
				setHiChart2(data);
			}
			,complete : function(data) {
			}
			, error: function(xhr, ajaxOptions, thrownError) {

			}
		});
}


/********************************************************************
Name   : 그리드
Desc   :
Param  :
********************************************************************/
var grid = {
	fn_MemGrid:function(){

		$("#mem_list" ).jqGrid( 'GridUnload' );
		$("#mem_list").jqGrid({
			url:"/main/memList"
			,caption:"회원리스트"
			,datatype 	    : 'json'
			,mtype         : 'POST'
			, postData	  : {
			}
			,height:150
			,width:500
			,rowNum:10
			,rowList:[10,20,30]
			,rownumbers : true
			,viewrecords: true
			,colNames:["고유번호","회원명", "거주지역","사용기기","가입일"]
			,colModel:[
			{name:"MEM_SQ", 	index:"MEM_SQ", 	sortable:false,	width:100,	  hidden:true ,	align:'center'},
			{name:"NM", 	index:"NM", 	sortable:false,	width:100,		align:'center'},
			{name:"AREA", 	index:"AREA", 	sortable:false,	width:100,		align:'center'},
			{name:"MOBILE_TYPE", 	index:"MOBILE_TYPE", 	sortable:false,	width:100,		align:'center'},
			{name:"REG_DTTM", 	index:"REG_DTTM", 	sortable:false,	width:100,		align:'center'}
			]
			,pager:"#pager"
	        ,sortname     : 'NM'     //초기화 될때 sort할 컬럼을 지정
	        ,sortorder    : 'asc'       //초기화정렬방법ㄴ
	        ,jsonReader:{ repeatitems:false
	        	, id:"SMS_SQ"
	        	, root: "rows"
	        	, page : "page"
	        	, total : "total"
	        	,records : "records" }

	        	,gridComplete : function(data,status) {

	        	}
	        	, recordpos:'right'
	        	,gridview: true
	        	,emptyrecords :" 등록된 데이터가 없습니다."
	        	,loadError:function(xhr, status, error) {

	        		console.log(status);
	        		console.log(xhr);
	        		console.log(error);
	        	}
	        	,onSelectRow   : function(rowid, status, e) {
	        		var ret = jQuery("#mem_list").jqGrid('getRowData',rowid);
	        	}
	        	,ondblClickRow  : function(rowid, status, e) {
	        		var ret = jQuery("#mem_list").jqGrid('getRowData', rowid);
	        		memDet(ret.MEM_SQ);
	        	}
	        }).trigger('reloadGrid');

		$("#mem_list").jqGrid(
			"navGrid",
			"#pager",
			{search:false, edit:false, add:false, del:false},
			{closeAfterEdit:false, reloadAfterSubmit:false},
			{closeAfterAdd:false, reloadAfterSubmit:false},
			{reloadAfterSubmit:false}
			);
	},
	fn_AsGrid:function(){

		$("as_list" ).jqGrid( 'GridUnload' );
		$("#as_list").jqGrid({
			url:"/main/asList"
			,caption:"as접수목록"
			,datatype 	    : 'json'
			,mtype         : 'POST'
			, postData	  : {
			}
			,height:150
			,width:500
			,rowNum:10
			,rowList:[10,20,30]
			,rownumbers : true
			,viewrecords: true
			,colNames:["고유번호","회원명", "제품 S/N","등록일시","상태"]
			,colModel:[
			{name:"AS_REQ_SQ", 	index:"AS_REQ_SQ", 	sortable:false,	width:100,	  hidden:true ,	align:'center'},
			{name:"NM", 	index:"NM", 	sortable:false,	width:100,		align:'center'},
			{name:"AS_REQ_CP_SN", 	index:"AS_REQ_CP_SN", 	sortable:false,	width:100,		align:'center'},
			{name:"REG_DTTM", 	index:"REG_DTTM", 	sortable:false,	width:100,		align:'center'},
			{name:"STAT_NM", 	index:"STAT_NM", 	sortable:false,	width:100,		align:'center'}
			]
			,pager:"#pager"
	        ,sortname     : 'SMS_SQ'     //초기화 될때 sort할 컬럼을 지정
	        ,sortorder    : 'asc'       //초기화정렬방법
	        ,jsonReader:{ repeatitems:false
	        	, id:"AS_REQ_SQ"
	        	, root: "rows"
	        	, page : "page"
	        	, total : "total"
	        	,records : "records" }

	        	,gridComplete : function(data,status) {

	        	}
	        	, recordpos:'right'
	        	,gridview: true
	        	,emptyrecords :" 등록된 데이터가 없습니다."
	        	,loadError:function(xhr, status, error) {

	        		console.log(status);
	        		console.log(xhr);
	        		console.log(error);
	        	}
	        	,onSelectRow   : function(rowid, status, e) {
		    /*	var ret = jQuery("#as_list").jqGrid('getRowData',rowid);
		    	//pouup(ret.SMS_SQ);
		    */		     }
		    ,ondblClickRow: function (rowid, iRow, iCol, e) {
		    	var ret = jQuery("#as_list").jqGrid('getRowData',rowid);
			    	//상세보기
			    	goView(ret.AS_REQ_SQ);
			    }
			}).trigger('reloadGrid');

		$("#as_list").jqGrid(
			"navGrid",
			"#pager",
			{search:false, edit:false, add:false, del:false},
			{closeAfterEdit:false, reloadAfterSubmit:false},
			{closeAfterAdd:false, reloadAfterSubmit:false},
			{reloadAfterSubmit:false}
			);
	}

	,
	fn_QnaGrid:function(){
		$("qna_list" ).jqGrid( 'GridUnload' );
		$("#qna_list").jqGrid({
			url:"/main/qnaList"
			,caption:"문의접수목록"
			,datatype 	    : 'json'
			,mtype         : 'POST'
			,postData	  : {}
			,height:150
			,width:500
			,rowNum:10
			,rowList:[10,20,30]
			,rownumbers : true
			,viewrecords: true
			,colNames:["고유번호","회원명", "카테고리","접수일자","상태"]
			,colModel:[
			{name:"BD_SQ", 	    index:"BD_SQ" , 	sortable:true,	width:30,		align:'center',  hidden:true},
			{name:"NM", 	index:"NM", 	sortable:false,	width:100,		align:'center'},
			{name:"CATEGORY", 	index:"CATEGORY", 	sortable:false,	width:100,		align:'center'},
			{name:"REG_DTTM1", 	index:"REG_DTTM1", 	sortable:false,	width:100,		align:'center'},
			{name:"BDANSYN", 	index:"BDANSYN", 	sortable:false,	width:100,		align:'center'}
			]
			,pager:"#pager"
	        ,sortname     : 'SMS_SQ'     //초기화 될때 sort할 컬럼을 지정
	        ,sortorder    : 'asc'       //초기화정렬방법
	        ,jsonReader:{ repeatitems:false
	        	, id:"SMS_SQ"
	        	, root: "rows"
	        	, page : "page"
	        	, total : "total"
	        	,records : "records" }

	        	,gridComplete : function(data,status) {

	        	}
	        	, recordpos:'right'
	        	,gridview: true
	        	,emptyrecords :" 등록된 데이터가 없습니다."
	        	,loadError:function(xhr, status, error) {

	        		console.log(status);
	        		console.log(xhr);
	        		console.log(error);
	        	}
	        	,onSelectRow   : function(rowid, status, e) {
	        		var ret = jQuery("#qna_list").jqGrid('getRowData',rowid);
		    	//pouup(ret.SMS_SQ);
		    }
		    ,ondblClickRow: function (rowid, iRow, iCol, e) {

		    	var ret = jQuery("#qna_list").jqGrid('getRowData',rowid);
		    	//상세보기
		    	goView2(ret.BD_SQ);
		    }
		}).trigger('reloadGrid');

		$("#qna_list").jqGrid(
			"navGrid",
			"#pager",
			{search:false, edit:false, add:false, del:false},
			{closeAfterEdit:false, reloadAfterSubmit:false},
			{closeAfterAdd:false, reloadAfterSubmit:false},
			{reloadAfterSubmit:false}
			);
	}
}

/********************************************************************
Name   : 그래프 생성
Desc   :
Param  :
********************************************************************/
function setHiChart(data){
	var categoriesdata = [];
	var seriesdata = [];
	if(data.memList != null){
		for(var i=0;i <data.memList.length ; i++){
			console.log(data.memList[i].DATE);
			console.log(data.memList[i].CNT);
			categoriesdata.push(data.memList[i].DATE);
			seriesdata.push(data.memList[i].CNT);
		}
	}

	Highcharts.chart('containers', {
		credits: {
			enabled: false
		},
		chart: {
			type: 'spline'
		},
		title: {
			text: '회원가입 현황'
		},
		subtitle: {
			text: ''
		},
		xAxis: {
			categories: categoriesdata
			, title: {
				text: '날짜'
			}
		},
		yAxis: {
			title: {
				text: ''
			},
			min: 0
		},
		tooltip: {
			headerFormat: '<b>{point.x}</b><br>',
			pointFormat:  '{point.y}'+'명'
		},
		plotOptions: {
			spline: {
				marker: {
					enabled: true
				}
			}
		},
		series: [{
			name: '유저가입수',
			data:seriesdata
		}]
	});
}


/********************************************************************
Name   : 파이 그래프 생성
Desc   :
Param  :
********************************************************************/
function setHiChart2(data){
	var categoriesdata = [];
	var seriesdata = [];
	Highcharts.chart('containers2', {
		credits: {
			enabled: false
		},
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false,
			type: 'pie'
		},
		title: {
			text: 'OS별 App Download 통계'
		},
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true
				},
				showInLegend: true
			}
		},
		series: [{
			name: '기기비율:',
			colorByPoint: true,
			data: [{
				name: 'IOS [' + data.appList[0].IOSCNT + ']건',
				y: data.appList[0].IOSCNT,
				sliced: true,
				selected: true
			}, {
				name: 'ANDROID [' + data.appList[0].ANDROIDCNT + ']건',
				y: data.appList[0].ANDROIDCNT,
				sliced: true,
				selected: true
			}]
		}]
	});
}



/********************************************************************
Name   : goView
Desc   : 상세보기
Param  :
********************************************************************/
function goView(val){
	$('#as_req_sq').val(val);
	$('#frm').attr('action', '/board/asView');
	$("#frm").submit();
}

/********************************************************************
Name   : memDet
Desc   : 회원상세로 이동
Param  :
********************************************************************/
function memDet(sq) {
	$("#memSq").val(sq);
	$("#gubun").val("det");
	$("#detForm").submit();
}

/********************************************************************
Name   : goView2
Desc   : qna 이동
Param  :
********************************************************************/
function goView2(val){
//	alert(val);
$('#bd_sq').val(val);
$('#bdForm').attr('action', '/board/qnaView');
$("#bdForm").submit();
}
