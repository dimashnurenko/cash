package dmitry.shnurenko.cash.view.button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class ButtonFactoryTest {

    private static final String SOME_TEXT = "someText";

    @InjectMocks
    private ButtonFactory factory;

    @Test
    public void functionalButtonShouldBeCreated() {
        Button button = factory.createFunctionalButton(SOME_TEXT);

        assertThat(button, is(notNullValue()));
        assertTrue(button instanceof FunctionalButton);
    }

    @Test
    public void eachCallOfCreateFunctionalButtonShouldReturnedDifferentInstances() {
        Button button = factory.createFunctionalButton(SOME_TEXT);
        Button button1 = factory.createFunctionalButton(SOME_TEXT);

        assertThat(button.equals(button1), equalTo(false));
    }
}