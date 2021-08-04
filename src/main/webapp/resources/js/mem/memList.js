/********************************************************************
Name   : 전역 환경 설정
Desc   :
Param  :
********************************************************************/
$(function() {
	if($("#s_adm_class").val() == 3 || $("#s_adm_class").val() == 4){
		$(".tabmenu li").eq(2).hide();
		$(".tabmenu li").eq(3).hide();
		$("#siteNm").val($("#btbs_nm_s").val());
		$("#btbsSq").val($("#btbs_sq_s").val());
		$("#siteNm").attr("readOnly","readOnly");
		$("#btbSearch").hide();
	}

	/* btb사이트 검색 */
	$("#btbSearch").click(function() {
		$("#dim-layer").show();
		$("#layerPopup2").show();
		$("#layerPopup2").draggable();
		$("#btbsNm").val($("#siteNm").val());
		grid2.fn_SearchGrid2();
	});

	/* btb사이트 검색 */
	$("#btbSearch2").click(function() {
		grid2.fn_SearchGrid2();
	});

	/* 레이어팝업 닫기 */
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup2").hide();
	});

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
	
	$("#siteNm").click(function() {
		$("#dim-layer").show();
		$("#layerPopup2").show();
		$("#layerPopup2").draggable();
		$("#btbsNm").val($("#siteNm").val());
		grid2.fn_SearchGrid2();
	});
	
	$("#btnClear").click(function() {
		$("#siteNm").val("");
		$("#btbsSq").val("");
	});
	
	
	$("#btnClear").click(function() {
		$("#siteNm").val("");
		$("#btbsSq").val("");
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

	/* 회원 리스트 검색 */
	$("#memSearch").click(function() {
		if(!dayDiffCheck($('#startDate').val(),$('#endDate').val(),'-')){
			return false ;
		};
		if(!dateDiffDay($('#startDate').val(),$('#endDate').val(),'-',365)){
			return false ;
		};
		grid.fn_SearchGrid();
	});

	/* 탭이동 */
	$(".tabmenu>li").eq(0).addClass("active");

	/* 주소선택 */
	$("#addr_1").change(function(){
		$.ajax({
			url : "/mem/getSggList",
			data : {
				code : $(this).val()
			},
			type : "POST",
			success : function(result){
				var sHtml = "";
				sHtml += "<option value=''>전체</option>";
				for(var s=0; s<result.model.sggList.length; s++){
					sHtml += "<option value='"+result.model.sggList[s].CODE+"'>"+result.model.sggList[s].CODENAME+"</option>";
				}
				$("#addr_2").html(sHtml);
				$("#addr_3").html("<option value=''>전체</option");
			},
			error : function(e){
				alert("error : "+e);
			}
		});
	});

	/* 주소선택2 */
	$("#addr_2").change(function(){
		$.ajax({
			url : "/mem/getUmdList",
			data : {
				code : $(this).val()
			},
			type : "POST",
			success : function(result){
				var sHtml = "";
				sHtml += "<option value=''>전체</option>";
				for(var s=0; s<result.model.umdList.length; s++){
					sHtml += "<option value='"+result.model.umdList[s].CODE+"'>"+result.model.umdList[s].CODENAME+"</option>";
				}
				$("#addr_3").html(sHtml);
			},
			error : function(e){
				alert("erro : r"+e);
			}
		});
	});

	if($("#addr_1").val() != ""){
		$.ajax({
			url : "/mem/getSggList",
			data : {
				code : $("#addr_1").val()
			},
			type : "POST",
			success : function(result){
				var sHtml = "";
				sHtml += "<option value=''>전체</option>";
				for(var s=0; s<result.model.sggList.length; s++){
					sHtml += "<option value='"+result.model.sggList[s].CODE+"'>"+result.model.sggList[s].CODENAME+"</option>";
				}
				$("#addr_2").html(sHtml);
				$("#addr_3").html("<option value=''>전체</option");
				$("#addr_2").val($("#add2").val());
				if($("#addr_2").val() != ""){
					$.ajax({
						url : "/mem/getUmdList",
						data : {
							code : $("#add2").val()
						},
						type : "POST",
						success : function(result){
							var sHtml = "";
							sHtml += "<option value=''>전체</option>";
							for(var s=0; s<result.model.umdList.length; s++){
								sHtml += "<option value='"+result.model.umdList[s].CODE+"'>"+result.model.umdList[s].CODENAME+"</option>";
							}
							$("#addr_3").html(sHtml);
							$("#addr_3").val($("#add3").val());
						},
						error : function(e){
							alert("erro : r"+e);
						}
					});
				}
			},
			error : function(e){
				alert("error : "+e);
			}
		});
	}

	/* 회원리스트 헤더 그리드 */
	grid.fn_initLoadGrid();
	grid.fn_SearchGrid();

	/* btb사이트 그리드 */
	grid2.fn_initLoadGrid2();

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
function memDet(sq) {
	$("#memSq").val(sq);
	$("#gubun").val("det");
	$("#detForm").submit();
}

/* 회원리스트 그리드 */
var grid = {
	fn_initLoadGrid : function() {
		$("#mem_list").jqGrid('GridUnload');
		$("#mem_list").jqGrid({
					caption : "회원리스트",
					datatype : 'clientside',
					height : 301,
					width : 500,
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					rownumbers : true,
					viewrecords : true,
					colNames : [ "번호", "아이디", "이름", "사이트명", "거주지역", "성별", "핸드폰번호", "현재상태", "가입일자" ],
					colModel : [ {
						name : "MEM_SQ", index : "MEM_SQ", sortable : true, width : 0, align : 'center', hidden : true
					}, {
						name : "MEM_EMAIL", index : "MEM_EMAIL", sortable : true, width : 30, align : 'center'
					}, {
						name : "MEM_NM", index : "MEM_NM", sortable : true, width : 30, align : 'center'
					}, {
						name : "BTBS_NM", index : "BTBS_NM", sortable : false, width : 30, align : 'center'
					}, {
						name : "ADDR", index : "ADDR", sortable : false, width : 30, align : 'center'
					}, {
						name : "GENCODE", index : "GENCODE", sortable : true, width : 30, align : 'center'
					}, {
						name : "USER_HP", index : "USER_HP", sortable : true, width : 30, align : 'center'
					}, {
						name : "STATCODE", index : "STATCODE", sortable : true, width : 30, align : 'center'
					}, {
						name : "REG_DTTM", index : "REG_DTTM", sortable : true, width : 30, align : 'center'
					} ],
					pager : "#pager",
					sortname : 'MEM_SQ', // 초기화 될때 sort할 컬럼을 지정
					sortorder : 'asc', // 초기화정렬방법
					jsonReader : {
						repeatitems : false, id : "MEM_SQ", root : "rows", page : "page", total : "total", records : "records"
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
		$("#mem_list").jqGrid('GridUnload');
		$("#mem_list").jqGrid({
					url : "/mem/memSearch",
					caption : "회원리스트",
					datatype : 'json',
					mtype : 'POST',
					postData : {
						btbsNm : $('#siteNm').val(),
						btbsSq : $('#btbsSq').val(),
						addr_1 : $('#addr_1').val(),
						addr_2 : $('#addr_2').val(),
						addr_3 : $('#addr_3').val(),
						memGen : $('#memGen').val(),
						startDate : sDate,
						endDate : eDate,
						search : $('#search').val(),
						keyWord : $('#keyWord').val()
					},
					height : 301,
					width : 500,
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					rownumbers : true,
					viewrecords : true,
					colNames : [ "번호", "아이디", "이름", "사이트명", "거주지역", "성별", "핸드폰번호",
							"현재상태", "가입일자" ],
							colModel : [ {
								name : "MEM_SQ", index : "MEM_SQ", sortable : true, width : 0, align : 'center', hidden : true
							}, {
								name : "MEM_EMAIL", index : "MEM_EMAIL", sortable : true, width : 30, align : 'center'
							}, {
								name : "MEM_NM", index : "MEM_NM", sortable : true, width : 30, align : 'center'
							}, {
								name : "BTBS_NM", index : "BTBS_NM", sortable : false, width : 30, align : 'center'
							}, {
								name : "ADDR", index : "ADDR", sortable : false, width : 30, align : 'center'
							}, {
								name : "GENCODE", index : "GENCODE", sortable : true, width : 30, align : 'center'
							}, {
								name : "USER_HP", index : "USER_HP", sortable : true, width : 30, align : 'center'
							}, {
								name : "STATCODE", index : "STATCODE", sortable : true, width : 30, align : 'center'
							}, {
								name : "REG_DTTM", index : "REG_DTTM", sortable : true, width : 30, align : 'center'
							} ],
					pager : "#pager",
					sortname : 'MEM_SQ', // 초기화 될때 sort할 컬럼을 지정
					sortorder : 'asc', // 초기화정렬방법
					jsonReader : {
						repeatitems : false, id : "MEM_SQ", root : "rows", page : "page", total : "total", records : "records"
					},
					gridComplete : function(data, status) {
						var tcnt = jQuery("#mem_list").jqGrid('getGridParam','records');
						$("#tcnt").text("총 "+tcnt+"명");
						// 그리드 데이터의 ID 가져오기
						var ids = $('#mem_list').jqGrid('getDataIDs');
						// 그리드 데이터 가져오기
						var gridData = $("#mem_list").jqGrid('getRowData');
						// 데이터 확인후 색상 변경
						for (var i = 0; i < gridData.length; i++) {
							if (gridData[i].STATCODE == '삭제') {
								$("#"+ids[i]).css("background", "#B5B5B5");
						   }
						}
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
					ondblClickRow  : function(rowid, status, e) {
						var ret = jQuery("#mem_list").jqGrid('getRowData', rowid);
						memDet(ret.MEM_SQ);
					}
				}).trigger('reloadGrid');
		$("#mem_list").jqGrid("navGrid", "#pager", {
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

/* B2B사이트 목록 그리드 */
var grid2 = {
	fn_initLoadGrid2:function(){
		$("#btb_list").jqGrid('GridUnload');
		$("#btb_list").jqGrid({
			caption:"B2B사이트 목록"
			,datatype : 'clientside'
			,height:300
			,width:250
			,rowNum:8
			,rownumbers : false
			,viewrecords: true
			,colNames:["번호","사이트명","회사명","사이트URL"]
			,colModel:[
		          {name:"BTBS_SQ", index:"BTBS_SQ" , sortable:false,	width:10, align:'center'},
		          {name:"BTBS_SITE_NM", index:"BTBS_SITE_NM", sortable:false,	width:40, align:'center'},
		          {name:"BTBS_COMP_NM", index:"BTBS_COMP_NM", sortable:false, width:40, align:'center'},
		          {name:"BTBS_DOMAIN", index:"BTBS_DOMAIN", sortable:false, width:40, align:'center'}
		          ]
			,pager:"#pager2"
	        ,sortname     : 'REG_DTTM'     //초기화 될때 sort할 컬럼을 지정
	        ,sortorder    : 'asc'       //초기화정렬방법
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
	,fn_SearchGrid2:function(){
		$("#btb_list").jqGrid( 'GridUnload' );
		$("#btb_list").jqGrid({
			url:"/mem/btbsSearch"
			,caption:"B2B사이트 목록"
			,datatype 	   : 'json'
		    ,mtype         : 'POST'
	    	, postData	   : {
	        	btbsNm : $("#btbsNm").val()
	        }
			,height:300
			,width:250
			,rowNum:8
			,rownumbers : false
			,viewrecords: true
			,colNames:["번호","사이트명","회사명","사이트URL"]
			,colModel:[
			           {name:"BTBS_SQ", index:"BTBS_SQ" , sortable:false,	width:10, align:'center'},
				          {name:"BTBS_SITE_NM", index:"BTBS_SITE_NM", sortable:false,	width:40, align:'center'},
				          {name:"BTBS_COMP_NM", index:"BTBS_COMP_NM", sortable:false, width:40, align:'center'},
				          {name:"BTBS_DOMAIN", index:"BTBS_DOMAIN", sortable:false, width:40, align:'center'}
		          ]
			,pager:"#pager2"
	        ,sortname     : 'REG_DTTM'     //초기화 될때 sort할 컬럼을 지정
	        ,sortorder    : 'asc'       //초기화정렬방법
			,jsonReader:{ repeatitems:false
				, id:"ROWNUM"
				, root: "rows"
				, page : "page"
				, total : "total"
				,records : "records" }

			,gridComplete : function(data,status) {
				/*var val = jQuery("#btb_list").jqGrid('getGridParam','records');
				$("#totalCnt").html(val);*/
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
	    	,ondblClickRow    : function(rowid, status, e) {
		    	var ret = jQuery("#btb_list").jqGrid('getRowData',rowid);
		    	$("#siteNm").val(ret.BTBS_SITE_NM);
		    	$("#btbsSq").val(ret.BTBS_SQ);
		    	$("#dim-layer").hide();
				$("#layerPopup2").hide();
		     }
		}).trigger('reloadGrid');

		$("#btb_list").jqGrid(
				"navGrid",
				"#pager2",
				{search:false, edit:false, add:false, del:false},
				{closeAfterEdit:false, reloadAfterSubmit:false},
				{closeAfterAdd:false, reloadAfterSubmit:false},
				{reloadAfterSubmit:false}
		);
	}
}
