package cjc.utils;

public class State<T> {
	private String message;
	private Integer code;
	private T data;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static enum  SignInStateEnum {
		SUCCESS_SIGN(0,"签到成功"),
		REPEAT_SIGN(1,"今日已经签到");
		private Integer code;
		private String message ;
		SignInStateEnum(Integer code,String message){
			this.code=code;
			this.message=message;
		}
		public Integer getCode() {
			return code;
		}
		public void setCode(Integer code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}public static enum  LotteryStateEnum {
		ACTIVE(0,"活动尚未开始"),
		NOTSTART(-1,"活动尚未开始"),
		TIMEOUT(-2,"活动已经结束"),
		LIMITTIMES(-3,"没有抽奖机会"),
		CLOSED(-4,"活动已经关闭"),
		EXCEPTION(-99,"活动配置异常");
		private Integer code;
		private String message ;
		LotteryStateEnum(Integer code,String message){
			this.code=code;
			this.message=message;
		}
		public Integer getCode() {
			return code;
		}
		public void setCode(Integer code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
}
