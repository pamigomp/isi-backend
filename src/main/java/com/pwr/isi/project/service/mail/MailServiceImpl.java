package com.pwr.isi.project.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

  private JavaMailSender javaMailSender;

  @Autowired
  public MailServiceImpl(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Override
  public void placeOrder(String order) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo("podolak.93@gmail.com"); //TODO from Subscription
    message.setFrom("isiprojekt762@gmail.com");
    message.setSubject("Siemanko");
    message.setText("No elo!");
    javaMailSender.send(message);
  }
}
