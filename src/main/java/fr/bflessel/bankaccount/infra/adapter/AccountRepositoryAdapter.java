package fr.bflessel.bankaccount.infra.adapter;

import fr.bflessel.bankaccount.domain.model.Sum;
import fr.bflessel.bankaccount.domain.port.AccountRepositoryPort;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import fr.bflessel.bankaccount.infra.data.mapper.BalanceEntityMapper;
import fr.bflessel.bankaccount.infra.data.mapper.OperationHistoryMapper;
import fr.bflessel.bankaccount.infra.data.repository.BalanceRepository;
import fr.bflessel.bankaccount.infra.data.repository.OperationHistoryRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryAdapter implements AccountRepositoryPort {

  private final BalanceRepository balanceRepository;
  private final OperationHistoryRepository historyRepository;

  public AccountRepositoryAdapter(BalanceRepository balanceRepository, OperationHistoryRepository historyRepository) {
    this.balanceRepository = Objects.requireNonNull(balanceRepository);
    this.historyRepository = Objects.requireNonNull(historyRepository);
  }

  @Override
  public Sum getBalance() {
    return Sum.of(balanceRepository.findBalanceEntityByBid(1L).getSum());
  }

  @Override
  public void updateBalance(Sum balance) {
    balanceRepository.save(BalanceEntityMapper.toBalanceEntity(balance));
  }

  @Override
  public void updateHistory(OperationHistory history) {
     historyRepository.save(OperationHistoryMapper.toHistoryEntity(history));

  }

  @Override
  public List<OperationHistory> getHistory() {
    return OperationHistoryMapper.toOperationHistoryList(historyRepository.findAll());
  }

}
