package cjc.service.activity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.entity.activity.Activity;
import cjc.mapper.activity.ActivityMapper;
import cjc.service.activity.ActivityService;
import cjc.service.activity.TemplateLottery;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	private ActivityMapper	activityMapper;
	@Autowired
	private TemplateLottery templateLottery;
	
	@Override
	public Integer lottery(String userId, Integer activityId) {
		Activity activity=activityMapper.getActivity(activityId);
		int code=-99;
		switch (activity.getType()) {
		case 0://转盘抽奖--模板
			code=templateLottery.lottery(userId,activityId);
			break;
		default:
			code=templateLottery.lottery(userId,activityId);
			break;
		}
		return code;
	}

}
