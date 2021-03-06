package cjc.entity.weixin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weixin_reply")
public class WeixinReply {
	public static final Integer FLAG_OPEN=1;
	
	public static final Integer FLAG_CLOSE=0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "wel_context")
	private String  welContext;
	
	@Column(name = "reply_context")
	private String replyContext;
	
	@Column(name = "customer_flag")
	private Integer customerFlag;
	
	@Column(name = "config_id")
	private Integer configId;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
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

}
