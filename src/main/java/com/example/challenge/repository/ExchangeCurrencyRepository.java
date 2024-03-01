package com.example.challenge.repository;

import com.example.challenge.model.ExchangeCurrency;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeCurrencyRepository extends R2dbcRepository<ExchangeCurrency, Integer> {


}
