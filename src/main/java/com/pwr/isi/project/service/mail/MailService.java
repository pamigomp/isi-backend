package com.pwr.isi.project.service.mail;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.service.exception.UnprocessedEntityException;

import javax.mail.MessagingException;

public interface MailService {

  void placeOrder(Subscription subscription) throws UnprocessedEntityException, MessagingException;
}
