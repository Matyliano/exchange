package matyliano.exchange.controller;

import lombok.extern.slf4j.Slf4j;
import matyliano.exchange.service.CurrencySelectionHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
@Slf4j
public class CurrencySelectionHistoryController {

    private final CurrencySelectionHistoryService currencySelectionHistoryService;

    public CurrencySelectionHistoryController(CurrencySelectionHistoryService currencySelectionHistoryService) {
        this.currencySelectionHistoryService = Objects.requireNonNull(currencySelectionHistoryService, "CurrencySelectionHistoryService should be defined ");
    }

    @GetMapping("/currencies")
    public String listAllCurrencySelections(Model model) {
        model.addAttribute("lastHistoricalRates", currencySelectionHistoryService.getHistoryRate());
        log.info("Request to create list of currencies: {}",currencySelectionHistoryService.getHistoryRate());
        return "currencies";
    }
}
