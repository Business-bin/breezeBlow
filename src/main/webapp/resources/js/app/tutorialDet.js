/********************************************************************
Name   : 전역 환경 설정
Desc   :
Param  :
********************************************************************/
var oEditors = [];
$(function(){
	/* 레이어 팝업 */
	$("#addTut").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
		$("#layerPopup a").focus();
		return false;
	});
	$("#delTut").click(function(){
		$("#dim-layer").show();
		$("#layerPopup2").show();
		$("#layerPopup2 a").focus();
		return false;
	});
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
	});
	$("#close2").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup2").hide();
	});
	/* 레이어 팝업 */

	/* 신규/상세/수정 구분 */
	if($("#gubun").val() == "new"){
		$("#modTut").hide();
		$("#delTut").hide();
	}else{
		$("#addTut").hide();
	}

	/* 수정 */
	$("#modTut").click(function(){
		var pHtml = oEditors.getById["tutCont"].getIR();
		$("#tutCont").html(pHtml);
		if(isEmpty($("#tutNm").val())){
			alert("튜토리얼 이름을 입력해주세요.");
			return false;
		}
		if(isEmpty($("#tutCont").val())){
			alert("내용을 입력해주세요.");
			return false;
		}
		if(isEmpty($("#tutNm").val())){
			alert("튜토리얼이름을 입력해주세요.");
			return false;
		}
		ajaxForm("insertTutorialForm","/app/updateTutorial","수정되었습니다.");
		/*$.ajax({
			type : "POST",
			url : "/app/checkTutorial",
			data : {
				tutSq : $("#tutSq").val()
			},
			success : function(result){
				if($("#stat2").prop("checked") == true){
					if(result == 0){
						alert("튜토리얼 1개(필수)는 필수 활성 되어야 합니다.");
						return false;
					}
				}
			},
			error : function(error){
				alert(error);
			}
		});*/

	});

	/* 리스트로 이동 */
	$("#backTutList").click(function(){
		$("#backForm").submit();
	});

	/* 신규 등록 */
	$("#add").click(function(){
		var pHtml = oEditors.getById["tutCont"].getIR();
		$("#tutCont").html(pHtml);
		if(isEmpty($("#tutNm").val())){
			alert("튜토리얼이름을 입력해주세요.");
			return false;
		}
		if($("#stat1").prop("checked") == false && $("#stat2").prop("checked") == false){
			alert("현재상태를 선택해주세요.");
			return false;
		}
		if(isEmpty($("#tutCont").val())){
			alert("내용을 입력해주세요.");
			return false;
		}
		ajaxForm("insertTutorialForm","/app/insertTutorial","등록되었습니다.");
		$("#dim-layer").hide();
		$("#layerPopup").hide();
	});

	$("#del").click(function(){
		ajaxForm("insertTutorialForm","/app/deleteTutorial","삭제되었습니다.");
		$("#dim-layer").hide();
		$("#layerPopup2").hide();
	});
	$(".tabmenu>li").eq(2).addClass("active");

      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "tutCont", //textarea에서 지정한 id와 일치해야 합니다.
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
			location.href="/app/tutorialList";
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}