package com.pwr.isi.project.service.dto.subscription.input;

import com.pwr.isi.project.service.enums.BaseCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyDto {
  public String targetCurrencyName;
  public String targetCurrencyCode;
  public BaseCurrency baseCurrencyCode;
}
