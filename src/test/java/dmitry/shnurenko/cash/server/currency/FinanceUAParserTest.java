package dmitry.shnurenko.cash.server.currency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class FinanceUAParserTest {
    private static final String SITE_URL = "http://finance.i.ua/";

    @Test
    public void courseShouldBeGet() {
        Parser parser = new FinanceUAParser();

        //this block need to start tests when internet connection is lost. Then this test is ignored
        try {
            parser.connect(SITE_URL);
        } catch (IOException e) {
            return;
        }

        assertThat(parser.getUSDCourse(), is(notNullValue()));
        assertThat(parser.getEURCourse(), is(notNullValue()));
        assertThat(parser.getRUBCourse(), is(notNullValue()));
    }

    @Test
    public void coursesShouldBeSet() {
        Parser parser = new FinanceUAParser();

        parser.setUSDCourse(new BigDecimal(1));
        parser.setEURCourse(new BigDecimal(2));
        parser.setRUBCourse(new BigDecimal(3));

        assertThat(parser.getUSDCourse(), equalTo(new BigDecimal(1)));
        assertThat(parser.getEURCourse(), equalTo(new BigDecimal(2)));
        assertThat(parser.getRUBCourse(), equalTo(new BigDecimal(3)));
    }

}