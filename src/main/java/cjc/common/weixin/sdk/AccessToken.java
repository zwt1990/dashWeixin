package cjc.common.weixin.sdk;

import java.util.Date;


public class AccessToken {
	
	private String access_token;
	
	private Integer expires_in;//超时时间 默认7200秒

	private Date cacheTime;//缓存时间
	
	public AccessToken(){
		
	}
	
	public AccessToken(String access_token,Integer expires_in ){
		this.access_token=access_token;
		this.expires_in=expires_in;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
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
