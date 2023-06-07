package fr.bflessel.bankaccount.domain.service;

import fr.bflessel.bankaccount.domain.exception.DepositException;

public class DomainAccountService {

  private Double balance = 0.0;

  public void deposit(Double amount) throws DepositException {
    if(amount <0)
      throw new DepositException("Valeur incorrecte");
     this.balance = amount;
  }

}
