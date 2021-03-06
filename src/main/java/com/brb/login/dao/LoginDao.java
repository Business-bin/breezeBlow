package com.brb.login.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("LoginDao")
public class LoginDao{ 

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;
	     
	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;

	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LoginDao.class);
	

	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getUser(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("login.getUser",pdMap);
	}
	

	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getCheckUser(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("login.getCheckUser",pdMap);
	}
	
	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addTestUser(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.insert("login.addTestUser", pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modTestUser(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("login.modTestUser", pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modPassword(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("login.modPassword", pdMap);
	}
}
