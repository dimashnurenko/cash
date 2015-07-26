package dmitry.shnurenko.cash.view.dialog;

import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.server.entity.CashType;
import dmitry.shnurenko.cash.server.factories.EntityFactory;
import dmitry.shnurenko.cash.view.TabIndex;
import dmitry.shnurenko.cash.view.panels.center.CenterPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static dmitry.shnurenko.cash.Utils.downloadIcon;
import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.DIALOG_TITLE_ADD;
import static dmitry.shnurenko.cash.locale.LocaleKey.DIALOG_TITLE_LOAN;
import static dmitry.shnurenko.cash.view.TabIndex.ADD_CASH_TAB;
import static dmitry.shnurenko.cash.view.TabIndex.LOAN_CASH_TAB;

/**
 * The class contains business logic which allows to control actions when user uses dialog window.
 *
 * @author Dmitry Shnurenko
 */
@Component
@Lazy
final class DialogWindowPresenter implements DialogWindow, DialogWindowView.ActionDelegate {

    static final String PATH_TO_ADD_DIALOG_ICON  = "/images/tabsimage/add_cash.png";
    static final String PATH_TO_LOAN_DIALOG_ICON = "/images/tabsimage/loan_cash.png";

    private final DialogWindowView view;
    private final CenterPanel      centerPanel;
    private final EntityFactory    entityFactory;

    private final ImageIcon addCashImage;
    private final ImageIcon loanCashImage;

    private CashType currentType;
    private TabIndex activeTab;

    @Autowired
    public DialogWindowPresenter(DialogWindowView view, CenterPanel centerPanel, EntityFactory entityFactory) {
        this.view = view;
        this.view.setDelegate(this);

        this.centerPanel = centerPanel;
        this.entityFactory = entityFactory;

        addCashImage = downloadIcon(PATH_TO_ADD_DIALOG_ICON);
        loanCashImage = downloadIcon(PATH_TO_LOAN_DIALOG_ICON);
    }

    /** {inheritDoc} */
    @Override
    public void showFor(@Nonnull CashType cashType) {
        currentType = cashType;

        switch (cashType) {
            case ADD_CASH:
                view.setTitle(getText(DIALOG_TITLE_ADD));
                view.setImage(addCashImage.getImage());

                activeTab = ADD_CASH_TAB;
                break;
            case LOAN_CASH:
                view.setTitle(getText(DIALOG_TITLE_LOAN));
                view.setImage(loanCashImage.getImage());

                activeTab = LOAN_CASH_TAB;
                break;

            default:
        }

        view.showDialog();
    }

    /** {inheritDoc} */
    @Override
    public void onOkButtonClicked(@Nonnull BigDecimal sum, @Nullable String description) {
        LocalDateTime date = LocalDateTime.now();

        Cash cash = entityFactory.createCash(date, sum, description, currentType);

        Runnable saveCash = () -> centerPanel.saveCashToDB(cash, activeTab);
        new Thread(saveCash).start();

        view.hideDialog();
    }
}
