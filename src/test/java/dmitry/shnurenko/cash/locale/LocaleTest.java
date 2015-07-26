package dmitry.shnurenko.cash.locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.BUTTON_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class LocaleTest {

    @Test
    public void neededStringShouldBeReturnedBySpecialKey() {
        String string = getText(BUTTON_OK);

        assertThat(string, equalTo("OK"));
    }

}