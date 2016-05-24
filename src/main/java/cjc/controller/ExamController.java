package cjc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.controller.common.H5Response;
import cjc.dto.QuestionDTO;
import cjc.service.exam.ExamService;

@Controller
@RequestMapping(value="exam/")
public class ExamController extends BaseController{
	
	@Autowired
	private ExamService	examService;
		
	@RequestMapping(value = "/getQuestions")
    @ResponseBody
    public H5Response getQuestions(HttpServletRequest request,
			HttpServletResponse response,Integer examId) throws Exception {
		List<QuestionDTO> questuons=examService.queryQuestionsByExamId(examId);
		return succeed(questuons);
    }
	
	
	
}
