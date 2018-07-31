/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function() {
	if($("#s_adm_class").val() == 3 || $("#s_adm_class").val() == 4){
		$(".tabmenu li").eq(0).hide();
		$(".tabmenu li").eq(3).hide();
		$("#addProd").hide();
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
	if($("#startDate").val() == ""){
		$("#startDate").val(getStartDate(365));
		$("#endDate").val(year + "-" + month + "-" + day);
	}

	/* 제품등록 */
	$("#addProd").click(function() {
		location.href = "/product/productNew";
	});

	/* 날짜범위 제한 */
	$("#productSearch").click(function() {
		if(!dayDiffCheck($('#startDate').val(),$('#endDate').val(),'-')){
			return false ;
		};
		if(!dateDiffDay($('#startDate').val(),$('#endDate').val(),'-',365)){
			return false ;
		};
		grid.fn_SearchGrid();
	});

	/* 그리드 초기화 */
	grid.fn_initLoadGrid();
	grid.fn_SearchGrid();

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

function prodDet(pprtMac){
	$("#gubun").val("det");
	$("#pprtMac").val(pprtMac);
	$("#detForm").submit();
}

/* 제품현황 리스트 그리드 */
var grid = {
	fn_initLoadGrid : function() {
		$("#product_list").jqGrid('GridUnload');
		$("#product_list").jqGrid({
			caption : "제품현황 리스트",
			datatype : 'clientside',
			height : 301,
			width : 500,
			rowNum : 10,
			rowList : [ 10, 20, 30 ],
			rownumbers : false,
			viewrecords : true,
			colNames : ["Serial Number", "제품명", "모델번호", "제품분류", "제품상태","등록일시" ],
			colModel : [ {
				name : "PPRT_MAC", index : "PPRT_MAC", sortable : false, width : 30, align : 'center'
			}, {
				name : "CP_NM", index : "CP_NM", sortable : false, width : 30, align : 'center'
			}, {
				name : "MD_NM", index : "MD_NM", sortable : false, width : 30, align : 'center'
			}, {
				name : "MORM", index : "MORM", sortable : false, width : 30, align : 'center'
			}, {
				name : "PRODSTAT", index : "PRODSTAT", sortable : false, width : 30, align : 'center'
			}, {
				name : "REG_DTTM", index : "REG_DTTM", sortable : false, width : 30, align : 'center'
			} ],
			pager : "#pager",
			sortname : 'PPRT_SQ', // 초기화 될때 sort할 컬럼을 지정
			sortorder : 'asc', // 초기화정렬방법
			jsonReader : {
				repeatitems : false, id : "PPRT_SQ", root : "rows", page : "page", total : "total", records : "records"
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
		$("#product_list").jqGrid('GridUnload');
		$("#product_list").jqGrid({
					url : "/product/productSearch",
					caption : "제품리스트",
					datatype : 'json',
					mtype : 'POST',
					postData : {
						startDate : sDate,
						endDate : eDate,
						search : $('#search').val(),
						keyWord : $('#keyWord').val()
					},
					height : 301,
					width : 500,
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					rownumbers : false,
					viewrecords : true,
					colNames : [ "Serial Number", "제품명", "모델번호", "제품분류", "제품상태","등록일시" ],
					colModel : [ {
						name : "PPRT_MAC", index : "PPRT_MAC", sortable : false, width : 30, align : 'center'
					}, {
						name : "CP_NM", index : "CP_NM", sortable : false, width : 30, align : 'center'
					}, {
						name : "MD_NM", index : "MD_NM", sortable : false, width : 30, align : 'center'
					}, {
						name : "MORM", index : "MORM", sortable : false, width : 30, align : 'center'
					}, {
						name : "PRODSTAT", index : "PRODSTAT", sortable : false, width : 30, align : 'center'
					}, {
						name : "REG_DTTM", index : "REG_DTTM", sortable : false, width : 30, align : 'center'
					} ],
					pager : "#pager",
					sortname : 'PPRT_SQ', // 초기화 될때 sort할 컬럼을 지정
					sortorder : 'asc', // 초기화정렬방법
					jsonReader : {
						repeatitems : false, id : "PPRT_SQ", root : "rows", page : "page", total : "total", records : "records"
					},
					gridComplete : function(data, status) {
						var tcnt = jQuery("#product_list").jqGrid('getGridParam','records');
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
						var ret = jQuery("#product_list").jqGrid('getRowData', rowid);
						prodDet(ret.PPRT_MAC);
					},
					imageFormatter : function(cellvalue, options, rowObject)
					{
					    return '<img src="' + rowObject.CP_IMG_NM + '" />';
					}
				}).trigger('reloadGrid');
		$("#product_list").jqGrid("navGrid", "#pager", {
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

function imageFormatter(cellvalue, options, rowObject){
    return '<img src="' + rowObject.CP_IMG_NM + '" />';
}