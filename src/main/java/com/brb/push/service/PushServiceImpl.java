package com.brb.push.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.push.dao.PushDao;


@Service("PushService")
public class PushServiceImpl implements PushService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PushServiceImpl.class);

	@Autowired
	PushDao pushDao;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getPushList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.getPushList(fMap);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BrbMap getPushListTotal(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.getPushListTotal(fMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getMdList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.getMdList(fMap);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BrbMap getMdListTotal(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.getMdListTotal(fMap);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getAddrCode(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.getAddrCode(fMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getBtbSite(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.getBtbSite(fMap);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public BrbMap pushDetInfo(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.pushDetInfo(fMap);
	}


	@SuppressWarnings({ "rawtypes" })
	@Override
	public BrbMap getSendCnt(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.getSendCnt(fMap);
	}

	@Override
	public List<BrbMap<Object, Object>> getSendListInfo(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.getSendListInfo(fMap);
	}

	public int addPush(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.addPush(fMap);
	}

	public int addPushType(BrbMap<Object, Object> fMap) throws DataAccessException {
		return pushDao.addPushType(fMap);
	}

	@Override
	public BrbMap<Object, Object> getApp() {
		return pushDao.getApp();
	}

	@Override
	public void insertPushLog(List<BrbMap<Object, Object>> bMap) {
		pushDao.insertPushLog(bMap);
	}
}