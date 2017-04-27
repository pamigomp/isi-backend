package com.pwr.isi.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ExchangeRatesApplication {

  public static final String HOST_URL = "https://isi-backend.herokuapp.com/";

  public static void main(String[] args) {
    SpringApplication.run(ExchangeRatesApplication.class, args);
  }
}
