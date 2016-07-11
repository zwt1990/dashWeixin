package cjc.dto;


public class WechatConfig {
	public static final Integer FLAG_OPEN=1;
	
	public static final Integer FLAG_CLOSE=0;
	
	private String appId;
	
	private String name;
	
	private String  welContext;
	
	private String replyContext;
	
	private Integer customerFlag;
	
	private Integer configId;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWelContext() {
		return welContext;
	}

	public void setWelContext(String welContext) {
		this.welContext = welContext;
	}

	public String getReplyContext() {
		return replyContext;
	}

	public void setReplyContext(String replyContext) {
		this.replyContext = replyContext;
	}

	public boolean getCustomerFlag() {
		return customerFlag.equals(FLAG_OPEN);
	}

	public void setCustomerFlag(boolean  openCustomer) {
		this.customerFlag=openCustomer?FLAG_OPEN:FLAG_CLOSE;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
}
