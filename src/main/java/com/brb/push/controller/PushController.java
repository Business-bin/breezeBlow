package com.brb.push.controller;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.brbcom.common.util.RequestParameterUtil;
import com.brb.brbcom.common.util.Util;
import com.brb.push.service.PushService;

/**
 *
 * @author back
 *
 */
@Controller
public class PushController {


	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PushController.class);


	@Autowired
	PushService pushService;


	public PushController(){
	}

	/**
	 * sms발송이력
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "push/pushList" )
	public ModelAndView pushlist(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("pMap", pMap);
		view.setViewName("push/pushList") ;
		return view;
	}

	/**
	 * push신규발송
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "push/pushNew" )
	public ModelAndView pushNew(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("push/pushNew") ;
		return view;
	}

	/**
	 * push발송 상세 내역
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "push/pushDetPop" )
	public ModelAndView pushDetPop(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		try{
			infoMap = pushService.pushDetInfo(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("DET", infoMap);
		view.setViewName("push/pushDet") ;
		return view;
	}


	/**
	 * push발송 상세 내역
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "push/pushDet" )
	public ModelAndView pushDet(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		try{
			infoMap = pushService.pushDetInfo(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("dMap", dMap);
		view.addObject("DET", infoMap);
		view.setViewName("push/pushDet") ;
		return view;
	}

	/**
	 * 주소
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "push/addrList" , method = RequestMethod.POST)
	public ModelAndView addrList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		try{
			infoList = pushService.getAddrCode(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("ADDR", infoList);
		return view;
	}


	/**
	 * btb사이트
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "push/getBtbSite" , method = RequestMethod.POST)
	public ModelAndView getBtbSite(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		try{
			infoList = pushService.getBtbSite(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("B2B", infoList);
		return view;
	}


	/**
	 * 회원 발송 대상건수
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "push/getSendCnt" , method = RequestMethod.POST)
	public ModelAndView getSendCnt(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		try{
			infoMap = pushService.getSendCnt(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("MEMCNT", infoMap);
		return view;
	}

	/**
	 * push이력조회
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "push/pushListSearch" , method = RequestMethod.POST)
	public ModelAndView smsListSearch(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		List infoList = null;
		int rowSize = Util.attrParseInt(dMap, "rows");
		int currentPage = Util.attrParseInt(dMap, "page");
		String sidx = dMap.getString("sidx") == "" ? "PUSH_SQ" : dMap.getString("sidx");

		if("PUSH_SQ".equals(sidx)){
			sidx = "PUSH_SQ";
		}
		String sord = dMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    dMap.put("orderBy", orderbyColum);
	  	dMap.put("R_PAGE", (currentPage -1) *10 );
	  	dMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = pushService.getPushList(dMap);
		//리스트 카운트 조회
		infoMap = pushService.getPushListTotal(dMap);

		if(null != infoMap ){
			totalcnt  = Integer.parseInt(infoMap.get("total").toString());
		}
		double dSize = Util.attrParseInt(dMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}


	/**
	 *  push발송 + 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "push/sendpush" , method = RequestMethod.POST)
	public @ResponseBody Map<Object, Object> sedpush(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		Map<Object, Object> infoMap = new HashMap<Object, Object>();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		dMap.put("R_STAT", "001");
        dMap.put("R_REG_ADM_SQ", session.getAttribute("ADM_SQ"));
        dMap.put("R_SMS_RES_DTTM",  dMap.getString("R_RESERVETIME"));
        dMap.put("R_RESERVE_YN",  dMap.getString("R_PUSH_TYPE")=="0" ? "N" : "Y");

		//StringBuffer response = new StringBuffer();
		//StringBuffer sendParam = new StringBuffer();
		List<BrbMap<Object, Object>> sendInfoList = null;
		try {
			sendInfoList = pushService.getSendListInfo(dMap);

			if(sendInfoList.size() > 0){
				BrbMap<Object, Object> bMap = pushService.getApp();

				// push api
				try {
		    		   String jsonResponse;
		    		   String appId = bMap.getString("APP_ID");
		    		   String appKey = bMap.getString("APP_KEY");
		    		   URL url = new URL("https://onesignal.com/api/v1/notifications");
		    		   HttpURLConnection con = (HttpURLConnection)url.openConnection();
		    		   con.setUseCaches(false);
		    		   con.setDoOutput(true);
		    		   con.setDoInput(true);

		    		   con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		    		   con.setRequestProperty("Authorization", "Basic "+appKey);
		    		   con.setRequestMethod("POST");
		    		   String title = dMap.getString("R_PUSH_SUBJECT");
		    		   String cont = dMap.getString("R_PUSH_CONT");

		    		   String pushId = "";
		    		   String strJsonBody = "";
		    		   if(dMap.getString("R_ADDR_1").equals("")
		    				   &&dMap.getString("R_BTBS_SQ").equals("")
		    				   &&dMap.getString("R_MOBILE_TYPE").equals("")
		    				   &&dMap.getString("R_MD_SQ").equals("")){
		    			   strJsonBody = "{"
			                       + "\"app_id\": \""+appId+"\","
			                       + "\"included_segments\": [\"All\"],"
			                       + "\"headings\": {\"en\":\""+title+"\"},"
			                       +   "\"contents\": {\"en\":\""+cont+"\"}"
			                       + "}";
		    		   }else{
			    		   for(int p=0;p<sendInfoList.size();p++){
			    			   pushId += "\""+sendInfoList.get(p).getString("PUSH_ID")+"\"";
			    			   if(p < sendInfoList.size()-1){
			    				   pushId += ",";
			    			   }
			    		   }
		    			   strJsonBody = "{"
		    					   + "\"app_id\": \""+appId+"\","
			                       + "\"include_player_ids\": ["+pushId+"],"
			                       + "\"headings\": {\"en\":\""+title+"\"},"
			                       +   "\"contents\": {\"en\":\""+cont+"\"}"
			                       + "}";
		    		   }



		    		   System.out.println("strJsonBody:\n" + strJsonBody);

		    		   byte[] sendBytes = strJsonBody.getBytes("UTF-8");
		    		   con.setFixedLengthStreamingMode(sendBytes.length);

		    		   OutputStream outputStream = con.getOutputStream();
		    		   outputStream.write(sendBytes);

		    		   int httpResponse = con.getResponseCode();
		    		   System.out.println("httpResponse: " + httpResponse);

		    		   if (  httpResponse >= HttpURLConnection.HTTP_OK
		    		      && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
		    		      Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
		    		      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
		    		      scanner.close();
		    		   }
		    		   else {
		    		      Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
		    		      jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
		    		      scanner.close();
		    		   }
		    		   System.out.println("jsonResponse:\n" + jsonResponse);

		    		} catch(Throwable t) {
		    		   t.printStackTrace();
		    		}
				// push api





		        BrbMap<Object, Object> inMap = dMap;
		        inMap.put("R_ADDR_1", dMap.getString("R_ADDR_1")==""?"전체": dMap.getString("R_ADDR_1"));
		        inMap.put("R_ADDR_2", dMap.getString("R_ADDR_1")==""?"전체": dMap.getString("R_ADDR_2"));
		        inMap.put("R_ADDR_3", dMap.getString("R_ADDR_1")==""?"전체": dMap.getString("R_ADDR_3"));

		        //sms발송이력저장
		        pushService.addPush(inMap);
		        pushService.insertPushLog(sendInfoList);
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		return infoMap;
	}


	/**
	 *  desc : push발송(상태발송) + 저장
	 *
	 *  MEM_SQ   :회원sq
	 *  USER_EMAIL : test@test.com
	 *  USER_NM :홍길동
	 *  USER_PHONE : 01011112222
	 *  MSG :메세지
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "push/sendPushType" , method = RequestMethod.POST)
	public @ResponseBody Map<Object, Object> sedPushType(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		Map<Object, Object> infoMap = new HashMap<Object, Object>();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		//StringBuffer response = new StringBuffer();
		//StringBuffer sendParam = new StringBuffer();

		try {
			//================================================
			// push todo
			//================================================

	        BrbMap<Object, Object> inMap = dMap;
	        inMap.put("R_MEM_SQ", dMap.getString("MEM_SQ"));
	        inMap.put("R_USER_EMAIL", dMap.getString("USER_EMAIL"));
	        inMap.put("R_USER_NM", dMap.getString("USER_NM"));
	        inMap.put("R_USER_PHONE", dMap.getString("USER_PHONE"));
	        inMap.put("R_MSG", dMap.getString("MSG"));
	        inMap.put("R_REGADMINSQ", session.getAttribute("ADM_SQ"));

	        //sms발송이력저장
	        pushService.addPushType(inMap);

	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		return infoMap;
	}



	/**
	 * push이력조회
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "push/mdListSearch" , method = RequestMethod.POST)
	public ModelAndView mdListSearch(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		List infoList = null;
		int rowSize = Util.attrParseInt(dMap, "rows");
		int currentPage = Util.attrParseInt(dMap, "page");
		String sidx = dMap.getString("sidx") == "" ? "MD_SQ" : dMap.getString("sidx");

		if("MD_SQ".equals(sidx)){
			sidx = "MD_SQ";
		}
		String sord = dMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    dMap.put("orderBy", orderbyColum);
	  	dMap.put("R_PAGE", (currentPage -1) *10 );
	  	dMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = pushService.getMdList(dMap);
		//리스트 카운트 조회
		infoMap = pushService.getMdListTotal(dMap);

		if(null != infoMap ){
			totalcnt  = Integer.parseInt(infoMap.get("total").toString());
		}
		double dSize = Util.attrParseInt(dMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}
}
