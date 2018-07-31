/********************************************************************
Name   : ready                                                 
Desc   : 
Param  :            
********************************************************************/	
$(document).ready(function(){
	
	console.log("faqAdd");
	
	$('#btn_add').click(function(){
		console.log("btn_add");
		addFaq();
		
	});

	$("#goList").click(function() {
		location.href="/board/faqList";
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
              //oEditors.getById["ir1"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
          },
          fCreator: "createSEditor2"
      });
    
});

var oEditors2 = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors2,
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
              //oEditors.getById["ir1"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
          },
          fCreator: "createSEditor2"
      });
      
    
});



/********************************************************************
Name   : addFaq                                            
Desc   : FAQ 등록
Param  :            
********************************************************************/
function addFaq(){
	
	var sHTML = oEditors.getById["bd_cont"].getIR();
	var sHTML2 = oEditors2.getById["bd_reply"].getIR();

	if($('#bd_nm').val()==''){
		alert('제목을 입력하세요.');
		$('#bd_nm').focus();
	}else if (sHTML == '' || sHTML == '<p><br></p>') {
		alert('질문을 입력하세요.');
		oEditors[0].exec("FOCUS",[]);
	}else if (sHTML2 == '' || sHTML2 == '<p><br></p>') {
		alert('답변을 입력하세요.');
		oEditors2[0].exec("FOCUS",[]);
	}else if (calculate_byte(sHTML) > 65535) {
		alert('질문이 최대 문자수를 초과하였습니다.');
		oEditors[0].exec("FOCUS",[]);
	}else if (calculate_byte(sHTML2) > 65535) {
		alert('답변이 최대 문자수를 초과하였습니다.');
		oEditors2[0].exec("FOCUS",[]);
	}else if(confirm("등록 하시겠습니까?") == true){
    
		$.ajax({
			url : '/board/faqAddProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
				bd_stat    : $('#bd_stat').val(),
				bd_tp_2    : $('#bd_tp_2').val(),
				bd_nm 	   : $('#bd_nm').val(),	
				bd_cont	   : sHTML,
				bd_reply   : sHTML2,
				bd_tp_1    : $('#bd_tp_1').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("등록 되었습니다.");
				window.location.replace("/board/faqList");
			},
			error : function(e) {
				console.error('ajax 에러: ' + e.status);
			}
		});
	}
    
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
//	if ($('#bd_nm').val() == '') {
//		alert('제목을 입력하세요.');
//		$('#bd_nm').focus();
//	}else if ($('#bd_cont').val() == '') {
//		alert('내용을 입력하세요.');
//		$('#bd_cont').focus();
//	}else if ($('#bd_stat').val() == '') {
//		alert('오픈여부를 선택하세요.');
//		$('#bd_stat').focus();
//	}else if (!checkExt) {
//		alert(value+"는 허용된 파일 확장자가 아닙니다.");
//	}else if(confirm("신규 등록하시겠습니까?") == true){
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
//					addFaq();
//				}else{
//					alert(data.message);
//				}	
//			},
//			error: function (e) {
//				console.log("ERROR : ", e);
//			}
//		});
//	}
//
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




///********************************************************************
//Name   : delFile                                            
//Desc   : 파일 삭제
//Param  :            
//********************************************************************/
//function delFile(val){
//	$('#imageFile'+val).val("");
//}


