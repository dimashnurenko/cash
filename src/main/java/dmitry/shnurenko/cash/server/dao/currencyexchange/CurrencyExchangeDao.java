package dmitry.shnurenko.cash.server.dao.currencyexchange;

import dmitry.shnurenko.cash.server.entity.CurrencyExchange;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * The interface which provides methods to save currency exchange to data base and get last saved value
 * of currency exchange. We need get last saved value, because it's the newest value of currency exchange.
 *
 * @author Dmitry Shnurenko
 */
public interface CurrencyExchangeDao {

    /**
     * Saves value of currency exchange to data base.
     *
     * @param currencyExchange value which will be saved
     */
    void save(@Nonnull CurrencyExchange currencyExchange);

    /**
     * Gets last saved value of currency exchange from data base. Last saved value is the newest value of
     * currency exchange. Method can remove <code>null</code> if saved value is not found.
     *
     * @return an instance of {@link CurrencyExchange} or <code>null</code> if saved value is not found
     */
    @Nullable CurrencyExchange getLastSaved();
}
