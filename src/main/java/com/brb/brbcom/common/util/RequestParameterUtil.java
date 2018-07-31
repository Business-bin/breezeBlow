package com.brb.brbcom.common.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.brb.brbcom.common.collections.BrbMap;

/**
 * 
 * @author back
 *
 */
public class RequestParameterUtil{
	public static Logger logger  = Logger.getRootLogger();
	
	@SuppressWarnings("unchecked")
	public static BrbMap<Object, Object> getParameterMap(HttpServletRequest request){
		BrbMap<Object, Object> lmReturn  = new BrbMap<Object, Object>();
        Enumeration<Object>      en        = request.getParameterNames();
        String       lsKey   = null;
        String       lsValue = null;
        
        while(en.hasMoreElements()){
            lsKey   = (String)en.nextElement();
            lsValue = String.valueOf(request.getParameter(lsKey));
            //logger.debug("RequestParameterUtil.getParameterMap() : KEY[" + lsKey + "], VALUE[" + lsValue + "]");
            System.out.println("RequestParameterUtil.getParameterMap() : KEY[" + lsKey + "], VALUE[" + lsValue + "]");
            if(lsValue != null && !"".equals(lsValue)){
                lmReturn.put(lsKey, lsValue.trim());
            }
        }
            
        return lmReturn;
    }
	
	@SuppressWarnings("unchecked")
	public static ModelAndView  setParameterMAV(HttpServletRequest request){
		
		ModelAndView lmReturn  = new ModelAndView();
        Enumeration<Object>      en        = request.getParameterNames();
        String       lsKey   = null;
        String       lsValue = null;
        
        while(en.hasMoreElements()){
            lsKey   = (String)en.nextElement();
            lsValue = String.valueOf(request.getParameter(lsKey));
            logger.debug("RequestParameterUtil.setParameterMAV() : KEY[" + lsKey + "], VALUE[" + lsValue + "]");
            if(lsValue != null && !"".equals(lsValue)){
                lmReturn.addObject(lsKey, lsValue);
            }
            if(lsKey.equals("PAGE")){
            	lmReturn.setViewName(lsValue);
            }
        }
            
        return lmReturn;
    }
	
	@SuppressWarnings("unchecked")
	public static BrbMap<Object, Object> getParameterMapNullToStr(HttpServletRequest request){
		BrbMap<Object, Object> lmReturn = new BrbMap<Object, Object>();
        Enumeration<Object>      en       = request.getParameterNames();
        String       lsKey   = null;
        String       lsValue = null;
        while(en.hasMoreElements()){
            lsKey   = (String)en.nextElement();
            //lsValue = StringUtil.toKor((String)request.getParameter(lsKey));
            lsValue = String.valueOf(request.getParameter(lsKey))
                      .replaceAll("'", "''")
                      .replaceAll("--", "__")
                      .replaceAll(";", "|")
                      .replaceAll("%", "")
                      .replaceAll("<", "&lt;")
                      .replaceAll(">", "&gt;");
            logger.debug("RequestParameterUtil.getParameterMap() : KEY[" + lsKey + "], VALUE[" + lsValue + "]");
            
            if(lsValue == null)
            	lsValue = "";

            lmReturn.put(lsKey, lsValue);
        }
            
        return lmReturn;
    }

}
