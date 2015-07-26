package dmitry.shnurenko.cash.server.currency;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Parser which allows get information about proceed exchange from different resources. For each resource is needed
 * special implementation of parser
 *
 * @author Dmitry Shnurenko
 */
interface Parser {
    /**
     * Connects to needed site using special url.
     *
     * @param url url of site to connect
     * @throws IOException when site not founded or internet connection is lost
     */
    void connect(@Nonnull String url) throws IOException;

    /**
     * Sets custom usd course to parser.
     *
     * @param usdCourse usd course which need to set
     * @return an instance of {@link Parser} with course which we set
     */
    @Nonnull Parser setUSDCourse(@Nonnull BigDecimal usdCourse);

    /**
     * Sets custom euro course to parser.
     *
     * @param eurCourse euro course which need to set
     * @return an instance of {@link Parser} with course which we set
     */
    @Nonnull Parser setEURCourse(@Nonnull BigDecimal eurCourse);

    /**
     * Sets custom rub course to parser.
     *
     * @param rubCourse rub course which need to set
     * @return an instance of {@link Parser} with course which we set
     */
    @Nonnull Parser setRUBCourse(@Nonnull BigDecimal rubCourse);

    /** Returns usd course as {@link BigDecimal} object. */
    @Nonnull BigDecimal getUSDCourse();

    /** Returns euro course as {@link BigDecimal} object. */
    @Nonnull BigDecimal getEURCourse();

    /** Returns rub course as {@link BigDecimal} object. */
    @Nonnull BigDecimal getRUBCourse();
}
