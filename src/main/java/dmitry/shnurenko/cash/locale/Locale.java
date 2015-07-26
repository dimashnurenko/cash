package dmitry.shnurenko.cash.locale;

import javax.annotation.Nonnull;
import java.util.ResourceBundle;

/**
 * The class uses Swing locale technology. All locale's strings are located in special files (locale.properties,
 * locale_en_US.properties) in resources folder.It's util class.
 *
 * @author Dmitry Shnurenko
 */
public final class Locale {

    /** This constructor need to reject creating objects of current type. It's util class. */
    private Locale() {
    }

    /**
     * Returns text which matches special key.
     *
     * @param key special key which corresponds to text
     * @return a string
     */
    @Nonnull
    public static String getText(@Nonnull LocaleKey key) {
        java.util.Locale locale = java.util.Locale.ENGLISH;
        ResourceBundle bundle = ResourceBundle.getBundle("locale", locale);
        return bundle.getString(key.toString());
    }

}
