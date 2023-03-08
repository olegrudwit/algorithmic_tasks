package orudoi.find_pathes.models;

/*@author Oleg Rudoi
 *@version 1.0
 *@date 07.03.2022
 */

public class CitiesGraph {

    private int citiesQnty; // number of all cities, for initialisation
    private int[][] graph; // two-dimensional array with costs for the Dijkstra algorithm
    private String[] citiesRoster; // list of all cities
    private int[][] paths; // a two-dimensional array with the paths to count,
                           // [][]{start, end}{start, end}...

    public CitiesGraph(int quantity){
        // to init number of all cities
        this.citiesQnty = quantity;
        // init list with only the required number of cities
        citiesRoster = new String[citiesQnty];
        // init graph with only the required number of cities
        graph = new int[citiesQnty][citiesQnty];
    }

    // return graph - array with all costs
    public int[][] getGraph() {
        return graph;
    }

    // sets graph the costs to all neighbors from the model-city
    public void setGraph(City vertex, int cityIndex){
        for(int neigh = 0; neigh < citiesQnty; neigh++){
            graph[cityIndex][neigh] = vertex.getCost(neigh);
        }
    }

    // add name from the model-city to roster
    public void setCitiesRoster(City vertex, int cityIndex) {
        citiesRoster[cityIndex] = vertex.getName();
    }

    // return array with roster of all cities
    public String[] getCitiesRoster() {
        return citiesRoster;
    }

    // initializing the array with the required number of paths
    public void initPaths(int qnty){
        paths = new int[qnty][2];
    }

    // add path to the array of all paths
    public void setPaths(int pathNum, int[] path){
        for (int v = 0; v < 2; v++){
            paths[pathNum][v] = path[v];
        }
    }

    // return the path from array of paths
    public int getPath(int start, int end) {
        return paths[start][end];
    }

    // implementation of Dijkstra's algorithm to find the cheapest route between cities
    public int[] calcMinimumCostPath(int[][] graph, int start){
        // temporary variable for the number of cities
        int lnth = graph.length;
        // array with the cheapest routes from the starting city
        int[] result = new int[lnth];
        // boolean whether the city is visited
        boolean[] used = new boolean[lnth];

        // marking all paths and cities as unvisited
        for (int i = 0; i < lnth; i++) {
            result[i] = Integer.MAX_VALUE;
            used[i] = false;
        }

        // set start place
        result[start] = 0;

        for (int count = 0; count < (lnth - 1); count++) {
            // index of minimum cost
            int min = minDistance(result, used);
            used[min] = true;

            for (int j = 0; j < lnth; j++) {
                /* if:
                1. !used[j] - unvisited
                2. graph[min][j] != 0 - edge exist
                3. result[min] != Integer.MAX_VALUE - edge confirmed
                4. (result[min] + graph[min][j]) - new path less than current
                 */
                if (!used[j] &&
                        (graph[min][j] != 0) &&
                        (result[min] != Integer.MAX_VALUE) &&
                        ((result[min] + graph[min][j]) < result[j])) {
                    // new minimal edge is adding to current
                    result[j] = result[min] + graph[min][j];
                }
            }
        }
        // returns an array with the cheapest routes from the starting city
        return result;
    }
    public int minDistance(int[] dist, boolean[] used){
        int minimal = Integer.MAX_VALUE;
        int index = -1;
        // check for minimal cost between this city and all neighbors
        for (int x = 0; x < dist.length; x++){
            if (!used[x] && dist[x] <= minimal) {
                minimal = dist[x];
                index = x;
            }
        } return index;
    }

    // return cost of path to required city (by index in result array)
    public int getMinimumCostPath(int[] dist, int end){
        return dist[end];
    }
}