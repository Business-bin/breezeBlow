/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	$('#myInfo').click(function(){
		$('#myinfoForm').attr('action', '/login/adminView');
		$("#myinfoForm").submit();
	});


	$('#logout').click(function(){
		if (confirm("로그아웃 하시겠습니까?") == true){
			logout()
		}
	});
	
});

/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
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