package com.pwr.isi.project.service.subscription;

import static com.pwr.isi.project.service.enums.SubscriptionCurrency.isCurrencyValid;

import com.pwr.isi.project.domain.Subscription;
import com.pwr.isi.project.repository.SubscriptionRepository;
import com.pwr.isi.project.service.dto.subscription.SubscriptionDto;
import com.pwr.isi.project.service.exception.DataConflictException;
import com.pwr.isi.project.service.exception.UnprocessedEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  private SubscriptionRepository subscriptionRepository;

  @Autowired
  public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  @Override
  @Transactional(rollbackFor = UnprocessedEntityException.class)
  public void saveSubscription(SubscriptionDto subscription) throws DataConflictException {
    Optional<Subscription> savedSubscription = subscriptionRepository.findByEmail(subscription.getEmail());
    if (savedSubscription.isPresent() || !isSubscriptionValid(subscription)) throw new DataConflictException();
    subscriptionRepository.save(new Subscription(subscription));
  }

  @Override
  public Page<Subscription> getAllSubscriptions(Pageable pageRequest) {
    return subscriptionRepository.findAll(pageRequest);
  }

  private Boolean isSubscriptionValid(SubscriptionDto subscription) {
    return isEmailValid(subscription.getEmail()) && areCurrenciesValid(subscription.getSubscribedCurrencies());
  }

  private Boolean isEmailValid(String email) {
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
    return matcher.find();
  }

  private Boolean areCurrenciesValid(List<String> currencies) {
    for (String currency : currencies) {
      if (!isCurrencyValid(currency)) return false;
    }
    return true;
  }

}
