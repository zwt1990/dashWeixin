package cjc.service.score;

import cjc.utils.State.SignInStateEnum;

public interface SignInScoreService {
	
	SignInStateEnum signIn(String openId);

}
