package com.example.challenge.service.impl;

import com.example.challenge.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsService implements ReactiveUserDetailsService {

  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  UsersRepository usersRepository;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    var user = usersRepository.findByEmail(username);
    return user.map(users -> {
      UserDetails userDetails = User.withUsername(users.getEmail())
          .password(passwordEncoder.encode(users.getPassword())).roles("admin").build();
      return userDetails;
    });
  }
}
