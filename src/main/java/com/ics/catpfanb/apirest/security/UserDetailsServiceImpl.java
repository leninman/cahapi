package com.ics.catpfanb.apirest.security;

import com.ics.catpfanb.apirest.models.dao.IUserDao;
import com.ics.catpfanb.apirest.models.entity.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserDao userDao;

    public UserDetailsServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario=userDao.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("El usuario "+username+" no existe"));
        return new UserdetailsImpl(usuario);
    }
}
