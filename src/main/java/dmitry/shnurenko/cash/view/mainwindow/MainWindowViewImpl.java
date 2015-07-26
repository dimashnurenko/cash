package dmitry.shnurenko.cash.view.mainwindow;

import dmitry.shnurenko.cash.view.panels.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.MAIN_WINDOW_TITLE;

/**
 * @author Dmitry Shnurenko
 */
@Component
final class MainWindowViewImpl extends JFrame implements MainWindowView {

    private static final String PATH_TO_ICON = "/images/tabsimage/main_icon.png";

    private static final int MINIMUM_WIDTH  = 600;
    private static final int MINIMUM_HEIGHT = 400;

    @Autowired
    public MainWindowViewImpl(Panel mainPanel) {
        setTitle(getText(MAIN_WINDOW_TITLE));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));
        setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon(getClass().getResource(PATH_TO_ICON));
        setIconImage(image.getImage());

        add(mainPanel.getComponent());
    }

    /** {inheritDoc} */
    @Override
    public void display() {
        setVisible(true);
    }
}
