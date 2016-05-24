package cjc.service.reserve.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.entity.reserve.Reserve;
import cjc.mapper.reserve.ReserveMapper;
import cjc.service.reserve.ReserveService;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{

	@Autowired
	private ReserveMapper	reserveMapper;
	
	@Override
	public List<Reserve> queryReserveByUserId(String userId) {
		return reserveMapper.findByUserId(userId);
	}

	@Override
	public Reserve  submitReserve(Reserve reserve){
		Reserve newReserve=new Reserve();
		newReserve.setMobile(reserve.getMobile());
		newReserve.setName(reserve.getName());
		newReserve.setSex(reserve.getSex());
		newReserve.setResDate(reserve.getResDate());
		return reserveMapper.save(newReserve);
	}

}
