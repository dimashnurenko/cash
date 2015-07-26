package dmitry.shnurenko.cash.server.dao.currencyexchange;

import dmitry.shnurenko.cash.server.entity.CurrencyExchange;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangeDaoImplTest {

    @Mock
    private SessionFactory sessionFactory;

    //additional mocks
    @Mock
    private CurrencyExchange currencyExchange;
    @Mock
    private Session          session;
    @Mock
    private Transaction      transaction;

    @InjectMocks
    private CurrencyExchangeDaoImpl dao;

    @Before
    public void setUp() {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void currencyExchangeShouldBeSaved() {
        dao.save(currencyExchange);

        verify(sessionFactory).openSession();
        verify(session).beginTransaction();

        verify(session).save(currencyExchange);

        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void lastSavedValueShouldBeReturned() {
        Criteria criteria = mock(Criteria.class);

        when(session.createCriteria(CurrencyExchange.class)).thenReturn(criteria);
        when(criteria.addOrder(Matchers.<Order>anyObject())).thenReturn(criteria);
        when(criteria.setMaxResults(1)).thenReturn(criteria);

        when(criteria.uniqueResult()).thenReturn(currencyExchange);

        CurrencyExchange currencyExchangeTest = dao.getLastSaved();

        verify(sessionFactory).openSession();
        verify(session).beginTransaction();

        verify(session).createCriteria(CurrencyExchange.class);
        verify(criteria).addOrder(Matchers.<Order>anyObject());
        verify(criteria).setMaxResults(1);

        verify(criteria).uniqueResult();

        verify(session).close();

        assertThat(currencyExchangeTest, equalTo(currencyExchange));
    }
}