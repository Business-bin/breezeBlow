package com.brb.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brb.app.model.AppVo;
import com.brb.app.model.PolyVo;
import com.brb.app.service.AppService;
import com.brb.brbcom.common.collections.BrbMap;
import com.brb.brbcom.common.util.RequestParameterUtil;
import com.brb.brbcom.common.util.Util;
import com.brb.common.service.CommonService;
/**
 *
 * @author back
 *
 */
@Controller
public class AppController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AppController.class);


	@Autowired
	AppService appService;

	@Autowired
	CommonService commonService;

	/**
	 * APP버전 리스트
	 * @param int
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="app/appVrsList")
	public ModelAndView appVrsList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("pMap", pMap);
		view.setViewName("app/appVrsList");
		return view;
	}

	/**
	 * APP버전 리스트 검색
	 * @param HttpServletRequest, HttpServletResponse
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="app/appVrsSearch")
	public ModelAndView appVrsSearch(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap = RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "APP_SQ" : bMap.getString("sidx");

		if("APP_SQ".equals(sidx)){
			sidx = "APP_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *10 );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List)appService.getAppVrsList(bMap).get("appVrsList");
		//리스트 카운트 조회
		totalcnt = (int)appService.getAppVrsList(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * APP버전 상세
	 * @param String, String
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="app/appVrsDet")
	public ModelAndView appVrsDet(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("dMap", dMap);
		view.addObject("gubun", dMap.getString("gubun"));
		view.addObject("appDet", appService.getAppVrsDet(dMap.getInt("appSq")));
		view.addObject("appStat", appService.getAppStat());
		view.setViewName("app/appVrsDet");
		return view;
	}

	/**
	 * APP버전 수정
	 * @param AppVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="app/updateAppVrs")
	public void updateAppVrs(@ModelAttribute AppVo avo, HttpSession session, HttpServletRequest request){
		avo.setUptAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		appService.updateAppVrs(avo);
		commonService.addAdminActLog(request);
	}

	/**
	 * APP버전 등록
	 * @param AppVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="app/insertAppVrs")
	public void insertAppVrs(@ModelAttribute AppVo avo, HttpSession session, HttpServletRequest request){
		avo.setRegAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		appService.insertAppVrs(avo);
		commonService.addAdminActLog(request);
	}

	/**
	 * 사용자통계
	 * @param int
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="app/appUserStatusList")
	public ModelAndView appUserStatList(){
		ModelAndView view = new ModelAndView();
		view.setViewName("app/appUserStatusList");
		return view;
	}

	/**
	 * 그래프  통계 데이터
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "app/getAppGrape" , method = RequestMethod.POST)
	public ModelAndView getAppGrape(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		List deviceList = null;
		List osList = null;
		List ageList = null;
		try{
			deviceList = appService.getDeviceList(dMap);
			osList = appService.getOsList(dMap);
			ageList = appService.getAgeList(dMap);

		} catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("deviceList", deviceList);
		view.addObject("osList", osList);
		view.addObject("ageList", ageList);

		return view;
	}

	/**
	 * 튜토리얼 리스트
	 * @param
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "app/tutorialList")
	public ModelAndView tutorialList(){
		ModelAndView view = new ModelAndView();
		view.setViewName("app/tutorialList");
		return view;
	}

	/**
	 * APP버전 리스트 검색
	 * @param HttpServletRequest, HttpServletResponse
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="app/tutorialSearch")
	public ModelAndView tutorialSearch(HttpServletRequest request){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap = RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "TUT_SQ" : bMap.getString("sidx");

		if("TUT_SQ".equals(sidx)){
			sidx = "TUT_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *10 );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List)appService.getTutorialList(bMap).get("tutorialList");
		//리스트 카운트 조회
		totalcnt = (int)appService.getTutorialList(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * 튜토리얼 상세
	 * @param
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "app/tutorialDet")
	public ModelAndView appDet(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("dMap", dMap);
		if(dMap.getString("gubun").equals("det")){
			view.addObject("tutorialDet", appService.getTutorialDet(dMap.getInt("tutSq")));
		}
		view.addObject("gubun", dMap.getString("gubun"));
		view.setViewName("app/tutorialDet");
		return view;
	}

	/**
	 * 튜토리얼 수정
	 * @param HttpSession, HttpServletRequest
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "app/updateTutorial")
	public void updateTutorial(HttpSession session, HttpServletRequest request){
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		dMap.put("uptAdmSq", (String)session.getAttribute("ADM_SQ"));
		appService.updateTutorial(dMap);
		commonService.addAdminActLog(request);
	}

	/**
	 * 개인정보동의 등록
	 * @param PolyVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "app/insertTutorial")
	public void insertTutorial(@ModelAttribute PolyVo pvo, HttpSession session, HttpServletRequest request){
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		dMap.put("regAdmSq", (String)session.getAttribute("ADM_SQ"));
		appService.insertTutorial(dMap);
		commonService.addAdminActLog(request);
	}

	/**
	 * 튜토리얼 수정
	 * @param HttpSession, HttpServletRequest
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "app/deleteTutorial")
	public void deleteTutorial(HttpSession session, HttpServletRequest request){
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		dMap.put("delAdmSq", (String)session.getAttribute("ADM_SQ"));
		appService.deleteTutorial(dMap);
		commonService.addAdminActLog(request);
	}

	/**
	 * 노출 개수 체크
	 * @param
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "app/checkTutorial")
	public int checkTutorial(@RequestParam int tutSq){
		return appService.checkTutorial(tutSq);
	}
}
