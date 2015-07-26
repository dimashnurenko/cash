package dmitry.shnurenko.cash.view.button;

import javax.annotation.Nonnull;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * The represents functional button. This is a simple button with its own settings of displaying and
 * some business logic.
 *
 * @author Dmitry Shnurenko
 */
final class FunctionalButton extends JButton implements Button {

    private static final int WIDTH  = 120;
    private static final int HEIGHT = 35;

    public FunctionalButton(String title) {
        setText(title);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public JComponent getComponent() {
        return this;
    }
}
