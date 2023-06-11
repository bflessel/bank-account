package fr.bflessel.bankaccount.application.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"fr.bflessel.bankaccount"})
@OpenAPIDefinition(
    info = @Info(title = SwaggerConfig.TITLE, version = SwaggerConfig.VERSION, description = SwaggerConfig.DESCRIPTION))
public class SwaggerConfig {

  public static final String TITLE = "API Bank Account";
  public static final String VERSION = "v1";
  public static final String DESCRIPTION = "API basique de Bank Account";
  }

