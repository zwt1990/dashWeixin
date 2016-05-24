package cjc.mapper.reserve;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.reserve.Reserve;

public interface ReserveMapper extends CrudRepository<Reserve, Serializable>{
	
	public List<Reserve> findByUserId(String userId);
}
