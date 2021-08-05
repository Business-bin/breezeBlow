package com.brb.product.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.brbcom.common.util.ApplicationContextHolder;
import com.brb.brbcom.common.util.RequestParameterUtil;
import com.brb.brbcom.common.util.Util;
import com.brb.brbcom.foundation.property.PropertyService;
import com.brb.common.service.CommonService;
import com.brb.product.model.FwrVo;
import com.brb.product.model.MdVo;
import com.brb.product.model.PprtVo;
import com.brb.product.service.ProductService;
/**
 *
 * @author back
 *
 */
@Controller
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	protected PropertyService propertyService = (PropertyService)ApplicationContextHolder.getContext().getBean("propertiesService");

	@Autowired
	ProductService productService;

	@Autowired
	CommonService commonService;

	/**
	 * 모델 리스트
	 * @param MdVo
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="/product/modelList")
	public ModelAndView modelList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("pMap", pMap);
		view.addObject("model", productService.getModel());
		view.setViewName("product/modelList");
		return view;
	}

	/**
	 * 모델 검색
	 * @param HttpServletRequest
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/product/modelSearch")
	public ModelAndView modelSearch(HttpServletRequest request){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap = RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "MD_SQ" : bMap.getString("sidx");

		if("MD_SQ".equals(sidx)){
			sidx = "MD_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *10 );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List)productService.getModelList(bMap).get("modelList");
		//리스트 카운트 조회
		totalcnt = (int)productService.getModelList(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * 모델 검색2(제품등록)
	 * @param HttpServletRequest
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/product/modelSearch2")
	public ModelAndView modelSearch2(HttpServletRequest request){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap = RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "MD_SQ" : bMap.getString("sidx");

		if("MD_SQ".equals(sidx)){
			sidx = "MD_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *10 );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List)productService.getModelList2(bMap).get("modelList");
		//리스트 카운트 조회
		totalcnt = (int)productService.getModelList2(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * 모델 상세
	 * @param MdVo
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="/product/modelDet")
	public ModelAndView modelDet(HttpServletRequest request, HttpSession session){
		ModelAndView view = new ModelAndView();
		BrbMap<Object, Object> dMap	= RequestParameterUtil.getParameterMap(request);
		if(dMap.getString("gubun").equals("det")){
			view.addObject("modelDet", productService.getModelDet(dMap.getInt("mdSq")));
		}
		view.addObject("dMap", dMap);
		view.addObject("adminId", session.getAttribute("ADM_EMAIL"));
		view.addObject("gubun", dMap.getString("gubun"));
		view.setViewName("product/modelDet");
		return view;
	}

	/**
	 * 모델 등록
	 * @param MdVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/insertModel")
	public ModelAndView insertModel(@ModelAttribute MdVo mvo, HttpSession session, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		try{
			BufferedOutputStream bos = null;
	    	String fileName = mvo.getModelFile().getOriginalFilename();
	    	byte[] bytes = mvo.getModelFile().getBytes();
	    	String saveFileName = getExtension(fileName, today);
	    	mvo.setCpImgNm("/upload/product/"+saveFileName);
	    	String savePath = propertyService.getString("IMAGE_UPLOAD_PATH") + "/product/" + saveFileName;
	    	System.out.println("111111111111111 savePath = "+savePath);
	    	bos = new BufferedOutputStream(new FileOutputStream(savePath));
    		bos.write(bytes);
    		bos.flush();
	    	bos.close();
	    } catch (Exception e){
	    	logger.debug("imgUpload Fail, error : "+e);
	    } finally{
	    	logger.debug("File Upload Success!!");
	    }
		mvo.setRegAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		productService.insertModel(mvo);
		commonService.addAdminActLog(request);
		return view;
	}

	/**
	 * 제품 수정
	 * @param MdVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/updateModel")
	public ModelAndView updateModel(@ModelAttribute MdVo mvo, HttpSession session, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		try{
			if(!mvo.getModelFile().getOriginalFilename().equals("")){
				if(mvo.getDelImageNm() != null && mvo.getDelImageNm() != ""){
					File file = new File(propertyService.getString("IMAGE_UPLOAD_PATH") + "/product/" + mvo.getDelImageNm());
					if(file.exists()){
						if(file.delete()){
							logger.debug(mvo.getDelImageNm()+"File Delete Success!!");
							System.out.println(mvo.getDelImageNm()+"File Delete Success!!");
						}else{
							logger.debug(mvo.getDelImageNm()+"File Delete Fail!!");
							System.out.println(mvo.getDelImageNm()+"File Delete Fail!!");
						}
					}else{
						logger.debug(mvo.getDelImageNm()+"No File!!");
						System.out.println(mvo.getDelImageNm()+"No File!!");
					}
				}
				BufferedOutputStream bos = null;
		    	String fileName = mvo.getModelFile().getOriginalFilename();
		    	byte[] bytes = mvo.getModelFile().getBytes();
		    	String saveFileName = getExtension(fileName, today);
		    	mvo.setCpImgNm("/upload/product/"+saveFileName);
		    	String savePath = propertyService.getString("IMAGE_UPLOAD_PATH") + "/product/" + saveFileName;
		    	bos = new BufferedOutputStream(new FileOutputStream(savePath));
	    		bos.write(bytes);
	    		bos.flush();
		    	bos.close();
			}
	    } catch (Exception e){
	    	logger.debug("ImgUpload Fail, error : "+e);
	    } finally{
	    	logger.debug("File Upload Success!!");
	    }
		mvo.setUptAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		productService.updateModel(mvo);
		commonService.addAdminActLog(request);
		return view;
	}

	/**
	 * 모델 삭제
	 * @param MdVo, HttpSession
	 * @return
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/deleteModel")
	public void deleteModel(@ModelAttribute MdVo mvo, HttpSession session, HttpServletRequest request){
		mvo.setDelAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		productService.deleteModel(mvo);
		commonService.addAdminActLog(request);
	}

	/**
	 * 제품 리스트
	 * @param
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="/product/productList")
	public ModelAndView productList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("pMap", pMap);
		view.addObject("sList", productService.getProdSearch());
		view.setViewName("product/productList");
		return view;
	}

	/**
	 * 제품 등록 화면
	 * @param
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="/product/productNew")
	public ModelAndView productNew(){
		ModelAndView view = new ModelAndView();
		view.setViewName("product/productNew");
		return view;
	}

	/**
	 * 펌웨어버전(모델번호)
	 * @param int
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/fwrVrsList")
	public ModelAndView fwrVrsList(@RequestParam int mdSq){
		ModelAndView view = new ModelAndView();
		view.addObject("fwrList", productService.fwrList(mdSq));
		return view;
	}

	/**
	 * MacAddress중복체크
	 * @param String
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/macDupCheck")
	public ModelAndView macDupCheck(@RequestParam String pprtMac){
		ModelAndView view = new ModelAndView();
		view.addObject("dup", productService.macDupCheck(pprtMac));
		return view;
	}

	/**
	 * 제품 등록
	 * @param PprtVo, HttpSession
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/insertProduct")
	public void insertProduct(@ModelAttribute PprtVo pvo, HttpSession session, HttpServletRequest request){
		pvo.setRegAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
		productService.insertProduct(pvo);
		commonService.addAdminActLog(request);
	}

	/**
	 * 제품 검색
	 * @param HttpServletRequest
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/product/productSearch")
	public ModelAndView productSearch(HttpServletRequest request, HttpSession session){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap	= RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "PPRT_SQ" : bMap.getString("sidx");

		if("PPRT_SQ".equals(sidx)){
			sidx = "PPRT_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *10 );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;
		String admEmail = (String)session.getAttribute("ADM_EMAIL");
		String admClass = (String)session.getAttribute("ADM_CLASS");
		if(admClass.equals("3") || admClass.equals("4")){
			bMap.put("admEmail", admEmail);
		}

		//리스트 조회
		infoList = (List)productService.getProdList(bMap).get("prodList");
		//리스트 카운트 조회
		totalcnt = (int)productService.getProdList(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * 제품 상세
	 * @param PprtVo
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="/product/productDet")
	public ModelAndView productDet(HttpServletRequest request, HttpSession session){
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("dMap", dMap);
		view.addObject("prodDet", productService.getProdDet(dMap.getString("pprtMac")));
		view.addObject("code", productService.getCode());
		view.addObject("gubun", dMap.getString("gubun"));
		view.addObject("adminId", session.getAttribute("ADM_EMAIL"));
		view.setViewName("product/productDet");
		return view;
	}

	/**
	 * 매칭기기등록
	 * @param PprtVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/matching")
	public ModelAndView matching(@ModelAttribute PprtVo pvo, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		productService.matching(pvo);
		commonService.addAdminActLog(request);
		return view;
	}

	/**
	 * 제품 초기화
	 * @param PprtVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/initProduct")
	public ModelAndView initProduct(@ModelAttribute PprtVo pvo, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		productService.initProduct(pvo);
		commonService.addAdminActLog(request);
		return view;
	}

	/**
	 * 제품 사용중지
	 * @param PprtVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/stopProduct")
	public ModelAndView stopProduct(@ModelAttribute PprtVo pvo, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		productService.stopProduct(pvo);
		commonService.addAdminActLog(request);
		return view;
	}

	/**
	 * 제품 상세2
	 * @param PprtVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/productDet2")
	public ModelAndView productDet2(@ModelAttribute PprtVo pvo){
		ModelAndView view = new ModelAndView();
		view.addObject("prodDet", productService.getProdDet(pvo.getPprtMac()));
		return view;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/product/macSearch")
	public ModelAndView macSearch(HttpServletRequest request){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap	= RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> adminMap = null;
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "MD_SQ" : bMap.getString("sidx");

		if("MD_SQ".equals(sidx)){
			sidx = "MD_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *rowSize );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List)productService.getMacList(bMap).get("macList");
		//리스트 카운트 조회
		totalcnt = (int)productService.getMacList(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/product/macSearch2")
	public ModelAndView macSearch2(HttpServletRequest request){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap	= RequestParameterUtil.getParameterMap(request);
		BrbMap<Object, Object> adminMap = null;
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "MD_SQ" : bMap.getString("sidx");

		if("MD_SQ".equals(sidx)){
			sidx = "MD_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *rowSize );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List)productService.getMacList2(bMap).get("macList");
		//리스트 카운트 조회
		totalcnt = (int)productService.getMacList2(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
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
	 * 펌웨어 리스트
	 * @param
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="/product/fwrList")
	public ModelAndView fwrList(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> pMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("pMap", pMap);
		view.setViewName("product/fwrList");
		return view;
	}

	/**
	 * 펌웨어 검색
	 * @param HttpServletRequest
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/product/fwrSearch")
	public ModelAndView fwrSearch(HttpServletRequest request){
		ModelAndView view = new ModelAndView("jsonView");
		BrbMap<Object, Object> bMap	= RequestParameterUtil.getParameterMap(request);
		List infoList = null;
		int rowSize = Util.attrParseInt(bMap, "rows");
		int currentPage = Util.attrParseInt(bMap, "page");
		String sidx = bMap.getString("sidx") == "" ? "FWR_SQ" : bMap.getString("sidx");

		if("FWR_SQ".equals(sidx)){
			sidx = "FWR_SQ";
		}
		String sord = bMap.getString("sord");
	    String orderbyColum = sidx.trim() + "  "+sord;
	    bMap.put("orderBy", orderbyColum);
	    bMap.put("R_PAGE", (currentPage -1) *10 );
	    bMap.put("R_ROW", rowSize);
		int totalcnt = 0;

		//리스트 조회
		infoList = (List)productService.getFwrList(bMap).get("fwrList");
		//리스트 카운트 조회
		totalcnt = (int)productService.getFwrList(bMap).get("totalPage");

		double dSize = Util.attrParseInt(bMap, "rows");
		double liTotalPage = (double)Math.ceil(totalcnt/dSize) ;

		view.addObject("rows", infoList);
		view.addObject("total", liTotalPage);
		view.addObject("page", currentPage);
		view.addObject("records", totalcnt);
		return view;
	}

	/**
	 * 펌웨어 상세
	 * @param FwrVo
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value="/product/fwrDet")
	public ModelAndView fwrDet(HttpServletRequest request, HttpSession session){
		ModelAndView view = new ModelAndView();
		BrbMap <Object, Object> dMap = RequestParameterUtil.getParameterMap(request);
		view.addObject("dMap", dMap);
		view.addObject("adminId", session.getAttribute("ADM_EMAIL"));
		view.addObject("fwrDet", productService.getFwrDet(dMap.getInt("fwrSq")));
		view.addObject("gubun", dMap.getString("gubun"));
		view.setViewName("product/fwrDet");
		return view;
	}

	/**
	 * 펌웨어 등록
	 * @param FwrVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/insertFwr")
	public ModelAndView insertFwr(@ModelAttribute FwrVo fvo, HttpSession session, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		try{
			BufferedOutputStream bos = null;
	    	String fileName = fvo.getFwrFile().getOriginalFilename();
	    	byte[] bytes = fvo.getFwrFile().getBytes();
	    	String saveFileName = getExtension(fileName, today);
	    	String savePath = propertyService.getString("IMAGE_UPLOAD_PATH") + "/product/" + saveFileName;
	    	fvo.setFwrFileCont(bytes);
	    	fvo.setFwrFileNm("/upload/product/" + saveFileName);
	    	fvo.setFwrFileNm1(fileName);
	    	bos = new BufferedOutputStream(new FileOutputStream(savePath));
    		bos.write(bytes);
    		bos.flush();
	    	bos.close();
	    } catch (Exception e){
	    	logger.debug("imgUpload Fail, error : "+e);
	    } finally{
	    	logger.debug("File Upload Success!!");
	    }
		fvo.setRegAdmSq((Integer.parseInt((String)session.getAttribute("ADM_SQ"))));
		productService.insertFwr(fvo);
		commonService.addAdminActLog(request);
		return view;
	}

	/**
	 * 펌웨어 수정
	 * @param FwrVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/updateFwr")
	public ModelAndView updateFwr(@ModelAttribute FwrVo fvo, HttpSession session, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		if(fvo.getFwrFile() != null){
			if(fvo.getDelFwrFile() != null && fvo.getDelFwrFile() != ""){
				File file = new File(propertyService.getString("IMAGE_UPLOAD_PATH") + "/product/" + fvo.getDelFwrFile());
				if(file.exists()){
					if(file.delete()){
						logger.debug("File Delete Success!!");
						System.out.println("File Delete Success!!");
					}else{
						logger.debug("File Delete Fail!!");
						System.out.println("File Delete Fail!!");
					}
				}else{
					logger.debug("No File!!");
					System.out.println("No File!!");
				}
			}
			Calendar calendar = Calendar.getInstance();
	        Date date = calendar.getTime();
	        String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
			try{
				BufferedOutputStream bos = null;
		    	String fileName = fvo.getFwrFile().getOriginalFilename();
		    	byte[] bytes = fvo.getFwrFile().getBytes();
		    	String saveFileName = getExtension(fileName, today);
		    	String savePath = propertyService.getString("IMAGE_UPLOAD_PATH") + "/product/" + saveFileName;
		    	fvo.setFwrFileNm("/upload/product/" + saveFileName);
		    	fvo.setFwrFileNm1(fileName);
		    	bos = new BufferedOutputStream(new FileOutputStream(savePath));
	    		bos.write(bytes);
	    		bos.flush();
		    	bos.close();
		    } catch (Exception e){
		    	logger.debug("imgUpload Fail, error : "+e);
		    } finally{
		    	logger.debug("File Upload Success!!");
		    }
		}
		fvo.setUptAdmSq((Integer.parseInt((String)session.getAttribute("ADM_SQ"))));
		productService.updateFwr(fvo);
		commonService.addAdminActLog(request);
		return view;
	}

	/**
	 * 펌웨어 삭제
	 * @param FwrVo
	 * @return ModelAndView
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/product/deleteFwr")
	public ModelAndView deleteFwr(@ModelAttribute FwrVo fvo, HttpSession session, HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		fvo.setDelAdmSq((Integer.parseInt((String)session.getAttribute("ADM_SQ"))));
		productService.deleteFwr(fvo);
		commonService.addAdminActLog(request);
		return view;
	}

	/**
	 * getExtension
	 * @param fileName
	 * @return
	 */
	public String getExtension(String fileName, String today){
	    int dotPosition = fileName.lastIndexOf('.');
	    if ((-1 != dotPosition) && (fileName.length() - 1 > dotPosition)) {
	    	return fileName.substring(0, dotPosition)+today+"."+fileName.substring(dotPosition + 1);
	    }
	    return "";
	}

	/**
	 * CSV로드, 유효성 검사
	 * @param MultipartHttpServletRequest, HttpSession
	 * @return ModelAndView
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/product/csvLoad")
	public ModelAndView csvLoad(MultipartHttpServletRequest request, HttpSession session) throws IOException{
		ModelAndView view = new ModelAndView();
		MultipartFile file = request.getFile("csvFile");
		BufferedReader br = new BufferedReader(new InputStreamReader((file.getInputStream()),"UTF8"));
		String [] temp;
		String s;
		PprtVo pvo;
		ArrayList<PprtVo> ar = new ArrayList<PprtVo>();
		br.readLine();
		boolean wrong = true;
		while((s = br.readLine()) != null){
			pvo = new PprtVo();
			temp = s.split(",");
			if(temp[0].equals("") || temp[1].equals("") || temp[2].equals("") || temp[4].equals("")){
				wrong = false;
			}
			pvo.setPprtMac(temp[0]);
			pvo.setMdNm(temp[1]);
			pvo.setMiniYn(temp[2]);
			if(temp[2].equals("Y")){
				if(temp[3].equals("")){
					wrong = false;
				}
			}
			pvo.setPprtMaMac(temp[3]);
			pvo.setFwrVrsNm(temp[4]);
			ar.add(pvo);
		}
		boolean mac1 = true;
		for(int i=0; i<ar.size(); i++){
			for(int k=i; k<ar.size()-1; k++){
				if(ar.get(i).getPprtMac().equals(ar.get(k+1).getPprtMac())){
					mac1 = false;
				}
			}
		}
		boolean mac2 = productService.validation(ar);

		view.addObject("csvList", ar);
		view.addObject("mac1", mac1);
		view.addObject("mac2", mac2);
		view.addObject("wrong", wrong);
		br.close();
		return view;
	}

	/**
	 * CSV업로드
	 * @param MultipartHttpServletRequest, HttpSession
	 * @return ModelAndView
	 * @throws IOException
	 */
	@RequestMapping(value="/product/csvUploadFile")
	public ModelAndView csvUploadFile(MultipartHttpServletRequest request, HttpSession session) throws IOException{
		ModelAndView view = new ModelAndView();
		MultipartFile file = request.getFile("csvFile");
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),"UTF8"));
		String [] temp;
		String s;
		PprtVo pvo;
		br.readLine();
		while((s = br.readLine()) != null){
			pvo = new PprtVo();
			temp = s.split(",");
			pvo.setPprtMac(temp[0]);
			pvo.setMdNm(temp[1]);
			pvo.setMiniYn(temp[2]);
			pvo.setPprtMaMac(temp[3]);
			pvo.setFwrVrsNm(temp[4]);
			pvo.setRegAdmSq(Integer.parseInt((String)session.getAttribute("ADM_SQ")));
			productService.insertProduct2(pvo);
		}
		br.close();
		view.setViewName("product/productNew");
		commonService.addAdminActLog(request);
		return view;
	}


	/*@RequestMapping(value="/product/excelUpload")
	public ModelAndView excelUpload(){
		ModelAndView view = new ModelAndView();
		view.setViewName("product/excelUpload");
		return view;
	}

	@RequestMapping(value="/product/excelUploadFile", method = RequestMethod.POST)
	public void excelUploadFile(MultipartHttpServletRequest request){
		List<MultipartFile> mpf = request.getFiles("imgFile");
		String [] oriName = new String[mpf.size()];
		String today = "";
		for(int i=0; i<mpf.size(); i++){
			oriName[i] = mpf.get(i).getOriginalFilename();
		}
		String excelType = request.getParameter("excelType");
		if(excelType.equals("xlsx")){
			today = productService.xlsxExcelReader(request, oriName);
		}else if(excelType.equals("xls")){
			today = productService.xlsExcelReader(request, oriName);
		}
		try{
			BufferedOutputStream bos = null;
	    	for(int i=0; i<mpf.size(); i++){
		    	String fileName = mpf.get(i).getOriginalFilename();
		    	byte[] bytes = mpf.get(i).getBytes();
		    	String saveFileName = getExtension(fileName, today);
		    	String savePath = propertyService.getString("IMAGE_UPLOAD_PATH") + "/product/" + saveFileName;
	    		bos = new BufferedOutputStream(new FileOutputStream(savePath));
	    		bos.write(bytes);
	    		bos.flush();
	    	}
	    	bos.close();
	    } catch (Exception e){
	    	logger.debug("Excel Upload Fail!!");
	    }
	}*/

	/**
	 * 사용자 기기 등록 테스트
	 * @param
	 * @return
	 * @throws
	 */
	@RequestMapping(value="/product/regMemTest")
	public ModelAndView modelList(@RequestParam int pprtSq){
		ModelAndView view = new ModelAndView();
		view.addObject("memList", productService.getMem());
		view.addObject("pprtSq", pprtSq);
		view.setViewName("product/regMemTest");
		return view;
	}

	@ResponseBody
	@RequestMapping(value="/product/updateRegMem")
	public void updateRegMem(@ModelAttribute PprtVo pvo){
		productService.updateRegMem(pvo);
	}


}
