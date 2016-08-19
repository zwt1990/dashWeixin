package cjc.service.reserve;

import java.util.List;

import cjc.dto.ResFormDTO;
import cjc.entity.reserve.ResForm;
import cjc.entity.reserve.Reserve;

public interface ReserveService{
	
	
	public void  submitReserve(Reserve reserve);
	
	
	public List<ResFormDTO> getAllForms();
	
	public ResForm addForm(ResFormDTO resFormDTO);
	
	public List<Reserve> queryReserve(Integer formId);
	
	public ResFormDTO getResForm(Integer formId);
	
	public List<Reserve> queryByFormIdAndMobile(Integer formId,String mobile);
}
