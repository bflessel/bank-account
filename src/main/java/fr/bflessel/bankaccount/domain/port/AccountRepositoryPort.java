package fr.bflessel.bankaccount.domain.port;

import fr.bflessel.bankaccount.domain.model.Sum;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import java.util.List;

public interface AccountRepositoryPort {
  Sum getBalance();

  void updateBalance(Sum balance);

  void updateHistory(OperationHistory history);
  List<OperationHistory> getHistory();
}
