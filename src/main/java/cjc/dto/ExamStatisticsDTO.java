package cjc.dto;

import java.util.Date;

public class ExamStatisticsDTO {
	private Integer id;
	
	private String examName;
	
	private Date createDate;
	
	private Integer takeTime;
	
	private Integer totalScore;
	
	private Integer minScore;
	
	private Integer maxScore;
	
	private Integer num;
	
	private Double aveScore;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Integer takeTime) {
		this.takeTime = takeTime;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getMinScore() {
		return minScore;
	}

	public void setMinScore(Integer minScore) {
		this.minScore = minScore;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getAveScore() {
		return aveScore;
	}

	public void setAveScore(Double aveScore) {
		this.aveScore = aveScore;
	}
}
