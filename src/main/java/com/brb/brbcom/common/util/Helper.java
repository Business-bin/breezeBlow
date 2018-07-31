package com.brb.brbcom.common.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author back
 *
 */
public class Helper 
{
	private static final Logger logger = LoggerFactory.getLogger(Helper.class);
	
	public static String currentDate()
	{
		return currentDate("yyyy-MM-dd a HH:mm");
	}
	
	private static void setError(String methodName,String errMsg)
	{
		logger.debug("##################### Helper Class ############################");
		logger.debug("## Method Name   : "+methodName);
		logger.debug("## Error Message : "+errMsg);
		logger.debug("###############################################################");
	}
	public static String currentDate(String format)
	{
		java.util.Date sysdate = new java.util.Date(System.currentTimeMillis());
		String toDayDate	   = new java.text.SimpleDateFormat(format).format(sysdate); 
		return toDayDate;
	}
	
	public static boolean isListNullOrZero(List<?> list)
	{
		return (list == null || list.size() == 0);
	}
	
	public static boolean isStringEmpty(String str)
	{
		return (str == null || str.length() == 0); 
	}
	
	public static String urlOnly(String url)
	{
		if(isStringEmpty(url)) return "";
		
		int doUrlIndex = url.lastIndexOf("/");
		String doUrl = url.substring(doUrlIndex+1);
		return doUrl;
	}
	
	public static void errorCheck(HttpServletRequest request,ModelAndView mav,HashMap<String,Boolean> obj)
	{
		if(obj != null)
		{
			if(obj.get("isError"))
			{
				String ajaxCall = request.getHeader("ajax");
				if(ajaxCall != null && "true".equals(ajaxCall))
				{
					mav.setViewName("error/ajaxError");
				}
				else
				{
					mav.setViewName("error/errorPage");
				}
			}
		}
	}
	
	public static String getAjaxResultViewName()
	{
		return "ajaxResultView";
	}
	
	public static ModelAndView getForm(String viewName)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	
	public static void setPageNum(Object target,HashMap<String,Boolean> errChk) 
	{
		try
		{
			Method getCntM        = target.getClass().getMethod("getListCnt");
			Method setCntM        = target.getClass().getMethod("setListCnt",String.class);
			Method getPageNumM    = target.getClass().getMethod("getnPage"); 
			Method setPageNumM    = target.getClass().getMethod("setnPage",String.class);
			Method setPagerStartM = target.getClass().getMethod("setPagerStart",int.class);
			Method setPagerEndM   = target.getClass().getMethod("setPagerEnd",int.class);
			
			String cnt     = (String)getCntM.invoke(target);
			String pageNum = (String)getPageNumM.invoke(target);
			
			if("".equals(cnt))
			{
				cnt = "10";
				setCntM.invoke(target,cnt);
			}
			
			if("".equals(pageNum))
			{
				pageNum = "1";
				setPageNumM.invoke(target,pageNum);
			}
			
			int listCnt = Integer.parseInt(cnt);
			int nPage   = Integer.parseInt(pageNum);
			
			int startNum = listCnt*(nPage-1);
			int endNum   = Integer.parseInt(cnt);
			setPagerStartM.invoke(target,startNum);
			setPagerEndM.invoke(target,endNum);
		}
		catch(Exception e)
		{
			setError("setPageNum",e.getMessage());
			errChk.put("isError",true);
		}
	}
	
	public static String getTimeFileName(String cate)
	{
		long currentTime      = System.currentTimeMillis();
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyyMMddHHmmss");
		return cate+sdf.format(new Date(currentTime)); 
		
	}
	
	public static boolean isIE(HttpServletRequest request)
	{
		String ua = request.getHeader("User-Agent");
		return ua!=null && ua.indexOf("MSIE") > -1;
	}
	
