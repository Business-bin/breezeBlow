/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	console.log("adminView");

	// 레이어 팝업
	$("#layerPopup").hide();
	$("#dim-layer").hide();
	$("#layerPopup1").hide();
	$("#dim-layer1").hide();

	// 레이어 팝업 드래그 이동
	$("#layerPopup").draggable();
	$("#layerPopup1").draggable();

	// 3권한 관리자
	if($('#s_adm_class').val() == 3){
		$("#senkoTr").hide(); // 성별, 사원번호 숨김
	}else{
		$("#senkoTr").show();
	}

	// 삭제 버튼 클릭
	$("#btn_del").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
		$("#layerPopup a").focus();
		return false;
	});

	// 비밀번호 변경 버튼 클릭
	$("#btn_pwd").click(function() {
		$("#dim-layer1").show();
		$("#layerPopup1").show();
		$("#layerPopup a").focus();
		return false;
	});

	// 닫기 버튼 클릭
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
	});

	// 닫기 버튼 클릭
	$("#close1").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
	});

	// 2관리자, 4관리자
	if($('#s_adm_class').val() == 2 || $('#s_adm_class').val() == 4){
		$('#btn_del').hide(); // 삭제 버튼 숨김
		$('#btn_pwd').hide(); // 비밀번호 변경 버튼 숨김
		$('#btn_mod').hide(); // 수정완료 버튼 숨김
	}

	// 성별 select에 선택
	var val = $('#gender').val();
	$("select[name='adm_gen']").val(val).attr("selected", "selected");

	// 지역번호 select에 선택
	var val2 = $('#adm_hp1').val();
	$("select[name='hp_1']").val(val2).attr("selected", "selected");

	// 휴대전화번호 앞자리 select에 선택
	var val3 = $('#adm_tel1').val();
	$("select[name='tel_1']").val(val3).attr("selected", "selected");

	// 상태가 삭제일 때
	if($('#stat').val() == '03'){
		$('#tbody').find("input").prop("disabled", true);
		$('#adm_gen').attr("disabled", true);
		$('#hp_1').attr("disabled", true);
		$('#tel_1').attr("disabled", true);
		$("#btn_pwd").hide();
		$("#btn_mod").hide();
		$("#btn_del").hide();
	}

	// 수정완료 버튼 클릭 이벤트
	$('#btn_mod').click(function(){
		console.log("btn_mod");
		modAdmin();
	});

	// 삭제(팝업) 버튼 클릭 이벤트
	$('#delAdmin').click(function(){
		console.log("btn_del");
		delAdmin();
	});

	// 비밀번호변경(팝업) 버튼 클릭 이벤트
	$('#changePwd').click(function(){
		console.log("btn_mod");
		changePwd();
	});

	// 목록 버튼 클릭 이벤트
	$("#goList").click(function() {
		$("#backForm").submit();
	});

	//관리자명
	$('#adm_nm').keyup(function(event) {
		regExp_char('adm_nm');
	});

	//사원번호
	$('#adm_empn').keyup(function(event) {
		regExp_char('adm_empn');
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
	/* 휴대전화번호   */

	/* 전화번호   */
	$('#tel_2').keyup(function(event) {
		regExp_number('tel_2');
		proc(event,'tel_2','tel_3')
	});

	$('#tel_3').keyup(function(event) {
		regExp_number('tel_3');
	});
	/* 전화번호   */
});

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
	if($('#adm_nm').val()==''){
		alert('관리자명을 입력하세요.');
		$('#adm_nm').focus();
	}else if ($('#s_adm_class').val() == 3 && $('#adm_gen').val() == '') {
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


