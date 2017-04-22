package com.pwr.isi.project.service.exception;

public class SubscriberNotFound extends Exception {
  public SubscriberNotFound() {
    super("Subscriber could not be found");
  }
}
