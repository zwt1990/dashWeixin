package cjc.service.activity;

public interface ActivityService {
	
	/**
	 * 抽奖类的活动
	 * @param userId
	 * @param activityId
	 * @return
	 */
	public Integer lottery(String userId,Integer activityId);

}
