package dmitry.shnurenko.cash.server.service;

import dmitry.shnurenko.cash.server.dao.cash.CashDao;
import dmitry.shnurenko.cash.server.entity.Cash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The class contains business logic which allows do some actions with cashes.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class CashServiceImpl implements CashService {

    private final CashDao dao;

    @Autowired
    public CashServiceImpl(CashDao dao) {
        this.dao = dao;
    }

    /** {inheritDoc} */
    @Override
    public void saveToDB(@Nonnull Cash cash) {
        dao.save(cash);
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public List<Cash> getAll() {
        return dao.getAll();
    }
}
