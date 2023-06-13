package fr.bflessel.bankaccount.domain.service.impl.model;

import fr.bflessel.bankaccount.domain.model.OperationType;
import java.time.LocalDateTime;

public class OperationHistoryBuilder {

  private LocalDateTime date;
  private OperationType type;
  private Double amount;
  private Double balance;

  public OperationHistoryBuilder setDate(LocalDateTime date) {
    this.date = date;
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
    return new OperationHistory(date, type, amount, balance);
  }

}