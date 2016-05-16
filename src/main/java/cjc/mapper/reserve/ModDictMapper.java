package cjc.mapper.reserve;

import java.util.List;

import cjc.entity.reserve.Dictionary;

public interface ModDictMapper {
	
	public List<Dictionary> getDictionaryByModuleId(Integer moduleId);
}
