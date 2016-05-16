package cjc.service.reserve;

import java.util.List;

import cjc.dto.ModuleDictDTO;

public interface ModuleService {
	
	public List<ModuleDictDTO> queryDictorys(String userId,Integer moduleId);
	
}
