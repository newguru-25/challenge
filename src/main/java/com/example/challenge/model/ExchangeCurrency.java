package com.example.challenge.model;


import java.math.BigDecimal;
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
public class ExchangeCurrency {

  @Id
  private Integer id;
  private BigDecimal amount;
  private String currencyOrigin;
  private String currencyDestination;
  private String fieldTypeAmount;
  private BigDecimal exchangeType;

}
