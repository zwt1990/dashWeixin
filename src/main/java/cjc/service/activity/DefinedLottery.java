package cjc.service.activity;

import cjc.entity.activity.Prize;

public class DefinedLottery extends Lottery {

	@Override
	public Prize getPrize(String userId, Integer activityId) {
		return null;
	}

	@Override
	public int getRemainTimes(String userId, Integer activityId) {
		return 0;
	}


}
