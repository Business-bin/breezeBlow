package com.brb.app.service;

import java.util.List;

import com.brb.app.model.AppVo;
import com.brb.brbcom.common.collections.BrbMap;

/**
 *
 * @author back
 *
 */
public interface AppService {

	/**
	 * APP버전 리스트
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getAppVrsList(BrbMap<Object, Object> bMap);

	/**
	 * APP버전 상세
	 * @param String
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getAppVrsDet(int appSq);

	/**
	 * APP버전 코드
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getAppStat();

	/**
	 * APP버전 수정
	 * @param AppVo
	 * @return
	 * @throws
	 */
	public void updateAppVrs(AppVo avo);

	/**
	 * APP버전 등록
	 * @param AppVo
	 * @return
	 * @throws
	 */
	public void insertAppVrs(AppVo avo);


	/**
	 * 기기별 통계
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getDeviceList(BrbMap<Object, Object> fMap);

	/**
	 * os별 통계
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getOsList(BrbMap<Object, Object> fMap);


	/**
	 * 연령대별통계
	 * @param
	 * @return List<BrbMap<Object, Object>>
	 * @throws
	 */
	public List<BrbMap<Object, Object>> getAgeList(BrbMap<Object, Object> fMap);

	/**
	 * 튜토리얼 리스트
	 * @param BrbMap<Object, Object>
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getTutorialList(BrbMap<Object, Object> bMap);

	/**
	 * 튜토리얼 상세
	 * @param int
	 * @return BrbMap<Object, Object>
	 * @throws
	 */
	public BrbMap<Object, Object> getTutorialDet(int tutSq);


	/**
	 * 튜토리얼 수정
	 * @param BrbMap
	 * @return
	 * @throws
	 */
	public void updateTutorial(BrbMap<Object, Object> bMap);

	/**
	 * 튜토리얼 등록
	 * @param BrbMap
	 * @return
	 * @throws
	 */
	public void insertTutorial(BrbMap<Object, Object> bMap);

	/**
	 * 튜토리얼 수정
	 * @param BrbMap
	 * @return
	 * @throws
	 */
	public void deleteTutorial(BrbMap<Object, Object> bMap);

	/**
	 * 튜토리얼 노출개수
	 * @param BrbMap
	 * @return
	 * @throws
	 */
	public int checkTutorial(int tutSq);
}
