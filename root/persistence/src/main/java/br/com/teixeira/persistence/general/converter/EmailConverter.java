package br.com.teixeira.persistence.general.converter;

import br.com.teixeira.persistence.general.type.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email email) {
        if (email == null) {
            return null;
        }
        return email.toString();
    }

    @Override
    public Email convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return new Email(s);
        } catch (IllegalArgumentException e) {
            return null;
        }

    }
}
