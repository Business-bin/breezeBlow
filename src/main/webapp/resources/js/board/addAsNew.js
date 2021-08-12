$(document).ready(function(){
	$("#getRegCpNm").click(function(){
		showPop();
		
	});
});

function showPop() {
	$("#layerPopup").show();
}

function closePop(){
	$("#layerPopup").hide();
}

function goAsList(){
	location.href="/board/asList";
}

function getModel(){
	$.ajax({
		type : "POST",
		url : "/board/getModel",
		dataType: 'JSON',
		data : {
			memSq    : $('#memSq').val()
		},
		success : function(result){
			var list = result.modelList;
			if(list.length == 0){
				alert("보유한 제품이 없습니다.");
				$("#mdSq option").remove();
			}else{
				$("#mdSq option").remove();
				for(var i=0; i<list.length; i++){
					$("#mdSq").append("<option value='"+list[i].PPRT_MAC+"'>"+list[i].CP_NM);
				}
			}
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}

function addAsNew(){
	if($('#memSq').val() == ''){
		alert("접수 신청 고객을 선택하세요.");
		return;
	}
	if($('#mdSq').val() == ''){
		alert("접수 제품을 선택하세요.");
		return;
	}
	if($('#content').val() == ''){
		alert("접수 내용을 입력하세요.");
		return;
	}
	$.ajax({
		type : "POST",
		url : "/board/addAs",
		dataType: 'JSON',
		data : {
			memSq    : $('#memSq').val(),	
			mdSq 	   : $('#mdSq').val(),	
			mdNM	   : $("#mdSq option:selected").text(),
			content    : $('#content').val()
		},
		success : function(result){
			alert("AS 접수 되었습니다.");
			location.href="/board/asList";
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}