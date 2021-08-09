package com.brb.product.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.product.model.MdVo;
import com.brb.product.model.FwrVo;
import com.brb.product.model.PprtVo;

@Repository("ProductDao")
public class ProductDao{

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;

	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

	/**
	 * 모델 리스트
	 * @param BrbMap<Object, Object>
	 * @return List
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getModelList(BrbMap<Object, Object> bMap) throws DataAccessException {
		return queryS.selectList("product.getModelList", bMap);
	}

	/**
	 * 모델 리스트 총 개수
	 * @param BrbMap<Object, Object>
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getModelTotal(BrbMap<Object, Object> bMap) throws DataAccessException {
		return (Integer)queryS.selectOne("product.getModelTotal", bMap);
	}

	/**
	 * 모델 리스트2(제품등록)
	 * @param BrbMap<Object, Object>
	 * @return List
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getModelList2(BrbMap<Object, Object> bMap) throws DataAccessException {
		return queryS.selectList("product.getModelList2", bMap);
	}

	/**
	 * 모델 리스트 총 개수2(제품등록)
	 * @param BrbMap<Object, Object>
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getModelTotal2(BrbMap<Object, Object> bMap) throws DataAccessException {
		return (Integer)queryS.selectOne("product.getModelTotal2", bMap);
	}

	/**
	 * 모델상세
	 * @param MdVo
	 * @return BrbMap<Object, Object>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getModelDet(int mdSq) throws DataAccessException {
		return (BrbMap<Object, Object>)queryS.selectOne("product.getModelDet", mdSq);
	}

	/**
	 * 모델 등록
	 * @param MdVo
	 * @return
	 * @throws DataAccessException
	 */
	public void insertModel(MdVo mvo) throws DataAccessException {
		queryM.insert("product.insertModel", mvo);
	}

	/**
	 * 모델 수정
	 * @param MdVo
	 * @return
	 * @throws DataAccessException
	 */
	public void updateModel(MdVo mvo) throws DataAccessException {
		queryM.update("product.updateModel", mvo);
	}

	/**
	 * 모델 삭제
	 * @param MdVo
	 * @return
	 * @throws DataAccessException
	 */
	public void deleteModel(MdVo mvo) throws DataAccessException {
		queryM.update("product.deleteModel", mvo);
	}

	/**
	 * 모델명 리스트
	 * @param
	 * @return List<String>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<String> getModel() throws DataAccessException {
		return (List<String>)queryS.selectList("product.getModel");
	}

	/**
	 * 제품 리스트
	 * @param BrbMap<Object, Object>
	 * @return List<BrbMap<Object, Object> bMap>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getProdList(BrbMap<Object, Object> bMap) throws DataAccessException {
		return queryS.selectList("product.getProdList", bMap);
	}

	/**
	 * 제품 리스트 총 개수
	 * @param BrbMap<Object, Object>
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getProdTotal(BrbMap<Object, Object> bMap) throws DataAccessException{
		return (Integer)queryS.selectOne("product.getProdTotal", bMap);
	}

	/**
	 * 제품 검색 조건
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getProdSearch() throws DataAccessException{
		return queryS.selectList("product.getProdSearch");
	}

	/**
	 * 제품 코드 리스트
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getCode() throws DataAccessException{
		return queryS.selectList("product.getCode");
	}

	/**
	 * 제품 상세
	 * @param PprtVo
	 * @return BrbMap<Object, Object>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getProdDet(String pprtMac) throws DataAccessException{
		return (BrbMap<Object, Object>)queryS.selectOne("product.getProdDet", pprtMac);
	}

	/**
	 * 제품 초기화
	 * @param PprtVo
	 * @return
	 * @throws DataAccessException
	 */
	public void matching(PprtVo pvo) throws DataAccessException{
		queryM.update("product.matching", pvo);
	}

	/**
	 * 제품 초기화
	 * @param PprtVo
	 * @return
	 * @throws DataAccessException
	 */
	public void initProduct(PprtVo pvo) throws DataAccessException{
		queryM.update("product.initProduct", pvo);
	}

	/**
	 * 제품 사용중지
	 * @param PprtVo
	 * @return
	 * @throws DataAccessException
	 */
	public void stopProduct(PprtVo pvo) throws DataAccessException{
		queryM.update("product.stopProduct", pvo);
	}

