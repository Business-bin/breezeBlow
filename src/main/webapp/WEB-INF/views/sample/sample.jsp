<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- css 변환 -->
<link rel="stylesheet" href="/resources/css/sample/sample.css">	
<div id="o-wrapper">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/leftMenu.jsp" %>
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

				<table class="basictables basichover">
					<thead>
						<tr>
							<th><span class="checkboxs"><input type="checkbox" id="chkall" name="" onclick="chkall('listno',$(this))"/><label for="chkall">No</label></span></th>
							<th>Menu</th>
							<th>link</th>
							<th>Target</th>
							<th>Web</th>
							<th>Mobile</th>
							<th>Status</th>
							<th>Register Date</th>
							<th>Modify Date</th>
							<th>-</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="tcenter"><span class="checkboxs"><input type="checkbox" id="chk_7" name="listno"/><label for="chk_7">7</label></span></td>
							<td><i class="axi axi-dashboard"></i> HOME</td>
							<td><a href="/index.php">/index.php</a></td>
							<td class="tcenter">_self</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">2018-02-07 20:59:13</td>
							<td class="tcenter">2018-02-09 11:08:23</td>
							<td class="tcenter"><button class="button" onclick="location.href='#'">Modify</button></td>
						</tr>
						<tr>
							<td class="tcenter"><span class="checkboxs"><input type="checkbox" id="chk_6" name="listno"/><label for="chk_6">6</label></span></td>
							<td><i class="axi axi-ion-android-information"></i> Infomation</td>
							<td><a href="/html/infomation.php">/html/infomation.php</a></td>
							<td class="tcenter">_self</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">2018-02-09 10:42:29</td>
							<td class="tcenter">2018-02-09 11:09:36</td>
							<td class="tcenter"><button class="button" onclick="location.href='#'">Modify</button></td>
						</tr>
						<tr>
							<td class="tcenter"><span class="checkboxs"><input type="checkbox" id="chk_5" name="listno"/><label for="chk_5">5</label></span></td>
							<td><i class="axi axi-ion-clipboard"></i> Notice</td>
							<td><a href="/html/notice_list.php">/html/notice_list.php</a></td>
							<td class="tcenter">_self</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">2018-02-09 10:43:33</td>
							<td class="tcenter">2018-02-09 11:11:01</td>
							<td class="tcenter"><button class="button" onclick="location.href='#'">Modify</button></td>
						</tr>
						<tr>
							<td class="tcenter"><span class="checkboxs"><input type="checkbox" id="chk_4" name="listno"/><label for="chk_4">4</label></span></td>
							<td><i class="axi axi-calendar"></i> Schedule</td>
							<td><a href="/html/schedule.php">/html/schedule.php</a></td>
							<td class="tcenter">_self</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">2018-02-09 11:11:35</td>
							<td class="tcenter">2018-02-13 13:17:17</td>
							<td class="tcenter"><button class="button" onclick="location.href='#'">Modify</button></td>
						</tr>
						<tr>
							<td class="tcenter"><span class="checkboxs"><input type="checkbox" id="chk_3" name="listno"/><label for="chk_3">3</label></span></td>
							<td><i class="axi axi-person"></i> Buyer</td>
							<td><a href="/html/buyer_list.php">/html/buyer_list.php</a></td>
							<td class="tcenter">_self</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">2018-02-09 11:12:00</td>
							<td class="tcenter">2018-02-12 16:13:59</td>
							<td class="tcenter"><button class="button" onclick="location.href='#'">Modify</button></td>
						</tr>
						<tr>
							<td class="tcenter"><span class="checkboxs"><input type="checkbox" id="chk_2" name="listno"/><label for="chk_2">2</label></span></td>
							<td><i class="axi axi-group"></i> Company</td>
							<td><a href="/html/company_list.php">/html/company_list.php</a></td>
							<td class="tcenter">_self</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">2018-02-09 11:13:43</td>
							<td class="tcenter">2018-02-13 13:18:19</td>
							<td class="tcenter"><button class="button" onclick="location.href='#'">Modify</button></td>
						</tr>
						<tr>
							<td class="tcenter"><span class="checkboxs"><input type="checkbox" id="chk_1" name="listno"/><label for="chk_1">1</label></span></td>
							<td><i class="axi axi-happytalk-counselor"></i> Q&amp;A</td>
							<td><a href="#">#</a></td>
							<td class="tcenter">_self</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">Y</td>
							<td class="tcenter">2018-02-09 11:14:15</td>
							<td class="tcenter"></td>
							<td class="tcenter"><button class="button" onclick="location.href='#'">Modify</button></td>
						</tr>
					</tbody>
				</table>
				<div class="paging"  id="paging" onclick="clickPaging(event)">
				</div>
				<div class="button_area tright">
					<input type="text" id="totalCnt" name="totalCnt" value="10"/>
					<input type="text" id="test" name="test" value="10"/>
					<a href="#" class="button" style="float:left;" onclick="chkval('listno', 'MenuDelete', '/inc/datainfo.php', '/html/menu_list.php');"><i class="axi axi-delete2"></i> Delete</a>
					<a href="/html/menu_form.php" class="button"><i class="axi axi-menu"></i> Menu Add</a>
				</div>
			</div>
		</div>
	</section>
	 
	 
<!-- 소스 작성 end-->
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- js 작성 -->
<script src="/resources/js/sample/sample.js"></script>