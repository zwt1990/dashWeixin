/**
 * @author zwt
 */
package cjc.common.weixin.sdk;

/**
 * 微信业务异常
 * @author zwt
 */
public class WeixinException extends Exception {

    /**
     * @author zwt, 2014-2-19 下午2:53:13
     */
    private static final long serialVersionUID = 1L;


    /** 异常代码 */
    private Integer errcode;

    /** 错误信息 */
    private String errmsg;
    
    
    public WeixinException(String methodName, String errMsg, Throwable cause) {
        super((cause == null ? "" : "cause by: " + cause + ", ") + "methodName=" + methodName, cause);
    }
    
    
    public WeixinException(Integer errcode, String errmsg, Throwable cause) {
        super((cause == null ? "" : "cause by: " + cause + ", ") + "errcode=" + errcode + ", errmsg=" + errmsg, cause);
        this.errcode = errcode;
        this.errmsg = errmsg;
    }


    /**
     * @return 异常代码 {@link #errcode}
     */
    public Integer getErrcode() {
        return errcode;
    }

    /**
     * @return 错误信息 {@link #errmsg}
     */
    public String getErrmsg() {
        return errmsg;
    }
}
