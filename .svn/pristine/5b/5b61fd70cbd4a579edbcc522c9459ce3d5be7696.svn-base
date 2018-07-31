/* 메뉴 드롭다운 체크 S */
$(document).ready(function(){
	// gnb에 마우스오버하면 서브메뉴 나타나기
	$('.gnb').hover(function(){
		$('.bg_sub').stop().animate({height:'180px'},280);
	},function(){
		$('.bg_sub').hover(function(){
			$('.bg_sub').stop().animate({height:'180px'},280);
		},function(){
			$('.bg_sub').stop().animate({height:'0px'},280);
		});
	});

	// 서브메뉴에 마우스오버하면 해당 메인메뉴 글씨색 변하기
	$('.bg_sub li').hover(function(){
		$(this).prev().addClass('active');
	},function(){
		$(this).prev().removeClass('active');
	});
})
/* 메뉴 드롭다운 체크 E */

/* 다음API 주소 체크 S */
function Postcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var fullAddr = data.address; // 최종 주소 변수
			var extraAddr = ''; // 조합형 주소 변수

			// 기본 주소가 도로명 타입일때 조합한다.
			if(data.addressType === 'R'){
				//법정동명이 있을 경우 추가한다.
				if(data.bname !== ''){
					extraAddr += data.bname;
				}
				// 건물명이 있을 경우 추가한다.
				if(data.buildingName !== ''){
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
			}

			// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
			if(data.userSelectedType === 'R'){
				//법정동명이 있을 경우 추가한다.
				if(data.bname !== ''){
					extraAddr += data.bname;
				}
				// 건물명이 있을 경우 추가한다.
				if(data.buildingName !== ''){
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('address1').value = fullAddr;
            document.getElementById('addressenglish').value = data.addressEnglish;

			// 커서를 상세주소 필드로 이동한다.
			document.getElementById('address2').focus();
		}
	}).open();
}
/* 다음API 주소 체크 E */


$(function(){
	// 초기화
	$("#tableReset").on("click",function(){
		$("#OrderInfo").each(function(){ this.reset(); });
		$("input[name='btnRemove']").each(function () {
			$(this).trigger('click');
		});
	});
});

var datepickeroption = {
		inline: true,
		dateFormat: "yy-mm-dd",		//날짜형식
		prevText: 'prev',			//이전달
		nextText: 'next',			//다음달
		showButtonPanel: true,		//버튼 패널 사용
		changeMonth: true,			//월 선택박스 사용
		changeYear: true,			//년 선택박스 사용
		yearRange: 'c-100:c+0',		//해당년도로부터
		showOtherMonths: true,		//이전/다음 달 일수 보이기
		selectOtherMonths: true,	//이전/다음 달 일 선택하기
		showOn: "button",
		buttonImage: "/images/icon/icon_calendar.png",
		buttonImageOnly: true,
		closeText: '닫기',
		currentText: '오늘',
		showMonthAfterYear: true,        /* 년과 달의 위치 바꾸기 */
		/* 한글화 */
		monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
		showAnim: 'slideDown',
		/* 날짜 유효성 체크 */
		onClose: function( selectedDate ) {
			$('#fromDate').datepicker("option","minDate", selectedDate);
		}
	};
/* 달력 체크 S */
$(function() {
	$(".dates").datepicker({
		inline: true,
		dateFormat: "yy-mm-dd",		//날짜형식
		prevText: 'prev',			//이전달
		nextText: 'next',			//다음달
		showButtonPanel: true,		//버튼 패널 사용
		changeMonth: true,			//월 선택박스 사용
		changeYear: true,			//년 선택박스 사용
		yearRange: 'c-100:c+0',		//해당년도로부터
		showOtherMonths: true,		//이전/다음 달 일수 보이기
		selectOtherMonths: true,	//이전/다음 달 일 선택하기
		showOn: "button",
		buttonImage: "/images/icon/icon_calendar.png",
		buttonImageOnly: true,
		closeText: '닫기',
		currentText: '오늘',
		showMonthAfterYear: true,        /* 년과 달의 위치 바꾸기 */
		/* 한글화 */
		monthNames : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dayNames : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort : ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
		showAnim: 'slideDown',
		/* 날짜 유효성 체크 */
		onClose: function( selectedDate ) {
			$('#fromDate').datepicker("option","minDate", selectedDate);
		}
	});
});
/* 달력 체크 E */

