package com.brb.sms.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.brb.brbcom.common.util.ApplicationContextHolder;
import com.brb.brbcom.common.util.RequestParameterUtil;
import com.brb.brbcom.common.util.Util;
import com.brb.brbcom.foundation.property.PropertyService;
import com.brb.common.service.CommonService;
import com.brb.sms.service.SmsService;

/**
 *
 * @author back
 *
 */
@Controller
public class SmsController {

	protected PropertyService propertyService;
	String smsId ="";
	String smsPw = "";
	String adminPhone = "";
	String smsUrls = "";
	String smsUrlm = "";
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SmsController.class);


	@Autowired
	SmsService smsService;

	@Autowired
	CommonService commonService;

	public SmsController(){
		propertyService = (PropertyService)ApplicationContextHolder.getContext().getBean("propertiesService");
		this.smsId = propertyService.getString("SMS_ID");
		this.smsPw = propertyService.getString("SMS_PW");
		this.smsUrls = propertyService.getString("SMS_URL_S");
		this.smsUrlm = propertyService.getString("SMS_URL_M");
		this.adminPhone 	= propertyService.getString("SMS_ADMIN");
	}
	/**
	 * sms발송이력
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sms/smsList" )
	public ModelAndView smslist(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("pMap", pMap);
		view.setViewName("sms/smsList") ;
		return view;
	}

	/**
	 * sms신규발송
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sms/smsNew" )
	public ModelAndView smsNew(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("sms/smsNew") ;
		return view;
	}

	/**
	 * sms발송 상세 내역
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "sms/smsDetPop" )
	public ModelAndView smsDetPop(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		try{
			infoMap = smsService.smsDetInfo(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("DET", infoMap);
		view.setViewName("sms/smsDet") ;
		return view;
	}


	/**
	 * sms발송 상세 내역
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "sms/smsDet" )
	public ModelAndView smsDet(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		try{
			infoMap = smsService.smsDetInfo(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("dMap", dMap);
		view.addObject("DET", infoMap);
		view.setViewName("sms/smsDet") ;
		return view;
	}

	/**
	 * 주소
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "sms/addrList" , method = RequestMethod.POST)
	public ModelAndView addrList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		try{
			infoList = smsService.getAddrCode(dMap);
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
	@RequestMapping(value = "sms/getBtbSite" , method = RequestMethod.POST)
	public ModelAndView getBtbSite(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		try{
			infoList = smsService.getBtbSite(dMap);
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
	@RequestMapping(value = "sms/getSendCnt" , method = RequestMethod.POST)
	public ModelAndView getSendCnt(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		try{
			infoMap = smsService.getSendCnt(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("MEMCNT", infoMap);
		return view;
	}

	/**
	 * sms이력조회
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "sms/smsListSearch" , method = RequestMethod.POST)
	public ModelAndView smsListSearch(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		List infoList = null;
		int rowSize = Util.attrParseInt(dMap, "rows");
		int currentPage = Util.attrParseInt(dMap, "page");
		String sidx = dMap.getString("sidx") == "" ? "SMS_SQ" : dMap.getString("sidx");

		if("SMS_SQ".equals(sidx)){
			sidx = "SMS_SQ";
		}
		String sord = dMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    dMap.put("orderBy", orderbyColum);
	  	dMap.put("R_PAGE", (currentPage -1) *10 );
	  	dMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = smsService.getSmsList(dMap);
		//리스트 카운트 조회
		infoMap = smsService.getSmsListTotal(dMap);

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
	 * sms화면 이동
	 * @return
	 */
	@RequestMapping("sms/sms")
	public ModelAndView smsSend() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sms/sms") ;
		return mav;
	}

	/**
	 *  sms발송 + 저장
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "sms/sendsms" , method = RequestMethod.POST)
	public @ResponseBody Map<Object, Object> sedsms(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		Map<Object, Object> infoMap = new HashMap<Object, Object>();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		dMap.put("R_STAT", "001");
        dMap.put("R_REG_ADM_SQ", session.getAttribute("ADM_SQ"));
        dMap.put("R_SMS_RES_DTTM",  dMap.getString("R_RESERVETIME"));
        dMap.put("R_RESERVE_YN",  dMap.getString("R_SMS_TYPE").equals("0") ? "N" : "Y");

        String type = dMap.getString("R_SEND_TYPE");

		StringBuffer response = new StringBuffer();
		StringBuffer sendParam = new StringBuffer();
		List sendInfoList = null;
		try {
			String sendPhon = "";
			String sendName = "";
			sendInfoList = smsService.getSendListInfo(dMap);

			if(sendInfoList.size() > 0){
				for(int i=0 ; i < sendInfoList.size() ; i++ ){
					BrbMap inMap = (BrbMap) sendInfoList.get(i);
					if(i>0){
						sendPhon+=",";
						sendName+=",";
					}
					sendPhon += inMap.getString("USER_HP");
					sendName += inMap.getString("MEM_NM");
				}
				URL obj;
				if(type.equals("01")){
					obj = new URL(smsUrls);
				}else{
					obj = new URL(smsUrlm);
				}
				URLConnection conn = obj.openConnection();
				// 문자박사 아이디
				sendParam.append("remote_id=" + smsId );
				// 문자박사 패스워드
				sendParam.append("&remote_pass=" + smsPw);
				// 발송후 리턴할 주소 형식
				sendParam.append("&remote_returnurl=" + "");
				//수신번호 개수 default : 1, 다수 번호,번호
				sendParam.append("&remote_num=" + dMap.getString("R_SMS_CNT"));
				//예약 1 , 일반 0
				sendParam.append("&remote_reserve=" + dMap.getString("R_SMS_TYPE"));
				//예약시 예약 시간 년-월-일 시:분
				sendParam.append("&remote_reservetime=" + dMap.getString("R_RESERVETIME"));
				//수신번호 다수일때는 쉼표','로 구분
				sendParam.append("&remote_phone=" + sendPhon);
				//수신번호 다수일때는 이름을 쉼표','로 구분
				sendParam.append("&remote_name=" + sendName);
				//발신번호 숫자만 입력
				sendParam.append("&remote_callback=" + adminPhone);
				//장문(lms)전송시에만 입력
				sendParam.append("&remote_subject=" + dMap.getString("R_SUBJECT"));
				//송신 메세지
				sendParam.append("&remote_msg=" + dMap.getString("R_MSG"));
				//미리 업로드 된 이미지 파일명
				sendParam.append("&remote_contents=" + "");
				//사용자 정의 변수1
				sendParam.append("&remote_etc1=" + "");
				//사용자 정의 변수2
				sendParam.append("&remote_etc2=" + "");


		        // POST 값 전송일 경우 true
		        conn.setDoOutput(true);
		        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		        // 파라미터를 wr에 넣어주고 flush
		        wr.write(sendParam.toString());
		        wr.flush();

		        // in에 readLine이 null이 아닐때까지 StringBuffer에 append
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
		        wr.close();
		        BrbMap<Object, Object> inMap = dMap;
		        inMap.put("R_ADDR_1", dMap.getString("R_ADDR_1")==""?"전체": dMap.getString("R_ADDR_1"));
		        inMap.put("R_ADDR_2", dMap.getString("R_ADDR_1")==""?"전체": dMap.getString("R_ADDR_2"));
		        inMap.put("R_ADDR_3", dMap.getString("R_ADDR_1")==""?"전체": dMap.getString("R_ADDR_3"));

		        //sms발송이력저장
		        smsService.addSms(inMap);

		        //action log
		        commonService.addAdminActLog(request);
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		return infoMap;
	}


	/**
	 *  desc : sms발송(상태발송) + 저장
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
	@RequestMapping(value = "sms/sendSmsType" , method = RequestMethod.POST)
	public @ResponseBody Map<Object, Object> sedSmsType(HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		Map<Object, Object> infoMap = new HashMap<Object, Object>();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		StringBuffer response = new StringBuffer();
		StringBuffer sendParam = new StringBuffer();

		String type = dMap.getString("R_SEND_TYPE");

		try {
			URL obj;
			if(type.equals("01")){
				obj = new URL(smsUrls);
			}else{
				obj = new URL(smsUrlm);
			}
			URLConnection conn = obj.openConnection();
			// 문자박사 아이디
			sendParam.append("remote_id=" + smsId );
			// 문자박사 패스워드
			sendParam.append("&remote_pass=" + smsPw);
			// 발송후 리턴할 주소 형식
			sendParam.append("&remote_returnurl=" + "");
			//수신번호 개수 default : 1, 다수 번호,번호
			sendParam.append("&remote_num=1");
			//예약 1 , 일반 0
			sendParam.append("&remote_reserve=0");
			//예약시 예약 시간 년-월-일 시:분
			sendParam.append("&remote_reservetime=");
			//수신번호 다수일때는 쉼표','로 구분
			sendParam.append("&remote_phone=" +  dMap.getString("USER_PHONE"));
			//수신번호 다수일때는 이름을 쉼표','로 구분
			sendParam.append("&remote_name=" + dMap.getString("USER_NM"));
			//발신번호 숫자만 입력
			sendParam.append("&remote_callback=" + adminPhone);
			//장문(lms)전송시에만 입력
			sendParam.append("&remote_subject=");
			//송신 메세지
			sendParam.append("&remote_msg=" + dMap.getString("MSG"));
			//미리 업로드 된 이미지 파일명
			sendParam.append("&remote_contents=" + "");
			//사용자 정의 변수1
			sendParam.append("&remote_etc1=" + "");
			//사용자 정의 변수2
			sendParam.append("&remote_etc2=" + "");


	        // POST 값 전송일 경우 true
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        // 파라미터를 wr에 넣어주고 flush
	        wr.write(sendParam.toString());
	        wr.flush();

	        // in에 readLine이 null이 아닐때까지 StringBuffer에 append
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	        wr.close();

	        BrbMap<Object, Object> inMap = dMap;
	        inMap.put("R_MEM_SQ", dMap.getString("MEM_SQ"));
	        inMap.put("R_USER_EMAIL", dMap.getString("USER_EMAIL"));
	        inMap.put("R_USER_NM", dMap.getString("USER_NM"));
	        inMap.put("R_USER_PHONE", dMap.getString("USER_PHONE"));
	        inMap.put("R_MSG", dMap.getString("MSG"));
	        inMap.put("R_REGADMINSQ", session.getAttribute("ADM_SQ"));

	        //sms발송이력저장
	        smsService.addSmsType(inMap);

	        //action log
	        commonService.addAdminActLog(request);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		return infoMap;
	}

	/**
	 *  sms테스트
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "sms/sendsms2" , method = RequestMethod.POST)
	public @ResponseBody Map<Object, Object> sedsms2(HttpServletRequest request) throws Exception {
		Map<Object, Object> infoMap = new HashMap<Object, Object>();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);


		StringBuffer response = new StringBuffer();

		try {
			String url = "http://www.munja123.com/Remote/RemoteSms.html";
			URL obj = new URL(url);
			URLConnection conn = obj.openConnection();
			String param1="senko";
			String param2="hagood74";

			String urlParameters = "remote_id=" + param1 +"&remote_pass=" + param2+"&remote_num=" + "1"
					+"&remote_reserve="+"0"+"&remote_phone="+"01042233619"+"&remote_name="+"홍길동"
					+"&remote_subject="+""+"&remote_callback="+"01054094926"+"&remote_msg="+"테스트"
					+"&remote_returnurl=";


	        // POST 값 전송일 경우 true
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        // 파라미터를 wr에 넣어주고 flush
	        wr.write(urlParameters);
	        wr.flush();

	        // in에 readLine이 null이 아닐때까지 StringBuffer에 append
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	        wr.close();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }

		//response
		//0000|전송 성공|1|6496|||||||1520483032
        //infoMap.put("list", listMap);
		return infoMap;
	}


	/**
	 * sms이력조회
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "sms/mdListSearch" , method = RequestMethod.POST)
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
		infoList = smsService.getMdList(dMap);
		//리스트 카운트 조회
		infoMap = smsService.getMdListTotal(dMap);

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
