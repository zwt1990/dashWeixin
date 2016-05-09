package cjc.mapper.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.entity.activity.Prize;

public interface PrizeMapper {
	
	public List<Prize> findByActivityId(@Param(value="activity") Integer activity);
}
