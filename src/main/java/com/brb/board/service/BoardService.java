package com.brb.board.service;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.brb.brbcom.common.collections.BrbMap;

/**
 *
 * @author back
 *
 */
public interface BoardService {

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getNoticeList(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getNoticeListCnt(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getNotice(BrbMap<Object, Object> fMap) throws DataAccessException;


	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addNotice(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modNotice(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delNotice(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getQnaList(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getQnaListCnt(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getQna(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modQna(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delQna(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getFaqList(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getFaqListCnt(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getFaq(BrbMap<Object, Object> fMap) throws DataAccessException;


	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addFaq(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modFaq(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delFaq(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getCateList() throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addFileInfo(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getFileList(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delFile(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getDList(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modComment(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int addComment(BrbMap<Object, Object> fMap) throws DataAccessException;
	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int delComment(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modReadCnt(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getAsList(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getAsListCnt(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public BrbMap<Object, Object> getAs(BrbMap<Object, Object> fMap) throws DataAccessException;

	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List<BrbMap> getStatList() throws DataAccessException;
	/**
	 *
	 * @param fMap
	 * @return
	 * @throws DataAccessException
	 */
	public int modAs(BrbMap<Object, Object> fMap) throws DataAccessException;

}

