package fr.bflessel.bankaccount.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import fr.bflessel.bankaccount.domain.model.OperationType;
import fr.bflessel.bankaccount.domain.model.dto.output.OperationHistoryDTO;
import fr.bflessel.bankaccount.domain.model.dto.output.builder.OperationHistoryDTOBuilder;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistoryBuilder;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.Test;

class OperationHistoryDtoMapperTest {

  @Test
  void toDTOHistory() {
    //GIVEN
    Calendar calendar = Calendar.getInstance();
    List<OperationHistory> history = List.of(
        new OperationHistoryBuilder()
            .setType(OperationType.DEPOSIT)
            .setCalendar(calendar)
            .setAmount(8.0)
            .setBalance(8.0)
            .createOperationHistory(),
        new OperationHistoryBuilder()
            .setType(OperationType.DEPOSIT)
            .setCalendar(calendar)
            .setAmount(8.0)
            .setBalance(16.0)
            .createOperationHistory(),
        new OperationHistoryBuilder()
            .setType(OperationType.DEPOSIT)
            .setCalendar(calendar)
            .setAmount(8.0)
            .setBalance(24.0)
            .createOperationHistory(),
        new OperationHistoryBuilder()
            .setType(OperationType.WITHDRAWAL)
            .setCalendar(calendar)
            .setAmount(10.0)
            .setBalance(14.0)
            .createOperationHistory()
    );
    List<OperationHistoryDTO> historyDTO = List.of(
        new OperationHistoryDTOBuilder()
            .setType(OperationType.DEPOSIT)
            .setCalendar(calendar)
            .setAmount(8.0)
            .setBalance(8.0)
            .createOperationHistoryDTO(),
        new OperationHistoryDTOBuilder()
            .setType(OperationType.DEPOSIT)
            .setCalendar(calendar)
            .setAmount(8.0)
            .setBalance(16.0)
            .createOperationHistoryDTO(),
        new OperationHistoryDTOBuilder()
            .setType(OperationType.DEPOSIT)
            .setCalendar(calendar)
            .setAmount(8.0)
            .setBalance(24.0)
            .createOperationHistoryDTO(),
        new OperationHistoryDTOBuilder()
            .setType(OperationType.WITHDRAWAL)
            .setCalendar(calendar)
            .setAmount(10.0)
            .setBalance(14.0)
            .createOperationHistoryDTO()
    );
    assertThat(OperationHistoryDtoMapper.toDTOHistory(history)).isEqualTo(historyDTO);
  }
}