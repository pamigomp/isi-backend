package com.pwr.isi.project.web;

import static com.pwr.isi.project.service.URL.URLContainer.ECB_URL_PATTERN;
import static com.pwr.isi.project.service.URL.URLContainer.NBP_URL_PATTERN;

import com.pwr.isi.project.service.dto.ecb.ECBResponseDto;
import com.pwr.isi.project.service.dto.exchange.rates.ExchangeRatesDto;
import com.pwr.isi.project.service.dto.nbp.NBPResponseDto;
import com.pwr.isi.project.service.exchange.rates.parser.ParserService;
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
public class ExchangeRatesController {

  @Autowired
  private ParserService parserService;

  private RestTemplate restTemplate = new RestTemplate();

  //TODO: add validation

  /**
   * @param currency  currency ISO 4217 code in UPPERCASE
   * @param startDate yyyy-mm-dd - must be older than 3 day
   * @param endDate   yyyy-mm-dd - cannot be in the future
   */
  @RequestMapping(value = "/nbp/exchange_rates", method = RequestMethod.GET)
  public ExchangeRatesDto getCurrencyFromNBP(@RequestParam("currency") String currency,
                                             @RequestParam("startDate") String startDate,
                                             @RequestParam("endDate") String endDate) {
    NBPResponseDto nbpResponse = restTemplate.getForObject(
        String.format(NBP_URL_PATTERN, currency, startDate, endDate),
        NBPResponseDto.class);

    log.info(nbpResponse.toString());

    return parserService.getExchangeRatesFromNBP(nbpResponse);
  }

  //TODO: add validation AND get target currency from response

  /**
   * @param currency  currency ISO 4217 code in UPPERCASE
   * @param startDate yyyy-mm-dd - must be older than 3 day
   * @param endDate   yyyy-mm-dd - cannot be in the future
   */
  @RequestMapping(value = "/ecb/exchange_rates", method = RequestMethod.GET)
  public ExchangeRatesDto getCurrencyFromECB(@RequestParam("currency") String currency,
                                             @RequestParam("startDate") String startDate,
                                             @RequestParam("endDate") String endDate) {
    ECBResponseDto ecbResponse = restTemplate.getForObject(
        String.format(ECB_URL_PATTERN, currency, startDate, endDate),
        ECBResponseDto.class);

    log.info(ecbResponse.toString());

    return parserService.getExchangeRatesFromECB(ecbResponse, currency);
  }
}
