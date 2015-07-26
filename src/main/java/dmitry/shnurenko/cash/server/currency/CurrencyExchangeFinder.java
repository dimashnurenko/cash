package dmitry.shnurenko.cash.server.currency;

import dmitry.shnurenko.cash.server.entity.CurrencyExchange;

import javax.annotation.Nonnull;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * Provides methods which allows connect to definite site and get information about proceed exchange.
 * Now we can get information only from finance.ua
 *
 * @author Dmitry Shnurenko
 */
public interface CurrencyExchangeFinder {

    /**
     * Connects to specified site using URL address of site.
     *
     * @param urlAddress URL address of site
     * @throws UnknownHostException if using URL is incorrect of site not found or can't connect to internet.
     */
    void connectToSite(@Nonnull String urlAddress) throws UnknownHostException;

    /**
     * Returns special object {@link CurrencyExchange} which contains information about proceed exchange. Also method
     * saves proceed exchange course to data base.
     *
     * @return an instance of {@link CurrencyExchange}
     * @throws ExecutionException   when can't find needed information in special place
     * @throws InterruptedException
     */
    CurrencyExchange getCurrencyExchange() throws ExecutionException, InterruptedException;
}
