package dmitry.shnurenko.cash.view.panels.bottom;

import dmitry.shnurenko.cash.view.panels.notification.NotificationPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

import static javax.swing.BorderFactory.createMatteBorder;

/**
 * The class contains business logic which allows change displaying of bottom panel.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class BottomPanelImpl extends JPanel implements BottomPanel {

    private static final int PANEL_HEIGHT = 150;

    @Autowired
    public BottomPanelImpl(NotificationPanel notificationPanel) {
        setBackground(new Color(13, 66, 105));
        setBorder(createMatteBorder(1, 0, 0, 0, new Color(52, 144, 119)));
        setPreferredSize(new Dimension(0, PANEL_HEIGHT));

        add(notificationPanel.getComponent());
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public JComponent getComponent() {
        return this;
    }
}
