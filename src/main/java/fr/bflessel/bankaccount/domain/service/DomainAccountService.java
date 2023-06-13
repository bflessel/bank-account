package fr.bflessel.bankaccount.domain.service;

import fr.bflessel.bankaccount.domain.exception.OperationException;
import fr.bflessel.bankaccount.domain.model.OperationType;
import fr.bflessel.bankaccount.domain.model.Sum;
import fr.bflessel.bankaccount.domain.port.AccountRepositoryPort;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistoryBuilder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class DomainAccountService implements AccountService {

  private final AccountRepositoryPort accountRepository;
  private Sum balance;

  public DomainAccountService(AccountRepositoryPort accountRepository) {
    this.accountRepository = Objects.requireNonNull(accountRepository);
    this.balance = initBalance();
  }

  private Sum initBalance() {
    assert accountRepository != null;
    Sum originalBalance = accountRepository.getBalance();
    return originalBalance != null ? originalBalance : Sum.of(0.0);
  }

  public void deposit(Sum amount) throws OperationException {
    Sum add = this.balance.add(amount.sum());

    accountRepository.updateBalance(this.balance);
    this.balance = add;
    OperationHistory operationHistory = new OperationHistoryBuilder()
        .setType(OperationType.DEPOSIT)
        .setDate(LocalDateTime.now())
        .setAmount(amount.sum())
        .setBalance(this.balance.getSum())
        .createOperationHistory();
    accountRepository.updateHistory(operationHistory);
  }

  public void withdraw(Sum amount) throws OperationException {
    this.balance = this.balance.subtract(amount.sum());
    OperationHistory operationHistory = (new OperationHistoryBuilder()
        .setType(OperationType.WITHDRAWAL)
        .setDate(LocalDateTime.now())
        .setAmount(amount.sum())
        .setBalance(this.balance.getSum())
        .createOperationHistory());
    accountRepository.updateHistory(operationHistory);
  }

  public Sum getBalance() {
    return this.balance;
  }

  public void setBalance(Double base) {
    this.balance = Sum.of(base);
  }

  public List<OperationHistory> getHistory() {
    return accountRepository.getHistory();
  }
}
