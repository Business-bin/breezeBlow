package com.brb.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.product.model.MdVo;
import com.brb.product.model.FwrVo;
import com.brb.product.model.PprtVo;
/**
 *
 * @author back
 *
 */
public interface ProductService {

	/**
	 * 모델 리스트
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getModelList(BrbMap<Object, Object> bMap);

	/**
	 * 모델 리스트2(제품등록)
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getModelList2(BrbMap<Object, Object> bMap);

	/**
	 * 모델 상세
	 * @param MdVo
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getModelDet(int mdSq);

	/**
	 * 모델 등록
	 * @param MdVo
	 * @return
	 * @throws
	 */
	public void insertModel(MdVo mvo);

	/**
	 * 모델 수정
	 * @param MdVo
	 * @return
	 * @throws
	 */
	public void updateModel(MdVo mvo);

	/**
	 * 모델 수정
	 * @param MdVo
	 * @return
	 * @throws
	 */
	public void deleteModel(MdVo mvo);

	/**
	 * 모델명 리스트
	 * @param
	 * @return List<String>
	 * @throws
	 */
	public List<String> getModel();

	/**
	 * 제품 리스트
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getProdList(BrbMap<Object, Object> bMap);

	/**
	 * 제품 검색 조건
	 * @param
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getProdSearch();

	/**
	 * 제품 코드 리스트
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getCode();

	/**
	 * 제품 상세
	 * @param PprtVo
	 * @return BrbMap<Object, Object>
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getProdDet(String pprtMac);

	/**
	 * 매칭기기등록
	 * @param PprtVo
	 * @return
	 * @throws
	 */
	public void matching(PprtVo pvo);

	/**
	 * 제품 초기화
	 * @param PprtVo
	 * @return
	 * @throws
	 */
	public void initProduct(PprtVo pvo);

	/**
	 * 제품 사용중지
	 * @param PprtVo
	 * @return
	 * @throws
	 */
	public void stopProduct(PprtVo pvo);

	/**
	 * 시리얼검색
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getMacList(BrbMap<Object, Object> bMap);

	/**
	 * 시리얼검색
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getMacList2(BrbMap<Object, Object> bMap);

	/**
	 * 제품 등록(개별)
	 * @param PprtVo
	 * @return
	 * @throws
	 */
	public void insertProduct(PprtVo pvo);

	/**
	 * 제품 등록(대량)
	 * @param PprtVo
	 * @return
	 * @throws
	 */
	public void insertProduct2(PprtVo pvo);

	/**
	 * 펌웨어버전(모델번호)
	 * @param int
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> fwrList(int mdSq);

	/**
	 * MacAddress중복체크
	 * @param String
	 * @return boolean
	 * @throws
	 */
	public boolean macDupCheck(String pprtMac);

	/**
	 * MacAddress 유효성검사
	 * @param ArrayList<PprtVo>
	 * @return boolean
	 * @throws
	 */
	public boolean validation(ArrayList<PprtVo> pvo);

	/**
	 * 제품 수정
	 * @param PprtVo
	 * @return
	 * @throws
	 */
	public void updateProduct(MdVo mvo);

	/**
	 * 펌웨어 리스트
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getFwrList(BrbMap<Object, Object> bMap);

	/**
	 * 펌웨어 상세
	 * @param FwrVo
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getFwrDet(int fwrSq);

	/**
	 * 펌웨어 등록
	 * @param FwrVo
	 * @return
	 * @throws
	 */
	public void insertFwr(FwrVo fvo);

	/**
	 * 펌웨어 수정
	 * @param FwrVo
	 * @return
	 * @throws
	 */
	public void updateFwr(FwrVo fvo);

	/**
	 * 펌웨어 삭제
	 * @param FwrVo
	 * @return
	 * @throws
	 */
	public void deleteFwr(FwrVo fvo);

	/*public String xlsExcelReader(MultipartHttpServletRequest req, String [] oriName);
	public String xlsxExcelReader(MultipartHttpServletRequest req, String [] oriName);*/

	public List<BrbMap<Object, Object>> getMem();

	public void updateRegMem(PprtVo pvo);
}
