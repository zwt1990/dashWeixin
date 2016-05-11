package cjc.service.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cjc.entity.activity.Activity;
import cjc.entity.activity.Prize;
import cjc.entity.activity.UserLotteryLog;
import cjc.mapper.activity.ActivityMapper;
import cjc.mapper.activity.UserLotteryLogMapper;
import cjc.utils.DateUtil;
import cjc.utils.State.LotteryStateEnum;

@Component
public abstract class Lottery {
	
	
	@Autowired
	protected ActivityMapper	activityMapper;
	@Autowired
	protected UserLotteryLogMapper	userLotteryLogMapper;
	
	public Lottery(){
	}
	
	
	
	public int lottery(String userId,Integer activityId){
		LotteryStateEnum stateEnum=validateLottery(userId,activityId);
		if(!LotteryStateEnum.ACTIVE.equals(stateEnum)){
			return stateEnum.getCode();
		}
		Prize prize = getPrize(userId,activityId);
		UserLotteryLog	lotteryLog=new UserLotteryLog();
		lotteryLog.setUserId(userId);
		lotteryLog.setLotteryTime(DateUtil.getDate());
		if(prize==null){
			lotteryLog.setSuccFlag(UserLotteryLog.LOTTERY_FAIL);
			userLotteryLogMapper.save(lotteryLog);
			return UserLotteryLog.LOTTERY_FAIL;
		}else{
			lotteryLog.setSuccFlag(UserLotteryLog.LOTTERY_SUCESS);
			lotteryLog.setPrizeId(prize.getId());
			userLotteryLogMapper.save(lotteryLog);
			return prize.getType();
		}
	}
	
	private  LotteryStateEnum validateLottery(String userId,Integer activityId){
		Activity activity=activityMapper.getActivity(activityId);
		if(activity.getState().equals(LotteryStateEnum.CLOSED.getCode())){
			return LotteryStateEnum.CLOSED;
		}
		if(DateUtil.compareToCurTime(activity.getBeginDate())<0){
			return LotteryStateEnum.NOTSTART;
		}
		if(DateUtil.compareToCurTime(activity.getEndDate())>0){
			return LotteryStateEnum.TIMEOUT;
		}
		if(getRemainTimes(userId,activityId)==0){
			return LotteryStateEnum.LIMITTIMES;
		}
		return LotteryStateEnum.ACTIVE;
	}
	
	/**
	 * 根据需求产生抽奖结果，子类具体实现
	 * @return
	 */
	public abstract Prize getPrize(String userId,Integer activityId);
	
	/**
	 * 获取是否有抽奖机会，子类具体实现
	 * @return
	 */
	public abstract int getRemainTimes(String userId,Integer activityId);
}
