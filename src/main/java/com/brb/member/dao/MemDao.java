package com.brb.member.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.member.model.DataVo;
import com.brb.member.model.MemVo;

@Repository("MemDao")
public class MemDao{

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;

	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;


	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MemDao.class);

	/**
	 * 회원 리스트
	 * @param BrbMap<Object, Object>
	 * @return List
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getMemList(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		return queryS.selectList("mem.getMemList", bMap);
	}

	/**
	 * 회원 총개수
	 * @param BrbMap<Object, Object>
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getMemTotal(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		return (Integer)queryS.selectOne("mem.getMemTotal", bMap);
	}

	public Integer getBtbsSq(String admEmail)
			throws DataAccessException {
		return (Integer)queryS.selectOne("mem.getBtbsSq", admEmail);
	}

	/**
	 * 회원 상세
	 * @param MemVo
	 * @return BrbMap<Object, Object>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getMem(int memSq)
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("mem.getMem", memSq);
	}

	/**
	 * 회원 수정
	 * @param MemVo
	 * @return
	 * @throws DataAccessException
	 */
	public void updateMem(MemVo bvo)
			throws DataAccessException {
		queryM.update("mem.updateMem", bvo);
	}

	/**
	 * 회원 비밀번호 변경
	 * @param MemVo
	 * @return
	 * @throws DataAccessException
	 */
	public void updatePwd(MemVo bvo)
			throws DataAccessException {
		queryM.update("mem.updatePwd", bvo);
	}

	/**
	 * 회원 삭제
	 * @param MemVo
	 * @return
	 * @throws DataAccessException
	 */
	public void deleteMem(MemVo bvo)
			throws DataAccessException {
		queryM.delete("mem.deleteMem", bvo);
	}

	/**
	 * 회원 활동로그
	 * @param BrbMap<Object, Object>
	 * @return List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getLogList(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		return queryS.selectList("mem.getLogList", bMap);
	}

	/**
	 * 회원 총개수
	 * @param BrbMap<Object, Object>
	 * @return Integer
	 * @throws DataAccessException
	 */
	public Integer getLogTotal(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		return (Integer)queryS.selectOne("mem.getLogTotal", bMap);
	}

	/**
	 * 보유제품 수정
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws DataAccessException
	 */
	public void modProduct(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		queryM.update("mem.modProduct", bMap);
	}

	/**
	 * 보유제품 삭제
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws DataAccessException
	 */
	public void delProduct(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		queryM.update("mem.delProduct", bMap);
	}

	/**
	 * 보유제품 등록
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws DataAccessException
	 */
	public void regProduct(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		queryM.update("mem.regProduct", bMap);
	}

	/**
	 * 사용자 기기 등록
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws DataAccessException
	 */
	public void regProductUpdate(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		queryM.update("mem.regProductUpdate", bMap);
	}

	/**
	 * MAC Address 등록
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws DataAccessException
	 */
	public String getMac(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		return (String) queryM.selectOne("mem.getMac", bMap);
	}

	/**
	 * 핸드폰 앞자리 리스트
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getHpList()
			throws DataAccessException {
		return queryS.selectList("mem.getHpList");
	}

	/**
	 * B2B사이트 리스트
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getBtbsList()
			throws DataAccessException {
		return queryS.selectList("mem.getBtbsList");
	}

	/**
	 * 시/도 리스트
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getSdList()
			throws DataAccessException {
		return queryS.selectList("mem.getSdList");
	}

	/**
	 * 시/군/구 리스트
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getSggList(String code)
			throws DataAccessException {
		return queryS.selectList("mem.getSggList", code);
	}

	/**
	 * 읍/면/동 리스트
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getUmdList(String code)
			throws DataAccessException {
		return queryS.selectList("mem.getUmdList", code);
	}

	/**
	 * b2b사이트 리스트
	 * @param BrbMap<Object, Object>
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getBtbList(BrbMap<Object, Object> dMap)
			throws DataAccessException {
		return queryS.selectList("mem.getBtbList", dMap);
	}

	/**
	 * b2b사이트 총개수
	 * @param BrbMap<Object, Object>
	 * @return  Integer
	 * @throws DataAccessException
	 */
	public Integer getBtbListCnt(BrbMap<Object, Object> dMap)
			throws DataAccessException {
		return (Integer)queryS.selectOne("mem.getBtbListCnt", dMap);
	}

	/**
	 * 제품 리스트
	 * @param BrbMap<Object, Object>
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getProdList(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		return queryS.selectList("mem.getProdList", bMap);
	}

	/**
	 * 제품 리스트 총개수
	 * @param int
	 * @return  Integer
	 * @throws DataAccessException
	 */
	public Integer getProdTotal(int memSq) throws DataAccessException {
		return (Integer)queryS.selectOne("mem.getProdTotal", memSq);
	}

	/**
	 * 제품 리스트 총개수
	 * @param int
	 * @return  Integer
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getProdSearch()
			throws DataAccessException {
		return queryS.selectList("mem.getProdSearch");
	}

	/**
	 * 제품 검색 조건
	 * @param
	 * @return  List<DataVo>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<DataVo> getDataTime()
			throws DataAccessException {
		return queryS.selectList("mem.getDataTime");
	}

	/**
	 * 제품별 회원통계
	 * @param MemVo
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getProdStat(MemVo mvo)
			throws DataAccessException {
		return queryS.selectList("mem.getProdStat", mvo);
	}

	/**
	 * 지역별 회원통계
	 * @param MemVo
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getLocStat(MemVo mvo)
			throws DataAccessException {
		if(mvo.getAddr1().equals("")){
			return queryS.selectList("mem.getLocStat", mvo);
		}else{
			return queryS.selectList("mem.getLocStat2", mvo);
		}
	}

	/**
	 * 연령별 회원통계
	 * @param MemVo
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getAgeStat(MemVo mvo)
			throws DataAccessException {
		return queryS.selectList("mem.getAgeStat", mvo);
	}

	/**
	 * 성별 회원통계
	 * @param MemVo
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getGenStat(MemVo mvo)
			throws DataAccessException {
		return (BrbMap<Object, Object>)queryS.selectOne("mem.getGenStat", mvo);
	}

	@SuppressWarnings("unchecked")
	public List<DataVo> getTimeData()
			throws DataAccessException {
		return queryS.selectList("mem.getTimeData");
	}
}
