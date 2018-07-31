package  com.brb.brbcom.common.customTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.brb.brbcom.common.util.ApplicationContextHolder;
import com.brb.brbcom.foundation.property.PropertyService;


/**
 * 
 * @author back
 *
 */
public class WebUrlTag extends TagSupport{
	
	protected PropertyService propertyService;
	
	private String mValue;
	private String imgServerURL = "";
	private String contextRoot =  "";
	
    
	
	/**
	 * 
	 */
	public WebUrlTag() {
		propertyService = (PropertyService)ApplicationContextHolder.getContext().getBean("propertiesService");
		contextRoot =  propertyService.getString("CONTEXT_ROOT");
		imgServerURL = propertyService.getString("IMAGE_SERVER_URL");
		
	}
	
    public void setValue(String pValue) {
    	mValue = pValue;
    }
    
    
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            if (mValue.matches(".*.jpg|.*.gif|.*.png|.*.css")) {
            	out.print(imgServerURL + mValue);
            } else {
            	out.print( contextRoot + mValue);
            }
            
        } catch(IOException ioe) {
            throw new JspException("Error: " + ioe.getMessage());
        }       
        return SKIP_BODY;
    }
    
    
    public int doEndTag() throws JspException {
    	return EVAL_PAGE;
    }
    
}
