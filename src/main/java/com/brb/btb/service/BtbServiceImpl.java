package com.brb.btb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.btb.dao.BtbDao;
import com.brb.brbcom.common.collections.BrbMap;


@Service("BtbService")
public class BtbServiceImpl implements BtbService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BtbServiceImpl.class);
	
	@Autowired
	BtbDao btbDao;
	
	

	@Override
	public List<BrbMap> getBtbList(BrbMap<Object, Object> fMap) throws DataAccessException {
		
		return btbDao.getBtbList(fMap);
	}
	

	@Override
	public BrbMap<Object, Object> getBtbListCnt(BrbMap<Object, Object> fMap) throws DataAccessException {

		return btbDao.getBtbListCnt(fMap);
	}
	
	@Override
	public BrbMap<Object, Object> getBtb(BrbMap<Object, Object> fMap) throws DataAccessException {
		
		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("btb", btbDao.getBtb(fMap));
		return resMap;
	}
	
	@Override
	public int addBtb(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.addBtb(fMap);
	}
	
	@Override
	public BrbMap<Object, Object> getBtbSq() throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.getBtbSq();
	}	

	@Override
	public int modBtb(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.modBtb(fMap);
	}
	
	@Override
	public int delBtb(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.delBtb(fMap);
	}
	
	@Override
	public int goStop(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.goStop(fMap);
	}
	
	@Override
	public int goUse(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.goUse(fMap);
	}
	
	@Override
	public List<BrbMap> getAdminList(BrbMap<Object, Object> fMap) throws DataAccessException {
		
		return btbDao.getAdminList(fMap);
	}
	

	@Override
	public BrbMap<Object, Object> getAdminListCnt(BrbMap<Object, Object> fMap) throws DataAccessException {
		
		return btbDao.getAdminListCnt(fMap);
	}	
	
	
	@Override
	public List<BrbMap> getAdminLogList(BrbMap<Object, Object> fMap) throws DataAccessException {

		return btbDao.getAdminLogList(fMap);
	}

	@Override
	public BrbMap<Object, Object> getAdminLogListCnt(BrbMap<Object, Object> fMap) throws DataAccessException {
		
		return btbDao.getAdminLogListCnt(fMap);
	}
	
	@Override
	public BrbMap<Object, Object> getAdmin(BrbMap<Object, Object> fMap) throws DataAccessException {
		
		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("admin", btbDao.getAdmin(fMap));
		return resMap;
	}
	
	@Override
	public int addAdmin(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.addAdmin(fMap);
	}

	@Override
	public int modAdmin(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.modAdmin(fMap);
	}
	
	@Override
	public int delAdmin(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.delAdmin(fMap);
	}	
	
	@Override
	public List<BrbMap> getBtbNmList() throws DataAccessException {
		
		return btbDao.getBtbNmList();
	}
	
	@Override
	public BrbMap<Object, Object> checkBtbNm(BrbMap<Object, Object> fMap) throws DataAccessException {

		return btbDao.checkBtbNm(fMap);
	}
	
	@Override
	public int goAdminStop(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.goAdminStop(fMap);
	}
	
	@Override
	public int goAdminUse(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return btbDao.goAdminUse(fMap);
	}

	@Override
	public List<BrbMap> getHpTelList() throws DataAccessException {
		
		return btbDao.getHpTelList();
	}
	
	@Override
	public List<BrbMap> getTemplList() throws DataAccessException {
		
		return btbDao.getTemplList();
	}	
}