package dmitry.shnurenko.cash.view.mainwindow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The class contains business logic to control main window.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class MainWindowPresenter implements MainWindow {

    private final MainWindowView view;

    @Autowired
    public MainWindowPresenter(MainWindowView view) {
        this.view = view;
    }

    /** {inheritDoc} */
    @Override
    public void show() {
        view.display();
    }
}
