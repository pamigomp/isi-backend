package com.pwr.isi.project.service.mail;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.service.exception.UnprocessedEntityException;

public interface MailService {

  void placeOrder(Subscription subscription) throws UnprocessedEntityException;
}
