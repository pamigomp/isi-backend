package com.example.service.dto.subscription;

import com.example.service.enums.FrequencyValue;
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
  public SubsciptionFrequency subsciptionFrequency;

  public class SubsciptionFrequency {
    public FrequencyValue frequencyValue;
  }
}