package com.github.Luiztins1.security;

import com.github.Luiztins1.exceptions.NotFoundException;
import com.github.Luiztins1.model.entity.UserAuth;
import com.github.Luiztins1.service.UserAuthService;
import com.github.Luiztins1.utils.RandomGeneratedUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserAuthService userAuthService;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;

        OAuth2User oAuth2User = authenticationToken.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        UserAuth userAuth = userAuthService.findByEmail(email);

        if(userAuth == null) userAuth = registerUserAuth(email);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    public UserAuth registerUserAuth(String email){
        UserAuth userAuth = new UserAuth();
        userAuth.setLogin(RandomGeneratedUtils.generateLogin(8));
        userAuth.setPassword(RandomGeneratedUtils.generatePassword(8));
        userAuth.setEmail(email);
        userAuth.setRoles(List.of("USER"));

        userAuthService.registerUserAuth(userAuth);
        return userAuth;
    }
}
