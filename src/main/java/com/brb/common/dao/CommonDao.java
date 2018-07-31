package com.brb.common.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("CommonDao")
public class CommonDao{

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;

	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;


	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CommonDao.class);


	/**
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addAdminLog(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.insert("common.insertAdminLog", pdMap);
	}

	/**
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addAdminActLog(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.insert("common.insertAdminActLog", pdMap);
	}

	/**
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getHpList()
			throws DataAccessException {
		return queryS.selectList("common.getHpList");
	}

	/**
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getTelList()
			throws DataAccessException {
		return queryS.selectList("common.getTelList");
	}

	/**
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public void insertEmailLog(BrbMap<Object, Object> bMap)
			throws DataAccessException {
		queryM.insert("common.insertEmailLog", bMap);
	}

	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getEnvInfo(){
		return (BrbMap<Object, Object>)queryM.selectOne("common.getEnvInfo");
	}
}