/* 숫자값 체크 S */
$(function ($) {
    // 숫자 제외하고 모든 문자 삭제.
    $.fn.removeText = function(_v){
        //console.log("removeText: 숫자 제거 합니다.");
        if (typeof(_v)==="undefined"){
            $(this).each(function(){
                this.value = this.value.replace(/[^0-9]/g,'');
            });
        } else {
            return _v.replace(/[^0-9]/g,'');
        }
    };

    // php의 number_format과 같은 효과.
    $.fn.numberFormat = function(_v){
        this.proc = function(_v){
            var tmp = '',
                number = '',
                cutlen = 3,
                comma = ','
                i = 0,
                len = _v.length,
                mod = (len % cutlen),
                k = cutlen - mod;

            for (i; i < len; i++)
            {
                number = number + _v.charAt(i);
                if (i < len - 1)
                {
                    k++;
                    if ((k % cutlen) == 0)
                    {
                        number = number + comma;
                        k = 0;
                    }
                }
            }
            return number;
        };

        var proc = this.proc;
        if (typeof(_v)==="undefined"){
            $(this).each(function(){
                this.value = proc($(this).removeText(this.value));
            });
        } else {
            return proc(_v);
        }
    };

    // 위 두개의 합성.
    // 콤마 불필요시 numberFormat 부분을 주석처리.
    $.fn.onlyNumber = function (p) {
        $(this).each(function(i) {
            $(this).attr({'style':'text-align:right'});

            this.value = $(this).removeText(this.value);
            this.value = $(this).numberFormat(this.value);

            $(this).bind('change',function(e){
                this.value = $(this).removeText(this.value);
                this.value = $(this).numberFormat(this.value);
            });
            $(this).bind('click',function(){$(this).select();});
        });
    };
});
/* 숫자값 체크 E */

/* 필수입력값 체크 S */
function check_onoff(obj){
	if($("#"+obj.id).is(":checked")){
		$(".dp_onoff_"+obj.id).show();
		$(".dp_onoff_"+obj.id).children("input").prop("disabled",false);
		$(".dp_onoff_"+obj.id).children("input").prop("required",true);
	} else {
		$(".dp_onoff_"+obj.id).hide();
		$(".dp_onoff_"+obj.id).children("input").prop("disabled",true);
		$(".dp_onoff_"+obj.id).children("input").prop("required",false);
	}
}

function radio_onoff(obj){
	$(".dp_onoff_group").hide();
	$(".dp_onoff_group").children("input").prop("required",false);
	$(".dp_onoff_group").children("select").prop("required",false);
	if($("#"+obj.id).is(":checked")){
		$(".dp_onoff_"+obj.id).show();
		$(".dp_onoff_"+obj.id).children("input").prop("disabled",false);
		$(".dp_onoff_"+obj.id).children("input").prop("required",true);
		$(".dp_onoff_"+obj.id).children("select").prop("required",true);
	} else {
		$(".dp_onoff_"+obj.id).hide();
		$(".dp_onoff_"+obj.id).children("input").prop("disabled",true);
		$(".dp_onoff_"+obj.id).children("input").prop("required",false);
		$(".dp_onoff_"+obj.id).children("select").prop("required",false);
	}
}

