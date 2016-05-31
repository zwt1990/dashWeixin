package cjc.mapper.sys;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cjc.entity.sys.Role;
import cjc.entity.sys.User;

@Component
public class CustomUserDetailsService implements UserDetailsService{


	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleMapper	roleMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<User> users=userDao.findByUsername(username);
		if(users==null||users.size()==0){
			throw new  UsernameNotFoundException(username +"：用户不存在");
		}
		final User user=users.get(0);
		final List<Role> roles=roleMapper.findRolesByUserId(user.getId());
		
		return new UserDetails(){

			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				List<SimpleGrantedAuthority> auths = new ArrayList<>();
				for(Role r:roles){
					 auths.add(new SimpleGrantedAuthority(r.getName()));
				}
		        return auths;
			}

			@Override
			public String getPassword() {
				return user.getPassword();
			}

			@Override
			public String getUsername() {
				return user.getUsername();
			}

			@Override
			public boolean isAccountNonExpired() {
				return false;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isEnabled() {
				return true;
			}
			
		};
	}
}