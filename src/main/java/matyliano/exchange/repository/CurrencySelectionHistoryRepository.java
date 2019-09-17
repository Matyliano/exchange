package matyliano.exchange.repository;

import matyliano.exchange.model.CurrencySelectionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CurrencySelectionHistoryRepository extends JpaRepository<CurrencySelectionHistory, Long> {

    CurrencySelectionHistory save(CurrencySelectionHistory currencySelectionHistory);


}
