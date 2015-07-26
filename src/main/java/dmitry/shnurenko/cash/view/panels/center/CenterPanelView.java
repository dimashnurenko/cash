package dmitry.shnurenko.cash.view.panels.center;

import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.view.TabIndex;
import dmitry.shnurenko.cash.view.common.View;
import dmitry.shnurenko.cash.view.common.ViewComponent;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Provides methods which allow change displaying of central panel.
 *
 * @author Dmitry Shnurenko
 */
interface CenterPanelView extends View<CenterPanelView.ActionDelegate>, ViewComponent {

    /**
     * Shows table which contains list of cashes.
     *
     * @param cashes cashes which will be displayed
     */
    void showTable(@Nonnull List<Cash> cashes);

    /**
     * Selects current tab using special {@link TabIndex}.
     *
     * @param tabIndex index which matches corresponding type
     */
    void selectTab(@Nonnull TabIndex tabIndex);

    interface ActionDelegate {
        /**
         * Performs some actions when user clicks on tab of the table.
         *
         * @param tabIndex index which matches corresponding type
         */
        void onTabClicked(@Nonnull TabIndex tabIndex);
    }
}
