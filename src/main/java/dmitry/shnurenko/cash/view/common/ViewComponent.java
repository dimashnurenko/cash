package dmitry.shnurenko.cash.view.common;

import javax.annotation.Nonnull;
import javax.swing.*;

/**
 * The interface is common for all view component which displays some view components and don't have business logic
 * to handle other class.
 *
 * @author Dmitry Shnurenko
 */
public interface ViewComponent {

    /** Returns view representation of component. */
    @Nonnull JComponent getComponent();
}
