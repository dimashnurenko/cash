package dmitry.shnurenko.cash.server.currency;

import dmitry.shnurenko.cash.server.dao.currencyexchange.CurrencyExchangeDao;
import dmitry.shnurenko.cash.server.entity.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static dmitry.shnurenko.cash.Utils.DATE_TIME_FORMATTER;

/**
 * The class contains business logic which allows connect to definite site and get information
 * about currency exchange.
 *
 * @author Dmitry Shnurenko
 */
@Component
final class CurrencyExchangeFinderImpl implements CurrencyExchangeFinder {

    static final String DEFAULT_USD_COURSE = "21";
    static final String DEFAULT_EUR_COURSE = "24";
    static final String DEFAULT_RUB_COURSE = "0.38";

    private final CurrencyExchangeDao exchangeDao;
    private final Parser              parser;

    private boolean isConnected;

    @Autowired
    public CurrencyExchangeFinderImpl(CurrencyExchangeDao exchangeDao, Parser parser) {
        this.exchangeDao = exchangeDao;
        this.parser = parser;
    }

    /** {inheritDoc} */
    @Override
    public void connectToSite(@Nonnull String urlAddress) throws UnknownHostException {
        try {
            parser.connect(urlAddress);

            isConnected = true;
        } catch (IOException e) {
            isConnected = false;

            CurrencyExchange exchange = exchangeDao.getLastSaved();

            boolean isExchangeExist = exchange != null;

            parser.setEURCourse(isExchangeExist ? exchange.getEurCourse() : new BigDecimal(DEFAULT_EUR_COURSE))
                  .setUSDCourse(isExchangeExist ? exchange.getUsdCourse() : new BigDecimal(DEFAULT_USD_COURSE))
                  .setRUBCourse(isExchangeExist ? exchange.getRubCourse() : new BigDecimal(DEFAULT_RUB_COURSE));

            throw new UnknownHostException(
                    "Can't connect to internet. The currency exchange is actual on " +
                            (isExchangeExist ? DATE_TIME_FORMATTER.format(exchange.getDate()) : " 27.06.2015")
            );
        }
    }

    /** {inheritDoc} */
    @Override
    @Nonnull
    public CurrencyExchange getCurrencyExchange() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<BigDecimal> usdFuture = executorService.submit(parser::getUSDCourse);
        Future<BigDecimal> eurFuture = executorService.submit(parser::getEURCourse);
        Future<BigDecimal> rubFuture = executorService.submit(parser::getRUBCourse);

        CurrencyExchange currencyExchange = CurrencyExchange.newBuilder()
                                                            .setDate(LocalDateTime.now())
                                                            .setUSDCourse(usdFuture.get())
                                                            .setEURCourse(eurFuture.get())
                                                            .setRUBCourse(rubFuture.get()).build();
        if (isConnected) {
            exchangeDao.save(currencyExchange);
        }

        return currencyExchange;
    }
}
