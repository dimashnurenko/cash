package dmitry.shnurenko.cash.server.currency;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * The class contains business logic to get information from special place on site finance.ua
 *
 * @author Dmitry Shnurenko
 */
@Component
final class FinanceUAParser implements Parser {

    private Document   document;
    private BigDecimal usdCourse;
    private BigDecimal eurCourse;
    private BigDecimal rubCourse;

    public FinanceUAParser() {
    }

    /** {inheritDoc} */
    @Override
    public void connect(@Nonnull String url) throws IOException {
        document = Jsoup.connect(url).get();
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public FinanceUAParser setUSDCourse(@Nonnull BigDecimal usdCourse) {
        this.usdCourse = usdCourse;
        return this;
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public FinanceUAParser setEURCourse(@Nonnull BigDecimal eurCourse) {
        this.eurCourse = eurCourse;
        return this;
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public FinanceUAParser setRUBCourse(@Nonnull BigDecimal rubCourse) {
        this.rubCourse = rubCourse;
        return this;
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public BigDecimal getUSDCourse() {
        return document == null ? usdCourse : getTDElements(2);
    }

    @Nonnull
    private BigDecimal getTDElements(@Nonnegative int index) {
        Element courseTable = document.select("table").get(4);
        Elements tBody = courseTable.select("tbody").select("tr").select("td");

        if (tBody == null || tBody.isEmpty()) {
            return new BigDecimal(0);
        }

        Element usdCourse = tBody.get(index);

        String course = usdCourse.text();

        return new BigDecimal(course);
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public BigDecimal getEURCourse() {
        return document == null ? eurCourse : getTDElements(5);
    }

    /** {inheritDoc} */
    @Nonnull
    @Override
    public BigDecimal getRUBCourse() {
        return document == null ? rubCourse : getTDElements(8);
    }
}
