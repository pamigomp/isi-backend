package com.pwr.isi.project.service.exchange.rates.parser;

import static com.pwr.isi.project.service.enums.BaseCurrency.EUR;
import static com.pwr.isi.project.service.enums.BaseCurrency.PLN;

import com.pwr.isi.project.service.dto.ecb.ECBResponseDto;
import com.pwr.isi.project.service.dto.ecb.Observations;
import com.pwr.isi.project.service.dto.exchange.rates.CurrencyDataDto;
import com.pwr.isi.project.service.dto.exchange.rates.ExchangeRatesDto;
import com.pwr.isi.project.service.dto.nbp.NBPRateDto;
import com.pwr.isi.project.service.dto.nbp.NBPResponseDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class ParserServiceImpl implements ParserService {

  @Override
  public ExchangeRatesDto getExchangeRatesFromNBP(NBPResponseDto nbpResponseDto) {
    return buildExchangeRates(PLN.toString(), nbpResponseDto.getCode(), buildCurrencyDataListFromNBP(nbpResponseDto.getRates()));
  }

  @Override
  public ExchangeRatesDto getExchangeRatesFromECB(ECBResponseDto ecbResponseDto, String targetCurrency) {
    return buildExchangeRates(EUR.toString(), targetCurrency, buildCurrencyDataListFromECB(Arrays.asList(ecbResponseDto.getDataSets().get(0).getSeries().get_00000().getObservations())));
  }

  private List<CurrencyDataDto> buildCurrencyDataListFromECB(List<Observations> obs) {
    List<CurrencyDataDto> currenciesData = new LinkedList<>();
    for (Observations obsDto : obs) {
      currenciesData.add(CurrencyDataDto.builder()
          .effectiveDate("sdvdsv")
          .value(35.4)
          .build());
    }
    return currenciesData;
  }

  private List<CurrencyDataDto> buildCurrencyDataListFromNBP(List<NBPRateDto> rates) {
    List<CurrencyDataDto> currenciesData = new LinkedList<>();
    for (NBPRateDto nbpRate : rates) {
      currenciesData.add(CurrencyDataDto.builder()
          .effectiveDate(nbpRate.getEffectiveDate())
          .value(Double.parseDouble(nbpRate.getMid()))
          .build());
    }
    return currenciesData;
  }

  private ExchangeRatesDto buildExchangeRates(String baseCurrency, String targetCurrency, List<CurrencyDataDto> currencyData) {
    return ExchangeRatesDto.builder()
        .baseCurrencyCode(baseCurrency)
        .targetCurrencyCode(targetCurrency)
        .currencyData(currencyData)
        .build();
  }

}
