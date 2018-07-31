package com.brb.etc.service;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.brb.brbcom.common.collections.BrbMap;

/**
 * 
 * @author back
 *
 */
public interface EtcService {

	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getPopList(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	@SuppressWarnings("rawtypes")
	public BrbMap getPopListTotal(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	
	public int addPop(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	public int modPop(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	public int delPop(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	public int modEtcInit(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	@SuppressWarnings("rawtypes")
	public BrbMap getPopDet(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	@SuppressWarnings("rawtypes")
	public BrbMap getEtcInit(BrbMap<Object, Object> fMap) throws DataAccessException;
} 
