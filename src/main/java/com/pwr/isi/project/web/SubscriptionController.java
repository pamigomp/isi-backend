package com.pwr.isi.project.web;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.service.dto.subscription.SubscriptionDto;
import com.pwr.isi.project.service.exception.DataConflictException;
import com.pwr.isi.project.service.exception.SubscriberNotFound;
import com.pwr.isi.project.service.exception.UnprocessedEntityException;
import com.pwr.isi.project.service.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exchange/v1")
public class SubscriptionController {

  private static final String SUCCESS_MESSAGE = "User %s was subscribed correctly!";

  private SubscriptionService subscriptionService;

  @Autowired
  public SubscriptionController(SubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
  }

  /**
   * @param subscription subscriptionDto
   */
  @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity subscribeUser(@RequestBody SubscriptionDto subscription) {
    try {
      subscriptionService.saveSubscription(subscription);
    } catch (DataConflictException e) {
      return badRequest().body(e.getMessage());
    }
    return ok().body(String.format(SUCCESS_MESSAGE, subscription.getEmail()));
  }

  /**
   * @param pageRequest page, size, sort
   */
  @RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
  public Page<Subscription> getAllSubscriptions(Pageable pageRequest) {
    return subscriptionService.getAllSubscriptions(pageRequest);
  }

  /**
   * @param email subscriber email
   */
  @RequestMapping(value = "/send_subscription_report", method = RequestMethod.GET)
  public ResponseEntity sendSubscriptionReportForGivenUser(@RequestParam("email") String email) {
    try {
      subscriptionService.sendSubscription(email);
      return ok().body("Report sent correctly!");
    } catch (SubscriberNotFound subscriberNotFound) {
      subscriberNotFound.printStackTrace();
      return notFound().build();
    } catch (UnprocessedEntityException e) {
      e.printStackTrace();
      return badRequest().body(e.getMessage());
    }
  }
}
