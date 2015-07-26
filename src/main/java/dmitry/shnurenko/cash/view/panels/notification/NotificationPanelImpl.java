package dmitry.shnurenko.cash.view.panels.notification;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

import static java.awt.Color.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

/**
 * @author Dmitry Shnurenko
 */
@Component
final class NotificationPanelImpl extends JPanel implements NotificationPanel {

    private final JTextPane notification;

    public NotificationPanelImpl() {
        setBackground(new Color(13, 66, 105));
        setPreferredSize(new Dimension(600, 60));

        notification = new JTextPane();
        notification.setPreferredSize(new Dimension(600, 60));
        notification.setBackground(new Color(52, 144, 119));
        notification.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(notification);
        scrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        add(scrollPane);
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public JComponent getComponent() {
        return this;
    }

    /** {inheritDoc} */
    @Override
    public void showError(@Nonnull String error) {
        printMessage("[ERROR]   " + error, RED);
    }

    private void printMessage(@Nonnull String message, @Nonnull Color color) {
        StyledDocument doc = notification.getStyledDocument();

        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord, color);
        StyleConstants.setBold(keyWord, true);
        try {
            doc.insertString(doc.getLength(), message + '\n', keyWord);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    /** {inheritDoc} */
    @Override
    public void showWarning(@Nonnull String warning) {
        printMessage("[WARNING]   " + warning, ORANGE);
    }

    /** {inheritDoc} */
    @Override
    public void showInfo(@Nonnull String info) {
        printMessage("[INFO]   " + info, GREEN);
    }
}
