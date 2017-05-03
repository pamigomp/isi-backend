package com.pwr.isi.project.service.external;

import com.pwr.isi.project.service.dto.nbp.rates.NBPResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.pwr.isi.project.service.URL.URLContainer.ECB_URL_PATTERN;
import static com.pwr.isi.project.service.URL.URLContainer.NBP_URL_PATTERN;
import static com.pwr.isi.project.service.enums.ResponseFormat.JSON;
import static com.pwr.isi.project.service.enums.ResponseFormat.XML;

@Service
public class ExternalServiceImpl implements ExternalService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public NBPResponseDto getExchangeRatesFromNBP(String currency, String startDate, String endDate) {
        return restTemplate.getForObject(String.format(NBP_URL_PATTERN, currency, startDate, endDate, JSON.getValue()), NBPResponseDto.class);
    }

    @Override
    public String getExchangeRatesFromNBPAsXml(String currency, String startDate, String endDate) {
        return restTemplate.getForObject(String.format(NBP_URL_PATTERN, currency, startDate, endDate, XML.getValue()), String.class);
    }

    @Override
    public String getExchangeRatesFromECB(String currency, String startDate, String endDate) {
        return restTemplate.getForObject(String.format(ECB_URL_PATTERN, currency, startDate, endDate), String.class);
    }
}
