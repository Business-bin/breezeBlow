
/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

    $("#mailSetBtn").click(function (event) {
    	updateData('01');
    });
    
    $("#alarmSetBtn").click(function (event) {
    	updateData('02');
    });
    
});


/********************************************************************
Name   : updateData
Desc   :
Param  :
********************************************************************/
function updateData(type){
	if(type == '01' ){
		if(isEmpty($('#mailHost').val())){
			alert('SMTP HOST를 입력하세요.');
			return false;
		}
		if(isEmpty($('#mailPort').val())){
			alert('SMTP PORT를 입력하세요.');
			return false;
		}
		if(isEmpty($('#mailId').val())){
			alert('계정 ID를 입력하세요.');
			return false;
		}
		if(isEmpty($('#mailPwd').val())){
			alert('계정 비밀번호를 입력하세요.');
			return false;
		}
		if($(':radio[name="sslYn"]:checked').val() == 'Y' && $('#sslPort').val()==''){
			alert('SSL PORT를 입력하세요.');
			return false;
		}
	} else {
		
		if($("#atoMin").val() == "" || Number($("#atoMin").val()) == 0 ){
			alert('아토피 지수  최소길이를 입력하세요.');
			return false;
		}
		if($("#atoMax").val() == "" || Number($("#atoMax").val()) == 0 ){
			alert('아토피 지수  최대길이를 입력하세요.');
			return false;
		}
	}
	
	if(confirm('저장 하시겠습니까?')==false) return;
	
	$.ajax({async : false
		, url: '/etc/modEtcInit'
		, dataType: 'JSON'
		, type: 'POST'
		, data: {
			R_MOD_TYPE : type,
			R_ID : $("#mailId").val(),
			R_PWD : $("#mailPwd").val(),
			R_HOST : $("#mailHost").val(),
			R_PORT : $("#mailPort").val(),
			R_SSL_YN : $(':radio[name="sslYn"]:checked').val(),
			R_SSL_PORT : $("#sslPort").val(),
			R_ATO_MIN : $("#atoMin").val(),
			R_ATO_MAX : $("#atoMax").val()
		}
		, success: function(data) {
			 console.log("SUCCESS : ", data);
			 location.href = "/etc/etcInit";
		}
		,complete : function(data) {
		}
		, error: function(xhr, ajaxOptions, thrownError) {
	
		}
	});
}
