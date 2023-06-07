package fr.bflessel.bankaccount.domain.service;

import fr.bflessel.bankaccount.domain.model.OperationType;
import java.util.Calendar;

public record OperationHistory(Calendar calendar, OperationType type, Double amount, Double balance) {
}
