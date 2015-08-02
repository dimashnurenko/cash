package dmitry.shnurenko.cash.server.factories;

import dmitry.shnurenko.cash.server.entity.Cash;
import dmitry.shnurenko.cash.server.entity.OperationType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static dmitry.shnurenko.cash.server.entity.OperationType.ADD_CASH;
import static org.junit.Assert.assertEquals;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class EntityFactoryTest {

    @InjectMocks
    private EntityFactory factory;

    @Test
    public void cashShouldBeCreated() {
        LocalDateTime dateTime = LocalDateTime.now();
        BigDecimal sum = new BigDecimal(1);
        String description = "description";
        OperationType type = ADD_CASH;

        Cash cash = factory.createCash(dateTime, sum, description, type);

        assertEquals(dateTime, cash.getDate());
        assertEquals(sum, cash.getSum());
        assertEquals(description, cash.getDescription());
        assertEquals(type, cash.getOperationType());
    }
}