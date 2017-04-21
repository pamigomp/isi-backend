package com.pwr.isi.project.repository;

import com.pwr.isi.project.domain.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

  Page<Subscription> findAll(Pageable pageRequest);

  Optional<Subscription> findByEmail(String email);

}
