package cjc.web.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.common.weixin.sdk.WeiXinUtil;
import cjc.common.weixin.sdk.WeixinException;
import cjc.common.weixin.sdk.menu.CustomMenu.Button;
import cjc.common.weixin.sdk.menu.CustomMenu.CustomButton;
import cjc.common.weixin.sdk.menu.CustomMenu.TYPE;
import cjc.dto.MenuButton;
import cjc.dto.WechatConfig;
import cjc.entity.weixin.WeixinConfig;
import cjc.service.weixin.WechatService;
import cjc.web.controller.common.H5Response;

import com.alibaba.fastjson.JSONObject;
@Controller
public class WechatController extends BaseController{

	@Autowired
	private WechatService wechatService;
	
	
	@RequestMapping("/weixin/updateBtn")
	@ResponseBody
	public JSONObject updateBtn(Integer configId,@RequestBody List<MenuButton> menuButtons,HttpServletRequest request) {
		JSONObject json=new JSONObject();
		if(CollectionUtils.isEmpty(menuButtons)){
			json.put("code", -2);
			json.put("msg","参数不正确");
			return json;
		}
		CustomButton customButton=new CustomButton();
		for(MenuButton menuButton: menuButtons){
			Button button=customButton.addButton(TYPE.view, menuButton.getName(), menuButton.getEventKey(), menuButton.getUrl());
			if(!CollectionUtils.isEmpty(menuButton.getSubButtons())){
				for(MenuButton subBtn: menuButton.getSubButtons()){
					button.addSubButton(TYPE.view, subBtn.getName(), subBtn.getEventKey(), subBtn.getUrl());
				}
			}
		}
		WeixinConfig config=wechatService.getWxConfig(configId);
		try{
			WeiXinUtil.updateMenu(config, customButton);
			json.put("code", 0);
			return json;
		}catch(WeixinException e){
			json.put("code", e.getErrcode());
			json.put("msg", e.getErrmsg());
			return json;
		}
		
	}
	
	@RequestMapping("/weixin/getConfigs")
	@ResponseBody
	public H5Response getConfigs() throws WeixinException {
		List<WeixinConfig> configs=wechatService.allWeixinConfigs();
		return succeed(configs);
	}
	
	@RequestMapping("/weixin/addConfig")
	@ResponseBody
	public H5Response addConfig(HttpServletRequest request,
			HttpServletResponse response,String appId,String appSecret,String name,String token,String originalId) throws WeixinException {
		WeixinConfig  weixinConfig =new WeixinConfig();
		weixinConfig.setAppId(appId);
		weixinConfig.setAppSecret(appSecret);
		weixinConfig.setName(name);
		weixinConfig.setOriginalId(originalId);
		weixinConfig.setToken(token);
		wechatService.addWxConfig(weixinConfig);
		return succeed();
	}	

	@RequestMapping("/weixin/cfgReply")
	@ResponseBody
	public H5Response cfgReply(Integer configId,String welContext,String replyContext,boolean openCustomer) {
		wechatService.updateReply(configId, welContext, replyContext, openCustomer);
		return succeed();
	}
	@RequestMapping("/weixin/getReplays")
	@ResponseBody
	public H5Response getReplays(Integer configId) {
		if(configId==null){
			List<WechatConfig>  wechatConfigs=wechatService.getReplyBys();
			return succeed(wechatConfigs);
		}
		WechatConfig wechatConfig=wechatService.getReplyByConfig(configId);
		return succeed(wechatConfig);
	}
}
