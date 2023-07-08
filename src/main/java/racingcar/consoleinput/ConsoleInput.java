package racingcar.consoleinput;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import racingcar.controller.extension.input.Inputable;

public class ConsoleInput implements Inputable {

    private static final String CAR_NAME_SEPARATE_EXPRESSION = ",";

    private ConsoleInput() {
    }

    public static ConsoleInput getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public int inputRound() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("시도할 회수는 몇회인가요?");
        return scanner.nextInt();
    }

    @Override
    public List<String> inputCarNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return Arrays.asList(scanner.next().split(CAR_NAME_SEPARATE_EXPRESSION));
    }

    private static final class SingletonHelper {

        private static final ConsoleInput INSTANCE = new ConsoleInput();

    }

}
