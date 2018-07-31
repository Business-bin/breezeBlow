package com.brb.status.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.status.dao.StatusDao;


@Service("StatusService")
public class StatusServiceImpl implements StatusService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StatusServiceImpl.class);

	@Autowired
	StatusDao statusDao;

	@Override
	public BrbMap<Object, Object> getStatus(String location) throws DataAccessException {
		return statusDao.getStatus(location);
	}
}