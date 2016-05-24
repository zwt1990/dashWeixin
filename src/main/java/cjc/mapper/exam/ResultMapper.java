package cjc.mapper.exam;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.exam.ExamResult;

public interface ResultMapper extends CrudRepository<ExamResult, Serializable>{
	
	
	
}
