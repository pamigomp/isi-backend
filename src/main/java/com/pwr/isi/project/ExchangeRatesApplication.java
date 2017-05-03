package com.pwr.isi.project;

import static com.pwr.isi.project.service.URL.URLContainer.NBP_ALL_CURRENCIES;

import com.pwr.isi.project.service.dto.nbp.table.NBPTablesDto;
import com.pwr.isi.project.service.dto.nbp.table.NBPTablesRateDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class ExchangeRatesApplication {

  public static final String HOST_URL = "https://isi-frontend.herokuapp.com";
  public static List<String> listOfSupportedCurrencies = new LinkedList<>();

  private RestTemplate restTemplate = new RestTemplate();

  public static void main(String[] args) {
    SpringApplication.run(ExchangeRatesApplication.class, args);
  }

  @PostConstruct
  private void getAvailableNbpCurrencies() {
    NBPTablesDto[] allCurrencies = restTemplate.getForObject(NBP_ALL_CURRENCIES, NBPTablesDto[].class);
    listOfSupportedCurrencies.add("PLN");
    for (NBPTablesRateDto rate : allCurrencies[0].getRates()) {
      listOfSupportedCurrencies.add(rate.getCode());
    }
  }
}
