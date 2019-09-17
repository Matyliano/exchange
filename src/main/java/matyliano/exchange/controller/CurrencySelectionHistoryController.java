package matyliano.exchange.controller;

import matyliano.exchange.service.CurrencySelectionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
public class CurrencySelectionHistoryController {

    private final CurrencySelectionHistoryService currencySelectionHistoryService;


    @Autowired
    public CurrencySelectionHistoryController(CurrencySelectionHistoryService currencySelectionHistoryService) {
        this.currencySelectionHistoryService = Objects.requireNonNull(currencySelectionHistoryService, "CurrencySelectionHistoryService should be defined ");
    }

    @GetMapping("/currencies")
    public String listAllCurrencySelections(Model model) {
        model.addAttribute("lastHistoricalRates", currencySelectionHistoryService.getHistoryRate());
        return "currencies";
    }

}
