package dmitry.shnurenko.cash.server.currency;

import dmitry.shnurenko.cash.server.dao.currencyexchange.CurrencyExchangeDao;
import dmitry.shnurenko.cash.server.entity.CurrencyExchange;
import dmitry.shnurenko.cash.server.factories.EntityFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

import static dmitry.shnurenko.cash.server.currency.CurrencyExchangeFinderImpl.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangeFinderImplTest {

    private static final String SOME_TEXT = "someText";

    //constructor mocks
    @Mock
    private CurrencyExchangeDao exchangeDao;
    @Mock
    private Parser              parser;
    @Mock
    private EntityFactory       entityFactory;

    //additional mocks
    @Mock
    private CurrencyExchange currencyExchange;

    @Captor
    private ArgumentCaptor<BigDecimal>       sumCaptor;
    @Captor
    private ArgumentCaptor<CurrencyExchange> exchangeCaptor;

    @InjectMocks
    private CurrencyExchangeFinderImpl exchangeFinder;

    @Test
    public void connectionShouldBeSet() throws Exception {
        exchangeFinder.connectToSite(SOME_TEXT);

        verify(parser).connect(SOME_TEXT);
    }

    @Test(expected = UnknownHostException.class)
    public void exceptionShouldBeThrownWhenConnectionLostAndExchangeIsExist() throws Exception {
        doThrow(IOException.class).when(parser).connect(SOME_TEXT);
        when(exchangeDao.getLastSaved()).thenReturn(currencyExchange);
        when(currencyExchange.getDate()).thenReturn(LocalDateTime.now());

        when(parser.setUSDCourse(Matchers.<BigDecimal>anyObject())).thenReturn(parser);
        when(parser.setEURCourse(Matchers.<BigDecimal>anyObject())).thenReturn(parser);
        when(parser.setRUBCourse(Matchers.<BigDecimal>anyObject())).thenReturn(parser);

        BigDecimal euro = new BigDecimal(1);
        BigDecimal usd = new BigDecimal(2);
        BigDecimal rub = new BigDecimal(3);

        when(currencyExchange.getEurCourse()).thenReturn(euro);
        when(currencyExchange.getUsdCourse()).thenReturn(usd);
        when(currencyExchange.getRubCourse()).thenReturn(rub);

        exchangeFinder.connectToSite(SOME_TEXT);

        verify(exchangeDao).getLastSaved();

        verify(currencyExchange).getUsdCourse();
        verify(parser).setUSDCourse(sumCaptor.capture());

        assertThat(sumCaptor.getValue(), equalTo(usd));

        verify(currencyExchange).getEurCourse();
        verify(parser).setEURCourse(sumCaptor.capture());

        assertThat(sumCaptor.getValue(), equalTo(euro));

        verify(currencyExchange).getRubCourse();
        verify(parser).setRUBCourse(sumCaptor.capture());

        assertThat(sumCaptor.getValue(), equalTo(rub));
    }

    @Test(expected = UnknownHostException.class)
    public void exceptionShouldBeThrownWhenConnectionLostAndExchangeIsNotExist() throws Exception {
        doThrow(IOException.class).when(parser).connect(SOME_TEXT);
        when(exchangeDao.getLastSaved()).thenReturn(null);

        when(parser.setUSDCourse(Matchers.<BigDecimal>anyObject())).thenReturn(parser);
        when(parser.setEURCourse(Matchers.<BigDecimal>anyObject())).thenReturn(parser);
        when(parser.setRUBCourse(Matchers.<BigDecimal>anyObject())).thenReturn(parser);

        exchangeFinder.connectToSite(SOME_TEXT);

        verify(exchangeDao).getLastSaved();

        verify(parser).setUSDCourse(sumCaptor.capture());

        verify(currencyExchange, never()).getUsdCourse();
        assertThat(sumCaptor.getValue(), equalTo(new BigDecimal(DEFAULT_USD_COURSE)));

        verify(parser).setEURCourse(sumCaptor.capture());

        verify(currencyExchange, never()).getEurCourse();
        assertThat(sumCaptor.getValue(), equalTo(new BigDecimal(DEFAULT_EUR_COURSE)));

        verify(parser).setRUBCourse(sumCaptor.capture());

        verify(currencyExchange, never()).getRubCourse();
        assertThat(sumCaptor.getValue(), equalTo(new BigDecimal(DEFAULT_RUB_COURSE)));
    }

    @Test
    public void currencyExchangeShouldBeGotAndSavedToDB() throws Exception {
        BigDecimal euro = new BigDecimal(1);
        BigDecimal usd = new BigDecimal(2);
        BigDecimal rub = new BigDecimal(3);

        when(parser.getEURCourse()).thenReturn(euro);
        when(parser.getUSDCourse()).thenReturn(usd);
        when(parser.getRUBCourse()).thenReturn(rub);

        exchangeFinder.connectToSite(SOME_TEXT);

        exchangeFinder.getCurrencyExchange();

        verify(parser).getRUBCourse();
        verify(parser).getEURCourse();
        verify(parser).getUSDCourse();

        verify(exchangeDao).save(exchangeCaptor.capture());

        CurrencyExchange exchange = exchangeCaptor.getValue();

        assertThat(exchange.getEurCourse(), equalTo(euro));
        assertThat(exchange.getUsdCourse(), equalTo(usd));
        assertThat(exchange.getRubCourse(), equalTo(rub));
    }

    @Test
    public void currencyExchangeShouldBeGotButNotSavedToDB() throws Exception {
        when(parser.getEURCourse()).thenReturn(new BigDecimal(1));
        when(parser.getUSDCourse()).thenReturn(new BigDecimal(2));
        when(parser.getRUBCourse()).thenReturn(new BigDecimal(3));

        exchangeFinder.getCurrencyExchange();

        verify(exchangeDao, never()).save(currencyExchange);
    }
}