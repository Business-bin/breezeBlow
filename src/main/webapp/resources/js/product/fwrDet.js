/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(function() {
	if($("#s_adm_class").val() == 3 || $("#s_adm_class").val() == 4){
		$(".tabmenu li").eq(0).hide();
		$(".tabmenu li").eq(3).hide();
	}
	$("#layerPopup5").draggable();
	$("#layerPopup6").draggable();

	if($("#gubun").val() != "det"){
		$("#det").hide();
	}

	/* 검색 시작일 */
	$("#startDate").datepicker({
		changeMonth : true,
		onSelect : function(dateText, inst) {
			var dateArr = dateText.split("/");
			year = dateArr[2];
			month = dateArr[0];
			day = dateArr[1];
		}
	});

	/* 당월 1일부터 당일 계산 */
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	if (month < 10) {
		month = "0" + month.toString();
	}
	if (day < 10) {
		day = "0" + day.toString();
	}
	$("#startDate").val(year + "-" + month + "-" + day);

	/* 레이어팝업(모델검색) */
	$("#modSearch").click(function(){
		grid.fn_initLoadGrid();
		grid.fn_SearchGrid();
		$("#dim-layer").show();
		$("#layerPopup5").show();
	});

	/* 펌웨어삭제 */
	$("#delFwr").click(function() {
		$("#dim-layer").show();
		$("#layerPopup6").show();
	});

	/* 레이어팝업 닫기 */
	$("#close").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup5").hide();
	});

	/* 레이어팝업 닫기2 */
	$("#close2").click(function() {
		$("#dim-layer").hide();
		$("#layerPopup6").hide();
	});

	/* 펌웨어 리스트로 이동 */
	$("#backFwrList").click(function() {
		$("#backForm").submit();
	});

	/* 모델검색 */
	$("#modelSearch").click(function(){
		grid.fn_SearchGrid();
	});

	/* 펌웨어 파일 업로드 */
	$("#fwrFile").change(function() {
		if (this.files && this.files[0]) {
			$('#fwrFileNm').val(this.files[0].name);
		}
	});

	/* 펌웨어 삭제 */
	$("#del").click(function(){
		$.ajax({
			type : "post",
			url : "/product/deleteFwr",
			data : {
				delRsn : $("#delRsn").val(),
				fwrSq : $("#fwrSq").val()
			}, success : function(result){
				alert("삭제되었습니다.");
				$("#dim-layer").hide();
				$("#layerPopup6").hide();
			}, error : function(result){

			}
		})
	});

	/* 펌웨어 등록 */
	$("#newFwr").click(function(){
		if(isEmpty($("#fwrVrsNm").val())){
			alert("버전명을 입력해주세요.");
			return false;
		}
		if(trim($("#fwrVrsNm").val()).length != $("#fwrVrsNm").val().length){
			alert("버전명에는 공백이 들어갈 수 없습니다.");
			return false;
		}
		if(isEmpty($("#fwrMd").val())){
			alert("적용모델을 선택해 주세요.");
			return false;
		}
		if(isEmpty($("#fwrVrsNm1").val())){
			alert("버전제목을 입력해주세요.");
			return false;
		}
		if(isEmpty($("#fwrUptDes").val())){
			alert("펌웨어설명을 입력해주세요.");
			return false;
		}
		if(isEmpty($("#fwrFile").val())){
			alert("펌웨어 파일을 업로드해주세요.");
			return false;
		}
		if(isEmpty($("#stat").val())){
			alert("사용여부를 선택해주세요.");
			return false;
		}

		fileupload('insertFwrForm','/product/insertFwr','등록되었습니다.')
	});

	/* 펌웨어 수정 */
	$("#modFwr").click(function(){
		if(isEmpty($("#fwrVrsNm").val())){
			alert("버전명을 입력해주세요.");
			return false;
		}
		if(trim($("#fwrVrsNm").val()).length != $("#fwrVrsNm").val().length){
			alert("버전명에는 공백이 들어갈 수 없습니다.");
			return false;
		}
		if(isEmpty($("#fwrVrsNm1").val())){
			alert("버전제목을 입력해주세요.");
			return false;
		}
		if(isEmpty($("#fwrUptDes").val())){
			alert("펌웨어설명을 입력해주세요.");
			return false;
		}

		fileupload('insertFwrForm','/product/updateFwr','수정되었습니다.')
	});

	/* 탭이동 */
	$(".tabmenu>li").eq(3).addClass("active");
});

function fileupload(formId, url, msg) {
	var form = $('#'+formId)[0];
    var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: url,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	alert(msg);
        },
        error: function (e) {
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
        }
    });
}

function ajaxForm(formId, url, msg){
	var form = $("#"+formId).serialize();
	$.ajax({
		type : "POST",
		url : url,
		data : form,
		success : function(result){
			alert(msg);
			location.href="/product/fwrList";
		},
		error : function(e){
			alert("error : "+e);
		}
	});
}

/* 모델 리스트 그리드 */
var grid = {
		fn_initLoadGrid:function(){
			$("#model_list").jqGrid('GridUnload');
			$("#model_list").jqGrid({
				caption:"제품 모델 검색"
				,datatype : 'clientside'
				,height:300
				,width:350
				,rowNum:8
				,rownumbers : false
				,viewrecords: true
				,colNames:["번호","제품명","모델명","제품분류"]
				,colModel:[
				      {name:"MD_SQ", index:"MD_SQ" , sortable:false,	width:0, align:'center', hidden: true},
			          {name:"CP_NM", index:"CP_NM" , sortable:false,	width:20, align:'center'},
			          {name:"MD_NM", index:"MD_NM", sortable:false,	width:20, align:'center'},
			          {name:"MORM", index:"MORM", sortable:false, width:10, align:'center'},
			    ]
				,pager:"#pager"
		        ,sortname     : 'MD_SQ'     //초기화 될때 sort할 컬럼을 지정
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
			$("#model_list").jqGrid( 'GridUnload' );
			$("#model_list").jqGrid({
				url:"/product/modelSearch2"
				,caption:"제품 모델 검색"
				,datatype : 'json'
			    ,mtype : 'POST'
		    	, postData : {
		          cpNm : $("#prodNm").val(),
		          mdNm : $("#prodNm").val()
		        }
				,height:300
				,width:250
				,rowNum:8
				,rownumbers : false
				,viewrecords: true
				,colNames:["번호","제품명","모델명","제품분류"]
				,colModel:[
				      {name:"MD_SQ", index:"MD_SQ" , sortable:false,	width:0, align:'center', hidden: true},
			          {name:"CP_NM", index:"CP_NM" , sortable:false,	width:20, align:'center'},
			          {name:"MD_NM", index:"MD_NM", sortable:false,	width:20, align:'center'},
			          {name:"MORM", index:"MORM", sortable:false, width:10, align:'center'},
			    ]
				,pager:"#pager"
		        ,sortname     : 'MD_SQ'     //초기화 될때 sort할 컬럼을 지정
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
			    	var ret = jQuery("#model_list").jqGrid('getRowData',rowid);
			    	$("#fwrMd").val(ret.MD_NM);
			    	$("#fwrMdSq").val(ret.MD_SQ);
			    	$("#dim-layer").hide();
					$("#layerPopup5").hide();
			     }
			}).trigger('reloadGrid');

			$("#model_list").jqGrid(
					"navGrid",
					"#pager",
					{search:false, edit:false, add:false, del:false},
					{closeAfterEdit:false, reloadAfterSubmit:false},
					{closeAfterAdd:false, reloadAfterSubmit:false},
					{reloadAfterSubmit:false}
			);
		}
	}