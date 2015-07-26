package dmitry.shnurenko.cash.view.panels.left.proceed;

import dmitry.shnurenko.cash.server.entity.CurrencyExchange;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * The class which contains business logic to get UAH sum representation in different currencies. To get values in
 * different currencies we need set value in UAH and via getters methods get value of our sum in different
 * currencies.
 *
 * @author Dmitry Shnurenko
 */
@Component
class CurrencyExchangeProvider {

    private CurrencyExchange currencyExchange;

    private BigDecimal sum;

    public void setCurrencyExchange(@Nonnull CurrencyExchange currencyExchange) {
        this.currencyExchange = currencyExchange;
    }

    public void setSumInUAH(@Nonnull BigDecimal sum) {
        this.sum = sum;
    }

    @Nonnull
    public BigDecimal getUSD() {
        return sum.divide(currencyExchange.getUsdCourse(), 2);
    }

    @Nonnull
    public BigDecimal getEUR() {
        return sum.divide(currencyExchange.getEurCourse(), 2);
    }

    @Nonnull
    public BigDecimal getRUB() {
        return sum.divide(currencyExchange.getRubCourse(), 2);
    }

    @Nonnull
    public BigDecimal getUAH() {
        return sum;
    }
}
