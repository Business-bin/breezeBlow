package com.brb.brbcom.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.brb.brbcom.common.collections.BrbMap;

public class Util {

	/**
	 * localip 가져오기
	 * 
	 * @return
	 */
	public static String getLocalServerIp() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
							&& inetAddress.isSiteLocalAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
		}
		return "";
	}

	public static String ipChk(HttpServletRequest request){
		/*String result = "";
		String ip ="";
		try{
			InetAddress inet = InetAddress.getLocalHost();
			result += "Local host IP : " + inet.getHostAddress() + "<br>";
			URL whatismyip = new URL("http://ipconfig.co.kr/");
			HttpURLConnection con = (HttpURLConnection) whatismyip.openConnection();
			InputStream is = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			for (int i=0;i<=1000;i++){
	    	   result += br.readLine();
			}
			br.close();
			is.close();
			ip = result.substring(result.indexOf("IP Address:")+11,result.indexOf("IP Address:")+11+ 17);
		} catch(Exception ex){
			return getLocalServerIp();
		}*/
		
        String clientIp = request.getHeader("Proxy-Client-IP");
        if (clientIp == null) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
            if (clientIp == null) {
                clientIp = request.getHeader("X-Forwarded-For");
                if (clientIp == null) {
                    clientIp = getLocalServerIp();
                }
            }
        }
	
		return clientIp;
	}

	

	/**
	 * 브라우저체크
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowser(HttpServletRequest request) {
		String checkBrow = "";
		String header = request.getHeader("User-Agent").toLowerCase();
		if (header.indexOf("edge") != -1) {
			return checkBrow = "edge";
		}
		if (header.indexOf("chrome") != -1) {
			return checkBrow = "Chrome";
		}
		if (header.indexOf("opera") != -1) {
			return checkBrow = "Opera";
		}
		if (header.indexOf("firefox") != -1) {
			return checkBrow = "Firefox";
		}
		if (header.indexOf("safari") != -1) {
			return checkBrow = "Safari";
		}
		if (header.indexOf("rv:11") != -1) {
			return checkBrow = "IE11";
		}
		if (header.indexOf("msie 10") != -1) {
			return checkBrow = "IE10";
		}
		if (header.indexOf("msie 9") != -1) {
			return checkBrow = "IE9";
		}
		if (header.indexOf("msie 8") != -1) {
			return checkBrow = "IE8";
		}
		if (header.indexOf("msie 7") != -1) {
			return checkBrow = "IE7";
		}
		return checkBrow;
	}
	
	
	public static String getOs(HttpServletRequest request) {
		String os = "";
		String agent  = request.getHeader("User-Agent").toLowerCase();
		if(agent.indexOf("windows nt 5.1") >= 0) {
			os ="Windows XP";
		} else if(agent.indexOf("windows nt 6.0") >=0) {
			os= "Windows Vista";
		} else if(agent.indexOf("windows nt 6.1") >= 0) {
			os = "Windows 7";
		} else if(agent.indexOf("windows nt 6.3") >= 0)  {
			os = "windows 10";
		} else if(agent.indexOf("windows nt 10.0") >=0) {
			os = "windows 10";
		}else if(agent.indexOf("windows phone 8.1") >= 0) {
			os = "Windows Phone 8.1";
		} else if(agent.indexOf("windows PHONE 10.0") >= 0) {
			os = "Windows Phone 10.0";
		} else if(agent.indexOf("android") >= 0 ) {
			os = "Android";
		} else if(agent.indexOf("iphone") >= 0 ) {
			os = "IPhone";
		} else if(agent.indexOf("ipad") >= 0 ) {
			os = "IPad";
		} else if(agent.indexOf("ipod") >= 0 ) {
			os = "IPod";
		} else if(agent.indexOf("mac") >= 0 ) {
			os = "mac";
		} else  {
			os = "";
		}
		return os;
	}

	public static int attrParseInt(BrbMap<Object, Object> dMap, String name) {
		Object obj = dMap.get(name);
		int rtnInt = 0;
		if (obj != null) {
			rtnInt = Integer.parseInt((String) obj);
		}
		return rtnInt;
	}

	/**
	 * 
	 * 파일에 존재여부를 체크 한다.
	 * 
	 * @param s
	 * @return
	 *
	 */
	public static Boolean fileIsLive(String s) {
		File f1 = new File(s);
		if (f1.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * <pre>
	 * 파일 삭제
	 * </pre>
	 *
	 * @param deleteFile
	 *
	 */
	public static void fileDelete(String deleteFile) {
		File i = new File(deleteFile);
		i.delete();
	}

}
