package com.brb.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.login.dao.LoginDao;


@Service("LoginService")
public class LoginServiceImpl implements LoginService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	LoginDao loginDao;

	@Override
	public BrbMap getUser(BrbMap<Object, Object> fMap) throws DataAccessException {
		return loginDao.getUser(fMap);
	}

	@Override
	public int addUser(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modUser(BrbMap<Object, Object> fMap) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modPassword(BrbMap<Object, Object> fMap) throws DataAccessException {
		return loginDao.modPassword(fMap);
	}

	@Override
	public BrbMap<?, ?> getCheckUser(BrbMap<Object, Object> fMap) throws DataAccessException {
		return loginDao.getCheckUser(fMap);
	}


	
	

}