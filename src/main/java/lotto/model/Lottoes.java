package lotto.model;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Lottoes implements Iterable<Lotto>{

    private final List<Lotto> lottoes;

    public Lottoes(List<Lotto> lottoes) {
        this.lottoes = Collections.unmodifiableList(lottoes);
    }

    public <R> Collection<R> mapAndCollect(Function<Lotto, R> mapper) {
        return lottoes.stream().map(mapper).collect(toUnmodifiableList());
    }

    public int size() {
        return lottoes.size();
    }

    public Lottoes combine(Lottoes other) {
        List<Lotto> result = new ArrayList<>();
        result.addAll(lottoes);
        result.addAll(other.lottoes);
        return new Lottoes(result);
    }

    public Money getPrice() {
        return Lotto.PRICE.multiply(lottoes.size());
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoes.iterator();
    }

    public static Lottoes empty() {
        return new Lottoes(Collections.emptyList());
    }
}
