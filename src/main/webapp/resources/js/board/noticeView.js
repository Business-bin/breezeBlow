/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	console.log("noticeView");

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


	var val = $('#stat').val();
	$('#bd_stat').val(val).attr("selected", "selected");

	if($('#stat').val() == '03'){
		$("#btn_mod").hide();
		$("#btn_del").hide();

		var sHtml = "";
		sHtml += "<option value='03' selected='selected'>삭제</option>";
		$("#bd_stat").html(sHtml);
		$('#bd_stat').attr("disabled", "disabled");
	}


	$('#btn_mod').click(function(){
		console.log("btn_mod");
		modNotice();
	});

	$('#delNotice').click(function(){
		console.log("btn_del");
		delNotice();
	});

	$("#goList").click(function() {
		$("#backForm").submit();
	});

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
          elPlaceHolder: "bd_cont", //textarea에서 지정한 id와 일치해야 합니다.
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
Name   : modNotice
Desc   : 공지사항 수정
Param  :
********************************************************************/
function modNotice(){

	var sHTML = oEditors.getById["bd_cont"].getIR();

	if($('#bd_nm').val()==''){
		alert('제목을 입력하세요.');
		$('#bd_nm').focus();
	}else if (sHTML == '' || sHTML == '<p><br></p>') {
		alert('내용을 입력하세요.');
		oEditors[0].exec("FOCUS",[]);
	}else if (calculate_byte(sHTML) > 65535) {
		alert('내용이 최대 문자수를 초과하였습니다.');
		oEditors[0].exec("FOCUS",[]);
	}else if(confirm("수정하시겠습니까?") == true){

		$.ajax({
			url : '/board/noticeViewProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
				bd_stat    : $('#bd_stat').val(),
				bd_nm 	   : $('#bd_nm').val(),
				bd_cont	   : sHTML,
				bd_sq      : $('#bd_sq').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("수정이 완료되었습니다.");
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
Name   : delNotice
Desc   : 공지사항 삭제
Param  :
********************************************************************/
function delNotice(){
	if($('#del_rsn').val()==''){
		alert('삭제사유를 입력하세요.');
		$('#del_rsn').focus();
	}else if(confirm("삭제하시겠습니까?") == true){

		$.ajax({
			url : '/board/noticeDelProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
			    bd_sq       : $('#bd_sq').val(),
			 	del_rsn     : $('#del_rsn').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("삭제가 완료되었습니다.");
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
Name   : goView
Desc   : 상세보기 리로드
Param  :
********************************************************************/
function goView(){
	$('#frm').attr('action', '/board/noticeView');
	$("#frm").submit();
}

///********************************************************************
//Name   : fileCheck
//Desc   : 첨부파일 체크
//Param  :
//********************************************************************/
//function fileCheck() {
//
//    var checkExt = true;
//    var value = '';
//    $("input[name=imageFile]").each(function(idx){
//    	if($(this).val() != ""){
//            // 파일 확장자 가져오기
//            value = $(this).val().slice($(this).val().lastIndexOf(".")+1).toLowerCase();
//            if(!checkFileExt(value)){
//            	checkExt = false;
//            	return false;
//            }
//    	}
//    });
//
//
//	if($('#bd_nm').val()==''){
//		alert('제목을 입력하세요.');
//		$('#bd_nm').focus();
//	}else if ($('#bd_cont').val() == '') {
//		alert('내용을 입력하세요.');
//		$('#bd_cont').focus();
//	}else if (!checkExt) {
//	  	alert(value+"는 허용된 파일 확장자가 아닙니다.");
//	}else if(confirm("저장 하시겠습니까?") == true){
//
//		var form = $('#frm')[0];
//	    var data = new FormData(form);
//
//		$.ajax({
//			type: "POST",
//			enctype: 'multipart/form-data',
//			url: "/board/fileCheck",
//			data: data,
//			processData: false,
//			contentType: false,
//			cache: false,
//			timeout: 600000,
//			success: function (data) {
//				if(data.message ==""){
//					modNotice();
//				}else{
//					alert(data.message);
//				}
//			},
//			error: function (e) {
//				console.log("ERROR : ", e);
//			}
//		});
//	}
//}
//
///********************************************************************
//Name   : checkFileExt
//Desc   : 파일 확장자 체크
//Param  :
//********************************************************************/
//function checkFileExt(val){
//	var extList = new Array("zip","hwp","doc","docx","xls","xlsx","ppt","pptx","png","gif","jpeg","jpg","txt");
//	for(var i=0; i<extList.length; i++){
//		if(val == extList[i]){
//			return true;
//		}
//	}
//	return false;
//}
//
//function modNotice(){
//
//	var val = $("#bd_stat option:selected").val();
//	$('#bd_stat').val(val);
//
//	var form = $('#frm')[0];
//    var data = new FormData(form);
//
//    $.ajax({
//        type: "POST",
//        enctype: 'multipart/form-data',
//        url: "/board/noticeViewProc",
//        data: data,
//        processData: false,
//        contentType: false,
//        cache: false,
//        timeout: 600000,
//        success: function (data) {
//            console.log("SUCCESS : ", data);
//            alert("저장 되었습니다.");
//            goView();
//        },
//        error: function (e) {
//            console.log("ERROR : ", e);
//        }
//    });
//}




//function fileDownload(fileNewName, fileName) {
//	$('#fileNewName').val(fileNewName);
//	$('#fileName').val(fileName);
//	$('#frm').attr('action', '/board/fileDownload');
//	$("#frm").submit();
//
//}

//function delFile(fileNewName, fileSq){
//
//	if(confirm("정말로 삭제 하시겠습니까?") == true){
//
//		$.ajax({
//			url : '/board/delFile',
//			method : 'POST',
//			dataType: 'JSON',
//			data : {
//				fileNewName   : fileNewName,
//				fileSq        : fileSq
//
//			},
//			success : function(jsonData) {
//				console.log("success");
//				alert("삭제 되었습니다.");
//				goView();
//			},
//			error : function(e) {
//				console.error('ajax 에러: ' + e.status);
//			}
//		});
//
//	}else{
//		return;
//	}
//
//}

///********************************************************************
//Name   : canFile
//Desc   : 파일 선택 취소
//Param  :
//********************************************************************/
//function canFile(val){
//	$('#imageFile'+val).val("");
//}



