package fr.bflessel.bankaccount.domain.model.dto.output.builder;

import fr.bflessel.bankaccount.domain.model.OperationType;
import fr.bflessel.bankaccount.domain.model.dto.output.OperationHistoryDTO;
import java.util.Calendar;

public class OperationHistoryDTOBuilder {

  private Calendar calendar;
  private OperationType type;
  private Double amount;
  private Double balance;

  public OperationHistoryDTOBuilder setCalendar(Calendar calendar) {
    this.calendar = calendar;
    return this;
  }

  public OperationHistoryDTOBuilder setType(OperationType type) {
    this.type = type;
    return this;
  }

  public OperationHistoryDTOBuilder setAmount(Double amount) {
    this.amount = amount;
    return this;
  }
  public OperationHistoryDTOBuilder setBalance(Double balance) {
    this.balance = balance;
    return this;
  }

  public OperationHistoryDTO createOperationHistoryDTO() {
    return new OperationHistoryDTO(calendar, type, amount, balance);
  }

}