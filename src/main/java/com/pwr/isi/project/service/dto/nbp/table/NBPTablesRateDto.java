package com.pwr.isi.project.service.dto.nbp.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NBPTablesRateDto {
  private String currency;
  private String code;
  private Double mid;
}