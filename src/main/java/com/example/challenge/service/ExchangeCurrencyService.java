package com.example.challenge.service;

import com.example.challenge.model.ExchangeCurrency;
import reactor.core.publisher.Mono;

public interface ExchangeCurrencyService {

  Mono<ExchangeCurrency> save(ExchangeCurrency exchangeCurrency);

  Mono<ExchangeCurrency> findById(int id);

  Mono<ExchangeCurrency> update(int id, ExchangeCurrency exchangeCurrency);

}
