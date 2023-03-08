package orudoi.ParenthesisPairs;
/*
@author Oleg Rudoi
@version 1.0
 */

import java.util.Scanner;

public class ParenthesisPairs {

    // declaration of a variable number
    public static int quantity;

    public static void main(String[] args) {
        doInputs();
        // call to execute methods
        showData(calcParenthesisPairs(quantity));
    }

    public static void doInputs() {
        Scanner scan = new Scanner(System.in);
        // assign value to variable after validation
        quantity = validateQuantity(scan);

        scan.close();
    }

    // validating that the typed numeric, integer and positive value
    public static int validateQuantity(Scanner scan) {
        int qnty = 0;
        do {
            System.out.print("To determine the number of parenthesis pairs that match correctly,\n" +
                    "please type the number of opening (or closing) parentheses N: ");
            // the nested scanner method verifies that the value entered is an integer,
            // not a letter, symbol, or fractional number
            if (!scan.hasNextInt()) {
                // if a non-integer is entered, an error message is received
                // and the loop is restarted
                System.out.println("Not a number! Please try again");
                scan.next();
            } else
                // if an integer is entered, it is written to a variable
                qnty = scan.nextInt();
        // the received integer value is checked whether it is more.
        // The cycle is restarted if it is not so
        } while (qnty <= 0);

        return qnty;
    }

    // The method calculates the number of valid sequences of parentheses.
    // To perform the task, use the recurrent relation of the Catalan.
    public static long calcParenthesisPairs(int qnty) {
        // initialization of the array to record the results of the cycle
        long[] res = new long[qnty + 1];

        res[0] = 1;
        // cycle to determine the multiplier C0 in the expression
        for (int i = 1; i <= qnty; i++) {
            res[i] = 0;
            // recursive cycle to determine the product C0 * Cn in the expression
            for (int j = 0; j < i; j++) {
                res[i] += res[j] * res[i - 1 - j];
            }
        }
        //return count of valid sequences of parentheses
        return res[qnty];
    }

    // viewing method
    public static void showData(long result) {
        System.out.println(result);
    }
}
