package dmitry.shnurenko.cash.view;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * The class which match tab id to special enum name.
 *
 * @author Dmitry Shnurenko
 */
public enum TabIndex {
    COMMON_TAB(0), ADD_CASH_TAB(1), LOAN_CASH_TAB(2);

    private final int index;

    TabIndex(int index) {
        this.index = index;
    }

    /** Returns index of current tab */
    @Nonnegative
    public int getIndex() {
        return index;
    }

    /**
     * Returns {@link TabIndex} using special index which is matched to current tab. Method can throws
     * {@link IllegalArgumentException} if you will pass index for which will not be founded value of {@link TabIndex}
     *
     * @param index index for which tab will be founded
     * @return an instance of {@link TabIndex}
     */
    @Nonnull
    public static TabIndex valueOf(@Nonnegative int index) {
        for (TabIndex tabIndex : values()) {
            if (index == tabIndex.getIndex()) {
                return tabIndex;
            }
        }

        throw new IllegalArgumentException("Tab with index " + index + " is absent...");
    }
}
