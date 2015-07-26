package dmitry.shnurenko.cash.view.panels.left;

import dmitry.shnurenko.cash.view.panels.left.buttons.ButtonsPanel;
import dmitry.shnurenko.cash.view.panels.left.proceed.ProceedPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * The class provides common view representation of left panel.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class LeftPanelImpl extends JPanel implements LeftPanel {

    private static final int WIDTH = 190;

    @Autowired
    public LeftPanelImpl(ButtonsPanel buttonsPanel, ProceedPanel proceedPanel) {
        setBackground(new Color(13, 66, 105));
        setPreferredSize(new Dimension(WIDTH, 0));

        add(buttonsPanel.getView());
        add(proceedPanel.getView());
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public JComponent getComponent() {
        return this;
    }
}
