/********************************************************************
Name   : 전역 환경 설정
Desc   :
Param  :
********************************************************************/
var oEditors = [];
$(function(){
	/* 레이어 팝업 */
	$("#addPoly").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
		$("#layerPopup a").focus();
		return false;
	});
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
	});
	/* 레이어 팝업 */

	/* 신규/상세/수정 구분 */
	if($("#gubun").val() == "new"){
		$("#modPoly").hide();
	}else{
		$("#addPoly").hide();
	}

	/* 수정 */
	$("#modPoly").click(function(){
		var pHtml = oEditors.getById["polyCont"].getIR();
		$("#polyCont").html(pHtml);
		if(isEmpty($("#polyNm").val())){
			alert("동의종류를 선택해주세요.");
			$("#dim-layer").hide();
			$("#layerPopup").hide();
			return false;
		}
		if(isEmpty($("#polyCont").val())){
			alert("내용을 입력해주세요.");
			$("#dim-layer").hide();
			$("#layerPopup").hide();
			return false;
		}
		$.ajax({
			type : "POST",
			url : "/app/checkPoly",
			data : {
				polyNm : $("#polyNm").val(),
				polySq : $("#polySq").val()
			},
			success : function(result){
				if($("#stat2").prop("checked") == true){
					if(result == 0){
						alert($("#polyNm").val()+"은 1개(필수) 노출 되어야 합니다.");
						return false;
					}
				}
				ajaxForm("insertPolyForm","/app/updatePoly","수정되었습니다.");
				
			},
			error : function(error){
				alert(error);
			}
		});

	});

	/* 리스트로 이동 */
	$("#backPolyList").click(function(){
		$("#backForm").submit();
	});

	/* 신규 등록 */
	$("#add").click(function(){
		var pHtml = oEditors.getById["polyCont"].getIR();
		$("#polyCont").html(pHtml);
		if(isEmpty($("#polyNm").val())){
			alert("동의종류를 선택해주세요.");
			$("#dim-layer").hide();
			$("#layerPopup").hide();
			return false;
		}
		if(isEmpty($("#vrs").val())){
			alert("버전정보를 입력해주세요.");
			$("#dim-layer").hide();
			$("#layerPopup").hide();
			return false;
		}
		if(trim($("#vrs").val()).length != $("#vrs").val().length){
			alert("버전정보에는 공백이 들어갈 수 없습니다.");
			$("#dim-layer").hide();
			$("#layerPopup").hide();
			return false;
		}
		if(isEmpty($("#polyCont").val())){
			alert("내용을 입력해주세요.");
			$("#dim-layer").hide();
			$("#layerPopup").hide();
			return false;
		}
		if($("#stat1").prop("checked") == false && $("#stat2").prop("checked") == false){
			alert("현재상태를 선택해주세요.");
			$("#dim-layer").hide();
			$("#layerPopup").hide();
			return false;
		}
		ajaxForm("insertPolyForm","/app/insertPoly","등록되었습니다.");
		$("#dim-layer").hide();
		$("#layerPopup").hide();
	});

	$(".tabmenu>li").eq(1).addClass("active");

      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "polyCont", //textarea에서 지정한 id와 일치해야 합니다.
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "/resources/SE2/SmartEditor2Skin.html",
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,
              fOnBeforeUnload : function(){
              }
          },
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              //oEditors.getById["ir1"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
          },
          fCreator: "createSEditor2"
      });
});

function ajaxForm(formId, url, msg){
	var form = $("#"+formId).serialize();
	$.ajax({
		type : "POST",
		url : url,
		data : form,
		success : function(result){
			alert(msg);
			location.href="/app/polyList";
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}