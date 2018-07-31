package com.brb.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.app.dao.AppDao;
import com.brb.app.model.AppVo;
import com.brb.brbcom.common.collections.BrbMap;


@Service("AppService")
public class AppServiceImpl implements AppService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

	@Autowired
	AppDao appDao;

	@Override
	public BrbMap<Object, Object> getAppVrsList(BrbMap<Object, Object> bMap) throws DataAccessException {
		BrbMap<Object, Object> rMap = new BrbMap<Object, Object>();
		rMap.put("appVrsList", appDao.getAppVrsList(bMap));
		rMap.put("totalPage", appDao.getAppVrsTotal(bMap));
		return rMap;
	}

	@Override
	public BrbMap<Object, Object> getAppVrsDet(int appSq) throws DataAccessException {
		return appDao.getAppVrsDet(appSq);
	}

	@Override
	public List<BrbMap<Object, Object>> getAppStat() throws DataAccessException {
		return appDao.getAppStat();
	}

	@Override
	public void updateAppVrs(AppVo avo) throws DataAccessException {
		appDao.updateAppVrs(avo);
	}

	@Override
	public void insertAppVrs(AppVo avo) throws DataAccessException {
		appDao.insertAppVrs(avo);
	}

	@Override
	public List<BrbMap<Object, Object>> getDeviceList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return appDao.getDeviceList(fMap);
	}

	@Override
	public List<BrbMap<Object, Object>> getOsList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return appDao.getOsList(fMap);
	}

	@Override
	public List<BrbMap<Object, Object>> getAgeList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return appDao.getAgeList(fMap);
	}

	@Override
	public BrbMap<Object, Object> getTutorialList(BrbMap<Object, Object> bMap) {
		BrbMap<Object, Object> rMap = new BrbMap<Object, Object>();
		rMap.put("tutorialList", appDao.getTutorialList(bMap));
		rMap.put("totalPage", appDao.getTutorialTotal(bMap));
		return rMap;
	}

	@Override
	public BrbMap<Object, Object> getTutorialDet(int tutSq) {
		return appDao.getTutorialDet(tutSq);
	}

	@Override
	public void updateTutorial(BrbMap<Object, Object> bMap) {
		appDao.updateTutorial(bMap);
	}

	@Override
	public void insertTutorial(BrbMap<Object, Object> bMap) {
		appDao.insertTutorial(bMap);
	}

	@Override
	public void deleteTutorial(BrbMap<Object, Object> bMap) {
		appDao.deleteTutorial(bMap);
	}

	@Override
	public int checkTutorial(int tutSq) {
		return appDao.checkTutorial(tutSq);
	}
}