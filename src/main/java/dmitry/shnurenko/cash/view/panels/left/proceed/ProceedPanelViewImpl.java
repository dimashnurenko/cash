package dmitry.shnurenko.cash.view.panels.left.proceed;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.*;
import static javax.swing.BorderFactory.createBevelBorder;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * The class contains business logic which allows change view representation of proceed panel.
 *
 * @author Dmitry Shnurenko
 */
@Component
@Scope(value = SCOPE_PROTOTYPE)
final class ProceedPanelViewImpl extends JPanel implements ProceedPanelView {

    private static final int WIDTH  = 180;
    private static final int HEIGHT = 160;

    private static final int LABEL_WIDTH  = 50;
    private static final int LABEL_HEIGHT = 25;

    private static final int MARGIN_TOP  = 20;
    private static final int MARGIN_LEFT = 50;

    private final JLabel uah;
    private final JLabel usd;
    private final JLabel eur;
    private final JLabel rub;

    public ProceedPanelViewImpl() {
        this.uah = new JLabel();
        this.usd = new JLabel();
        this.rub = new JLabel();
        this.eur = new JLabel();

        setBackground(Color.YELLOW);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBorder(createBevelBorder(EtchedBorder.RAISED));

        JLabel panelTitle = new JLabel(getText(CURRENCY_PANEL_TITLE));
        panelTitle.setLocation(10, 10);
        panelTitle.setSize(WIDTH, 20);

        int margin = 35;

        uah.setLocation(MARGIN_LEFT, margin);
        uah.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        JLabel uahLabel = new JLabel(getText(CURRENCY_UAH));
        uahLabel.setLocation(MARGIN_LEFT + LABEL_WIDTH, margin);
        uahLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        usd.setLocation(MARGIN_LEFT, margin += MARGIN_TOP);
        usd.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        JLabel usdLabel = new JLabel(getText(CURRENCY_USD));
        usdLabel.setLocation(MARGIN_LEFT + LABEL_WIDTH, margin);
        usdLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        eur.setLocation(MARGIN_LEFT, margin += MARGIN_TOP);
        eur.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        JLabel eurLabel = new JLabel(getText(CURRENCY_EUR));
        eurLabel.setLocation(MARGIN_LEFT + LABEL_WIDTH, margin);
        eurLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        rub.setLocation(MARGIN_LEFT, margin += MARGIN_TOP);
        rub.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        JLabel rubLabel = new JLabel(getText(CURRENCY_RUB));
        rubLabel.setLocation(MARGIN_LEFT + LABEL_WIDTH, margin);
        rubLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);

        add(panelTitle);

        add(uah);
        add(uahLabel);

        add(usd);
        add(usdLabel);

        add(eur);
        add(eurLabel);

        add(rub);
        add(rubLabel);
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public JComponent getComponent() {
        return this;
    }

    /** {inheritDoc} */
    @Override
    public void update(@Nonnull CurrencyExchangeProvider currencyExchangeProvider) {
        uah.setText(currencyExchangeProvider.getUAH().toString());
        eur.setText(currencyExchangeProvider.getEUR().toString());
        usd.setText(currencyExchangeProvider.getUSD().toString());
        rub.setText(currencyExchangeProvider.getRUB().toString());
    }
}
