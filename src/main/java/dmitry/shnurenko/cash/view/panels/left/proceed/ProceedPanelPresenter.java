package dmitry.shnurenko.cash.view.panels.left.proceed;

import dmitry.shnurenko.cash.server.currency.CurrencyExchangeFinder;
import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.server.entity.CashType;
import dmitry.shnurenko.cash.server.entity.CurrencyExchange;
import dmitry.shnurenko.cash.view.panels.notification.NotificationPanel;
import dmitry.shnurenko.cash.view.panels.right.currencyinfo.CurrencyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.CAN_NOT_UPDATE_CURRENCY;
import static dmitry.shnurenko.cash.locale.LocaleKey.CURRENCY_EXCHANGE_UPDATED;
import static dmitry.shnurenko.cash.server.entity.CashType.LOAN_CASH;

/**
 * The class contains business logic which allows connect to any site and get information about proceed
 * exchange and update information in special place on view.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class ProceedPanelPresenter implements ProceedPanel {
    static final String CURRENCY_EXCHANGE_SITE_URL = "http://finance.i.ua/";

    private final ProceedPanelView view;

    private final CurrencyExchangeProvider exchangeProvider;

    @Autowired
    public ProceedPanelPresenter(ProceedPanelView view,
                                 CurrencyInfo currencyInfo,
                                 CurrencyExchangeFinder exchangeFinder,
                                 CurrencyExchangeProvider exchangeProvider,
                                 NotificationPanel notification) {
        this.view = view;
        this.exchangeProvider = exchangeProvider;

        try {
            exchangeFinder.connectToSite(CURRENCY_EXCHANGE_SITE_URL);
        } catch (UnknownHostException e) {
            notification.showWarning(e.getMessage());
        }

        try {
            CurrencyExchange currencyExchange = exchangeFinder.getCurrencyExchange();
            currencyInfo.update(currencyExchange);
            exchangeProvider.setCurrencyExchange(currencyExchange);
        } catch (ExecutionException | InterruptedException e) {
            notification.showError(getText(CAN_NOT_UPDATE_CURRENCY) + e.getCause());
        }

        notification.showInfo(getText(CURRENCY_EXCHANGE_UPDATED) + "  " + CURRENCY_EXCHANGE_SITE_URL);
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public JComponent getView() {
        return view.getComponent();
    }

    /** {inheritDoc} */
    @Override
    public void updateInfo(@Nonnull List<Cash> cashes) {
        BigDecimal sumAdded = new BigDecimal(0);

        for (Cash cash : cashes) {
            CashType cashType = cash.getCashType();
            BigDecimal sum = cash.getSum();

            if (LOAN_CASH.equals(cashType)) {
                sumAdded = sumAdded.subtract(sum);
            } else {
                sumAdded = sumAdded.add(sum);
            }
        }

        exchangeProvider.setSumInUAH(sumAdded);

        view.update(exchangeProvider);
    }
}
