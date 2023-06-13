package fr.bflessel.bankaccount.infra.data.repository;

import fr.bflessel.bankaccount.infra.data.model.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceEntity, Long> {

  BalanceEntity findBalanceEntityByBid(long bid);
}
