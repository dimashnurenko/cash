package dmitry.shnurenko.cash.locale;

import javax.annotation.Nonnull;

/**
 * The enum which contains all locale keys which correspond to special string values in locale files.
 *
 * @author Dmitry Shnurenko
 */
public enum LocaleKey {
    MAIN_WINDOW_TITLE("main.window.title"),
    MAIN_WINDOW_BUTTON_ADD("main.window.button.add"),
    MAIN_WINDOW_BUTTON_LOAN("main.window.button.loan"),

    DIALOG_TITLE_ADD("dialog.add.title"),
    DIALOG_TITLE_LOAN("dialog.loan.title"),
    DIALOG_LABEL_SUM("dialog.label.sum"),
    DIALOG_LABEL_DESCRIPTION("dialog.label.description"),

    BUTTON_OK("button.ok"),
    BUTTON_CANCEL("button.cancel"),

    TABLE_COLUMN_DATE("table.column.date"),
    TABLE_COLUMN_SUM("table.column.sum"),
    TABLE_COLUMN_DESCRIPTION("table.column.description"),

    TAB_COMMON("tab.common"),
    TAB_TOOLTIP_COMMON("tab.tooltip.common"),
    TAB_ADD_CASH("tab.add"),
    TAB_TOOLTIP_ADD("tab.tooltip.add"),
    TAB_LOAN_CASH("tab.loan"),
    TAB_TOOLTIP_LOAN("tab.tooltip.loan"),

    INCORRECT_INPUT("error.incorrect.input"),
    CAN_NOT_UPDATE_CURRENCY("error.can.not.get.currency.exchange"),

    CURRENCY_PANEL_TITLE("currency.panel.title"),
    CURRENCY_EXCHANGE_TITLE("currency.info.panel.title"),
    CURRENCY_UAH("currency.uah"),
    CURRENCY_USD("currency.usd"),
    CURRENCY_EUR("currency.eur"),
    CURRENCY_RUB("currency.rub"),

    CURRENCY_EXCHANGE_UPDATED("currency.exchange.info.updated");

    private final String value;

    LocaleKey(@Nonnull String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
