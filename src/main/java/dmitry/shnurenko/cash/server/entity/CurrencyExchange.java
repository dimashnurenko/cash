package dmitry.shnurenko.cash.server.entity;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static dmitry.shnurenko.cash.Utils.DATE_TIME_FORMATTER;

/**
 * The class represents entity {@link CurrencyExchange}. This entity stores information about currency exchange.
 * The information contains date of currency update and data about different currency exchange courses.
 * The class has annotations which allow match this class to data base table using special
 * ORM framework. For now supports only three main currency exchange USD, EURO, RUB. You can create instances of
 * current entity using special builder. {@link java.lang.IllegalStateException} will be thrown if at least
 * one of the parameters will not be initialized.
 *
 * @author Dmitry Shnurenko
 */
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange {

    @Id
    @GeneratedValue
    private int           id;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "usd")
    private BigDecimal    usdCourse;
    @Column(name = "eur")
    private BigDecimal    eurCourse;
    @Column(name = "rub")
    private BigDecimal    rubCourse;

    private CurrencyExchange() {
    }

    @Nonnull
    public String getDate() {
        return DATE_TIME_FORMATTER.format(date);
    }

    @Nonnull
    public BigDecimal getUsdCourse() {
        return usdCourse;
    }

    @Nonnull
    public BigDecimal getEurCourse() {
        return eurCourse;
    }

    @Nonnull
    public BigDecimal getRubCourse() {
        return rubCourse;
    }

    public static Builder newBuilder() {
        return new CurrencyExchange().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setDate(@Nonnull LocalDateTime date) {
            CurrencyExchange.this.date = date;

            return this;
        }

        public Builder setUSDCourse(@Nonnull BigDecimal usdCourse) {
            CurrencyExchange.this.usdCourse = usdCourse;

            return this;
        }

        public Builder setEURCourse(@Nonnull BigDecimal eurCourse) {
            CurrencyExchange.this.eurCourse = eurCourse;

            return this;
        }

        public Builder setRUBCourse(@Nonnull BigDecimal rubCourse) {
            CurrencyExchange.this.rubCourse = rubCourse;

            return this;
        }

        public CurrencyExchange build() {
            if (eurCourse == null) {
                throw new IllegalStateException("Can't create object Currency Exchange euro course is null...");
            }

            if (usdCourse == null) {
                throw new IllegalStateException("Can't create object Currency Exchange usd course is null...");
            }

            if (rubCourse == null) {
                throw new IllegalStateException("Can't create object Currency Exchange rub course is null...");
            }

            return CurrencyExchange.this;
        }
    }
}
