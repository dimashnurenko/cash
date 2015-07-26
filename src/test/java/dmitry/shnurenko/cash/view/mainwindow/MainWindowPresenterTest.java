package dmitry.shnurenko.cash.view.mainwindow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class MainWindowPresenterTest {

    @Mock
    private MainWindowView view;

    @InjectMocks
    private MainWindowPresenter presenter;

    @Test
    public void presenterShouldBeShown() {
        presenter.show();

        verify(view).display();
    }
}