/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	console.log("smslist");
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

	//주소 시 리스트
	addrList('sido','SI');

	addrList('sido','SI');
	var sd = $("#sd").val();
	if(sd != ""){
		$("#sido").val(sd);
		addrList('gugun',sd);
	}

	var gg = $("#gg").val();
	if(gg != ""){
		$("#gugun").val(gg);
		addrList('dong',gg);
	}

	var dg = $("#dg").val();
	if(dg != ""){
		$("#dong").val(dg);
	}
	//b2b사이트 리스트
//	getBtb();

	$("#btbSearch").click(function() {
		$("#dim-layer").show();
		$("#layerPopup2").show();
		$("#layerPopup2").draggable();
		$("#btbsNm").val($("#siteNm").val());
		grid2.fn_SearchGrid2();
	});
	$("#btbSearch2").click(function() {
		grid2.fn_SearchGrid2();
	});
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup2").hide();
	});

	$("#mdSearch").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
		$("#layerPopup").draggable();
		$("#smdNm").val($("#mdNm").val());
		grid2.fn_SearchGrid();
	});
	$("#mdSearch2").click(function() {
		grid2.fn_SearchGrid();
	});
	$("#close2").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
	});

	grid.fn_SearchGrid();

	//sms목록 조회
    $("#sarchBtn").click(function(){
    	grid.fn_SearchGrid();
    });

    //신규 생성
    $("#newBtn").click(function(){
    	location.href = "/sms/smsNew";
    });
});

