package br.com.teixeira.persistence.stock;

import br.com.teixeira.persistence.general.DefaultEntityGeneral;
import br.com.teixeira.persistence.general.type.UnitStock;
import br.com.teixeira.persistence.subcategory.Subcategory;
import br.com.teixeira.persistence.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Checks;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "stock")
@AttributeOverrides({
        @AttributeOverride(name = "uuid", column = @Column(name = "stock_id")),
        @AttributeOverride(name = "creationDate", column = @Column(name = "stock_creation_date")),
        @AttributeOverride(name = "updateDate", column = @Column(name = "stock_update_date"))
})
@Checks({
        @Check(name = "stock_quantity_valid", constraints = "stock_quantity >= 0"),
        @Check(name = "stock_minimum_alert_valid", constraints = "stock_minimum_alert IS NULL OR stock_minimum_alert >= 0"),
        @Check(name = "stock_price_unit_valid", constraints = "stock_price_unit IS NULL OR stock_price_unit >= 0")
})
public class Stock extends DefaultEntityGeneral implements Serializable {
    @Serial
    private static final long serialVersionUID = 5048568480153076087L;

    @Column(name = "stock_name", columnDefinition = "VARCHAR(150) NOT NULL")
    private String name;

    @Column(name = "stock_description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "stock_quantity", columnDefinition = "DECIMAL(10, 3) NOT NULL DEFAULT 0")
    private BigDecimal quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_unit", columnDefinition = "VARCHAR(20) NOT NULL")
    private UnitStock unit;

    @Column(name = "stock_minimum_alert", columnDefinition = "DECIMAL(10, 3)")
    private BigDecimal minimum;

    @Column(name = "stock_price_unit", columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal price;

    @Column(name = "stock_last_date_sell", columnDefinition = "DATE")
    private Date lastSell;

    @OneToOne(optional = false)
    @JoinColumn(name = "subcategory_id", columnDefinition = "UUID NOT NULL")
    private Subcategory subcategory;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", columnDefinition = "UUID NOT NULL")
    private User user;

}
