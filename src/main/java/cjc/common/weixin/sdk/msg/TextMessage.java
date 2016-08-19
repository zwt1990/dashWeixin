package cjc.common.weixin.sdk.msg;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

@XmlRootElement(name = "xml")
public class TextMessage extends BaseMessage {
	@XmlCDATA 
	@XmlElement(name="Content")
	private String content;
	
	public TextMessage() {
		setMsgType("text");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
