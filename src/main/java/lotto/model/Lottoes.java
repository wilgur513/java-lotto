package lotto.model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Lottoes implements Iterable<Lotto>{

    private final List<Lotto> lottoes;

    public Lottoes(List<Lotto> lottoes) {
        this.lottoes = List.copyOf(lottoes);
    }

    public Stream<Lotto> stream() {
        return lottoes.stream();
    }

    public int size() {
        return lottoes.size();
    }

    public Lottoes combine(Lottoes other) {
        return Stream.concat(stream(), other.stream())
            .collect(collectingAndThen(toList(), Lottoes::new));
    }

    public Money price() {
        return Lotto.PRICE.multiply(lottoes.size());
    }

    @Override
    public Iterator<Lotto> iterator() {
        return lottoes.iterator();
    }
}
