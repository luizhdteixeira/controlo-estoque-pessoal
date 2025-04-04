package br.com.teixeira.persistence.user;

import br.com.teixeira.persistence.general.DefaultEntityGeneral;
import br.com.teixeira.persistence.general.converter.EmailConverter;
import br.com.teixeira.persistence.general.converter.PasswordConverter;
import br.com.teixeira.persistence.general.type.Email;
import br.com.teixeira.persistence.general.type.Password;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "user")
@AttributeOverrides({
        @AttributeOverride(name = "uuid", column = @Column(name = "user_id")),
        @AttributeOverride(name = "creationDate", column = @Column(name = "user_creation_date")),
        @AttributeOverride(name = "updateDate", column = @Column(name = "user_update_date"))
})
public class User extends DefaultEntityGeneral implements Serializable {

    @Serial
    private static final long serialVersionUID = 272936696747716022L;

    @Column(columnDefinition = "VARCHAR(100) NOT NULL")
    private String username;

    @Convert(converter = EmailConverter.class)
    @Column(columnDefinition = "VARCHAR(100) UNIQUE NOT NULL")
    private Email email;

    @Convert(converter = PasswordConverter.class)
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private Password password;

}
