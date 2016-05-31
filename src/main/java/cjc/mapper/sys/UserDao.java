package cjc.mapper.sys;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.sys.User;

public interface UserDao extends CrudRepository<User, Serializable>{

	public List<User> findByUsername(String username);
}
