package fr.bflessel.bankaccount.domain.exception;

public class OperationException extends Exception {

  private final int code;

  public OperationException(OperationErrorCode injectionErrorCode, Object... messageParameters) {
    super(String.format(injectionErrorCode.getMessage(), messageParameters));
    this.code = injectionErrorCode.getCode();
  }

  public int getCode() {
    return this.code;
  }
}
