package dmitry.shnurenko.cash.view.panels.center;

import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.server.entity.OperationType;
import dmitry.shnurenko.cash.server.service.CashService;
import dmitry.shnurenko.cash.view.TabIndex;
import dmitry.shnurenko.cash.view.panels.center.CenterPanelView.ActionDelegate;
import dmitry.shnurenko.cash.view.panels.left.proceed.ProceedPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dmitry.shnurenko.cash.server.entity.OperationType.ADD_CASH;
import static dmitry.shnurenko.cash.server.entity.OperationType.LOAN_CASH;

/**
 * Provides business logic to save different cashes to data base and calls special methods to show
 * necessary cashes.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class CenterPanelPresenter implements CenterPanel, ActionDelegate {

    private final CenterPanelView view;
    private final CashService     service;
    private final ProceedPanel    proceedPanel;
    private final List<Cash>      cashes;

    @Autowired
    public CenterPanelPresenter(CenterPanelView view, CashService service, ProceedPanel proceedPanel) {
        this.view = view;
        this.view.setDelegate(this);

        this.service = service;
        this.proceedPanel = proceedPanel;

        this.cashes = new ArrayList<>();

        cashes.addAll(service.getAll());

        proceedPanel.updateInfo(cashes);

        view.showTable(cashes);
    }

    /** {inheritDoc} */
    @Nonnull @Override
    public JComponent getView() {
        return view.getComponent();
    }

    /** {inheritDoc} */
    @Override
    public void saveCashToDB(@Nonnull Cash cash, @Nonnull TabIndex tabIndex) {
        cashes.add(cash);

        service.saveToDB(cash);

        OperationType type = cash.getOperationType();

        view.showTable(getCashesByType(type));
        view.selectTab(tabIndex);

        proceedPanel.updateInfo(cashes);
    }

    @Nonnull
    private List<Cash> getCashesByType(@Nonnull OperationType type) {
        return cashes.stream()
                     .filter(cash -> type.equals(cash.getOperationType()))
                     .collect(Collectors.toList());
    }

    /** {inheritDoc} */
    @Override
    public void onTabClicked(@Nonnull TabIndex tabIndex) {
        switch (tabIndex) {
            case COMMON_TAB:
                view.showTable(cashes);
                break;

            case ADD_CASH_TAB:
                view.showTable(getCashesByType(ADD_CASH));
                break;

            case LOAN_CASH_TAB:
                view.showTable(getCashesByType(LOAN_CASH));
                break;

            default:
        }
    }
}
