package com.fgk.im.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.fgk.im.dao.IUserDao;

public class Data  implements Serializable{

	private static final long serialVersionUID = -8377743708414236875L;
	
	private String uri;
	private HashMap<String, Object>  params;

	public Data() {}
	public Data(String uri, HashMap<String, Object> params) {
		this.uri = uri;
		this.params = params;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public HashMap<String, Object> getParams() {
		return params;
	}
	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Data [uri=" + uri + ", params=" + params + "]";
	}
	
	
	
}
