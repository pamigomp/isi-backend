package com.pwr.isi.project.service.dto.exchange.rates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRatesDto {
  public String baseCurrencyCode;
  public String targetCurrencyCode;
  public List<CurrencyDataDto> currencyData;
}
