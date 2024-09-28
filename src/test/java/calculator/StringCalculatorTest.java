package calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculatorTest {

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "   "})
    void 빈_문자열_입력_시_0_반환(final String input){
        int result = StringCalculator.splitAndSum(input);
        assertThat(result).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "123", "45678912"})
    void 숫자만_입력시_숫자_반환(final String input ){
        int result = StringCalculator.splitAndSum(input);
        assertThat(result).isEqualTo(Integer.parseInt(input));
    }


    @ParameterizedTest
    @CsvSource(value = {
            "1,2=3",
            "4,5=9",
            "1,2,=3",
            "4,5,=9",
            "1,=1",
            "1,2,3,4=10",
            "1,2,3,4,=10"
    }, delimiter = '=')
    void 쉼표로_구분된_여러_숫자_입력_시_합_반환(final String input, final Integer expected){
        Integer result = StringCalculator.splitAndSum(input);
        assertThat(expected).isEqualTo(result);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "1,2:=3",
            "4,5:=9",
            "1,2:3,4=10",
            "1,2,3:4,=10"
    }, delimiter = '=')
    void 구분자가_쉼표와_콜론일때_합_반환(final String input, final Integer expected){
        Integer result = StringCalculator.splitAndSum(input);
        assertThat(expected).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'//;\n1;2;3', 6",
            "'//|\n1|2|3', 6",
            "'//,\n1,2,3', 6",
            "'//:\n1:2:3', 6",
            "'//#\n1#2#3', 6",
            "'//;\n1;2;3;4', 10",
            "'//,|\n1,2|3,4', 10"
    })
    void 커스텀_구분자를_사용한_합_반환(final String input, final Integer expected) {
        Integer result = StringCalculator.splitAndSum(input);
        assertThat(expected).isEqualTo(result);
    }
}
