package dmitry.shnurenko.cash.view.panels.center;

import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.view.TabIndex;
import dmitry.shnurenko.cash.view.panels.center.table.CashTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.util.List;

import static dmitry.shnurenko.cash.Utils.downloadIcon;
import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.*;

/**
 * The class contains business logic which allows change displaying of central panel.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class CenterPanelViewImpl extends JPanel implements CenterPanelView {

    private static final String PATH_TO_COMMON_TAB_ICON = "/images/tabsimage/main_icon.png";
    private static final String PATH_TO_ADD_TAB_ICON    = "/images/tabsimage/add_cash.png";
    private static final String PATH_TO_LOAN_TAB_ICON   = "/images/tabsimage/loan_cash.png";

    private final CashTable   table;
    private final JTabbedPane tabs;

    private ActionDelegate delegate;

    @Autowired
    public CenterPanelViewImpl(CashTable table) {
        this.table = table;

        setLayout(new BorderLayout());
        setBackground(new Color(13, 66, 105));

        tabs = new JTabbedPane();
        tabs.setBackground(new Color(52, 144, 119));

        JScrollPane scrollPane = new JScrollPane(table.getComponent());

        tabs.addTab(getText(TAB_COMMON),
                    downloadIcon(PATH_TO_COMMON_TAB_ICON),
                    scrollPane,
                    getText(TAB_TOOLTIP_COMMON));

        tabs.addTab(getText(TAB_ADD_CASH),
                    downloadIcon(PATH_TO_ADD_TAB_ICON),
                    null,
                    getText(TAB_TOOLTIP_ADD));

        tabs.addTab(getText(TAB_LOAN_CASH),
                    downloadIcon(PATH_TO_LOAN_TAB_ICON),
                    null,
                    getText(TAB_TOOLTIP_LOAN));

        tabs.addChangeListener(event -> {
            int tabIndex = tabs.getSelectedIndex();

            delegate.onTabClicked(TabIndex.valueOf(tabIndex));
        });

        add(tabs, BorderLayout.CENTER);
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public JPanel getComponent() {
        return this;
    }

    /** {inheritDoc} */
    @Override
    public void setDelegate(@Nonnull ActionDelegate delegate) {
        this.delegate = delegate;
    }

    /** {inheritDoc} */
    @Override
    public void showTable(@Nonnull List<Cash> cashes) {
        table.show(cashes);
    }

    /** {inheritDoc} */
    @Override
    public void selectTab(@Nonnull TabIndex tabIndex) {
        tabs.setSelectedIndex(tabIndex.getIndex());
    }
}
