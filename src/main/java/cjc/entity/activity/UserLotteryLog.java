package cjc.entity.activity;

import java.util.Date;

public class UserLotteryLog {

	public static Integer LOTTERY_SUCESS =1;//中奖
	public static Integer LOTTERY_FAIL =0;//没有中奖
	
	private String userId;
	private Date lotteryTime;
	private Integer prizeId;
	private Integer succFlag;//是否中奖
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getLotteryTime() {
		return lotteryTime;
	}
	public void setLotteryTime(Date lotteryTime) {
		this.lotteryTime = lotteryTime;
	}
	public Integer getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}
	public Integer getSuccFlag() {
		return succFlag;
	}
	public void setSuccFlag(Integer succFlag) {
		this.succFlag = succFlag;
	}
}
