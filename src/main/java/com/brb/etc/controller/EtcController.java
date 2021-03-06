package com.brb.etc.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.brbcom.common.util.ApplicationContextHolder;
import com.brb.brbcom.common.util.RequestParameterUtil;
import com.brb.brbcom.common.util.Util;
import com.brb.brbcom.foundation.property.PropertyService;
import com.brb.etc.service.EtcService;

/**
 *
 * @author back
 *
 */
@Controller
public class EtcController {

	protected PropertyService propertyService;
	String smsId ="";
	String smsPw = "";
	String adminPhone = "";
	String smsUrl = "";
	String uploadpath = "";
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EtcController.class);


	@Autowired
	EtcService etcService;


	public EtcController(){
		propertyService = (PropertyService)ApplicationContextHolder.getContext().getBean("propertiesService");
		this.smsId     		= propertyService.getString("SMS_ID");
		this.smsPw     		= propertyService.getString("SMS_PW");
		this.smsUrl	 		= propertyService.getString("SMS_URL");
		this.adminPhone 	= propertyService.getString("SMS_ADMIN");
		this.uploadpath		= propertyService.getString("IMAGE_UPLOAD_PATH");
	}
	/**
	 * 팝업목록
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "etc/popList" )
	public ModelAndView smslist(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("pMap", pMap);
		view.setViewName("etc/popList") ;
		return view;
	}

	/**
	 * 팝업신규
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "etc/popNew" )
	public ModelAndView popNew(){
		ModelAndView view = new ModelAndView();
		view.setViewName("etc/popNew") ;
		return view;
	}



	/**
	 * 팝업목록 검색
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "etc/popListSearch" , method = RequestMethod.POST)
	public ModelAndView smsListSearch(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		List infoList = null;
		int rowSize = Util.attrParseInt(dMap, "rows");
		int currentPage = Util.attrParseInt(dMap, "page");
		String sidx = dMap.getString("sidx") == "" ? "POP_SQ" : dMap.getString("sidx");

		if("POP_SQ".equals(sidx)){
			sidx = "POP_SQ";
		}
		String sord = dMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    dMap.put("orderBy", orderbyColum);
	  	dMap.put("R_PAGE", (currentPage -1) *10 );
	  	dMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = etcService.getPopList(dMap);
		//리스트 카운트 조회
		infoMap = etcService.getPopListTotal(dMap);

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
	 * 팝업등록
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "etc/popInsert" , method = RequestMethod.POST)
	public ModelAndView popInsert(HttpServletRequest request, HttpServletResponse response
			, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		HttpSession session = request.getSession();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> inMap = new BrbMap<>();
		String fileName ="";
		String savePath = "";
		int popW = 0;
		int popH = 0;
		try{
			if("01".equals(dMap.getString("popType"))){
				//이미지 파일 업로드
				MultipartRequest multi = (MultipartRequest) request;
		        MultipartFile file = multi.getFile("imageFile");

		        if (null != file && !file.isEmpty()) {
		        	popW = dMap.getInt("popwidth");
		        	popH = dMap.getInt("popheight");
			        String genId = UUID.randomUUID().toString();
					fileName = file.getOriginalFilename();
					byte[] bytes = file.getBytes();
					String saveFileName = genId + "." + getExtension(fileName);
					savePath =  "/pop/"+saveFileName;

		        	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(uploadpath +savePath));
					bos.write(bytes);
					bos.flush();
					bos.close();
		        }
			}

			inMap.put("R_POP_NM", dMap.getString("popNm"));
			inMap.put("R_POP_CONT",  dMap.getString("popContData"));
			inMap.put("R_BEGIN_DTTM",  dMap.getString("monthfrom")+" " + dMap.getString("monthfromTime"));
			inMap.put("R_END_DTTM",  dMap.getString("monthto")+" " + dMap.getString("monthtoTime"));
			inMap.put("R_LNK_URL",  dMap.getString("linkurl"));
			inMap.put("R_POP_IMG_FILE_NM",  fileName);
			inMap.put("R_POP_IMG_PATH",  "/upload/"+savePath);
			inMap.put("R_POP_TP",  dMap.getString("popType"));
			inMap.put("R_POP_WIDTH", popW);
			inMap.put("R_POP_HEIGHT",  popH);
			inMap.put("R_STAT",  dMap.getString("stat"));
			inMap.put("R_REG_ADM_SQ", session.getAttribute("ADM_SQ").toString() );
			inMap.put("R_BTBS_SQ", session.getAttribute("BTBS_SQ").toString());

			//정보 저장
			etcService.addPop(inMap);
			view.addObject("resultCode","001");

		}catch(Exception e){
			view.addObject("resultCode","002");
			e.printStackTrace();
		}

		return view;
	}


	/**
	 * 팝업수정
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "etc/popMod" , method = RequestMethod.POST)
	public ModelAndView popMod(HttpServletRequest request, HttpServletResponse response
			, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		HttpSession session = request.getSession();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> inMap = new BrbMap<>();
		String fileName ="";
		String savePath = "";
		int popW = 0;
		int popH = 0;
		String linkUrl="";
		String popContent = "";
		try{
			if("01".equals(dMap.getString("popType"))){
				//이미지 파일 업로드
				MultipartRequest multi = (MultipartRequest) request;
		        MultipartFile file = multi.getFile("imageFile");

				popW = dMap.getInt("popwidth");
	        	popH = dMap.getInt("popheight");
		        if (null != file && !file.isEmpty()) {
		        	if(dMap.getString("old_file_path") != "" && Util.fileIsLive(uploadpath+dMap.getString("old_file_path").replaceAll("/upload/", ""))){
			        	Util.fileDelete(uploadpath+dMap.getString("old_file_path").replaceAll("/upload/", ""));
		        	}

			        String genId = UUID.randomUUID().toString();
					fileName = file.getOriginalFilename();

					byte[] bytes = file.getBytes();
					String saveFileName = genId + "." + getExtension(fileName);
					savePath =  "/pop/"+saveFileName;

		        	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(uploadpath +savePath));
					bos.write(bytes);
					bos.flush();
					bos.close();

		        }  else {
		        	fileName = dMap.getString("old_file_nm");
		        	savePath = dMap.getString("old_file_path");
		        }
		        linkUrl = dMap.getString("linkurl");
		        popContent = "";
			}  else {
				if(dMap.getString("old_file_path") != "" && Util.fileIsLive(uploadpath+dMap.getString("old_file_path").replaceAll("/upload/", ""))){
		        	Util.fileDelete(uploadpath+dMap.getString("old_file_path").replaceAll("/upload/", ""));
	        	}
				popContent =  dMap.getString("popContData");
			}
			inMap.put("R_POP_SQ", dMap.getString("R_POP_SQ"));
			inMap.put("R_POP_NM", dMap.getString("popNm"));
			inMap.put("R_POP_CONT", popContent);
			inMap.put("R_BEGIN_DTTM",  dMap.getString("monthfrom")+" " + dMap.getString("monthfromTime"));
			inMap.put("R_END_DTTM",  dMap.getString("monthto")+" " + dMap.getString("monthtoTime"));
			inMap.put("R_POP_IMG_FILE_NM",  fileName);
			inMap.put("R_POP_IMG_PATH",  "/upload/"+savePath);
			inMap.put("R_POP_TP",  dMap.getString("popType"));
			inMap.put("R_LNK_URL",  linkUrl);
		    inMap.put("R_POP_WIDTH", popW);
			inMap.put("R_POP_HEIGHT",  popH);
			inMap.put("R_STAT",  dMap.getString("stat"));
			inMap.put("R_UPT_ADM_SQ", session.getAttribute("ADM_SQ").toString() );
			inMap.put("R_BTBS_SQ", session.getAttribute("BTBS_SQ").toString());

			//정보 저장
			etcService.modPop(inMap);
			view.addObject("resultCode","001");

		}catch(Exception e){
			view.addObject("resultCode","002");
			e.printStackTrace();
		}

		return view;
	}

	/**
	 * 팝업삭제
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "etc/popDel" , method = RequestMethod.POST)
	public ModelAndView popDel(HttpServletRequest request, HttpServletResponse response
			, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		HttpSession session = request.getSession();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		try{
			dMap.put("DEL_ADM_EMAIL", session.getAttribute("ADM_EMAIL").toString() );
			//정보 저장
			etcService.delPop(dMap);
			view.addObject("resultCode","001");

		}catch(Exception e){
			view.addObject("resultCode","002");
			e.printStackTrace();
		}

		return view;
	}

	/**
	 * 파일이름으로부터 확장자를 반환하는 메서드
	 * 파일이름에 확장자 구분을 위한 . 문자가 없거나. 가장 끝에 있는 경우는 빈문자열 ""을 리턴
	 */
	private String getExtension(String fileName) {
		int dotPosition = fileName.lastIndexOf('.');

		if (-1 != dotPosition && fileName.length() - 1 > dotPosition) {
			return fileName.substring(dotPosition + 1);
		} else {
			return "";
		}
	}

	/**
	 * 팝업 상세
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("pop/getPop")
	public ModelAndView testInfo(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		mav.addObject("R_POP_SQ", dMap.getString("R_POP_SQ"));
		Map infoMap = new HashMap<String, String>();
		try{
			infoMap = etcService.getPopDet(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("dMap", dMap);
		mav.addObject("POPDET", infoMap);
		mav.setViewName("etc/popDet") ;
		return mav;
	}



	/**
	 * 환결설정
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "etc/etcInit" )
	public ModelAndView getEtcInit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap)throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
		try{
			infoMap = etcService.getEtcInit(dMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("ETC", infoMap);
		mav.setViewName("etc/etcInit") ;
		return mav;
	}

	/**
	 * 환경설정 수정
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "etc/modEtcInit" , method = RequestMethod.POST)
	public ModelAndView modEtcInit(HttpServletRequest request, HttpServletResponse response
			, ModelMap modelMap)throws Exception{
		ModelAndView view = new ModelAndView("jsonView");
		HttpSession session = request.getSession();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		try{
			dMap.put("R_UPT_ADM_SQ", session.getAttribute("ADM_SQ").toString() );
			//정보 저장
			etcService.modEtcInit(dMap);
			view.addObject("resultCode","001");

		}catch(Exception e){
			view.addObject("resultCode","002");
			e.printStackTrace();
		}

		return view;
	}

}
