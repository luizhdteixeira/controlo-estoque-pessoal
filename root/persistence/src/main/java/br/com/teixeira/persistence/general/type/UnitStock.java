package br.com.teixeira.persistence.general.type;

public enum UnitStock {

    KILOGRAM("kg"),
    GRAM("g"),

    LITER("L"),
    MILLILITRE("mL"),

    METRE("m"),
    INCH("cm"),

    UNIT("un"),
    DOZEN("dz"),

    PACKAGE("pct"),
    BURDEN("fd"),
    BOX("cx");

    private final String value;

    UnitStock(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
