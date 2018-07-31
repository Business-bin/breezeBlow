package com.brb.brbcom.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

/**
 * 
 * @author back
 *
 */
@Service("logHandlerFilter")
public class LogHandlerFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept");
		
		try{

		}catch(Exception e){
			logger.error(e.getMessage());
			//로그히스토리
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected String getRequestParameters(HttpServletRequest request) throws UnsupportedEncodingException {
        StringBuffer buf = new StringBuffer();
        Map<?, ?> params = WebUtils.getParametersStartingWith(request, null);
        if (params == null) {
        	params = new HashMap<String, String>();
        }
        
        Iterator<?> it = params.keySet().iterator();
        while(it.hasNext()) {
        	String key = (String)it.next();
        	
        	if (buf.length() > 0) {buf.append("&");}
        	
        	buf.append(key).append("=").append(URLEncoder.encode((String)params.get(key),"utf-8"));
        }
        
        return buf.toString();
	}

}
