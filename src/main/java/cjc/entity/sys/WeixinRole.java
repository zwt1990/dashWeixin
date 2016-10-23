package cjc.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_weixin_role")
public class WeixinRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="wx_cofig_id")
	private Integer wxCofigId;
	
	@Column(name="role_id")
	private Integer roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getWxCofigId() {
		return wxCofigId;
	}

	public void setWxCofigId(Integer wxCofigId) {
		this.wxCofigId = wxCofigId;
	}
	
}
