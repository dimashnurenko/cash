package dmitry.shnurenko.cash.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static dmitry.shnurenko.cash.view.TabIndex.COMMON_TAB;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class TabIndexTest {

    @Test
    public void neededIndexShouldBeReturned() {
        assertTrue(COMMON_TAB.getIndex() == 0);
    }

    @Test
    public void tabIndexShouldBeReturned() {
        TabIndex tabIndex = TabIndex.valueOf(0);

        assertThat(tabIndex, equalTo(COMMON_TAB));
    }

    @Test(expected = IllegalArgumentException.class)
    public void exceptionShouldBeReturnedWhenWePassIncorrectIndexValue() {
        TabIndex.valueOf(25);
    }
}