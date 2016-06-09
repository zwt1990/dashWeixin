package cjc.weixin.sdk;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.toowell.market.common.weixin.sdk.msg.ArticleMessage.Article;

public class Articles {
	private final static ArrayListMultimap<String,Article> articleMap = ArrayListMultimap.create();

	public final static String WELCOME_MSG_KEY = UUID.randomUUID().toString();
	private final static String FM996 = "996车舞飞扬";
	

	static {	
		articleMap.put(WELCOME_MSG_KEY, welcome());
		articleMap.put(FM996, fm996());
	}
	
	private static Article fm996() {
		Article article = new Article();
		article.setTitle("汽车超人送壕礼，天才就该这么玩！");
		article.setDesc("一组可能会对你造成1000000点暴击伤害的问答。。。慎点！");
		article.setPicUrl("http://static.qichechaoren.com/upload/2015/09/feiyang.jpg");
		article.setUrl("http://sale.qccr.com/wenda/index.html");
		return article;
	}
	
	private static Article welcome() {
		Article article = new Article();
		article.setTitle("汽车超人感谢您的关注");
		article.setDesc("各种优惠等着您，一定不会失望的哦！");
		article.setPicUrl("http://static.qichechaoren.com/upload/2015/09/feiyang.jpg");
		article.setUrl("");
		return article;
	}
	
	
	public static List<Article> get(String userInput) {
		String key = StringUtils.trimToEmpty(userInput);
		List<Article> articles = articleMap.get(key);
		if(CollectionUtils.isEmpty(articles)) {
			key = StringUtils.remove(key, StringUtils.SPACE);
			articles = articleMap.get(key);
		}
		
		return articles;
	}
}
