package br.com.teixeira.persistence.general.type;

public enum PaymentType
{
    CREDIT_CARD("Cartão de Crédito"),
    DEBIT_CARD ("Cartão de Débito"),
    PIX("Pix"),
    MONEY("Dinheiro"),
    FINANCING("Finaciamento");

    private final String description;

    PaymentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentType getEnum(String description) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.getDescription().equals(description)) {
                return paymentType;
            }
        }
        throw new IllegalArgumentException("Invalid description");
    }
}
