package dmitry.shnurenko.cash.view.panels.toolbar;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * @author Dmitry Shnurenko
 */
@Component
public class ToolbarPanel extends JPanel implements dmitry.shnurenko.cash.view.panels.Panel {

    private static final int TOOLBAR_HEIGHT = 50;

    public ToolbarPanel() {
        setPreferredSize(new Dimension(0, TOOLBAR_HEIGHT));
        setBackground(new Color(13, 66, 105));
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public JPanel getComponent() {
        return this;
    }
}