function dovalidate(formId){
	var res = true;
	$("#"+formId+" input, #"+formId+" select, #"+formId+" [type='checkbox'], #"+formId+" [type='radio']").each(function(){
		if($(this).prop("type") == 'text' || $(this).prop("type") == 'password' || $(this).prop("tagName") == 'SELECT'){

			if($(this).prop("required") == true && $(this).val() == ""){
				var txt = $(this).parents("tr").children("th").text();
				alert(txt + " 은(는) 필수입력 항목입니다.");
				$(this).focus();
				res = false;
				return false;
			}
			if($(this).prop("type") == 'password' && $(this).prop("disabled") == false){
				res = !passchk()
			}
		} else if($(this).prop("type") == 'checkbox'){
			if($(this).prop("required") == true && !$(this).is(":checked")){
				var txt = $(this).parents("tr").children("th").text();
				alert(txt + " 은(는) 필수입력 항목입니다.");
				$(this).focus();
				res = false;
				return false;
			}
		} else if($(this).prop("type") == 'radio'){
			var nm = $(this).prop("name");
			var chk = false;
			$("input[name='"+nm+"']").each(function(){
				if($(this).is(":checked")){
					chk = true;
				}
			});
			if(!chk){
				var txt = $(this).parents("tr").children("th").text();
				alert(txt + " 은(는) 필수입력 항목입니다.");
				//$(this).focus();
				res = false;
				return false;
			}
		}
	});

	if(!res){
		return false;
	} else{
		$("#"+formId).prop("action","/inc/datainfo.php");
		$("#"+formId).submit();
	}
}
/* 필수입력값 체크 E */

/* 비밀번호 체크 S */
function passchk(){
	$(".error").hide();

	var hasError = false;
	var passwordVal = $("#password").val();
	var checkVal = $("#repassword").val();
	//var num = passwordVal.search(/[0-9]/g);
	//var eng = passwordVal.search(/[a-z]/ig);
	//var spe = passwordVal.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	if (passwordVal == '') {
		$("#password").after(' <span class="fred error">비밀번호를 입력하세요.</span>');
		hasError = true;
	} else if (checkVal == '') {
		$("#repassword").after(' <span class="fred error">비밀번호를 다시 한번 입력하세요.</span>');
		hasError = true;
	} else if (passwordVal != checkVal ) {
		$("#repassword").after(' <span class="fred error">비밀번호가 일치하지 않습니다.</span>');
		hasError = true;
	} else if(passwordVal.length < 4 || passwordVal.length > 32){
		$("#password").after(' <span class="fred error">비밀번호는 4자리 이상 32자리 이하로 입력하세요.</span>');
		hasError = true;
	} else if(passwordVal.search(/₩s/) != -1){
		$("#password").after(' <span class="fred error">비밀번호는 공백없이 입력해주세요.</span>');
		hasError = true;
	}/* else if(num < 0 || eng < 0 || spe < 0 ){
		$("#password").after(' <span class="fred error">영문,숫자, 특수문자를 혼합하여 입력해주세요.</span>');
		hasError = true;
	}*/
	return hasError;
}
/* 비밀번호 체크 E */

/* 체크삭제 부분 S */
function chkall(chkname, obj){
	var chk = $(obj).is(":checked");
	$("input[name^='"+chkname+"']").each(function(){
		$(this).prop("checked",chk);
	});
}

function chkval(chkname, mode, url, returl, msgval){
	var chk = "";
	$("input[name^='"+chkname+"']").each(function(idx){
		if($(this).is(":checked")){
			//arr.push($(this).val());
			chk = chk + $(this).val() + ",";
		}
	});

	if(chk.length == 0){
		alert("데이터를 선택해주세요");
		return false;
	}

	if(msgval){
		if(confirm(msgval)){
			location.href = url + "?chkval=" + chk + "&mode=" + mode + "&return_url=" + returl;
			//console.log(url + "?chkval=" + chk + "&amp;mode=" + mode);
		}
	} else {
		if(confirm("선택항목을 삭제 하시겠습니까?")){
			location.href = url + "?chkval=" + chk + "&mode=" + mode + "&return_url=" + returl;
			//console.log(url + "?chkval=" + chk + "&amp;mode=" + mode);
		}
	}
}
/* 체크삭제 부분 E */

/* 자동검색기능 부분 S */
function chkval2(chkname, mode, url, returl){
	var chk = "";
	var payment = "";
	var addyear = $('#addyear').val();
	$("input[name^='"+chkname+"']").each(function(idx){
		if($(this).is(":checked")){
			//arr.push($(this).val());
			chk = chk + $(this).val() + ",";
		}
	});

	$("input[name^='payment']").each(function(idx){
		if($(this).val().length == 0){
			//arr.push($(this).val());
			//payment = payment + "" + ",";
		} else {
			payment = payment + $(this).val() + ",";
		}
	});

	if(addyear.length == 0){
		alert("년도를 선택해주세요");
		return false;
	}

	if(chk.length == 0){
		alert("데이터를 선택해주세요");
		return false;
	}

	if(confirm("데이터를 추가 하시겠습니까?")){
		location.href = url + "?addyear=" + addyear + "&chkval=" + chk + "&paymentval=" + payment + "&mode=" + mode + "&return_url=" + returl;
		//console.log(url + "?chkval=" + chk + "&amp;mode=" + mode);
	}
}

