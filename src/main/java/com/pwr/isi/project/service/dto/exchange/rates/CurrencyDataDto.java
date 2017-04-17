package com.pwr.isi.project.service.dto.exchange.rates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDataDto {
  public String effectiveDate;
  public double value;
}