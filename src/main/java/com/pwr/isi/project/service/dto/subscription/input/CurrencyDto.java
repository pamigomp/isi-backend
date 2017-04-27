package com.pwr.isi.project.service.dto.subscription.input;

import com.pwr.isi.project.service.enums.BaseCurrency;
import com.pwr.isi.project.service.enums.SubscriptionCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyDto {
  public String targetCurrencyName;
  public SubscriptionCurrency targetCurrencyCode;
  public BaseCurrency baseCurrencyCode;
}
