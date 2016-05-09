package cjc.service.activity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.entity.activity.Activity;
import cjc.mapper.activity.ActivityMapper;
import cjc.service.activity.ActivityService;
import cjc.service.activity.Lottery;
import cjc.service.activity.TemplateLottery;
import cjc.utils.State.LotteryStateEnum;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityMapper	activityMapper;
	
	@Override
	public Integer lottery(String userId, Integer activityId) {
		Activity activity=activityMapper.getActivity(activityId);
		Lottery lottery=null;
		switch (activity.getType()) {
		case 0://转盘抽奖--模板
			lottery=new TemplateLottery(userId, activityId);
			break;
		default:
			break;
		}
		if(lottery==null){
			return LotteryStateEnum.EXCEPTION.EXCEPTION.getCode();
		}
		return lottery.lottery();
	}

}