function chkval3(chkname, mode, url, returl, msgval){
	var chk = "";
	var ctType = "";
	var caic = "";
	var assignno = "";
	$("input[name^='" + chkname + "']").each(function(idx){
		if($(this).is(":checked")){

			chk = chk + $(this).val() + "|";

			$(this).parents("tr").next("tr").children().find("[name^='caic']").each(function(){
				caic = caic + $(this).val() + "|";
			});
			caic = caic + "@";

			$(this).parents("tr").next("tr").children().find("[name^='assignno']").each(function(){
				assignno = assignno + $(this).val() + "|";
			});
			assignno = assignno + "@";
		}
	});

	for (var i=0; i < contractType.length; i++) {
		if (contractType.options[i].selected) {
			ctType = contractType.options[i].value;
		}
	}

	if(chk.length == 0){
		alert("데이터를 선택해주세요");
		return false;
	}

	if(ctType.length == 0){
		alert("계약방법을 선택해주세요");
		return false;
	}

	if(confirm(msgval)){
		location.href = url + "?chkval=" + chk + "&mode=" + mode + "&return_url=" + returl + "&contractType=" + ctType + "&caic=" + caic + "&assignno=" + assignno;
		//console.log(url + "?chkval=" + chk + "&amp;mode=" + mode);
	}

}

function show_String(strValue){
	if (strValue.replace(/\s/g, '').length > 0){
		var bchk = false;
		var strText = '';
		var nLen = strValue.length;
		for (var i = 0; i < arrStr.length; i++){
			if(arrStr[i].indexOf(strValue)!=-1){
				strText += '<div class="keyvalue" onclick="javascript:fncChoice(this);" link0="'+arrStr[i].split('|')[0]+'" link1="'+arrStr[i].split('|')[1]+'" link2="'+arrStr[i].split('|')[2]+'" link3="'+arrStr[i].split('|')[3]+'">'+arrStr[i].split('|')[1]+' ('+arrStr[i].split('|')[2]+')</div>';
				bchk = true;
			}
		}

		var objidiv = document.getElementById('kv_01');
		if (bchk){
			objidiv.style.display = 'block';
			objidiv.innerHTML = strText;
		}
		else
		objidiv.style.display = 'none';
	}
	else
	document.getElementById('kv_01').innerHTML = '';
}

function fncChoice(obj){
	document.getElementById('keyword0').value = $(obj).attr('link0');
	document.getElementById('keyword1').value = $(obj).attr('link1');
	document.getElementById('keyword2').value = $(obj).attr('link2');
	document.getElementById('keyword3').value = $(obj).attr('link3');
	obj.parentNode.style.display = 'none';
}
/* 자동검색기능 부분 E */

/* 숫자만 입력 S */
$(document).ready(function(){
	$("[name^=quantity]").onlyNumber();
	$(".onlynumber").onlyNumber();
});
/* 숫자만 입력 E */

/* 테이블 행 추가 S */
$(document).ready(function(){
	// 학력사항
	$("#OrderItemAdd").click(function(){
		// item 의 최대번호 구하기
		var lastItemNo = $("#OrderItemList tbody  tr:last").attr("class").replace("OrderItem", "");
		var newitem = $("#OrderItemList tr:eq(1)").clone();
		newitem.find('.dates').each(function() {
			$(this).removeAttr('id').removeClass(); // added the removeClass part.
			$(this).next().remove();
            $(this).datepicker(datepickeroption);
		});
		newitem.removeClass();
		newitem.addClass("OrderItem"+(parseInt(lastItemNo)+1));
		$(newitem).find("input[type='text'], input[type='hidden'], select").each(function(){
			$(this).val("");
		});
		$("#OrderItemList").append(newitem);
		$(newitem).find("input[type='button']").removeAttr('onclick');
		$(".delBtn").click(function(){
			$(this).parents("tr").remove();
		});
	});
});
/* 테이블 행 추가 E */

