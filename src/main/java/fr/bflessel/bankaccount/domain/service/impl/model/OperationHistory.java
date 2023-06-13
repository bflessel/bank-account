package fr.bflessel.bankaccount.domain.service.impl.model;

import fr.bflessel.bankaccount.domain.model.OperationType;
import java.time.LocalDateTime;

public record OperationHistory(LocalDateTime date, OperationType type, Double amount, Double balance) {

}
