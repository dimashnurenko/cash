package dmitry.shnurenko.cash.view.panels.right.currencyinfo;

import dmitry.shnurenko.cash.view.common.ViewComponent;
import dmitry.shnurenko.cash.server.entity.CurrencyExchange;

import javax.annotation.Nonnull;

/**
 * The panel which contains information about proceed exchange.
 *
 * @author Dmitry Shnurenko
 */
public interface CurrencyInfo extends ViewComponent {

    /**
     * Updates proceed exchange in special place on view.
     *
     * @param currencyExchange contains information about proceed exchange
     */
    void update(@Nonnull CurrencyExchange currencyExchange);
}
