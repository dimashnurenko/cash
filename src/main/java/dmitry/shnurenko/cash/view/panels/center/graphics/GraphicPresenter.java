package dmitry.shnurenko.cash.view.panels.center.graphics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;

/**
 * The class contains business logic which allow action needed operations to build graphics of savings.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class GraphicPresenter implements GraphicPanel {

    private final GraphicView view;

    @Autowired
    public GraphicPresenter(GraphicView view) {
        this.view = view;
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public JComponent getView() {
        return view.getComponent();
    }
}
