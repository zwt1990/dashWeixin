package cjc.service.exam;

import java.util.List;

import cjc.dto.QuestionDTO;

public interface ExamService {
	
	/**
	 * 创建题目
	 * @param examId
	 * @param questions
	 */
	public void createExamQues(Integer examId,List<QuestionDTO> questions);

	/**
	 * 根据exmaId查询题目
	 * @param examId
	 * @param questions
	 */
	public List<QuestionDTO> queryQuestionsByExamId(Integer exmaId);
	
	/**
	 * 提交考卷
	 * @param examId
	 * @param questions
	 */
	public void  submitExam(String userId,Integer examId,List<QuestionDTO> questions);
	
}
