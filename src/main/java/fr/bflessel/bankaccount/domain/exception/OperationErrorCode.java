package fr.bflessel.bankaccount.domain.exception;

public enum OperationErrorCode {
  DEPOSIT_ERROR(500, "Error while making a deposit : %s"),
  WITHDRAWAL_ERROR(500, "Error while making a withdrawal : %s");
  private final int code;
  private final String message;

  OperationErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

}
