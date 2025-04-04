package br.com.teixeira.persistence.general.converter;

import br.com.teixeira.persistence.general.type.Password;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PasswordConverter implements AttributeConverter<Password, String> {

    @Override
    public String convertToDatabaseColumn(Password password) {
        if (password == null) {
            return null;
        }
        return password.toString();
    }

    @Override
    public Password convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        try {
            return Password.generate(s.toCharArray());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
