package com.brb.product.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.product.dao.ProductDao;
import com.brb.product.dao.ProductDao2;
import com.brb.product.model.MdVo;
import com.brb.product.model.FwrVo;
import com.brb.product.model.PprtVo;


@Service("ProductService2")
public class ProductServiceImpl2 implements ProductService2{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl2.class);

	@Autowired
	ProductDao2 productDao2;
	
	@Override
	public List<BrbMap> getChartList(BrbMap<Object, Object> fMap) throws DataAccessException {
		
		return productDao2.getChartList(fMap);
	}
	@Override
	public List<BrbMap> getSiList() throws DataAccessException {
		
		return productDao2.getSiList();
	}
	
	@Override
	public List<BrbMap> getBtbList() throws DataAccessException {
		
		return productDao2.getBtbList();
	}	
	
	@Override
	public List<BrbMap> getAgeList() throws DataAccessException {
		
		return productDao2.getAgeList();
	}	
	
	@Override
	public List<BrbMap> getSensorTypeList() throws DataAccessException {
		
		return productDao2.getSensorTypeList();
	}		
	
}