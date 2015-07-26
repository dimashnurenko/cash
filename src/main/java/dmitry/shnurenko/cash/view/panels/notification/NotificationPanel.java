package dmitry.shnurenko.cash.view.panels.notification;

import dmitry.shnurenko.cash.view.common.ViewComponent;

import javax.annotation.Nonnull;

/**
 * The interface provides methods which allow show special messages depending on behaviour.
 * The methods are able display (info, warning, error) messages.
 *
 * @author Dmitry Shnurenko
 */
public interface NotificationPanel extends ViewComponent {

    /**
     * Shows error message when something was wrong. Message has red color.
     *
     * @param error message which will be shown
     */
    void showError(@Nonnull String error);

    /**
     * Shows warning message. Message has yellow color.
     *
     * @param warning message which will be shown
     */
    void showWarning(@Nonnull String warning);

    /**
     * Shows information message. Message has green color.
     *
     * @param info message which will be shown
     */
    void showInfo(@Nonnull String info);

}
