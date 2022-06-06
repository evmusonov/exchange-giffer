package exchange.dto.request;

import feign.Param;

public class ExchangeRequestDTO {
    private String appId;
    private String base;
    private String symbols;
    private boolean prettyPrint;
    private boolean showAlternative;

    public ExchangeRequestDTO(String appId, String base, String symbols, boolean prettyPrint, boolean showAlternative) {
        this.appId = appId;
        this.base = base;
        this.symbols = symbols;
        this.prettyPrint = prettyPrint;
        this.showAlternative = showAlternative;
    }

    @Param("app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
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

    @Param("prettyprint")
    public boolean getPrettyPrint() {
        return prettyPrint;
    }

    public void setPrettyPrint(boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    @Param("show_alternative")
    public boolean getShowAlternative() {
        return showAlternative;
    }

    public void setShowAlternative(boolean showAlternative) {
        this.showAlternative = showAlternative;
    }
}
