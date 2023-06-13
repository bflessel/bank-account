package fr.bflessel.bankaccount.infra.data.mapper;

import fr.bflessel.bankaccount.domain.model.OperationType;
import fr.bflessel.bankaccount.infra.data.model.OperationHistoryEntity;
import java.time.LocalDateTime;

public class OperationHistoryEntityBuilder {

  private int ohid;
  private LocalDateTime updateDate;
  private OperationType type;
  private Double amount;
  private Double balance;

  public OperationHistoryEntityBuilder setBid(int ohid) {
    this.ohid = ohid;
    return this;
  }

  public OperationHistoryEntityBuilder setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  public OperationHistoryEntityBuilder setType(OperationType type) {
    this.type = type;
    return this;
  }

  public OperationHistoryEntityBuilder setAmount(Double amount) {
    this.amount = amount;
    return this;
  }

  public OperationHistoryEntityBuilder setBalance(Double balance) {
    this.balance = balance;
    return this;
  }

  public OperationHistoryEntity createOperationHistoryEntity() {
    return new OperationHistoryEntity(ohid, updateDate, type, amount, balance);
  }
}