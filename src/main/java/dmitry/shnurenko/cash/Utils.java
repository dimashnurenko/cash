package dmitry.shnurenko.cash;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.time.format.DateTimeFormatter;

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
}
