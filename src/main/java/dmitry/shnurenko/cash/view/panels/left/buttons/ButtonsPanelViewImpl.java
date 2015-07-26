package dmitry.shnurenko.cash.view.panels.left.buttons;

import dmitry.shnurenko.cash.view.button.Button;
import dmitry.shnurenko.cash.view.button.ButtonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.MAIN_WINDOW_BUTTON_ADD;
import static dmitry.shnurenko.cash.locale.LocaleKey.MAIN_WINDOW_BUTTON_LOAN;
import static javax.swing.BorderFactory.createBevelBorder;

/**
 * The class contains business logic which allows change view representation of buttons panel.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class ButtonsPanelViewImpl extends JPanel implements ButtonsPanelView {

    private static final int PANEL_WIDTH  = 180;
    private static final int PANEL_HEIGHT = 160;

    private final ButtonFactory buttonFactory;

    private ActionDelegate delegate;

    @Autowired
    public ButtonsPanelViewImpl(ButtonFactory buttonFactory) {
        this.buttonFactory = buttonFactory;

        setBackground(Color.RED);
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        setBorder(createBevelBorder(EtchedBorder.RAISED));

        Button addCash = createButton(getText(MAIN_WINDOW_BUTTON_ADD),
                                      event -> delegate.onAddCashButtonClicked());

        Button loanCash = createButton(getText(MAIN_WINDOW_BUTTON_LOAN),
                                       event -> delegate.onLoanCashButtonClicked());

        add(addCash.getComponent());
        add(loanCash.getComponent());
    }

    @Nonnull
    private Button createButton(@Nonnull String title, @Nonnull ActionListener listener) {
        Button button = buttonFactory.createFunctionalButton(title);
        button.addActionListener(listener);

        return button;
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public JPanel getComponent() {
        return this;
    }

    /** {inheritDoc} */
    @Override
    public void setDelegate(@Nonnull ActionDelegate delegate) {
        this.delegate = delegate;
    }
}
