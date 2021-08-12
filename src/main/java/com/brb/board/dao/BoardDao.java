package com.brb.board.dao;

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

@Repository("BoardDao")
public class BoardDao{

	@Autowired
	@Resource(name="sqlSessionTemplate")
	private SqlSession queryM;

	@Autowired
	@Resource(name="sqlSessionTemplate_s")
	private SqlSession queryS;


	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getNoticeList(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("board.getNoticeList",pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getNoticeListCnt(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("board.getNoticeListCnt",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getNotice(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("board.getNotice",pdMap);
	}

//	/**
//	 *
//	 * @param pdMap
//	 * @return
//	 * @throws DataAccessException
//	 */
//	public int addNotice(BrbMap<Object, Object> pdMap)
//			throws DataAccessException {
//		queryM.insert("board.addNotice", pdMap);
//
//		int bd_sq = Integer.parseInt(pdMap.get("bd_sq").toString());
//		System.out.println("bd_sq>>>>>>>>>>>>>>>>"+bd_sq);
//
//		return bd_sq;
//	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addNotice(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		int cnt = queryM.insert("board.addNotice", pdMap);
		queryM.insert("board.insertBdLog02", pdMap);
		return cnt;
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modNotice(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		int cnt = queryM.update("board.modNotice", pdMap);
		queryM.insert("board.insertBdLog02", pdMap);
		return cnt;
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delNotice(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		int cnt = queryM.update("board.delNotice", pdMap);
		queryM.insert("board.insertBdLog02", pdMap);
		return cnt;
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getQnaList(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("board.getQnaList",pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getQnaListCnt(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("board.getQnaListCnt",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getQna(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("board.getQna",pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addQna(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.insert("board.addQna", pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	/*public int qnaReply(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
//		return queryM.insert("board.qnaReply", pdMap);
		queryM.insert("board.qnaReply", pdMap);

		int seq = Integer.parseInt(pdMap.get("bd_ans_sq").toString());
		System.out.println("seq>>>>>>>>>>>>>>>>"+seq);
		queryM.insert("board.insertBdLog04", pdMap);
		return seq;
	}*/

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modQna(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		int cnt = queryM.update("board.modQna", pdMap);
		queryM.insert("board.insertBdLog04", pdMap);
		return cnt;
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delQna(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		int cnt = queryM.update("board.delQna", pdMap);
		queryM.insert("board.insertBdLog04", pdMap);
		return cnt;
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getFaqList(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("board.getFaqList",pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getFaqListCnt(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("board.getFaqListCnt",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getFaq(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("board.getFaq",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addFaq(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		int cnt = queryM.insert("board.addFaq", pdMap);
		queryM.insert("board.insertBdLog03", pdMap);
		return cnt;
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modFaq(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		int cnt = queryM.update("board.modFaq", pdMap);
		queryM.insert("board.insertBdLog03", pdMap);
		return cnt;
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delFaq(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		int cnt = queryM.update("board.delFaq", pdMap);
		queryM.insert("board.insertBdLog03", pdMap);
		return cnt;
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getCateList()
			throws DataAccessException {
		return queryS.selectList("board.getCateList");
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addFileInfo(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.insert("board.addFileInfo", pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getFileList(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("board.getFileList", pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delFile(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.update("board.delFile", pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getDList(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("board.getDList", pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modComment(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.update("board.modComment", pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addComment(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.update("board.addComment", pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delComment(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.update("board.delComment", pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modReadCnt(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryM.update("board.modReadCnt", pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getAsList(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return queryS.selectList("board.getAsList",pdMap);
	}


	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getAsListCnt(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("board.getAsListCnt",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public BrbMap<Object, Object> getAs(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		return (BrbMap<Object, Object>) queryS.selectOne("board.getAs",pdMap);
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List getStatList()
			throws DataAccessException {
		return queryS.selectList("board.getStatList");
	}

	/**
	 *
	 * @param pdMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modAs(BrbMap<Object, Object> pdMap)
			throws DataAccessException {
		int cnt = queryM.insert("board.modAs", pdMap);
		queryM.insert("board.insertBdLog01", pdMap);
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	public List<BrbMap<Object, Object>> getModel(BrbMap<Object, Object> dMap){
		return queryS.selectList("board.getModel", dMap);
	}
	
	public void addAs(BrbMap<Object, Object> bMap) throws DataAccessException {
		queryM.insert("board.addAs", bMap);
	}
}
