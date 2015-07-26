package dmitry.shnurenko.cash.view.common;

import javax.annotation.Nonnull;

/**
 * The interface provides methods which are common for all view components which have special delegate
 * to do some business logic in delegate's class. This interface must be implemented by classes which
 * can't handle some business logic themselves and must delegate handling to other class.
 *
 * @author Dmitry Shnurenko
 */
public interface View<T> {

    /**
     * Sets special delegate which will handle some business logic.
     *
     * @param delegate delegate which need to set
     */
    void setDelegate(@Nonnull T delegate);
}
