package fr.bflessel.bankaccount.domain.service;

import fr.bflessel.bankaccount.domain.exception.DepositException;
import java.util.ArrayList;
import java.util.List;

public class DomainAccountService {

  private final List<Double> balance = new ArrayList<>();

  public void deposit(Double amount) throws DepositException {
    if (amount <= 0) {
      throw new DepositException("Valeur incorrecte");
    }
    this.balance.add(amount);
  }

  public int getNumberOfDeposits() {
    return balance.size();
  }

  public void withdraw(double amount) throws DepositException {
    if (amount <= 0) {
      throw new DepositException("Valeur incorrecte");
    }
    this.balance.add(-amount);
  }

  public Double getBalance() {
    return this.balance.stream().mapToDouble(Double::valueOf).sum();
  }
}
