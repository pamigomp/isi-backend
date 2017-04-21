package com.pwr.isi.project.domain;

import com.pwr.isi.project.service.dto.subscription.SubscriptionDto;
import com.pwr.isi.project.service.enums.FrequencyValue;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity(name = "SUBSCRIPTIONS")
@Getter
@NoArgsConstructor
public class Subscription implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "TECH_CREATION_TIME")
  private Date creationTime;
  @Column(name = "TECH_UPDATE_TIME")
  private Date updateTime;
  @Column(name = "EMAIL", nullable = false)
  private String email;
  @Column(name = "FREQUENCY", nullable = false)
  private String frequency;
  @Column(name = "CURRENCIES", nullable = false)
  private String currencies;

  public Subscription(String email, FrequencyValue frequency, String currencies) {
    this.email = email;
    this.frequency = frequency.toString();
    this.currencies = currencies;
  }

  public Subscription(SubscriptionDto subscriptionDto) {
    this.email = subscriptionDto.getEmail();
    this.frequency = subscriptionDto.getSubsciptionFrequency().name();
    this.currencies = String.join(",", subscriptionDto.getSubscribedCurrencies());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Subscription other = (Subscription) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
      return super.equals(other);
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return id == null ? super.hashCode() : id.hashCode();
  }

  @Override
  public String toString() {
    return "Subscription(id=" + getId() + ")";
  }

  @PrePersist
  protected void onCreate() {
    creationTime = Date.from(Instant.now());
    updateTime = Date.from(Instant.now());
  }

  @PreUpdate
  protected void onUpdate() {
    updateTime = Date.from(Instant.now());
  }
}