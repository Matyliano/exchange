package matyliano.exchange.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ExchangeController {



    @PostMapping("exchangeRate")
    public ModelAndView showExchangeRate(@RequestParam String exchangeRateFrom,
                                         @RequestParam String exchangeRateTo) {

        //exchange rate


        //historical rate
ModelAndView modelAndView = null;
return modelAndView;
    }
}
