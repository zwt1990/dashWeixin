package cjc.weixin.sdk;

import java.util.List;

import cjc.weixin.sdk.msg.ArticleMessage;
import cjc.weixin.sdk.msg.ArticleMessage.Article;
import cjc.weixin.sdk.msg.CustomerServerMessage;
import cjc.weixin.sdk.msg.ReceivedMessage;
import cjc.weixin.sdk.msg.TextMessage;



public class WeiXinReplyMessages {
	//回复图文
	public static ArticleMessage createArticleMessageWithArticles(
				List<Article> articles, ReceivedMessage receivedMsg) {
		ArticleMessage replyMsg = new ArticleMessage();
		replyMsg.setCreateTime(Long.toString(System.currentTimeMillis()));
		replyMsg.setToUserName(receivedMsg.getFromUserName());
		replyMsg.setFromUserName(receivedMsg.getToUserName());
		replyMsg.setArticles(articles);
		return replyMsg;
	}
	
	//回复文本
	public static TextMessage createTextMessage(String text, ReceivedMessage receivedMsg) {
		TextMessage textMessage = new TextMessage();
		textMessage.setCreateTime(Long.toString(System.currentTimeMillis()));
		textMessage.setToUserName(receivedMsg.getFromUserName());
		textMessage.setFromUserName(receivedMsg.getToUserName());
		textMessage.setContent(text);
		return textMessage;
	}
	
	//回复文本
	public static TextMessage createDefaultReply(ReceivedMessage receivedMsg) {
		String DEFAULT_REPLY = "汽车超人，感谢您的关注！";
		return createTextMessage(DEFAULT_REPLY, receivedMsg);
	}
	
	//回复到客服系统
		public static CustomerServerMessage transferCustomerService(ReceivedMessage receivedMsg) {
			CustomerServerMessage customerServerMessage=new CustomerServerMessage();
			customerServerMessage.setCreateTime(receivedMsg.getCreateTime());
			customerServerMessage.setToUserName(receivedMsg.getFromUserName());
			customerServerMessage.setFromUserName(receivedMsg.getToUserName());
			return customerServerMessage;
		}
}
