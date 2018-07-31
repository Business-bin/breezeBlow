package com.brb.app.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.app.model.AppVo;
import com.brb.brbcom.common.collections.BrbMap;

@Repository("AppDao")
public class AppDao{

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;

	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;


	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AppDao.class);

	/**
	 * APP버전 리스트
	 * @param BrbMap<Object, Object>
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getAppVrsList(BrbMap<Object, Object> bMap) throws DataAccessException{
		return queryS.selectList("app.getAppVrsList", bMap);
	}

	/**
	 * APP버전 리스트
	 * @param BrbMap<Object, Object>
	 * @return  Integer
	 * @throws DataAccessException
	 */
	public Integer getAppVrsTotal(BrbMap<Object, Object> bMap) throws DataAccessException{
		return (Integer)queryS.selectOne("app.getAppVrsTotal", bMap);
	}

	/**
	 * APP버전 상세
	 * @param String
	 * @return  BrbMap<Object, Object>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getAppVrsDet(Integer appSq) throws DataAccessException{
		return (BrbMap<Object, Object>)queryS.selectOne("app.getAppVrsDet", appSq);
	}

	/**
	 * APP버전 코드
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getAppStat() throws DataAccessException{
		return queryS.selectList("app.getAppStat");
	}

	/**
	 * APP버전 수정
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	public void updateAppVrs(AppVo avo) throws DataAccessException{
		queryM.update("app.updateAppVrs", avo);
	}

	/**
	 * APP버전 등록
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	public void insertAppVrs(AppVo avo) throws DataAccessException{
		queryM.insert("app.insertAppVrs", avo);
	}

	/**
	 * 기기별
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getDeviceList(BrbMap<Object, Object> pdMap) throws DataAccessException{
		return queryS.selectList("app.getDeviceList",pdMap);
	}

	/**
	 * os별
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getOsList(BrbMap<Object, Object> pdMap) throws DataAccessException{
		return queryS.selectList("app.getOsList",pdMap);
	}

	/**
	 * 연령대
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getAgeList(BrbMap<Object, Object> pdMap) throws DataAccessException{
		return queryS.selectList("app.getAgeList",pdMap);
	}

	/**
	 * 튜토리얼 리스트
	 * @param
	 * @return  List<BrbMap<Object, Object>>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getTutorialList(BrbMap<Object, Object> pMap) throws DataAccessException{
		return queryS.selectList("app.getTutorialList",pMap);
	}

	/**
	 * 튜토리얼 리스트 총개수
	 * @param BrbMap<Object, Object>
	 * @return  Integer
	 * @throws DataAccessException
	 */
	public Integer getTutorialTotal(BrbMap<Object, Object> bMap) throws DataAccessException{
		return (Integer)queryS.selectOne("app.getTutorialTotal", bMap);
	}

	/**
	 * 튜토리얼 상세
	 * @param int
	 * @return BrbMap<Object, Object>
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getTutorialDet(int tutSq)
			throws DataAccessException {
		return (BrbMap<Object, Object>)queryS.selectOne("app.getTutorialDet", tutSq);
	}

	/**
	 * 튜토리얼 수정
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws DataAccessException
	 */
	public void updateTutorial(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		queryM.update("app.updateTutorial", bMap);
		queryM.insert("app.insertTutorialLog", bMap);
	}

	/**
	 * 튜토리얼 삭제
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws DataAccessException
	 */
	public void deleteTutorial(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		queryM.update("app.deleteTutorial",bMap);
		queryM.insert("app.insertTutorialLog", bMap);
	}

	/**
	 * 튜토리얼 등록
	 * @param BrbMap<Object, Object>
	 * @return
	 * @throws DataAccessException
	 */
	public void insertTutorial(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		queryM.insert("app.insertTutorial", bMap);
		queryM.insert("app.insertTutorialLog", bMap);
	}

	/**
	 * 튜토리얼 리스트 총개수
	 * @param BrbMap<Object, Object>
	 * @return  Integer
	 * @throws DataAccessException
	 */
	public int checkTutorial(int tutSq) throws DataAccessException{
		return (Integer)queryS.selectOne("app.checkTutorial", tutSq);
	}

}
