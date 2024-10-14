package com.aquilesleite.todosimple.services;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aquilesleite.todosimple.models.User;
import com.aquilesleite.todosimple.repositories.UserRepository;
import com.aquilesleite.todosimple.security.UserSprinSecurity;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired

    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (Objects.isNull(user))
        throw new UsernameNotFoundException("Usuario não encontrado: "+ username);
        return new UserSprinSecurity(user.getId(), user.getUsername(), user.getPassword(), user.getProfiles());
    }
    
}
