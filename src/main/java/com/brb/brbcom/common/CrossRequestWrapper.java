package com.brb.brbcom.common;

/**
 * 
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletRequestWrapper; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CrossRequestWrapper extends HttpServletRequestWrapper {     
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
	public CrossRequestWrapper(HttpServletRequest servletRequest) {         
		super(servletRequest);     
	}     

	@Override
	public String[] getParameterValues(String parameter) {       
		String[] values = super.getParameterValues(parameter); 
		
		if (values==null)  {                   
			return null;            
		}
		
		int count = values.length;       
		String[] encodedValues = new String[count];       

	    for (int i = 0; i < count; i++) {                  
	        encodedValues[i] = encodeXSS(values[i]); 
	        
	        if(log.isDebugEnabled()){
	            log.debug("CrossRequestWrapper.getParameterValues encodeValues :: {} ", i, encodedValues[i]);
	        }
	    }
	    
		return encodedValues;     
	}     
	
	@Override
	public String getParameter(String parameter) {           
		String value = super.getParameter(parameter);           
		if (value == null) {                  
			return null;                   
		}
        value = encodeXSS(value);
        
        if(log.isDebugEnabled()){
            log.debug("CrossRequestWrapper.getParameter encodeValues :: {} ", value);
        }
        
        return value;     
	}
	
	@Override
    public String getHeader(String name) {         
		String value = super.getHeader(name);         
		if (value == null)             
			return null;         
		return encodeXSS(value);     
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
    public Map<String,String[]> getParameterMap(){
        Map<String,String[]> parameterMap = super.getParameterMap();

        Set<String> keySet = parameterMap.keySet();
        Iterator<String>  itrator = keySet.iterator();

        Map<String,String[]> cleanMap = new HashMap<String, String[]>();

        while(itrator.hasNext()){
            String key = itrator.next();
            String[] paramValues = parameterMap.get(key);

            if(paramValues == null){
                cleanMap.put(key, paramValues);
            } else{
                int count = paramValues.length;

                String[] encodedValues = new String[count];
                for (int i = 0; i < count; i++) {
                    encodedValues[i] = encodeXSS(paramValues[i]);
                }

                cleanMap.put(key, encodedValues);
            }
        }

        return cleanMap;
    }
	
	public static String encodeXSS(String _strValue){
	        
	        String value = _strValue;
	            
	        value = value.replaceAll("'", "&#39;");
	        value = value.replaceAll("\\(", "&#40;");
	        value = value.replaceAll("\\)", "&#41;");
	        value = value.replaceAll("--", "&#45;&#45;");
	        value = value.replaceAll("eval\\((.*)\\)", "");
	        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*, arg1)[\\\"\\\']", "\"\"");
		    value = value.replaceAll("<", "&lt;");
		    value = value.replaceAll(">", "&gt;");
		    value = value.replaceAll("\"", "&quot;");
		    value = value.replaceAll("#", "&#35;");
		    value = value.replaceAll("&", "&#amp;");
		    value = value.replaceAll("%", "&#37;");
		    value = value.replaceAll("\"", "&#34;");
	        value = value.replaceAll("(?i)script", "&#115;cript");
	        value = value.replaceAll("(?i)iframe", "i-frame");
	        value = value.replaceAll("(?i)frameset", "frame-set");
	        value = value.replaceAll("(?i)applet", "apple&#116;");
	        
	        value = value.replaceAll("(?i)javascript", "java-script");
	        value = value.replaceAll("(?i)vbscript", "vb-script");
	        value = value.replaceAll("(?i)onactivate", "on-activate");
	        value = value.replaceAll("(?i)on-abort", "onabort");
	        value = value.replaceAll("(?i)onafterprint", "on-afterprint");
	        value = value.replaceAll("(?i)onafterupdate", "on-afterupdate");
	        value = value.replaceAll("(?i)onbeforeactivate", "on-beforeactivate");
	        value = value.replaceAll("(?i)onbeforecopy", "on-beforecopy");
	        value = value.replaceAll("(?i)onbeforecut", "on-beforecut");
	        value = value.replaceAll("(?i)onbeforedeactivate", "on-beforedeactivate");
	        value = value.replaceAll("(?i)onbeforeeditfocus", "on-beforeeditfocus");
	        value = value.replaceAll("(?i)onbeforepaste", "on-beforepaste");
	        value = value.replaceAll("(?i)onbeforeunload", "on-beforeunload");
	        value = value.replaceAll("(?i)onbeforeupdate", "on-beforeupdate");
	        value = value.replaceAll("(?i)onblur", "on-blur");
	        value = value.replaceAll("(?i)onbounce", "on-bounce");
	        value = value.replaceAll("(?i)onbegin", "on-begin");
	        value = value.replaceAll("(?i)oncanplay", "on-canplay");
	        value = value.replaceAll("(?i)oncellchange", "on-cellchange");
	        value = value.replaceAll("(?i)onchange", "on-change");
	        value = value.replaceAll("(?i)onclick", "on-click");
	        value = value.replaceAll("(?i)oncontextmenu", "on-contextmenu");
	        value = value.replaceAll("(?i)oncontrolselect", "on-controlselect");
	        value = value.replaceAll("(?i)oncopy", "on-copy");
	        value = value.replaceAll("(?i)oncut", "on-cut");
	        value = value.replaceAll("(?i)oncontentready", "on-contentready");
	        value = value.replaceAll("(?i)oncontentsave", "on-contentsave");
	        value = value.replaceAll("(?i)ondataavailable", "on-dataavailable");
	        value = value.replaceAll("(?i)ondatasetchanged", "on-datasetchanged");
	        value = value.replaceAll("(?i)ondatasetcomplete", "on-datasetcomplete");
	        value = value.replaceAll("(?i)ondblclick", "on-dblclick");
	        value = value.replaceAll("(?i)ondeactivate", "on-deactivate");
	        value = value.replaceAll("(?i)ondetach", "on-detach");
	        value = value.replaceAll("(?i)ondocumentready", "on-documentready");
	        value = value.replaceAll("(?i)ondrag", "on-drag");
	        value = value.replaceAll("(?i)ondragend", "on-dragend");
	        value = value.replaceAll("(?i)ondragenter", "on-dragenter");
	        value = value.replaceAll("(?i)ondragleave", "on-dragleave");
	        value = value.replaceAll("(?i)ondragover", "on-dragover");
	        value = value.replaceAll("(?i)ondragstart", "on-dragstart");
	        value = value.replaceAll("(?i)ondragdrop", "on-dragdrop");
	        value = value.replaceAll("(?i)ondrop", "on-drop");
	        value = value.replaceAll("(?i)ondurationchange", "on-durationchange");
	        value = value.replaceAll("(?i)onemptied", "on-emptied");
	        value = value.replaceAll("(?i)onended", "on-ended");
	        value = value.replaceAll("(?i)onerrorupdate", "on-errorupdate");
	        value = value.replaceAll("(?i)onfilterchange", "on-filterchange");
	        value = value.replaceAll("(?i)onfinish", "on-finish");
	        value = value.replaceAll("(?i)onfocus", "on-focus");
	        value = value.replaceAll("(?i)onfocusin", "on-focusin");
	        value = value.replaceAll("(?i)onfocusout", "on-focusout");
	        value = value.replaceAll("(?i)onhashchange", "on-hashchange");
	        value = value.replaceAll("(?i)onhelp", "on-help");
	        value = value.replaceAll("(?i)onhide", "on-hide");
	        value = value.replaceAll("(?i)oninput", "on-input");
	        value = value.replaceAll("(?i)onkeydown", "on-keydown");
	        value = value.replaceAll("(?i)onkeypress", "on-keypress");
	        value = value.replaceAll("(?i)onkeyup", "on-keyup");
	        value = value.replaceAll("(?i)onlayoutcomplete", "on-layoutcomplete");
	        value = value.replaceAll("(?i)onload", "on-load");
	        value = value.replaceAll("(?i)onloadeddata", "on-loadeddata");
	        value = value.replaceAll("(?i)onloadedmetadata", "on-loadedmetadata");
	        value = value.replaceAll("(?i)onloadstart", "on-loadstart");
	        value = value.replaceAll("(?i)onlosecapture", "on-losecapture");
	        value = value.replaceAll("(?i)onmessage", "on-message");
	        value = value.replaceAll("(?i)onmediacomplete", "on-mediacomplete");
	        value = value.replaceAll("(?i)onmediaerror", "on-mediaerror");
	        value = value.replaceAll("(?i)onmedialoadfailed", "on-medialoadfailed");
	        value = value.replaceAll("(?i)onmousedown", "on-mousedown");
	        value = value.replaceAll("(?i)onmouseenter", "on-mouseenter");
	        value = value.replaceAll("(?i)onmouseleave", "on-mouseleave");
	        value = value.replaceAll("(?i)onmousemove", "on-mousemove");
	        value = value.replaceAll("(?i)onmouseout", "on-mouseout");
	        value = value.replaceAll("(?i)onmouseover", "on-mouseover");
	        value = value.replaceAll("(?i)onmouseup", "on-mouseup");
	        value = value.replaceAll("(?i)onmousewheel", "on-mousewheel");
	        value = value.replaceAll("(?i)onmove", "on-move");
	        value = value.replaceAll("(?i)onmoveend", "on-moveend");
	        value = value.replaceAll("(?i)onmovestart", "on-movestart");
	        value = value.replaceAll("(?i)onoffline", "on-offline");
	        value = value.replaceAll("(?i)ononline", "on-online");
	        value = value.replaceAll("(?i)ononopenstatechange", "on-openstatechange");
	        value = value.replaceAll("(?i)ononoutofsync", "on-outofsync");
	        value = value.replaceAll("(?i)onpage", "on-page");
	        value = value.replaceAll("(?i)onerror", "on-error");
	        value = value.replaceAll("(?i)onpaste", "on-paste");
	        value = value.replaceAll("(?i)onpause", "on-pause");
	        value = value.replaceAll("(?i)onplay", "on-play");
	        value = value.replaceAll("(?i)onplaying", "on-playing");
	        value = value.replaceAll("(?i)onplaystatechange", "on-playstatechange");
	        value = value.replaceAll("(?i)onprogress", "on-progress");
	        value = value.replaceAll("(?i)onratechange", "on-ratechange");
	        value = value.replaceAll("(?i)onpropertychange", "on-propertychange");
	        value = value.replaceAll("(?i)onreadystatechange", "on-readystatechange");
	        value = value.replaceAll("(?i)onrepeat", "on-repeat");
	        value = value.replaceAll("(?i)onresume", "on-resume");
	        value = value.replaceAll("(?i)onreset", "on-reset");
	        value = value.replaceAll("(?i)onresize", "on-resize");
	        value = value.replaceAll("(?i)onresizeend", "on-resizeend");
	        value = value.replaceAll("(?i)onresizestart", "on-resizestart");
	        value = value.replaceAll("(?i)onreverse", "on-reverse");
	        value = value.replaceAll("(?i)onrowclick", "on-rowclick");
	        value = value.replaceAll("(?i)onrowout", "on-rowout");
	        value = value.replaceAll("(?i)onrowenter", "on-rowenter");
	        value = value.replaceAll("(?i)onrowover", "on-rowover");
	        value = value.replaceAll("(?i)onrowdelete", "on-rowdelete");
	        value = value.replaceAll("(?i)onrowexit", "on-rowexit");
	        value = value.replaceAll("(?i)onrowsdelete", "on-rowsdelete");
	        value = value.replaceAll("(?i)onrowsinserted", "on-rowsinserted");
	        value = value.replaceAll("(?i)onsave", "on-save");
	        value = value.replaceAll("(?i)onseek", "on-seek");
	        value = value.replaceAll("(?i)onscroll", "on-scroll");
	        value = value.replaceAll("(?i)onseeked", "on-seeked");
	        value = value.replaceAll("(?i)onseeking", "on-seeking");
	        value = value.replaceAll("(?i)onselect", "on-select");
	        value = value.replaceAll("(?i)onselectionchange", "on-selectionchange");
	        value = value.replaceAll("(?i)onselectstart", "on-selectstart");
	        value = value.replaceAll("(?i)onshow", "on-show");
	        value = value.replaceAll("(?i)onstalled", "on-stalled");
	        value = value.replaceAll("(?i)onstart", "on-start");
	        value = value.replaceAll("(?i)onstop", "on-stop");
	        value = value.replaceAll("(?i)onstorage", "on-storage");
	        value = value.replaceAll("(?i)onstoragecommit", "on-storagecommit");
	        value = value.replaceAll("(?i)onsubmit", "on-submit");
	        value = value.replaceAll("(?i)onsuspend", "on-suspend");
	        value = value.replaceAll("(?i)onsyncrestored", "on-syncrestored");
	        value = value.replaceAll("(?i)ontimeerror", "on-timeerror");
	        value = value.replaceAll("(?i)ontimeout", "on-timeout");
	        value = value.replaceAll("(?i)ontimeupdate", "on-timeupdate");
	        value = value.replaceAll("(?i)ontrackchange", "on-trackchange");
	        value = value.replaceAll("(?i)onunload", "on-unload");
	        value = value.replaceAll("(?i)onurlflip", "on-urlflip");
	        value = value.replaceAll("(?i)onvolumechange", "on-volumechange");
	        value = value.replaceAll("(?i)onwaiting", "on-waiting");
	        
	        return value;     
	}

	public static String decodeHTML(String value){
	    String source = value;
	    
	    source = source.replaceAll("&amp;", "&");
	    source = source.replaceAll("&lt;", "<");
	    source = source.replaceAll("&gt;", ">");
	    source = source.replaceAll("&quot;", "\"");
	    source = source.replaceAll("&#39;", "\'");
	    source = source.replaceAll("&#45;&#45;", "--");
	    source = source.replaceAll( "&#40;", "\\(");
	    source = source.replaceAll( "&#41;", "\\)");
	    source = source.replaceAll( "&#37;", "%");

	    return source;
	}
	
}
