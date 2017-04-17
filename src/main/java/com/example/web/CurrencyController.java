package com.example.web;

import com.example.service.dto.exchange.rates.CurrencyDataDto;
import com.example.service.dto.exchange.rates.ExchangeRatesDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/exchange/v1")
public class CurrencyController {

  //TODO: DO WYJEBANIA
  @RequestMapping(value = "/currencyDataFromTable", method = RequestMethod.GET)
  public ExchangeRatesDto getDataSeriesRelatesToPln() {
    ExchangeRatesDto exchangeRatesDto = new ExchangeRatesDto();
    exchangeRatesDto.baseCurrencyCode = "PLN";
    exchangeRatesDto.targetCurrencyCode = "USD";
    List<CurrencyDataDto> currencyDatumDtos = new ArrayList<CurrencyDataDto>();
    CurrencyDataDto currencyOne = new CurrencyDataDto();
    currencyOne.effectiveDate = "2017-02-01";
    currencyOne.value = 3.123;
    currencyDatumDtos.add(currencyOne);
    exchangeRatesDto.currencyData = currencyDatumDtos;

    return exchangeRatesDto;
  }

}
