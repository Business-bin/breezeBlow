<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="customTag" uri="/WEB-INF/customTag.tld" %>
<!-- css 변환 -->
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
<!-- 소스 작성 start-->
<script type="text/javascript">
	function checkFileType(filePath) {
		var fileFormat = filePath.split(".");
		if (fileFormat.indexOf("xls") > -1) {
			$("#excelType").val("xls");
			return true;
		} else if (fileFormat.indexOf("xlsx") > -1) {
			$("#excelType").val("xlsx");
			return true;
		} else {
			return false;
		}
	}

	function check() {
		var file = $("#excel").val();
		if (file == "" || file == null) {
			alert("파일을 선택");
			return false;
		} else if (!checkFileType(file)) {
			alert("엑셀 파일만 업로드");
			return false;
		}
		fileupload("excelUpload", "/product/excelUploadFile");
	}

</script>

<section>
		<div id="dim-layer"></div>
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
				<div class="navi">
					<ul>
						<li>파일업로드</li>
					</ul>
				</div>
			<form action="/product/excelUploadFile" method="post" id="excelUpload" enctype="multipart/form-data">
				<input type="file" id="excel" class="init" name="excel"  />
				<input type="text" id="excelType" class="init" name="excelType" />
				<input multiple="multiple"  type="file" class="init" name="imgFile" />
				<input type="button" onclick="check()" value="업로드"/>
			</form>
		</div>
		</div>
	</section>
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/product/product.js"></script>