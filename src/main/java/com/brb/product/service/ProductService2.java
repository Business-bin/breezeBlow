package com.brb.product.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.product.model.MdVo;
import com.brb.product.model.FwrVo;
import com.brb.product.model.PprtVo;
/**
 *
 * @author back
 *
 */
public interface ProductService2 {

	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getChartList(BrbMap<Object, Object> fMap) throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getSiList() throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getBtbList() throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getAgeList() throws DataAccessException;
	
	/**
	 * 
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public List<BrbMap> getSensorTypeList() throws DataAccessException;	
}
