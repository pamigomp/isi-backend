package com.pwr.isi.project.service.dto.subscription.input;

import com.pwr.isi.project.service.enums.FrequencyValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SubscriptionDto {
  public String email;
  public List<CurrencyDto> currencies;
  public FrequencyValue period;
}