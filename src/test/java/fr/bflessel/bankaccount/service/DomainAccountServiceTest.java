package fr.bflessel.bankaccount.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import fr.bflessel.bankaccount.domain.exception.DepositException;
import fr.bflessel.bankaccount.domain.service.DomainAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DomainAccountServiceTest {

  @InjectMocks
  private DomainAccountService domainAccountService;

  @Test
  void should_make_one_deposit() throws DepositException {
    // WHEN
    domainAccountService.deposit(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(1);

  }

  @Test
  void should_make_a_negative_deposits() {
    // WHEN + THEN
    assertThatThrownBy(() -> domainAccountService.deposit(-1.0))
        .isExactlyInstanceOf(DepositException.class)
        .hasMessage("Valeur incorrecte");
  }


  @Test
  void should_make_2_deposits() throws DepositException {
    // WHEN
    domainAccountService.deposit(10.0);
    domainAccountService.deposit(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(2);
  }

  @Test
  void should_make_one_withdrawal() throws DepositException {
    // WHEN
    domainAccountService.withdraw(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(1);
  }

  @Test
  void should_make_a_negative_withdrawal() {
    // WHEN + THEN
    assertThatThrownBy(() -> domainAccountService.withdraw(-1.0))
        .isExactlyInstanceOf(DepositException.class)
        .hasMessage("Valeur incorrecte");
  }


  @Test
  void should_make_2_withdrawals() throws DepositException {
    // WHEN
    domainAccountService.withdraw(10.0);
    domainAccountService.withdraw(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(2);
  }

  @Test
  void should_getBalance_for_one_deposit() throws DepositException {
    // WHEN
    domainAccountService.deposit(10.0);

    // THEN
    assertThat(domainAccountService.getBalance()).isEqualTo(10.0);
  }

  @Test
  void should_getBalance_for_two_deposit() throws DepositException {
    // WHEN
    domainAccountService.deposit(10.0);
    domainAccountService.deposit(20.0);

    // THEN
    assertThat(domainAccountService.getBalance()).isEqualTo(30.0);
  }

  @Test
  void should_getBalance_for_one_withdrawals() throws DepositException {
    // WHEN
    domainAccountService.withdraw(10.0);

    // THEN
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(1);
    assertThat(domainAccountService.getBalance()).isEqualTo(-10.0);
  }

  @Test
  void should_getBalance_for_operations() throws DepositException {
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



}