package dmitry.shnurenko.cash.view.dialog;

import dmitry.shnurenko.cash.view.button.Button;
import dmitry.shnurenko.cash.view.button.ButtonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.math.BigDecimal;

import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.*;

/**
 * The class contains business logic which allows change displaying of dialog window.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class DialogWindowViewImpl extends JFrame implements DialogWindowView {

    private static final int WIDTH  = 400;
    private static final int HEIGHT = 250;

    private final JLabel errorLabel;

    private ActionDelegate delegate;

    @Autowired
    public DialogWindowViewImpl(ButtonFactory buttonFactory) {
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(null);

        JLabel sumLabel = new JLabel(getText(DIALOG_LABEL_SUM));
        sumLabel.setBounds(30, 0, 100, 50);

        JTextField sumField = new JTextField();
        sumField.setBounds(130, 15, 220, 20);
        sumField.setBorder(new BevelBorder(BevelBorder.LOWERED));

        String errorLabelMessage = "<html><font color='red'>" + getText(INCORRECT_INPUT) + "</font></html>";
        errorLabel = new JLabel(errorLabelMessage);
        errorLabel.setBounds(130, 35, 220, 20);
        errorLabel.setVisible(false);

        JLabel descriptionLabel = new JLabel(getText(DIALOG_LABEL_DESCRIPTION));
        descriptionLabel.setBounds(30, 30, 100, 50);

        JTextArea description = new JTextArea();
        description.setBounds(30, 70, 320, 80);
        description.setBorder(new BevelBorder(BevelBorder.LOWERED));

        Button buttonOk = buttonFactory.createFunctionalButton(getText(BUTTON_OK));
        buttonOk.setBounds(270, 170, 80, 30);
        buttonOk.addActionListener(event -> {
            String fieldValue = sumField.getText();

            double sum = 0;
            try {
                sum = Double.valueOf(fieldValue);

                errorLabel.setVisible(false);
            } catch (NumberFormatException exception) {
                errorLabel.setVisible(true);

                return;
            }

            String descriptionValue = description.getText();

            delegate.onOkButtonClicked(new BigDecimal(sum), descriptionValue);
        });

        JButton buttonCancel = new JButton(getText(BUTTON_CANCEL));
        buttonCancel.setBounds(170, 170, 80, 30);
        buttonCancel.addActionListener(event -> hideDialog());

        dialogPanel.add(sumLabel);
        dialogPanel.add(sumField);
        dialogPanel.add(errorLabel);
        dialogPanel.add(descriptionLabel);
        dialogPanel.add(description);

        dialogPanel.add(buttonOk.getComponent());
        dialogPanel.add(buttonCancel);

        add(dialogPanel);
    }

    /** {inheritDoc} */
    @Override
    public void showDialog() {
        setVisible(true);
    }

    /** {inheritDoc} */
    @Override
    public void hideDialog() {
        setVisible(false);
    }

    /** {inheritDoc} */
    @Override
    public void setImage(@Nonnull Image image) {
        setIconImage(image);
    }

    /** {inheritDoc} */
    @Override
    public void setDelegate(@Nonnull ActionDelegate delegate) {
        this.delegate = delegate;
    }
}