	/**
	 * 시리얼 검색
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getMacList(BrbMap<Object, Object> bMap){
		return queryS.selectList("product.getMacList", bMap);
	}

	/**
	 * 시리얼 검색 총 개수
	 * @param BrbMap<Object, Object>
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getMacTotal(BrbMap<Object, Object> bMap){
		return (Integer)queryS.selectOne("product.getMacTotal", bMap);
	}

	/**
	 * 시리얼 검색
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getMacList2(BrbMap<Object, Object> bMap){
		return queryS.selectList("product.getMacList2", bMap);
	}

	/**
	 * 시리얼 검색 총 개수
	 * @param BrbMap<Object, Object>
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getMacTotal2(BrbMap<Object, Object> bMap){
		return (Integer)queryS.selectOne("product.getMacTotal2", bMap);
	}

	/**
	 * MacAddress중복체크
	 * @param String
	 * @return boolean
	 * @throws
	 */
	public boolean macDupCheck(String pprtMac){
		int d = (Integer)queryS.selectOne("product.macDupCheck", pprtMac);
		if(d == 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 제품 등록(개별)
	 * @param PprtVo
	 * @return
	 * @throws DataAccessException
	 */
	public void insertProduct(PprtVo pvo) throws DataAccessException{
		queryM.insert("product.insertProduct", pvo);
	}

	/**
	 * 제품 등록(대량)
	 * @param PprtVo
	 * @return
	 * @throws DataAccessException
	 */
	public void insertProduct2(PprtVo pvo) throws DataAccessException{
		queryM.insert("product.insertProduct2", pvo);
	}

	/**
	 * 펌웨어버전(모델번호)
	 * @param int
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> fwrList(int mdSq) throws DataAccessException{
		return queryS.selectList("product.fwrList", mdSq);
	}

	/**
	 * MacAddress 유효성검사
	 * @param ArrayList<PprtVo>
	 * @return boolean
	 * @throws
	 */
	public boolean validation(ArrayList<PprtVo> pvo) throws DataAccessException{
		int count=0;
		for(int i=0; i<pvo.size(); i++){
			count += (Integer)queryS.selectOne("product.validation", pvo.get(i).getPprtMac());
		}
		if(count == 0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 모델 유효성검사
	 * @param ArrayList<PprtVo>
	 * @return boolean
	 * @throws
	 */
	public boolean modelValidation(ArrayList<PprtVo> pvo) throws DataAccessException{
		int count=0;
		for(int i=0; i<pvo.size(); i++){
			count += (Integer)queryS.selectOne("product.modelValidation", pvo.get(i).getMdNm());
		}
		if(count == 0){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 제품 수정
	 * @param MdVo
	 * @return
	 * @throws DataAccessException
	 */
	public void updateProduct(MdVo mvo) throws DataAccessException{
		queryM.insert("product.updateProduct", mvo);
	}

	/**
	 * 펌웨어 리스트
	 * @param BrbMap<Object, Object> bMap
	 * @return List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getFwrList(BrbMap<Object, Object> bMap) throws DataAccessException {
		return queryS.selectList("product.getFwrList", bMap);
	}

	/**
	 * 펌웨어 리스트 총 개수
	 * @param BrbMap<Object, Object> bMap
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getFwrTotal(BrbMap<Object, Object> bMap) throws DataAccessException{
		return (Integer)queryS.selectOne("product.getFwrTotal", bMap);
	}

	/**
	 * 펌웨어 상세
	 * @param FwrVo
	 * @return BrbMap<Object, Object>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getFwrDet(int fwrSq) throws DataAccessException{
		return (BrbMap<Object, Object>)queryS.selectOne("product.getFwrDet", fwrSq);
	}

	/**
	 * 펌웨어 등록
	 * @param FwrVo
	 * @return
	 * @throws DataAccessException
	 */
	public void insertFwr(FwrVo fvo) throws DataAccessException{
		queryM.insert("product.insertFwr", fvo);
	}

	/**
	 * 펌웨어 수정
	 * @param FwrVo
	 * @return
	 * @throws DataAccessException
	 */
	public void updateFwr(FwrVo fvo) throws DataAccessException{
		queryM.update("product.updateFwr", fvo);
	}

	/**
	 * 펌웨어 삭제
	 * @param FwrVo
	 * @return
	 * @throws DataAccessException
	 */
	public void deleteFwr(FwrVo fvo) throws DataAccessException{
		queryM.update("product.deleteFwr", fvo);
	}

	/*public void insertExcel(List<MdVo> list)
			throws DataAccessException{
		for(int i=0; i<list.size(); i++){
			queryM.insert("product.insertExcel", list.get(i));
		}
	}*/

	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getMem(){
		return queryS.selectList("product.getMem");
	}

	public void updateRegMem(PprtVo pvo){
		queryM.update("product.updateRegMem", pvo);
	}
}
