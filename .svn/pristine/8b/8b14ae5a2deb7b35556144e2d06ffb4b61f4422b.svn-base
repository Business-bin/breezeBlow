package com.brb.status.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;

@Repository("StatusDao")
public class StatusDao{

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;

	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;


	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(StatusDao.class);

	/**
	 *
	 * @param String
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getStatus(String location)
			throws DataAccessException {
		BrbMap<String, String> bMap = new BrbMap<>();
		String curTime = (String)queryS.selectOne("status.getMaxTime", location);
		bMap.put("curTime", curTime);
		bMap.put("location", location);
		return (BrbMap<Object, Object>)queryS.selectOne("status.getStatusList", bMap);
	}
}
