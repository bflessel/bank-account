package fr.bflessel.bankaccount.domain.model.dto.output;

import fr.bflessel.bankaccount.domain.model.OperationType;
import java.time.LocalDateTime;

public record OperationHistoryDTO(LocalDateTime date, OperationType type, Double amount, Double balance) {

}
