package com.example.demo.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.util.HandleResponseError;
import com.example.demo.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("Attempting authentication");

        try {
            UsernameAndPasswordRequest usernameAndPasswordRequest = new ObjectMapper().readValue(request.getInputStream(),UsernameAndPasswordRequest.class);
            var username = usernameAndPasswordRequest.getUsername();
            var password = usernameAndPasswordRequest.getPassword();
            var authentication = new UsernamePasswordAuthenticationToken(username,password);
            return authenticationManager.authenticate(authentication);
        }catch (Exception exception){
            throw new AuthenticationException(exception.getLocalizedMessage()) {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        log.info("Authentication successful");
        try{
            User user = (User) authResult.getPrincipal();
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            var access_token = TokenUtil.createAccessToken(request, user, algorithm);
            var refresh_token = TokenUtil.createRefreshToken(request, user, algorithm);
            TokenUtil.sendUserTokens(response, refresh_token, access_token);

        }catch (Exception exception){
            response.setStatus(500);
            HandleResponseError.sendError(response,exception);
        }
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
        log.error("Authentication unsuccessful");
        response.setStatus(401);
        HandleResponseError.sendError(response, exception);
    }

    @Data
    static
    class UsernameAndPasswordRequest{
        private String username;
        private String password;
    }

}
