package cjc.mapper.exam;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.exam.Question;

public interface QuestionMapper extends CrudRepository<Question, Serializable>{
	
	public List<Question> findByExamIdOrderByIndexAsc(Integer examId);
}
