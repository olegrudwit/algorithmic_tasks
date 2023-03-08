package orudoi.find_pathes.models;

/*@author Oleg Rudoi
 *@version 1.0
 *@date 07.03.2022
 */

public class City {
    private int cityIndex; // zero-based city index, to construct graph
    private String name; // NAME [city name]
    private int neighsQnty; // p [the number of neighbors of city NAME]
    private int[] pathsCosts = new int[10000]; // array with cost to neighboring cities

    public int getCityIndex() {
        return cityIndex;
    }

    public void setCityIndex(int cityIndex) {
        this.cityIndex = cityIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeighsQnty() {
        return neighsQnty;
    }

    public void setNeighsQnty(int neighsQnty) {
        this.neighsQnty = neighsQnty;
    }

    // initialization of the array with only the required number of cities
    public void initCostsArray(int citiesQnty){
        pathsCosts = new int[citiesQnty];
    }

    // returns the value of the cost to the required city (index in the array)
    public int getCost(int index) {
        return pathsCosts[index];
    }

    // sets the value of the cost to the required city (index in the array)
    public void setPathsCosts(int index, int cost) {
        pathsCosts[index] = cost;
    }

    // return the array with cost to all neighboring cities
    public int[] getPathsCosts() {
        return pathsCosts;
    }

}