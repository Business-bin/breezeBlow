package com.brb.brbcom.common;

public class Result {
	public static final String SUCCESS = "0";
	public static final String FAILURE = "1";

    private String type;
    protected String code = SUCCESS;
    private String errDesc = "";
    private String ajaxCode = SUCCESS;
    private String sysCode = "";
    private Object value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static String getSuccess() {
		return SUCCESS;
	}

	public static String getFailure() {
		return FAILURE;
	}

	public String getAjaxCode() {
		return ajaxCode;
	}

	public void setAjaxCode(String ajaxCode) {
		this.ajaxCode = ajaxCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	

    
}