/********************************************************************
Name   : 조회 체크
Desc   :
Param  :
********************************************************************/
function validation(){

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

/********************************************************************
Name   : 지역선택
Desc   :
Param  :
********************************************************************/
function addrList(id,groupid){

	$.ajax({async : true
		, url: '/sms/addrList'
		, dataType: 'JSON'
		, type: 'POST'
		, data: {
			R_GROUPID 		: groupid,
		}
		, success: function(data) {
			console.log(data.ADDR.length);
//			if(null != data.ADDR && data.ADDR.length > 0){
//				itemChange(data.ADDR,id)
//			}
			itemChange(data.ADDR,id)
		}
		,complete : function(data) {
		}
		, error: function(xhr, ajaxOptions, thrownError) {

		}
	});

}

/********************************************************************
Name   : selectbox change
Desc   :
Param  :
********************************************************************/
function itemChange(changItem ,id ){

	var id = "#"+id;
	$(id).empty();
	if(id == "#gugun"){
		$("#dong").empty();
		var option = $("<option value='000'>전체</option>");
		$("#dong").append(option);
	}
	var option = $("<option value='000'>전체</option>");
	$(id).append(option);
	for(var i = 0; i < changItem.length; i++){
		var codedata  = changItem[i].CODE;
		var codename  = changItem[i].CODENAME;
        option = $("<option value="+ codedata +">"+codename+"</option>");
        $(id).append(option);
    }
}


/********************************************************************
Name   : b2b조회
Desc   :
Param  :
********************************************************************/
function getBtb(){

	$.ajax({async : true
		, url: '/sms/getBtbSite'
		, dataType: 'JSON'
		, type: 'POST'
		, data: {}
		, success: function(data) {
			console.log(data.B2B.length);
			var btblist = data.B2B
			if(null != btblist && btblist.length > 0){
				$("#btbsnm").empty();
				var option = $("<option value='000'>전체</option>");
				$("#btbsnm").append(option);
				for(var i = 0; i < btblist.length; i++){
					var codedata  = btblist[i].BTBS_SQ;
					var codename  = btblist[i].BTBS_SITE_NM;
			        option = $("<option value="+ codedata +">"+codename+"</option>");
			        $("#btbsnm").append(option);
			    }
			}
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
	fn_initLoadGrid:function(){
		$( "#sms_list" ).jqGrid( 'GridUnload' );
		$("#sms_list").jqGrid({
			caption:"sms리스트"
			,datatype 	    : 'clientside'
			,height:500
			,width:500
			,rowNum:10
			,rowList:[10,20,30]
			,rownumbers : true
			,viewrecords: true
			,colNames:["SMS_SQ","타입", "제목","내용","지역","회원","기기","상품","발송건수","발송일시"]
			,colModel:[
		          {name:"SMS_SQ", 	index:"SMS_SQ" , 		sortable:true,	width:0,		align:'center' ,hidden:true},
		          {name:"SMS_TP", 	index:"SMS_TP", 	sortable:true,	width:10,		align:'center'},
		          {name:"SMS_SUBJECT", 	index:"SMS_SUBJECT", 	sortable:true,	width:30,		align:'center'},
		          {name:"SMS_CONT", 	index:"SMS_CONT", 	sortable:false,	width:100,		align:'center'},
		          {name:"AREA", 	index:"AREA", 	sortable:true,	width:30,		align:'center'},
		          {name:"SMS_TO_BTB", 	index:"SMS_TO_BTB", 	sortable:true,	width:30,		align:'center'},
		          {name:"MOBILE_TYPE", 	index:"MOBILE_TYPE", 	sortable:true,	width:30,		align:'center'},
		          {name:"MD_SQ", 	index:"MD_SQ", 	sortable:true,	width:30,		align:'center'},
		          {name:"SMS_CNT", 	index:"SMS_CNT", 	sortable:true,	width:30,		align:'center'},
		          {name:"REG_DTTM", 	index:"REG_DTTM", 	sortable:true,	width:30,		align:'center'},
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
	    	}
	    	,onSelectRow   : function(rowid, status, e) {
		     }

		}).trigger('reloadGrid');

        $("#sms_list").jqGrid('setGroupHeaders', {
            useColSpanStyle: true,
              groupHeaders:[
                {startColumnName: 'AREA', numberOfColumns: 4, titleText: "발송대상" }
              ]
        });
	}
	,fn_SearchGrid:function(){
		var sDate = $('#monthfrom').val().replace(/-/g,'');
		var eDate = $('#monthto').val().replace(/-/g,'');
		if($("#nCal").prop("checked") == true){
			sDate = "";
			eDate = "";
		}
		if($('#mdNm').val() == ""){$('#mdSq').val("000");}
		if($('#siteNm').val() == ""){$('#siteSq').val("000");}
		$( "#sms_list" ).jqGrid( 'GridUnload' );
		$("#sms_list").jqGrid({
			url:"/sms/smsListSearch"
			,caption:"sms리스트"
			,datatype 	    : 'json'
		    ,mtype         : 'POST'
	    	, postData	  : {
	    		R_FROMDT : sDate,
	    		R_TODT : eDate,
	    		R_ADDR_1 : $("#sido option:selected").text() == '전체' ? '' : $("#sido option:selected").text(),
	    		R_ADDR_2 : $("#gugun option:selected").text() == '전체' ? '' :  $("#gugun option:selected").text(),
	    		R_ADDR_3 : $("#dong option:selected").text() == '전체' ? '' : $("#dong option:selected").text(),
	    		R_BTB_SQ : $('#siteSq').val() == '000' ? '' : $('#siteSq').val(),
	    		R_MOBILE_TYPE : $('#mtype').val() == '000' ? '' : $('#mtype').val() ,
	    		R_MD_SQ : $('#mdSq').val() == '000' ? '' : $('#mdSq').val(),
	    		R_SEARCH_TYPE : $('#searchType').val(),
	    		R_KEYWORD: $('#searStr').val()
	        }
			,height:500
			,width:500
			,rowNum:10
			,rowList:[10,20,30]
			,rownumbers : true
			,viewrecords: true
			,colNames:["SMS_SQ","타입", "제목","내용","지역","회원","기기","상품","발송건수","발송일시"]
			,colModel:[
		          {name:"SMS_SQ", 	index:"SMS_SQ" , 		sortable:true,	width:0,		align:'center' ,hidden:true},
		          {name:"SMS_TP", 	index:"SMS_TP", 	sortable:true,	width:10,		align:'center'},
		          {name:"SMS_SUBJECT", 	index:"SMS_SUBJECT", 	sortable:true,	width:30,		align:'center'},
		          {name:"SMS_CONT", 	index:"SMS_CONT", 	sortable:false,	width:100,		align:'center'},
		          {name:"AREA", 	index:"AREA", 	sortable:true,	width:30,		align:'center'},
		          {name:"SMS_TO_BTB", 	index:"SMS_TO_BTB", 	sortable:true,	width:30,		align:'center'},
		          {name:"MOBILE_TYPE", 	index:"MOBILE_TYPE", 	sortable:true,	width:30,		align:'center'},
		          {name:"MD_SQ", 	index:"MD_SQ", 	sortable:true,	width:30,		align:'center'},
		          {name:"SMS_CNT", 	index:"SMS_CNT", 	sortable:true,	width:30,		align:'center'},
		          {name:"REG_DTTM", 	index:"REG_DTTM", 	sortable:true,	width:30,		align:'center'},
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
				var tcnt = jQuery("#sms_list").jqGrid('getGridParam','records');
				$("#tcnt").text("총 "+tcnt+"개");
				// 그리드 데이터의 ID 가져오기
				var ids = $('#sms_list').jqGrid('getDataIDs');
				// 그리드 데이터 가져오기
				var gridData = $("#sms_list").jqGrid('getRowData');
				// 데이터 확인후 색상 변경
				/*for (var i = 0; i < gridData.length; i++) {
					if (gridData[i].MOBILE_TYPE == 'IOS') {
						$("#"+ids[i]).css("background", "#485675");
				   }
				}*/
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
	    	,ondblClickRow    : function(rowid, status, e) {
	    		var ret = jQuery("#sms_list").jqGrid('getRowData',rowid);
		    	goDet(ret.SMS_SQ);
		     }
		}).trigger('reloadGrid');

		$("#sms_list").jqGrid('setGroupHeaders', {
            useColSpanStyle: true,
              groupHeaders:[
            	 {startColumnName: 'AREA', numberOfColumns: 4, titleText: "발송대상" }
              ]
        });
		$("#sms_list").jqGrid(
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
Name   : sms상세
Desc   :
Param  :
********************************************************************/
function goDet(id){
	$('#R_SMS_SQ').val(id);
	$("#smsform").submit();
}



/********************************************************************
Name   : sms상세
Desc   :
Param  :
********************************************************************/
function pouup(id){
	var popupX = (window.screen.width / 2) - (800 / 2);
	var popupY= (window.screen.height /2) - (800 / 2);
	window.open('', 'smsDetPop', 'status=no, height=800, width=800, left='+ popupX + ', top='+ popupY + ', screenX='+ popupX + ', screenY= '+ popupY);
	$('#R_SMS_SQ').val(id);
	$("#poupform").submit();
}


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
				,colNames:["BTBS_SQ","번호","사이트명","회사명","사이트URL"]
				,colModel:[
			          {name:"BTBS_SQ", index:"BTBS_SQ" , sortable:false,hidden:true	,width:10, align:'center'},
			          {name:"ROWNUM", index:"ROWNUM" , sortable:false,	width:10, align:'center'},
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
				,colNames:["BTBS_SQ","번호","사이트명","회사명","사이트URL"]
				,colModel:[
							{name:"BTBS_SQ", index:"BTBS_SQ" , sortable:false,hidden:true,	width:10, align:'center'},
							{name:"ROWNUM", index:"ROWNUM" , sortable:false,	width:10, align:'center'},
					          {name:"BTBS_SITE_NM", index:"BTBS_SITE_NM", sortable:false,	width:40, align:'center'},
					          {name:"BTBS_COMP_NM", index:"BTBS_COMP_NM", sortable:false, width:40, align:'center'},
					          {name:"BTBS_DOMAIN", index:"BTBS_DOMAIN", sortable:false, width:40, align:'center'}
			          ]
				,pager:"#pager2"
		        ,sortname     : 'REG_DTTM'     //초기화 될때 sort할 컬럼을 지정
		        ,sortorder    : 'asc'       //초기화정렬방법
				,jsonReader:{ repeatitems:false
					, id:"BTBS_SQ"
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
		    	  alert(status + " : " +error);
		    	  console.log(status);
		    	  console.log(xhr);
		    	  console.log(error);
		    	}
		    	,ondblClickRow    : function(rowid, status, e) {
			    	var ret = jQuery("#btb_list").jqGrid('getRowData',rowid);
			    	$("#siteNm").val(ret.BTBS_SITE_NM);
			    	$("#siteSq").val(ret.BTBS_SQ);
			    	//getSendCnt();
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
		,fn_initLoadGrid:function(){
			$("#md_list").jqGrid('GridUnload');
			$("#md_list").jqGrid({
				caption:"상품 목록"
				,datatype : 'clientside'
				,height:300
				,width:250
				,rowNum:8
				,rownumbers : false
				,viewrecords: true
				,colNames:["BTBS_SQ","번호","상품명","상품상세"]
				,colModel:[
			          {name:"MD_SQ", index:"MD_SQ" , sortable:false,hidden:true	,width:10, align:'center'},
			          {name:"ROWNUM", index:"ROWNUM" , sortable:false,	width:10, align:'center'},
			          {name:"CP_NM", index:"CP_NM", sortable:false,	width:40, align:'center'},
			          {name:"DES", index:"DES", sortable:false, width:40, align:'center'}

			          ]
				,pager:"#pager"
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
		,fn_SearchGrid:function(){
			$("#md_list").jqGrid( 'GridUnload' );
			$("#md_list").jqGrid({
				url:"/sms/mdListSearch"
				,caption:"상품 목록"
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
				,colNames:["MD_SQ","번호","상품명","상품상세"]
				,colModel:[
			          {name:"MD_SQ", index:"MD_SQ" , sortable:false,hidden:true	,width:10, align:'center'},
			          {name:"ROWNUM", index:"ROWNUM" , sortable:false,	width:10, align:'center'},
			          {name:"CP_NM", index:"CP_NM", sortable:false,	width:40, align:'center'},
			          {name:"DES", index:"DES", sortable:false, width:40, align:'center'}

			          ]
				,pager:"#pager"
		        ,sortname     : 'REG_DTTM'     //초기화 될때 sort할 컬럼을 지정
		        ,sortorder    : 'asc'       //초기화정렬방법
				,jsonReader:{ repeatitems:false
					, id:"MD_SQ"
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
		    	  alert(status + " : " +error);
		    	  console.log(status);
		    	  console.log(xhr);
		    	  console.log(error);
		    	}
		    	,ondblClickRow    : function(rowid, status, e) {
			    	var ret = jQuery("#md_list").jqGrid('getRowData',rowid);
			    	$("#mdNm").val(ret.CP_NM);
			    	$("#mdSq").val(ret.MD_SQ);
			    	$("#dim-layer").hide();
					$("#layerPopup").hide();
					//getSendCnt();
			     }
			}).trigger('reloadGrid');

			$("#md_list").jqGrid(
					"navGrid",
					"#pager",
					{search:false, edit:false, add:false, del:false},
					{closeAfterEdit:false, reloadAfterSubmit:false},
					{closeAfterAdd:false, reloadAfterSubmit:false},
					{reloadAfterSubmit:false}
			);
		}
	}
