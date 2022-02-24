package lotto.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProfitRate {

    private final BigDecimal rate;

    public ProfitRate(BigDecimal rate) {
        this.rate = rate;
    }

    public double getDoubleValue() {
        return rate.doubleValue();
    }

    public boolean isProfit() {
        return rate.compareTo(BigDecimal.ONE) == 1;
    }

    public boolean isLoss() {
        return rate.compareTo(BigDecimal.ONE) == -1;
    }

    public boolean isPrincipal() {
        return rate.compareTo(BigDecimal.ONE) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProfitRate that = (ProfitRate) o;
        return Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rate);
    }
}
