package fr.bflessel.bankaccount.domain.model;

import fr.bflessel.bankaccount.domain.exception.OperationErrorCode;
import fr.bflessel.bankaccount.domain.exception.OperationException;

public record Sum(double sum) {

  public static Sum of(double sum) {
    return new Sum(sum);
  }

  public Sum add(Double amount) throws OperationException {
    if (amount <= 0.0) {
      throw new OperationException(OperationErrorCode.DEPOSIT_ERROR, "Can't make a negative operation as " +amount);
    }
    return of(sum + amount);
  }

  public Double getSum() {
    return this.sum;
  }

  public Sum subtract(double amount) throws OperationException {
    if (amount <= 0.0) {
      throw new OperationException(OperationErrorCode.WITHDRAWAL_ERROR, "Can't make a negative operation as " +amount);
    }
    return of(sum - amount);

  }
}
