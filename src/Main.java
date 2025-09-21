import service.AtmService;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author Ali Sharifi
 * <p>ali.sharifi.dev@gmail.com<p>
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AtmService atmService = new AtmService();

    public static void main(String[] args) {
        System.out.println("Welcome to ATM service");
        while (true) {
            System.out.println("Options: 1. Insert Card, 2. Exit");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            if ("2".equals(choice)) break;
            if ("1".equals(choice)) handleSession();
        }
        System.out.println("Goodbye!");
    }

    private static void handleSession() {
        System.out.println("Enter card number: ");
        String cardNumber = scanner.nextLine().trim();
        System.out.println("Enter pin: ");
        String pin = scanner.nextLine().trim();

        try {
            if (!atmService.authenticate(cardNumber, pin))
                return;
        } catch (RuntimeException e) {
            return;
        }


        boolean exit = false;
        while (!exit) {
            System.out.println("1. Withdraw, 2. End session");
            System.out.println("Enter your choice: ");
            String choice = scanner.nextLine().trim();
            if ("2".equals(choice)) exit = true;
            if ("1".equals(choice)) {
                System.out.println("Enter amount to withdraw: ");
                String line = scanner.nextLine().trim();
                int amount = Integer.parseInt(line);
                try {
                    var result = atmService.withdraw(new BigDecimal(amount));

                    if (!result.isSuccess()) {
                        System.out.println(result.getMessage());
                        break;
                    }

                    System.out.println("Please pick ur card");
                    System.out.println("Please collect your cash: " + result.getDispensed().toString());
                    System.out.println("Remaining balance: " + result.getRemaining());
                    exit = true;

                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }
        atmService.endSession();
        System.out.println("Session Ended, Card ejected");

    }
}
