package cjc.mapper.reserve;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.reserve.Dictionary;

public interface DictionaryMapper extends CrudRepository<Dictionary, Serializable>{
	
	public List<Dictionary> findByType(Integer type);
	
	public List<Dictionary> findByRefId(Integer refId);
	
}
