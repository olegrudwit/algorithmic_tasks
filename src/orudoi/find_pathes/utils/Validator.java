package orudoi.find_pathes.utils;

/*@author Oleg Rudoi
 *@version 1.0
 *@date 07.03.2022
 */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Pattern pattern;
    // pattern of the correct city name
    private final String CITY_NAME_PATTERN = "^[a-z]{1,10}$";
    // pattern of correct writing of a search query
    private final String PATH_NAMES_PATTERN = "^[^\s]*\s[^\s]*$";
    private final String MESSAGE_INCORRECT = "Incorrect value! Please try again.";
    private final int MAX_TESTS_QNTY = 10; // [the number of tests <= 10]
    private final int MAX_CITIES_QNTY = 10000; // [the number of cities <= 10000]
    private final int MAX_PATH_COST = 200000; // the cost of each path (...) is at most 200000
    private final int MAX_PATH_QNTY = 100; // [the number of paths to find <= 100]

    public int validateTestsQnty(Scanner scanner, String title) {
        int value = 0;

        do {
            // to print the title of request
            System.out.print(title);
            // check for an integer value
            if (!scanner.hasNextInt()) {
                System.out.println(MESSAGE_INCORRECT);
                scanner.next();
            } else {
                value = scanner.nextInt();
                //check number within range
                if (value <= 0 || value > MAX_TESTS_QNTY) {
                    System.out.println(MESSAGE_INCORRECT);
                    // if not, 0 is returned
                    value = 0;
                }
            }
        } while (value <= 0);

        return value;
    }

    public int validateCitiesQnty(Scanner scanner, String title) {
        int value = 0;

        do {
            // to print the title of request
            System.out.print(title);
            // check for an integer value
            if (!scanner.hasNextInt()) {
                System.out.println("1 " + MESSAGE_INCORRECT);
                scanner.next();
            } else {
                value = scanner.nextInt();
                //check number within range
                if (value <= 0 || value > MAX_CITIES_QNTY) {
                    System.out.println("2 " + MESSAGE_INCORRECT);
                    // if not, 0 is returned
                    value = 0;
                }
            }
        } while (value <= 0);

        return value;
    }

    public String validateName(Scanner scanner, String title) {
        String string;
        pattern = Pattern.compile(CITY_NAME_PATTERN);

        do {
            // to print the title of request
            System.out.print(title);
            string = scanner.next().trim();
            // pattern for comparison [a-z]{1,10}
            Matcher matcher = pattern.matcher(string);

            if (!matcher.matches()) {
                System.out.println(MESSAGE_INCORRECT);
                // if not, empty is returned
                string = "";
            }
        } while (string.isEmpty());

        return string;
    }

    public int validateNeighsQnty(Scanner scanner, String title, int citiesQnty) {
        int value = 0;

        do {
            // to print the title of request
            System.out.print(title);
            // check for an integer value
            if (!scanner.hasNextInt()) {
                System.out.println(MESSAGE_INCORRECT);
                scanner.next();
            } else {
                value = scanner.nextInt();
                //check number within range
                if (value <= 0 || value > (citiesQnty - 1)) {
                    System.out.println(MESSAGE_INCORRECT);
                    // if not, 0 is returned
                    value = 0;
                }
            }
        } while (value <= 0);

        return value;
    }

    public int validateNeighNumber(Scanner scanner, String title, int citiesQnty, int startCityIndex) {
        int value = 0;

        do {
            // to print the title of request
            System.out.print(title);
            // check for an integer value, check number within range
            // by separate method, as variant
            value = isIntegerInRange(scanner, 1, citiesQnty);

        } while (value <= 0 || value == (startCityIndex + 1));

        return value;
    }

    public int validatePathCost(Scanner scanner, String title) {
        int value = 0;

        do {
            // to print the title of request
            System.out.print(title);
            // check for an integer value, check number within range
            // by separate method, as variant
            value = isIntegerInRange(scanner, 1, MAX_PATH_COST);

        } while (value <= 0);

        return value;
    }

    public int validatePathsQnty(Scanner scanner, String title) {
        int value = 0;

        do {
            // to print the title of request
            System.out.print(title);
            // check for an integer value, check number within range
            // by separate method, as variant
            value = isIntegerInRange(scanner, 1, MAX_PATH_QNTY);
        } while (value <= 0);

        return value;
    }

    public int[] validatePathNames(Scanner scanner, String[] list, String title) {
        String string;
        // array for two indexes of cities name from roster
        int[] pathStartEnd = new int[2];

        pattern = Pattern.compile(PATH_NAMES_PATTERN);

        do {
            // to print the title of request
            System.out.print(title);
            string = scanner.nextLine();
            // pattern for comparison two words
            Matcher matcher = pattern.matcher(string);

            if (!matcher.matches()) {
                System.out.println(MESSAGE_INCORRECT);
                string = "";
            } else {
                // split the words to array
                String[] names = string.split("\\s");

                // compare with city-names roster
                pathStartEnd[0] = compareToList(names[0], list);
                pathStartEnd[1] = compareToList(names[1], list);

                // check to unacceptable indexes and equality between start-end
                if (pathStartEnd[0] == -1 ||
                        pathStartEnd[1] == -1 ||
                        pathStartEnd[0] == pathStartEnd[1]) {
                    System.out.println(MESSAGE_INCORRECT);
                    // if not, empty is returned
                    string = "";
                }
            }

        } while (string.isEmpty());

        // return an array with start-end indexes
        return pathStartEnd;
    }

    // method to check for an integer value, check number within range
    private int isIntegerInRange(Scanner scanner, int min, int max) {
        int value = 0;

        if (!scanner.hasNextInt()) {
            System.out.println(MESSAGE_INCORRECT);
            scanner.next();
        } else {
            value = scanner.nextInt();
            if (value < min || value > max) {
                System.out.println(MESSAGE_INCORRECT);
                value = 0;
            }
        }
        return value;
    }

    // to compare with city-names roster
    private int compareToList(String numb, String[] list) {
        for (int i = 0; i < list.length; i++) {
            // compare with every word in roster
            if (numb.equals(list[i])) {
                // return index when found
                return i;
            }
        }
        // return unacceptable index if not
        return -1;
    }
}
