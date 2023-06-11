package fr.bflessel.bankaccount.infra.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import fr.bflessel.bankaccount.domain.exception.OperationErrorCode;
import fr.bflessel.bankaccount.domain.exception.OperationException;
import fr.bflessel.bankaccount.domain.model.OperationType;
import fr.bflessel.bankaccount.domain.model.Sum;
import fr.bflessel.bankaccount.domain.model.dto.output.OperationHistoryDTO;
import fr.bflessel.bankaccount.domain.model.dto.output.SumDTO;
import fr.bflessel.bankaccount.domain.model.dto.output.builder.OperationHistoryDTOBuilder;
import fr.bflessel.bankaccount.domain.service.DomainAccountService;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistoryBuilder;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class BankControllerTest {

  private BankController controller;

  @Mock
  private DomainAccountService accountService;

  @BeforeEach
  void setUp() {
    controller = new BankController(accountService);
  }

  @Test
  void deposit() throws OperationException {
    // GIVEN + WHEN + THEN
    assertThat(controller.deposit(Sum.of(10.0))).usingRecursiveComparison().isEqualTo(ok().body("Deposit done : " + 10.0+ " saved"));
    verify(accountService).deposit(Sum.of(10.0));
  }

  @Test
  void deposit_with_negative_sum() throws OperationException {
    // GIVEN + WHEN + THEN
    Mockito.doThrow(new OperationException(OperationErrorCode.DEPOSIT_ERROR, "Can't make a negative operation as " + -10.0)).when(accountService).deposit(Sum.of(-10.0));
    assertThat(controller.deposit(Sum.of(-10.0))).usingRecursiveComparison().isEqualTo(status(OperationErrorCode.DEPOSIT_ERROR.getCode()).body("Error while making a deposit : Can't make a negative operation as -10.0"));
    verify(accountService).deposit(Sum.of(-10.0));
  }

  @Test
  void withdrawal() throws OperationException {
    // GIVEN + WHEN + THEN
    assertThat(controller.withdraw(Sum.of(10.0))).usingRecursiveComparison().isEqualTo(ok().body("Withdrawal done : " + 10.0+ " taken"));
    verify(accountService).withdraw(Sum.of(10.0));
  }

  @Test
  void withdrawal_with_negative_sum() throws OperationException {
    // GIVEN + WHEN + THEN
    Mockito.doThrow(new OperationException(OperationErrorCode.WITHDRAWAL_ERROR, "Can't make a negative operation as " + -10.0)).when(accountService).withdraw(Sum.of(-10.0));
    assertThat(controller.withdraw(Sum.of(-10.0))).usingRecursiveComparison().isEqualTo(status(OperationErrorCode.WITHDRAWAL_ERROR.getCode()).body("Error while making a withdrawal : Can't make a negative operation as -10.0"));
    verify(accountService).withdraw(Sum.of(-10.0));
  }

  @Test
  void getBalance() {
    when(accountService.getBalance()).thenReturn(Sum.of(100.0));
    // GIVEN + WHEN + THEN
    assertThat(controller.getBalance()).usingRecursiveComparison().isEqualTo(new ResponseEntity<>(SumDTO.toDto(100.0), HttpStatus.OK));
    verify(accountService).getBalance();
  }

  @Test
  void getHistory() {
    Calendar calendar = Calendar.getInstance();

    List<OperationHistoryDTO> historyDTO = List.of(
        getHistoryDTO(OperationType.DEPOSIT, calendar, 8.0, 8.0),
        getHistoryDTO(OperationType.DEPOSIT, calendar, 8.0, 16.0),
        getHistoryDTO(OperationType.DEPOSIT, calendar, 8.0, 24.0),
        getHistoryDTO(OperationType.WITHDRAWAL, calendar, 10.0, 14.0)
    );
    List<OperationHistory> history = List.of(
        getOperationHistory(OperationType.DEPOSIT, calendar, 8.0, 8.0),
        getOperationHistory(OperationType.DEPOSIT, calendar, 8.0, 16.0),
        getOperationHistory(OperationType.DEPOSIT, calendar, 8.0, 24.0),
        getOperationHistory(OperationType.WITHDRAWAL, calendar, 10.0, 14.0)
    );
    when(accountService.getHistory()).thenReturn(history);
    // GIVEN + WHEN + THEN
    assertThat(controller.getHistory()).usingRecursiveComparison().isEqualTo(new ResponseEntity<>(
        historyDTO,
        HttpStatus.OK));
    verify(accountService).getHistory();
  }

  private OperationHistoryDTO getHistoryDTO(OperationType withdrawal, Calendar calendar, double amount, double balance) {
    return new OperationHistoryDTOBuilder()
        .setType(withdrawal)
        .setCalendar(calendar)
        .setAmount(amount)
        .setBalance(balance)
        .createOperationHistoryDTO();
  }

  private OperationHistory getOperationHistory(OperationType deposit, Calendar calendar, double amount, double amount1) {
    return new OperationHistoryBuilder()
        .setType(deposit)
        .setCalendar(calendar)
        .setAmount(amount)
        .setBalance(amount1)
        .createOperationHistory();
  }

}