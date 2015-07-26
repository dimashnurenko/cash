package dmitry.shnurenko.cash.server.dao.cash;

import dmitry.shnurenko.cash.server.entity.Cash;
import org.hibernate.Criteria;
import org.hibernate.Session;
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
final class CashDaoImpl implements CashDao {

    private final SessionFactory factory;

    @Autowired
    public CashDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    /** {inheritDoc} */
    @Override
    public void save(@Nonnull Cash cash) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(cash);

        session.getTransaction().commit();
        session.close();
    }

    /** {inheritDoc} */
    @Override
    public List<Cash> getAll() {
        Session session = factory.openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Cash.class);

        //noinspection unchecked
        List<Cash> cashes = criteria.list();

        session.close();

        return cashes;
    }
}
