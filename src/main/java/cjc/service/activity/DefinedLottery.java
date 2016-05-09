package cjc.service.activity;

import cjc.entity.activity.Prize;

public class DefinedLottery extends Lottery {

	public DefinedLottery(String userId, Integer activityId) {
		super(userId, activityId);
	}

	@Override
	public Prize getPrize() {
		return null;
	}

	@Override
	public int getRemainTimes() {
		return 0;
	}

}
