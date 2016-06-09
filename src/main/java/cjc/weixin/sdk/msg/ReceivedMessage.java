package cjc.weixin.sdk.msg;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

@XmlRootElement(name = "xml")
public class ReceivedMessage extends BaseMessage {
	@XmlCDATA @XmlElement(name="Content") private String content;
	@XmlCDATA @XmlElement(name="MsgId") private String msgId;
	@XmlCDATA @XmlElement(name="Event") private String event;
	@XmlCDATA @XmlElement(name="EventKey") private String eventKey;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
