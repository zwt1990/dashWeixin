package cjc.service.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cjc.entity.activity.Activity;
import cjc.entity.activity.Prize;
import cjc.entity.activity.UserLotteryLog;
import cjc.mapper.activity.ActivityMapper;
import cjc.utils.DateUtil;
import cjc.utils.State.LotteryStateEnum;

@Component
public abstract class Lottery {
	
	protected String userId;
	protected Integer activityId;
	
	@Autowired
	protected ActivityMapper	activityMapper;
	
	public Lottery(){
	}
	
	@Autowired
	public Lottery(String userId,Integer activityId){
		this.userId=userId;
		this.activityId=activityId;
	}
	
	
	public int lottery(){
		LotteryStateEnum stateEnum=validateLottery();
		if(!LotteryStateEnum.ACTIVE.equals(stateEnum)){
			return stateEnum.getCode();
		}
		Prize prize = getPrize();
		UserLotteryLog	lotteryLog=new UserLotteryLog();
		lotteryLog.setUserId(userId);
		lotteryLog.setLotteryTime(DateUtil.getDate());
		if(prize==null){
			lotteryLog.setSuccFlag(UserLotteryLog.LOTTERY_FAIL);
			return UserLotteryLog.LOTTERY_FAIL;
		}else{
			lotteryLog.setSuccFlag(UserLotteryLog.LOTTERY_SUCESS);
			lotteryLog.setPrizeId(prize.getId());
			return prize.getType();
		}
	}
	
	private  LotteryStateEnum validateLottery(){
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
		if(getRemainTimes()==0){
			return LotteryStateEnum.LIMITTIMES;
		}
		return LotteryStateEnum.ACTIVE;
	}
	
	/**
	 * 根据需求产生抽奖结果，子类具体实现
	 * @return
	 */
	public abstract Prize getPrize();
	
	/**
	 * 获取是否有抽奖机会，子类具体实现
	 * @return
	 */
	public abstract int getRemainTimes();
}
