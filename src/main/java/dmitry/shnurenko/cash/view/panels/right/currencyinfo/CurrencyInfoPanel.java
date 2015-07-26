package dmitry.shnurenko.cash.view.panels.right.currencyinfo;

import dmitry.shnurenko.cash.server.entity.CurrencyExchange;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.*;
import static javax.swing.BorderFactory.createBevelBorder;

/**
 * View representation of right currency info panel.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class CurrencyInfoPanel extends JPanel implements CurrencyInfo {

    private final static int WIDTH  = 180;
    private final static int HEIGHT = 150;

    private final static int LABEL_WIDTH  = 80;
    private final static int LABEL_HEIGHT = 20;

    private final JLabel usd;
    private final JLabel eur;
    private final JLabel rub;

    public CurrencyInfoPanel() {
        this.usd = new JLabel();
        this.eur = new JLabel();
        this.rub = new JLabel();

        setBackground(Color.GREEN);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBorder(createBevelBorder(EtchedBorder.RAISED));

        JLabel panelTitle = new JLabel(getText(CURRENCY_EXCHANGE_TITLE));
        panelTitle.setLocation(10, 10);
        panelTitle.setSize(WIDTH, 20);

        JLabel usdLabel = new JLabel(getText(CURRENCY_USD));
        usdLabel.setLocation(20, 40);
        usdLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        usd.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        usd.setLocation(90, 40);

        JLabel eurLabel = new JLabel(getText(CURRENCY_EUR));
        eurLabel.setLocation(20, 65);
        eurLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        eur.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        eur.setLocation(90, 65);

        JLabel rubLabel = new JLabel(getText(CURRENCY_RUB));
        rubLabel.setLocation(20, 90);
        rubLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        rub.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        rub.setLocation(90, 90);

        add(panelTitle);

        add(usdLabel);
        add(usd);

        add(eurLabel);
        add(eur);

        add(rubLabel);
        add(rub);
    }

    /** {inheritDoc} */
    @Override
    public void update(@Nonnull CurrencyExchange currencyExchange) {
        usd.setText(currencyExchange.getUsdCourse().toString());
        eur.setText(currencyExchange.getEurCourse().toString());
        rub.setText(currencyExchange.getRubCourse().toString());
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public JComponent getComponent() {
        return this;
    }
}
