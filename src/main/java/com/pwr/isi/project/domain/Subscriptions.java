package com.pwr.isi.project.domain;

import com.pwr.isi.project.service.enums.FrequencyValue;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Entity(name = "SUBSCRIPTIONS")
@Getter
public class Subscriptions implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;
  @Column(name = "TECH_CREATION_TIME")
  private ZonedDateTime creationTime;
  @Column(name = "TECH_UPDATE_TIME")
  private ZonedDateTime updateTime;
  @Column(name = "EMAIL", nullable = false)
  private String email;
  @Column(name = "FREQUENCY", nullable = false)
  private String frequency;
  @Column(name = "CURRENCIES", nullable = false)
  private String currencies;

  public Subscriptions(String email, FrequencyValue frequency, String currencies) {
    this.email = email;
    this.frequency = frequency.toString();
    this.currencies = currencies;
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
    Subscriptions other = (Subscriptions) obj;
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
    creationTime = ZonedDateTime.now();
    updateTime = ZonedDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updateTime = ZonedDateTime.now();
  }
}