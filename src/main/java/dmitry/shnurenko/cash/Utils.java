package dmitry.shnurenko.cash;

import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.server.entity.OperationType;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static dmitry.shnurenko.cash.server.entity.OperationType.LOAN_CASH;

/**
 * The class which provides utils methods which are used around the application
 *
 * @author Dmitry Shnurenko
 */
public class Utils {

    //special format for string representation of dates
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private Utils() {
        throw new AssertionError("Object of this class can't be created... Sorry...");
    }

    /**
     * The methods downloads images using special path.
     *
     * @param path path to image (e.g. /images/tabsimage/main_icon.png)
     * @return an instance of {@link ImageIcon}
     */
    @Nonnull
    public static ImageIcon downloadIcon(@Nonnull String path) {
        return new ImageIcon(Utils.class.getResource(path));
    }

    /**
     * Calculates general sum which is allowed in cash.
     *
     * @param cashes list of cashes using which we can calculate added sum
     * @return a instance of {@link BigDecimal} which contains general sum
     */
    @Nonnull
    public static BigDecimal calculateAddedSum(@Nonnull List<Cash> cashes) {
        BigDecimal sumAdded = new BigDecimal(0);

        for (Cash cash : cashes) {
            OperationType operationType = cash.getOperationType();
            BigDecimal sum = cash.getSum();

            if (LOAN_CASH.equals(operationType)) {
                sumAdded = sumAdded.subtract(sum);
            } else {
                sumAdded = sumAdded.add(sum);
            }
        }

        return sumAdded;
    }
}
