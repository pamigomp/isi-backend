package com.pwr.isi.project.service.mail;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.service.exception.UnprocessedEntityException;
import com.pwr.isi.project.service.xstl.XsltTransformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

  private static final String SUBJECT = "%s reports for currencies: %s";

  @Value("${spring.mail.username}")
  private String sendersEmail;

  private JavaMailSender javaMailSender;
  private XsltTransformService xsltTransformService;

  @Autowired
  public MailServiceImpl(JavaMailSender javaMailSender, XsltTransformService xsltTransformService) {
    this.javaMailSender = javaMailSender;
    this.xsltTransformService = xsltTransformService;
  }

  @Override
  public void placeOrder(Subscription subscription) throws MessagingException, UnprocessedEntityException {
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper msgHelper = new MimeMessageHelper(message, true);

    msgHelper.setFrom(sendersEmail);
    msgHelper.setTo(subscription.getEmail());
    msgHelper.setSubject(String.format(SUBJECT, subscription.getFrequency(), subscription.getTargetCurrency()));
    msgHelper.setText(xsltTransformService.createSubscriptionReport(subscription), true);
    javaMailSender.send(message);
  }
}
