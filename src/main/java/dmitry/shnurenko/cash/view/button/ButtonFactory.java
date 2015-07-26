package dmitry.shnurenko.cash.view.button;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

/**
 * Special factory for creating different buttons.
 *
 * @author Dmitry Shnurenko
 */
@Component
public class ButtonFactory {

    /**
     * Creates functional button. The button must have special listener to perform some actions.
     *
     * @param title title of button
     * @return an instance of {@link FunctionalButton}
     */
    @Nonnull
    public Button createFunctionalButton(@Nonnull String title) {
        return new FunctionalButton(title);
    }
}
