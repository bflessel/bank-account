package fr.bflessel.bankaccount.service;

import static org.mockito.Mockito.times;

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
  void should_make_one_deposit() {
    // WHEN
    domainAccountService.deposit(10.0);

    // THEN
    Mockito.verify(domainAccountService, times(1)).deposit(10.0);
  }
}