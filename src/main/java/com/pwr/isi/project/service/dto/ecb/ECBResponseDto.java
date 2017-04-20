package com.pwr.isi.project.service.dto.ecb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ECBResponseDto {

  public String key;
  public String freq;
  public String currency;
  public String currencyDenom;
  public String exrType;
  public String exrSuffix;
  public String timePeriod;
  public Double obsValue;

  public ECBResponseDto(String[] ecbResponse) {
    this.key = ecbResponse[0];
    this.freq = ecbResponse[1];
    this.currency = ecbResponse[2];
    this.currencyDenom = ecbResponse[3];
    this.exrType = ecbResponse[4];
    this.exrSuffix = ecbResponse[5];
    this.timePeriod = ecbResponse[6];
    this.obsValue = Double.parseDouble(ecbResponse[7]);
  }
}
