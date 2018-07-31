/********************************************************************
Name   : 전역 환경 설정
Desc   :
Param  :
********************************************************************/
$(function() {
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
	if($("#startDate").val() == ""){
		$("#startDate").val(getStartDate(365));
		$("#endDate").val(year + "-" + month + "-" + day);
	}
 	/* 회원 리스트 검색 */
	$("#tutSearch").click(function() {
		grid.fn_SearchGrid();
	});

	/* 회원리스트 헤더 그리드 */
	grid.fn_initLoadGrid();
	grid.fn_SearchGrid();

	/* 신규 등록 화면으로 이동 */
	$("#addTut").click(function(){
		location.href = "/app/tutorialDet?gubun=new";
	});

	/* 탭선택 */
	$(".tabmenu>li").eq(2).addClass("active");
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

/* 회원상세로 이동 */
function tutDet(sq) {
	$("#tutSq").val(sq);
	$("#gubun").val("det");
	$("#detForm").submit();
}

/* 정책리스트 그리드 */
var grid = {
	fn_initLoadGrid : function() {
		$("#tut_list").jqGrid('GridUnload');
		$("#tut_list").jqGrid({
					caption : "튜토리얼리스트",
					datatype : 'clientside',
					height : 301,
					width : 500,
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					/*rownumbers : true,*/
					viewrecords : true,
					colNames : [ "번호", "튜토리얼이름", "현재상태", "등록일시"],
					colModel : [ {
						name : "TUT_SQ", index : "TUT_SQ", sortable : true, width : 10, align : 'center'
					}, {
						name : "TUT_NM", index : "TUT_NM", sortable : true, width : 30, align : 'center'
					}, {
						name : "STATCODE", index : "STATCODE", sortable : true, width : 30, align : 'center'
					}, {
						name : "REG_DTTM", index : "REG_DTTM", sortable : true, width : 30, align : 'center'
					}],
					pager : "#pager",
					sortname : 'TUT_SQ', // 초기화 될때 sort할 컬럼을 지정
					sortorder : 'desc', // 초기화정렬방법
					jsonReader : {
						repeatitems : false, id : "TUT_SQ", root : "rows", page : "page", total : "total", records : "records"
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
		var sDate = $('#startDate').val();
		var eDate = $('#endDate').val();
		if($("#nCal").prop("checked") == true){
			sDate = "";
			eDate = "";
		}
		$("#tut_list").jqGrid('GridUnload');
		$("#tut_list").jqGrid({
					url : "/app/tutorialSearch",
					caption : "튜토리얼리스트",
					datatype : 'json',
					mtype : 'POST',
					postData : {
						startDate : sDate,
						endDate : eDate,
						search : $('#search').val()
					},
					height : 301,
					width : 500,
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					/*rownumbers : true,*/
					viewrecords : true,
					colNames : [ "번호", "튜토리얼이름", "현재상태", "등록일시"],
					colModel : [ {
						name : "TUT_SQ", index : "TUT_SQ", sortable : true, width : 10, align : 'center'
					}, {
						name : "TUT_NM", index : "TUT_NM", sortable : true, width : 30, align : 'center'
					}, {
						name : "STATCODE", index : "STATCODE", sortable : true, width : 30, align : 'center'
					}, {
						name : "REG_DTTM", index : "REG_DTTM", sortable : true, width : 30, align : 'center'
					}],
					pager : "#pager",
					sortname : 'TUT_SQ', // 초기화 될때 sort할 컬럼을 지정
					sortorder : 'desc', // 초기화정렬방법
					jsonReader : {
						repeatitems : false, id : "TUT_SQ", root : "rows", page : "page", total : "total", records : "records"
					},
					gridComplete : function(data, status) {
						var tcnt = jQuery("#tut_list").jqGrid('getGridParam','records');
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
					ondblClickRow : function(rowid, status, e) {
						var ret = jQuery("#tut_list").jqGrid('getRowData', rowid);
						tutDet(ret.TUT_SQ);
					}
				}).trigger('reloadGrid');
		$("#tut_list").jqGrid("navGrid", "#pager", {
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
