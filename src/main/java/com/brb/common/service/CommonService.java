package com.brb.common.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;

import com.brb.brbcom.common.collections.BrbMap;

/**
 *
 * @author back
 *
 */
public interface CommonService {


	/**
	 * 관리자 로그인 로그
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addAdminLog(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 * 관리자 액션 로그
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addAdminActLog(HttpServletRequest request) throws DataAccessException;

	/**
	 * 핸드폰번호
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getHpList() throws DataAccessException;

	/**
	 * 전화번호
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getTelList() throws DataAccessException;

	/**
	 * 이메일 발송 로그 저장
	 * @param BrbMap
	 * @return
	 */
	public void insertEmailLog(BrbMap<Object, Object> bMap);

	/**
	 * 이메일 환경데이터
	 * @param BrbMap
	 * @return
	 */
	public BrbMap<Object, Object> getEnvInfo();

}
