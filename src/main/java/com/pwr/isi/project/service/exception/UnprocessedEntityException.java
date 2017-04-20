package com.pwr.isi.project.service.exception;

public class UnprocessedEntityException extends Exception {
  public UnprocessedEntityException() {
    super("Resource could not be processed, wrong parameters supplied");
  }
}
