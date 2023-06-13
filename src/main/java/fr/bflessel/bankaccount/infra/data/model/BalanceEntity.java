package fr.bflessel.bankaccount.infra.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BALANCE")
public class BalanceEntity {

  @Id
  @Column(name = "BID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int bid;

  @Column(name = "SUM")
  private double sum;

  private BalanceEntity(int bid, double sum) {
    this.bid = bid;
    this.sum = sum;
  }

  public BalanceEntity() {

  }

  public static BalanceEntity of(int bid, Double sum) {
    return new BalanceEntity(bid, sum);
  }

  public double getSum() {
    return sum;
  }

  public void setSum(double sum) {
    this.sum = sum;
  }

  public int getBid() {
    return bid;
  }

  public void setBid(int bid) {
    this.bid = bid;
  }
}
