package com.pwr.isi.project.service.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(builderMethodName = "anError")
@AllArgsConstructor
public class ErrorDto {
  public int statusCode;
  public String message;
}