	public static String getNumberComma(String s)
	{
		if(isStringEmpty(s)) return "";
		
		try
		{
			double amount = Double.parseDouble(s);
			DecimalFormat formatter = new DecimalFormat("#,##0");
			return formatter.format(amount);
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	public static String getClientIp(HttpServletRequest request)
	{
		String clientIp = request.getHeader("ns_client_ip");
		if(isStringEmpty(clientIp))
		{
			clientIp = request.getHeader("Proxy-Client-IP");
			if(isStringEmpty(clientIp))
			{
				clientIp = request.getRemoteAddr();
			}
		}

		return clientIp;
	}
	
	 /**
	  *
	  * 전화번호 포맷
	  * @param  dt String
	  * @return String
	  *
	  */
	 public static String freeTelForm(String dt) {
	     String aaaa = new String();
	     String bbbb = new String();
	     String cccc = new String();
	     String tel = new String();
	     
	     // 박정웅부장님 요청
	     if (dt != null && dt.length() == 12) {
	         aaaa = dt.substring(0, 4);
	         if (aaaa.equals("0002")) {
	        	 aaaa = "02";
	             bbbb = dt.substring(4, 8);
	             cccc = dt.substring(8);
	         }
	         else {
	             aaaa = dt.substring(1, 4);
	             bbbb = dt.substring(4, 8);
	             cccc = dt.substring(8);
	         }
	         tel = aaaa + "-" + bbbb + "-" + cccc;
	         return tel;
	         //'00-000-0000'인 경우
	     }
	     //' 000-000-0000'와 '000-0000-0000' 형식인 경우(이동전화.지역전화)
	     else if (dt != null && dt.length() == 11) {
	         if (dt.substring(0, 1).equals(" ")) {
	             aaaa = dt.substring(0, 4);
	             bbbb = dt.substring(4, 7);
	             cccc = dt.substring(7);
	         }
	         else {
	             aaaa = dt.substring(0, 3);
	             bbbb = dt.substring(3, 7);
	             cccc = dt.substring(7);
	         }
	         if (aaaa.equals("002"))
	        	 aaaa = "02";
	         tel = aaaa + "-" + bbbb + "-" + cccc;
	         return tel;
	         //'00-0000-0000'와 '000-000-0000' 형식인 경우(이동전화.지역전화)
	     }
	     else if (dt != null && dt.length() == 10) {
	         aaaa = dt.substring(0, 2);
	         if (aaaa.equals("02")) {
	             bbbb = dt.substring(2, 6);
	             cccc = dt.substring(6);
	         }
	         else {
	             aaaa = dt.substring(0, 3);
	             bbbb = dt.substring(3, 6);
	             cccc = dt.substring(6);
	         }
	         tel = aaaa + "-" + bbbb + "-" + cccc;
	         return tel;
	         //'00-000-0000'인 경우
	     }
	     else if (dt != null && dt.length() == 9) {
	         aaaa = dt.substring(0, 2);
	         if (aaaa.equals("02")) {
	             bbbb = dt.substring(2, 5);
	             cccc = dt.substring(5);
	             tel = aaaa + "-" + bbbb + "-" + cccc;
	         }
	         else {
	             tel = dt;
	         }
	         return tel;
	         //기타 경우
	     }
	     else if (dt != null) {
	         tel = dt;
	         return tel;
	     }
	     else {
	         return "&nbsp;";
	     }
	 }
	
	/**
	 * 웹로직 호환용 
	 * 웹로직에서 request.getServletPath() 사용불가(JSP경로가 떨어짐)
	 *  
	 * @param request
	 * @return
	 */
	public static String getServletPath(HttpServletRequest request) {
		String context = request.getContextPath();
		String uri = request.getRequestURI();
		
		context = context != null ? context : "";
		uri = uri != null ? uri : "";
		
		if (context.length() <= 0) {
			return uri;
		}
		
    	context = context.replaceAll("\\/", "\\\\/");
    	
    	uri = uri.replaceAll("^" + context, "");
    	uri = ("/" + uri).replaceAll("^\\/\\/", "/");
    	
		return uri;
	}
	
	
	/**
	 * 패턴 체크
	 * @param uri
	 * @return
	 */
	public static  boolean isPatternMatch(String uri, String pattern) {
		if (uri == null || uri.trim().length() <= 0) {
			return false;
		}
		
		// 패턴 비교
		if (uri.trim().matches(toRegex(pattern))) {
			logger.debug(">> Pattern matched [" + pattern + "]: " + uri);
			return true;
		}
		
		return false;
	}
	
	/**
	 * 패턴 체크
	 * @param uri
	 * @return
	 */
	public static  boolean isPatternMatch(String uri, String[] pattern) {
		if (uri == null || uri.trim().length() <= 0) {
			return false;
		}
		if(pattern != null && pattern.length > 0){
			for(int i=0;i<pattern.length;i++){
				// 패턴 비교
				if (uri.trim().matches(toRegex(pattern[i]))) {
					logger.debug(">> Pattern matched [" + pattern + "]: " + uri);
					return true;
				}
			}
		}		
		return false;
	}
	
	/**
     * 패턴유형을 정규식 형태로 변환
     * @param pattern
     * @return
     */
	public static String toRegex(String pattern) {
    	StringBuffer sBuf = new StringBuffer();

    	for(int i = 0; pattern != null && i < pattern.trim().length(); i++) {
    		String c = pattern.trim().substring(i, (i+1));
    		
    		if (c.matches("[a-zA-Z_0-9]")) { // 문자,숫자
    			sBuf.append(c);
    		} else if ("*".equals(c)) { // wildcard
    			sBuf.append(".*");
    		} else if ("|".equals(c)) { // OR
    			sBuf.append(c);
    		} else { // 그외는 특수문자로 간주
    			sBuf.append("\\").append(c);
    		}
    	}
    	return sBuf.toString();
    }
	

	/**
	 * <PRE>
	 * 1. MethodName	: getAuthNum
	 * 2. ClassName		: Helper
	 * 3. Commnet			: 인증번호 생성
	 * 4. 작성자				: 
	 * 5. 작성일				: 
	 * </PRE>
	 * 		@return String
	 * 		@return
	 */
	public static String getAuthNum() {
		String authNum = "0";
		/***********************************************************************
         * RANDOM 6자리 인증번호 생성
         **********************************************************************/
        Random rd = new Random();
        int rdNum = rd.nextInt(1000000);
        authNum = String.valueOf(rdNum);
        int sSmsNoLength = authNum.length();
        if (sSmsNoLength != 6) {
            for (int r = 0; r < 6 - sSmsNoLength; r++) {
            	authNum = "9" + authNum;
            }
        }
		return authNum;
	}
	
	public static String chngByteLen(String str,int len){
    	byte[] rcvArr = str.getBytes();
		
    	int rcvLen = rcvArr.length;

    	int spcLen = 160 - rcvLen;
    	
    	for(int i=0 ; i < spcLen ; i++){
    		str += " ";
    	}
    	return str;
	}
	
	public static boolean inqrErrChk(Map<String, Object> mp){
		if((mp.get("RESPONSE_CD")).equals("G")){
			return false;
		}else{
			if((mp.get("ERROR_CD")).equals("E6")){
				return false;
			}
		}
		return true;
	}
	
	public static Map<String, Object> securiNum(Map<String, Object> mp){
		String maskingNum = (String) mp.get("PAY_METHOD_INFO2");
		if((mp.get("PAY_METHOD_CD")).equals("A2901")){//은행
			maskingNum = chngBankAst(maskingNum,4);//은행 뒷 4자리 애스터리스트
		}else{
			if(!(mp.get("PAY_METHOD_CD")).equals("A2903")){//지로가 아닐때
				maskingNum = chngCardAst(maskingNum,8); //신용카드 앞8자리이후 애스터리스크
			}
		}
		mp.put("PAY_METHOD_INFO2", maskingNum);
		return mp;
	}
	
	public static String chngCardAst(String str ,int end){
		String rtnStr = "";
		if(str != null && str.length() > end){
			for(int i = 0; i < str.length(); i++){
				if(i > end){
					rtnStr += "*";
				}else{
					rtnStr += str.substring(i, i+1);
				}
			}
		}else{
			return str;
		}
		return rtnStr;
	}
	
	public static String chngBankAst(String str,int end){
		String rtnStr = "";
		if(str != null && str.length() > end){
			for(int i = 0; i < str.length(); i++){
				if(i >= str.length() - 4){
					rtnStr += "*";
				}else{
					rtnStr += str.substring(i, i+1);
				}
			}
		}else{
			return str;
		}
		return rtnStr;
	}
	
	public static String byteArrayToString(byte[] data, int len) {
		String str = "";		
		for (int i = 0; i < len; i++) {
			int val_ten = ((data[i] & 0xf0) >> 4);
			if (val_ten > 9) {
				if (val_ten == 10)
					str += "a";
				else if (val_ten == 11)
					str += "b";
				else if (val_ten == 12)
					str += "c";
				else if (val_ten == 13)
					str += "d";
				else if (val_ten == 14)
					str += "e";
				else if (val_ten == 15)
					str += "f";
			} else {
				str += val_ten;
			}
			int val_one = (data[i] & 0x0f);
			if (val_one > 9) {
				if (val_one == 10)
					str += "a";
				else if (val_one == 11)
					str += "b";
				else if (val_one == 12)
					str += "c";
				else if (val_one == 13)
					str += "d";
				else if (val_one == 14)
					str += "e";
				else if (val_one == 15)
					str += "f";
			} else {
				str += val_one;
			}
		}

		return str;
	}
	

	public static int read(byte b[], int off, int len, java.io.InputStream in) throws IOException, InterruptedException {
		if (b == null) {
			throw new NullPointerException();
		} else if (off < 0 || len < 0 || len > b.length - off) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return 0;
		}

		int c = in.read();
		if (c == -1) {
			return -1;
		}
		b[off] = (byte) c;

		int i = 1;
		int retryCnt = 0;
		try {
			for (; i < len; i++) {
				c = in.read();
				if (c == -1) {
					if(retryCnt < 10){
						Thread.sleep(10);
						--i;
						++retryCnt;
						logger.debug("read()  ["+i+"/"+len+"]>>>>  Retry wating..........");
						continue;
					}else{
						logger.debug("read()  ["+i+"/"+len+"]>>>>  Failed..........");
						break;
					}
					
				}
				b[off + i] = (byte) c;
			}
		} catch (IOException ee) {
		}
		return i;
	}
	
	public static String chkBrowserOS(String userAgent) {
		String browser_name = "";
		
        if(userAgent.indexOf("chrome") != -1) browser_name =  "chrome";
        else if(userAgent.indexOf("firefox") != -1) browser_name =  "firefox";
        else if(userAgent.indexOf("ipad") != -1) browser_name =  "ipad";
        else if(userAgent.indexOf("iphone") != -1) browser_name =  "iphone";
        else if(userAgent.indexOf("android") != -1) browser_name =  "android";
        else if(userAgent.indexOf("symbian") != -1) browser_name =  "symbian";
        else if(userAgent.indexOf("msie 6") != -1) browser_name = "ie6";
        else if(userAgent.indexOf("msie 7") != -1) browser_name =  "ie7";
        else if(userAgent.indexOf("msie 8") != -1) browser_name =  "ie8";
        else if(userAgent.indexOf("msie 9") != -1) browser_name =  "ie9";
        else if(userAgent.indexOf("msie 10") != -1) browser_name =  "ie10";
        else if(userAgent.indexOf("iemobile") != -1) browser_name =  "iemobile";
        else if(userAgent.indexOf("opera") != -1) browser_name =  "opera";
        else if(userAgent.indexOf("safari") != -1)  browser_name =  "safari";
        else if(userAgent.indexOf("mac") != -1) browser_name =  "mac";
        else if(userAgent.indexOf("mobi") != -1) browser_name =  "operaM";

    	logger.debug("userAgent 의 browserOS : " + browser_name);
		return browser_name;
	}
}
