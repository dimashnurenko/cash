package dmitry.shnurenko.cash.server.dao.cash;

import dmitry.shnurenko.cash.server.entity.Cash;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Provides methods to access to database and saving to or getting from data base information about cash.
 *
 * @author Dmitry Shnurenko
 */
public interface CashDao {

    /**
     * Saves cash to data base.
     *
     * @param cash cash which will be saved
     */
    void save(@Nonnull Cash cash);

    /**
     * Returns all recording from data base.
     *
     * @return {@link List} of recording
     */
    List<Cash> getAll();
}
