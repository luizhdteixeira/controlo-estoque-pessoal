package br.com.teixeira.persistence.general.type;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public final class Email implements Serializable {

    @Serial
    private static final long serialVersionUID = 8980633476174016469L;

    private final String address;

    public Email(String address) {
        if (!valid()) throw new IllegalArgumentException("Invalid email address");
        this.address = address;
    }

    private boolean valid() {
        if (address == null || address.isEmpty()) {
            return false;
        }
        return address.matches("[A-Za-z0-9+_.]+@[A-Za-z0-9.-]+$");
    }

    @Override
    public String toString() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Email email)) return false;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(address);
    }
}
