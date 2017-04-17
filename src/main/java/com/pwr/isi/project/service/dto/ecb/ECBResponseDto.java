package com.pwr.isi.project.service.dto.ecb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ECBResponseDto {

  @JsonProperty("DataSet")
  public DataSetDto dataSet;
}
