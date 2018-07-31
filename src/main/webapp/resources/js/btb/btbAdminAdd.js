/********************************************************************
Name   : ready                                                 
Desc   : 
Param  :            
********************************************************************/	
$(document).ready(function(){
	console.log("adminAdd");
	
	$("#layerPopup3").hide();
	$("#dim-layer").hide();
	$("#layerPopup2").hide();
	$("#dim-layer2").hide();
	$("#layerPopup3").draggable();
	$("#layerPopup2").draggable();
	
	$("#btn_site_nm").click(function() {
		$("#dim-layer").show();
		$("#layerPopup3").show();
		$("#layerPopup3 a").focus();
		$("#btbsNm").val($("#btb_site_nm").val());
		grid2.fn_SearchGrid2();
	});
	$("#btbSearch").click(function() {
		grid2.fn_SearchGrid2();
	});
	
	$("#btbsNm").keypress(function(e) {
	   if(e.keyCode==13) {
		   grid2.fn_SearchGrid2();
	   }
	}); 
	
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup3").hide();
		$("#dim-layer2").hide();
		$("#layerPopup2").hide();
	});
	
	$("#close2").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup3").hide();
		$("#dim-layer2").hide();
		$("#layerPopup2").hide();
	});
	
	
	$('#btn_add').click(function(){
		console.log("btn_add");
		addAdmin();
		
	});

	$("#btn_check").click(function() {
		$("#dim-layer2").show();
		$("#layerPopup2").show();
		$('#admEmail').val($('#adm_email').val());
		$("#msg").text("");
		if($('#admEmail').val() != ""){
			checkId();
		}
	});	
	
	$('#checkId').click(function(){
		checkId();
	});
	
	$("#admEmail").keypress(function(e) {
	    if(e.keyCode==13) {
	    	checkId();
	    }
	}); 
	
	$("#goList").click(function() {
		location.href="/btb/btbAdminList";
	});
	
	$('#selAdmEmail').click(function(){
		if($('#gubun').val() == "N"){
			return false;
		}else{
			if($('#admEmail').val() == "" || $('#check_yn').val() !="Y" || $('#check_id').val() != $('#admEmail').val()){
				alert("먼저 아이디를 중복확인하세요.");
				$('#admEmail').focus();
			}else{
				$('#adm_email').val($('#admEmail').val());
				$("#dim-layer2").hide();
				$("#layerPopup2").hide();
			}
		}
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
Name   : addAdmin                                            
Desc   : 관리자 신규 생성
Param  :            
********************************************************************/
function addAdmin(){
		
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

	if($('#btbs_sq').val()==''){
		alert('사이트명을 검색하세요.');
		$('#btb_site_nm').focus();
	}else if ($('#adm_email').val() == '') {
		alert('아이디를 입력하세요.');
		$('#adm_email').focus();
	}else if ($('#adm_pwd').val() == '') {
		alert('비밀번호를 입력하세요.');
		$('#adm_pwd').focus();
	}else if ($('#adm_pwd1').val() == '') {
		alert('비밀번호 확인을 입력하세요.');
		$('#adm_pwd1').focus();
	}else if($('#adm_nm').val()==''){
		alert('관리자명을 입력하세요.');
		$('#adm_nm').focus();
	}else if ($('#hp_1').val() == '') {
		alert('휴대전화번호를 입력하세요.');
		$('#hp_1').focus();
	}else if ($('#hp_2').val() == '') {
		alert('휴대전화번호를 입력하세요.');
		$('#hp_2').focus();
	}else if ($('#hp_3').val() == '') {
		alert('휴대전화번호를 입력하세요.');
		$('#hp_3').focus();
	}else if(idCheck($('#adm_email').val()) == false){
		$('#adm_email').focus();
	}else if($('#check_yn').val() != 'Y' || $('#check_id').val() != $('#adm_email').val()){
		alert("아이디  중복확인을 해주세요.");
	}else if(checkPassword($('#adm_pwd').val())== false){
	//	alert("비밀번호는 영문/숫자/특수문자 2가지 이상 조합하여 8자 이상이어야 합니다.");
		$('#adm_pwd').focus();
	}else if($('#adm_pwd').val() != $('#adm_pwd1').val()){
		alert("입력한 비밀번호가 일치하지 않습니다.");
		$('#adm_pwd1').focus();
	} else if(confirm("B2B 관리자를 등록하시겠습니까?") == true){

		$.ajax({
			url : '/btb/btbAdminAddProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
				adm_email      : $('#adm_email').val(),	
				btbs_sq        : $('#btbs_sq').val(),
				btbs_nm        : $('#btb_site_nm').val(),
				adm_nm         : $('#adm_nm').val(),
				adm_pwd        : $('#adm_pwd').val(),
				adm_part 	   : $('#adm_part').val(),	
				adm_rank	   : $('#adm_rank').val(),		
				adm_hp	       : adm_hp,		            
				adm_tel        : adm_tel                    
			},
			success : function(jsonData) {
				console.log("success");
				alert("등록되었습니다.");
				window.location.replace("/btb/btbAdminList");
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});
		
	}else{
		return;
	}
	
}
//아이디 중복체크
function checkId(){

	if($('#admEmail').val()==''){
		alert('아이디를 입력하세요.');
		$('#admEmail').focus();
		return false;
	}
	
	if(idCheck($('#admEmail').val()) == false){
		$('#admEmail').focus();
	}else{

		$.ajax({
			url : '/admin/checkId',
			method : 'POST',
			dataType: 'JSON',
			data : {
				user_id	       : $('#admEmail').val()       
			},
			success : function(jsonData) {
				console.log("success");
				var useYn = jsonData.useYn;
				var checkYn = jsonData.checkYn;
				if(useYn == "Y"){
					$('#check_yn').val(checkYn);
					$('#check_id').val($('#admEmail').val());
					$("#msg").text("'"+$('#admEmail').val()+"'은 사용가능한 아이디입니다. 사용하시겠습니까?");
					$('#msg').css("color", "blue");
					$('#gubun').val("Y");
				}else{
					$('#msg').text("'"+$('#admEmail').val()+"'은 이미 사용 중입니다.");
					$('#msg').css("color", "red");
					$('#gubun').val("N");
				}				
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});
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
		    	$("#dim-layer").hide();
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
