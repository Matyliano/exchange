package matyliano.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class History {

    private String fromCurrency;
    private String toCurrency;
    private String rate;
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
}
