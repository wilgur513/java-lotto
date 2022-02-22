import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

public class LottoNumberParser {

    private static final int LOTTO_NUMBER_LENGTH = 6;
    private static final String REGEX_DELIMITER = ",";

    static final String INVALID_LOTTO_NUMBER_LENGTH_MESSAGE = "당첨 번호는 6개여야 합니다.";
    static final String DUPLICATED_LOTTO_NUMBER_MESSAGE = "중복된 당첨 번호는 허용하지 않습니다.";

    public List<Integer> parse(String numbers) {
        List<String> tokens = splitNumbers(numbers);
        List<String> trimNumbers = trimNumbers(tokens);
        checkLottoNumber(trimNumbers);
        return toInts(trimNumbers);
    }

    private List<Integer> toInts(List<String> trimNumbers) {
        return trimNumbers.stream()
                .map(Integer::valueOf)
                .collect(toList());
    }

    private void checkLottoNumber(List<String> tokens) {
        checkLottoNumberLength(tokens);
        checkDuplicatedLottoNumber(tokens);
    }

    private List<String> splitNumbers(String numbers) {
        return List.of(numbers.split(REGEX_DELIMITER));
    }

    private List<String> trimNumbers(List<String> numbers) {
        return numbers.stream()
                .map(String::trim)
                .collect(toList());
    }

    private void checkLottoNumberLength(List<String> tokens) {
        if (tokens.size() != LOTTO_NUMBER_LENGTH) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_LENGTH_MESSAGE);
        }
    }

    private void checkDuplicatedLottoNumber(List<String> result) {
        if (hasDuplicatedNumber(result)) {
            throw new IllegalArgumentException(DUPLICATED_LOTTO_NUMBER_MESSAGE);
        }
    }

    private boolean hasDuplicatedNumber(List<String> result) {
        return getDistinctSize(result) != result.size();
    }

    private long getDistinctSize(List<String> result) {
        return result.stream()
                .distinct()
                .count();
    }
}
