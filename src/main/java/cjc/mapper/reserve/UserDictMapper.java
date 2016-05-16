package cjc.mapper.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.dto.ModuleDictDTO;


public interface UserDictMapper {
	
	public List<ModuleDictDTO> getModuleDict(@Param(value="moduleId") Integer moduleId, @Param(value="userId") String userId);
}
