package com.pwr.isi.project.repository;

import com.pwr.isi.project.domain.Subscriptions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscriptions, Long> {

  Optional<Subscriptions> findByEmail(String email);

}
