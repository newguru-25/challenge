package com.example.challenge.controller;

import com.example.challenge.service.ExchangeCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/exchangecurrency")
public class ExchangeCurrency {


  @Autowired
  ExchangeCurrencyService exchangeCurrencyService;

  @PostMapping("/")
  public Mono<com.example.challenge.model.ExchangeCurrency> postExchangeCurrency(
      @RequestBody com.example.challenge.model.ExchangeCurrency exchangeCurrency) {
    return exchangeCurrencyService.save(exchangeCurrency);
  }


  @GetMapping("/{id}")
  public Mono<com.example.challenge.model.ExchangeCurrency> getExchangeCurrency(
      @PathVariable("id") int id) {
    return exchangeCurrencyService.findById(id);
  }

  @PutMapping("/{id}")
  public Mono<com.example.challenge.model.ExchangeCurrency> updateExchangeCurrency(
      @PathVariable("id") int id,
      @RequestBody com.example.challenge.model.ExchangeCurrency exchangeCurrency) {
    return exchangeCurrencyService.update(id, exchangeCurrency);
  }


}
