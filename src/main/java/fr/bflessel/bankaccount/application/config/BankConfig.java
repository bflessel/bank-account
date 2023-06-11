package fr.bflessel.bankaccount.application.config;

import fr.bflessel.bankaccount.domain.service.AccountService;
import fr.bflessel.bankaccount.domain.service.DomainAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankConfig {

  @Bean
  public AccountService accountService() {
    return new DomainAccountService();
  }
}
