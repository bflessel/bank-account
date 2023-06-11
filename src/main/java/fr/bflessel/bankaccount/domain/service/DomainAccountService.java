package fr.bflessel.bankaccount.domain.service;

import fr.bflessel.bankaccount.domain.exception.OperationException;
import fr.bflessel.bankaccount.domain.model.OperationType;
import fr.bflessel.bankaccount.domain.model.Sum;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistoryBuilder;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DomainAccountService implements AccountService {

  private final List<OperationHistory> history = new LinkedList<>();
  private Sum balance = initBalance();

  private Sum initBalance() {
    return Sum.of(0.0);
  }

  public void deposit(Sum amount) throws OperationException {
      this.balance = this.balance.add(amount.sum());
      this.history.add(new OperationHistoryBuilder()
          .setType(OperationType.DEPOSIT)
          .setCalendar(Calendar.getInstance())
          .setAmount(amount.sum())
          .setBalance(this.balance.getSum())
          .createOperationHistory());
  }



  public void withdraw(Sum amount) throws OperationException {
    this.balance = this.balance.subtract(amount.sum());
    this.history.add(new OperationHistoryBuilder()
        .setType(OperationType.WITHDRAWAL)
        .setCalendar(Calendar.getInstance())
        .setAmount(amount.sum())
        .setBalance(this.balance.getSum())
        .createOperationHistory());
  }

  public Sum getBalance() {
    return this.balance;
  }

  public void setBalance(Double base) {
    this.balance = Sum.of(base);
  }

  public List<OperationHistory> getHistory() {
    return history;
  }
}
