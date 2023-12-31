package fr.bflessel.bankaccount.infra.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import fr.bflessel.bankaccount.application.mapper.OperationHistoryDtoMapper;
import fr.bflessel.bankaccount.domain.exception.OperationException;
import fr.bflessel.bankaccount.domain.model.Sum;
import fr.bflessel.bankaccount.domain.model.dto.output.OperationHistoryDTO;
import fr.bflessel.bankaccount.domain.model.dto.output.SumDTO;
import fr.bflessel.bankaccount.domain.service.AccountService;
import fr.bflessel.bankaccount.infra.controller.apiresponses.GetApiResponses;
import fr.bflessel.bankaccount.infra.controller.apiresponses.PostApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Bank", description = "Simulateur de banque")
@RequestMapping(path = "/bank/operations")
@RestController
public class BankController {

  private final AccountService accountService;

  public BankController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping(path = "/deposit", consumes = APPLICATION_JSON_VALUE)
  @PostApiResponses
  @Operation(summary = "Dépose de l'argent")
  @ResponseBody
  public ResponseEntity<String> deposit(
      @RequestBody Sum amount) {
    try {
      accountService.deposit(amount);
      return ok().body("Deposit done : " + amount.sum() + " saved");
    } catch (OperationException e) {
      return status(e.getCode()).body(e.getMessage());
    }
  }

  @PostMapping(path = "/withdraw", consumes = APPLICATION_JSON_VALUE)
  @PostApiResponses
  @Operation(summary = "Retire de l'argent")
  @ResponseBody
  public ResponseEntity<String> withdraw(
      @RequestBody Sum amount) {
    try {
      accountService.withdraw(amount);
      return ok().body("Withdrawal done : " + amount.sum() + " taken");
    } catch (OperationException e) {
      return status(e.getCode()).body(e.getMessage());
    }
  }

  @GetMapping(path = "/balance")
  @GetApiResponses
  @Operation(summary = "Obtenir le solde du compte")
  @ResponseBody
  public ResponseEntity<SumDTO> getBalance() {
    return new ResponseEntity<>(SumDTO.toDto(accountService.getBalance().getSum()), HttpStatus.OK);

  }

  @GetMapping(path = "/history")
  @GetApiResponses
  @Operation(summary = "Obtenir l'historique du compte")
  @ResponseBody
  public ResponseEntity<List<OperationHistoryDTO>> getHistory() {
    return new ResponseEntity<>(
        OperationHistoryDtoMapper.toDTOHistory(accountService.getHistory()),
        HttpStatus.OK);

  }

}
