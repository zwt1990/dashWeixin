package cjc.service.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import cjc.entity.activity.Activity;
import cjc.entity.activity.Prize;
import cjc.entity.score.UserScoreLog;
import cjc.mapper.activity.PrizeMapper;
import cjc.mapper.score.UserScoreLogMapper;
/**
 * 模板抽奖，抽奖规则统一
 * @author zwt
 *
 */
@Component
public class TemplateLottery extends Lottery{
	
	@Autowired
	private PrizeMapper	prizeMapper;
	@Autowired
	private UserScoreLogMapper userScoreLogMapper;
	
	public TemplateLottery(String userId, Integer activityId) {
		super(userId, activityId);
	}

	@Override
	public  Prize getPrize() {
		List<Prize> prizes=prizeMapper.findByActivityId(activityId);
		if(CollectionUtils.isEmpty(prizes)){
			return null;
		}
		 List<Range> ranges=new ArrayList<TemplateLottery.Range>();
		 int peakValue=0;
		 int valleyValue=0;
		for(int i=0;i<prizes.size();i++){
			peakValue+=prizes.get(i).getRate()*10000;
			Range range=new Range();
			range.setPeakValue(peakValue);
			range.setValleyValue(valleyValue);
			range.setPrize(prizes.get(i));
			ranges.add(range);
			valleyValue+=prizes.get(i).getRate()*10000;
		}
		return getFitPrize(ranges);
	}
	private Prize getFitPrize( List<Range> ranges){
		 int radmon = new Random().nextInt(10000); 
		Prize prize=null;
		for(Range range: ranges ){
			if(radmon>=range.getValleyValue()&&radmon<range.getPeakValue()){
				prize=range.getPrize();
				break;
			}
		}
		return prize;
	}
	
	
	@Override
	public int getRemainTimes() {
		Activity activity=activityMapper.getActivity(activityId);
		int sumScore=userScoreLogMapper.getSumScore(userId);
		return sumScore/activity.getTakeScore();
	}
	class Range{
		Integer peakValue;
		Integer valleyValue;
		Prize prize;
		public Integer getPeakValue() {
			return peakValue;
		}
		public void setPeakValue(Integer peakValue) {
			this.peakValue = peakValue;
		}
		public Integer getValleyValue() {
			return valleyValue;
		}
		public void setValleyValue(Integer valleyValue) {
			this.valleyValue = valleyValue;
		}
		public Prize getPrize() {
			return prize;
		}
		public void setPrize(Prize prize) {
			this.prize = prize;
		}
	}
}
