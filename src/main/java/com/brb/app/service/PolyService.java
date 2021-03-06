package com.brb.app.service;

import java.util.List;

import com.brb.app.model.PolyVo;
import com.brb.brbcom.common.collections.BrbMap;

/**
 *
 * @author back
 *
 */
public interface PolyService {

	/**
	 * 개인정보동의 종류 리스트
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getPoly();

	/**
	 * 개인정보동의 리스트
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getPolyList(BrbMap<Object, Object> bMap);

	/**
	 * 개인정보동의 상세
	 * @param String polySq
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getPolyDet(int polySq);


	/**
	 * 개인정보동의 수정
	 * @param PolyVo
	 * @return
	 * @throws
	 */
	public void updatePoly(PolyVo pvo);

	/**
	 * 개인정보동의 등록
	 * @param PolyVo
	 * @return
	 * @throws
	 */
	public void insertPoly(PolyVo pvo);

	/**
	 * 개인정보동의 등록
	 * @param String
	 * @return int
	 * @throws
	 */
	public int checkPoly(PolyVo pvo);
}
