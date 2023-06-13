package fr.bflessel.bankaccount.infra.data.mapper;

import fr.bflessel.bankaccount.domain.model.Sum;
import fr.bflessel.bankaccount.infra.data.model.BalanceEntity;

public class BalanceEntityMapper {

  private BalanceEntityMapper() {
  }

  public static BalanceEntity toBalanceEntity(Sum sum){
      return  BalanceEntity.of(1, sum.getSum());
    }
}
