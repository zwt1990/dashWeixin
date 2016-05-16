package cjc.mapper.reserve;

import java.util.List;

import cjc.entity.reserve.Dictionary;

public interface DictionaryMapper {
	
	public List<Dictionary> findByType(Integer type);
	
}
