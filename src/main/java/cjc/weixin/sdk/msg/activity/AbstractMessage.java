package cjc.weixin.sdk.msg.activity;

import java.io.Serializable;

/**
 * 主动消息的公用部分
 * @author zwt
 */
public abstract class AbstractMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 普通用户openid */
    public String touser;

    /** 消息类型 */
    public String msgtype;

}
