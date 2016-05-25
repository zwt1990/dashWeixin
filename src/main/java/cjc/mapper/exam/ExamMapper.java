package cjc.mapper.exam;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.exam.Exam;

public interface ExamMapper extends CrudRepository<Exam, Serializable>{

}
