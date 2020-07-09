package UserInterface;

import dbconnection.DataBaseContainer;
import dbconnection.connect;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //sets up scene
        primaryStage.setTitle("Chrome History Manager");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 850, 400);
        primaryStage.setScene(scene);
        Button searchButton = new Button();
        searchButton.setText("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            System.out.println("Test");
            }

        });
        // renders everything
        grid.add(searchButton, 0, 1, 2, 1);
        TextField userTextField = new TextField();
        userTextField.setPromptText("Ex. google.com");
        userTextField.textProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                        listenerMethod(t1, primaryStage);
                    }
                }
        );
        //userTextField.setPrefWidth(30);
        grid.add(userTextField, 2, 1, 2, 1);

        ObservableList<PieChart.Data> pieChartData = PieChartData.createChartData();
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Websites");
        chart.setLegendSide(Side.RIGHT);
        grid.add(chart, 5, 1);
        primaryStage.show();

    }

    private void listenerMethod(String t1, Stage primaryStage) {
        List<DataBaseContainer> ListContainer;
        ListContainer = null;
        try {
            ListContainer = connect.Connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<HashMap<String, Integer>> urlViewLists = DatabaseParser.getResults(t1, ListContainer);

        if (urlViewLists != null) {
            List<String> stringUrlList = Labels.createStringForLabel(urlViewLists);
            List<Label> labellist = Labels.createLabel(stringUrlList);
            ScrollPane sp = new ScrollPane();
            GridPane secondaryLayout = new GridPane();
            sp.setContent(secondaryLayout);
            Labels.loadLabels(labellist, secondaryLayout);


            Scene secondScene = new Scene(secondaryLayout, 230, 100);

            // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Results in " + t1);
            newWindow.setScene(secondScene);

            // Set position of second window, related to primary window.
            newWindow.setX(primaryStage.getX() + 200);
            newWindow.setY(primaryStage.getY() + 100);

            newWindow.show();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}
