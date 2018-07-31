package com.brb.admin.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("AdminDao")
public class AdminDao{ 

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;
	     
	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;

	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AdminDao.class);
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getAdminList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("admin.getAdminList",pdMap);
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
		return (BrbMap<Object, Object>) queryS.selectOne("admin.getAdminListCnt",pdMap);
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
		return (BrbMap<Object, Object>) queryS.selectOne("admin.getAdmin",pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addAdmin(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.insert("admin.addAdmin", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modAdmin(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("admin.modAdmin", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delAdmin(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("admin.delAdmin", pdMap);
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
		return queryS.selectList("admin.getAdminLogList",pdMap);
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
		return (BrbMap<Object, Object>) queryS.selectOne("admin.getAdminLogListCnt",pdMap);
	}	

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> checkId(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("admin.checkId",pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int changePwd(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryM.update("admin.changePwd", pdMap);
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getChartList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("admin.getChartList", pdMap);
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getSiList() 
			throws DataAccessException {
		return queryS.selectList("admin.getSiList");
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getGuList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("admin.getGuList", pdMap);
	}
}
