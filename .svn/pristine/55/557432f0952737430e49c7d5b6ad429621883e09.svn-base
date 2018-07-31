package com.brb.login.service;
import org.springframework.dao.DataAccessException;

import com.brb.brbcom.common.collections.BrbMap;

/**
 * 
 * @author back
 *
 */
public interface LoginService {

	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getUser(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	@SuppressWarnings("rawtypes")
	public BrbMap getCheckUser(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addUser(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modUser(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modPassword(BrbMap<Object, Object> fMap) throws DataAccessException;
} 
