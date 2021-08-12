package com.brb.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.brb.board.dao.BoardDao;
import com.brb.brbcom.common.collections.BrbMap;


@Service("BoardService")
public class BoardServiceImpl implements BoardService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Autowired
	BoardDao boardDao;



	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getNoticeList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.getNoticeList(fMap);
	}


	@Override
	public BrbMap<Object, Object> getNoticeListCnt(BrbMap<Object, Object> fMap) throws DataAccessException {

		return boardDao.getNoticeListCnt(fMap);
	}

	@Override
	public BrbMap<Object, Object> getNotice(BrbMap<Object, Object> fMap) throws DataAccessException {

		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("notice", boardDao.getNotice(fMap));
		return resMap;
	}

	@Override
	public int addNotice(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.addNotice(fMap);
	}

	@Override
	public int modNotice(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.modNotice(fMap);
	}

	@Override
	public int delNotice(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.delNotice(fMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getQnaList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.getQnaList(fMap);
	}


	@Override
	public BrbMap<Object, Object> getQnaListCnt(BrbMap<Object, Object> fMap) throws DataAccessException {

		return boardDao.getQnaListCnt(fMap);
	}

	@Override
	public BrbMap<Object, Object> getQna(BrbMap<Object, Object> fMap) throws DataAccessException {

		return boardDao.getQna(fMap);
	}

	@Override
	public int modQna(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.modQna(fMap);
	}

	@Override
	public int delQna(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.delQna(fMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getFaqList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.getFaqList(fMap);
	}


	@Override
	public BrbMap<Object, Object> getFaqListCnt(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.getFaqListCnt(fMap);
	}

	@Override
	public BrbMap<Object, Object> getFaq(BrbMap<Object, Object> fMap) throws DataAccessException {
		BrbMap<Object, Object> resMap = new BrbMap<>();
		resMap.put("faq", boardDao.getFaq(fMap));
		return resMap;
	}

	@Override
	public int addFaq(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.addFaq(fMap);
	}

	@Override
	public int modFaq(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.modFaq(fMap);
	}

	@Override
	public int delFaq(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.delFaq(fMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getCateList() throws DataAccessException {
		return boardDao.getCateList();
	}

	@Override
	public int addFileInfo(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.addFileInfo(fMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getFileList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.getFileList(fMap);
	}

	@Override
	public int delFile(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.delFile(fMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getDList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.getDList(fMap);
	}

	@Override
	public int modComment(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.modComment(fMap);
	}

	@Override
	public int addComment(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.addComment(fMap);
	}

	@Override
	public int delComment(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.delComment(fMap);
	}

	@Override
	public int modReadCnt(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.modReadCnt(fMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getAsList(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.getAsList(fMap);
	}


	@Override
	public BrbMap<Object, Object> getAsListCnt(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.getAsListCnt(fMap);
	}

	@Override
	public BrbMap<Object, Object> getAs(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.getAs(fMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<BrbMap> getStatList() throws DataAccessException {
		return boardDao.getStatList();
	}

	@Override
	public int modAs(BrbMap<Object, Object> fMap) throws DataAccessException {
		return boardDao.modAs(fMap);
	}
	
	@Override
	public List<BrbMap<Object, Object>> getModel(BrbMap<Object, Object> dMap){
		return boardDao.getModel(dMap);
	}

	@Override
	public void addAs(BrbMap<Object, Object> bMap) {
		boardDao.addAs(bMap);
	}
}