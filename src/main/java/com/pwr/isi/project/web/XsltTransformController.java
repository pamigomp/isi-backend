package com.pwr.isi.project.web;

import static com.pwr.isi.project.ExchangeRatesApplication.HOST_URL;
import static org.springframework.http.ResponseEntity.ok;

import com.pwr.isi.project.service.xstl.XsltTransformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/exchange/v1/xslt")
@CrossOrigin(origins = HOST_URL)
public class XsltTransformController {

  private XsltTransformService xsltTransformService;

  @Autowired
  public XsltTransformController(XsltTransformService xsltTransformService) {
    this.xsltTransformService = xsltTransformService;
  }

  /**
   * @param currency  currency ISO 4217 code in UPPERCASE
   * @param startDate yyyy-mm-dd - must be older than 3 day
   * @param endDate   yyyy-mm-dd - cannot be in the future
   */
  @RequestMapping(value = "/nbp", method = RequestMethod.GET)
  public ResponseEntity getXslt(@RequestParam("currency") String currency,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate) {
    String responseFromNBP = xsltTransformService.transformNBPResponse(currency, startDate, endDate);
    return ok().body(responseFromNBP);
  }
}
