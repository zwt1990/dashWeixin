package cjc.entity.reserve;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reserve_user_dict")
public class ModDict {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "module_id")
	private Integer moduleId;
	
	@Column(name = "dict_id")
	private Integer dictionaryId;
	
	private Integer flag;//1是必填 0是选填 -1禁用

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}	
