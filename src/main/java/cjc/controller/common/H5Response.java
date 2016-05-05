package cjc.controller.common;

import java.util.ArrayList;
import java.util.List;

/**
 * http返回体
 * 
 * @author 彭代超
 * @date: 2015年11月4日 下午6:11:16
 */
public class H5Response {
	private boolean status = true;
	private String msg;
	private Object data;

	public int getDataSize() {
		if (data instanceof List) {
			return ((ArrayList) data).size();
		}
		return 0;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
