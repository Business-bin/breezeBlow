
/********************************************************************
Name   : ready                                                 
Desc   : 
Param  :            
********************************************************************/
$(document).ready(function(){

	
	$('#addrSearchBtn').click(function(){
		goPopup();
	});
	
	  //저장버튼 클릭시 form 전송
    $("#save").click(function(){
        //oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
        //$("#frm").submit();
    	save();
    	
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
