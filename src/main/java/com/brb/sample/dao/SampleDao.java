package com.brb.sample.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("SampleDao")
public class SampleDao{ 

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;
	     
	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;

	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SampleDao.class);
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getTestList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("sample.getList",pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getTestListAll(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("sample.getListAll",pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getTestUser(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("sample.getTestUser",pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addTestUser(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.insert("sample.addTestUser", pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modTestUser(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("sample.modTestUser", pdMap);
	}
}
