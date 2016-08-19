/**
 * @author zwt, 2014-2-24 下午1:26:12
 */
package cjc.common.weixin.sdk.qrcode;

import java.io.Serializable;

/**
 * 申请带参数的二维码的请求
 * @author zwt
 */
public class QrCodeRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 该二维码有效时间，以秒为单位。 最大不超过1800。申请临时二维码时使用，永久二维码不使用。 */
    public Integer expire_seconds;

    /** 二维码类型 */
    public TYPE action_name;

    /** 二维码详细信息 */
    public ActionInfo action_info;

    /**
     * 给映射用的空构造方法
     * @author zwt
     */
    public QrCodeRequest() {

    }

    /**
     * 构建一个带参数的二维码请求
     * @author zwt
     * @param actionName 请求类型
     * @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @param expreSeconds 该二维码有效时间，以秒为单位。 最大不超过1800。申请临时二维码时使用，永久二维码不使用。
     */
    public QrCodeRequest(TYPE actionName, Integer sceneId,String sceneStr, Integer expreSeconds) {
        expire_seconds = expreSeconds;
        action_name = actionName;
        action_info = new ActionInfo();
        action_info.scene.scene_id = sceneId;
        action_info.scene.scene_str = sceneStr;
    }

    @Override
    public String toString() {
        return "QrCodeRequest [expire_seconds=" + expire_seconds + ", action_name=" + action_name + ", action_info=" + action_info + "]";
    }

    /**
     * 二维码详细信息
     * @author zwt, 2014-2-24 下午1:37:05
     */
    public static class ActionInfo implements Serializable {

        private static final long serialVersionUID = 1L;
        
        /** 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000） */
        public Scene scene = new Scene();
        
        /** 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000） */
        public static class Scene implements Serializable {

            private static final long serialVersionUID = 1L;

            /** 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000） */
            public Integer scene_id;
            
            /** 场景值ID，支持32位以内的字符串类型 */
            public String scene_str;
            @Override
            public String toString() {
                return "Scene [scene_id=" + scene_id + "]";
            }
            
        }

        @Override
        public String toString() {
            return "ActionInfo [scene=" + scene + "]";
        }

    }

    /**
     * 二维码类型
     * @author zwt
     */
    public static enum TYPE {
        /** 临时 */
        QR_SCENE,
        /** 永久 */
        QR_LIMIT_SCENE,
        /** 永久字符串 */
        QR_LIMIT_STR_SCENE;
    }
    
}
