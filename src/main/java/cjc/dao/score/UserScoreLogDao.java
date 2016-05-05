package cjc.dao.score;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.dao.entity.score.UserScoreLog;


public interface UserScoreLogDao  extends CrudRepository<UserScoreLog, Integer>{
	
	List<UserScoreLog>  findByOpenIdAndTypeAndLogTimeLike(String openId,Integer type,Date logTime );

}
