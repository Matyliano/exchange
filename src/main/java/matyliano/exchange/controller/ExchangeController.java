package matyliano.exchange.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static matyliano.exchange.api.ApiKey.API_KEY;
import static matyliano.exchange.service.ClientHelper.getRestTemplate;

@RestController
public class ExchangeController {


    @PostMapping("exchangeRate")
    public ModelAndView showExchangeRate(@RequestParam String exchangeRateFrom,
                                         @RequestParam String exchangeRateTo) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        //exchange rate
        String exchangeUrl = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=" + exchangeRateFrom + "&to_currency="  +exchangeRateTo + "&apikey=" + API_KEY;
        ResponseEntity<String> exchangeResponse = getRestTemplate().getForEntity(exchangeUrl,String.class);
        String exchaneRate = null;

        String exchangeRateResp = exchangeResponse.toString().replaceAll("\\s","");
        String regex = "\"5.ExchangeRate\\\":\\\"(.*?)\",";
        Pattern exchangeRatePattern = Pattern.compile(regex);
        Matcher exchangeRateMatcher = exchangeRatePattern.matcher(exchangeRateResp);

        if(exchangeRateMatcher.find()){
            exchaneRate = exchangeRateMatcher.group(1);
        }


        //historical rate
        String historyUrl;
        ResponseEntity<String> history;


        ModelAndView modelAndView = null;
        return modelAndView;
    }
}
