package cjc.entity.score;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "score_user_log")
public class UserScoreLog {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
	
		@Column(name = "open_id")
		private String openId;
		
		private Integer type;
		
		@Column(name = "log_time")
		@Temporal(TemporalType.TIMESTAMP)
		private Date logTime;
		
		private Integer score;
		
		public String getOpenId() {
			return openId;
		}
		public void setOpenId(String openId) {
			this.openId = openId;
		}
		public Integer getType() {
			return type;
		}
		public void setType(Integer type) {
			this.type = type;
		}
		public Integer getScore() {
			return score;
		}
		public void setScore(Integer score) {
			this.score = score;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Date getLogTime() {
			return logTime;
		}
		public void setLogTime(Date logTime) {
			this.logTime = logTime;
		}
}
