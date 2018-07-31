package com.brb.etc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("EtcDao")
public class EtcDao{ 

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;
	     
	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;

	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EtcDao.class);
	

	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getPopList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("etc.getPopList",pdMap);
	}
	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getPopListTotal(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("etc.getPopListTotal",pdMap);
	}
	
	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addPop(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.insert("etc.addPop", pdMap);
	}
	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modPop(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("etc.modPop", pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delPop(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("etc.delPop", pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modEtcInit(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("etc.modEtcInit", pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes" })
	public BrbMap getPopDet(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap)queryS.selectOne("etc.getPopDet",pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes" })
	public BrbMap getEtcInit(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap)queryS.selectOne("etc.getEtcInit",pdMap);
	}
	
	
}
