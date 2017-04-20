package com.pwr.isi.project.service.dto.subscription;

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
  public List<String> subscribedCurrencies;
  public FrequencyValue subsciptionFrequency;
}