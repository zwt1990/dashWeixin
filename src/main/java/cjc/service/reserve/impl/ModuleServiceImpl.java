package cjc.service.reserve.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.dto.ModuleDictDTO;
import cjc.mapper.reserve.ModDictMapper;
import cjc.mapper.reserve.UserDictMapper;
import cjc.service.reserve.ModuleService;
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService{
	
	@Autowired
	private ModDictMapper modDictMapper;
	
	@Autowired
	private UserDictMapper 	userDictMapper;
	
		public List<ModuleDictDTO> queryDictorys(String userId,Integer moduleId){
			 List<ModuleDictDTO> moduleDicts= userDictMapper.getModuleDict(moduleId, userId);
			 return moduleDicts;
		}
}
