package com.brb.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brb.app.dao.PolyDao;
import com.brb.app.model.PolyVo;
import com.brb.brbcom.common.collections.BrbMap;


@Service("PolyService")
public class PolyServiceImpl implements PolyService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PolyServiceImpl.class);

	@Autowired
	PolyDao polyDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<BrbMap<Object, Object>> getPoly(){
		return polyDao.getPoly();
	}

	@Override
	public BrbMap<Object, Object> getPolyList(BrbMap<Object, Object> bMap){
		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("polyList", polyDao.getPolyList(bMap));
		resMap.put("totalPage", polyDao.getPolyTotal(bMap));
		return resMap;
	}

	@Override
	public BrbMap<Object, Object> getPolyDet(int polySq){
		return polyDao.getPolyDet(polySq);
	}

	@Override
	public void updatePoly(PolyVo pvo){
		polyDao.updatePoly(pvo);
	}

	@Override
	public void insertPoly(PolyVo pvo){
		polyDao.insertPoly(pvo);
	}

	@Override
	public int checkPoly(PolyVo pvo) {
		return polyDao.checkPoly(pvo);
	}
}