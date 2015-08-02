package dmitry.shnurenko.cash.view.panels.left.buttons;

import dmitry.shnurenko.cash.view.dialog.DialogWindow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import static dmitry.shnurenko.cash.server.entity.OperationType.ADD_CASH;
import static dmitry.shnurenko.cash.server.entity.OperationType.LOAN_CASH;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class ButtonsPanelPresenterTest {

    @Mock
    private ButtonsPanelView   view;
    @Mock
    private DialogWindow       dialog;
    @Mock
    private ApplicationContext applicationContext;

    @InjectMocks
    private ButtonsPanelPresenter presenter;

    @Before
    public void setUp() {
        when(applicationContext.getBean(DialogWindow.class)).thenReturn(dialog);

        presenter.setApplicationContext(applicationContext);
    }

    @Test
    public void constructorShouldBeVerified() {
        verify(view).setDelegate(presenter);
    }

    @Test
    public void viewShouldBeReturned() {
        presenter.getView();

        verify(view).getComponent();
    }

    @Test
    public void onAddButtonShouldBeClicked() {
        presenter.onAddCashButtonClicked();

        verify(applicationContext).getBean(DialogWindow.class);
        verify(dialog).showFor(ADD_CASH);
    }

    @Test
    public void onLoanButtonShouldBeClicked() {
        presenter.onLoanCashButtonClicked();

        verify(applicationContext).getBean(DialogWindow.class);
        verify(dialog).showFor(LOAN_CASH);
    }
}