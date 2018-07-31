/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){
	/* 사용자 기기등록 (테스트용) */
	$("#regMem").click(function(){
		location.href="/product/regMemTest?pprtSq="+$("#pprtSq").val();
	});
	/**/

	if($("#s_adm_class").val() == 3 || $("#s_adm_class").val() == 4){
		$(".tabmenu li").eq(0).hide();
		$(".tabmenu li").eq(3).hide();
		$("#initProduct").hide();
		$("#stopProduct").hide();
		$("#regMac").hide();
	}
	/* 레이어팝업 */
	$("#layerPopup2").draggable();
	$("#layerPopup3").draggable();
	$("#layerPopup7").draggable();

	/* 레이어팝업(마스터기기등록) */
	$("#regMac").click(function() {
		$("#dim-layer").show();
		$("#layerPopup2").show();
		grid.fn_SearchGrid();
	});

	/* 기기등록 초기화 */
	$("#initProduct").click(function() {
		$("#dim-layer").show();
		$("#layerPopup3").show();
	});

	/* 기기사용 중지 */
	$("#stopProduct").click(function(){
		$("#dim-layer").show();
		$("#layerPopup7").show();
	});

	/* 레이어팝업 닫기 */
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup2").hide();
	});

	/* 레이어팝업 닫기2 */
	$("#close2").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup3").hide();
	});

	/* 레이어팝업 닫기3 */
	$("#close3").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup7").hide();
	});

	/* MacAddress 검색 */
	$("#macSearch").click(function(){
		grid.fn_SearchGrid();
	});

	/* 제품목록으로 이동 */
	$("#backProdList").click(function() {
		$("#backForm").submit();
	});

	/* 기기등록 초기화 */
	$("#pprtInit").click(function(){
		if(isEmpty($("#pprtInitRsn").val())){
			alert("초기화 사유를 입력해주세요.");
			return false;
		}
		if(calculate_byte($("#pprtInitRsn").val()) > 200){
			alert("초기화 사유는 한글 100자, 영문 200자까지 입력 가능합니다.");
			return false;
		}
		$.ajax({
			type : "post",
			url : "/product/initProduct",
			data : {
				pprtSq : $("#pprtSq").val(),
				pprtInitRsn : $("#pprtInitRsn").val()
			},
			success : function(result){
				alert("초기화 되었습니다.");
				$("#dim-layer").hide();
				$("#layerPopup3").hide();
			},
			error : function(result){

			}
		});
	});

	/* 기기사용 중지 */
	$("#pprtStop").click(function(){
		if(isEmpty($("#pprtStopRsn").val())){
			alert("기기사용 중지 사유를 입력해주세요.");
			return false;
		}
		if(calculate_byte($("#pprtStopRsn").val()) > 200){
			alert("기기사용 중지 사유는 한글 100자, 영문 200자까지 입력 가능합니다.");
			return false;
		}
		$.ajax({
			type : "post",
			url : "/product/stopProduct",
			data : {
				pprtSq : $("#pprtSq").val(),
				pprtStopRsn : $("#pprtStopRsn").val()
			},
			success : function(result){
				alert("사용중지 되었습니다.");
				$("#dim-layer").hide();
				$("#layerPopup7").hide();
			},
			error : function(result){

			}
		});
	});

	if($("#miniYn").val() != "미니"){
		$("#regMac").hide();
	}

	/* 회원정보 상세보기 */
	$("#memDet").click(function(){
		location.href="/mem/memDet?gubun=det&memSq="+$("#memSq").val();
	});

	/* 탭이동 */
	$(".tabmenu>li").eq(1).addClass("active");
});

/* 마스터제품 검색 그리드 */
var grid = {
	fn_initLoadGrid:function(){
		$("#mac_list").jqGrid('GridUnload');
		$("#mac_list").jqGrid({
			caption:"시리얼 번호 검색"
			,datatype : 'clientside'
			,height:300
			,width:350
			,rowNum:8
			,rownumbers : false
			,viewrecords: true
			,colNames:["고유번호","번호","제품명","모델명","제품분류","S/N"]
			,colModel:[
			      {name:"MD_SQ", index:"MD_SQ" , sortable:false,	width:0, align:'center', hidden: true},
			      {name:"ROWNUM", index:"ROWNUM" , sortable:true,	width:10, align:'center'},
		          {name:"CP_NM", index:"CP_NM" , sortable:false,	width:20, align:'center'},
		          {name:"MD_NM", index:"MD_NM", sortable:false,	width:20, align:'center'},
		          {name:"MORM", index:"MORM", sortable:false, width:10, align:'center'},
		          {name:"PPRT_MAC", index:"PPRT_MAC", sortable:false, width:20, align:'center'}
		          ]
			,pager:"#pager"
	        ,sortname     : 'MD_SQ'     //초기화 될때 sort할 컬럼을 지정
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
	,fn_SearchGrid:function(){
		$("#mac_list").jqGrid( 'GridUnload' );
		$("#mac_list").jqGrid({
			url:"/product/macSearch"
			,caption:"시리얼 번호 검색"
			,datatype 	   : 'json'
		    ,mtype         : 'POST'
	    	, postData	   : {
	        	pprtMac : $("#pprtMac").val()
	        }
			,height:300
			,width:250
			,rowNum:8
			,rownumbers : false
			,viewrecords: true
			,colNames:["고유번호","번호","제품명","모델명","제품분류","S/N"]
			,colModel:[
					      {name:"MD_SQ", index:"MD_SQ" , sortable:false,	width:0, align:'center', hidden: true},
					      {name:"ROWNUM", index:"ROWNUM" , sortable:false,	width:10, align:'center'},
				          {name:"CP_NM", index:"CP_NM" , sortable:false,	width:20, align:'center'},
				          {name:"MD_NM", index:"MD_NM", sortable:false,	width:20, align:'center'},
				          {name:"MORM", index:"MORM", sortable:false, width:10, align:'center'},
				          {name:"PPRT_MAC", index:"PPRT_MAC", sortable:false, width:20, align:'center'}
				          ]
			,pager:"#pager"
	        ,sortname     : 'MD_SQ'     //초기화 될때 sort할 컬럼을 지정
	        ,sortorder    : 'asc'       //초기화정렬방법
			,jsonReader:{ repeatitems:false
				, id: "ROWNUM"
				, root: "rows"
				, page : "page"
				, total : "total"
				,records : "records" }

			,gridComplete : function(data,status) {
				/*var val = jQuery("#mac_list").jqGrid('getGridParam','records');
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
	    	,ondblClickRow : function(rowid, status, e) {
		    	var ret = jQuery("#mac_list").jqGrid('getRowData',rowid);
			    $.ajax({
			    	type : "post",
			    	url : "/product/matching",
			    	data : {
			    		pprtMac : ret.PPRT_MAC,
			    		pprtSq : $("#pprtSq").val()
			    	},
			    	success : function(){
			    		alert("매칭기기가 등록되었습니다.");
			    		$("#dim-layer").hide();
						$("#layerPopup2").hide();
			    	},
			    	error : function(){

			    	}
			    })
		     }
		}).trigger('reloadGrid');

		$("#mac_list").jqGrid(
				"navGrid",
				"#pager",
				{search:false, edit:false, add:false, del:false},
				{closeAfterEdit:false, reloadAfterSubmit:false},
				{closeAfterAdd:false, reloadAfterSubmit:false},
				{reloadAfterSubmit:false}
		);
	}
}