/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	console.log("noticeList");
	if($("#start_dt").val() == ""){
		$('#start_dt').val(getStartDate(365));
		$('#end_dt').val($.datepicker.formatDate('yy-mm-dd', new Date()));
	}

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

	$("#keyword").keypress(function(e) {
	   if(e.keyCode==13) {
		   grid.fn_SearchGrid('');
	   }
	});


	//초기화 그리드
	grid.fn_initLoadGrid();
	grid.fn_SearchGrid($("#stat").val());


    $("#btn_sch").click(function(){
    	$("#stat").val("");
    	grid.fn_SearchGrid('');
    });
    //전체
    $("#btn_sch1").click(function(){
    	$("#stat").val("");
    	grid.fn_SearchGrid('');
    });
    //AS 접수대기중
    $("#btn_sch2").click(function(){
    	$("#stat").val("01");
    	grid.fn_SearchGrid('01');
    });
    //AS 처리중
    $("#btn_sch3").click(function(){
    	$("#stat").val("02");
    	grid.fn_SearchGrid('02');
    });
    //AS 처리완료
    $("#btn_sch4").click(function(){
    	$("#stat").val("03");
    	grid.fn_SearchGrid('03');
    });
    //AS 배송중
    $("#btn_sch5").click(function(){
    	$("#stat").val("04");
    	grid.fn_SearchGrid('04');
    });
    //AS 배송완료
    $("#btn_sch6").click(function(){
    	$("#stat").val("05");
    	grid.fn_SearchGrid('05');
    });

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

/********************************************************************
Name   : jqGrid
Desc   : AS 목록조회
Param  :
********************************************************************/
var grid = {
		fn_initLoadGrid:function(){
			$("#as_list").jqGrid('GridUnload');
			$("#as_list").jqGrid({
				caption:"AS 목록"
				,datatype 	    : 'clientside'
				,height:500
				,width:500
				,rowNum:10
				,rowList:[10,20,30]
				,rownumbers : false
				,viewrecords: true
				,colNames:["번호","신청자명","아이디","AS제품","연락처","현재상태","등록일시"]
				,colModel:[
			          {name:"ROWNUM", 	        index:"ROWNUM" , 	    sortable:true,	width:30,		align:'center'},
			          {name:"AS_REQ_NM", 	    index:"AS_REQ_NM", 	    sortable:true,	width:30,		align:'center'},
			          {name:"AS_REQ_EMAIL", 	index:"AS_REQ_EMAIL", 	sortable:true,	width:30,		align:'center'},
			          {name:"AS_REP_CP_NM", 	index:"AS_REP_CP_NM", 	sortable:true,	width:30,		align:'center'},
			          {name:"AS_REQ_HP", 	    index:"AS_REQ_HP", 	    sortable:true,	width:30,		align:'center'},
			          {name:"STAT_NM", 	        index:"STAT_NM", 	    sortable:true,	width:30,		align:'center'},
			          {name:"REG_DTTM1", 	    index:"REG_DTTM1", 	    sortable:true,	width:30,		align:'center'}
			          ]
				,pager:"#pager"
		        ,sortname     : 'REG_DTTM1'  //초기화 될때 sort할 컬럼을 지정
		        ,sortorder    : 'desc'       //초기화정렬방법
				,jsonReader:{ repeatitems:false
					, id:"ROWNUM"
					, root: "rows"
					, page : "page"
					, total : "total"
					,records : "records" }

				,gridComplete : function(data,status) {
				}
				,recordpos:'right'
				,gridview: true
				,emptyrecords :" 등록된 데이터가 없습니다."
		    	,loadError:function(xhr, status, error) {
		    	}
		    	,onSelectRow   : function(rowid, status, e) {
			     }

			}).trigger('reloadGrid');
		}
		,fn_SearchGrid:function(stat){

		    if(!dayDiffCheck($('#start_dt').val(),$('#end_dt').val(),'-')){
			    return false ;
		    };

			var val = $("#category option:selected").val();
			$('#category').val(val);

			if($('#start_dt').val()==''){
				alert('등록일을 입력하세요.');
				$('#start_dt').focus();
			}else if ($('#end_dt').val() == '') {
				alert('등록일을 입력하세요.');
				$('#end_dt').focus();
			}else{
				var sDate = $('#start_dt').val();
				var eDate = $('#end_dt').val();
				if($("#nCal").prop("checked") == true){
					sDate = "";
					eDate = "";
				}
				$("#as_list").jqGrid( 'GridUnload' );
				$("#as_list").jqGrid({
					url:"/board/asListProc"
					,caption:"관리자 목록"
					,datatype 	   : 'json'
				    ,mtype         : 'POST'
			    	, postData	   : {
			        	start_dt   : sDate,
			        	end_dt     : eDate,
			        	category   : $('#category').val(),
			        	keyword    : $('#keyword').val(),
			        	stat       : stat

			        }
					,height:500
					,width:500
					,rowNum:10
					,rowList:[10,20,30]
					,rownumbers : false
					,viewrecords: true
					,colNames:["고유번호","번호","신청자명","아이디","AS제품","연락처","현재상태","등록일시"]
					,colModel:[
						  {name:"AS_REQ_SQ", 	    index:"AS_REQ_SQ" , 	sortable:true,	width:30,		align:'center',  hidden:true},
				          {name:"ROWNUM", 	        index:"ROWNUM" , 	    sortable:true,	width:30,		align:'center'},
				          {name:"AS_REQ_NM", 	    index:"AS_REQ_NM", 	    sortable:true,	width:30,		align:'center'},
				          {name:"AS_REQ_EMAIL", 	index:"AS_REQ_EMAIL", 	sortable:true,	width:30,		align:'center'},
				          {name:"AS_REP_CP_NM", 	index:"AS_REP_CP_NM", 	sortable:true,	width:30,		align:'center'},
				          {name:"AS_REQ_HP", 	    index:"AS_REQ_HP", 	    sortable:true,	width:30,		align:'center'},
				          {name:"STAT_NM", 	        index:"STAT_NM", 	    sortable:true,	width:30,		align:'center'},
				          {name:"REG_DTTM1", 	    index:"REG_DTTM1", 	    sortable:true,	width:30,		align:'center'}
				          ]
					,pager:"#pager"
			        ,sortname     : 'REG_DTTM1'     //초기화 될때 sort할 컬럼을 지정
			        ,sortorder    : 'desc'       //초기화정렬방법
					,jsonReader:{ repeatitems:false
						, id:"ROWNUM"
						, root: "rows"
						, page : "page"
						, total : "total"
						,records : "records" }

					,gridComplete : function(data,status) {
						var val = jQuery("#as_list").jqGrid('getGridParam','records');
						$("#totalCnt").html(val);
					}
					,recordpos:'right'
					,gridview: true
					,emptyrecords :" 등록된 데이터가 없습니다."
			    	,loadError:function(xhr, status, error) {
			    	  alert(status + " : " +error);
			    	  console.log(status);
			    	  console.log(xhr);
			    	  console.log(error);
			    	}
			    	,onSelectRow   : function(rowid, status, e) {
				     }
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

		}
	}


/********************************************************************
Name   : getFirstDate
Desc   : 현재월의 1일 가져오기
Param  :
********************************************************************/
function getFirstDate(){
	var date = new Date();
	var year = date.getFullYear();                 //yyyy
	var month = (1 + date.getMonth());             //M
	month = month >= 10 ? month : '0' + month;     // month 두자리로 저장
//	var day = date.getDate();                      //d
//	day = day >= 10 ? day : '0' + day;             //day 두자리로 저장
	return  year + '-' + month + '-' + '01';
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



