package com.pwr.isi.project.service.dto.ecb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SeriesDto {

  @JsonProperty("Obs")
  public List<ObsDto> obs;
}
