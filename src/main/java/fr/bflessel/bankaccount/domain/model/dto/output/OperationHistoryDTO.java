package fr.bflessel.bankaccount.domain.model.dto.output;

import fr.bflessel.bankaccount.domain.model.OperationType;
import java.util.Calendar;

public record OperationHistoryDTO(Calendar calendar, OperationType type, Double amount, Double balance){
    }
