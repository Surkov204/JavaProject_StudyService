package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Validation {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getString(String msg) {
        String value;
        while (true) {
            if (msg != null) {
                System.out.print(msg);
            }
            value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                break;
            }
            System.err.println("Please enter again...");
        }
        return value;
    }
    
    public static Date getDate(String msg) {
        Date value;
        while (true) {
            try {
                System.out.print(msg);
                value = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
                break;
            } catch (ParseException e) {
                System.err.println("Invalid date. Please try again.");
            }
        }
        return value;
    }
}