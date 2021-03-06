package com.brb.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.brbcom.common.util.Util;
import com.brb.common.dao.CommonDao;


@Service("CommonService")
public class CommonServiceImpl implements CommonService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	CommonDao commonDao;

	/**
	 * 관리자 로그인 로그
	 */
	@Override
	public int addAdminLog(BrbMap<Object, Object> fMap) throws DataAccessException {
		return commonDao.addAdminLog(fMap);
	}

	/**
	 * 관리자 액션로그
	 */
	@Override
	public int addAdminActLog(HttpServletRequest request) throws DataAccessException {
		HttpSession session = request.getSession();
		//action log 저장
		BrbMap<Object, Object> logActMap = new BrbMap<>();
		try{
			logActMap.put("R_ADM_EMAIL", session.getAttribute("ADM_EMAIL"));
			logActMap.put("R_CON_ENV",session.getAttribute("OSNAME")+" : "+ Util.getBrowser(request));
			logActMap.put("R_IP", session.getAttribute("R_IP"));
			logActMap.put("R_CUR_URL", session.getAttribute("URL"));
			logActMap.put("R_PATH", request.getRequestURI());
			logActMap.put("R_REFFER", session.getAttribute("REFERER") );
		} catch(Exception e){
			e.printStackTrace();
		}
		return commonDao.addAdminActLog(logActMap);
	}

	/**
	 * 핸드폰번호
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BrbMap> getHpList() throws DataAccessException {
		return commonDao.getHpList();
	}

	/**
	 * 전화번호
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getTelList() throws DataAccessException {
		return commonDao.getTelList();
	}

	@Override
	public void insertEmailLog(BrbMap<Object, Object> bMap) {
		commonDao.insertEmailLog(bMap);
	}

	@Override
	public BrbMap<Object, Object> getEnvInfo() {
		return commonDao.getEnvInfo();
	}
}