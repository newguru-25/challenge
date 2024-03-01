package com.example.challenge.service.impl;


import com.example.challenge.model.ExchangeCurrency;
import com.example.challenge.repository.ExchangeCurrencyRepository;
import com.example.challenge.service.ExchangeCurrencyService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExchangeCurrencyServiceImpl implements ExchangeCurrencyService {

  public static final String EXCHANGE_TYPE = "$ ";

  @Autowired
  ExchangeCurrencyRepository exchangeCurrencyRepository;

  public Mono<ExchangeCurrency> save(ExchangeCurrency exchangeCurrency) {
    return exchangeCurrencyRepository.save(extracted(exchangeCurrency));
  }


  public Mono<ExchangeCurrency> findById(int id) {
    return exchangeCurrencyRepository.findById(id);
  }

  public Mono<ExchangeCurrency> update(int id, ExchangeCurrency exchangeCurrency) {
    return exchangeCurrencyRepository.findById(id).map(Optional::of)
        .defaultIfEmpty(Optional.empty())
        .flatMap(optionalExchangeCurrency -> {
          if (optionalExchangeCurrency.isPresent()) {
            exchangeCurrency.setId(id);
            return exchangeCurrencyRepository.save(extracted(exchangeCurrency));
          }
          return Mono.empty();
        });
  }

  private ExchangeCurrency extracted(ExchangeCurrency exchangeCurrency) {
    var fieldTypeAmount = exchangeCurrency.getAmount().multiply(exchangeCurrency.getExchangeType());
    exchangeCurrency.setFieldTypeAmount(EXCHANGE_TYPE.concat(String.valueOf(fieldTypeAmount)));
    return exchangeCurrency;
  }

}
