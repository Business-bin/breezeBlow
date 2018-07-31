/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	console.log("btbAdminView");

	grid2.fn_initLoadGrid2();

	/* 레이어 팝업 */
	$("#layerPopup").hide();
	$("#dim-layer").hide();
	$("#layerPopup1").hide();
	$("#dim-layer1").hide();
	$("#layerPopup2").hide();
	$("#dim-layer2").hide();
	$("#layerPopup3").hide();
	$("#dim-layer3").hide();

	$("#layerPopup").draggable();
	$("#layerPopup1").draggable();
	$("#layerPopup2").draggable();
	$("#layerPopup3").draggable();

	$("#btn_del").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
		$("#layerPopup a").focus();
		return false;
	});
	$("#btn_pwd").click(function() {
		$("#dim-layer1").show();
		$("#layerPopup1").show();
		$("#layerPopup1 a").focus();
		return false;
	});
	$("#btn_stop").click(function() {
		$("#dim-layer2").show();
		$("#layerPopup2").show();
		return false;
	});
	$("#btn_site_nm").click(function() {
		$("#dim-layer3").show();
		$("#layerPopup3").show();
		$("#layerPopup3 a").focus();
		$("#btbsNm").val($("#btb_site_nm").val());
		grid2.fn_SearchGrid2();
	});
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
		$("#layerPopup2").hide();
		$("#dim-layer2").hide();
		$("#dim-layer3").hide();
		$("#layerPopup3").hide();
	});
	$("#close1").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
		$("#layerPopup2").hide();
		$("#dim-layer2").hide();
		$("#dim-layer3").hide();
		$("#layerPopup3").hide();
	});
	$("#close2").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
		$("#layerPopup2").hide();
		$("#dim-layer2").hide();
		$("#dim-layer3").hide();
		$("#layerPopup3").hide();
	});
	$("#close3").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
		$("#layerPopup2").hide();
		$("#dim-layer2").hide();
		$("#dim-layer3").hide();
		$("#layerPopup3").hide();
	});


	$("#btbSearch").click(function() {
		grid2.fn_SearchGrid2();
	});

	$("#goList").click(function() {
		$("#backForm").submit();
	});

	$("#btbsNm").keypress(function(e) {
	   if(e.keyCode==13) {
		   grid2.fn_SearchGrid2();
	   }
	});


	/* 레이어 팝업 */


	var val = $('#gender').val();
	$("select[name='adm_gen']").val(val).attr("selected", "selected");

	var val2 = $('#adm_hp1').val();
	$("select[name='hp_1']").val(val2).attr("selected", "selected");

	var val3 = $('#adm_tel1').val();
	$("select[name='tel_1']").val(val3).attr("selected", "selected");

	if($('#stat').val() == '01'){
		$("#btn_use").hide();
	}else if($('#stat').val() == '02'){
		$("#btn_stop").hide();
	}else if($('#stat').val() == '03'){
//		$('#buttonDiv').find("button").prop("disabled", true);
		$('#tbody').find("input").prop("readonly", true);
		$('#adm_gen').attr("disabled", true);
		$('#hp_1').attr("disabled", true);
		$('#tel_1').attr("disabled", true);

		$("#btn_pwd").hide();
		$("#btn_mod").hide();
		$("#btn_del").hide();
		$("#btn_stop").hide();
		$("#btn_use").hide();

	}


	//초기화 그리드
	grid.fn_initLoadGrid();

	grid.fn_SearchGrid();

	$('#btn_mod').click(function(){
		console.log("btn_mod");
		modAdmin();
	});
	$('#delAdmin').click(function(){
		console.log("delAdmin");
		delAdmin();
	});

	$('#changePwd').click(function(){
		console.log("changePwd");
		changePwd();
	});

