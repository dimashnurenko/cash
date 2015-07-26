package dmitry.shnurenko.cash.view.panels.center;

import dmitry.shnurenko.cash.view.TabIndex;
import dmitry.shnurenko.cash.server.entity.Cash;

import javax.annotation.Nonnull;
import javax.swing.*;

/**
 * Provides methods with business logic which allows control behaviour of central panel
 * depending on user's actions.
 *
 * @author Dmitry Shnurenko
 */
public interface CenterPanel {

    /** Returns view representation of central panel. */
    @Nonnull JComponent getView();

    /**
     * Calls special methods which saves {@link Cash} entity to data base.
     *
     * @param cash     cash entity which will be saved to data base
     * @param tabIndex active tab need to remember what active tab should shown. For example if we add cash
     *                 then must display TabIndex.ADD_CASH_TAB
     */
    void saveCashToDB(@Nonnull Cash cash, @Nonnull TabIndex tabIndex);
}
