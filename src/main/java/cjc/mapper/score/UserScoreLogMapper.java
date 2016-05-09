package cjc.mapper.score;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.entity.score.UserScoreLog;

public interface UserScoreLogMapper {
	
	public List<UserScoreLog> findByUserIdAndTypeAndDate(@Param(value="openId") String openId,@Param(value="type") Integer type,@Param(value="logTime") String logTime);
	 
	public int save(UserScoreLog userScoreLog);
}
