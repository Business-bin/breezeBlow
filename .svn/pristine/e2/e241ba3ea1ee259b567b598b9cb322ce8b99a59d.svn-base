/********************************************************************
Name   : ready                                                 
Desc   : 
Param  :            
********************************************************************/	
//document.domain = "test.com";
$(document).ready(function(){
	
	console.log("btbAdd");
	
	$("#layerPopup").hide();
	$("#dim-layer").hide();
	
	$("#layerPopup").draggable();

	$("#btn_check").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
		$('#btbsNm').val($('#btbs_nm').val());
		$("#msg").text("");
		if($('#btbsNm').val() != ""){
			checkBtbNm();
		}
		
	//	return false;
	});	
	
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
	});
	
	$('#addrSearchBtn').click(function(){
		goPopup();
	});
	
	
	$('#btn_add').click(function(){
		addBtb();
		
	});
	
	$('#checkBtbNm').click(function(){
		checkBtbNm();
	});
	
	$("#goList").click(function() {
		location.href="/btb/btbList";
	});
	
	$("#btbsNm").keypress(function(e) {
	    if(e.keyCode==13) {
		   checkBtbNm();
	    }
	}); 
	
	$('#selBtbsNm').click(function(){
		if($('#gubun').val() == "N"){
			return false;
		}else{
			if($('#btbsNm').val() == "" || $('#check_yn').val() !="Y" || $('#check_id').val() != $('#btbsNm').val()){
				alert("사이트명을 중복확인하세요.");
				$('#btbsNm').focus();
			}else{
				$('#btbs_nm').val($('#btbsNm').val());
				$("#dim-layer").hide();
				$("#layerPopup").hide();
			}
		}
	});	
	
	//사이트명
	$('#btbs_nm').keyup(function(event) {
		regExp_char('btbs_nm');
	});
	
	//회사명
	$('#comp_nm').keyup(function(event) {
		regExp_char('comp_nm');
	});
	
	//담당자명
	$('#btbm_nm').keyup(function(event) {
		regExp_char('btbm_nm');
	});
	
	//직급
	$('#btbm_rank').keyup(function(event) {
		regExp_char('btbm_rank');
	});
	
	/* 휴대전화번호   */
	$('#hp_2').keyup(function(event) {
		regExp_number('hp_2');
		proc(event,'hp_2','hp_3')
	});
	
	$('#hp_3').keyup(function(event) {
		regExp_number('hp_3');
	});
	
});


