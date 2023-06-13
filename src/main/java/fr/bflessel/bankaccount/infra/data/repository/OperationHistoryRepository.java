package fr.bflessel.bankaccount.infra.data.repository;

import fr.bflessel.bankaccount.infra.data.model.OperationHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationHistoryRepository extends JpaRepository<OperationHistoryEntity, Long> {

}
