package fr.bflessel.bankaccount.application.mapper;

import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import fr.bflessel.bankaccount.domain.model.dto.output.OperationHistoryDTO;
import fr.bflessel.bankaccount.domain.model.dto.output.builder.OperationHistoryDTOBuilder;
import java.util.List;

public class OperationHistoryDtoMapper {

  private OperationHistoryDtoMapper() {
  }

  public static List<OperationHistoryDTO> toDTOHistory(List<OperationHistory> operationHistories) {
    return operationHistories.stream().map(
            operationHistory -> new OperationHistoryDTOBuilder()
                .setAmount(operationHistory.amount())
                .setBalance(operationHistory.balance())
                .setCalendar(operationHistory.calendar())
                .setType(operationHistory.type()).createOperationHistoryDTO()
        )
        .toList();
  }
}
