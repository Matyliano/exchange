package matyliano.exchange.service;


import matyliano.exchange.model.CurrencySelectionHistory;
import matyliano.exchange.repository.CurrencySelectionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CurrencySelectionHistoryService {

    private final CurrencySelectionHistoryRepository repository;

    @Autowired
    public CurrencySelectionHistoryService(CurrencySelectionHistoryRepository repository) {
        this.repository = Objects.requireNonNull(repository, "CurrencySelectionHistoryRepository should be defined");
    }


    public List<CurrencySelectionHistory> getHistoryRate() {
        return repository.findAll();
    }


    public CurrencySelectionHistory save(CurrencySelectionHistory currencySelectionHistory) {
        return repository.save(currencySelectionHistory);
    }

}
