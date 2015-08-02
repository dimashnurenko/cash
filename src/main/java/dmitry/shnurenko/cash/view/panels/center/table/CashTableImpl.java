package dmitry.shnurenko.cash.view.panels.center.table;

import dmitry.shnurenko.cash.server.entity.Cash;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.Arrays;
import java.util.List;

import static dmitry.shnurenko.cash.Utils.DATE_TIME_FORMATTER;
import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.*;

/**
 * The class contains business logic which allows display entities in table.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class CashTableImpl extends JTable implements CashTable {

    /** {inheritDoc} */
    @Override
    public void show(@Nonnull List<Cash> list) {
        String columnDate = getText(TABLE_COLUMN_DATE);
        String columnSum = getText(TABLE_COLUMN_SUM);
        String columnDescription = getText(TABLE_COLUMN_DESCRIPTION);

        setModel(createTableModel(list, Arrays.asList(columnDate, columnSum, columnDescription)));
    }

    @Nonnull
    private TableModel createTableModel(@Nonnull final List<Cash> list, @Nonnull final List<String> columnNames) {
        return new AbstractTableModel() {
            @Override
            public String getColumnName(int column) {
                return columnNames.get(column);
            }

            @Override
            public int getRowCount() {
                return list.size();
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return DATE_TIME_FORMATTER.format(list.get(rowIndex).getDate());
                    case 1:
                        return list.get(rowIndex).getSum().doubleValue();
                    case 2:
                        return list.get(rowIndex).getDescription();
                    default:
                        return "";
                }
            }
        };
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public JComponent getComponent() {
        return this;
    }
}
