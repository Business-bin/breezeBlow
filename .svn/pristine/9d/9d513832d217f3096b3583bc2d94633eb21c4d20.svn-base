/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	console.log("poplist");
	var now = new Date();
	if($("#monthfrom").val() == ""){
		$("#monthfrom").val(getStartDate(365));
		$("#monthto").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
	}

	$("#monthfrom").datepicker({
		 changeMonth: true,
		onSelect: function(dateText , inst){
			var dateArr = dateText.split("/");
			year = dateArr[2];
			month = dateArr[0];
			day = dateArr[1];
		}
	});
	$("#monthto").datepicker({
		 changeMonth: true,
		onSelect: function(dateText , inst){
			var dateArr = dateText.split("/");
			year = dateArr[2];
			month = dateArr[0];
			day = dateArr[1];
		}
	});

	//초기화 그리드
	grid.fn_initLoadGrid();
	grid.fn_SearchGrid();
	//pop목록 조회
    $("#sarchBtn").click(function(){

    	grid.fn_SearchGrid();
    });

    //신규 생성
    $("#newBtn").click(function(){
    	location.href = "/etc/popNew";
    });
});

/********************************************************************
Name   : 조회 체크
Desc   :
Param  :
********************************************************************/
function validation(){

}

/********************************************************************
Name   : 그리드
Desc   :
Param  :
********************************************************************/
var grid = {
	fn_initLoadGrid:function(){
		$( "#pop_list" ).jqGrid( 'GridUnload' );
		$("#pop_list").jqGrid({
			caption:"팝업리스트"
			,datatype 	    : 'clientside'
			,height:500
			,width:500
			,rowNum:10
			,rowList:[10,20,30]
			,rownumbers : true
			,viewrecords: true
			,colNames:["POP_SQ", "제목","시작일자","종료일자","현재상태"]
			,colModel:[
		          {name:"POP_SQ", 	index:"POP_SQ" , 		sortable:true,	width:0,		align:'center' ,hidden:true},
		          {name:"POP_NM", 	index:"POP_NM", 	sortable:true,	width:100,		align:'center'},
		          {name:"BEGIN_DTTM", 	index:"BEGIN_DTTM", 	sortable:true,	width:30,		align:'center'},
		          {name:"END_DTTM", 	index:"END_DTTM", 	sortable:false,	width:30,		align:'center'},
		          {name:"STAT", 	index:"STAT", 	sortable:true,	width:30,		align:'center'}
		           ]
			,pager:"#pager"
	        ,sortname     : 'POP_SQ'     //초기화 될때 sort할 컬럼을 지정
	        ,sortorder    : 'desc'       //초기화정렬방법
			,jsonReader:{ repeatitems:false
				, id:"POP_SQ"
				, root: "rows"
				, page : "page"
				, total : "total"
				,records : "records" }
			,gridComplete : function(data,status) {}
			, recordpos:'right'
			,gridview: true
			,emptyrecords :" 등록된 데이터가 없습니다."
	    	,loadError:function(xhr, status, error) {
	    	}
	    	,onSelectRow   : function(rowid, status, e) {
		     }
		}).trigger('reloadGrid');
	}
	,fn_SearchGrid:function(){
		var sDate = $('#monthfrom').val().replace(/-/g,'');
		var eDate = $('#monthto').val().replace(/-/g,'');
		if($("#nCal").prop("checked") == true){
			sDate = "";
			eDate = "";
		}
		$( "#pop_list" ).jqGrid( 'GridUnload' );
		$("#pop_list").jqGrid({
			url:"/etc/popListSearch"
			,caption:"pop리스트"
			,datatype 	    : 'json'
		    ,mtype         : 'POST'
	    	, postData	  : {
	    		R_FROMDT : sDate,
	    		R_TODT : eDate,
	    		R_ADDR_1 : $("#sido option:selected").text() == '전체' ? '' : $("#sido option:selected").text(),
	    		R_ADDR_2 : $("#gugun option:selected").text() == '전체' ? '' :  $("#gugun option:selected").text(),
	    		R_ADDR_3 : $("#dong option:selected").text() == '전체' ? '' : $("#dong option:selected").text(),
	    		R_BTB_SQ : $('#btbsnm').val() == '000' ? '' : $('#btbsnm').val(),
	    		R_MOBILE_TYPE : $('#mtype').val() == '000' ? '' : $('#mtype').val() ,
	    		R_PROD : $('#prod').val() == '000' ? '' : $('#prod').val(),
	    		R_SEARCH_TYPE : $('#searchType').val(),
	    		R_KEYWORD: $('#searStr').val()
	        }
			,height:500
			,width:500
			,rowNum:10
			,rowList:[10,20,30]
			,rownumbers : true
			,viewrecords: true
			,colNames:["POP_SQ", "제목","시작일자","종료일자","현재상태"]
			,colModel:[
		          {name:"POP_SQ", 	index:"POP_SQ" , 		sortable:true,	width:0,		align:'center' ,hidden:true},
		          {name:"POP_NM", 	index:"POP_NM", 	sortable:true,	width:100,		align:'center'},
		          {name:"BEGIN_DTTM", 	index:"BEGIN_DTTM", 	sortable:true,	width:30,		align:'center'},
		          {name:"END_DTTM", 	index:"END_DTTM", 	sortable:false,	width:30,		align:'center'},
		          {name:"STAT_NM", 	index:"STAT_NM", 	sortable:true,	width:30,		align:'center'}
		          ]
			,pager:"#pager"
	        ,sortname     : 'POP_SQ'     //초기화 될때 sort할 컬럼을 지정
	        ,sortorder    : 'desc'       //초기화정렬방법
			,jsonReader:{ repeatitems:false
				, id:"POP_SQ"
				, root: "rows"
				, page : "page"
				, total : "total"
				,records : "records" }

			,gridComplete : function(data,status) {
				var tcnt = jQuery("#pop_list").jqGrid('getGridParam','records');
				$("#tcnt").text("총 "+tcnt+"개");
				// 그리드 데이터의 ID 가져오기
				var ids = $('#pop_list').jqGrid('getDataIDs');
				// 그리드 데이터 가져오기
				var gridData = $("#pop_list").jqGrid('getRowData');
				// 데이터 확인후 색상 변경
				for (var i = 0; i < gridData.length; i++) {
					if (gridData[i].STAT == '004') {
						$("#"+ids[i]).css("background", "#485675");
				   }
				}
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
	    	}
	    	,ondblClickRow:function(rowid,iRow,iCol,e){
	    		var ret = jQuery("#pop_list").jqGrid('getRowData',rowid);
	    		goDet(ret.POP_SQ);
	    	}
		}).trigger('reloadGrid');

		$("#pop_list").jqGrid(
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
Name   : 팝업상세
Desc   :
Param  :
********************************************************************/
function goDet(popSq){
	$("#R_POP_SQ").val(popSq);
	$("#poupform").submit();
}

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