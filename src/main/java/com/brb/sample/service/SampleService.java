package com.brb.sample.service;
import org.springframework.dao.DataAccessException;

import com.brb.brbcom.common.collections.BrbMap;

/**
 * 
 * @author back
 *
 */
public interface SampleService {

	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getTestList(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getTestListAll(BrbMap<Object, Object> fMap) throws DataAccessException;
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public BrbMap getTestUser(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addTestUser(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modTestUser(BrbMap<Object, Object> fMap) throws DataAccessException;
} 
