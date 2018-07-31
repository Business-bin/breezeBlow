package com.brb.brbcom.common.model;

import java.util.LinkedHashMap;

/**
 * 
 * @author back
 *
 */
public class ApiResponseModel extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = -2604938280380243443L;
	private String KEY_NAME_DATA = "data";
	
	public ApiResponseModel(Object o) {
		super.put(this.KEY_NAME_DATA, o);
	}
	
	public void setData(Object o) {
		super.put(this.KEY_NAME_DATA, o);
	}
	
	public Object getData() {
		return super.get(this.KEY_NAME_DATA);
	}
}
