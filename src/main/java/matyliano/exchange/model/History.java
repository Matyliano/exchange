package matyliano.exchange.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class History {

    String fromCurrency;
    String toCurrency;
    String rate;
    String date;
    String open;
    String high;
    String low;
    String close;

    public History(String fromCurrency, String toCurrency, String rate, String date, String open, String high, String low, String close) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public String getRate() {
        return rate;
    }

    public String getDate() {
        return date;
    }

    public String getOpen() {
        return open;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getClose() {
        return close;
    }
}