//	$('#btn_site_nm').click(function(){
//		console.log("btn_site_nm");
//		siteNmPop();
//	});

	$('#goStop').click(function(){
		console.log("btn_stop");
		goStop();
	});
	$('#btn_use').click(function(){
		console.log("btn_use");
		goUse();
	});

	//관리자명
	$('#adm_nm').keyup(function(event) {
		regExp_char('adm_nm');
	});
	//소속
	$('#adm_part').keyup(function(event) {
		regExp_char('adm_part');
	});
	//직급
	$('#adm_rank').keyup(function(event) {
		regExp_char('adm_rank');
	});

	/* 휴대전화번호   */
	$('#hp_2').keyup(function(event) {
		regExp_number('hp_2');
		proc(event,'hp_2','hp_3')
	});

	$('#hp_3').keyup(function(event) {
		regExp_number('hp_3');
	});

	/* 전화번호   */
	$('#tel_2').keyup(function(event) {
		regExp_number('tel_2');
		proc(event,'tel_2','tel_3')
	});

	$('#tel_3').keyup(function(event) {
		regExp_number('tel_3');
	});

});

/********************************************************************
Name   : jqGrid
Desc   : 목록 조회
Param  :
********************************************************************/
var grid = {
		fn_initLoadGrid:function(){
			$("#admin_log_list").jqGrid('GridUnload');
			$("#admin_log_list").jqGrid({
				caption:"B2B관리자 활동로그"
				,datatype 	    : 'clientside'
				,height:160
				,width:500
				,rowNum:5
				,rowList:[5,10,20]
				,rownumbers : false
				,viewrecords: true
				,colNames:["번호","일시","접속IP","활동내역","접속환경"]
				,colModel:[
			          {name:"ROWNUM", 	    index:"ROWNUM" , 	sortable:true,	width:30,		align:'center'},
			          {name:"DTTM1", 	    index:"DTTM1", 	    sortable:true,	width:30,		align:'center'},
			          {name:"IP", 	        index:"IP", 	    sortable:true,	width:30,		align:'center'},
			          {name:"ACT_CONT", 	index:"ACT_CONT", 	sortable:false,	width:30,		align:'center'},
			          {name:"CON_ENV", 	    index:"CON_ENV", 	sortable:true,	width:30,		align:'center'}
			          ]
				,pager:"#pager"
		        ,sortname     : 'DTTM'     //초기화 될때 sort할 컬럼을 지정
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

			$("#admin_log_list").jqGrid( 'GridUnload' );
			$("#admin_log_list").jqGrid({
				url:"/btb/btbAdminLogListProc"
				,caption:"관리자 목록"
				,datatype 	   : 'json'
			    ,mtype         : 'POST'
		    	, postData	   : {
		        	adm_email  : $('#adm_email').val()
		        }
				,height:160
				,width:500
				,rowNum:5
				,rowList:[5,10,20]
				,rownumbers : false
				,viewrecords: true
				,colNames:["번호","일시","접속IP","활동내역","접속환경"]
				,colModel:[
			          {name:"ROWNUM", 	    index:"ROWNUM" , 	sortable:true,	width:30,		align:'center'},
			          {name:"DTTM1", 	    index:"DTTM1", 	    sortable:true,	width:30,		align:'center'},
			          {name:"IP", 	        index:"IP", 	    sortable:true,	width:30,		align:'center'},
			          {name:"ACT_CONT", 	index:"ACT_CONT", 	sortable:false,	width:30,		align:'center'},
			          {name:"CON_ENV", 	    index:"CON_ENV", 	sortable:true,	width:30,		align:'center'}
			          ]
				,pager:"#pager"
		        ,sortname     : 'DTTM'     //초기화 될때 sort할 컬럼을 지정
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

/********************************************************************
Name   : modAdmin
Desc   : 관리자 수정
Param  :
********************************************************************/
function modAdmin(){

	var adm_hp = $('#hp_1').val()+'-'+ $('#hp_2').val()+'-'+$('#hp_3').val();
	var adm_tel = '';

	if($('#tel_1').val() !="" || $('#tel_2').val() !="" || $('#tel_3').val() !=""){
		if($('#tel_1').val() ==""){
			alert('전화번호를 입력하세요.');
			$('#tel_1').focus();
			return;
		}else if($('#tel_2').val() ==""){
			alert('전화번호를 입력하세요.');
			$('#tel_2').focus();
			return;
		}else if($('#tel_3').val() ==""){
			alert('전화번호를 입력하세요.');
			$('#tel_3').focus();
			return;
		}

	}

	if($('#tel_1').val()== '' && $('#tel_2').val()== '' && $('#tel_3').val()== ''){
		adm_tel = '';
	}else{
		adm_tel = $('#tel_1').val()+'-'+$('#tel_2').val()+'-'+$('#tel_3').val();
	}

	var val = $("#adm_gen option:selected").val();
	$('#adm_gen').val(val);

	if($('#btb_site_nm').val()==''){
		alert('사이트명을 입력하세요.');
		$('#btb_site_nm').focus();
	}else if ($('#adm_nm').val() == '') {
		alert('관리자명을 입력하세요.');
		$('#adm_nm').focus();
	}else if ($('#adm_gen').val() == '') {
		alert('성별을 선택하세요.');
		$('#adm_gen').focus();
	}else if ($('#hp_1').val() == '') {
		alert('휴대전화번호를 입력하세요.');
		$('#hp_1').focus();
	}else if ($('#hp_2').val() == '') {
		alert('휴대전화번호를 입력하세요.');
		$('#hp_2').focus();
	}else if ($('#hp_3').val() == '') {
		alert('휴대전화번호를 입력하세요.');
		$('#hp_3').focus();
	} else if(confirm("수정 하시겠습니까?") == true){

		$.ajax({
			url : '/btb/btbAdminViewProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
				   adm_sq          : $('#adm_sq').val(),
 		           adm_nm          : $('#adm_nm').val(),
		           adm_part	       : $('#adm_part').val(),
		           adm_tel         : adm_tel,
		           adm_empn	       : $('#adm_empn').val(),
		           adm_hp          : adm_hp,
		           adm_rank 	   : $('#adm_rank').val(),
		           adm_gen	       : $('#adm_gen').val(),
		           btbs_sq	       : $('#btbs_sq').val(),
		           btb_site_nm     : $('#btb_site_nm').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("정보 수정이 완료되었습니다.");
				goView();
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});

	}else{
		return;
	}

}


/********************************************************************
Name   : delAdmin
Desc   : 관리자 삭제
Param  :
********************************************************************/
function delAdmin(){

	if($('#adm_comment').val()==''){
		alert('삭제사유를 입력하세요.');
		$('#adm_comment').focus();
	}else if(confirm("관리자를 삭제 하시겠습니까?") == true){

		$.ajax({
			url : '/admin/adminDelProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
			    adm_sq      : $('#adm_sq').val(),
			 	adm_comment : $('#adm_comment').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("삭제가 완료되었습니다.");
				goView();
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});

	}else{
		return;
	}

}

/********************************************************************
Name   : goView
Desc   : 관리자 상세보기
Param  :
********************************************************************/
function goView(){
	$('#admViewForm').attr('action', '/btb/btbAdminView');
	$("#admViewForm").submit();
}

/********************************************************************
Name   : changePwd
Desc   : 비밀번호 변경
Param  :
********************************************************************/
function changePwd(){

	if($('#adm_pwd').val()==''){
		alert('변경할 비밀번호를 입력하세요.');
		$('#adm_pwd').focus();
	}else if($('#adm_pwd1').val()==''){
		alert('비밀번호를 다시한번 입력하세요.');
		$('#adm_pwd1').focus();
	}else if($('#adm_pwd').val() != $('#adm_pwd1').val()){
		alert('입력한 비밀번호가 일치하지 않습니다.');
		$('#adm_pwd1').focus();
	}else if(checkPassword($('#adm_pwd').val())== false){
//		alert("비밀번호는 영문/숫자/특수문자 2가지 이상 조합하여, 8자가 넘는 경우 변경 가능합니다.");
		$('#adm_pwd').focus();
	}else if(confirm("변경 하시겠습니까?") == true){

		$.ajax({
			url : '/admin/changePwdProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
			    adm_sq      : $("#adm_sq").val(),
			    adm_pwd     : $('#adm_pwd').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("비밀번호 변경이 완료되었습니다.");
				goView();
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});

	}else{
		return;
	}

}

function onlyNumber(event){
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
		return;
	else
		return false;
}

function removeChar(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
		return;
	else
		event.target.value = event.target.value.replace(/[^0-9]/g, "");
}

function proc(event, val1, val2){
	var val1 =  $('#'+val1).val();
	if(val1.length >=4 && event.keycode !=8){
		$('#'+val2).focus();
	}
}

/********************************************************************
Name   : goStop
Desc   : 이용정지
Param  :
********************************************************************/
function goStop(){

	if($('#stop_comment').val()==''){
		alert('이용정지 사유를 입력하세요.');
		$('#stop_comment').focus();
	}else if(confirm("관리자를 이용정지 하시겠습니까?") == true){

		$.ajax({
			url : '/btb/goAdminStop',
			method : 'POST',
			dataType: 'JSON',
			data : {
			    adm_sq         : $('#adm_sq').val(),
			    stop_comment   : $('#stop_comment').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("이용정지 되었습니다.");
				goView();
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});

	}else{
		return;
	}
}
/********************************************************************
Name   : goUse
Desc   : 이용정지 해제
Param  :
********************************************************************/
function goUse(){

	if(confirm("이용정지 해제하시겠습니까?") == true){

		$.ajax({
			url : '/btb/goAdminUse',
			method : 'POST',
			dataType: 'JSON',
			data : {
			    adm_sq         : $('#adm_sq').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("이용정지 해제되었습니다.");
				goView();
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});

	}else{
		return;
	}
}

// 글자수 제한
//$('#stop_comment').keyup(function(event) {
//	//fnMsgLength(event.target, "textByte", 80);
//	fnMsgLengthAlert(event.target, "stop_comment", 20);
//});

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
				url:"/btb/btbListProc"
				,caption:"B2B사이트 목록"
				,datatype 	   : 'json'
			    ,mtype         : 'POST'
		    	, postData	   : {
		    		category   : 'btbs_nm',
		    		keyword    : $("#btbsNm").val(),
		    		gubun      : 'btbSiteNm'
		        }
				,height:300
				,width:250
				,rowNum:8
				,rownumbers : false
				,viewrecords: true
				,colNames:["번호","고유번호","사이트명","회사명","사이트URL"]
				,colModel:[
				           {name:"ROWNUM", index:"ROWNUM" , sortable:false,	width:10, align:'center'},
				           {name:"BTBS_SQ", index:"BTBS_SQ", sortable:false,	width:40, align:'center', hidden:true},
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
			    	$("#btb_site_nm").val(ret.BTBS_SITE_NM);
			    	$("#btbs_sq").val(ret.BTBS_SQ);
			    	$("#dim-layer3").hide();
					$("#layerPopup3").hide();
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

/********************************************************************
Name   : regExp_char
Desc   : 특수문자 제외
Param  :
********************************************************************/
function regExp_char(id){
	var val = $('#'+id).val();
    var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
    if(regExp.test(val)){
        var t = val.replace(regExp, "");
        $('#'+id).val(t);
    }
}
/********************************************************************
Name   : regExp_number
Desc   : 숫자만 가능
Param  :
********************************************************************/
function regExp_number(id){
	var val = $('#'+id).val();
	var regExp = /[^(0-9)]/gi;
    if(regExp.test(val)){
        var t = val.replace(regExp, "");
        $('#'+id).val(t);
    }
}
