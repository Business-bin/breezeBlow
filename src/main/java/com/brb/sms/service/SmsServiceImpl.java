package com.brb.sms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.sms.dao.SmsDao;


@Service("SmsService")
public class SmsServiceImpl implements SmsService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);
	
	@Autowired
	SmsDao smsDao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getSmsList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.getSmsList(fMap);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BrbMap getSmsListTotal(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.getSmsListTotal(fMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getMdList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.getMdList(fMap);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BrbMap getMdListTotal(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.getMdListTotal(fMap);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getAddrCode(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.getAddrCode(fMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getBtbSite(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.getBtbSite(fMap);
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public BrbMap smsDetInfo(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.smsDetInfo(fMap);
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public BrbMap getSendCnt(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.getSendCnt(fMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getSendListInfo(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.getSendListInfo(fMap);
	}
	
	public int addSms(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.addSms(fMap);
	}
	
	public int addSmsType(BrbMap<Object, Object> fMap) throws DataAccessException {
		return smsDao.addSmsType(fMap);
	}
}