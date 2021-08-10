
$(document).ready(function(){
	$("#regMem").click(function() {

	});

	$("#addrBtn").click(function(){
		var pop = window.open("/common/jusoPopup?gubun=3","pop3","width=570,height=420, scrollbars=yes, resizable=yes");
	});
});


$("#pprtAli").change(function(){
	if($("#pprtAli").val() == ""){
		$("#aliText").prop("readonly","");
	}else{
		$("#aliText").val("");
		$("#aliText").prop("readonly","readonly");
	}
});

function jusoCallBack3(siNm,sggNm, emdNm,lnbrMnnm,lnbrSlno,addrDetail){
	$("#regAddr1").val(siNm);
	$("#regAddr2").val(sggNm);
	$("#regAddr3").val(emdNm);
	var detail="";
	if(lnbrMnnm != null && lnbrMnnm != ""){
		detail += lnbrMnnm;
	}
	if(lnbrSlno != null && lnbrSlno != ""){
		detail += "-"+lnbrSlno;
	}
	$("#regAddr4").val(detail);
	if(addrDetail != null && addrDetail != ""){
		$("#regAddr5").val(addrDetail);
	}
}

function regProd(){
	if($("#regAddr1").val() == ""){
		alert("주소를 입력하세요.");
		return;
	}
	if($("#memSq").val() == ""){
		alert("회원을 선택하세요.");
		return;
	}
	$.ajax({
		type : "POST",
		url : "/mem/regProductUpdate",
		data : {
			pprtSq : $("#pprtSq").val(),
			addr1 : $("#regAddr1").val(),
			addr2 : $("#regAddr2").val(),
			addr3 : $("#regAddr3").val(),
			addr4 : $("#regAddr4").val(),
			addr5 : $("#regAddr5").val(),
			pprtAli : $("#pprtAli").val(),
			aliText : $("#aliText").val(),
			memSq : $("#memSq").val()
		},
		success : function(result){
			alert("사용자 기기가 등록되었습니다.");
			$("#pprtMac").val(result.model.pprtMac);
			$("#detForm").submit();
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}

function goList(){
	location.href="productList";
}