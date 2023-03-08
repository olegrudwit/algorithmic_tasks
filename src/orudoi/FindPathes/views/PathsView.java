package orudoi.FindPathes.views;

/*@author Oleg Rudoi
 *@version 1.0
 *@date 07.03.2022
 */

import orudoi.FindPathes.models.CitiesGraph;
import orudoi.FindPathes.models.City;
import orudoi.FindPathes.utils.Validator;

import java.util.Scanner;

public class PathsView {

    City city; // model
    public CitiesGraph graph;
    public static int testsQnty; // s [the number of tests <= 10] (to repeat all program)
    public static int citiesQnty; // n [the number of cities <= 10,000]
    public static String name; // NAME [city name]
    public static int neighsQnty; // p [the number of neighbors of city NAME]
    public static int cost; // nr cost [nr - index of a city connected to NAME (the index of the first city is 1)]
    // [cost - the transportation cost]
    public static int pathsQnty; // r [the number of paths to find <= 100]
    public static int[] path;

    public PathsView(City city) {
        this.city = city;
    }

    // to input quantity of tests
    public void getTestsQnty(){
        System.out.println("To calculate the duration of the trip, please type the necessary information\n");

        // using to validate input values
        Validator val3 = new Validator();
        Scanner scan3 = new Scanner(System.in);

        String title;
        title = "Quantity of search tests (from 1 to 10): ";
        testsQnty = val3.validateTestsQnty(scan3, title);

    }

    // to input other information
    public void getInputs(){
        Validator val = new Validator();
        Scanner scan = new Scanner(System.in);

        String title;
        title = "Quantity of search cities (from 1 to 10,000): ";
        citiesQnty = val.validateCitiesQnty(scan, title);

        // to initialize the array with only the required number of cities
        city.initCostsArray(citiesQnty);

        // to initialize Object with only the required number of cities
        graph = new CitiesGraph(citiesQnty);

        // counter of city's index in graph, zero-based
        int cityIndex = 0;
        // repeat to all cities
        for (int i = 0; i < citiesQnty; ++i) {
            // to set index into current object City
            city.setCityIndex(cityIndex);

            // for erase array values
            for (int v = 0; v < citiesQnty; v++) {
                city.setPathsCosts(v, 0);
            }

            title = "Name of this city: ";
            name = val.validateName(scan, title);
            // set name to object
            city.setName(name);

            title = "Quantity of neighbors of this city (no more than cities in search): ";
            // validate the quantity of neighbors (neighsQnty < citiesQnty)
            neighsQnty = val.validateNeighsQnty(scan, title, citiesQnty);
            city.setNeighsQnty(neighsQnty);

            for (int j = 0; j < neighsQnty; ++j) {
                // index of neighbor for array, 0 to erase current
                int neighIndex = 0;

                title = "Number of neighbor city: ";
                // user puts 1-based, but index is 0-based
                neighIndex = val.validateNeighNumber(scan, title, citiesQnty, cityIndex) - 1;

                title = "Transportation cost to this neighbor (from 1 to 200,000): ";
                cost = val.validatePathCost(scan, title);
                // set the value to right place in array
                city.setPathsCosts(neighIndex, cost);
            }

            // add graph with current city object
            graph.setGraph(city, city.getCityIndex());

            // add city roster with current city
            graph.setCitiesRoster(city, city.getCityIndex());

            // increase counter
            cityIndex++;
        }

        title = "Quantity of paths to find  (from 1 to 100): ";
        pathsQnty = val.validatePathsQnty(scan, title);

        // to initialize the path-array with only the required number of paths
        graph.initPaths(pathsQnty);

        // new Scanner to avoid processing errors nextInt->nextLine
        Scanner scan2 = new Scanner(System.in);

        // counter of path's index in graph, zero-based
        int pathIndex = 0;
        for (int i = 0; i < pathsQnty; i++) {
            title = "Names of cities (pattern \"source destination\"): ";
            // compares typed words with the elements of a list of cities
            // build an array [2]{start, end}
            path = val.validatePathNames(scan2, graph.getCitiesRoster(), title);

            // set the value to right place in array
            graph.setPaths(pathIndex, path);

            // increase counter
            pathIndex++;
        }
            /* the scanner must be closed to avoid data leakage,
             * but this causes a processing error
             * At this time, I haven't solved this problem any other way
             */
//        scan.close();
//        scan2.close();
    }

    // prints the completed String output
    public void getOutput(String output){
        System.out.println(output);
    }
}
