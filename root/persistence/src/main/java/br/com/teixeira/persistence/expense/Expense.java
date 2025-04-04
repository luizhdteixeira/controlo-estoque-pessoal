package br.com.teixeira.persistence.expense;

import br.com.teixeira.persistence.general.DefaultEntityGeneral;
import br.com.teixeira.persistence.general.type.Occurrence;
import br.com.teixeira.persistence.general.type.PaymentType;
import br.com.teixeira.persistence.subcategory.Subcategory;
import br.com.teixeira.persistence.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Checks;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "expense")
@AttributeOverrides({
        @AttributeOverride(name = "uuid", column = @Column(name = "expense_id")),
        @AttributeOverride(name = "creationDate", column = @Column(name = "expense_creation_date")),
        @AttributeOverride(name = "updateDate", column = @Column(name = "expense_update_date"))
})
@Checks({
        @Check(name = "expense_frequency_occurrence_check", constraints = "CHECK (expense_occurrence IS TRUE AND expense_frequency_occurrence IN ('DIARIA', 'SEMANAL', 'MENSAL', 'ANUAL'))"),
        @Check(name = "expense_amout_check", constraints = "CHECK (expense_amout > 0)")
})
public class Expense extends DefaultEntityGeneral implements Serializable {
    @Serial
    private static final long serialVersionUID = -8919142263385251146L;

    @Column(name = "expense_date", columnDefinition = "DATE NOT NULL")
    private Date expenseDate;

    @Column(name = "expense_description", columnDefinition = "VARCHAR(255) NOT NULL")
    private String description;

    @Column(name = "expense_amout", columnDefinition = "DECIMAL(12, 2) NOT NULL")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_payment_type", columnDefinition = "VARCHAR(50)")
    private PaymentType paymentType;

    @Column(name = "expense_notes", columnDefinition = "TEXT")
    private String notes;

    @OneToOne
    @JoinColumn(name = "subcategory_id", foreignKey = @ForeignKey(name = "fk_expense_subcategory", foreignKeyDefinition = "ON DELETE RESTRICT"), columnDefinition = "UUID NOT NULL")
    private Subcategory subcategory;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_expense_user", foreignKeyDefinition = "ON DELETE CASCADE"), columnDefinition = "UUID NOT NULL")
    private User user;

    @Column(name = "expense_occurrence", columnDefinition = "BOOLEAN NOT NULL DEFAULT FALSE")
    private boolean occurrence;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_frequency_occurrence", columnDefinition = "VARCHAR(20)")
    private Occurrence frequencyOccurrence;

    @Column(name = "expense_date_ocurrence", columnDefinition = "DATE")
    private Date expenseDateOccurrence;







}
