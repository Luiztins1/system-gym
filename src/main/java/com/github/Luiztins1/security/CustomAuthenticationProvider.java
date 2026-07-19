package com.github.Luiztins1.security;

import com.github.Luiztins1.exceptions.NotFoundException;
import com.github.Luiztins1.model.entity.UserAuth;
import com.github.Luiztins1.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider, Serializable {

    private final UserAuthService userAuthService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var login = authentication.getName();
        var password = authentication.getCredentials().toString();
        var userFound = userAuthService.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        var passwordMatch = passwordEncoder.matches(password, userFound.getPassword());

        if(passwordMatch) return new CustomAuthentication(userFound);

        throw new UsernameNotFoundException("Usuário não encontrado.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
