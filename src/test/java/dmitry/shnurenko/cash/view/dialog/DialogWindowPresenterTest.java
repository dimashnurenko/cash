package dmitry.shnurenko.cash.view.dialog;

import dmitry.shnurenko.cash.view.panels.center.CenterPanel;
import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.server.entity.CashType;
import dmitry.shnurenko.cash.server.factories.EntityFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static dmitry.shnurenko.cash.Utils.downloadIcon;
import static dmitry.shnurenko.cash.locale.Locale.getText;
import static dmitry.shnurenko.cash.locale.LocaleKey.DIALOG_TITLE_ADD;
import static dmitry.shnurenko.cash.locale.LocaleKey.DIALOG_TITLE_LOAN;
import static dmitry.shnurenko.cash.view.dialog.DialogWindowPresenter.PATH_TO_ADD_DIALOG_ICON;
import static dmitry.shnurenko.cash.view.dialog.DialogWindowPresenter.PATH_TO_LOAN_DIALOG_ICON;
import static dmitry.shnurenko.cash.server.entity.CashType.ADD_CASH;
import static dmitry.shnurenko.cash.server.entity.CashType.LOAN_CASH;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class DialogWindowPresenterTest {

    private static final String SOME_TEXT = "someText";

    @Mock
    private DialogWindowView view;
    @Mock
    private CenterPanel      centerPanel;
    @Mock
    private EntityFactory    entityFactory;

    @Mock
    private Cash cash;

    @InjectMocks
    private DialogWindowPresenter presenter;

    @Test
    public void constructorShouldBeVerified() {
        verify(view).setDelegate(presenter);
    }

    @Test
    public void dialogShouldBeDisplayedForAddCashType() {
        presenter.showFor(ADD_CASH);

        verify(view).setTitle(getText(DIALOG_TITLE_ADD));
        verify(view).setImage(downloadIcon(PATH_TO_ADD_DIALOG_ICON).getImage());

        verify(view).showDialog();
    }

    @Test
    public void dialogShouldBeDisplayedForLoanCashType() {
        presenter.showFor(LOAN_CASH);

        verify(view).setTitle(getText(DIALOG_TITLE_LOAN));
        verify(view).setImage(downloadIcon(PATH_TO_LOAN_DIALOG_ICON).getImage());

        verify(view).showDialog();
    }

    @Test
    public void onOkButtonShouldBeClicked() {
        BigDecimal sum = new BigDecimal(100);
        when(entityFactory.createCash(Matchers.<LocalDateTime>anyObject(),
                                      Matchers.<BigDecimal>anyObject(),
                                      anyString(),
                                      Matchers.<CashType>anyObject())).thenReturn(cash);
        presenter.showFor(ADD_CASH);

        presenter.onOkButtonClicked(sum, SOME_TEXT);

        verify(entityFactory).createCash(Matchers.<LocalDateTime>anyObject(), eq(sum), eq(SOME_TEXT), eq(ADD_CASH));

        verify(view).hideDialog();
    }
}