package com.brb.push.service;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.brb.brbcom.common.collections.BrbMap;

/**
 *
 * @author back
 *
 */
public interface PushService {

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getPushList(BrbMap<Object, Object> fMap) throws DataAccessException;

	@SuppressWarnings("rawtypes")
	public BrbMap getPushListTotal(BrbMap<Object, Object> fMap) throws DataAccessException;

	@SuppressWarnings("rawtypes")
	public List<BrbMap> getMdList(BrbMap<Object, Object> fMap) throws DataAccessException;

	@SuppressWarnings("rawtypes")
	public BrbMap getMdListTotal(BrbMap<Object, Object> fMap) throws DataAccessException;

	@SuppressWarnings("rawtypes")
	public List<BrbMap> getAddrCode(BrbMap<Object, Object> fMap) throws DataAccessException;

	@SuppressWarnings("rawtypes")
	public List<BrbMap> getBtbSite(BrbMap<Object, Object> fMap) throws DataAccessException;

	@SuppressWarnings("rawtypes")
	public BrbMap pushDetInfo(BrbMap<Object, Object> fMap) throws DataAccessException;

	@SuppressWarnings("rawtypes")
	public BrbMap getSendCnt(BrbMap<Object, Object> fMap) throws DataAccessException;

	public List<BrbMap<Object, Object>> getSendListInfo(BrbMap<Object, Object> fMap) throws DataAccessException;

	public int addPush(BrbMap<Object, Object> fMap) throws DataAccessException;

	public int addPushType(BrbMap<Object, Object> fMap) throws DataAccessException;

	public BrbMap<Object, Object> getApp();

	public void insertPushLog(List<BrbMap<Object, Object>> bMap);
}
