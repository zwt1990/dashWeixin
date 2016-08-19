package cjc.service.score;

import cjc.common.utils.State.SignInStateEnum;

public interface SignInScoreService {
	
	SignInStateEnum signIn(String openId);

}
