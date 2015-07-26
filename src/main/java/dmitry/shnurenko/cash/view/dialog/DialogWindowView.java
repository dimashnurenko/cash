package dmitry.shnurenko.cash.view.dialog;

import dmitry.shnurenko.cash.view.common.View;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.math.BigDecimal;

/**
 * Provides methods to control displaying of dialog window.
 *
 * @author Dmitry Shnurenko
 */
interface DialogWindowView extends View<DialogWindowView.ActionDelegate> {

    /** Shows dialog window. */
    void showDialog();

    /** Hides dialog window. */
    void hideDialog();

    /**
     * Sets special title to dialog window.
     *
     * @param title title which will be set
     */
    void setTitle(@Nonnull String title);

    /**
     * Sets image in special place on dialog window.
     *
     * @param image image which will be set
     */
    void setImage(@Nonnull Image image);

    interface ActionDelegate {
        /**
         * Performs some actions when user clicks on 'OK' button. Pass some information from dialog window
         * to special delegate for further processing.
         *
         * @param sum         sum which client input in special place on view
         * @param description description which front input in special place on view
         */
        void onOkButtonClicked(@Nonnull BigDecimal sum, @Nullable String description);
    }
}
