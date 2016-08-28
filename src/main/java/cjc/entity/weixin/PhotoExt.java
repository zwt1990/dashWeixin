package cjc.entity.weixin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weixin_photo_ext")
public class PhotoExt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "p_config_id")
	private Integer pConfigId;
	
	@Column(name = "ext_key")
	private String extKey;
	
	@Column(name = "ext_value")
	private String extValue;

	private Integer type;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpConfigId() {
		return pConfigId;
	}

	public void setpConfigId(Integer pConfigId) {
		this.pConfigId = pConfigId;
	}

	public String getExtKey() {
		return extKey;
	}

	public void setExtKey(String extKey) {
		this.extKey = extKey;
	}

	public String getExtValue() {
		return extValue;
	}

	public void setExtValue(String extValue) {
		this.extValue = extValue;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
