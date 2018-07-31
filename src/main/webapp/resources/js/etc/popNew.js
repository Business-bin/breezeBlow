
var oEditors = [];
function smartE(){
	 nhn.husky.EZCreator.createInIFrame({
         oAppRef: oEditors,
         elPlaceHolder: "popCont", //textarea에서 지정한 id와 일치해야 합니다. 
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
            // oEditors.getById["content"].exec("PASTE_HTML", [""]);
         },
         fCreator: "createSEditor2"
     });
}
/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){

	var Now = new Date();
	var intime = Now.getHours() + " : " + Now.getMinutes();
	$("#monthfrom").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
	$("#monthfrom").datepicker({
		changeMonth: true,
		onSelect: function(dateText , inst){ 
			var dateArr = dateText.split("/"); 
			year = dateArr[2]; 
			month = dateArr[0];
			day = dateArr[1]; 
		}
	});

   $('#monthfromTime').timepicker({
    	timeFormat: 'H:mm',
        interval: 10,
        minTime: '9',
        maxTime: '10:00pm',
        defaultTime: intime,
        startTime: '9:00am',
        dynamic: false,
        dropdown: true,
        scrollbar: true
    });

   var date = new Date();
   date.setDate(date.getDate() + 7);
   $("#monthto").val($.datepicker.formatDate($.datepicker.ATOM, date));
   $("#monthto").datepicker({
		changeMonth: true,
		onSelect: function(dateText , inst){ 
			var dateArr = dateText.split("/"); 
			year = dateArr[2]; 
			month = dateArr[0];
			day = dateArr[1]; 
		}
   });
   $('#monthtoTime').timepicker({
   	timeFormat: 'H:mm',
       interval: 10,
       minTime: '9',
       maxTime: '10:00pm',
       defaultTime: intime,
       startTime: '9:00am',
       dynamic: false,
       dropdown: true,
       scrollbar: true
   });
	
    $("#popListBtn").click(function(){
    	location.href = "/etc/popList";
    });
    
	$("#layerPopup").hide();
	$("#dim-layer").hide();
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
	});
	
	var fileTarget = $('.filebox .upload-hidden'); 
	
	fileTarget.on('change', function(){ // 값이 변경되면 
		if(window.FileReader){ // modern browser 
			var filename = $(this)[0].files[0].name; 
		} else { // old IE 
			var filename = $(this).val().split('/').pop().split('\\').pop(); // 파일명만 추출 
				
		} 
		// 추출한 파일명 삽입 
		$(this).siblings('.upload-name').val(filename); 

		var tmppath = URL.createObjectURL(event.target.files[0]);
		$("#file_path").val(tmppath);
	});

	poptypeChange("01");

	//
	$("input[name='popType']:radio").change(function () {
		poptypeChange(this.value);
	});
	
	
    //팝업미리보기
    $("#popViewBtn").click(function(){
    	view();
    });
    
    $("#ex_chk").click(function(){
    	if($('input:checkbox[id="ex_chk"]').is(":checked") == true){
    		$("#monthfrom").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
    	} 
    });
    
    $("#ex_chk2").click(function(){ 
    	if($('input:checkbox[id="ex_chk2"]').is(":checked") == true){
    		var date = new Date();
    		date.setDate(date.getDate() + 7);
    		$("#monthto").val($.datepicker.formatDate($.datepicker.ATOM, date));
    	} 
    });
 
    
    $("#popModBtn").click(function (event) {
    	insertfile();
    });
    
    
});




/********************************************************************
Name   : number maxlength 체크 
Desc   :
Param  :
********************************************************************/
function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }    
}


/********************************************************************
Name   : 팝업 타입 체인지 
Desc   :
Param  :
********************************************************************/
function poptypeChange(value){
	
	//01 이미지형
	if(value == "01"){
		
		$("#popcontent").hide();
		$("#popimg").show();
		$("#poplink").show();
		$("#popsize").show();
		
		$("#imageFile").val("");
		$("#file_path").val("");
		$("#linkurl").val("");
		$("#popwidth").val("");
		$("#popheight").val("");
	} else {
		if(oEditors.length == 0){
			smartE()
		} else {
			oEditors.getById["popCont"].exec("SET_IR", [""]);
		}
		$("#popcontent").show();
		$("#popimg").hide();
		$("#poplink").hide();
		$("#popsize").hide();
	}
}

/********************************************************************
Name   : 팝업미리보기
Desc   :
Param  :
********************************************************************/
function view(){
	
	var st = $(":input:radio[name=popType]:checked").val();
	if(st == "01"){
		
		if($('#file_path').val() == "" ){
			alert("이미지를  선택해주세요 ");
			return false;
		}
		
		if($("#popwidth").val() == "" || Number($("#popwidth").val()) == 0 ){
			alert("가로사이즈를 입력해주세요 ");
			return false;
		}
		if($("#popheight").val() == "" ||  Number($("#popheight").val()) == 0 ){
			alert("세로사이즈를 입력해주세요 ");
			return false;
		}
		
		var width =  Number($("#popwidth").val())+10;
		var height =  Number($("#popheight").val())+50;
		$('#layerPopup').css('width', width+"px");
		$('#layerPopup').css('height',height+"px");
		var img = "<img src='' id='blah'  style='width:"+$("#popwidth").val()+"px; height:"+$("#popheight").val()+"px;' />";
		$("#innerHtml").html(img);
		$("#blah").fadeIn("fast").attr('src',$('#file_path').val());
	} else {
		
		$('#layerPopup').css('width',  "430px");
		$('#layerPopup').css('height', "730px");
		var sHTML = oEditors.getById["popCont"].getIR();
		
		$("#innerHtml").html(sHTML);
	}
	$("#dim-layer").show();
	$("#layerPopup").show();
	$("#layerPopup").draggable();

}

/********************************************************************
Name   : 팝업저장
Desc   :
Param  :
********************************************************************/
function insertfile(){
	if(confirm('저장 하시겠습니까?')==false) return;
	var st = $(":input:radio[name=popType]:checked").val();
	
	if(isEmpty($('#popNm').val())){
		alert("팝업제목  입력해주세요 ");
		return false;
	}
	
	if(st =="01"){
		if($('#file_path').val() == "" ){
			alert("이미지를  선택해주세요 ");
			return false;
		}
		if($("#popwidth").val() == "" || Number($("#popwidth").val()) == 0 ){
			alert("가로사이즈를 입력해주세요 ");
			return false;
		}
		if($("#popheight").val() == "" ||  Number($("#popheight").val()) == 0 ){
			alert("세로사이즈를 입력해주세요 ");
			return false;
		}
		
	} else {
		if(oEditors.getById["popCont"].getIR() == "<p><br></p>" ||  oEditors.getById["popCont"].getIR() == "" ){
			alert("팝업 내용을   입력해주세요 ");
			return false;
		}
	}
	
	var form = $('#popForm')[0];
    var data = new FormData(form);
    data.append("monthfrom",  $('#monthfrom').val());
    data.append("monthfromTime",  $('#monthfromTime').val());
    data.append("monthto",  $('#monthto').val());
    data.append("monthtoTime",  $('#monthtoTime').val());
    data.append("popwidth",  $('#popwidth').val());
    data.append("popheight",  $('#popheight').val());
	if(st == "02"){
		var sHTML = oEditors.getById["popCont"].getIR();
		data.append("popContData",sHTML);
	}
    
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/etc/popInsert",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS : ", data);
            location.href = "/etc/popList";
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}
