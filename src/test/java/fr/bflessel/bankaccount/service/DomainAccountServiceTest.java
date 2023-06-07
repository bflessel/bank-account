package fr.bflessel.bankaccount.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import fr.bflessel.bankaccount.domain.exception.DepositException;
import fr.bflessel.bankaccount.domain.service.DomainAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DomainAccountServiceTest {

  @Spy
  private DomainAccountService domainAccountService;

  @Test
  void should_make_one_deposit() throws DepositException {
    // WHEN
    domainAccountService.deposit(10.0);

    // THEN
    Mockito.verify(domainAccountService, times(1)).deposit(10.0);
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
    Mockito.verify(domainAccountService, times(2)).deposit(any());
    assertThat(domainAccountService.getNumberOfDeposits()).isEqualTo(2);
  }
}