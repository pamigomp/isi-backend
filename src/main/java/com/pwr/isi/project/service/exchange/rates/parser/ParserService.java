package com.pwr.isi.project.service.exchange.rates.parser;

import com.pwr.isi.project.service.dto.exchange.rates.ExchangeRatesDto;
import com.pwr.isi.project.service.dto.exchange.rates.ExchangeRatesPageableDto;
import com.pwr.isi.project.service.dto.nbp.NBPResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParserService {

  ExchangeRatesDto getExchangeRatesFromNBP(NBPResponseDto nbpResponseDto);

  ExchangeRatesPageableDto getExchangeRatesFromNBP(NBPResponseDto nbpResponseDto, Pageable pageRequest);

  ExchangeRatesDto getExchangeRatesFromECB(String ecbResponse);

  ExchangeRatesPageableDto getExchangeRatesFromECB(String ecbResponse, Pageable pageRequest);

}
