package cjc.entity.reserve;

import java.util.Date;

import cjc.common.utils.DateUtil;

public class Course {
	private Integer id;
	
	private String name;
	
	private Date startDate;
	
	private String startDateStr;
	
	private Date endDate;
	
	private String endDateStr;
	
	private String coach;//教练
	
	private Integer number;//数量
	
	private Integer resAmount;//预约数量
	
	private Integer remainAmount;//预约数量
	
	private Integer duration;//时长，计算得到
	
	private Date createTime;
	
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		setStartDateStr(DateUtil.getDateTime("HH:mm", startDate));
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		setEndDateStr(DateUtil.getDateTime("HH:mm", endDate));
		this.endDate = endDate;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
		if(resAmount!=null){
			setRemainAmount(number-resAmount);
		}
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getResAmount() {
		return resAmount;
	}

	public void setResAmount(Integer resAmount) {
		this.resAmount = resAmount;
		if(number!=null){
			setRemainAmount(number-resAmount);
		}
	}

	public Integer getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(Integer remainAmount) {
		this.remainAmount = remainAmount;
	}
}
