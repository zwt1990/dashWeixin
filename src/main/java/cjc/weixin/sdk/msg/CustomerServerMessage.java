package cjc.weixin.sdk.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

@XmlRootElement(name = "xml")
public class CustomerServerMessage  extends BaseMessage{

	@XmlElement(name="TransInfo")
	private KFAccount transInfo;
	
	public CustomerServerMessage() {
		setMsgType("transfer_customer_service");
	}
	
	public KFAccount getTransInfo() {
		return transInfo;
	}

	public void setTransInfo(KFAccount transInfo) {
		this.transInfo = transInfo;
	}
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class KFAccount {
		@XmlCDATA
		@XmlElement(name="KfAccount")
		private String kfAccount;

		public String getKfAccount() {
			return kfAccount;
		}

		public void setKfAccount(String kfAccount) {
			this.kfAccount = kfAccount;
		}
	}
}
