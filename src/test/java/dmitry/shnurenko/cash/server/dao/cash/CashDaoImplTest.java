package dmitry.shnurenko.cash.server.dao.cash;

import dmitry.shnurenko.cash.server.entity.Cash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class CashDaoImplTest {

    @Mock
    private SessionFactory sessionFactory;

    //additional mocks
    @Mock
    private Cash        cash;
    @Mock
    private Session     session;
    @Mock
    private Transaction transaction;

    @InjectMocks
    private CashDaoImpl dao;

    @Before
    public void setUp() {
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void cashShouldBeSaved() {
        dao.save(cash);

        verify(sessionFactory).openSession();
        verify(session).beginTransaction();

        verify(session).save(cash);

        verify(session).getTransaction();
        verify(transaction).commit();
        verify(session).close();
    }

    @Test
    public void allCashesShouldBeReturned() {
        Criteria criteria = mock(Criteria.class);
        when(session.createCriteria(Cash.class)).thenReturn(criteria);
        when(criteria.list()).thenReturn(Arrays.asList(cash));

        List<Cash> cashes = dao.getAll();

        verify(sessionFactory).openSession();
        verify(session).beginTransaction();

        verify(session).createCriteria(Cash.class);
        verify(criteria).list();
        verify(session).close();

        assertThat(cashes.get(0), equalTo(cash));
    }
}