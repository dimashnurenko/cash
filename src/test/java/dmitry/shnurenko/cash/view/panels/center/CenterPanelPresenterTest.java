package dmitry.shnurenko.cash.view.panels.center;

import dmitry.shnurenko.cash.view.panels.left.proceed.ProceedPanel;
import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.server.service.CashService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static dmitry.shnurenko.cash.view.TabIndex.*;
import static dmitry.shnurenko.cash.server.entity.OperationType.ADD_CASH;
import static dmitry.shnurenko.cash.server.entity.OperationType.LOAN_CASH;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class CenterPanelPresenterTest {

    //constructor mocks
    @Mock
    private CenterPanelView view;
    @Mock
    private CashService     service;
    @Mock
    private ProceedPanel    proceedPanel;

    //additional mocks
    @Mock
    private Cash cash;

    @Captor
    private ArgumentCaptor<List<Cash>> cashListCaptor;

    private List<Cash> cashes;

    private CenterPanelPresenter presenter;

    @Before
    public void setUp() {
        cashes = new ArrayList<>();
        cashes.add(cash);

        when(service.getAll()).thenReturn(cashes);

        presenter = new CenterPanelPresenter(view, service, proceedPanel);
    }

    @Test
    public void constructorShouldBeVerified() {
        verify(view).setDelegate(presenter);
        verify(service).getAll();
        verify(proceedPanel).updateInfo(cashes);
        verify(view).showTable(cashes);
    }

    @Test
    public void viewShouldBeReturned() {
        presenter.getView();

        verify(view).getComponent();
    }

    @Test
    public void cashShouldBeSaved() {
        when(cash.getOperationType()).thenReturn(ADD_CASH);
        reset(view);

        presenter.saveCashToDB(cash, ADD_CASH_TAB);

        verify(service).saveToDB(cash);
        verify(cash, times(3)).getOperationType();

        verify(view).showTable(cashListCaptor.capture());

        Cash testCash = cashListCaptor.getValue().get(0);

        assertThat(testCash, equalTo(cash));

        verify(view).selectTab(ADD_CASH_TAB);

        verify(proceedPanel, times(2)).updateInfo(Matchers.<List<Cash>>anyObject());
    }

    @Test
    public void onCommonTabClicked() {
        reset(view);
        presenter.onTabClicked(COMMON_TAB);

        verify(view).showTable(cashes);
    }

    @Test
    public void onAddTabClicked() {
        reset(view);
        when(cash.getOperationType()).thenReturn(ADD_CASH);

        presenter.onTabClicked(ADD_CASH_TAB);

        verify(view).showTable(cashListCaptor.capture());

        Cash testCash = cashListCaptor.getValue().get(0);

        assertThat(testCash, equalTo(cash));
    }

    @Test
    public void onLoanTabClicked() {
        reset(view);
        when(cash.getOperationType()).thenReturn(LOAN_CASH);

        presenter.onTabClicked(LOAN_CASH_TAB);

        verify(view).showTable(cashListCaptor.capture());

        Cash testCash = cashListCaptor.getValue().get(0);

        assertThat(testCash, equalTo(cash));
    }
}