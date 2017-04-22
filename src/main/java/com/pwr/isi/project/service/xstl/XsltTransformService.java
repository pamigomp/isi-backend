package com.pwr.isi.project.service.xstl;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.service.exception.UnprocessedEntityException;

public interface XsltTransformService {
    String transformNBPResponse(String currency, String startDate, String endDate);

    String createSubscriptionReport(Subscription subscription) throws UnprocessedEntityException;
}
