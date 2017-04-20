package com.pwr.isi.project.service.enums;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.List;

public enum SubscriptionCurrency {
  USD,
  PLN,
  EUR,
  THB,
  AUD,
  HKD,
  CAD,
  HUF,
  GBP,
  JPY;

  public static List<String> toListOfString() {
    return new ArrayList<>(asList(stream(SubscriptionCurrency.values()).map(SubscriptionCurrency::name).toArray(String[]::new)));
  }

  public static Boolean isCurrencyValid(String currency) {
    return toListOfString().contains(currency);
  }
}
