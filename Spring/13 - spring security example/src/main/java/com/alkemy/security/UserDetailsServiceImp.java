package com.alkemy.security;

import com.alkemy.models.dao.IUsuarioDao;
import com.alkemy.models.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        UserBuilder builder = null;
        if(usuario != null){
            builder = User.withUsername(username);
            builder.disabled(false);
            builder.password(usuario.getPassword());
            builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return builder.build();
    }
    
}
