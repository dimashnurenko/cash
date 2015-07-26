package dmitry.shnurenko.cash.server.service;

import dmitry.shnurenko.cash.server.dao.cash.CashDao;
import dmitry.shnurenko.cash.server.entity.Cash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class CashServiceImplTest {

    @Mock
    private CashDao dao;
    @Mock
    private Cash    cash;

    @InjectMocks
    private CashServiceImpl service;

    @Test
    public void cashShouldBeSaved() {
        service.saveToDB(cash);

        verify(dao).save(cash);
    }

    @Test
    public void allCashesShouldBeReturned() {
        service.getAll();

        verify(dao).getAll();
    }
}