package cjc.mapper.activity;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.activity.UserLotteryLog;

public interface UserLotteryLogMapper extends CrudRepository<UserLotteryLog, Serializable>{
	
	
}
