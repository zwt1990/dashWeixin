package cjc.service.score.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cjc.dao.entity.score.UserScoreLog;
import cjc.service.score.BaseScoreOperate;
import cjc.service.score.SignInScoreService;
import cjc.utils.DateUtil;
import cjc.utils.State.SignInStateEnum;

@Service("signInScoreService")
public class SignInScoreServiceImpl extends BaseScoreOperate implements  SignInScoreService{

	
	@Override
	public SignInStateEnum signIn(String openId) {
		if(validateRepeat(openId)){
			addScore(openId,ScoreEnum.signIn);
			return SignInStateEnum.REPEAT_SIGN;
		}
		return SignInStateEnum.SUCCESS_SIGN;
	}

	private boolean validateRepeat(String openId){
		List<UserScoreLog>   userScoreLogs =userScoreLogDao.findByOpenIdAndTypeAndLogTimeLike(openId, ScoreEnum.signIn.getType(), DateUtil.getDate());
		if(userScoreLogs==null||userScoreLogs.size()==0){
			return true;
		}
		return false;
	}
	
	
}
