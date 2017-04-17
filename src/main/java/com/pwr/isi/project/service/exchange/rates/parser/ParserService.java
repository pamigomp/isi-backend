package com.pwr.isi.project.service.exchange.rates.parser;

import com.pwr.isi.project.service.dto.ecb.ECBResponseDto;
import com.pwr.isi.project.service.dto.exchange.rates.ExchangeRatesDto;
import com.pwr.isi.project.service.dto.nbp.NBPResponseDto;

public interface ParserService {

  ExchangeRatesDto getExchangeRatesFromNBP(NBPResponseDto nbpResponseDto);

  ExchangeRatesDto getExchangeRatesFromECB(ECBResponseDto ecbResponseDto);

}
