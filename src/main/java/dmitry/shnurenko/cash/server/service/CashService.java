package dmitry.shnurenko.cash.server.service;

import dmitry.shnurenko.cash.server.entity.Cash;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Special cash service which represents additional layer between client and data base.
 *
 * @author Dmitry Shnurenko
 */
public interface CashService {

    /**
     * Saves cash to data base using special dao object.
     *
     * @param cash cash which will be saved
     */
    void saveToDB(@Nonnull Cash cash);

    /** Gets all cashes from data base. */
    @Nonnull List<Cash> getAll();
}
