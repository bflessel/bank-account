package fr.bflessel.bankaccount.application.config;

import fr.bflessel.bankaccount.domain.service.AccountService;
import fr.bflessel.bankaccount.domain.service.DomainAccountService;
import fr.bflessel.bankaccount.infra.adapter.AccountRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankConfig {

  @Bean
  public AccountService accountService(AccountRepositoryAdapter accountRepositoryAdapter) {
    return new DomainAccountService(accountRepositoryAdapter);
  }
}
