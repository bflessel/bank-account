package fr.bflessel.bankaccount.domain.exception;

public class DepositException extends Exception {

  public DepositException() {
  }

  public DepositException(String cause) {
    super(cause);
  }
}
