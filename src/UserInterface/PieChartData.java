package UserInterface;

import dbconnection.DataBaseContainer;
import dbconnection.connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieChartData {
    public static HashMap<String, Integer> createURLHashMap(List<DataBaseContainer> listContainer) {
        HashMap<String, Integer> url_views = new HashMap<>();

        for (DataBaseContainer container : listContainer) {
            if (url_views.containsKey(container.getSplitUrl().get(2))) {
                int current_value = container.getVisit_duration_secs();
                url_views.put(container.getSplitUrl().get(2), current_value + container.getVisit_duration_secs());
            } else {
                url_views.put(container.getSplitUrl().get(2), container.getVisit_duration_secs());
            }
        }

        return url_views;
    }

    public static ObservableList<javafx.scene.chart.PieChart.Data> createChartData() throws SQLException {
        List<DataBaseContainer> ListContainer = connect.Connect();
        ObservableList<javafx.scene.chart.PieChart.Data> pieChartData = FXCollections.observableArrayList();
        HashMap<String, Integer> url_view = createURLHashMap(ListContainer);
        for (Map.Entry mapElement : url_view.entrySet()) {
            String key = (String) mapElement.getKey();
            double val = (int) mapElement.getValue();
            javafx.scene.chart.PieChart.Data keyval = new javafx.scene.chart.PieChart.Data(key, val);
            pieChartData.add(keyval);
        }
        return pieChartData;
    }
}
