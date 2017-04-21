package com.pwr.isi.project.web;

import static com.pwr.isi.project.service.URL.URLContainer.ECB_URL_PATTERN;
import static com.pwr.isi.project.service.URL.URLContainer.NBP_URL_PATTERN;

import com.pwr.isi.project.service.dto.exchange.rates.ExchangeRatesDto;
import com.pwr.isi.project.service.dto.exchange.rates.ExchangeRatesPageableDto;
import com.pwr.isi.project.service.dto.nbp.NBPResponseDto;
import com.pwr.isi.project.service.exchange.rates.parser.ParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

  //TODO: add validation

  /**
   * @param currency    currency ISO 4217 code in UPPERCASE
   * @param startDate   yyyy-mm-dd - must be older than 3 day
   * @param endDate     yyyy-mm-dd - cannot be in the future
   * @param pageRequest page, size, sort
   */
  @RequestMapping(value = "/nbp/exchange_rates/pageable", method = RequestMethod.GET)
  public ExchangeRatesPageableDto getCurrencyFromNBPPageable(@RequestParam("currency") String currency,
                                                             @RequestParam("startDate") String startDate,
                                                             @RequestParam("endDate") String endDate,
                                                             Pageable pageRequest) {
    NBPResponseDto nbpResponse = restTemplate.getForObject(
        String.format(NBP_URL_PATTERN, currency, startDate, endDate),
        NBPResponseDto.class);

    log.info(nbpResponse.toString());

    return parserService.getExchangeRatesFromNBP(nbpResponse, pageRequest);
  }

  //TODO: add validation

  /**
   * @param currency  currency ISO 4217 code in UPPERCASE
   * @param startDate yyyy-mm-dd - must be older than 3 day
   * @param endDate   yyyy-mm-dd - cannot be in the future
   */
  @RequestMapping(value = "/ecb/exchange_rates", method = RequestMethod.GET)
  public Object getCurrencyFromECB(@RequestParam("currency") String currency,
                                   @RequestParam("startDate") String startDate,
                                   @RequestParam("endDate") String endDate) {
    String ecbResponse = restTemplate.getForObject(
        String.format(ECB_URL_PATTERN, currency, startDate, endDate),
        String.class);

    log.info(ecbResponse);

    return parserService.getExchangeRatesFromECB(ecbResponse);
  }

  //TODO: add validation

  /**
   * @param currency    currency ISO 4217 code in UPPERCASE
   * @param startDate   yyyy-mm-dd - must be older than 3 day
   * @param endDate     yyyy-mm-dd - cannot be in the future
   * @param pageRequest page, size, sort
   */
  @RequestMapping(value = "/ecb/exchange_rates/pageable", method = RequestMethod.GET)
  public ExchangeRatesPageableDto getCurrencyFromECBPageable(@RequestParam("currency") String currency,
                                                             @RequestParam("startDate") String startDate,
                                                             @RequestParam("endDate") String endDate,
                                                             Pageable pageRequest) {
    String ecbResponse = restTemplate.getForObject(
        String.format(ECB_URL_PATTERN, currency, startDate, endDate),
        String.class);

    log.info(ecbResponse);

    return parserService.getExchangeRatesFromECB(ecbResponse, pageRequest);
  }
}
