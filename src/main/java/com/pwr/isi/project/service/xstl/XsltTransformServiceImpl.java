package com.pwr.isi.project.service.xstl;

import com.pwr.isi.project.service.external.ExternalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class XsltTransformServiceImpl implements XsltTransformService {

    public static final String XsltFile = "nbp.xsl";
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
}
