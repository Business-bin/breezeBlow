package com.brb.etc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.etc.dao.EtcDao;


@Service("EtcService")
public class EtcServiceImpl implements EtcService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EtcServiceImpl.class);
	
	@Autowired
	EtcDao etcDao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getPopList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return etcDao.getPopList(fMap);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BrbMap getPopListTotal(BrbMap<Object, Object> fMap) throws DataAccessException {
		return etcDao.getPopListTotal(fMap);
	}

	public int addPop(BrbMap<Object, Object> fMap) throws DataAccessException {
		return etcDao.addPop(fMap);
	}
	
	public int modPop(BrbMap<Object, Object> fMap) throws DataAccessException {
		return etcDao.modPop(fMap);
	}
	
	public int delPop(BrbMap<Object, Object> fMap) throws DataAccessException {
		return etcDao.delPop(fMap);
	}
	
	public int modEtcInit(BrbMap<Object, Object> fMap) throws DataAccessException {
		return etcDao.modEtcInit(fMap);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public BrbMap getPopDet(BrbMap<Object, Object> fMap) throws DataAccessException {
		return etcDao.getPopDet(fMap);
	}
	
	public BrbMap getEtcInit(BrbMap<Object, Object> fMap) throws DataAccessException {
		return etcDao.getEtcInit(fMap);
	}
}