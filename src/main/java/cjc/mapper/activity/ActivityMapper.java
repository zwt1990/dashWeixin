package cjc.mapper.activity;

import org.apache.ibatis.annotations.Param;

import cjc.entity.activity.Activity;

public interface ActivityMapper {
	
	public Activity getActivity(@Param(value="id") Integer id);

}
