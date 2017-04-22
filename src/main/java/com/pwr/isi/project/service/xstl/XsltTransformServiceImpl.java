package com.pwr.isi.project.service.xstl;

import static com.pwr.isi.project.service.common.DateHelper.getAnyDateFromCurrentDay;
import static com.pwr.isi.project.service.enums.BaseCurrency.PLN;
import static com.pwr.isi.project.service.enums.FrequencyValue.createFrequencyValue;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.service.common.XsltHelper;
import com.pwr.isi.project.service.exception.UnprocessedEntityException;
import com.pwr.isi.project.service.external.ExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class XsltTransformServiceImpl implements XsltTransformService {

  private static final String XsltFile = "nbp.xsl";

  private ExternalService externalService;

  @Autowired
  public XsltTransformServiceImpl(ExternalService externalService) {
    this.externalService = externalService;
  }

  @Override
  public String transformNBPResponse(String currency, String startDate, String endDate) {
    String nbpResponse = externalService.getExchangeRatesFromNBPAsXml(currency, startDate, endDate);
    log.info(nbpResponse);
    return XsltHelper.ApplyXsltTransformToXml(nbpResponse, XsltFile);
  }

  @Override
  public String createSubscriptionReport(Subscription subscription) throws UnprocessedEntityException {
    if (subscription.getCurrencyDenom().equals(PLN.name())) {
      return transformNBPResponse(subscription.getCurrencies(),
          getAnyDateFromCurrentDay(createFrequencyValue(subscription.getFrequency()).getValue()),
          getAnyDateFromCurrentDay(0));
    } else {
      return externalService.getExchangeRatesFromECB(subscription.getCurrencies(),
          getAnyDateFromCurrentDay(createFrequencyValue(subscription.getFrequency()).getValue()),
          getAnyDateFromCurrentDay(0));
    }
  }
}
