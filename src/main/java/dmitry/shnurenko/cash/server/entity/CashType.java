package dmitry.shnurenko.cash.server.entity;

/**
 * The class which represents kinds of cash payments. There are two cash types. <code>ADD_CASH</code> matches
 * cash type when user adds cash and <code>LOAN_CASH</code> when user loan cash.
 *
 * @author Dmitry Shnurenko
 */
public enum CashType {
    ADD_CASH, LOAN_CASH
}
