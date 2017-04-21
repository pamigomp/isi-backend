package com.pwr.isi.project.service.enums;

public enum ResponseFormat {
  JSON("json"),
  XML("xml");

  private final String value;

  ResponseFormat(String value) {
    this.value = value;
  }

  public String getValue() { return value; }
}