/* Onchange S */
function InfoSelect(obj, nameArr) {
	var nmArr = nameArr.split('|');
	var opt = $(obj).find("option:selected");
	var tr = $(obj).parents("tr");
	var attr = "";
	$(nmArr).each(function(){
		 attr = $(opt).attr(this);
		 $(tr).find("[name^='"+this+"']").val(attr);
	});
}
/* Onchange E */

/* 합계 구하기 S */
function sumrow(rownm, targetnm, obj, sumid){
	var rowsum = 0;
	var tsum = 0;
	var pr = parseInt($(obj).val());
	var qy = parseInt($(obj).parents("tr").find("[name^='"+rownm+"']").val());
	rowsum = pr * qy;
	$(obj).parents("tr").find("[name^='"+targetnm+"']").val(rowsum).onlyNumber();
	$(obj).parents("tbody").find("[name^='"+targetnm+"']").each(function(){
		var nsum = $(this).val().replace(/,/gi,'');
		tsum = parseInt(tsum) + parseInt(nsum);
	});
	$("#"+sumid).prev().val(tsum).onlyNumber();
	$("#"+sumid).html($("#"+sumid).prev().val());
}

function totalSum(targetnm,sumid){
	var total = 0;
	$("input[name^='"+targetnm+"']").each(function(){
		total = parseInt(total) + parseInt($(this).val().replace(/,/gi,''));
	});
	$("#"+sumid).prev().val(total).onlyNumber();
	$("#"+sumid).html($("#"+sumid).prev().val());

}
/* 합계 구하기 E */


function FNC_productNameChn(obj) {
	//alert($(obj).val());
	$(obj).parents("td").next().children("input").val($(obj).val());

	//select box 갯수 가져 오기
	var selcnt = $("select[name^='productName']").length;;
	//alert(selcnt);

	//선택한 selebox index 가져 오져오기
	//console.log($(obj).parents("tr").index()-1);
	var selindex = $(obj).parents("tr").index()-1;
	//alert(selindex);
}

/* Windows Popup*/
function winPopUPCenter(url, winName, pwidth, pheight, scrollYN, resizeYN) {
	var win = null;
	var winL = (screen.width-pwidth)/2;
	var winT = (screen.height-pheight)/2;
	var spec = 'toolbar=no,'; // 도구메뉴
	spec += 'status=no,'; // 상태바
	spec += 'location=yes,'; // 주소관련메뉴
	spec += 'height='+pheight+','; // 높이
	spec += 'width='+pwidth+','; // 너비
	spec += 'top='+winT+','; // 세로위치
	spec += 'left='+winL+','; // 가로위치
	spec += 'scrollbars='+(scrollYN == undefined ? "no" : scrollYN)+','; // 스크롤바 여부(기본)
	spec += 'resizable='+(resizeYN == undefined ? "no" : resizeYN); // 창크기조정 여부
	win = window.open(url, winName, spec);
	if(parseInt(navigator.appVersion) >= 4) {
		win.window.focus();
	}
}

/* 테이블속 같은 row 값 합치기 */
function rowSpan(tableId, columnNo){
	   var temp = document.getElementById(tableId);
	   var rows = temp.getElementsByTagName("TR");
	   var previous = -1;
	   for (var i = 1; i < rows.length; i++) {
	   	   if (i > 1){
	   	   	   var compare = (previous < 0) ? (i - 1) : previous ;
	   	   	   var preCol = rows[ compare ].getElementsByTagName("TD")[columnNo];
	   	   	   var curCol = rows[i].getElementsByTagName("TD")[columnNo];
	   	   	   if (preCol.innerHTML == curCol.innerHTML){
	   	   	   	   preCol.rowSpan = preCol.rowSpan + 1;
	   	   	   	   curCol.style.display = 'none';
	   	   	   	   previous = compare;
	   	   	   }else{
	   	   	   	   previous = -1;
	   	   	   }
	   	   }
	   }
}
