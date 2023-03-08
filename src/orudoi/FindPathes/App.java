package orudoi.FindPathes;

/*@author Oleg Rudoi
 *@version 1.0
 *@date 07.03.2022
 */

import orudoi.FindPathes.controllers.PathsController;
import orudoi.FindPathes.models.City;
import orudoi.FindPathes.views.PathsView;

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
