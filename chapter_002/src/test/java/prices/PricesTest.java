package prices;

import org.junit.Test;
import prices.models.Price;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PricesTest {

    @Test
    public void getUpdatedPrices() throws Exception {
        var avalableList = List.of(
                new Price(0L, "100", 1, 10000,
                        new SimpleDateFormat("dd/MM/yyyy").parse("06/01/2000"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2000"), 999L),
                new Price(1L, "100", 2, 10000,
                        new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2000"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("03/01/2000"), 888L),
                new Price(2L, "100", 3, 10000,
                        new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("08/01/2000"), 777L),
                new Price(3L, "200", 1, 10000,
                        new SimpleDateFormat("dd/MM/yyyy").parse("05/01/2000"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("06/01/2000"), 1111L),
                new Price(4L, "200", 2, 10000,
                        new SimpleDateFormat("dd/MM/yyyy").parse("03/01/2000"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("05/01/2000"), 5L),
                new Price(5L, "200", 3, 10000,
                        new SimpleDateFormat("dd/MM/yyyy").parse("06/01/2000"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2000"), 12345L)
        );
        new Prices().getUpdatedPrices(avalableList, null);
    }
}