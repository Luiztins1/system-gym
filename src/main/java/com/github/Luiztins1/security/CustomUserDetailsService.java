package com.github.Luiztins1.security;

import com.github.Luiztins1.model.entity.UserAuth;
import com.github.Luiztins1.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthService userAuthService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserAuth userAuth = userAuthService.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Usuário não registrado."));

        return User.builder()
                .username(userAuth.getLogin())
                .password(userAuth.getPassword())
                .roles(userAuth.getRoles().toArray(new String[userAuth.getRoles().size()]))
                .build();
    }
}
