package cjc.dto;

import java.util.List;

public class ExamDTO {
	private String userId;
	
	private Integer examId;
	
	private List<QuestionDTO> questions;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}
	
	public static void printRes(String ...str){
		for(String s:str){
			System.out.println(s);
		}
		if(str.length==0){
			System.out.println("孔点多 ");
		}
	}
	
	public static void main(String[] args) {
		printRes("i","2");
		printRes();
	}
	
}
