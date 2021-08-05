package com.brb.board.controller;

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

import com.brb.board.service.BoardService;
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
public class BoardController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);


	@Autowired
	BoardService boardService;

	@Autowired
	CommonService commonService;


	/**
	 * 공지사항 목록 페이지 호출
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("board/noticeList")
	public ModelAndView noticeList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		mav.addObject("pMap", pMap);
		mav.setViewName("board/noticeList") ;
		return mav;
	}


	/**
	 * 공지사항 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "board/noticeListProc" , method = RequestMethod.POST)
	public ModelAndView noticeListProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map<String, String> infoMap = new HashMap<String, String>();
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
	  	dMap.put("R_PAGE", (currentPage -1) *10 );
	  	dMap.put("R_ROW", rowSize);
		dMap.put("start_dt", dMap.getString("start_dt").replaceAll("-", ""));
		dMap.put("end_dt", dMap.getString("end_dt").replaceAll("-", ""));
		int totalcnt = 0;

		//리스트 조회
		infoList = boardService.getNoticeList(dMap);

		//리스트 카운트 조회
		infoMap = boardService.getNoticeListCnt(dMap);


		if(null != infoMap ){
			totalcnt  = Integer.parseInt(String.valueOf(infoMap.get("CNT")));
		}
		double dSize = Util.attrParseInt(dMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		int begin_index = (currentPage-1)* rowSize;
		int virtualNumber = totalcnt - begin_index;
		if(infoList.size()>0) {
			for(int i=0; i<infoList.size(); i++) {
				adminMap = (BrbMap<Object, Object>) infoList.get(i);
				adminMap.put("ROWNUM", virtualNumber--);
			}
		}


		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;

	}

	/**
	 * 공지사항 신규생성 페이지 호출
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("board/noticeAdd")
	public ModelAndView noticeAdd() {
		ModelAndView mav = new ModelAndView();

		List <BrbMap> statList = null;
		try{
			statList = boardService.getStatList();
		} catch(Exception e){
			e.printStackTrace();
		}

		mav.setViewName("board/noticeAdd") ;
		mav.addObject("statList", statList);
		return mav;
	}

//	/**
//	 * 첨부파일 체크
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "board/fileCheck", method = RequestMethod.POST)
//	public ModelAndView fileCheck(HttpServletRequest request,
//			HttpServletResponse response,
//			ModelMap modelMap, MultipartHttpServletRequest mrequest)throws Exception{
//
//		System.out.println("&&&&&&&&&&&&&&&&&fileCheck");
//
//		ModelAndView view = new ModelAndView("jsonView");
//	    try{
//	    	BrbMap dMap = RequestParameterUtil.getParameterMap(request);
//
//			String message ="";
//		    int cnt =0;
//
//			//파일업로드 및 파일정보 저장
//	    	List<MultipartFile> list = mrequest.getFiles("imageFile");
//	    	if(list.size()>0) {
//	    		for(int i=0; i<list.size(); i++) {
//	    			MultipartFile file = list.get(i);
//	    			String fileName = file.getOriginalFilename();
//	    			if(null != fileName && !fileName.equals("")) {
//	    				cnt++;
//
//		    			long size = file.getSize();
//		    			int maxSize = 1048576; //1MB
//		    			if(size > maxSize){
//		    				message="파일명: "+fileName +"이 파일 최대용량 1MB를 초과하였습니다.";
//		    				break;
//		    			}
//	    			}
//
//	    		}
//	    	}
//	    	if(cnt>5) {
//	    		message="파일을 최대 5개 첨부 할 수 있습니다.";
//	    	}
//	    	view.addObject("message", message);
//
//	    } catch (Exception e){
//	      e.printStackTrace();
//	    }
//
//	    return view;
//	}

	/**
	 * 공지사항 신규생성
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "board/noticeAddProc", method = RequestMethod.POST)
	public ModelAndView noticeAddProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap)throws Exception{

		ModelAndView view = new ModelAndView("jsonView");
	    try{
	    	BrbMap dMap = RequestParameterUtil.getParameterMap(request);

	    	HttpSession session = request.getSession(false);
			String bd_user_email = session.getAttribute("ADM_EMAIL").toString();
			String bd_user_nm = session.getAttribute("ADM_NM").toString();
			String bd_ip = session.getAttribute("LOCALIP").toString();
			String bd_os = session.getAttribute("OSNAME").toString();
			String reg_adm_sq = session.getAttribute("ADM_SQ").toString();
			String btbs_sq = session.getAttribute("BTBS_SQ").toString();

			dMap.put("bd_user_email", bd_user_email);
			dMap.put("bd_user_nm", bd_user_nm);
			dMap.put("bd_ip", bd_ip);
			dMap.put("bd_os", bd_os);
			dMap.put("reg_adm_sq", reg_adm_sq);
			dMap.put("btbs_sq", btbs_sq);


			boardService.addNotice(dMap);
			//action log
	        commonService.addAdminActLog(request);

	    } catch (Exception e){
	      e.printStackTrace();
	    }

	    return view;
	}

//	/**
//	 * 공지사항 신규생성
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "board/noticeAddProc", method = RequestMethod.POST)
//	public ModelAndView noticeAddProc(HttpServletRequest request,
//			HttpServletResponse response,
//			ModelMap modelMap, MultipartHttpServletRequest mrequest)throws Exception{
//
//		ModelAndView view = new ModelAndView("jsonView");
//	    try{
//	    	BrbMap dMap = RequestParameterUtil.getParameterMap(request);
//	   // 	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/views/file/");
//	    	String path = "C:\\work\\";
//
//	    	HttpSession session = request.getSession(false);
//			String bd_user_email = session.getAttribute("ADM_EMAIL").toString();
//			String bd_user_nm = session.getAttribute("ADM_NM").toString();
//			String bd_user_hp = session.getAttribute("ADM_HP").toString();
//			String bd_ip = session.getAttribute("LOCALIP").toString();
//			String bd_os = session.getAttribute("OSNAME").toString();
//			String reg_adm_sq = session.getAttribute("ADM_SQ").toString();
//			String btbs_sq = session.getAttribute("BTBS_SQ").toString();
//			String btbs_nm = session.getAttribute("BTBS_NM").toString();
//
//			dMap.put("bd_user_email", bd_user_email);
//			dMap.put("bd_user_nm", bd_user_nm);
//			dMap.put("bd_user_hp", bd_user_hp);
//			dMap.put("bd_ip", bd_ip);
//			dMap.put("bd_os", bd_os);
//			dMap.put("reg_adm_sq", reg_adm_sq);
//			dMap.put("btbs_sq", btbs_sq);
//			dMap.put("btbs_nm", btbs_nm);
//
//			int bd_sq = 0;
//			bd_sq = boardService.addNotice(dMap);
//
//			//파일업로드 및 파일정보 저장
//	    	List<MultipartFile> list = mrequest.getFiles("imageFile");
//	    	if(list.size()>0) {
//	    		for(int i=0; i<list.size(); i++) {
//	    			MultipartFile file = list.get(i);
//	    			String fileName = file.getOriginalFilename();
//	    			if(null != fileName && !fileName.equals("")) {
//		    			long size = file.getSize();
//
//		    			byte[] bytes = file.getBytes();
//		    	//		int fileSize
//		    			String genId = UUID.randomUUID().toString();
//		    			String saveFileName = genId + "." + getExtension(fileName);
//		    	//		String savePath = path +"\\" +saveFileName;
//		    			String savePath = path +saveFileName;
//
//		    			String mkFolder =path;
//		    			//디렉토리 생성
//		    			File desti = new File(mkFolder);
//		    		    //해당 디렉토리의 존재여부를 확인
//		    			if(!desti.exists()){
//			    			//없다면 생성
//			    			desti.mkdirs();
//		    			}
//
//		    	    	if (!"".equals(getExtension(fileName))){
//				    		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath));
//				    		bos.write(bytes);
//				    		bos.flush();
//				    		bos.close();
//		    	    	}
//
//		    	    	BrbMap<Object, Object> fileMap = new BrbMap<Object, Object>();
//
//		    	    	fileMap.put("file_nm", fileName);
//		    	    	fileMap.put("file_new_nm", saveFileName);
//		    	    	fileMap.put("file_path", savePath);
//		    	    	fileMap.put("file_sz", size);
//		    	    	fileMap.put("reg_user_sq", reg_adm_sq);
//		    	    	fileMap.put("bd_sq", bd_sq);
//
//		    	    	int cnt1=0;
//		    	    	cnt1 = boardService.addFileInfo(fileMap);
//	    			}
//	    		}
//	    	}
//
//	    } catch (Exception e){
//	      e.printStackTrace();
//	    }
//
//	    return view;
//	}



	/**
	 * 공지사항 상세보기 화면
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "board/noticeView" , method = RequestMethod.POST)
	public ModelAndView noticeView(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("board/noticeView");
		BrbMap<Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> rMap = null;
		List <BrbMap> statList = null;
		try{
			rMap = boardService.getNotice(dMap);
		//	cnt = boardService.modReadCnt(dMap);
			statList = boardService.getStatList();
		} catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("dMap", dMap);
		view.addObject("rMap", rMap);
		view.addObject("statList", statList);

		return view;
	}

	/**
	 * 공지사항 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/noticeViewProc" , method = RequestMethod.POST)
	public ModelAndView noticeViewProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		HttpSession session = request.getSession(false);
		String upt_adm_sq = session.getAttribute("ADM_SQ").toString();
		dMap.put("upt_adm_sq", upt_adm_sq); //수정 관리자고유번호

		int cnt = 0;
		try{
			cnt = boardService.modNotice(dMap);
			//action log
	        commonService.addAdminActLog(request);
		} catch(Exception e){
			e.printStackTrace();
		}

		view.addObject("cnt", cnt);

		return view;
	}

//	/**
//	 * 공지사항 수정
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "board/noticeViewProc" , method = RequestMethod.POST)
//	public ModelAndView noticeViewProc(HttpServletRequest request,
//			HttpServletResponse response,
//			ModelMap modelMap, MultipartHttpServletRequest mrequest) throws Exception {
//
//		logger.debug("############   noticeViewProc  ################");
//		ModelAndView view = new ModelAndView("jsonView");
//		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
//		String path = "C:\\work\\";
//
//		HttpSession session = request.getSession(false);
//		String upt_adm_sq = session.getAttribute("ADM_SQ").toString();
//		dMap.put("upt_adm_sq", upt_adm_sq); //수정 관리자고유번호
//
//		int cnt = 0;
//		try{
//			cnt = boardService.modNotice(dMap);
//
//			//파일업로드 및 파일정보 저장
//	    	List<MultipartFile> list = mrequest.getFiles("imageFile");
//	    	if(list.size()>0) {
//	    		for(int i=0; i<list.size(); i++) {
//	    			MultipartFile file = list.get(i);
//	    			String fileName = file.getOriginalFilename();
//	    			if(null != fileName && !fileName.equals("")) {
//		    			long size = file.getSize();
//
//		    			byte[] bytes = file.getBytes();
//		    	//		int fileSize
//		    			String genId = UUID.randomUUID().toString();
//		    			String saveFileName = genId + "." + getExtension(fileName);
//		    	//		String savePath = path +"\\" +saveFileName;
//		    			String savePath = path +saveFileName;
//
//		    			String mkFolder =path;
//		    			//디렉토리 생성
//		    			File desti = new File(mkFolder);
//		    		    //해당 디렉토리의 존재여부를 확인
//		    			if(!desti.exists()){
//			    			//없다면 생성
//			    			desti.mkdirs();
//		    			}
//
//		    	    	if (!"".equals(getExtension(fileName))){
//				    		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath));
//				    		bos.write(bytes);
//				    		bos.flush();
//				    		bos.close();
//		    	    	}
//
//		    	    	BrbMap<Object, Object> fileMap = new BrbMap<Object, Object>();
//
//		    	    	fileMap.put("file_nm", fileName);
//		    	    	fileMap.put("file_new_nm", saveFileName);
//		    	    	fileMap.put("file_path", savePath);
//		    	    	fileMap.put("file_sz", size);
//		    	    	fileMap.put("reg_user_sq", upt_adm_sq);
//		    	    	fileMap.put("bd_sq", dMap.getString("bd_sq"));
//
//		    	    	int cnt1=0;
//		    	    	cnt1 = boardService.addFileInfo(fileMap);
//	    			}
//	    		}
//	    	}
//
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//
//		view.addObject("cnt", cnt);
//
//		return view;
//	}


	/**
	 * 공지사항 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/noticeDelProc" , method = RequestMethod.POST)
	public ModelAndView noticeDelProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		HttpSession session = request.getSession(false);
		String del_user_sq = session.getAttribute("ADM_SQ").toString();
		String del_adm_email = session.getAttribute("ADM_EMAIL").toString();
		dMap.put("del_user_sq", del_user_sq); //삭제 관리자고유번호
		dMap.put("del_adm_email", del_adm_email);

		int cnt = 0;
		try{
			cnt = boardService.delNotice(dMap);
			//action log
	        commonService.addAdminActLog(request);
		} catch(Exception e){
			e.printStackTrace();
		}

		view.addObject("cnt", cnt);

		return view;
	}

	/**
	 * Q&A 목록 페이지 호출
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("board/qnaList")
	public ModelAndView qnaList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<BrbMap> cateList = null;
		try{
			cateList = boardService.getCateList();
		} catch(Exception e){
			e.printStackTrace();
		}
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		mav.addObject("pMap", pMap);
		mav.setViewName("board/qnaList") ;
		mav.addObject("cateList", cateList);

		return mav;
	}


	/**
	 * Q&A 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "board/qnaListProc" , method = RequestMethod.POST)
	public ModelAndView qnaListProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map infoMap = new HashMap<String, String>();
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
	  	dMap.put("R_PAGE", (currentPage -1) *10 );
	  	dMap.put("R_ROW", rowSize);
		dMap.put("start_dt", dMap.getString("start_dt").replaceAll("-", ""));
		dMap.put("end_dt", dMap.getString("end_dt").replaceAll("-", ""));
		int totalcnt = 0;

		//리스트 조회
		infoList = boardService.getQnaList(dMap);

		//리스트 카운트 조회
		infoMap = boardService.getQnaListCnt(dMap);

		if(null != infoMap ){
			totalcnt  = Integer.parseInt(String.valueOf(infoMap.get("CNT")));
		}
		double dSize = Util.attrParseInt(dMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		int begin_index = (currentPage-1)* rowSize;
		int virtualNumber = totalcnt - begin_index;
		if(infoList.size()>0) {
			for(int i=0; i<infoList.size(); i++) {
				adminMap = (BrbMap<Object, Object>) infoList.get(i);
				adminMap.put("ROWNUM", virtualNumber--);
			}
		}


		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * Q&A 신규생성 페이지 호출
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("board/qnaAdd")
	public ModelAndView qnaAdd() {

		ModelAndView mav = new ModelAndView();
		List<BrbMap> cateList = null;
		List<BrbMap> statList = null;

		try{
			cateList = boardService.getCateList();
			statList = boardService.getStatList();
		} catch(Exception e){
			e.printStackTrace();
		}
		mav.setViewName("board/qnaAdd") ;
		mav.addObject("cateList", cateList);
		mav.addObject("statList", statList);
		return mav;
	}

	/**
	 * Q&A 신규생성
	 * @param param
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "board/qnaAddProc" , method = RequestMethod.POST)
	public ModelAndView qnaAddProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		HttpSession session = request.getSession(false);
		String bd_user_email = session.getAttribute("ADM_EMAIL").toString();
		String bd_user_nm = session.getAttribute("ADM_NM").toString();
		String bd_user_hp = session.getAttribute("ADM_HP").toString();
		String bd_ip = session.getAttribute("LOCALIP").toString();
		String bd_os = session.getAttribute("OSNAME").toString();
		String reg_adm_sq = session.getAttribute("ADM_SQ").toString();
		String btbs_sq = session.getAttribute("BTBS_SQ").toString();
		String btbs_nm = session.getAttribute("BTBS_NM").toString();

		dMap.put("bd_user_email", bd_user_email);
		dMap.put("bd_user_nm", bd_user_nm);
		dMap.put("bd_user_hp", bd_user_hp);
		dMap.put("bd_ip", bd_ip);
		dMap.put("bd_os", bd_os);
		dMap.put("reg_adm_sq", reg_adm_sq);
		dMap.put("btbs_sq", btbs_sq);
		dMap.put("btbs_nm", btbs_nm);

		int cnt = 0;
		try{
			cnt = boardService.addQna(dMap);
			//action log
	        commonService.addAdminActLog(request);
		} catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("cnt", cnt);

		return view;

	}*/

	/**
	 * Q&A 상세보기 화면
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/qnaView" , method = RequestMethod.POST)
	public ModelAndView qnaView(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("board/qnaView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> rMap = null;
		try{
			rMap = boardService.getQna(dMap);
		//	cnt = boardService.modReadCnt(dMap);
		} catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("dMap", dMap);
		view.addObject("rMap", rMap);

		return view;
	}

	/**
	 * Q&A 답변달기
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/qnaViewProc" , method = RequestMethod.POST)
	public ModelAndView qnaViewProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		HttpSession session = request.getSession(false);
		String bd_ans_reg_adm_sq = session.getAttribute("ADM_SQ").toString();

		dMap.put("bd_ans_reg_adm_sq", bd_ans_reg_adm_sq);

		int cnt = 0;
		try{
			cnt = boardService.modQna(dMap);
			//action log
	        commonService.addAdminActLog(request);
		} catch(Exception e){
			e.printStackTrace();
		}

		view.addObject("cnt", cnt);

		return view;
	}


	/**
	 * Q&A 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/qnaDelProc" , method = RequestMethod.POST)
	public ModelAndView qnaDelProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		HttpSession session = request.getSession(false);
		String del_user_sq = session.getAttribute("ADM_SQ").toString();
		String del_adm_email = session.getAttribute("ADM_EMAIL").toString();
		dMap.put("del_user_sq", del_user_sq); //삭제 관리자고유번호
		dMap.put("del_adm_email", del_adm_email);

		int cnt = 0;
		try{
			cnt = boardService.delQna(dMap);
			//action log
	        commonService.addAdminActLog(request);
		} catch(Exception e){
			e.printStackTrace();
		}

		view.addObject("cnt", cnt);

		return view;
	}

	/**
	 * FAQ 목록 페이지 호출
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("board/faqList")
	public ModelAndView faqList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<BrbMap> cateList = null;
		try{
			cateList = boardService.getCateList();
		} catch(Exception e){
			e.printStackTrace();
		}
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		mav.addObject("pMap", pMap);
		mav.setViewName("board/faqList") ;
		mav.addObject("cateList", cateList);
		return mav;
	}


	/**
	 * FAQ 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "board/faqListProc" , method = RequestMethod.POST)
	public ModelAndView faqListProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map<String, String> infoMap = new HashMap<String, String>();
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
	  	dMap.put("R_PAGE", (currentPage -1) *10 );
	  	dMap.put("R_ROW", rowSize);
		dMap.put("start_dt", dMap.getString("start_dt").replaceAll("-", ""));
		dMap.put("end_dt", dMap.getString("end_dt").replaceAll("-", ""));
		int totalcnt = 0;

		//리스트 조회
		infoList = boardService.getFaqList(dMap);

		//리스트 카운트 조회
		infoMap = boardService.getFaqListCnt(dMap);

		if(null != infoMap ){
			totalcnt  = Integer.parseInt(String.valueOf(infoMap.get("CNT")));
		}
		double dSize = Util.attrParseInt(dMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		int begin_index = (currentPage-1)* rowSize;
		int virtualNumber = totalcnt - begin_index;
		if(infoList.size()>0) {
			for(int i=0; i<infoList.size(); i++) {
				adminMap = (BrbMap<Object, Object>) infoList.get(i);
				adminMap.put("ROWNUM", virtualNumber--);
			}
		}


		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;

	}

	/**
	 * FAQ 신규생성 페이지 호출
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("board/faqAdd")
	public ModelAndView faqAdd() {
		ModelAndView mav = new ModelAndView();

		List<BrbMap> cateList = null;
		List<BrbMap> statList = null;

		try{
			cateList = boardService.getCateList();
			statList = boardService.getStatList();
		} catch(Exception e){
			e.printStackTrace();
		}

		mav.setViewName("board/faqAdd") ;
		mav.addObject("cateList", cateList);
		mav.addObject("statList", statList);
		return mav;
	}

	/**
	 * FAQ 신규생성
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/faqAddProc" , method = RequestMethod.POST)
	public ModelAndView faqAddProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");

		try{
			BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

			HttpSession session = request.getSession(false);
			String bd_user_email = session.getAttribute("ADM_EMAIL").toString();
			String bd_user_nm = session.getAttribute("ADM_NM").toString();
			String bd_user_hp = session.getAttribute("ADM_HP").toString();
			String bd_ip = session.getAttribute("LOCALIP").toString();
			String bd_os = session.getAttribute("OSNAME").toString();
			String reg_adm_sq = session.getAttribute("ADM_SQ").toString();
			String btbs_sq = session.getAttribute("BTBS_SQ").toString();
			String btbs_nm = session.getAttribute("BTBS_NM").toString();

			dMap.put("bd_user_email", bd_user_email);
			dMap.put("bd_user_nm", bd_user_nm);
			dMap.put("bd_user_hp", bd_user_hp);
			dMap.put("bd_ip", bd_ip);
			dMap.put("bd_os", bd_os);
			dMap.put("reg_adm_sq", reg_adm_sq);
			dMap.put("btbs_sq", btbs_sq);
			dMap.put("btbs_nm", btbs_nm);

			boardService.addFaq(dMap);
			//action log
	        commonService.addAdminActLog(request);


		} catch(Exception e){
			e.printStackTrace();
		}

		return view;

	}

	/**
	 * FAQ 상세보기 화면
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "board/faqView" , method = RequestMethod.POST)
	public ModelAndView faqView(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("board/faqView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> rMap = null;
		List<BrbMap> cateList = null;
		List<BrbMap> statList = null;
		try{
			rMap = boardService.getFaq(dMap);
			cateList = boardService.getCateList();
			statList = boardService.getStatList();
		//	cnt = boardService.modReadCnt(dMap);
		} catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("dMap", dMap);
		view.addObject("rMap", rMap);
		view.addObject("cateList", cateList);
		view.addObject("statList", statList);

		return view;
	}

	/**
	 * FAQ 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/faqViewProc" , method = RequestMethod.POST)
	public ModelAndView faqViewProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		HttpSession session = request.getSession(false);
		String upt_adm_sq = session.getAttribute("ADM_SQ").toString();
		dMap.put("upt_adm_sq", upt_adm_sq); //수정 관리자고유번호

		int cnt = 0;
		try{
			cnt = boardService.modFaq(dMap);
			//action log
	        commonService.addAdminActLog(request);
		} catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("cnt", cnt);

		return view;
	}


	/**
	 * FAQ 삭제
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/faqDelProc" , method = RequestMethod.POST)
	public ModelAndView faqDelProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		HttpSession session = request.getSession(false);
		String del_user_sq = session.getAttribute("ADM_SQ").toString();
		String del_adm_email = session.getAttribute("ADM_EMAIL").toString();
		dMap.put("del_user_sq", del_user_sq); //삭제 관리자고유번호
		dMap.put("del_adm_email", del_adm_email);
		dMap.put("bd_stat", "03");

		int cnt = 0;
		try{
			cnt = boardService.delFaq(dMap);
			//action log
	        commonService.addAdminActLog(request);
		} catch(Exception e){
			e.printStackTrace();
		}

		view.addObject("cnt", cnt);

		return view;
	}

//	/**
//	 * 첨부파일 확장자 가져오기
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	private String getExtension(String fileName){
//	    int dotPosition = fileName.lastIndexOf('.');
//	    if ((-1 != dotPosition) && (fileName.length() - 1 > dotPosition)) {
//	      return fileName.substring(dotPosition + 1);
//	    }
//	    return "";
//	}
//
//	/**
//	 * 첨부파일 다운로드
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//    @RequestMapping(value = "board/fileDownload" , method = RequestMethod.POST)
//	public void fileDownload(HttpServletRequest request,
//			HttpServletResponse response,
//			ModelMap modelMap) throws Exception {
//
//        BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
//
//        String fileNewName     = dMap.getString("fileNewName");
//        String fileName     = dMap.getString("fileName");
//        String path = "C:\\work\\";
//        String filePath = path +fileNewName;
//        File file = new File(filePath);
//
//        response.setContentType("application/download; charset=utf-8");
//        response.setContentLength((int) file.length());
//
//        // 브라우저, 운영체제정보
//        String userAgent = request.getHeader("User-Agent");
//
//
//        // IE
//        if (userAgent.indexOf("MSIE") > -1) {
//
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//        }
//
//        // IE 11
//        if (userAgent.indexOf("Trident") > -1) {
//            fileName = URLEncoder.encode(fileName, "UTF-8");
//        }
//
//        else {
//            fileName = new String( fileName.getBytes("UTF-8"), "8859_1");
//        }
//
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
//        response.setHeader("Content-Transfer-Encoding", "binary");
//
//        OutputStream out = response.getOutputStream();
//
//        FileInputStream fis = null;
//
//        try {
//
//            fis = new FileInputStream(file);
//            FileCopyUtils.copy(fis, out);
//
//        } finally {
//
//            if(fis != null) {
//                fis.close();
//            }
//        }
//
//        out.flush();
//
//    //    return view;
//    }
//
//
//	/**
//	 * 첨부파일 삭제
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//    @RequestMapping(value = "board/delFile" , method = RequestMethod.POST)
//	public ModelAndView delFile(HttpServletRequest request,
//			HttpServletResponse response,
//			ModelMap modelMap) throws Exception {
//
//    	ModelAndView view = new ModelAndView("jsonView");
//
//        BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
//        String fileNewName     = dMap.getString("fileNewName");
//
//		HttpSession session = request.getSession(false);
//		String del_user_sq = session.getAttribute("ADM_SQ").toString();
//		dMap.put("del_user_sq", del_user_sq); //삭제 관리자고유번호
//
//  //    String path = request.getSession().getServletContext().getRealPath("/WEB-INF/views/file/");
//		String path = "C:\\work\\";
//        String filePath = path + fileNewName;
//        File file = new File(filePath);
//
//        //실제 파일 삭제
//        if(file.exists()) {
//        	file.delete();
//        }
//
//
//		int cnt = 0;
//		try{
//			cnt = boardService.delFile(dMap);
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//
//		view.addObject("cnt", cnt);
//
//        return view;
//    }
//
//	/**
//	 * Q&A 댓글 수정
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "board/modComment" , method = RequestMethod.POST)
//	public ModelAndView modComment(HttpServletRequest request,
//			HttpServletResponse response,
//			ModelMap modelMap) throws Exception {
//
//		logger.debug("############   modComment   ################");
//		ModelAndView view = new ModelAndView("jsonView");
//		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
//
//		HttpSession session = request.getSession(false);
//		String upt_user_sq = session.getAttribute("ADM_SQ").toString();
//
//
//		dMap.put("upt_user_sq", upt_user_sq);
//
//		int cnt = 0;
//		try{
//			cnt = boardService.modComment(dMap);
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//		view.addObject("cnt", cnt);
//
//		return view;
//
//	}
//
//	/**
//	 * Q&A 댓글 등록
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "board/addComment" , method = RequestMethod.POST)
//	public ModelAndView addComment(HttpServletRequest request,
//			HttpServletResponse response,
//			ModelMap modelMap) throws Exception {
//
//		logger.debug("############   addComment   ################");
//		ModelAndView view = new ModelAndView("jsonView");
//		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
//
//		HttpSession session = request.getSession(false);
//		String bd_user_email = session.getAttribute("ADM_EMAIL").toString();
//		String bd_user_hp = session.getAttribute("ADM_HP").toString();
//		String bd_ip = session.getAttribute("LOCALIP").toString();
//		String bd_os = session.getAttribute("OSNAME").toString();
//		String reg_adm_sq = session.getAttribute("ADM_SQ").toString();
//
//		dMap.put("bd_user_email", bd_user_email);
//		dMap.put("bd_user_hp", bd_user_hp);
//		dMap.put("bd_ip", bd_ip);
//		dMap.put("bd_os", bd_os);
//		dMap.put("reg_adm_sq", reg_adm_sq);
//
//		int cnt = 0;
//		try{
//			cnt = boardService.addComment(dMap);
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//		view.addObject("cnt", cnt);
//
//		return view;
//
//	}
//
//	/**
//	 * Q&A 댓글 삭제
//	 * @param param
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "board/delComment" , method = RequestMethod.POST)
//	public ModelAndView delComment(HttpServletRequest request,
//			HttpServletResponse response,
//			ModelMap modelMap) throws Exception {
//
//		logger.debug("############   delComment   ################");
//		ModelAndView view = new ModelAndView("jsonView");
//		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
//
//		HttpSession session = request.getSession(false);
//		String del_user_sq = session.getAttribute("ADM_SQ").toString();
//
//		dMap.put("del_user_sq", del_user_sq);
//
//		int cnt = 0;
//		try{
//			cnt = boardService.delComment(dMap);
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//		view.addObject("cnt", cnt);
//
//		return view;
//
//	}

	/**
	 * AS 목록 페이지 호출
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("board/asList")
	public ModelAndView asList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		mav.addObject("pMap", pMap);
		mav.setViewName("board/asList") ;
		return mav;
	}

	/**
	 * AS 목록 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "board/asListProc" , method = RequestMethod.POST)
	public ModelAndView asListProc(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		Map<String, String> infoMap = new HashMap<String, String>();
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
	  	dMap.put("R_PAGE", (currentPage -1) *10 );
	  	dMap.put("R_ROW", rowSize);
		dMap.put("start_dt", dMap.getString("start_dt").replaceAll("-", ""));
		dMap.put("end_dt", dMap.getString("end_dt").replaceAll("-", ""));
		int totalcnt = 0;

		//리스트 조회
		infoList = boardService.getAsList(dMap);

		//리스트 카운트 조회
		infoMap = boardService.getAsListCnt(dMap);

		if(null != infoMap ){
			totalcnt  = Integer.parseInt(String.valueOf(infoMap.get("CNT")));
		}
		double dSize = Util.attrParseInt(dMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		int begin_index = (currentPage-1)* rowSize;
		int virtualNumber = totalcnt - begin_index;
		if(infoList.size()>0) {
			for(int i=0; i<infoList.size(); i++) {
				adminMap = (BrbMap<Object, Object>) infoList.get(i);
				adminMap.put("ROWNUM", virtualNumber--);
			}
		}
		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;

	}

	/**
	 * AS 상세보기 화면
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/asView" , method = RequestMethod.POST)
	public ModelAndView asView(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("board/asView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> rMap = null;

		try{
			rMap = boardService.getAs(dMap);
		} catch(Exception e){
			e.printStackTrace();
		}
		view.addObject("dMap", dMap);
		view.addObject("rMap", rMap);

		return view;
	}

	/**
	 * AS 수정
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "board/modAs" , method = RequestMethod.POST)
	public ModelAndView modAs(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);

		HttpSession session = request.getSession(false);
		String upt_adm_sq = session.getAttribute("ADM_SQ").toString();
		dMap.put("upt_adm_sq", upt_adm_sq); //수정 관리자고유번호

		int cnt = 0;
		try{
			cnt = boardService.modAs(dMap);
			//action log
	        commonService.addAdminActLog(request);

		} catch(Exception e){
			e.printStackTrace();
		}

		view.addObject("cnt", cnt);

		return view;
	}

}

