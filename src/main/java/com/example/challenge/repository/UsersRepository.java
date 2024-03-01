package com.example.challenge.repository;

import com.example.challenge.model.Users;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UsersRepository extends R2dbcRepository<Users, Integer> {
  Mono<Users> findByEmail(String email);
}
