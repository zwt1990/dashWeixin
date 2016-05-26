package cjc.service.exam;

import java.util.List;

import cjc.dto.ExamDTO;
import cjc.dto.ExamResultDTO;
import cjc.dto.ExamStatisticsDTO;
import cjc.dto.QuestionDTO;
import cjc.entity.exam.Exam;
import cjc.entity.exam.ExamResult;

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
	
	/**
	 * 查询某个问卷结果
	 * @param examId
	 * @return
	 */
	public List<ExamResultDTO> getResultByExamId(Integer examId);
	
	/**
	 * 统计问卷结果
	 * @param examId
	 * @return
	 */
	public List<ExamStatisticsDTO> statisExamResult();
	
	public Exam getExam(Integer examId);
	
}
