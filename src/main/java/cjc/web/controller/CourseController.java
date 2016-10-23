package cjc.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.common.utils.DateUtil;
import cjc.common.utils.DateUtils;
import cjc.dto.DateInfoDTO;
import cjc.entity.reserve.Course;
import cjc.entity.reserve.UserCourse;
import cjc.service.reserve.ReserveService;
import cjc.web.controller.common.H5Response;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="course/")
public class CourseController extends BaseController{

	@Autowired
	private ReserveService reserveService;
	
	@RequestMapping(value = "/getCourses")
    @ResponseBody
    public JSONObject getCourses(HttpServletRequest request,
			HttpServletResponse response,String date,String op) throws Exception {
		String queryDate=getQueryDate(date,op);
		 List<Course> courses=new ArrayList<Course>();
		if(DateUtil.getMargin(queryDate,DateUtil.getDateStr() )>=0){
			 courses=reserveService.getCoursesByDate(queryDate);
		}
		 JSONObject json=new JSONObject();
		 json.put("courses", courses);
		 json.put("date", buildDate(queryDate));
		return json;
    }
	
	@RequestMapping(value = "/allCourses")
    @ResponseBody
    public H5Response allCourses(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Course> courses=reserveService.getCourse();
		return succeed(courses);
    }
	
	@RequestMapping(value = "/submitAppoint")
    @ResponseBody
    public H5Response submitAppoint(HttpServletRequest request,
			HttpServletResponse response,UserCourse userCourse) throws Exception {
		if(userCourse.getCourseId()==null||StringUtils.isEmpty(userCourse.getPhone())){
			return failed("参数缺失");
		}
		boolean result=reserveService.appointCourse(userCourse);
		if(!result){
			return failed("请勿重复预约");
		}
		return succeed();
    }
	
	@RequestMapping(value = "/getUserCourses")
    @ResponseBody
    public H5Response getUserCourses(HttpServletRequest request,
			HttpServletResponse response, Integer courseId ) throws Exception {
		List<UserCourse> usercourse=reserveService.queryUserCourse(courseId);
		return succeed(usercourse);
    }
	
	private String getQueryDate(String date,String op){
		if(StringUtils.isEmpty(date)&&StringUtils.isEmpty(op)){
			return DateUtils.getCurrentDateTimeStr();
		}
		if(op.equals("next")){
			return DateUtils.addDay(date, 1);
		}
		if(op.equals("pre")){
			return  DateUtils.addDay(date, -1);
		}
		return DateUtils.getCurrentDateTimeStr();
	}
	
	private DateInfoDTO buildDate(String date){
		try {
			Date currDate = DateUtils.parseDate(date);
			DateInfoDTO dateInfo=new DateInfoDTO();
			dateInfo.setDateStr(date);
			dateInfo.setShortDate(DateUtils.getDateString(currDate, "yyyy-MM-dd"));
			dateInfo.setShortTime(DateUtils.getDateString(currDate, "HH:mm"));
			dateInfo.setChineseWeek("星期"+DateUtils.getWeekChinese(currDate));
			return dateInfo;
		} catch (ParseException e) {
			return null;
		}
	}
}
