package com.github.Luiztins1.security;

import com.github.Luiztins1.model.entity.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    public UserAuth getUserAuth(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof CustomAuthentication customAuthentication) return customAuthentication.getUserAuth();
        return null;
    }
}
