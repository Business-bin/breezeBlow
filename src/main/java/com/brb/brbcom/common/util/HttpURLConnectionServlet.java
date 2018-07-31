package com.brb.brbcom.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author back
 *
 */
@Repository("httpURLConnectionServlet")
public class HttpURLConnectionServlet {
	private static final Logger logger = LoggerFactory.getLogger(HttpURLConnectionServlet.class);
	
  /**
   * 
   * @param p_req
   * @param p_res
   * @param clientUrl
   * @param sendData
   * @return
   * @throws Exception
   */
    public String catchService( HttpServletRequest p_req, HttpServletResponse p_res , 
                                 String clientUrl , String sendData    ) throws Exception {
        URL url = new URL(clientUrl);
        String result = "";
        //HttpURLConnection con = null;
        URLConnection con = null;
        String charSetout = "utf-8";
        
        try {
            con = url.openConnection();
	        con.setDoOutput(true);
	        con.setUseCaches(false);
	        con.setRequestProperty("Content-type", "text/xml;charset=" + charSetout);
            // 송신할 데이터 전송.
            send(con, sendData.getBytes(charSetout));
            result = read(con ,charSetout);
        } catch (Exception e) {
           //System.out.println(e.getMessage());
        	e.getMessage();
        }
        return result;
    }
    
    /**
     * 
     * @param clientUrl
     * @param sendData
     * @return
     * @throws Exception
     */
    public String catchService( String clientUrl , String sendData    ) throws Exception {
		URL url = new URL(clientUrl);
		String result = "";
		//HttpURLConnection con = null;
		URLConnection con = null;
		String charSetout = "utf-8";
		
		try {
			con = url.openConnection();
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-type", "text/xml;charset=" + charSetout);
			logger.debug("### catchService - clientUrl : "+ clientUrl);
			logger.debug("### catchService - charSetout : "+ charSetout);
			send(con, sendData.getBytes(charSetout));
			result = read(con ,charSetout);		
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			e.getMessage();
		}
		return result;
	}
  
    /**
     * 
     * @param p_con
     * @param p_writeMsg
     * @throws IOException
     */
    protected void send(URLConnection p_con, byte[] p_writeMsg) throws IOException {
		OutputStream os = null;
		os =  p_con.getOutputStream();
        os.write(p_writeMsg, 0, p_writeMsg.length);
		os.close();	
        
    }
    
   /**
    * 
    * @param p_con
    * @param charSetout
    * @return
    * @throws IOException
    */
    protected String read(URLConnection p_con , String charSetout) throws IOException {
    	
		InputStream in = null;
		in = p_con.getInputStream();
		String temp ="";
		
//    	InputStreamReader isr = new InputStreamReader(is, charSetout);
//		BufferedReader br = new BufferedReader(isr);
		
		final int MAX_SIZE = 200000;
		byte buf[] = new byte[MAX_SIZE];

		try {
			Helper.read(buf, 0, MAX_SIZE, in);
			temp = new String(buf,charSetout).trim();
			logger.debug("### read - temp :" + temp);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
		
        return temp;
        
    }
}
