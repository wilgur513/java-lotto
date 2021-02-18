package lotto.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getInputLine() {
        return SCANNER.nextLine();
    }
}