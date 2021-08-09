package com.brb.btb.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("BtbDao")
public class BtbDao{ 

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;
	     
	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;

	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BtbDao.class);
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getBtbList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("btb.getBtbList",pdMap);
	}
	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getBtbListCnt(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("btb.getBtbListCnt",pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getBtb(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("btb.getBtb",pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addBtb(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.insert("btb.addBtb", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getBtbSq() 
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("btb.getBtbSq");
	}
	
	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modBtb(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.modBtb", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delBtb(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.delBtb", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int goStop(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.goStop", pdMap);
	}	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int adminActivityStop(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.adminActivityStop", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int goUse(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.goUse", pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int adminActivityGo(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.adminActivityGo", pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getAdminList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("btb.getAdminList",pdMap);
	}
	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getAdminListCnt(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("btb.getAdminListCnt",pdMap);
	}
	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getAdminLogList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("btb.getAdminLogList",pdMap);
	}
	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getAdminLogListCnt(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("btb.getAdminLogListCnt",pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getAdmin(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("btb.getAdmin",pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addAdmin(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.insert("btb.addAdmin", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modAdmin(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.modAdmin", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delAdmin(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.delAdmin", pdMap);
	}		
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getBtbNmList() 
			throws DataAccessException {
		return queryS.selectList("btb.getBtbNmList");
	}

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> checkBtbNm(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("btb.checkBtbNm",pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int goAdminStop(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.goAdminStop", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int goAdminUse(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("btb.goAdminUse", pdMap);
	}		
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getHpTelList() throws DataAccessException {
		return queryS.selectList("btb.getHpTelList");
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getTemplList() throws DataAccessException {
		return queryS.selectList("btb.getTemplList");
	}		
}
