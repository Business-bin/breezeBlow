package com.brb.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.admin.dao.AdminDao;
import com.brb.brbcom.common.collections.BrbMap;


@Service("AdminService")
public class AdminServiceImpl implements AdminService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	AdminDao adminDao;



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getAdminList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return adminDao.getAdminList(fMap);
	}


	@Override
	public BrbMap<Object, Object> getAdminListCnt(BrbMap<Object, Object> fMap) throws DataAccessException {
		return adminDao.getAdminListCnt(fMap);
	}

	@Override
	public BrbMap<Object, Object> getAdmin(BrbMap<Object, Object> fMap) throws DataAccessException {

		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("admin", adminDao.getAdmin(fMap));
		return resMap;
	}

	@Override
	public int addAdmin(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return adminDao.addAdmin(fMap);
	}

	@Override
	public int modAdmin(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return adminDao.modAdmin(fMap);
	}

	@Override
	public int delAdmin(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return adminDao.delAdmin(fMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getAdminLogList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return adminDao.getAdminLogList(fMap);
	}

	@Override
	public BrbMap<Object, Object> getAdminLogListCnt(BrbMap<Object, Object> fMap) throws DataAccessException {

		return adminDao.getAdminLogListCnt(fMap);
	}

	@Override
	public BrbMap<Object, Object> checkId(BrbMap<Object, Object> fMap) throws DataAccessException {

		return adminDao.checkId(fMap);
	}

	@Override
	public int changePwd(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return adminDao.changePwd(fMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getChartList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return adminDao.getChartList(fMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getSiList() throws DataAccessException {
		return adminDao.getSiList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getGuList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return adminDao.getGuList(fMap);
	}

}