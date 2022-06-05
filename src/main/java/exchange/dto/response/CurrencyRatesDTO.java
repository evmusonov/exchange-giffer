package exchange.dto.response;

import java.util.Map;

public class CurrencyRatesDTO {
    private Long timestamp;
    private String base;
    private Map<String, Float> rates;

    public CurrencyRatesDTO() {}

    public CurrencyRatesDTO(Long timestamp, String base, Map<String, Float> rates) {
        this.timestamp = timestamp;
        this.base = base;
        this.rates = rates;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBase() {
        return this.base;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }

    public Map<String, Float> getRates() {
        return this.rates;
    }
}
