package cjc.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.service.activity.ActivityService;
import cjc.web.controller.common.H5Response;

@Controller
@RequestMapping(value="activity/")
public class LotteryController extends BaseController{

		@Autowired
		private ActivityService	activityService;
	
		@RequestMapping(value = "/lottery")
	    @ResponseBody
	    public H5Response userSignIn(HttpServletRequest request,
				HttpServletResponse response,String openId,Integer activityId) throws Exception {
			Integer code=activityService.lottery(openId, activityId);
			return succeed(code);
	    }
}
