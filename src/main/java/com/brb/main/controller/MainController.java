package com.brb.main.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.brbcom.common.util.RequestParameterUtil;
import com.brb.brbcom.common.util.Util;
import com.brb.main.service.MainService;

/**
 * 
 * @author back
 *
 */
@Controller
public class MainController {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	MainService mainService;
	
	@RequestMapping("/main")
	public ModelAndView testInfo() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/main") ;
		return mav;
	}
	
	
	/**
	 * 최근가입회원목록
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "main/memList" , method = RequestMethod.POST)
	public ModelAndView memListSearch(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		
		HttpSession session = request.getSession(false);
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		
		if(Integer.valueOf(session.getAttribute("ADM_CLASS").toString()) > 2){
			dMap.put("R_BTBS_SQ", session.getAttribute("BTBS_SQ"));
		}
		
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
		infoList = mainService.getMemList(dMap);
		//리스트 카운트 조회
		infoMap = mainService.getMemListTotal(dMap);
		
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
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "main/asList" , method = RequestMethod.POST)
	public ModelAndView b2bListSearch(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		List infoList = null;
		int rowSize = Util.attrParseInt(dMap, "rows");
		int currentPage = Util.attrParseInt(dMap, "page");
		String sidx = dMap.getString("sidx") == "" ? "AS_REQ_SQ" : dMap.getString("sidx");
		
		if("AS_REQ_SQ".equals(sidx)){
			sidx = "AS_REQ_SQ";
		}
		String sord = dMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    dMap.put("orderBy", orderbyColum);
	  	dMap.put("R_PAGE", (currentPage -1) *10 );
	  	dMap.put("R_ROW", rowSize);
		int totalcnt = 0;
		
		//리스트 조회
		infoList = mainService.getAsList(dMap);
		//리스트 카운트 조회
		infoMap = mainService.getAsListTotal(dMap);
		
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
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "main/qnaList" , method = RequestMethod.POST)
	public ModelAndView qnaListSearch(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
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
		infoList = mainService.getQnaList(dMap);
		//리스트 카운트 조회
		infoMap = mainService.getQnaListTotal(dMap);
		
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
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "main/getMainGrape" , method = RequestMethod.POST)
	public ModelAndView getMainGrape(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		HttpSession session = request.getSession(false);
		if(Integer.valueOf(session.getAttribute("ADM_CLASS").toString()) > 2){
			dMap.put("R_BTBS_SQ", session.getAttribute("BTBS_SQ"));
		}
		
		
		List appList = null;
		List memList = null;
		try{
			// app 다운로드 
			appList = mainService.getappList(dMap);
		
			memList = mainService.getmemList(dMap);
		} catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("appList", appList);
		view.addObject("memList", memList);
		return view;
	}
	
}
