package fr.bflessel.bankaccount.domain.service.impl.model;

import fr.bflessel.bankaccount.domain.model.OperationType;
import java.util.Calendar;

public class OperationHistoryBuilder {

  private Calendar calendar;
  private OperationType type;
  private Double amount;
  private Double balance;

  public OperationHistoryBuilder setCalendar(Calendar calendar) {
    this.calendar = calendar;
    return this;
  }

  public OperationHistoryBuilder setType(OperationType type) {
    this.type = type;
    return this;
  }

  public OperationHistoryBuilder setAmount(Double amount) {
    this.amount = amount;
    return this;
  }
  public OperationHistoryBuilder setBalance(Double balance) {
    this.balance = balance;
    return this;
  }

  public OperationHistory createOperationHistory() {
    return new OperationHistory(calendar, type, amount, balance);
  }

}