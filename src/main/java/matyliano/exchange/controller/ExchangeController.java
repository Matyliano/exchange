package matyliano.exchange.controller;

import matyliano.exchange.model.Currency;
import matyliano.exchange.model.ExchangeRateForm;
import matyliano.exchange.model.History;
import matyliano.exchange.util.CurrencyReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static matyliano.exchange.api.ApiKey.API_KEY;
import static matyliano.exchange.service.ClientHelper.getRestTemplate;

@RestController
public class ExchangeController {

    @GetMapping("/")
    public ModelAndView index() throws FileNotFoundException {
        List<Currency> currencies = CurrencyReader.loadDataFrom("currencies.json");

        return new ModelAndView("index")
                .addObject("exchangeRateForm", new ExchangeRateForm())
                .addObject("currencies", currencies);
    }

    @PostMapping("/exchange")
    public ModelAndView showExchangeRate(@ModelAttribute ExchangeRateForm exchangeRateForm) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String exchangeRateFrom = exchangeRateForm.getCurrencyFrom();
        String exchangeRateTo = exchangeRateForm.getCurrencyTo();

        //exchange rate
        String exchangeUrl = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=" + exchangeRateFrom + "&to_currency=" + exchangeRateTo + "&apikey=" + API_KEY;
        ResponseEntity<String> exchangeResponse = getRestTemplate().getForEntity(exchangeUrl, String.class);
        String exchaneRate = null;

        String exchangeRateResp = exchangeResponse.toString().replaceAll("\\s", "");
        String regex = "\"5.ExchangeRate\\\":\\\"(.*?)\",";
        Pattern exchangeRatePattern = Pattern.compile(regex);
        Matcher exchangeRateMatcher = exchangeRatePattern.matcher(exchangeRateResp);

        if (exchangeRateMatcher.find()) {
            exchaneRate = exchangeRateMatcher.group(1);
        }

        //historical rate
        String historyUrl = "https://www.alphavantage.co/query?function=FX_DAILY&from_symbol=" + exchangeRateFrom + "&to_symbol=" + exchangeRateTo + "&apikey=" + API_KEY;
        ResponseEntity<String> history = getRestTemplate().getForEntity(historyUrl, String.class);

        String historyResp = history.toString().replaceAll("\\s", "");

        if (historyResp.contains("ErrorMessage")) {
            ModelAndView modelAndView = new ModelAndView("404");
            modelAndView.addObject("exception", historyResp);
            return modelAndView;
        }

        Pattern historyPattern = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))...\"1.open\\\":\\\"(.*?)\",\"2.high\":\"(.*?)\",\"3.low\":\"(.*?)\",\"4.close\":\"(.*?)\"");

        List<History> histories = new ArrayList<>();
        Matcher match = historyPattern.matcher(historyResp);
        while (match.find()) {
            histories.add(new History(exchangeRateFrom, exchangeRateTo, exchaneRate, match.group(1), match.group(4), match.group(5), match.group(6), match.group(7)));
        }

        List<String> dateTime = new ArrayList<>();
        List<Double> open = new ArrayList<>();
        List<Double> close = new ArrayList<>();
        List<Double> high = new ArrayList<>();
        List<Double> low = new ArrayList<>();

        //Set dates list
        histories.forEach(h -> dateTime.add(h.getDate()));
        histories.forEach(h -> open.add(Double.parseDouble(h.getOpen())));
        histories.forEach(h -> high.add(Double.parseDouble(h.getHigh())));
        histories.forEach(h -> low.add(Double.parseDouble(h.getLow())));
        histories.forEach(h -> close.add(Double.parseDouble(h.getClose())));

        Collections.reverse(dateTime);
        Collections.reverse(open);
        Collections.reverse(high);
        Collections.reverse(low);
        Collections.reverse(close);

        ModelAndView modelAndView = new ModelAndView("results");
        modelAndView.addObject("history", histories);
        modelAndView.addObject("dateTime", dateTime);
        modelAndView.addObject("open", open);
        modelAndView.addObject("close", close);
        modelAndView.addObject("high", high);
        modelAndView.addObject("low", low);

        return modelAndView;
    }
}
