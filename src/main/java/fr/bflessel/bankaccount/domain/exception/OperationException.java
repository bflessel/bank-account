package fr.bflessel.bankaccount.domain.exception;

public class OperationException extends Exception {

  public OperationException() {
  }

  public OperationException(String cause) {
    super(cause);
  }
}
