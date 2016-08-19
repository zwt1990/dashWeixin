package cjc.mapper.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.entity.reserve.Reserve;

public interface ReserveMapper{
	
	 
	public List<Reserve> findByFormId(@Param(value="formId") Integer formId);
	
	public List<Reserve> queryByFormIdAndMobile(@Param(value="formId") Integer formId,@Param(value="mobile") String mobile);
	
	public void save(Reserve reserve);
}
