/********************************************************************
Name   : ready                                                 
Desc   : 
Param  :            
********************************************************************/	
$(document).ready(function(){
	
	console.log("adminView");
	
	/* 레이어 팝업 */
	$("#layerPopup").hide();
	$("#dim-layer").hide();
	$("#layerPopup1").hide();
	$("#dim-layer1").hide();
	$("#btn_del").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
		$("#layerPopup a").focus();
		return false;
	});
	$("#btn_pwd").click(function() {
		$("#dim-layer1").show();
		$("#layerPopup1").show();
		$("#layerPopup a").focus();
		return false;
	});	
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
	});
	$("#close1").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
	});
	/* 레이어 팝업 */
	
	
	var val = $('#gender').val();
	$("select[name='adm_gen']").val(val).attr("selected", "selected");
	
	var val2 = $('#adm_hp1').val();
	$("select[name='hp_1']").val(val2).attr("selected", "selected");
	
	var val3 = $('#adm_tel1').val();
	$("select[name='tel_1']").val(val3).attr("selected", "selected");
	
	if($('#stat').val() == '02'){
//		$('#buttonDiv').find("button").prop("disabled", true);
		$('#tbody').find("input").prop("readonly", true);
		$('#adm_gen').attr("disabled", true);
		$('#hp_1').attr("disabled", true);
		$('#tel_1').attr("disabled", true);
		
		$("#btn_pwd").hide();
		$("#btn_mod").hide();
		$("#btn_del").hide();
	
	}

    
	$('#btn_mod').click(function(){
		console.log("btn_mod");
		modAdmin();
	});
	
	$('#delAdmin').click(function(){
		console.log("btn_del");
		delAdmin();
	});
	
	
	$('#changePwd').click(function(){
		console.log("btn_mod");
		changePwd();
	});
	
	
});


function modAdmin(){
	
	var adm_hp = $('#hp_1').val()+'-'+ $('#hp_2').val()+'-'+$('#hp_3').val();
	var adm_tel = '';
	if($('#tel_2').val()== '' && $('#tel_3').val()== ''){
		adm_tel = '';
	}else{
		adm_tel = $('#tel_1').val()+'-'+$('#tel_2').val()+'-'+$('#tel_3').val();
	}
	
	var val = $("#adm_gen option:selected").val();
	$('#adm_gen').val(val);

	if($('#adm_nm').val()==''){
		alert('관리자명을 입력하세요.');
		$('#adm_nm').focus();
	}else if ($('#adm_gen').val() == '') {
		alert('성별을 입력하세요.');
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
	}else if(confirm("수정 하시겠습니까?") == true){
		
		$.ajax({
			url : '/admin/adminViewProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
				adm_sq         : $('#adm_sq').val(),
				adm_nm         : $('#adm_nm').val(),
				adm_gen        : $('#adm_gen').val(),
				adm_empn 	   : $('#adm_empn').val(),	
				adm_part	   : $('#adm_part').val(),		
				adm_rank	   : $('#adm_rank').val(),	
				adm_hp	       : adm_hp,		            
				adm_tel        : adm_tel                    
			},
			success : function(jsonData) {
				console.log("success");
				alert("정보 수정이 완료되었습니다.");
				window.location.replace("/admin/adminList");
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
	}else if(confirm("삭제 하시겠습니까?") == true){
		
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
	$('#admViewForm').attr('action', '/admin/adminView');
	$("#admViewForm").submit();
}

/********************************************************************
Name   : addAdmin                                            
Desc   : 관리자 신규 생성
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
		alert("비밀번호는 영문/숫자/특수문자 2가지 이상 조합하여, 8자가 넘는 경우 변경 가능합니다.");
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


