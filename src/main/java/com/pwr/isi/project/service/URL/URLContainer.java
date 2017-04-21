package com.pwr.isi.project.service.URL;

public class URLContainer {
  public static final String NBP_URL_PATTERN = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/?format=%s";
  public static final String ECB_URL_PATTERN = "http://sdw-wsrest.ecb.europa.eu/service/data/EXR/D.%s.EUR.SP00.A?startPeriod=%s&endPeriod=%s&detail=dataonly";
}
