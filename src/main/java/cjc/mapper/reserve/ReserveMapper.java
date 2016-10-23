package cjc.mapper.reserve;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.entity.reserve.Course;
import cjc.entity.reserve.Reserve;
import cjc.entity.reserve.UserCourse;

public interface ReserveMapper{
	
	 
	public List<Reserve> findByFormId(@Param(value="formId") Integer formId);
	
	public List<Reserve> queryByFormIdAndMobile(@Param(value="formId") Integer formId,@Param(value="mobile") String mobile);
	
	public void save(Reserve reserve);
	
	public List<Course> getCoursesByDate(@Param("startDate") Date  startDate,@Param("endDate")Date endDate);
	
	public Integer insertCourse(Course course);
	
	public List<Course> getCourses();
	
	public Integer insertUserCourse(UserCourse userCourse);
	
	public List<UserCourse> queryUserCourse(@Param("courseId")Integer courseId,@Param("phone")String phone);
} 
