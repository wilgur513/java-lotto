package lotto.model;

import static java.util.stream.Collectors.toMap;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Statistic {

    private final Map<Rank, Integer> ranks;

    public Statistic(Collection<Rank> ranks) {
        this.ranks = Collections.unmodifiableMap(collectRanks(ranks));
    }

    private Map<Rank, Integer> collectRanks(Collection<Rank> ranks) {
        return ranks.stream()
            .collect(toMap(r -> r, r -> 1, Integer::sum, () -> new EnumMap<>(Rank.class)));
    }

    public int countBy(Rank rank) {
        return ranks.getOrDefault(rank, 0);
    }

    public ProfitRate profitRate() {
        return new ProfitRate(rate());
    }

    private BigDecimal rate() {
        if (lottoQuantity() == 0) {
            return ProfitRate.PROFIT_PRINCIPAL_RATE;
        }
        return totalPrize().divide(totalLottoPrice());
    }

    private int lottoQuantity() {
        return ranks.values().stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    private Money totalLottoPrice() {
        return Lotto.PRICE.multiply(lottoQuantity());
    }

    private Money totalPrize() {
        return ranks.keySet().stream()
            .map(this::eachRankTotalPrize)
            .reduce(Money.ZERO, Money::plus);
    }

    private Money eachRankTotalPrize(Rank rank) {
        return rank.prize().multiply(countBy(rank));
    }
}
