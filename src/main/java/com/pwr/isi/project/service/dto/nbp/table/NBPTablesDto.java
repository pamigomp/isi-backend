package com.pwr.isi.project.service.dto.nbp.table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NBPTablesDto {
  private String table;
  private String no;
  private String effectiveDate;
  private List<NBPTablesRateDto> rates;
}
