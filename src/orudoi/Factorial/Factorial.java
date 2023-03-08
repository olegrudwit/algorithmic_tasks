package orudoi.Factorial;
/*
@author Oleg Rudoi
@version 1.0
 */

import java.math.BigInteger;

public class Factorial {

    // declaration of a variable number
    public static int number;

    public static void main(String[] args) {
        // assigning a value to a variable
        number = 100;
        // call to execute methods
        showData(calcSum(getFactorial(number)));
    }

    /*
    to determine the factorial of a number greater than 39
    it is necessary to use the BigInteger class, which contains more characters than the long
     */
    // the recursive method returns the value of the factorial of a given number through an argument
    public static BigInteger getFactorial(int num) {
        // in case the number 1 is entered or when the recursion reaches the value 1
        if (num <= 1) {
            return BigInteger.ONE;
        }
        else {
            // calls the method of multiplying the number N by the number N-1,
            // which is called through recursion of this method
            return BigInteger.valueOf(num).multiply(getFactorial(num - 1));
        }
    }

    // method returns the value of the sum of the digits of the BigInteger number
    public static int calcSum(BigInteger value) {
        int sum = 0;
        // initialization of the variable for the counter.
        // Determines the number of characters in the BigInteger number and assigns it to the counter
        int count = value.toString(10).length();
        // assigns the value of a number to a temporary variable
        BigInteger temp = value;

        // loop to search all digits in the number
        while (count > 0) {
            // the value of the sum of digits is determined from the remainder after division by 10
            sum = sum + temp.mod(BigInteger.TEN).intValue();
            //division of the prime number by 10
            temp = temp.divide(BigInteger.TEN);
            // subtraction of the counter
            count--;
        }
        return sum;
    }

    // viewing method
    public static void showData(int result) {
        System.out.println(result);
    }
}