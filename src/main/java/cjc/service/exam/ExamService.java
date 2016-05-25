package cjc.service.exam;

import java.util.List;

import cjc.dto.ExamDTO;
import cjc.dto.QuestionDTO;
import cjc.entity.exam.Exam;

public interface ExamService {
	
	/**
	 * 创建题目
	 * @param examId
	 * @param questions
	 */
	public void createExamQues(ExamDTO exam);

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
	
	
	public Exam getExam(Integer examId);
	
}
