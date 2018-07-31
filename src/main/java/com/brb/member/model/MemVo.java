package com.brb.member.model;

public class MemVo{
	private Integer memSq; // 회원고유번호(일련번호)
	private String memEmail; // 회원이메일
	private String memPwd; // 회원암호
	private String memNm; // 회원이름
	private String rcntConDttm; // 최종접속일시
	private String firstLoginYn; // 최초로그인여부
	private String memGen; // 회원성별
	private String memKind; // 회원구분(senko, btb)
	private String memBir; // 회원생년월일
	private String zip; // 우편번호
	private String addr1; // 시/도
	private String addr2; // 시/군/구
	private String addr3; // 읍/면/동
	private String addr4; // 상세
	private String addr5; // 예비
	private String hp1;
	private String hp2;
	private String hp3;
	private String userHp; // 핸드폰번호
	private String agrUseYn; // 이용약관 동의여부
	private String agrPpYn; // 개인정보보호정책 동의여부
	private String agrLiYn; // 위치정보 동의여부
	private String agrRmYn; // 마케팅수신 동의여부
	private String agrPushYn; // 푸쉬서비스 동의여부
	private String agrSmsYn; // SMS서비스 동의여부
	private String stat; // 상태코드
	private String delRsn; // 삭제사유
	private String delAdmEmail; // 삭제관리자 이메일
	private String adrInfo; // 안드로이드정보
	private String iosInfo; // IOS정보
	private String secedeDttm; // 탈퇴일시
	private String secedeRsn; // 탈퇴사유
	private String regDttm; // 등록일시
	private String uptDttm; // 수정일시
	private String delDttm; // 삭제일시
	private Integer regAdmSq; // 등록회원고유번호(일련번호)
	private Integer uptAdmSq; // 수정회원고유번호(일련번호)
	private Integer delAdmSq; // 삭제회원고유번호(일련번호)
	private Integer btbsSq; // BTBSITE 고유번호
	private String btbsNm; // BTB 고객명
	private String startDate; // 등록일검색
	private String endDate; // 등록일검색
	private String gubun;
	private int idx;
	private String prodNm; // 제품, 모델명

	public Integer getMemSq() {
		return memSq;
	}
	public void setMemSq(Integer memSq) {
		this.memSq = memSq;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public String getMemNm() {
		return memNm;
	}
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	public String getRcntConDttm() {
		return rcntConDttm;
	}
	public void setRcntConDttm(String rcntConDttm) {
		this.rcntConDttm = rcntConDttm;
	}
	public String getFirstLoginYn() {
		return firstLoginYn;
	}
	public void setFirstLoginYn(String firstLoginYn) {
		this.firstLoginYn = firstLoginYn;
	}
	public String getMemGen() {
		return memGen;
	}
	public void setMemGen(String memGen) {
		this.memGen = memGen;
	}
	public String getMemKind() {
		return memKind;
	}
	public void setMemKind(String memKind) {
		this.memKind = memKind;
	}
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getAddr3() {
		return addr3;
	}
	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}
	public String getAddr4() {
		return addr4;
	}
	public void setAddr4(String addr4) {
		this.addr4 = addr4;
	}
	public String getAddr5() {
		return addr5;
	}
	public void setAddr_5(String addr5) {
		this.addr5 = addr5;
	}
	public String getHp1() {
		return hp1;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	public String getHp2() {
		return hp2;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	public String getHp3() {
		return hp3;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	public String getUserHp() {
		return userHp;
	}
	public void setUserHp(String userHp) {
		this.userHp = userHp;
	}
	public String getAgrUseYn() {
		return agrUseYn;
	}
	public void setAgrUseYn(String agrUseYn) {
		this.agrUseYn = agrUseYn;
	}
	public String getAgrPpYn() {
		return agrPpYn;
	}
	public void setAgrPpYn(String agrPpYn) {
		this.agrPpYn = agrPpYn;
	}
	public String getAgrLiYn() {
		return agrLiYn;
	}
	public void setAgrLiYn(String agrLiYn) {
		this.agrLiYn = agrLiYn;
	}
	public String getAgrRmYn() {
		return agrRmYn;
	}
	public void setAgrRmYn(String agrRmYn) {
		this.agrRmYn = agrRmYn;
	}
	public String getAgrPushYn() {
		return agrPushYn;
	}
	public void setAgrPushYn(String agrPushYn) {
		this.agrPushYn = agrPushYn;
	}
	public String getAgrSmsYn() {
		return agrSmsYn;
	}
	public void setAgrSmsYn(String agrSmsYn) {
		this.agrSmsYn = agrSmsYn;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getDelRsn() {
		return delRsn;
	}
	public void setDelRsn(String delRsn) {
		this.delRsn = delRsn;
	}
	public String getDelAdmEmail() {
		return delAdmEmail;
	}
	public void setDelAdmEmail(String delAdmEmail) {
		this.delAdmEmail = delAdmEmail;
	}
	public String getAdrInfo() {
		return adrInfo;
	}
	public void setAdrInfo(String adrInfo) {
		this.adrInfo = adrInfo;
	}
	public String getIosInfo() {
		return iosInfo;
	}
	public void setIosInfo(String iosInfo) {
		this.iosInfo = iosInfo;
	}
	public String getSecedeDttm() {
		return secedeDttm;
	}
	public void setSecedeDttm(String secedeDttm) {
		this.secedeDttm = secedeDttm;
	}
	public String getSecedeRsn() {
		return secedeRsn;
	}
	public void setSecedeRsn(String secedeRsn) {
		this.secedeRsn = secedeRsn;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getUptDttm() {
		return uptDttm;
	}
	public void setUptDttm(String uptDttm) {
		this.uptDttm = uptDttm;
	}
	public String getDelDttm() {
		return delDttm;
	}
	public void setDelDttm(String delDttm) {
		this.delDttm = delDttm;
	}
	public Integer getRegAdmSq() {
		return regAdmSq;
	}
	public void setRegAdmSq(Integer regAdmSq) {
		this.regAdmSq = regAdmSq;
	}
	public Integer getUptAdmSq() {
		return uptAdmSq;
	}
	public void setUptAdmSq(Integer uptAdmSq) {
		this.uptAdmSq = uptAdmSq;
	}
	public Integer getDelAdmSq() {
		return delAdmSq;
	}
	public void setDelAdmSq(Integer delAdmSq) {
		this.delAdmSq = delAdmSq;
	}
	public Integer getBtbsSq() {
		return btbsSq;
	}
	public void setBtbsSq(Integer btbsSq) {
		this.btbsSq = btbsSq;
	}
	public String getBtbsNm() {
		return btbsNm;
	}
	public void setBtbsNm(String btbsNm) {
		this.btbsNm = btbsNm;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
}
