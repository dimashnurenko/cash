package dmitry.shnurenko.cash.server.factories;

import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.server.entity.CashType;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Special factory which allows us create different entities.
 *
 * @author Dmitry Shnurenko
 */
@Component
public class EntityFactory {

    /**
     * Creates {@link Cash} entity using special information. Each call of this method return new object.
     *
     * @param date        date when user add or loan cash
     * @param sum         sum which user add or loan
     * @param description additional information about cash
     * @param cashType    there are two types. When we add cash type is ADD_CASH and we loan cash type is LOAN_CASH
     * @return an instance of {@link Cash}
     */
    public Cash createCash(@Nonnull LocalDateTime date,
                           @Nonnull BigDecimal sum,
                           @Nullable String description,
                           @Nonnull CashType cashType) {

        return new Cash(date, sum, description, cashType);
    }
}
