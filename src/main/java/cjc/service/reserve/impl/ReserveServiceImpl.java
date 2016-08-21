package cjc.service.reserve.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.common.utils.DateUtil;
import cjc.dto.ResFormDTO;
import cjc.entity.reserve.Dictionary;
import cjc.entity.reserve.ResForm;
import cjc.entity.reserve.Reserve;
import cjc.mapper.reserve.DictionaryMapper;
import cjc.mapper.reserve.ResFormMapper;
import cjc.mapper.reserve.ReserveMapper;
import cjc.service.reserve.ReserveService;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{

	@Autowired
	private ReserveMapper	reserveMapper;
	@Autowired
	private ResFormMapper	resFormMapper;
	@Autowired
	private DictionaryMapper dictionaryMapper;
	

	@Override
	public void  submitReserve(Reserve reserve){
		
		try {
			reserve.setResDate(DateUtil.convertStringToDate(reserve.getResDateStr()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 reserveMapper.save(reserve);
	}


	@Override
	public List<ResFormDTO> getAllForms() {
		 Iterable<ResForm> resForms= resFormMapper.findAll();
		 List<ResFormDTO> resFormDTOs=new ArrayList<ResFormDTO>();
		 Iterator<ResForm> iterator = resForms.iterator();
	        while (iterator.hasNext()) {
	        	ResForm form = iterator.next();
	        	resFormDTOs.add(buildForm(form));
	        }
		 return resFormDTOs;
	}
	private ResFormDTO buildForm(ResForm form){
		ResFormDTO resForm=new ResFormDTO();
    	resForm.setConsumerName(form.getConsumerName());
    	resForm.setImgUrl(form.getImgUrl());
    	resForm.setTitle(form.getTitle());
    	resForm.setId(form.getId());
    	List<Reserve> reserves=reserveMapper.findByFormId(form.getId());
    	resForm.setResCounts(reserves.size());
    	resForm.setProjects(getProjectStr(form));
    	return resForm;
	}
	private String getProjectStr(ResForm form){
		StringBuilder sb=new StringBuilder();
		 List<Dictionary>  dicts= (List<Dictionary>) dictionaryMapper.findAll();
		for(Dictionary dict:dicts){
    		if(form.getId().equals(dict.getRefId())){
    			sb.append(dict.getKey());
    			sb.append("，");
    		}
    	}
		if(!StringUtils.isEmpty(sb.toString())){
    		return sb.toString().substring(0,sb.toString().length()-1);
    	}
		return  getDefaultPros();
	}
	
	private String getDefaultPros(){
		 List<Dictionary> defaultDicts= dictionaryMapper.findByType(0);
		 StringBuilder sb=new StringBuilder();
		 for(Dictionary dict: defaultDicts){
			 sb.append(dict.getKey()) ;
			 sb.append("，");
		 }
		 return sb.toString().substring(0,sb.length()-1);
	}


	@Override
	public ResForm addForm(ResFormDTO resFormDTO) {
		ResForm resForm=new ResForm();
		resForm.setConsumerName(resFormDTO.getConsumerName());
		resForm.setImgUrl(resFormDTO.getImgUrl());
		resForm.setTitle(resFormDTO.getTitle());
		return resFormMapper.save(resForm);
	}


	@Override
	public List<Reserve> queryReserve(Integer formId) {
		return reserveMapper.findByFormId(formId);
	}


	@Override
	public ResFormDTO getResForm(Integer formId) {
		ResForm resForm= resFormMapper.findOne(formId);
		 List<Dictionary> defaultDicts= dictionaryMapper.findByType(0);
		ResFormDTO resFormDTO=new ResFormDTO();
		resFormDTO.setTitle(resForm.getTitle());
		resFormDTO.setImgUrl(resForm.getImgUrl());
		resFormDTO.setLink(resForm.getLink());
		resFormDTO.setDictionarys(defaultDicts);
		return resFormDTO;
	}


	@Override
	public List<Reserve> queryByFormIdAndMobile(Integer formId, String mobile) {
		return reserveMapper.queryByFormIdAndMobile(formId, mobile);
	}

}
