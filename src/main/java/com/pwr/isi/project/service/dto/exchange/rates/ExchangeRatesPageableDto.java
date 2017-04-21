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
public class ExchangeRatesPageableDto {
  public String baseCurrencyCode;
  public String targetCurrencyCode;
  public List<CurrencyDataDto> currencyData;
  public int totalPages;
  public int totalElements;
  public Boolean last;
  public int size;
  public int number;
  public String sort;
  public int numberOfElements;
  public Boolean first;
}
