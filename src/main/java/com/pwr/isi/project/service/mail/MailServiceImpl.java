package com.pwr.isi.project.service.mail;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.service.exception.UnprocessedEntityException;
import com.pwr.isi.project.service.xstl.XsltTransformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

  private static final String SUBJECT = "%s reports for currencies: %s";

  @Value("${spring.mail.username}")
  private String sendersEmail;

  private MailSender mailSender;
  private XsltTransformService xsltTransformService;

  @Autowired
  public MailServiceImpl(MailSender mailSender, XsltTransformService xsltTransformService) {
    this.mailSender = mailSender;
    this.xsltTransformService = xsltTransformService;
  }

  @Override
  public void placeOrder(Subscription subscription) throws UnprocessedEntityException {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(subscription.getEmail());
    message.setFrom(sendersEmail);
    message.setSubject(String.format(SUBJECT, subscription.getFrequency(), subscription.getCurrencies()));
    message.setText(xsltTransformService.createSubscriptionReport(subscription));
    mailSender.send(message);
  }
}
