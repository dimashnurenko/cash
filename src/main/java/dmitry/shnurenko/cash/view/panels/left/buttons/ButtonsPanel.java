package dmitry.shnurenko.cash.view.panels.left.buttons;

import org.springframework.context.ApplicationContextAware;

import javax.annotation.Nonnull;
import javax.swing.*;

/**
 * Provides business logic which allows do some actions when user click on buttons which are located
 * on the panel. The interface extends {@link ApplicationContextAware} to set application context to
 * panel. This application context need for creating lazy beans when they really need.
 *
 * @author Dmitry Shnurenko
 */
public interface ButtonsPanel extends ApplicationContextAware {

    /** Returns view representation of buttons panel. */
    @Nonnull JComponent getView();
}
