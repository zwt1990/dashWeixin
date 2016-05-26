package cjc.mapper.exam;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.dto.ExamResultDTO;
import cjc.dto.ExamStatisticsDTO;
import cjc.entity.exam.ExamResult;


public interface ResultMapper{
	
	public List<ExamStatisticsDTO> statisExamResult();
	
	public Integer save(ExamResult result);
	
	public List<ExamResultDTO> getResultByExamId(@Param(value="examId") Integer examId);
}
