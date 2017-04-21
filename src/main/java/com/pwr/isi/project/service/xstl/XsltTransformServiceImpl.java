package com.pwr.isi.project.service.xstl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class XsltTransformServiceImpl implements XsltTransformService {

    public static final String NBP_URL_PATTERN_XML = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/?format=xml";
    public static final String XsltFile = "nbp.xsl";
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String TransformNBPResponse(String currency, String startDate, String endDate) {

        String nbpResponse = restTemplate.getForObject(
                String.format(NBP_URL_PATTERN_XML, currency, startDate, endDate),
                String.class);
        log.info(nbpResponse);
        return XsltHelper.ApplyXsltTransformToXml(nbpResponse, XsltFile);
    }
}
