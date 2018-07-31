/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	console.log("adminLogList");

	/* 달력세팅 */
	$('#start_dt').val(getStartDate(365));
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
	/* 달력세팅 */

	// enter key 검색
	$("#keyword").keypress(function(e) {
	   if(e.keyCode==13) {
		   grid.fn_SearchGrid();
	   }
	});

	//초기화 그리드
	grid.fn_initLoadGrid();
	grid.fn_SearchGrid();

	// 돋보기 버튼 클릭 이벤트
    $("#btn_sch").click(function(){
    	grid.fn_SearchGrid();
    });

});



/********************************************************************
Name   : getFirstDate
Desc   : 7일전 날짜 가져오기
Param  :
********************************************************************/
function getFirstDate(){
	var date = new Date();
	var year = date.getFullYear();                 //yyyy
	var month = (1 + date.getMonth());             //M
//	var month = date.getMonth();
	month = month >= 10 ? month : '0' + month;     // month 두자리로 저장
	var day = date.getDate();                      //d
	day = day >= 10 ? day : '0' + day;             //day 두자리로 저장
	return  year + '-' + month + '-' + day;
}

/********************************************************************
Name   : jqGrid
Desc   : 목록 조회
Param  :
********************************************************************/
var grid = {
		fn_initLoadGrid:function(){
			$("#admin_log_list").jqGrid('GridUnload');
			$("#admin_log_list").jqGrid({
				caption:"관리자 목록"
				,datatype 	    : 'clientside'
				,height:500
				,width:500
				,rowNum:10
				,rowList:[10,20,30]
				,rownumbers : false
				,viewrecords: true
				,colNames:["번호","아이디","관리자명","일시","활동내역","접속IP","접속환경"]
				,colModel:[
			          {name:"ROWNUM", 	    index:"ROWNUM" , 	sortable:true,	width:30,		align:'center'},
			          {name:"ADM_EMAIL", 	index:"ADM_EMAIL", 	sortable:true,	width:30,		align:'center'},
			          {name:"ADM_NM", 	    index:"ADM_NM", 	sortable:true,	width:30,		align:'center'},
			          {name:"DTTM", 	    index:"DTTM", 	    sortable:false,	width:30,		align:'center'},
			          {name:"ACT_CONT", 	index:"ACT_CONT", 	sortable:true,	width:30,		align:'center'},
			          {name:"IP", 	        index:"IP", 	    sortable:true,	width:30,		align:'center'},
			          {name:"CON_ENV", 	    index:"CON_ENV", 	sortable:true,	width:30,		align:'center'}
			          ]
				,pager:"#pager"
		        ,sortname     : 'DTTM'       //초기화 될때 sort할 컬럼을 지정
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
		,fn_SearchGrid:function(){

		    if(!dayDiffCheck($('#start_dt').val(),$('#end_dt').val(),'-')){
			    return false ;
		    };

		    if(!dateDiffDay($('#start_dt').val(),$('#end_dt').val(),'-',365)){
				return false ;
			};

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
				$("#admin_log_list").jqGrid( 'GridUnload' );
				$("#admin_log_list").jqGrid({
					url:"/admin/adminLogListProc"
					,caption:"관리자 로그 목록"
					,datatype 	   : 'json'
				    ,mtype         : 'POST'
			    	, postData	   : {
			        	start_dt   : sDate,
			        	end_dt     : eDate,
			        	category   : $('#category').val(),
			        	keyword    : $('#keyword').val(),

			        }
					,height:500
					,width:500
					,rowNum:10
					,rowList:[10,20,30]
					,rownumbers : false
					,viewrecords: true
					,colNames:["번호","아이디","관리자명","일시","활동내역","접속IP","접속환경"]
					,colModel:[
				          {name:"ROWNUM", 	    index:"ROWNUM" , 	sortable:false,	width:30,		align:'center'},
				          {name:"ADM_EMAIL", 	index:"ADM_EMAIL", 	sortable:true,	width:30,		align:'center'},
				          {name:"ADM_NM", 	    index:"ADM_NM", 	sortable:true,	width:30,		align:'center'},
				          {name:"DTTM1", 	    index:"DTTM1", 	    sortable:false,	width:30,		align:'center'},
				          {name:"ACT_CONT", 	index:"ACT_CONT", 	sortable:true,	width:30,		align:'center'},
				          {name:"IP", 	        index:"IP", 	    sortable:true,	width:30,		align:'center'},
				          {name:"CON_ENV", 	    index:"CON_ENV", 	sortable:true,	width:30,		align:'center'}
				          ]
					,pager:"#pager"
			        ,sortname     : 'DTTM'       //초기화 될때 sort할 컬럼을 지정
			        ,sortorder    : 'desc'       //초기화정렬방법
					,jsonReader:{ repeatitems:false
						, id:"ROWNUM"
						, root: "rows"
						, page : "page"
						, total : "total"
						,records : "records" }

					,gridComplete : function(data,status) {
						var val = jQuery("#admin_log_list").jqGrid('getGridParam','records');
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

				    	var ret = jQuery("#admin_log_list").jqGrid('getRowData',rowid);

				     }

				}).trigger('reloadGrid');

				$("#admin_log_list").jqGrid(
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
Name   : getStartDate
Desc   : 특정일 이전 날짜 가져오기
Param  :
********************************************************************/
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