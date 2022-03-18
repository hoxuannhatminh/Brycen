package com.brycen.vn.service.iml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brycen.vn.entity.User;
import com.brycen.vn.entity.UserGroup;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private com.brycen.vn.repositories.UserRepository userRepository;
    
    @Autowired
    private com.brycen.vn.repositories.UserGroupRepository userGroupRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        List<UserGroup> roles = userGroupRepository.findByName(username);
        for (UserGroup role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }

}
