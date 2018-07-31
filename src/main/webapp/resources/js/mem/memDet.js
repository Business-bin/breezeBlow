/********************************************************************
Name   : 전역 환경 설정
Desc   :
Param  :
********************************************************************/
$(function() {
	if($("#s_adm_class").val() == 3 || $("#s_adm_class").val() == 4){
		$(".tabmenu li").eq(2).hide();
		$(".tabmenu li").eq(3).hide();
	}

	if($("#stat").val() == "01"){
		$("#drsn").hide();
	}

	/* 레이어 팝업 */
	$("#layerPopup").draggable();
	$("#layerPopup3").draggable();
	$("#layerPopup4").draggable();
	$("#layerPopup5").draggable();
	$("#del").click(function() {
		$("#dim-layer").show();
		$("#layerPopup").show();
	});
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup").hide();
	});
	$("#mod").click(function() {
		$("#dim-layer").show();
		$("#layerPopup3").show();
	});
	$("#close2").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup3").hide();
	});
	$("#close3").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup4").hide();
	});
	$("#close4").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup5").hide();
	});
	$("#close5").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup6").hide();
	});
	$("#close6").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup7").hide();
	});
	/* 레이어 팝업 */

	/* 회원 리스트로 이동 */
	$("#backMemList").click(function() {
		$("#backForm").submit();
	});

	if($("#stat").val() == "01"){ // 회원상태가 활동중일 경우
		/* 회원삭제 */
		$("#delMem").click(function() {
			if(validation2()){
				ajaxForm("deleteMemForm", "/mem/deleteMem","삭제되었습니다.");
				$("#dim-layer").hide();
				$("#layerPopup").hide();
			}
		});
	}else{
		$("#del").hide();
	}

	/* 회원정보 수정 */
	$("#modMem").click(function() {
		if(validation()){
			ajaxForm("updateMemForm", "/mem/updateMem","수정되었습니다.");
		}
	});

	/* 활동로그 페이지로 이동 */
	$("#logMem").click(function() {
		location.href = "/mem/memLog?memSq="+$("#memSq").val()+"&memEmail="+$("#memEmail").val();
	});

	/* 비밀번호변경 팝업 */
	$("#chg").click(function(){
		$("#dim-layer").show();
		$("#layerPopup3").show();
	});

	/* 주소검색 */
	$("#addrBtn").click(function(){
		var pop = window.open("/common/jusoPopup?gubun=1","pop1","width=570,height=420, scrollbars=yes, resizable=yes");
	});

	$("#addrBtn2").click(function(){
		var pop = window.open("/common/jusoPopup?gubun=2","pop2","width=570,height=420, scrollbars=yes, resizable=yes");
	});

	$("#addrBtn3").click(function(){
		var pop = window.open("/common/jusoPopup?gubun=3","pop3","width=570,height=420, scrollbars=yes, resizable=yes");
	});

	/* 비밀번호 변경 */
	$("#chgPwd").click(function(){
		if($("#pwd").val() == $("#pwd2").val()){
			if(checkPassword($("#pwd").val())){
				ajaxForm("updatePwdForm","/mem/updatePwd","변경되었습니다.");
				$("#dim-layer").hide();
				$("#layerPopup3").hide();
			}else{
				return false;
			}
		}else{
			alert("비밀번호가 일치하지 않습니다.");
		}
	});

	$("#pprtAli").change(function(){
		if($("#pprtAli").val() == ""){
			$("#aliText").prop("readonly","");
		}else{
			$("#aliText").prop("readonly","readonly");
		}
	});

	$("#pprtAli2").change(function(){
		if($("#pprtAli2").val() == ""){
			$("#aliText2").prop("readonly","");
		}else{
			$("#aliText2").prop("readonly","readonly");
		}
	});

	/* 탭이동 */
	$(".tabmenu>li").eq(0).addClass("active");

	/* 회원 보유 제품 그리드 */
	grid.fn_initLoadGrid();
	grid.fn_SearchGrid();
	/* 회원 보유 제품 그리드 */
});

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#memImg").attr("src", e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function ajaxForm(formId, url, msg){
	var form = $("#"+formId).serialize();
	$.ajax({
		type : "POST",
		url : url,
		data : form,
		success : function(result){
			alert(msg);
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}

/* 회원 보유 제품 그리드 */
var grid = {
	fn_initLoadGrid : function() {
		$("#prod_list").jqGrid('GridUnload');
		$("#prod_list").jqGrid({
					caption : "생산구매제품리스트",
					datatype : 'clientside',
					height : 182,
					width : 500,
					rowNum : 2,
					rownumbers : true,
					viewrecords : true,
					colNames : [ "번호", "사진", "제품명", "S/N", "등록일시", "수정/삭제"],
					colModel : [ {
						name : "PPRT_SQ", index : "PPRT_SQ", sortable : true, width : 0, align : 'center', hidden : true
					}, {
						name : "CP_IMG_NM", index : "CP_IMG_NM", sortable : true, width : 30, align : 'center'
					}, {
						name : "CP_NM", index : "CP_NM", sortable : true, width : 30, align : 'center'
					}, {
						name : "PPRT_MAC", index : "PPRT_MAC", sortable : false, width : 30, align : 'center'
					}, {
						name : "REG_DTTM", index : "REG_DTTM", sortable : false, width : 30, align : 'center'
					}, {
						name : "MND", index : "MND", sortable : false, width : 10, align : 'center'
					}],
					pager : "#pager",
					sortname : 'PPRT_SQ', // 초기화 될때 sort할 컬럼을 지정
					sortorder : 'asc', // 초기화정렬방법
					jsonReader : {
						repeatitems : false, id : "PPRT_SQ", root : "rows", page : "page", total : "total", records : "records"
					},
					gridComplete : function(data, status) {
					},
					recordpos : 'right',
					gridview : true,
					emptyrecords : " 등록된 데이터가 없습니다.",
					loadError : function(xhr, status, error) {
					},
					onSelectRow : function(rowid, status, e) {
					}
				}).trigger('reloadGrid');
	},
	fn_SearchGrid : function() {
		$("#prod_list").jqGrid('GridUnload');
		$("#prod_list").jqGrid({
					url : "/mem/getProdList",
					caption : "생산구매제품리스트",
					datatype : 'json',
					mtype : 'POST',
					postData : {
						memSq : $('#memSq').val(),
					},
					height : 182,
					width : 500,
					rowNum : 2,
					rownumbers : true,
					viewrecords : true,
					colNames : [ "번호", "사진", "제품명", "S/N", "등록일시", "수정/삭제"],
					colModel : [ {
						name : "PPRT_SQ", index : "PPRT_SQ", sortable : true, width : 0, align : 'center', hidden : true
					}, {
						name : "CP_IMG_NM", index : "CP_IMG_NM", sortable : true, width : 25, align : 'center', formatter : imageFormatter
					}, {
						name : "CP_NM", index : "CP_NM", sortable : true, width : 25, align : 'center'
					}, {
						name : "PPRT_MAC", index : "PPRT_MAC", sortable : false, width : 25, align : 'center'
					}, {
						name : "REG_DTTM", index : "REG_DTTM", sortable : false, width : 25, align : 'center'
					}, {
						name : "MND", index : "MND", sortable : false, width : 10, align : 'center', formatter : buttonFormatter
					}],
					pager : "#pager",
					sortname : 'PPRT_SQ', // 초기화 될때 sort할 컬럼을 지정
					sortorder : 'asc', // 초기화정렬방법
					jsonReader : {
						repeatitems : false, id : "PPRT_SQ", root : "rows", page : "page", total : "total", records : "records"
					},
					gridComplete : function(data, status) {
						var tcnt = jQuery("#prod_list").jqGrid('getGridParam','records');
						$("#tcnt").text("총 "+tcnt+"개");
					},
					recordpos : 'right',
					gridview : true,
					emptyrecords : " 등록된 데이터가 없습니다.",
					loadError : function(xhr, status, error) {
						alert(status + " : " + error);
						console.log(status);
						console.log(xhr);
						console.log(error);
					},
					ondblClickRow : function(rowid, status, e) {
						var ret = jQuery("#prod_list").jqGrid('getRowData', rowid);
						prodDet(ret.PPRT_MAC);
						$("#dim-layer").show();
						$("#layerPopup4").show();
					}
				}).trigger('reloadGrid');
		$("#prod_list").jqGrid("navGrid", "#pager", {
			search : false,
			edit : false,
			add : false,
			del : false
		}, {
			closeAfterEdit : false,
			reloadAfterSubmit : false
		}, {
			closeAfterAdd : false,
			reloadAfterSubmit : false
		}, {
			reloadAfterSubmit : false
		});
	}
}

var grid2 = {
		fn_initLoadGrid:function(){
			$("#mac_list").jqGrid('GridUnload');
			$("#mac_list").jqGrid({
				caption:"시리얼 번호 검색"
				,datatype : 'clientside'
				,height:155
				,width:350
				,rowNum:5
				,rownumbers : false
				,viewrecords: true
				,colNames:["번호","제품명","모델명","제품분류","S/N"]
				,colModel:[
				      {name:"MD_SQ", index:"MD_SQ" , sortable:false,	width:0, align:'center', hidden: true},
			          {name:"CP_NM", index:"CP_NM" , sortable:false,	width:20, align:'center'},
			          {name:"MD_NM", index:"MD_NM", sortable:false,	width:20, align:'center'},
			          {name:"MORM", index:"MORM", sortable:false, width:10, align:'center'},
			          {name:"PPRT_MAC", index:"PPRT_MAC", sortable:false, width:20, align:'center'}
			    ]
				,pager:"#pager2"
		        ,sortname     : 'PPRT_MAC'     //초기화 될때 sort할 컬럼을 지정
		        ,sortorder    : 'asc'       //초기화정렬방법
				,jsonReader:{ repeatitems:false
					, id:"ROWNUM"
					, root: "rows"
					, page : "page"
					, total : "total"
					,records : "records" }

				,gridComplete : function(data,status) {
				}
				,recordpos:'right'
				,gridview: true
				,emptyrecords :" 등록된 데이터가 없습니다."
		    	,loadError:function(xhr, status, error) {
		    	}
		    	,onSelectRow   : function(rowid, status, e) {
			     }

			}).trigger('reloadGrid');

		}
		,fn_SearchGrid:function(){
			$("#mac_list").jqGrid('GridUnload');
			$("#mac_list").jqGrid({
				url:"/product/macSearch2"
				,caption:"시리얼 번호 검색"
				,datatype 	   : 'json'
			    ,mtype         : 'POST'
		    	, postData	   : {
		        	pprtMac : $("#regMac").val()
		        }
				,height:155
				,width:250
				,rowNum:5
				,rownumbers : false
				,viewrecords: true
				,colNames:["번호","제품명","모델명","제품분류","S/N"]
				,colModel:[
			      {name:"MD_SQ", index:"MD_SQ" , sortable:false,	width:0, align:'center', hidden: true},
		          {name:"CP_NM", index:"CP_NM" , sortable:false,	width:20, align:'center'},
		          {name:"MD_NM", index:"MD_NM", sortable:false,	width:20, align:'center'},
		          {name:"MORM", index:"MORM", sortable:false, width:10, align:'center'},
		          {name:"PPRT_MAC", index:"PPRT_MAC", sortable:false, width:20, align:'center'}
			    ]
				,pager:"#pager"
		        ,sortname     : 'PPRT_MAC'     //초기화 될때 sort할 컬럼을 지정
		        ,sortorder    : 'asc'       //초기화정렬방법
				,jsonReader:{ repeatitems:false
					, id: "ROWNUM"
					, root: "rows"
					, page : "page"
					, total : "total"
					,records : "records" }

				,gridComplete : function(data,status) {
					/*var val = jQuery("#mac_list").jqGrid('getGridParam','records');
					$("#totalCnt").html(val);*/
				}
				,recordpos:'right'
				,gridview: true
				,emptyrecords :" 등록된 데이터가 없습니다."
		    	,loadError:function(xhr, status, error) {
		    	  alert(status + " : " +error);
		    	  console.log(status);
		    	  console.log(xhr);
		    	  console.log(error);
		    	}
		    	,ondblClickRow    : function(rowid, status, e) {
			    	var ret = jQuery("#mac_list").jqGrid('getRowData',rowid);
			    	$("#regMac2").val(ret.PPRT_MAC);
			     }
			}).trigger('reloadGrid');

			$("#mac_list").jqGrid(
				"navGrid",
				"#pager",
				{search:false, edit:false, add:false, del:false},
				{closeAfterEdit:false, reloadAfterSubmit:false},
				{closeAfterAdd:false, reloadAfterSubmit:false},
				{reloadAfterSubmit:false}
			);
		}
	}

/* 이미지출력 */
function imageFormatter(cellvalue, options, rowObject){
    return '<img src="' + rowObject.CP_IMG_NM + '" />';
}

function buttonFormatter(cellvalue, options, rowObject){
	return '<button class="button" type="button" onclick="mpop(\''+rowObject.PPRT_MAC.toString()+'\')">수정</button><br><br><button class="button" id="delProd" type="button" onclick="dpop('+rowObject.PPRT_SQ+')">삭제</button>';
}

function mpop(pprtMac){
	$("#dim-layer").show();
	$("#layerPopup5").show();
	$.ajax({
		type : "POST",
		url : "/product/productDet2",
		data : {
			pprtMac : pprtMac
		},
		success : function(result){
			var r = result.model.prodDet;
			$("#prodImg2").attr("src", r.CP_IMG_NM);
			$("#cpNm2").text(r.CP_NM);
			$("#mdNm2").text(r.MD_NM);
			$("#detStd2").text(r.DET_STD);
			$("#des2").text(r.DES);
			$("#usg2").text(r.USG);
			$("#pprtMac2").text(r.PPRT_MAC);
			$("#regDttm3").text(r.REG_DTTM);
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}

function modProd(){
	var pprtMac = $("#pprtMac2").text();
	$.ajax({
		type : "POST",
		url : "/mem/modProduct",
		data : {
			pprtMac : pprtMac,
			addr1 : $("#modAddr1").val(),
			addr2 : $("#modAddr2").val(),
			addr3 : $("#modAddr3").val(),
			addr4 : $("#modAddr4").val(),
			addr5 : $("#modAddr5").val(),
			pprtAli : $("#pprtAli").val(),
			aliText : $("#aliText").val()
		},
		success : function(result){
			alert("수정되었습니다.");
			location.reload();
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}

function dpop(pprtSq){
	$("#dim-layer").show();
	$("#layerPopup6").show();
	$("#delSq").val(pprtSq);
}

function delProd(){
	$.ajax({
		type : "POST",
		url : "/mem/delProduct",
		data : {
			pprtSq : $("#delSq").val(),
			initRsn : $("#initRsn").val()
		},
		success : function(result){
			alert("삭제되었습니다.");
			location.reload();
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}

function rpop(){
	$("#dim-layer").show();
	$("#layerPopup7").show();
	grid2.fn_initLoadGrid();
	grid2.fn_SearchGrid();
}

function searchProd(){
	grid2.fn_SearchGrid();
}

function regProd(){
	$.ajax({
		type : "POST",
		url : "/mem/regProduct",
		data : {
			pprtMac : $("#regMac2").val(),
			addr1 : $("#regAddr1").val(),
			addr2 : $("#regAddr2").val(),
			addr3 : $("#regAddr3").val(),
			addr4 : $("#regAddr4").val(),
			addr5 : $("#regAddr5").val(),
			pprtAli : $("#pprtAli2").val(),
			aliText : $("#aliText2").val(),
			memSq : $("#memSq2").val()
		},
		success : function(result){
			location.reload();
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}

/* 주소검색 콜백 데이터 */
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr,
		jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,
		emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
	$("#addr1").val(siNm);
	$("#addr2").val(sggNm);
	$("#addr3").val(emdNm);
	var detail="";
	if(lnbrMnnm != null && lnbrMnnm != ""){
		detail += lnbrMnnm;
	}
	if(lnbrSlno != null && lnbrSlno != ""){
		detail += "-"+lnbrSlno;
	}
	if(addrDetail != null && addrDetail != ""){
		detail += " "+addrDetail;
	}
	$("#addr4").val(detail);
	$("#addr").val(siNm+" "+sggNm+" "+emdNm+" "+detail);
}

function jusoCallBack2(siNm,sggNm, emdNm,lnbrMnnm,lnbrSlno,addrDetail){
	$("#modAddr1").val(siNm);
	$("#modAddr2").val(sggNm);
	$("#modAddr3").val(emdNm);
	var detail="";
	if(lnbrMnnm != null && lnbrMnnm != ""){
		detail += lnbrMnnm;
	}
	if(lnbrSlno != null && lnbrSlno != ""){
		detail += "-"+lnbrSlno;
	}
	$("#modAddr4").val(detail);
	if(addrDetail != null && addrDetail != ""){
		$("#modAddr5").val(addrDetail);
	}
}

function jusoCallBack3(siNm,sggNm, emdNm,lnbrMnnm,lnbrSlno,addrDetail){
	$("#regAddr1").val(siNm);
	$("#regAddr2").val(sggNm);
	$("#regAddr3").val(emdNm);
	var detail="";
	if(lnbrMnnm != null && lnbrMnnm != ""){
		detail += lnbrMnnm;
	}
	if(lnbrSlno != null && lnbrSlno != ""){
		detail += "-"+lnbrSlno;
	}
	$("#regAddr4").val(detail);
	if(addrDetail != null && addrDetail != ""){
		$("#regAddr5").val(addrDetail);
	}
}

/* 제품상세 */
function prodDet(pprtMac){
	$.ajax({
		type : "POST",
		url : "/product/productDet2",
		data : {
			pprtMac : pprtMac
		},
		success : function(result){
			var r = result.model.prodDet;
			$("#prodImg").attr("src", r.CP_IMG_NM);
			$("#cpNm").text(r.CP_NM);
			$("#mdNm").text(r.MD_NM);
			$("#detStd").text(r.DET_STD);
			$("#des").text(r.DES);
			$("#usg").text(r.USG);
			$("#pprtMac").text(r.PPRT_MAC);
			$("#regDttm2").text(r.REG_DTTM);
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}

/* 유효성검사 */
function validation(){
	if(isEmpty($("#memEmail").val())){
		alert("회원ID를 입력해주세요.");
		return false;
	}
	if(!idCheck($("#memEmail").val())){return false;}
	if(trim($("#memEmail").val()).length != $("#memEmail").val().length){
		alert("아이디에는 공백이 들어갈 수 없습니다.");
		return false;
	}
	if(isEmpty($("#memNm").val())){
		alert("이름을 입력해주세요.");
		return false;
	}
	if(trim($("#memNm").val()).length != $("#memNm").val().length){
		alert("이름에는 공백이 들어갈 수 없습니다.");
		return false;
	}
	if(isEmpty($("#memBir").val())){
		alert("생년월일을 입력해주세요.");
		return false;
	}
	if($("#memBir").val().indexOf('.') != 4 || $("#memBir").val().lastIndexOf('.') != 7 || $("#memBir").val().length != 10){
		alert("생년월일 형식이 맞지 않습니다.(ex:0000.00.00)");
		return false;
	}
	if(trim($("#memBir").val()).length != $("#memBir").val().length){
		alert("생년월일에는 공백이 들어갈 수 없습니다.");
		return false;
	}
	if(isEmpty($("#hp2").val())){
		alert("핸드폰번호 앞자리를 입력해주세요.");
		return false;
	}
	if(trim($("#hp2").val()).length != $("#hp2").val().length){
		alert("핸드폰번호 앞자리에는 공백이 들어갈 수 없습니다.");
		return false;
	}
	if($("#hp2").val().length < 3){
		alert("핸드폰번호 앞자리는 3자리 이상이어야 합니다.");
		return false;
	}
	if(isEmpty($("#hp3").val())){
		alert("핸드폰번호 앞자리를 입력해주세요.");
		return false;
	}
	if(trim($("#hp3").val()).length != $("#hp3").val().length){
		alert("핸드폰번호 뒷자리에는 공백이 들어갈 수 없습니다.");
		return false;
	}
	if($("#hp3").val().length < 4){
		alert("핸드폰번호 뒷자리는 4자리 이상이어야 합니다.");
		return false;
	}
	return true;
}

/* 유효성검사2 */
function validation2(){
	if(isEmpty($("#delRsn2").val())){
		alert("삭제사유를 입력해주세요.");
		return false;
	}
	if(calculate_byte($("#delRsn2").val()) > 200){
		alert("삭제사유는 한글 100자, 영문 200자까지 입력 가능합니다.");
		return false;
	}
	return true;
}