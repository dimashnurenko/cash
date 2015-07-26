package dmitry.shnurenko.cash.view.button;

import dmitry.shnurenko.cash.view.common.ViewComponent;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.awt.event.ActionListener;

/**
 * Provides methods to control button's behaviour.
 *
 * @author Dmitry Shnurenko
 */
public interface Button extends ViewComponent {

    /**
     * Adds special listener to button which perform when user click on button.
     *
     * @param listener listener which need add to button
     */
    void addActionListener(@Nonnull ActionListener listener);

    /**
     * Moves and resizes this component. The new location of the top-left
     * corner is specified by <code>x</code> and <code>y</code>, and the
     * new size is specified by <code>width</code> and <code>height</code>.
     * <p/>
     * This method changes layout-related information, and therefore,
     * invalidates the component hierarchy.
     *
     * @param x      the new <i>x</i>-coordinate of this component
     * @param y      the new <i>y</i>-coordinate of this component
     * @param width  the new <code>width</code> of this component
     * @param height the new <code>height</code> of this component
     */
    void setBounds(@Nonnegative int x, @Nonnegative int y, @Nonnegative int width, @Nonnegative int height);
}
