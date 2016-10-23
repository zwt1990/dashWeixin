package cjc.dto;

public class DateInfoDTO {
	private String dateStr;//2014-11-09 14:22
	private String shortTime;
	private String shortDate;
	private Integer dayOfWeek;
	private String chineseWeek;
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getShortTime() {
		return shortTime;
	}
	public void setShortTime(String shortTime) {
		this.shortTime = shortTime;
	}
	public String getShortDate() {
		return shortDate;
	}
	public void setShortDate(String shortDate) {
		this.shortDate = shortDate;
	}
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getChineseWeek() {
		return chineseWeek;
	}
	public void setChineseWeek(String chineseWeek) {
		this.chineseWeek = chineseWeek;
	}
}
