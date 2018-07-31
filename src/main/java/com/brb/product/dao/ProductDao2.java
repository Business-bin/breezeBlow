package com.brb.product.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.product.model.MdVo;
import com.brb.product.model.FwrVo;
import com.brb.product.model.PprtVo;

@Repository("ProductDao2")
public class ProductDao2{

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;

	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getChartList(BrbMap<Object, Object> pdMap) 
			throws DataAccessException {
		return queryS.selectList("product2.getChartList",pdMap);
	}
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getSiList() 
			throws DataAccessException {
		return queryS.selectList("product2.getSiList");
	}	
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getBtbList() 
			throws DataAccessException {
		return queryS.selectList("product2.getBtbList");
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getAgeList() 
			throws DataAccessException {
		return queryS.selectList("product2.getAgeList");
	}
	
	/**
	 * 
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getSensorTypeList() 
			throws DataAccessException {
		return queryS.selectList("product2.getSensorTypeList");
	}	
}
