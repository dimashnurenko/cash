package dmitry.shnurenko.cash.view.panels.left.proceed;

import dmitry.shnurenko.cash.view.common.ViewComponent;

import javax.annotation.Nonnull;

/**
 * Provides methods to update information on view.
 *
 * @author Dmitry Shnurenko
 */
interface ProceedPanelView extends ViewComponent {

    /**
     * Updates spacial proceed information in special place on view.
     *
     * @param currencyExchangeProvider provider which allows convert cash in different currencies. Need to
     *                                 calculate sum of cash in different currencies.
     */
    void update(@Nonnull CurrencyExchangeProvider currencyExchangeProvider);
}
