package cjc.entity.activity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "activity_user_lottery_log")
public class UserLotteryLog {

	public static Integer LOTTERY_SUCESS =1;//中奖
	public static Integer LOTTERY_FAIL =0;//没有中奖
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="lottery_time")
	private Date lotteryTime;
	
	@Column(name="prize_id")
	private Integer prizeId;
	
	@Column(name="activity_id")
	private Integer activityId;
	
	@Column(name="succ_flag")
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
}
