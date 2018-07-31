/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){
	console.log("btbView");
//	document.domain = "test.com";
	/* 레이어 팝업 */
	$("#layerPopup").hide();
	$("#dim-layer").hide();
	$("#layerPopup1").hide();
	$("#dim-layer1").hide();
	$("#layerPopup2").hide();
	$("#dim-layer2").hide();

	$("#layerPopup").draggable();
	$("#layerPopup1").draggable();
	$("#layerPopup2").draggable();

	$("#btn_del").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
		return false;
	});
	$("#btn_stop").click(function() {
		$("#dim-layer1").show();
		$("#layerPopup1").show();
		return false;
	});
	$("#btn_check").click(function() {
		$("#dim-layer2").show();
		$("#layerPopup2").show();
		$('#btbsNm').val($('#btbs_nm').val());
		$("#msg").text("");
		if($('#btbsNm').val() != ""){
			checkBtbNm();
		}
	});
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
		$("#layerPopup2").hide();
		$("#dim-layer2").hide();
	});
	$("#close1").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
		$("#layerPopup2").hide();
		$("#dim-layer2").hide();
	});
	$("#close2").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
		$("#layerPopup1").hide();
		$("#dim-layer1").hide();
		$("#layerPopup2").hide();
		$("#dim-layer2").hide();
	});
	/* 레이어 팝업 */

	$("#goList").click(function() {
		$("#backForm").submit();
	});

	if($("#stat").val() == "01"){
		$("#btn_use").hide();
        $("#btn_stop").show();
        $("#btn_mod").show();
	}else if($("#stat").val() == "02"){
        $("#btn_stop").hide();
        $("#btn_mod").hide();
        $("#btn_use").show();
	}else if($('#stat').val() == '03'){
		$("#btn_mod").hide();
		$("#btn_stop").hide();
		$("#btn_use").hide();
		$("#btn_del").hide();
		$('#buttonDiv').find("button").prop("disabled", true);
		$('#tbody').find("input").prop("readonly", true);
		$('#tbody').find("button").prop("disabled", true);
		$('#hp_1').attr("disabled", true);
	}

	var val = $('#adm_hp1').val();
	$("select[name='hp_1']").val(val).attr("selected", "selected");

	var val2 = $('#btb_templ').val();
	$("select[name='btbs_cur_templ']").val(val2).attr("selected", "selected");


	$('#addrSearchBtn').click(function(){
		goPopup();
	});

	$('#btn_mod').click(function(){
		console.log("btn_mod");
		modBtb();
	});

	$('#goStop').click(function(){
		console.log("btn_stop");
		goStop();
	});

	$('#btn_use').click(function(){
		console.log("btn_use");
		goUse();
	});


	$('#delBtb').click(function(){
		console.log("btn_del");
		delBtb();
	});

	$('#checkBtbNm').click(function(){
		checkBtbNm();
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
				$("#dim-layer2").hide();
				$("#layerPopup2").hide();
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
Name   : modBtb
Desc   : 수정
Param  :
********************************************************************/
function modBtb(){

	var btbm_tel = $('#hp_1').val()+'-'+ $('#hp_2').val()+'-'+$('#hp_3').val();

	if ($('#btbs_nm').val() == '') {
		alert('사이트명을 입력하세요.');
		$('#btbs_nm').focus();
	}else if ($('#comp_nm').val() == '') {
		alert('회사명을 입력하세요.');
		$('#comp_nm').focus();
	}else if ($('#btbs_domain').val() == '') {
		alert('사이트URL을 입력하세요.');
		$('#btbs_domain').focus();
	}else if($('#jibunAddr').val()==''){
		alert('회사주소를 입력하세요.');
		$('#jibunAddr').focus();
	}else if($('#btbs_cur_templ').val()==''){
		alert('템플릿 정보를 입력하세요.');
		$('#btbs_cur_templ').focus();
	}else if($('#btbm_nm').val()==''){
		alert('담당자명을 입력하세요.');
		$('#btbm_nm').focus();
	}else if($('#btbm_rank').val()==''){
		alert('직급을 입력하세요.');
		$('#btbm_rank').focus();
	}else if($('#btbm_email').val()==''){
		alert('이메일을 입력하세요.');
		$('#btbm_email').focus();
	}else if($('#hp_2').val()==''){
		alert('연락처를 입력하세요.');
		$('#hp_2').focus();
	}else if($('#hp_3').val()==''){
		alert('연락처를 입력하세요.');
		$('#hp_3').focus();
	}else if(idCheck($('#btbm_email').val()) == false){
		$('#btbm_email').focus();
	}else if(($('#btbs_nm').val() !=$('#db_val').val() && $('#btbs_nm').val() != $('#check_id').val())){
		alert('사이트명을 중복확인 하세요.');
		$('#btbs_nm').focus();
	}else if(confirm("저장 하시겠습니까?") == true){

		$.ajax({
			url : '/btb/btbViewProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
				btbs_sq        : $('#btbs_sq').val(),
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
				alert("저장 되었습니다.");
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

/********************************************************************
Name   : goStop
Desc   : 운영중지하기
Param  :
********************************************************************/
function goStop(){

	if($('#stop_rsn').val()==''){
		alert('운영중지 사유를 입력하세요.');
		$('#stop_rsn').focus();
	}else if(confirm("B2B사이트 운영 중지하시겠습니까?") == true){

		$.ajax({
			url : '/btb/goStop',
			method : 'POST',
			dataType: 'JSON',
			data : {
			    btbs_sq        : $('#btbs_sq').val(),
			    btbs_comment   : $('#stop_rsn').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("운영중지 되었습니다.");
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
Name   : delBtb
Desc   : B2B 사이트 삭제
Param  :
********************************************************************/
function delBtb(){

	if($('#del_rsn').val()==''){
		alert('삭제 사유를 입력하세요.');
		$('#del_rsn').focus();
	}else if(confirm("삭제 하시겠습니까?") == true){

		$.ajax({
			url : '/btb/btbDelProc',
			method : 'POST',
			dataType: 'JSON',
			data : {
			    btbs_sq      : $('#btbs_sq').val(),
			 	btbs_comment : $('#del_rsn').val()
			},
			success : function(jsonData) {
				console.log("success");
				alert("삭제 되었습니다.");
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
Name   : goUse
Desc   : 정지해제
Param  :
********************************************************************/
function goUse(){

	if(confirm("운영중지를 해제하시겠습니까?") == true){

		$.ajax({
			url : '/btb/goUse',
			method : 'POST',
			dataType: 'JSON',
			data : {
			    btbs_sq        : $('#btbs_sq').val(),
			    btbs_comment   : ''
			},
			success : function(jsonData) {
				console.log("success");
				alert("운영중지 해제되었습니다.");
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
Desc   : 상세보기
Param  :
********************************************************************/
function goView(){
	$('#viewForm').attr('action', '/btb/btbView');
	$("#viewForm").submit();
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
