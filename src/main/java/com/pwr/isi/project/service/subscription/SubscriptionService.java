package com.pwr.isi.project.service.subscription;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.service.dto.subscription.SubscriptionDto;
import com.pwr.isi.project.service.exception.DataConflictException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionService {

  void saveSubscription(SubscriptionDto subscription) throws DataConflictException;

  Page<Subscription> getAllSubscriptions(Pageable pageRequest);

}
