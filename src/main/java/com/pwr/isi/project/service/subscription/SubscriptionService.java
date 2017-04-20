package com.pwr.isi.project.service.subscription;

import com.pwr.isi.project.service.dto.subscription.SubscriptionDto;
import com.pwr.isi.project.service.exception.DataConflictException;

public interface SubscriptionService {

  void saveSubscription(SubscriptionDto subscription) throws DataConflictException;

}
