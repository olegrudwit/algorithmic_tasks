package orudoi.find_pathes.controllers;

/*@author Oleg Rudoi
 *@version 1.0
 *@date 07.03.2022
 */

import orudoi.find_pathes.models.City;
import orudoi.find_pathes.views.PathsView;

public class PathsController {
    City model;
    PathsView view;

    public PathsController(City model, PathsView view) {
        this.model = model;
        this.view = view;
    }

    public void runApp(){

        // to define quantity of tests
        view.getTestsQnty();

        // repeat for required quantity of tests
        for (int test = 0; test < PathsView.testsQnty; test++) {
            // call the input method of other information
            view.getInputs();

            // for appending result at the end of string
            StringBuilder output = new StringBuilder();

            // repeat for required quantity of paths
            for (int i = 0; i < PathsView.pathsQnty; i++) {
                /* I don't think that this is how I can to call the graph through the view (by MVC)
                 * but else causes a processing error
                 * At this time, I haven't solved this problem any other way
                 */

                //set start city, get from paths roster
                int start = view.graph.getPath(i, 0);
                //set end city, get from paths roster
                int end = view.graph.getPath(i, 1);

                // calc the paths from start-city
                int[] dists = view.graph.calcMinimumCostPath(view.graph.getGraph(), start);
                // return the cost to end-city from result array
                int dist = (view.graph.getMinimumCostPath(dists, end));

                // append result at the end of string
                output.append("\n").append(dist);
            }

            // call the output method, with ready output-string
            view.getOutput(output + "\n");
        }
    }
}
