/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	console.log("qnaView");
	/* 레이어 팝업 */
	$("#layerPopup").hide();
	$("#dim-layer").hide();

	$("#layerPopup").draggable();

	$("#btn_del").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
		return false;
	});
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
	});
	/* 레이어 팝업 */

	$('#btn_mod').click(function(){
		console.log("btn_mod");
		modQna();
	});

	$('#delQna').click(function(){
		console.log("btn_del");
		delQna();
	});

	$("#goList").click(function() {
		$("#backForm").submit();
	});

	if($('#stat').val() == '03'){
		$("#btn_mod").hide();
		$("#btn_del").hide();

	}

	var val = $('#bd_ans_yn').val();
	$('#reply_yn').val(val).attr("selected", "selected");

});



/********************************************************************
Name   :
Desc   : 에디터 적용
Param  :
********************************************************************/
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "bd_reply", //textarea에서 지정한 id와 일치해야 합니다.
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
              //oEditors.getById["bd_cont"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
          },
          fCreator: "createSEditor2"
      });


});


/********************************************************************
Name   : modQna
Desc   : Q&A 수정
Param  :
********************************************************************/
function modQna(){

	var sHTML = oEditors.getById["bd_reply"].getIR();

	if(sHTML=='' || sHTML == '<p><br></p>'){
		alert('답변을 입력하세요.');
		oEditors[0].exec("FOCUS",[]);
	}else if (calculate_byte(sHTML) > 65535) {
		alert('답변이 최대 문자수를 초과하였습니다.');
		oEditors[0].exec("FOCUS",[]);
	}else if(confirm("답변을 등록하시겠습니까?") == true){

		$.ajax({
			url : '/board/qnaViewProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
				bd_reply     : sHTML,
				bd_sq        : $('#bd_sq').val(),
				bd_ans_yn    : $('#bd_ans_yn').val(),

			},
			success : function(jsonData) {
				console.log("success");
				alert("답변등록이 완료되었습니다.");
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
Name   : delQna
Desc   : Q&A 삭제
Param  :
********************************************************************/
function delQna(){

	if($('#comment').val()==''){
		alert('삭제사유를 입력하세요.');
		$('#comment').focus();
	}else if(confirm("삭제 하시겠습니까?") == true){

		$.ajax({
			url : '/board/qnaDelProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
			    bd_sq       : $('#bd_sq').val(),
			 	comment     : $('#comment').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("삭제가 완료되었습니다.");
//				goView();
				location.href="/board/qnaList"
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
Desc   : 상세보기
Param  :
********************************************************************/
function goView(){

	$('#frm').attr('action', '/board/qnaView');
	$("#frm").submit();
}


