

/********************************************************************
Name   : ready
Desc   :
Param  :
********************************************************************/
$(document).ready(function(){
    $(".tabmenu>li").eq(4).addClass("active")

    //목록으로
    $("#smsListBtn").click(function(){
    	$("#backForm").submit();
    });

});


