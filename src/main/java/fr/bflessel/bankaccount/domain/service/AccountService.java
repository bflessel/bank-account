package fr.bflessel.bankaccount.domain.service;

import fr.bflessel.bankaccount.domain.exception.OperationException;
import fr.bflessel.bankaccount.domain.model.Sum;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import java.util.List;

public interface AccountService {
  void deposit(Sum amount) throws OperationException;
  void withdraw(Sum amount) throws OperationException;
  Sum getBalance();
  List<OperationHistory> getHistory();
}
