package com.example.service.exchange.rates.parser;

import com.example.service.dto.exchange.rates.ExchangeRatesDto;
import com.example.service.dto.nbp.NBPResponseDto;

public interface ParserService {

  ExchangeRatesDto getExchangeRatesFromNBP(NBPResponseDto nbpResponseDto);

}
