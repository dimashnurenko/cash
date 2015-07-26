package dmitry.shnurenko.cash.view.panels.center.graphics;

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
public class GraphicPresenterTest {

    @Mock
    private GraphicView view;

    @InjectMocks
    private GraphicPresenter presenter;

    @Test
    public void viewShouldBeReturned() {
        presenter.getView();

        verify(view).getComponent();
    }

}