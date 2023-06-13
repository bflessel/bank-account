package fr.bflessel.bankaccount.infra.data.model;

import fr.bflessel.bankaccount.domain.model.OperationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "OPERATION_HISTORY")
public class OperationHistoryEntity {

  @Id
  @Column(name = "OHID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ohid;

  @Column(name = "UPDATE_DATE")
  private LocalDateTime updateDate;

  @Column(name = "OPERATION_TYPE")
  private OperationType type;

  @Column(name = "OPERATION_AMOUNT")
  private Double amount;

  @Column(name = "BALANCE")
  private Double balance;

  public OperationHistoryEntity() {
  }

  public OperationHistoryEntity(int ohid, LocalDateTime updateDate, OperationType type, Double amount, Double balance) {
    this.ohid = ohid;
    this.updateDate = updateDate;
    this.type = type;
    this.amount = amount;
    this.balance = balance;
  }

  public LocalDateTime getUpdateDate() {
    return updateDate;
  }

  public OperationType getType() {
    return type;
  }

  public Double getAmount() {
    return amount;
  }

  public Double getBalance() {
    return balance;
  }
}
