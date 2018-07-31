/********************************************************************
Name   : 전역 환경 설정
Desc   :
Param  :
********************************************************************/
$(function() {
	if($("#s_adm_class").val() == 3 || $("#s_adm_class").val() == 4){
		$(".tabmenu li").eq(2).hide();
		$(".tabmenu li").eq(3).hide();
	}
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
	$("#startDate").val(getStartDate(365));
	$("#endDate").val(year + "-" + month + "-" + day);

	/* 회원 상세로 이동 */
	$("#detMem").click(function(){
		location.href = "/mem/memDet?memSq=" + $("#memSq").val();
	});

	/* 회원로그 검색 */
	$("#logSearch").click(function(){
		if(!dayDiffCheck($('#startDate').val(),$('#endDate').val(),'-')){
			return false ;
		};
		if(!dateDiffDay($('#startDate').val(),$('#endDate').val(),'-',365)){
			return false ;
		};
		grid.fn_SearchGrid();
	});

	/* 회원리스트 헤더 그리드 */
	grid.fn_initLoadGrid();
	grid.fn_SearchGrid();
	$(".tabmenu>li").eq(0).addClass("active");
});

/* 회원로그 그리드 */
var grid = {
	fn_initLoadGrid : function() {
		$("#log_list").jqGrid('GridUnload');
		$("#log_list").jqGrid({
					caption : "로그리스트",
					datatype : 'clientside',
					height : 500,
					width : 500,
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					rownumbers : true,
					viewrecords : true,
					colNames : [ "순번", "활동일시", "접속IP", "사용기기", "OS"],
					colModel : [ {
						name : "LOG_USER_SQ", index : "LOG_USER_SQ", sortable : true, width : 0, align : 'center', hidden : true
					}, {
						name : "REG_DTTM", index : "REG_DTTM", sortable : true, width : 30, align : 'center'
					}, {
						name : "IP", index : "IP", sortable : true, width : 30, align : 'center'
					}, {
						name : "MODEL", index : "MODEL", sortable : true, width : 30, align : 'center'
					}, {
						name : "OS", index : "OS", sortable : true, width : 30, align : 'center'
					}],
					pager : "#pager",
					sortname : 'LOG_USER_SQ', // 초기화 될때 sort할 컬럼을 지정
					sortorder : 'asc', // 초기화정렬방법
					jsonReader : {
						repeatitems : false, id : "LOG_USER_SQ", root : "rows", page : "page", total : "total", records : "records"
					},
					gridComplete : function(data, status) {
					},
					recordpos : 'right',
					gridview : true,
					emptyrecords : " 등록된 데이터가 없습니다.",
					loadError : function(xhr, status, error) {
					},
					onSelectRow : function(rowid, status, e) {
					}
				}).trigger('reloadGrid');
	},
	fn_SearchGrid : function() {
		$("#log_list").jqGrid('GridUnload');
		$("#log_list").jqGrid({
					url : "/mem/searchLog",
					caption : "로그리스트",
					datatype : 'json',
					mtype : 'POST',
					postData : {
						memSq : $("#memSq").val(),
						memEmail : $("#memEmail").val(),
						startDate : $('#startDate').val(),
						endDate : $('#endDate').val()
					},
					height : 500,
					width : 500,
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					rownumbers : true,
					viewrecords : true,
					colNames : [ "순번", "활동일시", "접속IP", "사용기기", "OS"],
					colModel : [ {
						name : "LOG_USER_SQ", index : "LOG_USER_SQ", sortable : true, width : 0, align : 'center', hidden : true
					}, {
						name : "REG_DTTM", index : "REG_DTTM", sortable : true, width : 30, align : 'center'
					}, {
						name : "IP", index : "IP", sortable : true, width : 30, align : 'center'
					}, {
						name : "MODEL", index : "MODEL", sortable : true, width : 30, align : 'center'
					}, {
						name : "OS", index : "OS", sortable : true, width : 30, align : 'center'
					}],
					pager : "#pager",
					sortname : 'LOG_USER_SQ', // 초기화 될때 sort할 컬럼을 지정
					sortorder : 'asc', // 초기화정렬방법
					jsonReader : {
						repeatitems : false, id : "LOG_USER_SQ", root : "rows", page : "page", total : "total", records : "records"
					},
					gridComplete : function(data, status) {
						var tcnt = jQuery("#log_list").jqGrid('getGridParam','records');
						$("#tcnt").text("총 "+tcnt+"개");
					},
					recordpos : 'right',
					gridview : true,
					emptyrecords : " 등록된 데이터가 없습니다.",
					loadError : function(xhr, status, error) {
						alert(status + " : " + error);
						console.log(status);
						console.log(xhr);
						console.log(error);
					},
					onSelectRow : function(rowid, status, e) {
						var ret = jQuery("#log_list").jqGrid('getRowData', rowid);
						memDet(ret.MEM_SQ);
					}
				}).trigger('reloadGrid');
		$("#log_list").jqGrid("navGrid", "#pager", {
			search : false,
			edit : false,
			add : false,
			del : false
		}, {
			closeAfterEdit : false,
			reloadAfterSubmit : false
		}, {
			closeAfterAdd : false,
			reloadAfterSubmit : false
		}, {
			reloadAfterSubmit : false
		});
	}
}

/* 시작일 계산  */
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
