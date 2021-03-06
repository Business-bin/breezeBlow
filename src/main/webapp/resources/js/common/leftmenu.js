/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){


	if($('#s_adm_class').val() == 3 || $('#s_adm_class').val() == 4){
		$('#btnBtb1').hide();
		$("#btnAppList").hide();
		$("#btnBoard").hide();
		$("#btnEtc").hide();
	}

	if($('#s_adm_class').val() == 2 || $('#s_adm_class').val() == 4){
		$('#btnAdmin1').hide();
	}

	$("#btnAdmin1").click(function(){
		location.href="/admin/adminList"
	});

	$('#btnMain').click(function(){
		console.log("btn_sch");
		location.href="/main";
	});

	$('#btnSms').click(function(){
		location.href="/sms/smsList";
	});

	$("#btnMemList").click(function(){
		location.href="/mem/memList";
	});

	$("#btnModelList").click(function(){
		if($("#s_adm_class").val() == 3 || $("#s_adm_class").val() == 4){
			location.href="/product/productList";
		}else{
			location.href="/product/modelList";
		}
	});

	$('#btnBtb').click(function(){
		console.log("btnBtb");
		location.href="/btb/btbList";
	});

	$('#btnBoard').click(function(){
		if($('#s_adm_class').val() == 1 || $('#s_adm_class').val() == 2){
			location.href="/board/asList";
		}else{
			location.href="/board/noticeList";
		}
	});

	$("#btnAppList").click(function(){
		location.href="/app/appVrsList";
	});

	$("#btnEtc").click(function(){
		location.href="/etc/popList";
	});

	$("#btnStat").click(function(){
		location.href="/status/status";
	});

});


function logout(){
	$.ajax(
			{async : true
			,type : "POST"
			,url : "/login/logout"
			,dataType : "json"
			,data: {R_SADM_EMAIL : $('#loginId').val(),
				    R_SADM_PWD : $('#loginPw').val()
			 }
			,success : function(data) {
				location.href="/";
			}
			,error:	function(request,status,error){

			}
	});
}