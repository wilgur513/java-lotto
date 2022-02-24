package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static view.MoneyParser.INVALID_MONEY_FORMAT_MESSAGE;

import java.math.BigInteger;
import model.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyParserTest {

    @Test
    @DisplayName("정상적인 금액 입력")
    void parse() {
        MoneyParser parser = new MoneyParser();
        Money value = parser.parse("10000");
        assertThat(value).isEqualTo(new Money(10000));
    }

    @ParameterizedTest
    @ValueSource(strings = {"111", "-1", "numbers", "1111004"})
    @DisplayName("비정상적인 금액 형식 입력")
    void invalidMoneyFormat(String text) {
        MoneyParser parser = new MoneyParser();
        assertThatThrownBy(() -> parser.parse(text))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(INVALID_MONEY_FORMAT_MESSAGE);
    }

}
