package fr.bflessel.bankaccount.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import fr.bflessel.bankaccount.domain.exception.OperationException;
import fr.bflessel.bankaccount.domain.model.OperationType;
import fr.bflessel.bankaccount.domain.model.Sum;
import fr.bflessel.bankaccount.domain.service.DomainAccountService;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistory;
import fr.bflessel.bankaccount.domain.service.impl.model.OperationHistoryBuilder;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DomainAccountServiceTest {

  private static MockedStatic<Calendar> mockCalendar;
  @InjectMocks
  private DomainAccountService domainAccountService;

  private static Stream<Arguments> sumValues() {
    return Stream.of(
        Arguments.of(10.0, 50.0, 80.0, 0.0),
        Arguments.of(5000.0, 12.5, 17.8, 250.0),
        Arguments.of(10.0, 2000.0, 90.0, -200.0)
    );
  }

  private static Stream<Arguments> wrongValues() {
    return Stream.of(
        Arguments.of(-1000.0),
        Arguments.of(0.0),
        Arguments.of(-2.0)
    );
  }

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

  @ParameterizedTest
  @MethodSource("sumValues")
  void should_make_one_deposit(Double deposit) throws OperationException {
    // WHEN
    domainAccountService.deposit(Sum.of(deposit));

    // THEN
    assertThat(domainAccountService.getBalance()).isEqualTo(Sum.of(deposit));
  }

  @ParameterizedTest
  @MethodSource("wrongValues")
  void should_make_a_negative_deposits(Double value) {
    // WHEN + THEN
    assertThatThrownBy(() -> domainAccountService.deposit(Sum.of(value)))
        .isExactlyInstanceOf(OperationException.class)
        .hasMessage("Error while making a deposit : Can't make a negative operation as " + value);
  }

  @ParameterizedTest
  @MethodSource("sumValues")
  void should_make_several_deposits(Double first, Double second, Double third, Double baseValue) throws OperationException {
    // WHEN
    domainAccountService.setBalance(baseValue);
    domainAccountService.deposit(Sum.of(first));
    domainAccountService.deposit(Sum.of(second));
    domainAccountService.deposit(Sum.of(third));
    // THEN
    assertThat(domainAccountService.getHistory()).hasSize(3);
    assertThat(domainAccountService.getBalance()).isEqualTo(Sum.of(baseValue + first + second + third));
  }

  @ParameterizedTest
  @MethodSource("sumValues")
  void should_make_one_withdrawal(Double withdrawal) throws OperationException {
    // WHEN
    domainAccountService.withdraw(Sum.of(withdrawal));

    // THEN
    assertThat(domainAccountService.getHistory()).hasSize(1);
    assertThat(domainAccountService.getBalance()).isEqualTo(Sum.of(-withdrawal));
  }

  @ParameterizedTest
  @MethodSource("wrongValues")
  void should_make_a_negative_withdrawal(Double value) {
    // WHEN + THEN
    assertThatThrownBy(() -> domainAccountService.withdraw(Sum.of(value)))
        .isExactlyInstanceOf(OperationException.class)
        .hasMessage("Error while making a withdrawal : Can't make a negative operation as " + value);
  }

  @ParameterizedTest
  @MethodSource("sumValues")
  void should_make_several_withdrawals(Double first, Double second, Double third, Double baseValue) throws OperationException {
    // WHEN
    domainAccountService.setBalance(baseValue);
    domainAccountService.withdraw(Sum.of(first));
    domainAccountService.withdraw(Sum.of(second));
    domainAccountService.withdraw(Sum.of(third));
    // THEN
    assertThat(domainAccountService.getHistory()).hasSize(3);
    assertThat(domainAccountService.getBalance()).isEqualTo(Sum.of(baseValue - first - second - third));
  }

  @Test
  void should_getBalance_for_operations() throws OperationException {
    // WHEN
    domainAccountService.withdraw(Sum.of(8.0));
    domainAccountService.deposit(Sum.of(100.0));
    domainAccountService.withdraw(Sum.of(410.0));
    domainAccountService.deposit(Sum.of(210.0));
    domainAccountService.withdraw(Sum.of(1120.0));
    domainAccountService.deposit(Sum.of(1550.0));

    // THEN
    assertThat(domainAccountService.getHistory()).hasSize(6);
    assertThat(domainAccountService.getBalance()).isEqualTo(Sum.of(322.0));
  }

  @Test
  void should_get_history_for_a_wihdrawal() throws OperationException {
    // GIVEN
    OperationHistory history = new OperationHistoryBuilder()
        .setType(OperationType.WITHDRAWAL)
        .setDate(LocalDateTime.now())
        .setAmount(8.0)
        .setBalance(-8.0)
        .createOperationHistory();

    // WHEN
    domainAccountService.withdraw(Sum.of(8.0));

    // THEN
    List<OperationHistory> histories = domainAccountService.getHistory();
    assertThat(histories).isEqualTo(List.of(history));
    assertThat(domainAccountService.getBalance()).isEqualTo(Sum.of(-8.0));
  }

  @Test
  void should_get_history_for_a_deposit() throws OperationException {
    // GIVEN
    OperationHistory history = new OperationHistoryBuilder()
        .setType(OperationType.DEPOSIT)
        .setDate(LocalDateTime.now())
        .setAmount(8.0)
        .setBalance(8.0)
        .createOperationHistory();

    // WHEN
    domainAccountService.deposit(Sum.of(8.0));

    // THEN
    List<OperationHistory> histories = domainAccountService.getHistory();
    assertThat(histories).isEqualTo(List.of(history));
    assertThat(domainAccountService.getBalance()).isEqualTo(Sum.of(8.0));
  }

  @Test
  void should_get_history_for_operations() throws OperationException {
    // GIVEN
    List<OperationHistory> history = List.of(
        new OperationHistoryBuilder()
            .setType(OperationType.DEPOSIT)
            .setDate(LocalDateTime.now())
            .setAmount(8.0)
            .setBalance(8.0)
            .createOperationHistory(),
        new OperationHistoryBuilder()
            .setType(OperationType.DEPOSIT)
            .setDate(LocalDateTime.now())
            .setAmount(8.0)
            .setBalance(16.0)
            .createOperationHistory(),
        new OperationHistoryBuilder()
            .setType(OperationType.DEPOSIT)
            .setDate(LocalDateTime.now())
            .setAmount(8.0)
            .setBalance(24.0)
            .createOperationHistory(),
        new OperationHistoryBuilder()
            .setType(OperationType.WITHDRAWAL)
            .setDate(LocalDateTime.now())
            .setAmount(10.0)
            .setBalance(14.0)
            .createOperationHistory()
    );

    // WHEN
    domainAccountService.deposit(Sum.of(8.0));
    domainAccountService.deposit(Sum.of(8.0));
    domainAccountService.deposit(Sum.of(8.0));
    domainAccountService.withdraw(Sum.of(10.0));

    // THEN
    List<OperationHistory> histories = domainAccountService.getHistory();
    assertThat(histories).usingRecursiveComparison().isEqualTo(history);
    assertThat(domainAccountService.getBalance()).isEqualTo(Sum.of(14.0));
  }

}