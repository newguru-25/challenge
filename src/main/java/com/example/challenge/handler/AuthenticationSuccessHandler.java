package com.example.challenge.handler;


import com.example.challenge.jwt.JwtTokenUtil;
import com.example.challenge.model.Users;
import com.example.challenge.repository.UsersRepository;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

  @Autowired
  UsersRepository usersRepository;
  @Autowired
  JwtTokenUtil jwtTokenUtil;

  @Override
  public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange,
      Authentication authentication) {
    ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
    response.setStatusCode(HttpStatus.OK);
    response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    String username = userDetails.getUsername();
    return usersRepository.findByEmail(username).map(users -> {
          String accessToken = jwtTokenUtil.generateAccessToken(users);
          DataBuffer buffer = response.bufferFactory().wrap(accessToken.getBytes(CharsetUtil.UTF_8));
          return buffer;
        })
        .flatMap(dataBuffer -> response.writeWith(Mono.just(dataBuffer)));

  }
}
