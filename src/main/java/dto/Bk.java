package dto;

public enum Bk {

    ONEXBET("1xStavka"),
    BETCITY("BetCity");

    private String description;

    private Bk(String description) {
        this.description = description;
    }

    public String getDescription() {return description;}
}

