package fr.bflessel.bankaccount.domain.service;

import fr.bflessel.bankaccount.domain.exception.OperationException;
import fr.bflessel.bankaccount.domain.model.OperationType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DomainAccountService {

  private final List<OperationHistory> history = new ArrayList<>();
  private Double balance = 0.0;

  public void deposit(Double amount) throws OperationException {
    if (amount <= 0) {
      throw new OperationException("Valeur incorrecte");
    }
    this.balance += amount;
    this.history.add(new OperationHistoryBuilder()
        .setType(OperationType.DEPOSIT)
        .setCalendar(Calendar.getInstance())
        .setAmount(amount)
        .setBalance(this.balance)
        .createOperationHistory());
  }

  public int getNumberOfDeposits() {
    return history.size();
  }

  public void withdraw(double amount) throws OperationException {
    if (amount <= 0) {
      throw new OperationException("Valeur incorrecte");
    }

    this.balance -= amount;
    this.history.add(new OperationHistoryBuilder()
        .setType(OperationType.WITHDRAWAL)
        .setCalendar(Calendar.getInstance())
        .setAmount(amount)
        .setBalance(this.balance)
        .createOperationHistory());
  }

  public Double getBalance() {
    return this.balance;
  }

  public List<OperationHistory> getHistory() {
    return history;
  }
}
