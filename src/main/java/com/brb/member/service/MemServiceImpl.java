package com.brb.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brb.brbcom.common.collections.BrbMap;
import com.brb.member.dao.MemDao;
import com.brb.member.model.DataVo;
import com.brb.member.model.MemVo;


@Service("MemService")
public class MemServiceImpl implements MemService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(MemServiceImpl.class);

	@Autowired
	MemDao memDao;

	@Override
	public BrbMap<Object, Object> getMemList(BrbMap<Object, Object> bMap){
		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("memList", memDao.getMemList(bMap));
		resMap.put("totalPage", memDao.getMemTotal(bMap));
		return resMap;
	}

	@Override
	public BrbMap<Object, Object> getMemDet(int memSq){
		BrbMap<Object, Object> brbMap = memDao.getMem(memSq);
		String [] hp = ((String)brbMap.get("USER_HP")).split("-");
		brbMap.put("hp1", hp[0]);
		brbMap.put("hp2", hp[1]);
		brbMap.put("hp3", hp[2]);
		String bir = brbMap.get("MEM_BIR").toString().substring(0, 4)+"."+brbMap.get("MEM_BIR").toString().substring(4, 6)+"."+brbMap.get("MEM_BIR").toString().substring(6, 8);
		brbMap.put("MEM_BIR", bir);
		return brbMap;
	}

	@Override
	public void updateMem(MemVo bvo){
		bvo.setUserHp(bvo.getHp1()+"-"+bvo.getHp2()+"-"+bvo.getHp3());
		bvo.setMemBir(bvo.getMemBir().replace(".", ""));
		memDao.updateMem(bvo);
	}

	@Override
	public void updatePwd(MemVo bvo) {
		memDao.updatePwd(bvo);
	}

	@Override
	public void deleteMem(MemVo bvo){
		memDao.deleteMem(bvo);
	}

	@Override
	public BrbMap<Object, Object> getLogList(BrbMap<Object, Object> bMap){
		BrbMap<Object, Object> brbMap = new BrbMap<Object, Object>();
		brbMap.put("logList", memDao.getLogList(bMap));
		brbMap.put("totalPage", memDao.getLogTotal(bMap));
		return brbMap;
	}

	@Override
	public void modProduct(BrbMap<Object, Object> bMap) {
		memDao.modProduct(bMap);
	}

	@Override
	public void delProduct(BrbMap<Object, Object> bMap) {
		memDao.delProduct(bMap);
	}

	@Override
	public void regProduct(BrbMap<Object, Object> bMap) {
		memDao.regProduct(bMap);
	}

	@Override
	public BrbMap<Object, Object> getHpList(){
		BrbMap<Object, Object> brbMap = new BrbMap<Object, Object>();
		brbMap.put("hpList", memDao.getHpList());
		return brbMap;
	}

	@Override
	public List<BrbMap<Object, Object>> getBtbsList(){
		return memDao.getBtbsList();
	}

	@Override
	public List<BrbMap<Object, Object>> getSdList(){
		return memDao.getSdList();
	}

	@Override
	public List<BrbMap<Object, Object>> getSggList(String code) {
		return memDao.getSggList(code);
	}

	@Override
	public List<BrbMap<Object, Object>> getUmdList(String code) {
		return memDao.getUmdList(code);
	}

	@Override
	public List<BrbMap<Object, Object>> getBtbList(BrbMap<Object, Object> dMap) {
		return memDao.getBtbList(dMap);
	}

	@Override
	public Integer getBtbListCnt(BrbMap<Object, Object> dMap) {
		return memDao.getBtbListCnt(dMap);
	}

	@Override
	public List<BrbMap<Object, Object>> getProdList(BrbMap<Object, Object> bMap) {
		return memDao.getProdList(bMap);
	}

	@Override
	public Integer getProdTotal(int memSq) {
		return memDao.getProdTotal(memSq);
	}

	@Override
	public List<BrbMap<Object, Object>> getProdSearch() {
		return memDao.getProdSearch();
	}

	@Override
	public List<DataVo> getTimeData() {
		return memDao.getTimeData();
	}

	@Override
	public List<BrbMap<Object, Object>> getProdStat(MemVo mvo) {
		return memDao.getProdStat(mvo);
	}

	@Override
	public List<BrbMap<Object, Object>> getLocStat(MemVo mvo) {
		return memDao.getLocStat(mvo);
	}

	@Override
	public List<BrbMap<Object, Object>> getAgeStat(MemVo mvo) {
		return memDao.getAgeStat(mvo);
	}

	@Override
	public BrbMap<Object, Object> getGenStat(MemVo mvo) {
		return memDao.getGenStat(mvo);
	}
}