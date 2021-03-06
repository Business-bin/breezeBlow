package com.brb.push.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("PushDao")
public class PushDao{

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;

	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;


	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PushDao.class);



	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getPushList(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("push.getPushList",pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getPushListTotal(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("push.getPushListTotal",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getMdList(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("push.getMdList",pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getMdListTotal(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("push.getMdListTotal",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getAddrCode(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("push.getAddrCode",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getBtbSite(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("push.getBtbSite",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes" })
	public BrbMap getSendCnt(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap)queryS.selectOne("push.getSendCnt",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getSendListInfo(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (List<BrbMap<Object, Object>>)queryS.selectList("push.getSendListInfo",pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addPush(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.insert("push.addPush", pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addPushType(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.insert("push.addPushType", pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap pushDetInfo(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("push.pushDetInfo",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getApp()
			throws DataAccessException {
		return (BrbMap<Object, Object>)queryS.selectOne("push.getApp");
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public void insertPushLog(List<BrbMap<Object, Object>> pdMap)
			throws DataAccessException {
		for(int p=0;p<pdMap.size(); p++){
			queryM.insert("push.insertPushLog", pdMap.get(p));
		}
	}
}

