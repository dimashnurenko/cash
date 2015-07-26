package dmitry.shnurenko.cash.view.panels.left.buttons;

import dmitry.shnurenko.cash.view.dialog.DialogWindow;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;

import static dmitry.shnurenko.cash.server.entity.CashType.ADD_CASH;
import static dmitry.shnurenko.cash.server.entity.CashType.LOAN_CASH;

/**
 * Contains business logic processing tabs which allows react on users actions.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class ButtonsPanelPresenter implements ButtonsPanel, ButtonsPanelView.ActionDelegate {

    private final ButtonsPanelView view;

    private ApplicationContext applicationContext;

    @Autowired
    public ButtonsPanelPresenter(ButtonsPanelView view) {
        this.view = view;
        this.view.setDelegate(this);
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public JComponent getView() {
        return view.getComponent();
    }

    /** {inheritDoc} */
    @Override
    public void onAddCashButtonClicked() {
        DialogWindow dialog = applicationContext.getBean(DialogWindow.class);

        dialog.showFor(ADD_CASH);
    }

    /** {inheritDoc} */
    @Override
    public void onLoanCashButtonClicked() {
        DialogWindow dialog = applicationContext.getBean(DialogWindow.class);

        dialog.showFor(LOAN_CASH);
    }

    /** {inheritDoc} */
    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
