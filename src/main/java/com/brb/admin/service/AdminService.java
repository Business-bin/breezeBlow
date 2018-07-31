package com.brb.admin.service;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.brb.brbcom.common.collections.BrbMap;

/**
 *
 * @author back
 *
 */
public interface AdminService {

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getAdminList(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getAdminListCnt(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getAdmin(BrbMap<Object, Object> fMap) throws DataAccessException;


	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addAdmin(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modAdmin(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delAdmin(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getAdminLogList(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getAdminLogListCnt(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> checkId(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int changePwd(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getChartList(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getSiList() throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getGuList(BrbMap<Object, Object> fMap) throws DataAccessException;


}
