package cjc.weixin.sdk.msg;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;
import org.springframework.util.CollectionUtils;

@XmlRootElement(name = "xml")
public class ArticleMessage extends BaseMessage {
	@XmlCDATA 
	@XmlElement(name="ArticleCount")
	private int articleCount;

	@XmlCDATA
	@XmlElementWrapper(name="Articles") 
	@XmlElement(name="item") private List<Article> articles = new ArrayList<Article>();
	
	public ArticleMessage() {
		setMsgType("news");
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
		if(CollectionUtils.isEmpty(articles)) {
			this.articleCount = articles.size();
		}
	}
	
	public void addArticle(Article article) {
		articles.add(article);
		this.articleCount++;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Article {
		@XmlCDATA @XmlElement(name="Title") private String title;
		@XmlCDATA @XmlElement(name="Description") private String desc;
		@XmlCDATA @XmlElement(name="PicUrl") private String picUrl;
		@XmlCDATA @XmlElement(name="Url") private String url;
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
