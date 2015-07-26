package dmitry.shnurenko.cash.view.panels.left.proceed;

import dmitry.shnurenko.cash.server.currency.CurrencyExchangeFinder;
import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.server.entity.CurrencyExchange;
import dmitry.shnurenko.cash.view.panels.notification.NotificationPanel;
import dmitry.shnurenko.cash.view.panels.right.currencyinfo.CurrencyInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.CAN_NOT_UPDATE_CURRENCY;
import static dmitry.shnurenko.cash.locale.LocaleKey.CURRENCY_EXCHANGE_UPDATED;
import static dmitry.shnurenko.cash.server.entity.CashType.ADD_CASH;
import static dmitry.shnurenko.cash.server.entity.CashType.LOAN_CASH;
import static dmitry.shnurenko.cash.view.panels.left.proceed.ProceedPanelPresenter.CURRENCY_EXCHANGE_SITE_URL;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class ProceedPanelPresenterTest {

    //constructor mocks
    @Mock
    private ProceedPanelView         view;
    @Mock
    private CurrencyInfo             currencyInfo;
    @Mock
    private CurrencyExchangeProvider exchangeProvider;
    @Mock
    private CurrencyExchangeFinder   exchangeFinder;
    @Mock
    private NotificationPanel        notification;

    //additional mocks
    @Mock
    private CurrencyExchange currencyExchange;

    @Captor
    private ArgumentCaptor<BigDecimal> addedSumCaptor;

    @Before
    public void setUp() throws Exception {
        when(exchangeFinder.getCurrencyExchange()).thenReturn(currencyExchange);
    }

    @Test
    public void warningNotificationShouldBeShownWhenTryConnectToSite() throws UnknownHostException {
        doThrow(UnknownHostException.class).when(exchangeFinder).connectToSite(CURRENCY_EXCHANGE_SITE_URL);

        new ProceedPanelPresenter(view, currencyInfo, exchangeFinder, exchangeProvider, notification);

        verify(exchangeFinder).connectToSite(CURRENCY_EXCHANGE_SITE_URL);
        verify(notification).showWarning(anyString());
    }

    @Test
    public void errorNotificationShouldBeShownWhenTryGetCurrency() throws Exception {
        //noinspection unchecked
        when(exchangeFinder.getCurrencyExchange()).thenThrow(ExecutionException.class);

        new ProceedPanelPresenter(view, currencyInfo, exchangeFinder, exchangeProvider, notification);

        verify(exchangeFinder).connectToSite(CURRENCY_EXCHANGE_SITE_URL);

        verify(notification).showError(getText(CAN_NOT_UPDATE_CURRENCY) + "null");
    }

    @Test
    public void constructorShouldBeSuccessInitialized() throws Exception {
        new ProceedPanelPresenter(view, currencyInfo, exchangeFinder, exchangeProvider, notification);

        verify(exchangeFinder).connectToSite(CURRENCY_EXCHANGE_SITE_URL);
        verify(exchangeFinder).getCurrencyExchange();
        verify(currencyInfo).update(currencyExchange);
        verify(exchangeProvider).setCurrencyExchange(currencyExchange);

        verify(notification).showInfo(getText(CURRENCY_EXCHANGE_UPDATED) + "  " + CURRENCY_EXCHANGE_SITE_URL);
    }

    @Test
    public void viewShouldBeReturned() {
        new ProceedPanelPresenter(view, currencyInfo, exchangeFinder, exchangeProvider, notification).getView();

        verify(view).getComponent();
    }

    @Test
    public void currencyPanelShouldBeUpdated() {
        Cash cash1 = mock(Cash.class);
        Cash cash2 = mock(Cash.class);

        when(cash1.getCashType()).thenReturn(LOAN_CASH);
        when(cash2.getCashType()).thenReturn(ADD_CASH);

        when(cash1.getSum()).thenReturn(new BigDecimal(100));
        when(cash2.getSum()).thenReturn(new BigDecimal(200));

        ProceedPanelPresenter presenter = new ProceedPanelPresenter(view,
                                                                    currencyInfo,
                                                                    exchangeFinder,
                                                                    exchangeProvider,
                                                                    notification);
        presenter.updateInfo(Arrays.asList(cash1, cash2));

        verify(exchangeProvider).setSumInUAH(addedSumCaptor.capture());
        assertThat(addedSumCaptor.getValue(), equalTo(new BigDecimal(100)));

        verify(view).update(exchangeProvider);
    }

}