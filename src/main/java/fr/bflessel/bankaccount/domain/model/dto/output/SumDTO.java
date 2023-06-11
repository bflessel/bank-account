package fr.bflessel.bankaccount.domain.model.dto.output;

import fr.bflessel.bankaccount.domain.model.Sum;

public class SumDTO {

  private final Double sum;

  public SumDTO(Double sum) {
    this.sum = sum;
  }

  public static SumDTO toDto(Double sum) {
    return new SumDTO(sum);
  }
  public static SumDTO toDto(Sum sum) {
    return new SumDTO(sum.getSum());
  }

  public Double getSum() {
    return sum;
  }
}
