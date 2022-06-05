package exchange.dto.request;

public class ExchangeRequestDTO {
    private String app_id;
    private String base;
    private String symbols;
    private boolean prettyprint;
    private boolean show_alternative;

    public ExchangeRequestDTO(String appId, String base, String symbols, boolean prettyPrint, boolean showAlternative) {
        this.app_id = appId;
        this.base = base;
        this.symbols = symbols;
        this.prettyprint = prettyPrint;
        this.show_alternative = showAlternative;
    }


    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String appId) {
        this.app_id = appId;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public boolean getPrettyprint() {
        return prettyprint;
    }

    public void setPrettyprint(boolean prettyPrint) {
        this.prettyprint = prettyPrint;
    }

    public boolean getShow_alternative() {
        return show_alternative;
    }

    public void setShow_alternative(boolean showAlternative) {
        this.show_alternative = showAlternative;
    }
}
