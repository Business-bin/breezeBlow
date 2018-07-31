package com.brb.main.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("MainDao")
public class MainDao{ 

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;
	     
	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;

	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MainDao.class);
	

	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getMemList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("main.getMainMemList",pdMap);
	}
	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getMemListTotal(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("main.getMainMemListTotal",pdMap);
	}
	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getAsList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("main.getMainAsList",pdMap);
	}
	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getAsListTotal(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("main.getMainAsListTotal",pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getQnaList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("main.getMainQnaList",pdMap);
	}
	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getQnaListTotal(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("main.getMainQnaListTotal",pdMap);
	}
	
	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getappList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("main.getMainG",pdMap);
	}
	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getmemList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("main.getMainG2",pdMap);
	}
	
	
	
}
