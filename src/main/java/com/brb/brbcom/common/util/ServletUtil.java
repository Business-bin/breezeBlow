package com.brb.brbcom.common.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * @author back
 *
 */
public class ServletUtil 
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ServletUtil.class);
	
	public static HttpServletRequest getCurrentRequest() {

	       ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder
	               .currentRequestAttributes();

	       HttpServletRequest hsr = sra.getRequest();
	       return hsr;
	   }
}
