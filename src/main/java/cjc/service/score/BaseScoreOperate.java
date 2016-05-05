package cjc.service.score;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cjc.dao.entity.score.UserScoreLog;
import cjc.dao.score.UserScoreLogDao;
import cjc.utils.DateUtil;

@Component
public  class BaseScoreOperate {

	@Autowired
	protected UserScoreLogDao	userScoreLogDao;
	
	@Transactional
	public  void  addScore(String openId,ScoreEnum scoreEnum){
		 UserScoreLog  userScoreLog=new  UserScoreLog();
		 userScoreLog.setLogTime(DateUtil.getDate());
		 userScoreLog.setScore(scoreEnum.getScore());
		 userScoreLog.setType(scoreEnum.getType());
		 userScoreLog.setOpenId(openId);
		 userScoreLogDao.save(userScoreLog);
	}
	
	public static void deleteScore(String openId,ScoreEnum scoreEnum){
		
	}
	
	public static void getSumScore(String openId){
		
	}
	
	public boolean vlidateAdd(){
		return true;
	}
	
	
	public enum ScoreEnum {
		signIn(1,1);
		private Integer type;
		private Integer score;
		private ScoreEnum(Integer type,Integer score){
			this.type=type;
			this.score=score;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public Integer getScore() {
			return score;
		}
		public void setScore(Integer score) {
			this.score = score;
		}
		
	}
	
}
