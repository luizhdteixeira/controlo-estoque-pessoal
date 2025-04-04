package br.com.teixeira.persistence.subcategory;

import br.com.teixeira.persistence.general.DefaultEntityGeneral;
import br.com.teixeira.persistence.general.type.Category;
import br.com.teixeira.persistence.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Checks;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "subcategory")
@AttributeOverrides({
        @AttributeOverride(name = "uuid", column = @Column(name = "subcategory_id")),
        @AttributeOverride(name = "creationDate", column = @Column(name = "subcategory_creation_date")),
        @AttributeOverride(name = "updateDate", column = @Column(name = "subcategory_update_date"))
})
@Checks({
        @Check(name = "subcategory_type_valid", constraints = "CHECK (subcategory_type IN ('EXPENSE', 'REVENUE', 'STOCK', 'BUDGET'))")
})
public class Subcategory extends DefaultEntityGeneral implements Serializable {
    @Serial
    private static final long serialVersionUID = 4386472525557180467L;

    @Column(name = "subcategory_name", unique = true, columnDefinition = "VARCHAR(100) NOT NULL")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "subcategory_type", unique = true, columnDefinition = "VARCHAR(20) NOT NULL")
    private Category category;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", unique = true, columnDefinition = "UUID NOT NULL", foreignKey = @ForeignKey(name = "fk_subcategory_user"))
    private User user;

}
