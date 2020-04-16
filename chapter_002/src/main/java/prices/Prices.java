package prices;

import prices.models.Price;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prices {


    public List<Price> getUpdatedPrices(List<Price> availablePrices, List<Price> newPrices) {
        List<Price> result = null;
        SortedSet<Price> availablePricesSet = null;

        var c = availablePrices.stream().collect(Collectors.groupingBy(
                key -> key,
                value -> Stream.of(value).sorted(),
                Collectors.toList()

        ));



        var r = availablePrices.stream().collect(Collectors.toMap(key -> key,
                value -> Stream.of(value).sorted(new Comparator<Price>() {
                    @Override
                    public int compare(Price o1, Price o2) {
                        return o1.getEnd().compareTo(o2.getBegin());
                    }
                }).collect(Collectors.toList())));

        return result;
    }
}
