package com.brb.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.brbcom.common.util.RequestParameterUtil;
import com.brb.brbcom.common.util.Util;
import com.brb.common.service.CommonService;
import com.brb.member.model.DataVo;
import com.brb.member.model.MemVo;
import com.brb.member.service.MemService;
/**
 *
 * @author back
 *
 */
@Controller
public class MemController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MemController.class);


	@Autowired
	MemService memService;

	@Autowired
	CommonService commonService;

	/**
	 * 회원 리스트
	 * @param MemVo
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "mem/memList")
	public ModelAndView memList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("pMap", pMap);
		view.addObject("btbsList", memService.getBtbsList());
		view.addObject("sdList", memService.getSdList());
		view.setViewName("mem/memList");
		return view;
	}

	/**
	 * 회원 리스트 검색
	 * @param HttpServletRequest, HttpServletResponse
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "mem/memSearch")
	public ModelAndView memTable(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap = RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "MEM_SQ" : bMap.getString("sidx");

		if("MEM_SQ".equals(sidx)){
			sidx = "MEM_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *10 );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List)memService.getMemList(bMap).get("memList");
		//리스트 카운트 조회
		totalcnt = (int)memService.getMemList(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * B2B사이트 리스트 검색
	 * @param HttpServletRequest, HttpServletResponse
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "mem/btbsSearch")
	public ModelAndView btbsSearch(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> adminMap = null;
		List infoList = null;
		int rowSize = Util.attrParseInt(dMap, "rows");
		int currentPage = Util.attrParseInt(dMap, "page");
		String sidx = dMap.getString("sidx") == "" ? "REG_DTTM" : dMap.getString("sidx");

		if("REG_DTTM".equals(sidx)){
			sidx = "REG_DTTM";
		}
		String sord = dMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    dMap.put("orderBy", orderbyColum);
	  	dMap.put("R_PAGE", (currentPage -1) *rowSize );
	  	dMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = memService.getBtbList(dMap);

		totalcnt  = memService.getBtbListCnt(dMap);
		double dSize = Util.attrParseInt(dMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		if(infoList.size()>0) {
			for(int i=0; i<infoList.size(); i++) {
				adminMap = (BrbMap<Object, Object>) infoList.get(i);
				adminMap.put("ROWNUM", (currentPage -1) *rowSize+i+1);
			}
		}

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * 회원 상세
	 * @param MemVo, HttpSession
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "mem/memDet")
	public ModelAndView memDet(HttpServletRequest request, HttpSession session){
		ModelAndView view = new ModelAndView();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		int memSq = Util.attrParseInt(dMap, "memSq");
		if(memSq != 0){
			view.addObject("mem", memService.getMemDet(memSq));
		}
		view.addObject("dMap", dMap);
		view.addObject("admEmail", session.getAttribute("ADM_EMAIL"));
		view.addObject("hpList", memService.getHpList().get("hpList"));
		view.setViewName("mem/memDet");
		return view;
	}

	/**
	 * 회원 수정
	 * @param MemVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "mem/updateMem")
	public void updateMem(@ModelAttribute MemVo bvo, HttpSession session, HttpServletRequest request){
		bvo.setUptAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		memService.updateMem(bvo);
		commonService.addAdminActLog(request);
	}

	/**
	 * 회원 비밀번호 변경
	 * @param MemVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "mem/updatePwd")
	public void updatePwd(@ModelAttribute MemVo bvo, HttpSession session, HttpServletRequest request) throws Exception {
		bvo.setUptAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		memService.updatePwd(bvo);
		commonService.addAdminActLog(request);
	}

	/**
	 * 회원 삭제(논리)
	 * @param MemVo
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "mem/deleteMem")
	public void deleteMem(@ModelAttribute MemVo bvo, HttpSession session, HttpServletRequest request){
		bvo.setDelAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		memService.deleteMem(bvo);
		commonService.addAdminActLog(request);
	}

	/**
	 * 회원 활동로그
	 * @param MemVo
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "mem/memLog")
	public ModelAndView memLog(@ModelAttribute MemVo mvo){
		ModelAndView view = new ModelAndView();
		view.addObject("memData", memService.getMemDet(mvo.getMemSq()));
        view.setViewName("mem/memLog");
        return view;
    }

	/**
	 * 회원 활동로그 조회
	 * @param HttpServletRequest
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "mem/searchLog")
	public ModelAndView searchLog(HttpServletRequest request){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap	= RequestParameterUtil.getParameterMap(request);
		List<BrbMap<Object, Object>> infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "LOG_USER_SQ" : bMap.getString("sidx");

		if("LOG_USER_SQ".equals(sidx)){
			sidx = "LOG_USER_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *10 );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List<BrbMap<Object, Object>>)memService.getLogList(bMap).get("logList");
		//리스트 카운트 조회
		totalcnt = (int)memService.getLogList(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
    }

	/**
	 * 보유제품 수정
	 * @param HttpServletRequest, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "mem/modProduct")
	public void modProduct(HttpServletRequest request, HttpSession session){
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		dMap.put("admSq", (String)session.getAttribute("ADM_SQ"));
		memService.modProduct(dMap);
	}

	/**
	 * 보유제품 삭제(초기화)
	 * @param HttpServletRequest, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "mem/delProduct")
	public void delProduct(HttpServletRequest request, HttpSession session){
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		memService.delProduct(dMap);
	}

	/**
	 * 보유제품 등록
	 * @param HttpServletRequest, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "mem/regProduct")
	public void regProduct(HttpServletRequest request, HttpSession session){
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		dMap.put("admSq", (String)session.getAttribute("ADM_SQ"));
		memService.regProduct(dMap);
	}

	/**
	 * 시/군/구 리스트 조회
	 * @param String
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="mem/getSggList")
	public ModelAndView getSggList(@RequestParam String code){
		ModelAndView view = new ModelAndView();
		view.addObject("sggList", memService.getSggList(code));
		return view;
	}

	/**
	 * 읍/면/동 리스트 조회
	 * @param String
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="mem/getUmdList")
	public ModelAndView getUmdList(@RequestParam String code){
		ModelAndView view = new ModelAndView();
		view.addObject("umdList", memService.getUmdList(code));
		return view;
	}

	/**
	 * 제품조회
	 * @param HttpServletRequest
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="mem/getProdList")
	public ModelAndView getProdList(HttpServletRequest request){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap = RequestParameterUtil.getParameterMap(request);
		List<BrbMap<Object, Object>> infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "PPRT_SQ" : bMap.getString("sidx");

		if("PPRT_SQ".equals(sidx)){
			sidx = "PPRT_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) * rowSize);
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List<BrbMap<Object, Object>>)memService.getProdList(bMap);
		//리스트 카운트 조회
		totalcnt = memService.getProdTotal(Integer.parseInt((String)bMap.get("memSq")));

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * 회원통계
	 * @param
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="mem/memStat")
	public ModelAndView memStat(){
		ModelAndView view = new ModelAndView();
		view.addObject("sdList", memService.getSdList());
		view.addObject("prodList", memService.getProdSearch());
		view.setViewName("mem/memStat");
		return view;
	}

	/**
	 * 제품별 회원통계
	 * @param MemVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="mem/getProdStat")
	public ModelAndView getProdStat(@ModelAttribute MemVo mvo, HttpSession session){
		ModelAndView view = new ModelAndView();
		ArrayList<Object> ar = new ArrayList<>();
		ArrayList<Object> ar2 = new ArrayList<>();
		String admClass = (String)session.getAttribute("ADM_CLASS");
		if(admClass.equals("3") || admClass.equals("4")){
			mvo.setBtbsSq(Integer.parseInt((String)session.getAttribute("BTBS_SQ")));
		}
		List<BrbMap<Object, Object>> list = memService.getProdStat(mvo);
		for(int i=0; i<list.size(); i++){
			ar.add(list.get(i).get("CP_NM"));
			ar2.add(list.get(i).get("CNT"));
		}
		view.addObject("cat", ar);
		view.addObject("data", ar2);
		return view;
	}

	/**
	 * 성별 회원통계
	 * @param MemVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="mem/getGenStat")
	public ModelAndView getGenStat(@ModelAttribute MemVo mvo, HttpSession session){
		ModelAndView view = new ModelAndView();
		String admClass = (String)session.getAttribute("ADM_CLASS");
		if(admClass.equals("3") || admClass.equals("4")){
			mvo.setBtbsSq(Integer.parseInt((String)session.getAttribute("BTBS_SQ")));
		}
		view.addObject("data", memService.getGenStat(mvo));
		return view;
	}

	/**
	 * 지역별 회원통계
	 * @param MemVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="mem/getLocStat")
	public ModelAndView getLocStat(@ModelAttribute MemVo mvo, HttpSession session){
		ModelAndView view = new ModelAndView();
		ArrayList<Object> ar = new ArrayList<>();
		ArrayList<Object> ar2 = new ArrayList<>();
		String admClass = (String)session.getAttribute("ADM_CLASS");
		if(admClass.equals("3") || admClass.equals("4")){
			mvo.setBtbsSq(Integer.parseInt((String)session.getAttribute("BTBS_SQ")));
		}
		List<BrbMap<Object, Object>> list = memService.getLocStat(mvo);
		for(int i=0; i<list.size(); i++){
			ar.add(list.get(i).get("CODENAME"));
			ar2.add(list.get(i).get("CNT"));
		}
		view.addObject("cat", ar);
		view.addObject("data", ar2);
		return view;
	}

	/**
	 * 연령별 회원통계
	 * @param MemVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="mem/getAgeStat")
	public ModelAndView getAgeStat(@ModelAttribute MemVo mvo, HttpSession session){
		ModelAndView view = new ModelAndView();
		ArrayList<Object> ar = new ArrayList<>();
		ArrayList<Object> ar2 = new ArrayList<>();
		String admClass = (String)session.getAttribute("ADM_CLASS");
		if(admClass.equals("3") || admClass.equals("4")){
			mvo.setBtbsSq(Integer.parseInt((String)session.getAttribute("BTBS_SQ")));
		}
		List<BrbMap<Object, Object>> list = memService.getAgeStat(mvo);
		int count = 0;
		boolean ch = false;
		for(int k=0; k<list.size(); k++){
			if((double)list.get(k).get("AGE") >= 70){
				count += (long)list.get(k).get("CNT");
			}
		}
		for(int i=0; i<list.size(); i++){
			Double d = (double)list.get(i).get("AGE");
			int n = d.intValue();
			if(n < 70){
				if(n == 60){
					ch = true;
					ar.add(String.valueOf(n)+"대 이상");
					ar2.add((long)list.get(i).get("CNT")+count);
				}else if(n <= 10){
					ar.add(String.valueOf(n)+"대 이하");
					ar2.add(list.get(i).get("CNT"));
				}else{
					ar.add(String.valueOf(n)+"대");
					ar2.add(list.get(i).get("CNT"));
				}
			}
		}
		if(ch == false && count != 0){
			ar.add("60대 이상");
			ar2.add(count);
		}
		view.addObject("cat", ar);
		view.addObject("data", ar2);
		return view;
	}

	@RequestMapping(value="mem/memData")
	public ModelAndView memData(){
		ModelAndView view = new ModelAndView();
		view.setViewName("mem/memData");
		return view;
	}

	@ResponseBody
	@SuppressWarnings("unchecked")
	@RequestMapping(value="mem/getTimeData")
	public ModelAndView getTimeData(){
		ModelAndView view = new ModelAndView();
		JSONObject jsonObject;
		JSONArray jsonArray = new JSONArray();
		ArrayList<Object> ar2;
		List<DataVo> list2 = memService.getTimeData();
		for(int i=0; i<list2.size(); i++){
			jsonObject = new JSONObject();
			ar2 = new ArrayList<>();
			ar2.add(list2.get(i).getSO2());
			ar2.add(list2.get(i).getCO2());
			ar2.add(list2.get(i).getO3());
			ar2.add(list2.get(i).getNO2());
			/*ar2.add(list2.get(i).getPM10());
			ar2.add(list2.get(i).getPM25());*/
			jsonObject.put("data", ar2);
			jsonObject.put("name", list2.get(i).getDATATIME());
			jsonArray.add(jsonObject);
		}
		view.addObject("timeData", jsonArray);
		return view;
	}
}
