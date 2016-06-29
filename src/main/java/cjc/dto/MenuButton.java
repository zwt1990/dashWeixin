package cjc.dto;

import java.util.List;

public class MenuButton {
	private String name;
	
	private String type;
	
	private String eventKey;
	
	private String url;
	
	private List<MenuButton> subButtons;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<MenuButton> getSubButtons() {
		return subButtons;
	}

	public void setSubButtons(List<MenuButton> subButtons) {
		this.subButtons = subButtons;
	}
}
