package com.pwr.isi.project.web;

import com.pwr.isi.project.service.xstl.XsltTransformService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/exchange/v1/xslt")
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
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getXslt(@RequestParam("currency") String currency,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate) {
        String responseFromNBP = xsltTransformService.transformNBPResponse(currency, startDate, endDate);
        return ok().body(responseFromNBP);
    }
}
