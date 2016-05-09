package cjc.entity.activity;

import java.util.Date;

public class Activity {
	
	public static final Integer TYPE_LOTTERY_WHEEL=0;//转盘抽奖-模板
	
	public static final Integer TYPE_LOTTERY_DEFINED=1;//游戏抽奖-自定义
	
	private Integer id;
	private String name;
	private Date beginDate;
	private Date endDate;
	private Integer type;
	private Integer takeScore;
	private Integer state;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTakeScore() {
		return takeScore;
	}
	public void setTakeScore(Integer takeScore) {
		this.takeScore = takeScore;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
}
