package com.pwr.isi.project.service.enums;

import com.pwr.isi.project.service.exception.UnprocessedEntityException;

public enum FrequencyValue {
  DAILY(-1),
  WEEKLY(-7),
  MONTHLY(-30);

  private final int value;

  FrequencyValue(int value) {
    this.value = value;
  }

  public static FrequencyValue createFrequencyValue(String frequency) throws UnprocessedEntityException {
    for (FrequencyValue frequencyValue : values()) {
      if (frequencyValue.name().equals(frequency.toUpperCase())) return frequencyValue;
    }
    throw new UnprocessedEntityException();
  }

  public int getValue() { return value; }
}
