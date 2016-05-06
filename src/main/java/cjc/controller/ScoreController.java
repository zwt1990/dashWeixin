package cjc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.controller.common.H5Response;
import cjc.service.score.SignInScoreService;
import cjc.utils.State.SignInStateEnum;

@Controller
@RequestMapping(value="score/")
public class ScoreController extends BaseController{

		@Autowired
		private SignInScoreService	signInScoreService;
	
		@RequestMapping(value = "/signIn")
	    @ResponseBody
	    public H5Response userSignIn(HttpServletRequest request,
				HttpServletResponse response,String openId) throws Exception {
			SignInStateEnum  signInState=signInScoreService.signIn(openId);
//			JSONObject json=new JSONObject();
//			json.put("code", signInState.getCode());
//			json.put("msg", signInState.getMessage());
			return succeed(signInState);
	    }
}
