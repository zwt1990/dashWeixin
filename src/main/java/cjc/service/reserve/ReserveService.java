package cjc.service.reserve;

import java.util.List;

import cjc.entity.reserve.Reserve;

public interface ReserveService{
	
	public List<Reserve> queryReserveByUserId(String userId);
	
	public Reserve  submitReserve(Reserve reserve);
}
