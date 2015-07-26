package dmitry.shnurenko.cash.view.panels.center.table;

import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.view.common.ViewComponent;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The interface provides methods which allow show table which contains list of entities.
 *
 * @author Dmitry Shnurenko
 */
public interface CashTable extends ViewComponent {

    /**
     * Shows list of entities in the table.
     *
     * @param list list of entities which will be shown in table
     */
    void show(@Nonnull List<Cash> list);
}
