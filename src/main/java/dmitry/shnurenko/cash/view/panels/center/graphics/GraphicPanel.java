package dmitry.shnurenko.cash.view.panels.center.graphics;

import javax.annotation.Nonnull;
import javax.swing.*;

/**
 * The interface describes panel which contains graphics of changing sum of savings depending on user actions.
 * And provides business logic to change to current graphics.
 *
 * @author Dmitry Shnurenko
 */
public interface GraphicPanel {

    /** Returns view representation of graphics. */
    @Nonnull JComponent getView();
}
