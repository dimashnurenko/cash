package dmitry.shnurenko.cash.server.dao.currencyexchange;

import dmitry.shnurenko.cash.server.dao.AbstractDao;
import dmitry.shnurenko.cash.server.entity.CurrencyExchange;
import org.hibernate.Criteria;
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
final class CurrencyExchangeDaoImpl extends AbstractDao<CurrencyExchange> implements CurrencyExchangeDao {

    private static final String ID_COLUMN = "id";

    @Autowired
    public CurrencyExchangeDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /** {inheritDoc} */
    @Override
    public void save(@Nonnull CurrencyExchange currencyExchange) {
        super.save(currencyExchange);
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public CurrencyExchange getLastSaved() {
        startTransaction();

        Criteria criteria = session.createCriteria(CurrencyExchange.class)
                                   .addOrder(Order.desc(ID_COLUMN))
                                   .setMaxResults(1);

        CurrencyExchange currencyExchange = (CurrencyExchange) criteria.uniqueResult();

        session.close();

        return currencyExchange;
    }
}
