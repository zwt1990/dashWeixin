package cjc.dto;

import java.util.List;

import cjc.entity.exam.QuestionOpt;

public class QuestionDTO {
	
	private Integer id;
	
	private Integer index;
	
	private String name;
	
	private String answer;
	
	private Integer score;
	
	private List<QuestionOpt> options;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<QuestionOpt> getOptions() {
		return options;
	}

	public void setOptions(List<QuestionOpt> options) {
		this.options = options;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
}
