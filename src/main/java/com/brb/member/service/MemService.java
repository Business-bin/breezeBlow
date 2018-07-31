package com.brb.member.service;

import java.util.List;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.member.model.DataVo;
import com.brb.member.model.MemVo;

/**
 *
 * @author back
 *
 */
public interface MemService {

	/**
	 * 회원 리스트
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getMemList(BrbMap<Object, Object> bMap);

	/**
	 * 회원 상세
	 * @param MemVo
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getMemDet(int memSq);


	/**
	 * 회원 비밀번호 변경
	 * @param MemVo
	 * @return
	 * @throws
	 */
	public void updatePwd(MemVo bvo);

	/**
	 * 회원 수정
	 * @param MemVo
	 * @return
	 * @throws
	 */
	public void updateMem(MemVo bvo);

	/**
	 * 회원 삭제(논리)
	 * @param MemVo
	 * @return
	 * @throws
	 */
	public void deleteMem(MemVo bvo);

	/**
	 * 회원 활동로그 리스트
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getLogList(BrbMap<Object, Object> bMap);

	/**
	 * 보유제품 수정
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws
	 */
	public void modProduct(BrbMap<Object, Object> bMap);

	/**
	 * 보유제품 삭제(초기화)
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws
	 */
	public void delProduct(BrbMap<Object, Object> bMap);

	/**
	 * 보유제품 등록
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws
	 */
	public void regProduct(BrbMap<Object, Object> bMap);

	/**
	 * 핸드폰 앞자리
	 * @param
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getHpList();

	/**
	 * B2B사이트 리스트
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getBtbsList();

	/**
	 * 시/도 리스트
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getSdList();

	/**
	 * 시/군/구 리스트
	 * @param String
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getSggList(String code);

	/**
	 * 읍/면/동 리스트
	 * @param String
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getUmdList(String code);

	/**
	 * B2B사이트 리스트
	 * @param String
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getBtbList(BrbMap<Object, Object> dMap);

	/**
	 * B2B사이트 리스트 총개수
	 * @param String
	 * @return Integer
	 * @throws
	 */
	public Integer getBtbListCnt(BrbMap<Object, Object> dMap);

	/**
	 * 제품목록
	 * @param BrbMap<Object, Object>
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getProdList(BrbMap<Object, Object> bMap);

	/**
	 * 제품목록 총개수
	 * @param int
	 * @return Integer
	 * @throws
	 */
	public Integer getProdTotal(int memSq);

	/**
	 * 제품검색 조건
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getProdSearch();

	/**
	 * 상품별 회원통계
	 * @param MemVo
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getProdStat(MemVo mvo);

	/**
	 * 지역별 회원통계
	 * @param MemVo
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getLocStat(MemVo mvo);

	/**
	 * 연령별 회원통계
	 * @param MemVo
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getAgeStat(MemVo mvo);

	/**
	 * 성별 회원통계
	 * @param MemVo
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public BrbMap<Object, Object> getGenStat(MemVo mvo);

	public List<DataVo> getTimeData();
}
