package fr.bflessel.bankaccount.domain.service;

public class DomainAccountService {

  private Double balance = 0.0;

  public void deposit(Double amount) {
     this.balance = amount;
  }

}
