package com.example.challenge.filter;

import com.example.challenge.jwt.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class JwtFilter implements WebFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
  public static final String REGEX = "\\.";

  @Autowired
  JwtTokenUtil jwtTokenUtil;

  private static final String[] excludedAuthPages = {
      "/test/excludedAuthPages",
      "/swagger-ui/**",
      "/v2/api-docs/**",
      "/swagger-resources/**"
  };

  public static final String HEADER_PREFIX = "Bearer ";

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    String url = request.getPath().value();
    System.out.println(url);
    if (Stream.of("/swagger-ui", "/v2/api-docs", "/swagger-resources", "/test/testActiveMQ")
        .anyMatch(url::contains)) {
      return chain.filter(exchange);
    }

    String token = resolveToken(exchange.getRequest());
    if (jwtTokenUtil.validateAccessToken(token)) {
      Base64.Decoder decoder = Base64.getUrlDecoder();
      String[] chunks = token.split(REGEX);
      String payload = new String(decoder.decode(chunks[1]));
      try {
        Map<String, String> result =
            new ObjectMapper().readValue(payload, HashMap.class);
        LOGGER.debug(result.get("sub"));
      } catch (JsonProcessingException e) {
        LOGGER.error(e.getMessage());
      }

      return chain.filter(exchange);
    }
    return null;
  }

  private String resolveToken(ServerHttpRequest request) {
    String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
      return bearerToken.substring(7);
    }
    return null;
  }


}




