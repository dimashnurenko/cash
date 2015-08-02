package dmitry.shnurenko.cash.view.dialog;

import dmitry.shnurenko.cash.server.entity.OperationType;

import javax.annotation.Nonnull;

/**
 * Provides methods to control displaying of dialog window.
 *
 * @author Dmitry Shnurenko
 */
public interface DialogWindow {

    /**
     * Shows displaying of dialog window for current type. Each type has itself displaying (for example title of
     * dialog and other labels which depends on type).
     *
     * @param operationType type for which need show dialog
     */
    void showFor(@Nonnull OperationType operationType);
}
