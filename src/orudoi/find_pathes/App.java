package orudoi.find_pathes;

/*@author Oleg Rudoi
 *@version 1.0
 *@date 07.03.2022
 */

import orudoi.find_pathes.controllers.PathsController;
import orudoi.find_pathes.models.City;
import orudoi.find_pathes.views.PathsView;

public class App {

    public static void main(String[] args) {
        // init MVC
        City model = new City();
        PathsView view = new PathsView(model);
        PathsController controller = new PathsController(model, view);

        // run app from Controller
        controller.runApp();
    }
}