/********************************************************************
Name   : 주소팝업                                                 
Desc   : 
Param  :            
********************************************************************/
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	var pop = window.open("/common/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


/********************************************************************
Name   : addAdmin                                            
Desc   : 관리자 신규 생성
Param  :            
********************************************************************/
function addBtb(){
	
	var btbm_tel = $('#hp_1').val()+'-'+ $('#hp_2').val()+'-'+$('#hp_3').val();

	if ($('#btbs_nm').val() == '') {
		alert('사이트명을 입력하세요.');
		$('#btbs_nm').focus();
	}else if($('#comp_nm').val()==''){
		alert('회사명을 입력하세요.');
		$('#comp_nm').focus();
	}else if ($('#btbs_domain').val() == '') {
		alert('사이트URL을 입력하세요.');
		$('#btbs_domain').focus();
	}else if($('#zipNo').val()==''){
		alert('회사주소를 입력하세요.');
		$('#zipNo').focus();
	}else if($('#jibunAddr').val()==''){
		alert('회사주소를 입력하세요.');
		$('#jibunAddr').focus();
	}else if($('#btbs_cur_templ').val()==''){
		alert('템플릿정보를 입력하세요.');
		$('#btbs_cur_templ').focus();
	}else if ($('#btbm_nm').val() == '') {
		alert('담당자명을 입력하세요.');
		$('#btbm_nm').focus();
	}else if ($('#btbm_rank').val() == '') {
		alert('직급을 입력하세요.');
		$('#btbm_rank').focus();
	}else if ($('#btbm_email').val() == '') {
		alert('이메일을 입력하세요.');
		$('#btbm_email').focus();
	}else if ($('#hp_1').val() == '') {
		alert('연락처를 입력하세요.');
		$('#hp_1').focus();
	}else if ($('#hp_2').val() == '') {
		alert('연락처를 입력하세요.');
		$('#hp_2').focus();
	}else if ($('#hp_3').val() == '') {
		alert('연락처를 입력하세요.');
		$('#hp_3').focus();
	}else if(idCheck($('#btbm_email').val()) == false){
		$('#btbm_email').focus();
	}else if($('#check_yn').val() != 'Y' || $('#check_id').val() != $('#btbs_nm').val()){
		alert("사이트명을  중복확인 하세요.");
	}else if(confirm("B2B사이트를 등록하시겠습니까?") == true){
		
		$.ajax({
			url : '/btb/btbAddProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
				btbs_nm        : $('#btbs_nm').val(),
				comp_nm	       : $('#comp_nm').val(),	  
				btbs_domain    : $('#btbs_domain').val(),
				zipNo          : $('#zipNo').val(),
				jibunAddr      : $('#jibunAddr').val(),
				addrDetail     : $('#addrDetail').val(),
				btbs_cur_templ : $('#btbs_cur_templ').val(),
				btbm_nm        : $('#btbm_nm').val(),
				btbm_rank      : $('#btbm_rank').val(),	    
				btbm_email 	   : $('#btbm_email').val(),	
				btbm_tel	   : btbm_tel,
				siNm	       : $('#siNm').val(),		
				sggNm	       : $('#sggNm').val(),
				emdNm	       : $('#emdNm').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("등록되었습니다.");
				window.location.replace("/btb/btbList");
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
Name   : 결과리턴                                                 
Desc   : 
Param  :            
********************************************************************/
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		$("#roadFullAddr").val(roadFullAddr);
		$("#roadAddrPart1").val(roadAddrPart1);
		$("#roadAddrPart2").val(roadAddrPart2);
		$("#addrDetail").val(addrDetail);
		$("#engAddr").val(engAddr);
		$("#jibunAddr").val(jibunAddr);
		$("#zipNo").val(zipNo);
		$("#admCd").val(admCd);
		$("#rnMgtSn").val(rnMgtSn);
		$("#bdMgtSn").val(bdMgtSn);
		$("#detBdNmList").val(detBdNmList);
		/** 2017년 2월 추가제공 **/
		$("#bdNm").val(bdNm);
		$("#bdKdcd").val(bdKdcd);
		$("#siNm").val(siNm);
		$("#sggNm").val(sggNm);
		$("#emdNm").val(emdNm);
		$("#liNm").val(liNm);
		$("#rn").val(rn);
		$("#udrtYn").val(udrtYn);
		$("#buldMnnm").val(buldMnnm);
		$("#buldSlno").val(buldSlno);
		$("#mtYn").val(mtYn);
		$("#lnbrMnnm").val(lnbrMnnm);
		$("#lnbrSlno").val(lnbrSlno);
		/** 2017년 3월 추가제공 **/
		$("#emdNo").val(emdNo);
		
}

/********************************************************************
Name   : checkBtbNm                                            
Desc   : 사이트명 중복확인
Param  :            
********************************************************************/
function checkBtbNm(){

	if($('#btbsNm').val()==''){
		alert('사이트명을 입력하세요.');
		$('#btbsNm').focus();
		return false;
	}

	$.ajax({
		url : '/btb/checkBtbNm',
		method : 'POST',
		dataType: 'JSON',
		data : {
			btbs_nm	       : $('#btbsNm').val()       
		},
		success : function(jsonData) {
			console.log("success");
			var useYn = jsonData.useYn;
			var checkYn = jsonData.checkYn;
			if(useYn == "Y"){
				$('#check_yn').val(checkYn);
				$('#check_id').val($('#btbsNm').val());
				$("#msg").html("'"+$('#btbsNm').val()+"'은 사용가능한 사이트명입니다. <br> 사용하시겠습니까?");
				$('#msg').css("color", "blue");
				$('#gubun').val("Y");
			}else{
				$('#msg').text("'"+$('#btbsNm').val()+"'은 이미 사용중입니다.");
				$('#msg').css("color", "red");
				$('#gubun').val("N");
			}
			
		},
		error : function(e) {
			console.error('ajax 에러: ' + e.status);
		}
	});
}

function onlyNumber(event){
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
		return;
	else
		return false;
}

function removeChar(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 ) 
		return;
	else
		event.target.value = event.target.value.replace(/[^0-9]/g, "");
}

function proc(event, val1, val2){
	var val1 =  $('#'+val1).val();
	if(val1.length >=4 && event.keycode !=8){
		$('#'+val2).focus();
	}
}

/********************************************************************
Name   : regExp_char                                            
Desc   : 특수문자 제외
Param  :            
********************************************************************/
function regExp_char(id){
	var val = $('#'+id).val();
    var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
    if(regExp.test(val)){
        var t = val.replace(regExp, "");
        $('#'+id).val(t);
    }
}
/********************************************************************
Name   : regExp_number                                            
Desc   : 숫자만 가능
Param  :            
********************************************************************/
function regExp_number(id){
	var val = $('#'+id).val();
	var regExp = /[^(0-9)]/gi;
    if(regExp.test(val)){
        var t = val.replace(regExp, "");
        $('#'+id).val(t);
    }
}

