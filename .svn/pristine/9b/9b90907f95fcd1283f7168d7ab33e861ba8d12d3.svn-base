/********************************************************************
Name   : APP버전 상세
Desc   :
Param  :
********************************************************************/
$(function(){
	/* 신규/상세/수정 구분 */
	if($("#gubun").val() == "new"){
		$("#modApp").hide();
	}else{
		$("#addApp").hide();
	}

	/* 수정 */
	$("#modApp").click(function(){
		if(isEmpty($("#appVrs").val())){
			alert("버전을 입력해주세요.");
			return false;
		}
		if(trim($("#appVrs").val()).length != $("#appVrs").val().length){
			alert("버전에는 공백이 들어갈 수 없습니다.");
			return false;
		}
		if(isEmpty($("#appVnm").val())){
			alert("내용을 입력해주세요.");
		}
		ajaxForm("insertAppForm","/app/updateAppVrs","수정되었습니다.");
	});

	/* 리스트로 이동 */
	$("#backAppList").click(function(){
		$("#backForm").submit();
	});

	/* 등록 */
	$("#addApp").click(function(){
		if(isEmpty($("#appVrs").val())){
			alert("버전을 입력해주세요.");
			return false;
		}
		if(trim($("#appVrs").val()).length != $("#appVrs").val().length){
			alert("버전에는 공백이 들어갈 수 없습니다.");
			return false;
		}
		if(isEmpty($("#appVnm").val())){
			alert("내용을 입력해주세요.");
			return false;
		}
		if(isEmpty($("#appOs").val())){
			alert("OS를 선택해주세요.");
			return false;
		}

		ajaxForm("insertAppForm","/app/insertAppVrs","등록되었습니다.");
	});

	/* 탭이동 */
	$(".tabmenu>li").eq(0).addClass("active");
});

function ajaxForm(formId, url, msg){
	var formData = $("#"+formId).serialize();
	$.ajax({
		type : "post",
		url : url,
		data : formData,
		success : function(result){
			alert(msg);
		},
		error : function(result){

		}
	})
}