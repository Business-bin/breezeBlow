package com.brb.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brb.app.model.PolyVo;
import com.brb.app.service.PolyService;
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
public class PolyController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PolyController.class);


	@Autowired
	PolyService polyService;

	@Autowired
	CommonService commonService;

	/**
	 * 개인정보동의 리스트
	 * @param int
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "app/polyList")
	public ModelAndView polyList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("pMap", pMap);
		view.addObject("poly", polyService.getPoly());
		view.setViewName("app/polyList");
		return view;
	}

	/**
	 * 개인정보동의 리스트 검색
	 * @param HttpServletRequest
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "app/polySearch")
	public ModelAndView appTable(HttpServletRequest request){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap	= RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "POLY_SQ" : bMap.getString("sidx");

		if("POLY_SQ".equals(sidx)){
			sidx = "POLY_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  " + sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *10 );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List)polyService.getPolyList(bMap).get("polyList");
		//리스트 카운트 조회
		totalcnt = (int)polyService.getPolyList(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * 개인정보동의 상세
	 * @param String, String
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "app/polyDet")
	public ModelAndView appDet(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("dMap", dMap);
		if(dMap.getString("gubun").equals("det")){
			view.addObject("polyDet", polyService.getPolyDet(dMap.getInt("polySq")));
		}
		view.addObject("poly", polyService.getPoly());
		view.addObject("gubun", dMap.getString("gubun"));
		view.setViewName("app/polyDet");
		return view;
	}

	/**
	 * 개인정보동의 수정
	 * @param PolyVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "app/updatePoly")
	public void updatePoly(@ModelAttribute PolyVo pvo, HttpSession session, HttpServletRequest request){
		pvo.setUptAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		polyService.updatePoly(pvo);
		commonService.addAdminActLog(request);
	}

	/**
	 * 개인정보동의 등록
	 * @param PolyVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "app/insertPoly")
	public void insertPoly(@ModelAttribute PolyVo pvo, HttpSession session, HttpServletRequest request){
		pvo.setRegAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		polyService.insertPoly(pvo);
		commonService.addAdminActLog(request);
	}

	/**
	 * 노출 개수 체크
	 * @param PolyVo
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "app/checkPoly")
	public Integer checkPoly(@ModelAttribute PolyVo pvo){
		return polyService.checkPoly(pvo);
	}
}