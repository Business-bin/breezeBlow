package com.brb.btb.service;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.brb.brbcom.common.collections.BrbMap;

/**
 * 
 * @author back
 *
 */
public interface BtbService {

	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getBtbList(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getBtbListCnt(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getBtb(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addBtb(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getBtbSq() throws DataAccessException;	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modBtb(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delBtb(BrbMap<Object, Object> fMap) throws DataAccessException;	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int goStop(BrbMap<Object, Object> fMap) throws DataAccessException;	

	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int adminActivityStop(BrbMap<Object, Object> fMap) throws DataAccessException;	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int goUse(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getAdminList(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getAdminListCnt(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getAdminLogList(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getAdminLogListCnt(BrbMap<Object, Object> fMap) throws DataAccessException;	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getAdmin(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addAdmin(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modAdmin(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delAdmin(BrbMap<Object, Object> fMap) throws DataAccessException;	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getBtbNmList() throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> checkBtbNm(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int goAdminStop(BrbMap<Object, Object> fMap) throws DataAccessException;	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int goAdminUse(BrbMap<Object, Object> fMap) throws DataAccessException;	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getHpTelList() throws DataAccessException;
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getTemplList() throws DataAccessException;		
} 
