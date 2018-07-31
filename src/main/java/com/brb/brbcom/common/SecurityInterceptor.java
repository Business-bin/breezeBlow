package com.brb.brbcom.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.brb.brbcom.common.util.Util;
import com.brb.common.service.CommonService;

/**
 *
 * @author back
 *
 */
@Service("securityInterceptor")
@Repository
@Scope("request")
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CommonService commonService;

	/**
	 *
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {

		String url = request.getRequestURI();
		HttpSession session = request.getSession();
		String adminEamil = (String) session.getAttribute("ADM_EMAIL");

		String[] urlFilter = {
				"/login/login"
				,"/login/passSend"
				,"/"
				,"/sms/sendSmsType"
				,"/common/sendEmail"
				,"/upload"
		};

		for (int i = 0; i <  urlFilter.length; i++) {
			if (url.equals(urlFilter[i])) {
				return super.preHandle(request, response, handler);
			}
		}

		if(adminEamil == null || "".equals(adminEamil) ) {
			response.sendRedirect(request.getContextPath()+"/");
			return false;
		}

	    if(null != session && null != session.getAttribute("ADM_EMAIL")){
	    	try{
		    	String urls = request.getScheme () + "://" + request.getServerName () + ":" + request.getServerPort ()+request.getRequestURI();
		    	session.setAttribute("OSNAME", Util.getOs(request));
		    	session.setAttribute("USERAGENT", Util.getBrowser(request));
		    	session.setAttribute("URL", url);
		    	session.setAttribute("LOCALURL", urls);
		    	session.setAttribute("REFERER", null == request.getHeader("REFERER") ? "" : request.getHeader("REFERER"));

		    	logger.debug("# session.getAttribute(OSNAME====>" + session.getAttribute("OSNAME"));
				logger.debug("# session.getAttribute(USERAGENT====>" + session.getAttribute("USERAGENT"));
				logger.debug("# session.getAttribute(LOCALIP====>" + session.getAttribute("LOCALIP"));
				logger.debug("# session.getAttribute(LOCALURL====>" + session.getAttribute("LOCALURL"));
				logger.debug("# session.getAttribute(REFERER====>" + session.getAttribute("REFERER"));
				logger.debug("# session.getAttribute(ADM_EMAIL====>" + session.getAttribute("ADM_EMAIL"));
				logger.debug("# session.getAttribute(USERCHECK====>" + session.getAttribute("USERCHECK"));
				logger.debug("# session.getAttribute(ADM_NM====>" + session.getAttribute("ADM_NM"));
				logger.debug("# session.getAttribute(ADM_SQ====>" + session.getAttribute("ADM_SQ"));
				logger.debug("# session.getAttribute(ADM_HP====>" + session.getAttribute("ADM_HP"));

	    	}catch(Exception e){
	    		e.printStackTrace();
			}
	    }
		return super.preHandle(request, response, handler);
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
