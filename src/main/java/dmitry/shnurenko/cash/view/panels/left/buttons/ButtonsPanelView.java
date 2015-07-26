package dmitry.shnurenko.cash.view.panels.left.buttons;

import dmitry.shnurenko.cash.view.common.View;
import dmitry.shnurenko.cash.view.common.ViewComponent;

/**
 * Contains methods which allow change displaying of button's panel.
 *
 * @author Dmitry Shnurenko
 */
interface ButtonsPanelView extends View<ButtonsPanelView.ActionDelegate>, ViewComponent {

    interface ActionDelegate {
        /** Performs some actions when user clicks on 'Add' button */
        void onAddCashButtonClicked();

        /** Performs some actions when user clicks on 'Loan' button */
        void onLoanCashButtonClicked();
    }
}
