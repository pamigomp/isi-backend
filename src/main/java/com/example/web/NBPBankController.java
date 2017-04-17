package com.example.web;

import static com.example.service.URL.URLContainer.NBP_URL_PATTERN;

import com.example.service.dto.nbp.NBPResponseDto;
import com.example.service.exchange.rates.parser.ParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/api/exchange/v1")
public class NBPBankController {

  @Autowired
  private ParserService parserService;

  private RestTemplate restTemplate = new RestTemplate();

  //TODO: add validation
  /**
   * @param currencyTable values a, b, c
   * @param currency      currency ISO 4217 code
   * @param startDate     yyyy-mm-dd - must be older than 3 day
   * @param endDate       yyyy-mm-dd - cannot be in the future
   */
  @RequestMapping(value = "/nbp/exchange_rates", method = RequestMethod.GET)
  public Object getCurrency(@RequestParam("currencyTable") String currencyTable,
                            @RequestParam("currency") String currency,
                            @RequestParam("startDate") String startDate,
                            @RequestParam("endDate") String endDate) {
    NBPResponseDto nbpResponse = restTemplate.getForObject(String.format(NBP_URL_PATTERN, currencyTable, currency, startDate, endDate),
        NBPResponseDto.class);
    log.info(nbpResponse.toString());

    return parserService.getExchangeRatesFromNBP(nbpResponse);
  }
}
