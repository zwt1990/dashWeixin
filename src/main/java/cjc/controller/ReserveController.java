package cjc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.controller.common.H5Response;
import cjc.dto.ModuleDictDTO;
import cjc.service.activity.ActivityService;
import cjc.service.reserve.ModuleService;

@Controller
@RequestMapping(value="reserve/")
public class ReserveController extends BaseController{

		@Autowired
		private ModuleService	moduleService;
	
		@RequestMapping(value = "/queryForm")
	    @ResponseBody
	    public H5Response queryForm(HttpServletRequest request,
				HttpServletResponse response,String openId,Integer moduleId) throws Exception {
			List<ModuleDictDTO> moduleDictDTOs =  moduleService.queryDictorys(openId, moduleId);
			return succeed(moduleDictDTOs);
	    }
}
