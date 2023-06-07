package fr.bflessel.bankaccount.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import fr.bflessel.bankaccount.domain.exception.OperationException;
import fr.bflessel.bankaccount.domain.model.OperationType;
import fr.bflessel.bankaccount.domain.service.DomainAccountService;
import fr.bflessel.bankaccount.domain.service.OperationHistory;
import fr.bflessel.bankaccount.domain.service.OperationHistoryBuilder;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DomainAccountServiceTest {

  private static MockedStatic<Calendar> mockCalendar;
  @InjectMocks
  private DomainAccountService domainAccountService;

  @BeforeEach
  private void init() {
    Calendar endOfMarch = Calendar.getInstance();
    mockCalendar = Mockito.mockStatic(Calendar.class);
    endOfMarch.set(2023, Calendar.JUNE, 8);
    Mockito.when(Calendar.getInstance()).thenReturn(endOfMarch);

  }

  @AfterEach
  private void reset_mocks() {
    mockCalendar.close();
  }

  @Test
  void should_make_one_deposit() throws OperationException {
    // WHEN
    domainAccountService.deposit(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(1);

  }

  @Test
  void should_make_a_negative_deposits() {
    // WHEN + THEN
    assertThatThrownBy(() -> domainAccountService.deposit(-1.0))
        .isExactlyInstanceOf(OperationException.class)
        .hasMessage("Valeur incorrecte");
  }


  @Test
  void should_make_2_deposits() throws OperationException {
    // WHEN
    domainAccountService.deposit(10.0);
    domainAccountService.deposit(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(2);
  }

  @Test
  void should_make_one_withdrawal() throws OperationException {
    // WHEN
    domainAccountService.withdraw(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(1);
  }

  @Test
  void should_make_a_negative_withdrawal() {
    // WHEN + THEN
    assertThatThrownBy(() -> domainAccountService.withdraw(-1.0))
        .isExactlyInstanceOf(OperationException.class)
        .hasMessage("Valeur incorrecte");
  }


  @Test
  void should_make_2_withdrawals() throws OperationException {
    // WHEN
    domainAccountService.withdraw(10.0);
    domainAccountService.withdraw(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(2);
  }

  @Test
  void should_getBalance_for_one_deposit() throws OperationException {
    // WHEN
    domainAccountService.deposit(10.0);

    // THEN
    assertThat(domainAccountService.getBalance()).isEqualTo(10.0);
  }

  @Test
  void should_getBalance_for_two_deposit() throws OperationException {
    // WHEN
    domainAccountService.deposit(10.0);
    domainAccountService.deposit(20.0);

    // THEN
    assertThat(domainAccountService.getBalance()).isEqualTo(30.0);
  }

  @Test
  void should_getBalance_for_one_withdrawals() throws OperationException {
    // WHEN
    domainAccountService.withdraw(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(1);
    assertThat(domainAccountService.getBalance()).isEqualTo(-10.0);
  }

  @Test
  void should_getBalance_for_operations() throws OperationException {
    // WHEN
    domainAccountService.withdraw(8.0);
    domainAccountService.deposit(100.0);
    domainAccountService.withdraw(410.0);
    domainAccountService.deposit(210.0);
    domainAccountService.withdraw(1120.0);
    domainAccountService.deposit(1550.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(6);
    assertThat(domainAccountService.getBalance()).isEqualTo(322.0);
  }

  @Test
  void should_get_history_for_a_wihdrawal() throws OperationException {
    // GIVEN
    OperationHistory history = new OperationHistoryBuilder()
        .setType(OperationType.WITHDRAWAL)
        .setCalendar(Calendar.getInstance())
        .setAmount(8.0)
        .setBalance(-8.0)
        .createOperationHistory();

    // WHEN
    domainAccountService.withdraw(8.0);

    // THEN
    List<OperationHistory> histories = domainAccountService.getHistory();
    assertThat(histories).isEqualTo(List.of(history));
    assertThat(domainAccountService.getBalance()).isEqualTo(-8.0);
  }

  @Test
  void should_get_history_for_a_deposit() throws OperationException {
    // GIVEN
    OperationHistory history = new OperationHistoryBuilder()
        .setType(OperationType.DEPOSIT)
        .setCalendar(Calendar.getInstance())
        .setAmount(8.0)
        .setBalance(8.0)
        .createOperationHistory();

    // WHEN
    domainAccountService.deposit(8.0);
    // THEN
    List<OperationHistory> histories = domainAccountService.getHistory();
    assertThat(histories).isEqualTo(List.of(history));
    assertThat(domainAccountService.getBalance()).isEqualTo(8.0);
  }


  @Test
  void should_get_history_for_operations() throws OperationException {
    // GIVEN
    List<OperationHistory> history = List.of(
        new OperationHistoryBuilder()
            .setType(OperationType.DEPOSIT)
            .setCalendar(Calendar.getInstance())
            .setAmount(8.0)
            .setBalance(8.0)
            .createOperationHistory(),
        new OperationHistoryBuilder()
            .setType(OperationType.DEPOSIT)
            .setCalendar(Calendar.getInstance())
            .setAmount(8.0)
            .setBalance(16.0)
            .createOperationHistory(),
        new OperationHistoryBuilder()
            .setType(OperationType.DEPOSIT)
            .setCalendar(Calendar.getInstance())
            .setAmount(8.0)
            .setBalance(24.0)
            .createOperationHistory(),
        new OperationHistoryBuilder()
            .setType(OperationType.WITHDRAWAL)
            .setCalendar(Calendar.getInstance())
            .setAmount(10.0)
            .setBalance(14.0)
            .createOperationHistory()
    );

    // WHEN
    domainAccountService.deposit(8.0);
    domainAccountService.deposit(8.0);
    domainAccountService.deposit(8.0);
    domainAccountService.withdraw(10.0);

    // THEN
    List<OperationHistory> histories = domainAccountService.getHistory();
    assertThat(histories).usingRecursiveComparison().isEqualTo(history);
    assertThat(domainAccountService.getBalance()).isEqualTo(14.0);
  }

}