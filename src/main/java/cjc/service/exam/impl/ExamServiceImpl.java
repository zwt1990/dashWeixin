package cjc.service.exam.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.dto.QuestionDTO;
import cjc.entity.exam.ExamResult;
import cjc.entity.exam.QuestionOpt;
import cjc.entity.exam.Question;
import cjc.entity.exam.ResultDeatil;
import cjc.mapper.exam.QuestionOptMapper;
import cjc.mapper.exam.QuestionMapper;
import cjc.mapper.exam.ResultMapper;
import cjc.mapper.exam.ResultDeatilMapper;
import cjc.service.exam.ExamService;
import cjc.utils.DateUtil;
@Service("examService")
public class ExamServiceImpl implements ExamService{

	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionOptMapper	quesOptionMapper;
	@Autowired
	private ResultDeatilMapper	resultDeatilMapper;
	@Autowired
	private ResultMapper	resultMapper;
	
	
	@Override
	public List<QuestionDTO> queryQuestionsByExamId(Integer examId) {
		List<Question> questions=questionMapper.findByExamIdOrderByIndexAsc(examId);
		List<QuestionOpt> options=quesOptionMapper.findOptsByExamId(examId);
		List<QuestionDTO>  questionDTO=new ArrayList<QuestionDTO>();
		for(Question q : questions){
			QuestionDTO exam=new QuestionDTO();
			exam.setId(q.getId());
			exam.setIndex(q.getIndex());
			exam.setName(q.getName());
			List<QuestionOpt> newOptions=new ArrayList<QuestionOpt>();
			for(QuestionOpt o:options){
				if(o.getQuestionId().equals(q.getId())){
					newOptions.add(o);
				}
			}
			exam.setOptions(newOptions);
			questionDTO.add(exam);
		}
		return questionDTO;
	}

	private int getExamScore(Integer examId,List<QuestionDTO> answers){
		List<Question> questions=questionMapper.findByExamIdOrderByIndexAsc(examId);
		int sumScore=0;
		for(Question q:questions){
			for(QuestionDTO a:answers){
				if(!a.getId().equals(q.getId()))
					continue;
				sumScore+=getQuesScore(q,a.getAnswer());
			}
		}
		return sumScore;
	}
	
	private  int getQuesScore(Question q,String answer ){
		int score = 0;
		switch (q.getType()) {
		case 0:
			score = q.getAnswer().equals(answer) ? q.getScore() : 0;
			break;
		case 1:
			score = q.getAnswer().equals(answer) ? q.getScore() : 0;
			break;
		default:
			score = q.getAnswer().equals(answer) ? q.getScore() : 0;
			break;
		}
		return score;
	}

	@Transactional
	public void submitExam(String userId, Integer examId,List<QuestionDTO> quesAnswers) {
		ExamResult reslut=new ExamResult();
		reslut.setBeginDate(DateUtil.getDate());
		reslut.setEndDate(DateUtil.getDate());//TODO  后面涉及考试需要时间限制的修改
		reslut.setExamId(examId);
		reslut.setUserId(userId);
		reslut.setScore(getExamScore(examId, quesAnswers));
		reslut=resultMapper.save(reslut);
		for(QuestionDTO q: quesAnswers){
			ResultDeatil detail=new ResultDeatil();
			detail.setAnswer(q.getAnswer());
			detail.setQuestionId(q.getId());
			detail.setResultId(reslut.getId());
			resultDeatilMapper.save(detail);
		}
		
		
		
		
	}

	@Override
	@Transactional
	public void createExamQues(Integer examId,List<QuestionDTO> questions) {
		for(QuestionDTO q:questions){
			Question question=new Question();
			question.setAnswer(q.getAnswer());
			question.setIndex(q.getIndex());
			question.setExamId(examId);
			question.setName(q.getName());
			question.setScore(q.getScore());
			questionMapper.save(question);
			for(QuestionOpt o:q.getOptions()){
				QuestionOpt opt=new QuestionOpt();
				opt.setIndex(o.getIndex());
				opt.setName(o.getName());
				opt.setQuestionId(q.getId());
				quesOptionMapper.save(opt);
			}
		}
		
	}
}
