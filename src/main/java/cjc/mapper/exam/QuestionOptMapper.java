package cjc.mapper.exam;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.entity.exam.QuestionOpt;

public interface QuestionOptMapper{
		
	public List<QuestionOpt> findOptsByExamId(@Param(value="examId")Integer examId);
	
	public int  save(QuestionOpt opt);
}
