package cjc.weixin.sdk.msg.activity;


/**
 * 主动语音消息<br>
 * msgtype = "voice"
 * @author zwt
 */
public class VoiceMessage extends AbstractMessage {

    private static final long serialVersionUID = 1L;

    public VoiceMessage() {
        msgtype = "voice";
    }

    /** 发送的语音的媒体ID */
    public String media_id;

    @Override
    public String toString() {
        return "VoiceMessage [media_id=" + media_id + ", touser=" + touser + ", msgtype=" + msgtype + "]";
    }

}
