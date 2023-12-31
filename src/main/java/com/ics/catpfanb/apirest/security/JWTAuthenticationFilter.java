package com.ics.catpfanb.apirest.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)  {
        AuthCredentials authCredentials=new AuthCredentials();
        UsernamePasswordAuthenticationToken usernamePAT=new UsernamePasswordAuthenticationToken(
                authCredentials.getUserName(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResul) throws ServletException, IOException {
        UserdetailsImpl userdetails=(UserdetailsImpl) authResul.getPrincipal();
        String token=TokenUtils.createToken(userdetails.getUsername());

        response.addHeader("Authorization","Bearer "+token);
        response.getWriter().flush();

        super.successfulAuthentication(request,response,chain,authResul);


    }
}
