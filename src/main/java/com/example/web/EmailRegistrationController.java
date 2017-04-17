package com.example.web;

import com.example.service.dto.subscription.SubscriptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange/v1")
public class EmailRegistrationController {

  private final String RegistrationUrlBase = "/emailServices";


  @RequestMapping(value = RegistrationUrlBase, method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<String> registerEmail(@RequestBody SubscriptionDto emailDto) {
    //TODO save emailServices in database
    //There will be thrown response with code 400 if e.g email doesn't meet criteria

    return new ResponseEntity<>(emailDto.email + "Sucess!", HttpStatus.CREATED);
  }
}
