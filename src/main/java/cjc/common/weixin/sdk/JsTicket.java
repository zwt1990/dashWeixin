package cjc.common.weixin.sdk;

import java.util.Date;

public class JsTicket {
	
	private String ticket;
	private Integer expires_in;//超时时间 默认7200秒
	private Date cacheTime;//缓存时间
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public Date getCacheTime() {
		return cacheTime;
	}
	public void setCacheTime(Date cacheTime) {
		this.cacheTime = cacheTime;
	}
}
