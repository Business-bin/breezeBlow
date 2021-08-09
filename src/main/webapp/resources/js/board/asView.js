/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	console.log("asView");

	//AS 접수완료
	$('#btn_mod1').click(function(){
		modAs('02');
	});
	//AS 처리완료
	$('#btn_mod2').click(function(){
		modAs('03');
	});
	//AS 배송중
	$('#btn_mod3').click(function(){
		modAs('04');
	});
	//AS 배송완료
	$('#btn_mod4').click(function(){
		modAs('05');
	});

	//AS 접수대기
	if($('#stat').val()=='01'){

		$('#stat_01').show();
		$('#stat_01_date').hide();
		$('#rep_cpl_comment').focus();
		$('#stat_02').hide();
		$('#stat_03').hide();
		$('#stat_04').hide();

		$('#btn_mod1').show();
		$('#btn_mod2').hide();
		$('#btn_mod3').hide();
		$('#btn_mod4').hide();
	//AS 처리중
	}else if($('#stat').val()=='02'){

		$('#stat_01').show();
		$('#stat_02').show();
		$('#stat_02_date').hide();
		$('#cpl_comment').focus();
		$('#stat_03').hide();
		$('#stat_04').hide();
		$('#rep_cpl_comment').attr("disabled", true);

		$('#btn_mod1').hide();
		$('#btn_mod2').show();
		$('#btn_mod3').hide();
		$('#btn_mod4').hide();
	//AS 처리완료
	}else if($('#stat').val()=='03'){

		$('#stat_01').show();
		$('#stat_02').show();
		$('#stat_03').show();
		$('#stat_03_date').hide();
		$('#ship_comment').focus();
		$('#stat_04').hide();
		$('#rep_cpl_comment').attr("disabled", true);
		$('#cpl_comment').attr("disabled", true);

		$('#btn_mod1').hide();
		$('#btn_mod2').hide();
		$('#btn_mod3').show();
		$('#btn_mod4').hide();
	//AS 배송중
	}else if($('#stat').val()=='04'){

		$('#stat_01').show();
		$('#stat_02').show();
		$('#stat_03').show();
		$('#stat_04').show();
		$('#stat_04_date').hide();
		$('#ship_clp_comment').focus();
		$('#rep_cpl_comment').attr("disabled", true);
		$('#cpl_comment').attr("disabled", true);
		$('#ship_comment').attr("disabled", true);

		$('#btn_mod1').hide();
		$('#btn_mod2').hide();
		$('#btn_mod3').hide();
		$('#btn_mod4').show();
	//AS 배송완료
	}else if($('#stat').val()=='05'){

		$('#stat_01').show();
		$('#stat_02').show();
		$('#stat_03').show();
		$('#stat_04').show();
		$('#rep_cpl_comment').attr("disabled", true);
		$('#cpl_comment').attr("disabled", true);
		$('#ship_comment').attr("disabled", true);
		$('#ship_clp_comment').attr("disabled", true);

		$('#btn_mod1').hide();
		$('#btn_mod2').hide();
		$('#btn_mod3').hide();
		$('#btn_mod4').hide();
	}

	$("#goList").click(function() {
		$("#backForm").submit();
	});
});

/********************************************************************
Name   : modAs
Desc   : AS 수정(상태변경)
Param  :
********************************************************************/
function modAs(stat){

	var message ="처리 완료하시겠습니까?";
	var smsCont ="";

	if(stat =='02'){
	//	message = "상태를 AS 접수완료로 변경하시겠습니까?";
		smsCont = "[BreezeBlow] 신청하신 AS를 접수하였습니다.";
		if($('#rep_cpl_comment').val()==""){
			alert("AS 접수 내용을 입력하세요.");
			$('#rep_cpl_comment').focus();
			return;
		}
	}else if(stat =='03'){
	//	message = "상태를 AS 처리완료로 변경하시겠습니까?";
		smsCont = "[BreezeBlow] 신청하신 AS의 처리가 완료되었습니다.";
		if($('#cpl_comment').val()==""){
			alert("AS 처리 내용을 입력하세요.");
			$('#cpl_comment').focus();
			return;
		}
	}else if(stat =='04'){
	//	message = "상태를 AS 배송중으로 변경하시겠습니까?";
		smsCont = "[BreezeBlow] 신청하신 AS기기의 배송이 시작되었습니다.";
		if($('#ship_comment').val()==""){
			alert("AS 배송 정보을 입력하세요.");
			$('#ship_comment').focus();
			return;
		}
	}else if(stat =='05'){
	//	message = "상태를 AS 배송완료로 변경하시겠습니까?";
		smsCont = "[BreezeBlow] 신청하신 AS기기의 배송이 완료되었습니다.";
		if($('#ship_clp_comment').val()==""){
			alert("AS 배송완료 정보을 입력하세요.");
			$('#ship_clp_comment').focus();
			return;
		}
	}

	if(confirm(message) == true){

		$.ajax({
			url : '/board/modAs',
			method : 'POST',
			dataType: 'JSON',
			data : {
				as_req_sq         : $('#as_req_sq').val(),
				stat              : stat,
				agr_sms_yn        : $('#agr_sms_yn').val(),
				rep_cpl_comment   : $('#rep_cpl_comment').val(),
				cpl_comment       : $('#cpl_comment').val(),
				ship_comment      : $('#ship_comment').val(),
				ship_clp_comment  : $('#ship_clp_comment').val()

			},
			success : function(jsonData) {
				console.log("success");
				sendEmail(smsCont);
				//SMS 발송
				if($('#agr_sms_yn').val() == "Y"){
					sendSms(smsCont);
				}
				alert("완료되었습니다.");
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
Name   : sendSms
Desc   : SMS 발송
Param  :
********************************************************************/
function sendSms(smsCont){

	$.ajax({
		async : false,
		url : '/sms/sendSmsType',
		method : 'POST',
		dataType: 'JSON',
		data : {
			    MEM_SQ        : $('#as_req_mem_sq').val(),
			    USER_EMAIL    : $('#mem_email').val(),
			    USER_NM       : $('#as_req_nm').val(),
			    USER_PHONE    : $('#user_hp').val(),
			    MSG           : smsCont
		},
		success : function(jsonData) {
			console.log("success");
		},
		error : function(e) {
			console.error('ajax 에러: ' + e.status);
		}
	});

}

/********************************************************************
Name   : sendEmail
Desc   : Email 발송
Param  :
********************************************************************/
function sendEmail(smsCont){

	$.ajax({
		async : false,
		url : '/common/sendEmail',
		method : 'POST',
		dataType: 'JSON',
		data : {
				 R_ADM_EMAIL : $('#mem_email').val(),
				 R_AS_STAT   : smsCont,
				 as_req_nm : $("#as_req_nm").val(),
				 as_req_mem_sq : $("#as_req_mem_sq").val()

		},
		success : function(jsonData) {
			console.log("success");
		},
		error : function(e) {
			console.error('ajax 에러: ' + e.status);

		}
	});

}


/********************************************************************
Name   : goView
Desc   : 상세보기 리로드
Param  :
********************************************************************/
function goView(){
	$("#stat").val("");
	$('#frm').attr('action', '/board/asView');
	$("#frm").submit();
}

