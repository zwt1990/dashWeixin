package cjc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;







import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cjc.controller.common.H5Response;
import cjc.dto.ExamDTO;
import cjc.dto.QuestionDTO;
import cjc.entity.exam.Exam;
import cjc.service.exam.ExamService;

@Controller
@RequestMapping(value="exam/")
public class ExamController extends BaseController{
	
	@Autowired
	private ExamService	examService;
		
	@RequestMapping(value = "/getQuestions")
    @ResponseBody
    public JSONObject getQuestions(HttpServletRequest request,
			HttpServletResponse response,Integer examId) throws Exception {
		Exam exam=examService.getExam(examId);
		List<QuestionDTO> questions=examService.queryQuestionsByExamId(examId);
		JSONObject exmaObj=   (JSONObject) JSONObject.toJSON(exam);
		JSONObject json=new JSONObject();
		json.put("questions", questions);
		json.put("exam", exmaObj);
		json.put("status", true);
		return json;
    }
	@RequestMapping(value = "/submitExam")
    @ResponseBody
    public H5Response submitExam(HttpServletRequest request,
			HttpServletResponse response,@RequestBody  ExamDTO exam) throws Exception {
		examService.submitExam(exam.getUserId(), exam.getExamId(), exam.getQuestions() );
		return succeed();
    }
	
	
	@RequestMapping(value = "/createExamQues")
    @ResponseBody
    public H5Response createExamQues(HttpServletRequest request,
			HttpServletResponse response,@RequestBody  ExamDTO exam) throws Exception {
		examService.createExamQues(exam);
		return succeed();
    }
	
}
