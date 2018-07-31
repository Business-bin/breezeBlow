<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/sample/sample2.css">	
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>

<script type="text/javascript">
/* var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
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
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm").submit();
      });    
});
  */
 
 
</script>
<!-- 소스 작성 start-->
<section>
<div class="container">
<div class="container_body">
				<ul class="tabmenu web">
					<li><a href="#"><i class="axi axi-menu"></i> Site Info</a></li>
					<li><a href="#"><i class="axi axi-info"></i> Infomation</a></li>
					<li><a href="#"><i class="axi axi-sitemap"></i> Menu</a></li>
					<li><a href="#"><i class="axi axi-assignment-ind"></i> Manager</a></li>
					<li><a href="#"><i class="axi axi-history2"></i> Time Table</a></li>
					<li><a href="#"><i class="axi axi-ion-android-note"></i> Meeting Note</a></li>
					<li><a href="#"><i class="axi axi-code"></i> Common Code</a></li>
				</ul>
				<div class="pagetitle"><i class="axi axi-sitemap"></i> Menu</div>
				<div class="navi">
					<ul>
						<li>Home</li>
						<li>Setting</li>
						<li>Menu</li>
					</ul>
				</div>
				<form action="" id="frm" method="post" enctype="multipart/form-data">						
				<table width="100%">
        			<tr>
            			<td>제목</td>
            			<td><input type="text" id="title" name="title" style="width:650px"/></td>
        			</tr>
        			<tr>
            			<td>내용</td>
            		<td>
                		<textarea rows="10" cols="30" id="qna" name="content" style="width:650px; height:350px; "></textarea>
            		</td>
        			</tr>
        			<tr>
	            		<td colspan="2">
	                		<input type="button" id="save" value="저장"/>
	                		<input type="button" value="취소"/>
	            		</td>
        		   </tr>
			</table>
		</form>
</div>
</div>
</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/sample/sample2.js"></script>