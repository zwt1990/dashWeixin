package cjc.service.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.dto.ResFormDTO;
import cjc.entity.reserve.Course;
import cjc.entity.reserve.ResForm;
import cjc.entity.reserve.Reserve;
import cjc.entity.reserve.UserCourse;

public interface ReserveService{
	
	
	public void  submitReserve(Reserve reserve);
	
	
	public List<ResFormDTO> getAllForms();
	
	public ResForm addForm(ResFormDTO resFormDTO);
	
	public List<Reserve> queryReserve(Integer formId);
	
	public ResFormDTO getResForm(Integer formId);
	
	public List<Reserve> queryByFormIdAndMobile(Integer formId,String mobile);
	
	public  List<Course> getCoursesByDate(String courseDate);
	
	public Integer addCourse(Course course);
	
	public List<Course> getCourse();
	
	public boolean appointCourse(UserCourse userCourse);
	
	public List<UserCourse> queryUserCourse(Integer course);
	
	public void deleteCourse(Integer courseId);
	
	public void deleteUserCourse(Integer courseId);
}
