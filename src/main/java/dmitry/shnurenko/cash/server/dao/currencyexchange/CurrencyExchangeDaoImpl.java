package dmitry.shnurenko.cash.server.dao.currencyexchange;

import dmitry.shnurenko.cash.server.entity.CurrencyExchange;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * The class contains business logic to save currency exchange to data base and get the newest value
 * of currency exchange from data base.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class CurrencyExchangeDaoImpl implements CurrencyExchangeDao {

    private static final String ID_COLUMN = "id";

    private final SessionFactory sessionFactory;

    @Autowired
    public CurrencyExchangeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /** {inheritDoc} */
    @Override
    public void save(@Nonnull CurrencyExchange currencyExchange) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(currencyExchange);

        session.getTransaction().commit();
        session.close();
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public CurrencyExchange getLastSaved() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(CurrencyExchange.class)
                                   .addOrder(Order.desc(ID_COLUMN))
                                   .setMaxResults(1);

        CurrencyExchange currencyExchange = (CurrencyExchange) criteria.uniqueResult();

        session.close();

        return currencyExchange;
    }
}
