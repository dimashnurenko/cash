package dmitry.shnurenko.cash.view.panels;

import dmitry.shnurenko.cash.view.panels.bottom.BottomPanel;
import dmitry.shnurenko.cash.view.panels.center.CenterPanel;
import dmitry.shnurenko.cash.view.panels.left.LeftPanel;
import dmitry.shnurenko.cash.view.panels.right.RightPanel;
import dmitry.shnurenko.cash.view.panels.toolbar.ToolbarPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * The common view representation of all application window.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class MainPanel extends JPanel implements Panel {

    @Autowired
    public MainPanel(LeftPanel leftPanel,
                     CenterPanel centerPanel,
                     RightPanel rightPanel,
                     BottomPanel bottomPanel,
                     ToolbarPanel toolbarPanel) {
        setLayout(new BorderLayout());

        add(leftPanel.getComponent(), BorderLayout.WEST);
        add(toolbarPanel.getComponent(), BorderLayout.NORTH);
        add(bottomPanel.getComponent(), BorderLayout.SOUTH);
        add(rightPanel.getComponent(), BorderLayout.EAST);
        add(centerPanel.getView(), BorderLayout.CENTER);
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public JPanel getComponent() {
        return this;
    }
}
