package com.pwr.isi.project.service.subscription;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.service.dto.subscription.SubscriptionDto;
import com.pwr.isi.project.service.exception.DataConflictException;
import com.pwr.isi.project.service.exception.SubscriberNotFound;
import com.pwr.isi.project.service.exception.UnprocessedEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;

public interface SubscriptionService {

  void saveSubscription(SubscriptionDto subscription) throws DataConflictException;

  Page<Subscription> getAllSubscriptions(Pageable pageRequest);

  void sendSubscription(String subscribersEmail) throws SubscriberNotFound, UnprocessedEntityException, MessagingException;

}
