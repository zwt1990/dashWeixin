package cjc.dto;

import java.util.List;

import cjc.entity.reserve.Dictionary;

public class ResFormDTO {
	private Integer id;

	private String imgUrl;
	
	private String consumerName;
	
	private String link;
	
	private String title;
	
	private String projects;
	
	private Integer resCounts;//预约人数
	
	private List<Dictionary> dictionarys;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProjects() {
		return projects;
	}

	public void setProjects(String projects) {
		this.projects = projects;
	}

	public Integer getResCounts() {
		return resCounts;
	}

	public void setResCounts(Integer resCounts) {
		this.resCounts = resCounts;
	}

	public List<Dictionary> getDictionarys() {
		return dictionarys;
	}

	public void setDictionarys(List<Dictionary> dictionarys) {
		this.dictionarys = dictionarys;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
