package com.example.challenge.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Users {

  @Id
  private Integer id;
  private String email;
  private String password;

}
