package dmitry.shnurenko.cash.view.panels.right;

import dmitry.shnurenko.cash.view.panels.right.currencyinfo.CurrencyInfo;
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
final class RightPanelImpl extends JPanel implements RightPanel {

    private static final int WIDTH = 190;

    @Autowired
    public RightPanelImpl(CurrencyInfo currencyInfo) {
        setBackground(new Color(13, 66, 105));
        setPreferredSize(new Dimension(WIDTH, 0));

        add(currencyInfo.getComponent());
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public JComponent getComponent() {
        return this;
    }
}
