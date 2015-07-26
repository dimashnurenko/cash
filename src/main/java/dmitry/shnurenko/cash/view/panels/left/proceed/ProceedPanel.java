package dmitry.shnurenko.cash.view.panels.left.proceed;

import dmitry.shnurenko.cash.server.entity.Cash;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.util.List;

/**
 * Provides methods which contains business logic to update information related to proceed exchange.
 *
 * @author Dmitry Shnurenko
 */
public interface ProceedPanel {

    /** Returns view representation of proceed panel */
    @Nonnull JComponent getView();

    /**
     * Updates proceed information. Method gets sum of added cashes and calculates this sum to different proceed.
     *
     * @param cashes list of cashes which contains added cashes
     */
    void updateInfo(@Nonnull List<Cash> cashes);
}
