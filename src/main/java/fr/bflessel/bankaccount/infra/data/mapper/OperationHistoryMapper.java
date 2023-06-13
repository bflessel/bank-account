package fr.bflessel.bankaccount.infra.data.mapper;

import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistoryBuilder;
import fr.bflessel.bankaccount.infra.data.model.OperationHistoryEntity;
import java.util.List;

public class OperationHistoryMapper {

  private OperationHistoryMapper() {
  }

  public static OperationHistoryEntity toHistoryEntity(OperationHistory history) {
    return new OperationHistoryEntityBuilder()
        .setAmount(history.amount())
        .setBalance(history.balance())
        .setUpdateDate(history.date())
        .setType(history.type())
        .createOperationHistoryEntity();
  }

  public static List<OperationHistory> toOperationHistoryList(List<OperationHistoryEntity> entities) {
    return entities.stream().map(OperationHistoryMapper::toOperationHistory).toList();
  }

  private static OperationHistory toOperationHistory(OperationHistoryEntity entitiy) {
    return new OperationHistoryBuilder()
        .setAmount(entitiy.getAmount())
        .setBalance(entitiy.getBalance())
        .setType(entitiy.getType())
        .setDate((entitiy.getUpdateDate()))
        .createOperationHistory();
  }
}
