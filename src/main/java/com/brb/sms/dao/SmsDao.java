package com.brb.sms.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("SmsDao")
public class SmsDao{ 

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;
	     
	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;

	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SmsDao.class);
	

	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes"})
	public List getSmsList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("sms.getSmsList",pdMap);
	}
	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getSmsListTotal(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("sms.getSmsListTotal",pdMap);
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
		return queryS.selectList("sms.getMdList",pdMap);
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
		return (BrbMap) queryS.selectOne("sms.getMdListTotal",pdMap);
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
		return queryS.selectList("sms.getAddrCode",pdMap);
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
		return queryS.selectList("sms.getBtbSite",pdMap);
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
		return (BrbMap)queryS.selectOne("sms.getSendCnt",pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getSendListInfo(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("sms.getSendListInfo",pdMap);
	}


	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addSms(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.insert("sms.addSms", pdMap);
	}
	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addSmsType(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.insert("sms.addSmsType", pdMap);
	}
	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap smsDetInfo(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap) queryS.selectOne("sms.smsDetInfo",pdMap);
	}
	
}
