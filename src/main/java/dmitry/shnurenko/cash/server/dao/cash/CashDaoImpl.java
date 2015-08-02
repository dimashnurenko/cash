package dmitry.shnurenko.cash.server.dao.cash;

import dmitry.shnurenko.cash.server.dao.AbstractDao;
import dmitry.shnurenko.cash.server.entity.Cash;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The class contains business logic which allows get information about cashes from data base and save
 * needed information to data base.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class CashDaoImpl extends AbstractDao<Cash> implements CashDao {

    @Autowired
    public CashDaoImpl(SessionFactory factory) {
        super(factory);
    }

    /** {inheritDoc} */
    @Override
    public void save(@Nonnull Cash cash) {
        super.save(cash);
    }

    /** {inheritDoc} */
    @Override
    public List<Cash> getAll() {
        startTransaction();

        Criteria criteria = session.createCriteria(Cash.class);

        //noinspection unchecked
        List<Cash> cashes = criteria.list();

        session.close();

        return cashes;
    }
}
