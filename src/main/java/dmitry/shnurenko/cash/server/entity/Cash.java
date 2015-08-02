package dmitry.shnurenko.cash.server.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The class represents entity {@link Cash}. This entity stores information about added cash by user.
 * The information contains data of payment, sum and short description. Description is not necessary and
 * may be null. The class has annotations which allow match this class to data base table using special
 * ORM framework. Must contains constructor by default to correct work.
 *
 * @author Dmitry Shnurenko
 */
@Entity
@Table(name = "cash")
public class Cash {

    @Column(name = "id")
    @Id
    @GeneratedValue
    private int           id;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "sum")
    private BigDecimal    sum;
    @Column(name = "description")
    private String        description;
    @Column(name = "type")
    private OperationType operationType;

    public Cash() {
    }

    public Cash(@Nonnull LocalDateTime date,
                @Nonnull BigDecimal sum,
                @Nullable String description,
                @Nonnull OperationType operationType) {
        this.date = date;
        this.sum = sum;
        this.description = description;
        this.operationType = operationType;
    }

    @Nonnull
    public LocalDateTime getDate() {
        return date;
    }

    public BigDecimal getSum() {
        return sum;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nonnull
    public OperationType getOperationType() {
        return operationType;
    }
}
