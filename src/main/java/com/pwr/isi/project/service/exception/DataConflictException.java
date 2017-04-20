package com.pwr.isi.project.service.exception;

public class DataConflictException extends Exception {
  public DataConflictException() {
    super("Resource could not be processed due to a conflict");
  }
}
