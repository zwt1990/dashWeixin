package cjc.entity.reserve;

import java.util.Date;

import cjc.common.utils.DateUtil;

public class Reserve {
	private Integer id;
	
	private String openId;
	
	private String name;
	
	private String sex;
	
	private String mobile;
	
	private Date resDate;
	
	private String resDateStr;

	private String project;
	
	private Integer formId;
	
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getResDate() {
		return resDate;
	}

	public void setResDate(Date resDate) {
		setResDateStr(DateUtil.convertDateToString(resDate));
		this.resDate = resDate;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getResDateStr() {
		return resDateStr;
	}

	public void setResDateStr(String resDateStr) {
		this.resDateStr = resDateStr;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
}
