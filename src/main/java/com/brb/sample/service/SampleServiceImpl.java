package com.brb.sample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.sample.dao.SampleDao;


@Service("SampleService")
public class SampleServiceImpl implements SampleService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	@Autowired
	SampleDao sampleDao;
	
	
	/**
	 * 
	 */
	@Override
	public BrbMap<Object, Object> getTestList(BrbMap<Object, Object> fMap) throws DataAccessException {
		
		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("test", sampleDao.getTestList(fMap));
		return resMap;
	}
	
	/**
	 * 
	 */
	@Override
	public BrbMap<Object, Object> getTestListAll(BrbMap<Object, Object> fMap) 
			throws DataAccessException {
		
		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("test", sampleDao.getTestListAll(fMap));
		return resMap;
	}
	
	/**
	 * 
	 */
	@Override
	public BrbMap<Object, Object> getTestUser(BrbMap<Object, Object> fMap) 
			throws DataAccessException {
		
		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("test", sampleDao.getTestUser(fMap));
		
		return resMap;
	}
	
	@Override
	public int addTestUser(BrbMap<Object, Object> fMap) 
			throws DataAccessException {
		return sampleDao.addTestUser(fMap);
	}
	
	@Override
	public int modTestUser(BrbMap<Object, Object> fMap) 
			throws DataAccessException {
		return sampleDao.modTestUser(fMap);
	}

}